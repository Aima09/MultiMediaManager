var app = angular.module('detail', []);
app.controller('detailCtrl',function($scope, $location, $http){
    var curUrl = $location.$$absUrl;
    var isLogin = 0;
	var id = curUrl.substring(curUrl.lastIndexOf("=")+1,curUrl.length);
	$scope.id = id;
	var URL = "api/mfile/detail.show";
	var curCommentNum = 0;
	var curCommentPageSize = 10;
	var isLove = 0;
	$("#cover").attr("src", "../upload/file_cover/file_cover_" + id + ".png");
	$http({
		method : "post",
		url : "api/mfile/browse.do",
		params : {
			id : id
		}
	}).success(function(response) {
		if (response.status == 1) {
			$scope.cnt += 1;
		}
	}).error(function(response){
	});
	$http({
		method : "post",
		url : "api/muser/isTeacherLogin.do"
	}).success(function(response) {
		if (response.status != 1) {
//			window.location.href = "login.html";
			$("#exit").hide();
			$("#login").attr("href", "login.html");
			$scope.username = "登录";
		} else {
			$scope.username = response.data.data.account;
			isLogin = 1;
		}
	}).error(function(response){
	});
	$http({
		method : "post",
		url : URL,
		params : {
			id : id
		}
	}).success(function(response) {
		if (response.status == 1) {
			$scope.name = response.data.data.name;
			$scope.type = response.data.data.type;
			$scope.actor = response.data.data.actor;
			$scope.time = response.data.data.createDatetime;
			$scope.downloadCnt = response.data.data.downloadCnt;
			$scope.loveCnt = response.data.data.loveCnt;
			$scope.cnt = response.data.data.cnt;
			$scope.des = response.data.data.des;
			$scope.openFlg = response.data.data.openFlg;
			$scope.status = response.data.data.status;
		}
	}).error(function(response){
	});
	$http({
		method : "post",
		url : "api/mfilecomment/list.show",
		params : {
			pageNum : "1",
			pageSize : "10",
			file_id : id
		}
	}).success(function(response) {
		if (response.status == 1) {
			$scope.commentlist = response.data.data.list;
			curCommentNum = response.data.data.list.length;
		}
	}).error(function(response){
	});
	$scope.loginOut = function(){
		$http({
			method : "post",
			url : "api/muser/teacherLogOut.do"
		}).success(function(response) {
			if (response.status == 1) {
				window.location.href = "login.html";
			}
		}).error(function(response){
		});
	};
	$scope.publishComment = function(){
		if (isLogin != 1) {
			$('.login-pop').show();
		} else {
			if ($("#commentMsg").val().length < 1) {
				showtip('评论内容不能为空！', 'red');
			} else {
				$http({
					method : "post",
					url : "api/mfilecomment/add.do",
					params : {
						file_id : id,
						comment : $("#commentMsg").val()
					}
				}).success(function(response) {
					if (response.status == 1) {
						showtip('评论发表成功！', 'green');
						$("#commentMsg").val("");
						$http({
							method : "post",
							url : "api/mfilecomment/list.show",
							params : {
								pageNum : "1",
								pageSize : "10",
								file_id : id
							}
						}).success(function(response) {
							if (response.status == 1) {
								curCommentPageSize = 10;
								curCommentNum = response.data.data.list.length;
								$scope.commentlist = response.data.data.list;
							}
						}).error(function(response){
						});
					}
				}).error(function(response){
				});
			}
		}
	};
	$scope.loadMore = function(){
		$http({
			method : "post",
			url : "api/mfilecomment/list.show",
			params : {
				pageNum : "1",
				pageSize : curCommentPageSize + 10,
				file_id : id
			}
		}).success(function(response) {
			if (response.status == 1) {
				if (response.data.data.list.length <= curCommentNum) {
					showtip('没有更多了！', 'red');
				} else {
					curCommentNum = response.data.data.list.length;
					$scope.commentlist = response.data.data.list;
					if (curCommentPageSize < curCommentNum) {
						curCommentPageSize += 10;
					}
				}
			}
		}).error(function(response){
		});
		
	}
	$scope.love = function(){
		if (isLove == 0) {
			isLove = 1;
			$http({
				method : "post",
				url : "api/mfile/love.do",
				params : {
					id : id
				}
			}).success(function(response) {
				if (response.status == 1) {
					$scope.loveCnt += 1;
				}
			}).error(function(response){
			});
		}
	}
});


app.filter('openFlg',function(){
	return function(input){
		if(input == 0){
			return "私有";
		} else {
			if(input == 1){
				return "公开";
			}
		}
	};
});
app.filter('status',function(){
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
app.filter('type',function(){
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
app.filter("time",function(){
	return function(timeStamp){
		var date = new Date(timeStamp);
		Y = date.getFullYear() + '-';
		M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
		D = (date.getDate() < 10 ? '0'+ date.getDate() : date.getDate()) + '';
		return Y+M+D;  
	}
});
function showtip(str, color){
	dm_notification(str, color, 1500);
}