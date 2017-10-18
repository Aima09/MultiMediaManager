var app = angular.module('login', []);
app.controller('loginCtrl',function($scope, $location, $http){
	var dologin = function(){
		var username = "";
		var pwd = "";
		var href = "";
		var url = "";
		username = $("#username").val();
		pwd = $("#userpwd").val();
		url = "api/muser/teacherLogin.do?userName="+username+"&userPwd="+pwd;
		if(username == null || username == ""){
			$("#divMsg").html("用户名不能为空！");
			return;
		}
		if(pwd == null || pwd == ""){
			$("#divMsg").html("密码不能为空！");
			return;
		}
		href = "list.html";
		
		$.post(url, function(data){
			if (data["msg"] == "登录成功!") {
				window.location.href = href;
			} else {
				$("#divMsg").html("用户名或密码错误！");
			}
		}, "json");
	};
	
	$scope.login = function(){
		dologin();
	};
	$scope.mykeyup = function(e){
		var keycode = window.event?e.keyCode:e.which;
		if(keycode == 13){
			dologin();
		}
	};
});