var LessonManageModule = angular.module('LessonManageModule',[]);
LessonManageModule.controller('lessonManageCtrl',function($scope,$http){
	$('body,html').animate({scrollTop:0},50);
	var URL = "api/mfile/list.show";
	$scope.cateValue = '0';
	$scope.subcateValue = '0';
	$scope.typeValue = '0';
	$scope.statusValue = '-1';
	$scope.openFlgValue = '-1';
	$scope.searchName = '';
	$http({
		method : "post",
		url : URL,
		params : {
			pageNum : "1",
			pageSize : "10",
			fileCateId : $scope.cateValue,
			fileSubCateId : $scope.subcateValue,
			type : $scope.typeValue,
			status : $scope.statusValue,
			openFlg : $scope.openFlgValue,
			name : $scope.searchName
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
				fileCateId : $scope.cateValue,
				type : $scope.typeValue,
				status : $scope.statusValue,
				openFlg : $scope.openFlgValue,
				name : $scope.searchName
			}
		}).success(function(response) {
			if (response.status == 1) {
				$scope.list = response.data.data.list;
				$scope.pageCount = response.data.data.pages;
				$scope.currentPage = response.data.data.pageNum;
				$scope.totalCount = response.data.data.total;
				if ($scope.cateValue == 0) {
					$scope.subcatelist=null;
					$scope.subcateValue = '0';
				} else {
					$scope.subcateValue = '0';
					$http({
						method : "post",
						url : "api/mfilesubcate/list.show",
						params : {
							pageNum : "1",
							pageSize : "10",
							fileCateId : $scope.cateValue
						}
					}).success(function(response) {
						if (response.status == 1) {
							$scope.subcatelist = response.data.data.list;
						}
					}).error(function(response){
					});
				}
			}
		}).error(function(response){
		});
	}
	
	$scope.filt = function(){
		$http({
			method : "post",
			url : URL,
			params : {
				pageNum : "1",
				pageSize : "10",
				fileCateId : $scope.cateValue,
				fileSubCateId : $scope.subcateValue,
				type : $scope.typeValue,
				status : $scope.statusValue,
				openFlg : $scope.openFlgValue,
				name : $scope.searchName
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

	$scope.passInfo = function(id){
		$("#passid").val(id);
	};
	$scope.pass = function(){
		$http({
			method:"post",
			url:"api/mfile/pass.do",
			params:{
				id:$("#passid").val()
			}
			}).success(function(response){
				if(response.status == 1){
					showtip('通过成功!', 'green');
					$(".close").click();
					$http({
						method : "post",
						url : URL,
						params : {
							pageNum : "1",
							pageSize : "10",
							fileCateId : $scope.cateValue,
							fileSubCateId : $scope.subcateValue,
							type : $scope.typeValue,
							status : $scope.statusValue,
							openFlg : $scope.openFlgValue,
							name : $scope.searchName
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
				} else {
					showtip('通过失败，请重新操作!', 'red');
				}
			});
	};
	
	$scope.refuseInfo = function(id){
		$("#refuseid").val(id);
	};
	$scope.refuse = function(){
		$http({
			method:"post",
			url:"api/mfile/refuse.do",
			params:{
				id:$("#refuseid").val()
			}
		}).success(function(response){
			if(response.status == 1){
				showtip('拒绝成功!', 'green');
				$(".close").click();
				$http({
					method : "post",
					url : URL,
					params : {
						pageNum : "1",
						pageSize : "10",
						fileCateId : $scope.cateValue,
						fileSubCateId : $scope.subcateValue,
						type : $scope.typeValue,
						status : $scope.statusValue,
						openFlg : $scope.openFlgValue,
						name : $scope.searchName
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
			} else {
				showtip('拒绝失败，请重新操作!', 'red');
			}
		});
	};
	
	$scope.deleteInfo = function(id){
		$("#deleteid").val(id);
	};
	$scope.deleteFile = function(){
		$http({
			method:"post",
			url:"api/mfile/delete.do",
			params:{
				id:$("#deleteid").val()
			}
		}).success(function(response){
			if(response.status == 1){
				showtip('删除成功!', 'green');
				$(".close").click();
				$http({
					method : "post",
					url : URL,
					params : {
						pageNum : "1",
						pageSize : "10",
						fileCateId : $scope.cateValue,
						fileSubCateId : $scope.subcateValue,
						type : $scope.typeValue,
						status : $scope.statusValue,
						openFlg : $scope.openFlgValue,
						name : $scope.searchName
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
			} else {
				showtip('删除失败，请重新操作!', 'red');
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
				fileCateId : $scope.cateValue,
				fileSubCateId : $scope.subcateValue,
				type : $scope.typeValue,
				status : $scope.statusValue,
				openFlg : $scope.openFlgValue,
				name : $scope.searchName
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
LessonManageModule.filter('isOpen',function(){
	return function(input){
		if(input == 0){
			return "私有";
		} else {
			return "公有";
		}
	};
});

LessonManageModule.filter('status',function(){
	return function(input){
		if(input == 0){
			return "待审核";
		} else {
			if(input == 1){
				return "已通过";
			} else {
				return "已拒绝";
			}
		}
	};
});

LessonManageModule.filter('type',function(){
	return function(input){
		if(input == 1){
			return "视频";
		} else {
			if(input == 2){
				return "音频";
			} else {
				if(input == 3){
					return "图书";
				} else {
					return "图片";
				}
			}
		}
	};
});