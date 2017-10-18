var CommentManageModule = angular.module('CommentManageModule',[]);
CommentManageModule.controller('commentManageCtrl',function($scope,$http){
	$('body,html').animate({scrollTop:0},50);
	var URL = "api/mfilecomment/list.show";
	$http({
		method : "post",
		url : URL,
		params : {
			pageNum : "1",
			pageSize : "10"
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
	
	$scope.findCode = function(){
		$http({
			method:"post",
			url:URL,
			params:{
				pageNum:"1",
				pageSize:"10",
				comment:$scope.commentMsg
			}
			}).success(function(response){
				if(response.status == 1){
					$scope.list = response.data.data.list;
					$scope.pageCount = response.data.data.pages;
					$scope.currentPage = response.data.data.pageNum;
					$scope.totalCount = response.data.data.total;
				}
			}).error(function(response){
			});;
	};
	
	$scope.del = function(id){
		$("#delid").val(id);
	};
	$scope.delcomment = function(){
		var id = $("#delid").val();
		$http({
			method:"post",
			url:"api/mfilecomment/delete.do",
			params:{
				id:id
			}
			}).success(function(response){
				if(response.status == 1){
					showtip('删除成功!', 'green');
					$(".close").click();
					$http({
						method:"post",
						url:URL,
						params:{
							pageNum:"1",
							pageSize:"10",
							comment:$scope.commentMsg
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
				comment:$scope.commentMsg
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

