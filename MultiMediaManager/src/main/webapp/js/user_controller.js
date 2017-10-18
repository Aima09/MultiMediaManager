var UserManageModule = angular.module('UserManageModule',[]);
UserManageModule.controller('userManageCtrl',function($scope,$http){
	$('body,html').animate({scrollTop:0},50);
	$http.get("api/muser/getuserlist.show?pageSize=10&pageNum=1")
	.success(function(data){
		if(data.status == 1){
			$scope.pageCount = data["data"]["userlist"]["pages"]; //总页数
			$scope.currentPage = data["data"]["userlist"]["pageNum"];  //第几页
			$scope.totalCount = data["data"]["userlist"]["total"];  //总条数
			$scope.lists = data["data"]["userlist"]["list"];
		}
	});
	
	$scope.deleteUser1 = function(deleteId){
		$scope.deleteId = deleteId;
	};
	
	$scope.deleteUser2 = function(deleteId){
		$http.get("api/muser/deluser.do?id="+deleteId)
		.success(function(data){
			if(data.status == 1){
				showtip('删除成功！', 'green');
				$(".close").click();
				$http.get("api/muser/getuserlist.show?pageSize=10&pageNum=1")
				.success(function(data){
					if(data.status == 1){
						$scope.pageCount = data["data"]["userlist"]["pages"]; //总页数
						$scope.currentPage = data["data"]["userlist"]["pageNum"];  //第几页
						$scope.totalCount = data["data"]["userlist"]["total"];  //总条数
						$scope.lists = data["data"]["userlist"]["list"];
					}
				});
			}
		}).error(function(){
			showtip('删除失败！', 'red');
		});
	};
	
	$scope.onPageChange = function(){
		$('body,html').animate({scrollTop:0},50);
		var account = $scope.search;
		$http({
			method:"post",
			url:"api/muser/getuserlist.show",
			params:{
				pageNum:$scope.currentPage,
				pageSize:"10",
				account:account
			}
			}).success(function(data){
				if(data.status == 1){
					$scope.pageCount = data["data"]["userlist"]["pages"]; //总页数
					$scope.currentPage = data["data"]["userlist"]["pageNum"];  //第几页
					$scope.totalCount = data["data"]["userlist"]["total"];  //总条数
					$scope.lists = data["data"]["userlist"]["list"];
				}
			}).error(function(){
			});
	};
	
	$scope.keywordchange = function(){
		var account = $scope.search;
		$http({
			method:"post",
			url:"api/muser/getuserlist.show",
			params:{
				pageNum:"1",
				pageSize:"10",
				account:account
			}
		}).success(function(data){
			if(data.status == 1){
				$scope.lists = data["data"]["userlist"]["list"];
				$scope.pageCount = data["data"]["userlist"]["pages"]; //总页数
				$scope.currentPage = data["data"]["userlist"]["pageNum"];  //第几页
				var count = data.data.userlist.total;
				$scope.totalCount = count;
			}
		}).error(function(){
		});
		
	}
	
});

UserManageModule.filter("roleId",function(){
	return function(roleid){
		if(roleid==1){
			return "管理员";
		}
		if(roleid==2){
			return "教师";
		}
	}
	
});

UserManageModule.filter("onlineStatus",function(){
	return function(status){
		if(status==1){
			return "可用";
		}
		if(status==0){
			return "不可用";
		}
	}
	
});

UserManageModule.controller('addUserCtrl',function($scope,$http){
	$('body,html').animate({scrollTop:0},50);
	$scope.role = 1;
	$scope.status = 1;
	$scope.addUser = function(){
		if($scope.account == null || $scope.account == ""){
			showtip('账号不能为空！', 'red');
			return;
		}
		if($scope.pwd == null || $scope.pwd == ""){
			showtip('密码不能为空！', 'red');
			return;
		}
		if($scope.name == null || $scope.name == ""){
			showtip('姓名不能为空！', 'red');
			return;
		}
		if($scope.phone == null || $scope.phone == "" ){
			showtip('手机号不能为空！', 'red');
			return;
		}
			
		$.post(
				'api/muser/adduser.do', 
				{
					account: $scope.account,
					pwd: $scope.pwd,
					name: $scope.name,
					phone: $scope.phone,
					role: $scope.role,
					status: $scope.status
				},
				function(data){
					if(data.status == 1){
						showtip('新增成功！', 'green');
						window.location.href="#/userManage";
					}
					if(data.status == 0){
						showtip('该用户名已被注册，请重新输入！', 'red');
					}
				},
				'json'
			 );
	}

});

UserManageModule.controller('renewUserCtrl',function($scope,$http){
	$('body,html').animate({scrollTop:0},50);
	var updateId = getUrlPara("updateId");
	$http.get("api/muser/getuser.show?id="+updateId)
	.success(function(data){
		if(data.status == 1){
			$scope.account = data["data"]["user"]["account"];
			$scope.name = data["data"]["user"]["name"];
			$scope.phone = data["data"]["user"]["phone"];
			$scope.role = data["data"]["user"]["role"];
			$scope.status = data["data"]["user"]["status"];
		}
	}).error(function(){
	});
	
	$scope.updateDetails = function(){
		var pwd = $scope.pwd;
		var name = $scope.name;
		var phone = $scope.phone;
		if(name == null || name == ""){
			showtip('姓名不能为空！', 'red');
			return;
		}
		if(phone == null || phone == "" ){
			showtip('手机号不能为空！', 'red');
			return;
		}
			
		$.post(
				'api/muser/updateuser.do', 
				{
					id: updateId,
					pwd: pwd,
					name: name,
					phone: phone,
					role: $scope.role,
					status: $scope.status
				},
				function(data){
					if(data.status == 1){
						showtip('更新成功！', 'green');
						window.location.href="#/userManage";
					}
				},
				'json'
			 );

	};
	
	
});





