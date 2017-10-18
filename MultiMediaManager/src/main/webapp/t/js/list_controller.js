var app = angular.module('list', ['ngFileUpload']);
app.controller('listCtrl',function($scope, $location, $http, Upload, fileReader){
	var URL = "api/mfile/list.show";
	var userId = 0;
	var cateValue = '0';
	var subcateValue = '0';
	var typeValue = '0';
	var statusValue = '-1';
	var openFlgValue = '-1';
	var searchName = '';
	$scope.uploadCateValue = '0';
	$scope.uploadSubCateValue = '0';
	$scope.uploadOpenFlgValue = '0';
	$scope.uploadNameValue = "";
	$scope.uploadActorValue = "";
	$scope.uploadCompanyValue = "";
	$scope.uploadDesValue = "";
	$http({
		method : "post",
		url : "api/muser/isTeacherLogin.do"
	}).success(function(response) {
		if (response.status != 1) {
			window.location.href = "login.html";
		} else {
			$scope.username = response.data.data.account;
			userId = response.data.data.id;

			$http({
				method : "post",
				url : URL,
				params : {
					pageNum : "1",
					pageSize : "10",
					userId : userId
				}
			}).success(function(response) {
				if (response.status == 1) {
					$scope.list = response.data.data.list;
					$scope.pageCount = response.data.data.pages;
					$scope.currentPage = response.data.data.pageNum;
					$scope.totalCount = response.data.data.total;

					$(".tcdPageCode").createPage({
						pageCount: $scope.pageCount,
						current: $scope.currentPage,
						backFn: function(p) {
							$scope.currentPage = p;
							onPageChange();
						}
					});
				}
			}).error(function(response){
			});
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
			$scope.filecatelist = response.data.data.list;
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
	
	$scope.fileCateFilt = function($event, id){
		cateValue = id;
		$('#tabgroup1 a').removeClass('tab-active');
		$($event.target).addClass('tab-active');
		$http({
			method : "post",
			url : URL,
			params : {
				pageNum : "1",
				pageSize : "10",
				fileCateId : cateValue,
				type : typeValue,
				status : statusValue,
				openFlg : openFlgValue,
				name : searchName,
				userId : userId
			}
		}).success(function(response) {
			if (response.status == 1) {
				$scope.list = response.data.data.list;
				$scope.pageCount = response.data.data.pages;
				$scope.currentPage = response.data.data.pageNum;
				$scope.totalCount = response.data.data.total;

				$(".tcdPageCode").createPage({
					pageCount: $scope.pageCount,
					current: $scope.currentPage,
					backFn: function(p) {
						$scope.currentPage = p;
						onPageChange();
					}
				});
				if (id == 0) {
					$scope.filesubcatelist=null;
					subcateValue = '0';
				} else {
					subcateValue = '0';
					$http({
						method : "post",
						url : "api/mfilesubcate/list.show",
						params : {
							pageNum : "1",
							pageSize : "10",
							fileCateId : cateValue
						}
					}).success(function(response) {
						if (response.status == 1) {
							$scope.filesubcatelist = response.data.data.list;
						}
					}).error(function(response){
					});
				}
			}
		}).error(function(response){
		});
	};
	
	$scope.fileSubCateFilt = function($event, id){
		subcateValue = id;
		$('#tabgroup2 a').removeClass('tab-active');
		$($event.target).addClass('tab-active');
		filt();
	}
	
	$scope.typeFilt = function($event, id){
		typeValue = id;
		$('#tabgroup3 a').removeClass('tab-active');
		$($event.target).addClass('tab-active');
		filt();
	}
	
	$scope.statusFilt = function($event, id){
		statusValue = id;
		$('#tabgroup4 a').removeClass('tab-active');
		$($event.target).addClass('tab-active');
		filt();
	}
	
	$scope.openFlgFilt = function($event, id){
		openFlgValue = id;
		$('#tabgroup5 a').removeClass('tab-active');
		$($event.target).addClass('tab-active');
		filt();
	}
	
	$scope.searchFilt = function(){
		searchName = $('#searchName').val();
		filt();
	}
	
	$scope.detail = function(id){
		window.open("detail.html?id=" + id);
	}
	
	$scope.deleteFile = function(id){
		event.stopPropagation();
		$http({
			method : "post",
			url : "api/mfile/delete.do",
			params : {
				id : id
			}
		}).success(function(response) {
			if (response.status == 1) {
				filt();
			}
		}).error(function(response){
		});
	}
	
	var filt = function(){
		$http({
			method : "post",
			url : URL,
			params : {
				pageNum : "1",
				pageSize : "10",
				fileCateId : cateValue,
				fileSubCateId : subcateValue,
				type : typeValue,
				status : statusValue,
				openFlg : openFlgValue,
				name : searchName,
				userId : userId
			}
		}).success(function(response) {
			if (response.status == 1) {
				$scope.list = response.data.data.list;
				$scope.pageCount = response.data.data.pages;
				$scope.currentPage = response.data.data.pageNum;
				$scope.totalCount = response.data.data.total;

				$(".tcdPageCode").createPage({
					pageCount: $scope.pageCount,
					current: $scope.currentPage,
					backFn: function(p) {
						$scope.currentPage = p;
						onPageChange();
					}
				});
			}
		}).error(function(response){
		});
	}
	
	var onPageChange = function(){
		$('body,html').animate({scrollTop:0},50);
		$http({
			method:"post",
			url:URL,
			params:{
				pageNum:$scope.currentPage,
				pageSize:"10",
				fileCateId : cateValue,
				fileSubCateId : subcateValue,
				type : typeValue,
				status : statusValue,
				openFlg : openFlgValue,
				name : searchName,
				userId : userId
			}
		}).success(function(response){
			if(response.status == 1){
				$scope.list = response.data.data.list;
			}
		}).error(function(){
		});
	};
	
	$scope.fileChanged = function(ele){  
	    $scope.files = ele.files;
	    $scope.$apply(); //传播Model的变化。  
	}
	
	$scope.fileCoverChanged = function(ele){  
		$scope.coverFiles = ele.files;  
		$scope.$apply(); //传播Model的变化。  
		$('.sql-imgwrap').show();
		$('.sql-inputwrap').hide();
		fileReader.readAsDataUrl($scope.coverFiles[0], $scope)
        .then(function(result) {
            $scope.uploadCover = result;
        });
	}
	
	$scope.publish = function(){
		if ($scope.files == null || $scope.files[0] == null) {
			showtip('上传文件不能为空!', 'red');
			return;
		}
		var FileExtend = "mp4, avi, flv, mov, ts, rmvb, mkv,mp3, wma, wav, flac, m4a,doc, docx, xls, xlsx, pdf, ppt, pptx, txt, epub,bmp, gif, png, jpeg, jpg";
		var suffix = $scope.files[0].name.substring($scope.files[0].name.lastIndexOf('.') + 1).toLowerCase();
		if (FileExtend.indexOf(suffix)<0) {
			showtip('上传文件格式错误!', 'red');
			return;
		}
		if ($scope.uploadNameValue == "") {
			showtip('文件名称不能为空!', 'red');
			return;
		}
		if ($scope.uploadCateValue == "0") {
			showtip('必须选择具体的分类!', 'red');
			return;
		}
		if ($scope.uploadSubCateValue == "0") {
			showtip('必须选择具体的子分类!', 'red');
			return;
		}
		if ($scope.uploadActorValue == "") {
			showtip('作者不能为空!', 'red');
			return;
		}
		if ($scope.uploadCompanyValue == "") {
			showtip('单位不能为空!', 'red');
			return;
		}
		if ($scope.uploadDesValue == "") {
			showtip('简介不能为空!', 'red');
			return;
		}
		if ($scope.coverFiles == null || $scope.coverFiles[0] == null) {
			showtip('封面不能为空!', 'red');
			return;
		}
		showLoading();
		$scope.upload();
	}
	
	$scope.upload = function() {
		Upload.upload({
			// 服务端接收
			url : 'api/mfile/add.do',
			// 上传的同时带的参数
			data : {
				fileCateId : $scope.uploadCateValue,
				fileSubCateId : $scope.uploadSubCateValue,
				name : $scope.uploadNameValue,
				des : $scope.uploadDesValue,
				actor : $scope.uploadActorValue,
				company : $scope.uploadCompanyValue,
				openFlg : $scope.uploadOpenFlgValue,
				coverfile : $scope.coverFiles[0],
				file : $scope.files[0]
			}
		}).success(function(data) {
			hideLoading();
			showtip('新增成功', 'green');
			window.location.href = "list.html";
			// 上传成功
			console.log(data);
		}).error(function(data, status, headers, config) {
			hideLoading();
			showtip('新增失败', 'red');
			// 上传失败
			console.log('error status: ' + status);
		});
	};
	
	$scope.updateFileCateFilt = function(){
		$http({
			method : "post",
			url : "api/mfilesubcate/list.show",
			params : {
				pageNum : "1",
				pageSize : "10",
				fileCateId : $scope.uploadCateValue
			}
		}).success(function(response) {
			if (response.status == 1) {
				$scope.updatesubcatelist = response.data.data.list;
			}
		}).error(function(response){
		});
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

app.factory('fileReader', ["$q", "$log", function($q, $log){
  var onLoad = function(reader, deferred, scope) {
    return function () {
      scope.$apply(function () {
        deferred.resolve(reader.result);
      });
    };
  };
  var onError = function (reader, deferred, scope) {
    return function () {
      scope.$apply(function () {
        deferred.reject(reader.result);
      });
    };
  };
  var getReader = function(deferred, scope) {
    var reader = new FileReader();
    reader.onload = onLoad(reader, deferred, scope);
    reader.onerror = onError(reader, deferred, scope);
    return reader;
  };
  var readAsDataURL = function (file, scope) {
    var deferred = $q.defer();
    var reader = getReader(deferred, scope);		 
    reader.readAsDataURL(file);
    return deferred.promise;
  };
  return {
    readAsDataUrl: readAsDataURL  
  };
}]);

function showtip(str, color){
	dm_notification(str, color, 1500);
}

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
}

//隐藏遮罩
function hideLoading() {   
    $('#overlay').hide();   
    $("#loadingTip").hide();   
    $(document).scroll(function() {   
        return true;   
    });   
}

//浏览器兼容 取得浏览器可视区高度   
function getWindowInnerHeight() {   
    var winHeight = window.innerHeight   
            || (document.documentElement && document.documentElement.clientHeight)   
            || (document.body && document.body.clientHeight);   
    return winHeight;   
       
};
// 浏览器兼容 取得浏览器可视区宽度   
function getWindowInnerWidth() {   
    var winWidth = window.innerWidth   
            || (document.documentElement && document.documentElement.clientWidth)   
            || (document.body && document.body.clientWidth);   
    return winWidth;   
       
} ; 