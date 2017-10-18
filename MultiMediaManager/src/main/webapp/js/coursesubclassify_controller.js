var CoursesubclassifyManageModule = angular.module('CoursesubclassifyManageModule',[]);
CoursesubclassifyManageModule.controller('coursesubclassifyManageCtrl',function($scope,$http){
	$('body,html').animate({scrollTop:0},50);
	var URL = "api/mfilesubcate/list.show";
	$scope.cateValue = '0';
	$http({
		method : "post",
		url : URL,
		params : {
			pageNum : "1",
			pageSize : "10",
			fileCateId : $scope.cateValue
		}
	}).success(function(response) {
		if (response.status == 1) {
			$scope.list = response.data.data.list;
			$scope.pageCount = response.data.data.pages;
			$scope.currentPage = response.data.data.pageNum;
			$scope.totalCount = response.data.data.total;
		}
	}).error(function(response){
	});
	$http({
		method : "post",
		url : "api/mfilecate/list.show",
		params : {
			pageNum : "1",
			pageSize : "10"
		}
	}).success(function(response) {
		if (response.status == 1) {
			$scope.catelist = response.data.data.list;
		}
	}).error(function(response){
	});
	
	$scope.catefilt = function(){
		$http({
			method : "post",
			url : URL,
			params : {
				pageNum : "1",
				pageSize : "10",
				fileCateId : $scope.cateValue
			}
		}).success(function(response) {
			if (response.status == 1) {
				$scope.list = response.data.data.list;
				$scope.pageCount = response.data.data.pages;
				$scope.currentPage = response.data.data.pageNum;
				$scope.totalCount = response.data.data.total;
			}
		}).error(function(response){
		});
	}
	$scope.add = function() {
		$("#addcoursesubclassname").val("");
		$("#addcoursesubclasssort").val("");
	};
	
	$scope.addCourseSubClassify = function(){
		var cate_id = $("#addCateId").val();
		var name = $("#addcoursesubclassname").val();
		var sort = $("#addcoursesubclasssort").val();
		if(name == ""){
			showtip('请填写课件子分类名称!', 'red');
			return;
		} 
		if(sort == ""){
			showtip('请填写课件子分类排序!', 'red');
			return;
		}
		$http({
			method:"post",
			url:"api/mfilesubcate/add.do",
			params:{
				fileCateId:cate_id,
				name:name,
				sort:sort,
			}
			}).success(function(response){
				if(response.status == 1){
					showtip('添加成功!', 'green');
					$(".close").click();
					$http({
						method:"post",
						url:"api/mfilesubcate/list.show",
						params:{
							pageNum:"1",
							pageSize:"10",
							fileCateId : $scope.cateValue
						}
						}).success(function(response){
							if(response.status == 1){
								$scope.list = response.data.data.list;
								$scope.pageCount = response.data.data.pages;
								$scope.currentPage = response.data.data.pageNum;
								$scope.totalCount = response.data.data.total;
							}
						});
				} else {
					showtip('添加失败，请重新输入!', 'red');
				}
			});
	};
	$scope.updateInfo = function(id){
		$http({
			method:"post",
			url:"api/mfilesubcate/detail.show",
			params:{
				id:id
			}
		}).success(function(response){
			if(response.status == 1){
				$("#updatecoursesubclassname").val(response.data.data.name);
				$("#updatecoursesubclasssort").val(response.data.data.sort);
				$("#updateid").val(response.data.data.id);
				$scope.updateCateValue=""+response.data.data.fileCateId;
			}
		});
	};
	$scope.update = function(){
		var cate_id = $("#updateCateId").val();
		var name = $("#updatecoursesubclassname").val();
		var sort = $("#updatecoursesubclasssort").val();
		if(name == ""){
			showtip('请填写课件子分类名称!', 'red');
			return;
		} 
		if(sort == ""){
			showtip('请填写课件子分类排序!', 'red');
			return;
		}
		$http({
			method:"post",
			url:"api/mfilesubcate/update.do?",
			params:{
				id:$("#updateid").val(),
				fileCateId:cate_id,
				name:name,
				sort:sort
			}
		}).success(function(response){
			showtip('更新成功!', 'green');
			if(response.status == 1){
				$(".close").click();
				$http({
					method:"post",
					url:"api/mfilesubcate/list.show",
					params:{
						pageNum:"1",
						pageSize:"10",
						fileCateId : $scope.cateValue
					}
					}).success(function(response){
						if(response.status == 1){
							$scope.list = response.data.data.list;
							$scope.pageCount = response.data.data.pages;
							$scope.currentPage = response.data.data.pageNum;
							$scope.totalCount = response.data.data.total;
						}
					});
			} else {
				showtip('更新失败，请重新输入!', 'red');
			}
		});
	};
	$scope.del = function(id){
		$("#delid").val(id);
	};
	$scope.delcoursesubclassify = function(){
		var id = $("#delid").val();
		$http({
			method:"post",
			url:"api/mfilesubcate/delete.do",
			params:{
				id:id
			}
			}).success(function(response){
				if(response.status == 1){
					showtip('删除成功!', 'green');
					$(".close").click();
					$http({
						method:"post",
						url:"api/mfilesubcate/list.show",
						params:{
							pageNum:"1",
							pageSize:"10",
							fileCateId : $scope.cateValue
						}
						}).success(function(response){
							if(response.status == 1){
								$scope.list = response.data.data.list;
								$scope.pageCount = response.data.data.pages;
								$scope.currentPage = response.data.data.pageNum;
								$scope.totalCount = response.data.data.total;
							}
						});
				}
			});
	};
	$scope.onPageChange = function(){
		$('body,html').animate({scrollTop:0},50);
		$http({
			method:"post",
			url:URL,
			params:{
				pageNum:$scope.currentPage,
				pageSize:"10",
				fileCateId : $scope.cateValue
			}
		}).success(function(response){
			if(response.status == 1){
				$scope.list = response.data.data.list;
				$scope.pageCount = response.data.data.pages;
				$scope.currentPage = response.data.data.pageNum;
				$scope.totalCount = response.data.data.total;
			}
		}).error(function(){
		});
	};
	
});

function showLoading() {   
    // 显示遮罩层   
	$('#overlay').css({'height':$(document).height(),'width':$(document).width()});   
	$('#overlay').show();   
    // Loading提示窗口居中   
    $("#loadingTip").css('top',   
            (getWindowInnerHeight() - $("#loadingTip").height()) / 2 + 'px');   
    $("#loadingTip").css('left',   
            (getWindowInnerWidth() - $("#loadingTip").width()) / 2 + 'px');   
               
    $("#loadingTip").show();   
    $(document).scroll(function() {   
        return false;   
    });   
};
// 浏览器兼容 取得浏览器可视区高度   
function getWindowInnerHeight() {   
    var winHeight = window.innerHeight   
            || (document.documentElement && document.documentElement.clientHeight)   
            || (document.body && document.body.clientHeight);   
    return winHeight;   
       
} ; 
  
// 浏览器兼容 取得浏览器可视区宽度   
function getWindowInnerWidth() {   
    var winWidth = window.innerWidth   
            || (document.documentElement && document.documentElement.clientWidth)   
            || (document.body && document.body.clientWidth);   
    return winWidth;   
       
} ; 
//隐藏遮罩
function hideLoading() {   
    $('#overlay').hide();   
    $("#loadingTip").hide();   
    $(document).scroll(function() {   
        return true;   
    });   
}

