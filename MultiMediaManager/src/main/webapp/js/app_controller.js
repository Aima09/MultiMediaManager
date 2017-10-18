var AppManageModule = angular.module('AppManageModule',[]);
AppManageModule.controller('appManageCtrl',function($scope,$http){
	$('body,html').animate({scrollTop:0},50);
	var URL = "api/mapk/getapk.show";
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
//		s_show_loading(false);
	}).error(function(response){
//		s_show_loading(false);
	});
	var appid = "";
	$scope.setAppId = function(id) {
		appid = id;
	}
	$scope.deleteApp = function() {
		var dURL = "api/mapk/deleteapk.do";
		$http({
			method : "post",
			url : dURL,
			params : {
				id : appid
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
//					s_show_loading(false);
				}).error(function(response){
//					s_show_loading(false);
				});
				showtip('删除成功', 'green');
			}
		}).error(function(response){
		});
	}
	
	$scope.searchAppName = function(){
		$http({
			method : "post",
			url : URL,
			params : {
				name : $scope.appSearch,
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
	};
	
	$scope.onPageChange = function(){
		$('body,html').animate({scrollTop:0},50);
		$http({
			method:"post",
			url:URL,
			params:{
				pageNum:$scope.currentPage,
				pageSize:"10",
				name : $scope.appSearch
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

AppManageModule.controller('addAppCtrl',function($scope,$http, Upload){
	$('body,html').animate({scrollTop:0},50);
	$scope.addApp = function() {
		if ($scope.appname == null || $scope.appname == "") {
			showtip('名称不能为空！', 'red');
			return;
		}
		if ($scope.picFile == null || $scope.picFile == "") {
			showtip('封面不能为空！', 'red');
			return;
		}
		if ($scope.apkFile == null || $scope.apkFile == "") {
			showtip('应用不能为空！', 'red');
			return;
		}
		if ($scope.appsize == null || $scope.appsize == "") {
			showtip('大小不能为空！', 'red');
			return;
		}
		if ($scope.appdes == null || $scope.appdes == "") {
			showtip('描述不能为空！', 'red');
			return;
		}
		
		showLoading();
		$scope.upload($scope.picFile, $scope.apkFile);
	}
	
	$scope.upload = function(picfile, apkfile) {
		Upload.upload({
			// 服务端接收
			url : 'api/mapk/addapk.do',
			// 上传的同时带的参数
			data : {
				name : $scope.appname,
				size : $scope.appsize,
				des : $scope.appdes,
				coverfile : picfile,
				apkfile : apkfile
			}
		}).success(function(data) {
			hideLoading();
			showtip('新增成功', 'green');
			window.location.href = "#/appManage";						
			// 上传成功
			$scope.returnvalue = JSON.stringify(data);
			console.log(data);
		}).error(function(data, status, headers, config) {
			hideLoading();
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

AppManageModule.controller('appDetailCtrl',function($scope,$http,$stateParams){
	$('body,html').animate({scrollTop:0},50);
	var id = $stateParams.id;
	var URL = "api/mapk/getapkdetail.show";
	$http({
		method : "post",
		url : URL,
		params : {
			id : id
		}
	}).success(function(response) {
		if (response.status == 1) {
			$scope.app = response.data.data;
			$scope.name = $scope.app.name;
			$scope.cover = "upload/apk_cover/apk_cover_"+$scope.app.id+".png";
			$scope.apk = "apk_"+$scope.app.id+".apk";
			$scope.size = $scope.app.size;
			$scope.version = $scope.app.version;
			$scope.des = $scope.app.des;
		}
	}).error(function(response){
	});
	$scope.close = function() {
		window.location.href = "#/appManage";
	}
});
AppManageModule.controller('renewAppCtrl',function($scope,$http,$stateParams, Upload){
	$('body,html').animate({scrollTop:0},50);
	var id = $stateParams.id;
	var URL = "api/mapk/getapkdetail.show";
	$http({
		method : "post",
		url : URL,
		params : {
			id : id
		}
	}).success(function(response) {
		if (response.status == 1) {
			$scope.app = response.data.data;
			$scope.name = $scope.app.name;
			$scope.cover = "upload/apk_cover/apk_cover_"+$scope.app.id+".png";
			$scope.apk = "apk_"+$scope.app.id+".apk";
			$scope.size = $scope.app.size;
			$scope.version = $scope.app.version;
			$scope.des = $scope.app.des;
		}
	}).error(function(response){
	});
	
	$scope.updateSave = function() {
		if ($scope.name == null || $scope.name == "") {
			showtip('名称不能为空！', 'red');
			return;
		}
		if ($scope.size == null || $scope.size == "") {
			showtip('大小不能为空！', 'red');
			return;
		}
		if ($scope.des == null || $scope.des == "") {
			showtip('描述不能为空！', 'red');
			return;
		}
		showLoading();
		$scope.upload($scope.picFile, $scope.apkFile);
	}
	

	$scope.upload = function(picfile, apkfile) {
		Upload.upload({
			// 服务端接收
			url : 'api/mapk/updateapk.do',
			// 上传的同时带的参数
			data : {
				id : id,
				name : $scope.name,
				size : $scope.size,
				des : $scope.des,
				coverfile : picfile,
				apkfile : apkfile
			}
		}).success(function(data) {
			hideLoading();
			showtip('更新成功', 'green');
			window.location.href = "#/appManage";
			// 上传成功
			$scope.returnvalue = JSON.stringify(data);
			console.log(data);
		}).error(function(data, status, headers, config) {
			hideLoading();
			showtip('更新失败', 'red');
			// 上传失败
			console.log('error status: ' + status);
		});
	};
});

function Numbers(event)
{
	if(!(event.keyCode==46)&&!(event.keyCode==8)&&!(event.keyCode==37)&&!(event.keyCode==39))
	    if(!((event.keyCode>=48&&event.keyCode<=57)||(event.keyCode>=96&&event.keyCode<=105)))
	    	event.returnValue=false;
}
