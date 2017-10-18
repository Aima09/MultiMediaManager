var app = angular.module('MultiMediaManager', [ 'ui.router','AppManageModule','TerminalManageModule','UserManageModule','VersionManageModule','CourseclassifyManageModule','CoursesubclassifyManageModule','LessonManageModule','CommentManageModule', 'ngFileUpload', 'myModule']);
app.run(function($rootScope, $state, $stateParams) {
});
/*
配置路由
*/
app.config(function($stateProvider, $urlRouterProvider){
	$urlRouterProvider.otherwise('/appManage');
	$stateProvider.state('appManage',{
		url:"/appManage",
		templateUrl:"appManage.html",
		controller:"appManageCtrl"
	})
	.state('addApp',{
		url:"/addApp",
		templateUrl:"addApp.html",
		controller:"addAppCtrl"
	})
	.state('appDetail',{
		url:"/appDetail?id",
		templateUrl:"Appdetail.html",
		controller:"appDetailCtrl"
	})
	.state('renewApp',{
		url:"/renewApp?id",
		templateUrl:"renewApp.html",
		controller:"renewAppCtrl"
	})
	.state('terminalManage',{
		url:"/terminalManage",
		templateUrl:"terminalManage.html",
		controller:"terminalCtrl"
	})
	.state('sourceManage',{
		url:"/sourceManage?id",
		templateUrl:"sourceManage.html",
		controller:"sourceManageCtrl"
	})
	.state('userManage',{
		url:"/userManage",
		templateUrl:"userManage.html",
		controller:"userManageCtrl"
	})
	.state('addUser',{
		url:"/addUser",
		templateUrl:"addUser.html",
		controller:"addUserCtrl"
	})
	.state('renewUser',{
		url:"/renewUser",
		templateUrl:"renewUser.html",
		controller:"renewUserCtrl"
	})
	.state('versionManage',{
		url:"/versionManage",
		templateUrl:"versionManage.html",
		controller:"versionManageCtrl"
	})
	.state('addVersion',{
		url:"/addVersion",
		templateUrl:"addVersion.html",
		controller:"addVersionCtrl"
	})
	.state('versionHistory',{
		url:"/versionHistory",
		templateUrl:"versionHistory.html",
		controller:"versionHistoryCtrl"
	})
	.state('lessonManage',{
		url:"/lessonManage",
		templateUrl:"lessonManage.html",
		controller:"lessonManageCtrl"
	})
	.state('courseclassifyManage',{
		url:"/courseclassifyManage",
		templateUrl:"courseclassifyManage.html",
		controller:"courseclassifyManageCtrl"
	})
	.state('coursesubclassifyManage',{
		url:"/coursesubclassifyManage",
		templateUrl:"coursesubclassifyManage.html",
		controller:"coursesubclassifyManageCtrl"
	})
	.state('commentManage',{
		url:"/commentManage",
		templateUrl:"commentManage.html",
		controller:"commentManageCtrl"
	})
});

function getUrlPara(strName) {
	var strHref = location.href;
	var intPos = strHref.indexOf("?");
	var strRight = strHref.substr(intPos + 1);
	var arrTmp = strRight.split("&");
	for (var i = 0; i < arrTmp.length; i++) {
		var arrTemp = arrTmp[i].split("=");
		if (arrTemp[0].toUpperCase() == strName.toUpperCase())
			return arrTemp[1];
	}
	return "";
} 

function showtip(str, color){
	dm_notification(str, color, 1500);
}
