app.controller('indexCtrl',function($scope, $location, $http){
	var url = $location.url();
	if (url.indexOf("/appManage") >= 0 || url.indexOf("/renewApp") >= 0 || url.indexOf("/appDetail") >= 0 || url.indexOf("/addApp") >= 0) {
		$(".menu-list li").removeClass("nav-active");
		$(".banner li").removeClass("active");
		$("#app_p").addClass("nav-active");
		$("#app").addClass("active");
	} else if(url.indexOf("/terminalManage") >= 0 || url.indexOf("/sourceManage") >= 0){
		$(".menu-list li").removeClass("nav-active");
		$(".banner li").removeClass("active");
		$("#terminal_p").addClass("nav-active");
		$("#terminal").addClass("active");
	} else if(url.indexOf("/userManage") >= 0 || url.indexOf("/renewUser") >= 0 || url.indexOf("/addUser") >= 0){
		$(".menu-list li").removeClass("nav-active");
		$(".banner li").removeClass("active");
		$("#user_p").addClass("nav-active");
		$("#user").addClass("active");
	} else if(url.indexOf("/versionManage") >= 0 || url.indexOf("/addVersion") >= 0){
		$(".menu-list li").removeClass("nav-active");
		$(".banner li").removeClass("active");
		$("#version_p").addClass("nav-active");
		$("#version").addClass("active");
	} else if(url.indexOf("/versionHistory") >= 0){
		$(".menu-list li").removeClass("nav-active");
		$(".banner li").removeClass("active");
		$("#version_p").addClass("nav-active");
		$("#history").addClass("active");
	} else if(url.indexOf("/courseclassifyManage") >= 0){
		$(".menu-list li").removeClass("nav-active");
		$(".banner li").removeClass("active");
		$("#lesson_p").addClass("nav-active");
		$("#courseclassify").addClass("active");
	} else if(url.indexOf("/coursesubclassifyManage") >= 0){
		$(".menu-list li").removeClass("nav-active");
		$(".banner li").removeClass("active");
		$("#lesson_p").addClass("nav-active");
		$("#coursesubclassify").addClass("active");
	} else if(url.indexOf("/lessonManage") >= 0){
		$(".menu-list li").removeClass("nav-active");
		$(".banner li").removeClass("active");
		$("#lesson_p").addClass("nav-active");
		$("#lesson").addClass("active");
	} else if(url.indexOf("/commentManage") >= 0){
		$(".menu-list li").removeClass("nav-active");
		$(".banner li").removeClass("active");
		$("#lesson_p").addClass("nav-active");
		$("#comment").addClass("active");
	} else {
		$(".menu-list li").removeClass("nav-active");
		$(".banner li").removeClass("active");
	}
	
	var URL = "api/muser/isLogin.do";
	$http({
		method : "post",
		url : URL,
		params : {
			pageNum : "1",
			pageSize : "10"
		}
	}).success(function(response) {
		if (response.status != 1) {
			window.location.href = "login.html";
		} else {
			$scope.username = response.data.data.account;
		}
	}).error(function(response){
	});
	
	$scope.logOut = function(){
		var URL3 = "api/muser/logOut.do";
		$http({
			method : "post",
			url : URL3,
			params : {
			}
		}).success(function(response) {
			if (response.status == 1) {
				window.location.href = "login.html";
			}
		}).error(function(response){
		});
	}
});