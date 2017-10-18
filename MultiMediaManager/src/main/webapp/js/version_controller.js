var VersionManageModule = angular.module('VersionManageModule',[]);
VersionManageModule.controller('versionManageCtrl',function($scope,$http){
	$('body,html').animate({scrollTop:0},50);
	var URL = "api/mversion/getversion.show";
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
	var curid = null;
	var curdes = null;
	$scope.push = function(id, des) {
		curid = id;
		curdes = des;
	}
	
	$scope.surePush = function() {
		$('#myModal1').modal('hide');
		var URL2 = "api/history/addHistory.do";
		$http({
			method : "post",
			url : URL2,
			params : {
				versionId : curid,
				des : curdes
			}
		}).success(function(response) {
			if (response.status == 1) {
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
						showtip('推送成功', 'green');
					}
				}).error(function(response){
				});
			}
		}).error(function(response){
		});
	}
	
	var versionid = "";
	
	$scope.setVersionId = function(id){
		versionid = id;
	}
	
	$scope.deleteVersion = function() {
		var dURL = "api/mversion/deleteversion.do";
		$http({
			method : "post",
			url : dURL,
			params : {
				id : versionid
			}
		}).success(function(response) {
			if (response.status == 1) {
				$('#myModal').modal('hide');
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
				showtip('删除成功', 'green');
			}
		}).error(function(response){
		});
	}
	
	$scope.onPageChange = function(){
		$('body,html').animate({scrollTop:0},50);
		$http({
			method:"post",
			url:URL,
			params:{
				pageNum:$scope.currentPage,
				pageSize:"10"
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
VersionManageModule.controller('addVersionCtrl',function($scope, $http, Upload){
	$('body,html').animate({scrollTop:0},50);
	$scope.addVersion = function() {
		if ($scope.versionname == null || $scope.versionname == "") {
			showtip('名称不能为空！', 'red');
			return;
		}
		if ($scope.apkFile == null || $scope.apkFile == "") {
			showtip('应用不能为空！', 'red');
			return;
		}
		if ($scope.versiondes == null || $scope.versiondes == "") {
			showtip('描述不能为空！', 'red');
			return;
		}
		if ($scope.ispush) {
			$scope.ispush = 1;
		} else {
			$scope.ispush = 0;
		}
		showLoading();
		$scope.upload($scope.apkFile);
	}
	

	$scope.upload = function(apkfile) {
		Upload.upload({
			// 服务端接收
			url : 'api/mversion/addversion.do',
			// 上传的同时带的参数
			data : {
				name : $scope.versionname,
				des : $scope.versiondes,
				is_push : $scope.ispush,
				file : apkfile
			}
		}).success(function(data) {
			hideLoading();
			showtip('新增成功', 'green');
			window.location.href = "#/versionManage";
			// 上传成功
			$scope.returnvalue = JSON.stringify(data);
			console.log(data);
		}).error(function(data, status, headers, config) {
			hideLoading()
			showtip('新增失败', 'red');
			// 上传失败
			console.log('error status: ' + status);
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

VersionManageModule.controller('versionHistoryCtrl',function($scope, $http){
	$('body,html').animate({scrollTop:0},50);
	var URL = "api/history/getHistoryList.show";
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
	
	$scope.onPageChange = function(){
		$('body,html').animate({scrollTop:0},50);
		$http({
			method:"post",
			url:URL,
			params:{
				pageNum:$scope.currentPage,
				pageSize:"10"
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

function formatDate(now) {
	var year = now.getFullYear();
	var month = now.getMonth() + 1;
	if (month < 10) {
		month = "0" + month;
	}
	var date = now.getDate();
	if (date < 10) {
		date = "0" + date;
	}
	return year + "-" + month + "-" + date;
} 

VersionManageModule.filter("datetime",function(){
	return function(str){
		if(str == null){
			return "-";
		} else {
			var d = new Date(str);
			return formatDate(d);
		}
	};
});
VersionManageModule.filter("is_push",function(){
	return function(str){
		if(str == null){
			return "未推送";
		} else {
			return "已推送";
		}
	};
});