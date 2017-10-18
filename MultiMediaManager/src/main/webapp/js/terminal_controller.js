var TerminalManageModule = angular.module('TerminalManageModule',[]);
TerminalManageModule.filter('isOnLine',function(){
	return function(input){
		var timestamp = Date.parse(new Date());
		if((timestamp - input) > 1200000){
			return "离线";
		} else {
			return "在线";
		}
	};
});
TerminalManageModule.filter('fileType', function() {
	return function(input) {
		if (input == 0) {
			return "视频";
		} else if (input == 1) {
			return "音频";
		} else if (input == 2) {
			return "图书";
		} else {
			return "图片";
		}
	};
});
TerminalManageModule.controller('terminalCtrl',function($scope, $http){
	$scope.show = true;
	$scope.sizes = ["20","22","24","26","28","36","48","72"];
	$('body,html').animate({scrollTop:0},50);
	$http({
		method:"post",
		url:"api/machine/getMachineList.show",
		params:{
			pageNum:"1",
			pageSize:"10",
		}
		}).success(function(response){
			if(response.status == 1){
				$scope.terminals = response.data.data.list;
				$scope.pageCount = response.data.data.pages;
				$scope.currentPage = response.data.data.pageNum;
				$scope.totalCount = response.data.data.total;
			}
		});
	$scope.add = function() {
		$("#address").val("");
		$("#mac").val("")
	};
	$scope.addTerminal = function(){
		var address = $("#address").val();
		var mac = $("#mac").val();
		if(address == ""){
			showtip('请填写设备的地址!', 'red');
			return;
		} 
		if(mac == ""){
			showtip('请填写设备的MAC地址!', 'red');
			return;
		}
		$http({
			method:"post",
			url:"api/machine/addMachine.do",
			params:{
				mac:mac,
				address:address,
			}
			}).success(function(response){
				if(response.status == 1){
					showtip('添加成功!', 'green');
					$(".close").click();
					$http({
						method:"post",
						url:"api/machine/getMachineList.show",
						params:{
							pageNum:"1",
							pageSize:"10",
						}
						}).success(function(response){
							if(response.status == 1){
								$scope.terminals = response.data.data.list;
								$scope.pageCount = response.data.data.pages;
								$scope.currentPage = response.data.data.pageNum;
								$scope.totalCount = response.data.data.total;
							}
						});
				} else {
					showtip('当前MAC地址已被使用，请重新输入!', 'red');
				}
			});
	};
	
	$scope.findCode = function(){
		var mac = $scope.terminalmac;
		$http({
			method:"post",
			url:"api/machine/getMachineList.show",
			params:{
				pageNum:"1",
				pageSize:"10",
				mac:mac
			}
			}).success(function(response){
				if(response.status == 1){
					$scope.terminals = response.data.data.list;
					$scope.pageCount = response.data.data.pages;
					$scope.currentPage = response.data.data.pageNum;
					$scope.totalCount = response.data.data.total;
				}
			});
	};
	$scope.onPageChange = function(){
		$('body,html').animate({scrollTop:0},50);
		var mac = $scope.terminalmac;
		$http({
			method:"post",
			url:"api/machine/getMachineList.show",
			params:{
				pageNum:$scope.currentPage,
				pageSize:"10",
				mac:mac
			}
			}).success(function(response){
				if(response.status == 1){
					$scope.terminals = response.data.data.list;
					$scope.pageCount = response.data.data.pages;
					$scope.currentPage = response.data.data.pageNum;
					$scope.totalCount = response.data.data.total;
				}
			});
	};
	$scope.updateInfo = function(id){
		$http({
			method:"post",
			url:"api/machine/getMachineDetail.show",
			params:{
				id:id
			}
		}).success(function(response){
			if(response.status == 1){
				$("#updateaddress").val(response.data.data.address);
				$("#updatemac").val(response.data.data.mac);
				$("#updateid").val(response.data.data.id);
			}
		});
	};
	$scope.update = function(){
		var address = $("#updateaddress").val();
		var mac = $("#updatemac").val();
		if( mac == ""){
			showtip('请输入设备的MAC地址!', 'red');
			return;
		}
		if(address == ""){
			showtip('请输入设备的地址!', 'red');
			return;
		}
		$http({
			method:"post",
			url:"api/machine/updateMachine.do?",
			params:{
				mac:$("#updatemac").val(),
				versionId:$("#updateid").val(),
				address:$("#updateaddress").val()
			}
		}).success(function(response){
			showtip('更新成功!', 'green');
			if(response.status == 1){
				$(".close").click();
				$http({
					method:"post",
					url:"api/machine/getMachineList.show",
					params:{
						pageNum:"1",
						pageSize:"10",
					}
					}).success(function(response){
						if(response.status == 1){
							$scope.terminals = response.data.data.list;
							$scope.pageCount = response.data.data.pages;
							$scope.currentPage = response.data.data.pageNum;
							$scope.totalCount = response.data.data.total;
						}
					});
			} else {
				showtip('当前MAC地址已被使用，请重新输入!!', 'red');
			}
		});
	};
	$scope.del = function(id){
		$("#delid").val(id);
	};
	$scope.delterminal = function(){
		var id = $("#delid").val();
		$http({
			method:"post",
			url:"api/machine/deleteMachine.do",
			params:{
				id:id
			}
			}).success(function(response){
				if(response.status == 1){
					showtip('删除成功!', 'green');
					$(".close").click();
					$http({
						method:"post",
						url:"api/machine/getMachineList.show",
						params:{
							pageNum:"1",
							pageSize:"10",
						}
						}).success(function(response){
							if(response.status == 1){
								$scope.terminals = response.data.data.list;
								$scope.pageCount = response.data.data.pages;
								$scope.currentPage = response.data.data.pageNum;
								$scope.totalCount = response.data.data.total;
							}
						});
				}
			});
	};
	$scope.capture = function(id){
		$http({
			method:"post",
			url:"api/machine/machinescreen.do",
			params:{
				id:id
			}
		}).success(function(response){
			$("#loadImg").css("display","block");
			if(response.status == 1){
				$("#offsetImg").css("display","block");
				var url = "/upload/screen_shot/screen_shot_"+response.data.msg_id+".jpg";
				$("#img").attr("src",url);
			} else {
				$("#offsetImg").css("display","none");
				$('#myModal5').modal('hide');
				setTimeout("alertTitle()",600);
			}
			setTimeout("dis_load()",500);
		});
	};
	$scope.locked = function(id){
		$("#lockedId").val(id);
	};
	$scope.lockedSubmit = function(){
		var id = $("#lockedId").val();
		$http({
			method:"post",
			url:"api/machine/machinelock.do",
			params:{
				id:id
			}
		}).success(function(response){
			$(".close").click();
			if(response.status == 1){
				showtip('设备已锁定!', 'green');
			} else {
				showtip(response.msg, 'red');
			}
		});
	};
	$scope.checkRes = function(id){
		$http({
			method:"post",
			url:"api/machine/getmachineres.show",
			params:{
				pageNum:"1",
				pageSize:"10",
				id:id
			}
		}).success(function(response){
			if(response.status == 1){
				window.location.href = "#/sourceManage?id="+id;
			} else {
				showtip(response.msg, 'red');
			}
		});
	};
	$scope.type = 1;
	$scope.opt = function(){
		var type = $scope.type;
		var content = $("#content").val();
		var text_size = $("#text_size").val();
		var text_color = $("#text_color").val();
		var background_color = $("#background_color").val();
		var background_alpha = $("#backgroud_alpha").val();
		var content_location = $("#content_location").val();
		var time = $("#show_minute").val();
		$http({
			method:"post",
			url:"api/machine/optequipments.do",
			params:{
				type: type,
				content:content,
				time:time,
				size:text_size,
				color:text_color,
				back_color:background_color,
				alpha:background_alpha,
				location:content_location
			}
		}).success(function(response){
			$(".close").click();
			if(response.status == 1){
				showtip('管控成功!', 'green');
			} else {
				showtip(response.msg, 'red');
			}
		});
	};
	$scope.typeChange = function() {
		var type = $scope.type;
		if(type == 1){
			$scope.show = true;
		} else {
			$scope.show = false;
		}
	};
});	
TerminalManageModule.controller('sourceManageCtrl',function($scope, $http, $stateParams){
	$('body,html').animate({scrollTop:0},50);
	var id = $stateParams.id;
	$http({
		method:"post",
		url:"api/machine/getmachineres.show",
		params:{
			pageNum:"1",
			pageSize:"10",
			id:id
		}
	}).success(function(response){
		if(response.status == 1){
			$scope.res = response.data.reslist;
			$scope.pageCount = response.data.reslist[0].pages;
			$scope.currentPage = response.data.reslist[0].pageNum;
			$scope.totalCount = response.data.reslist[0].total;
		} else {
			alert(response.msg);
		}
	});
	$scope.onPageChange = function(){
		$('body,html').animate({scrollTop:0},50);
		$http({
			method:"post",
			url:"api/machine/getmachineres.show",
			params:{
				pageNum:$scope.currentPage,
				pageSize:"10",
				id:id
			}
		}).success(function(response){
			if(response.status == 1){
				$scope.res = response.data.reslist;
				$scope.pageCount = response.data.reslist[0].pages;
				$scope.currentPage = response.data.reslist[0].pageNum;
				$scope.totalCount = response.data.reslist[0].total;
			} else {
				alert(response.msg);
			}
		});
	}
	$scope.del = function(resId){
		$("#delResid").val(resId);
	};
	$scope.delSubmit = function(){
		var resId = $("#delResid").val();
		$http({
			method:"post",
			url:"api/machine/delmachineres.do",
			params:{
				id:id,
				resId:resId
			}
		}).success(function(response){
			$(".close").click();
			if(response.status == 1){
				$http({
					method:"post",
					url:"api/machine/getmachineres.show",
					params:{
						pageNum:$scope.currentPage,
						pageSize:"10",
						id:id
					}
				}).success(function(response){
					if(response.status == 1){
						$scope.res = response.data.reslist;
						$scope.pageCount = response.data.reslist[0].pages;
						$scope.currentPage = response.data.reslist[0].pageNum;
						$scope.totalCount = response.data.reslist[0].total;
					} else {
						alert(response.msg);
					}
				});
				showtip('删除成功!', 'green');
			} else {
				showtip(response.msg,"red");
			}
		});
	};
});	
function dis_load(){
	$("#loadImg").css("display","none");
}
function alertTitle(){
	showtip('终端不在线!', 'red');
}