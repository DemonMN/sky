var FB_APP_ID = '236027393115554';
FB.init({
	appId : FB_APP_ID,
	status : true, // check login status
	cookie : true, // enable cookies to allow the server to access the session
	xfbml : true
});
var APP_ROOT = 'http://localhost:8888';
//var APP_ROOT = 'https://shagai1111.appspot.com';
// var ROOT = 'http://localhost:8888/_ah/api';
var APP_LOGIN = APP_ROOT + '/_ah/api/users/v1/login/';
var APP = angular.module("app", [ 'ngRoute', 'LocalStorageModule']);
var UPLOAD_URL = 'https://www.googleapis.com/upload/storage/v1beta2/b/tsagmn1/o?uploadType=media&key=AIzaSyCtKbqAK-tCOEiwiRfSnfjFxnt00APHGpg';
var APP_LOGIN = APP_ROOT + '/_ah/api/users/v1/login/';
$(function() {
	$(".navbar-nav a").click(function() {
		$(".navbar-nav .active").removeClass("active");
		$(this).parent().addClass("active");
	});
});

function alert2(object) {
	alert(JSON.stringify(object));
}


APP.config(function($routeProvider) {
	$routeProvider
	.when('/', {
		templateUrl : "how-it-works.html",
	})
	.when('/news', {
		templateUrl : "news.html"
	})
	.when('/news/:postId', {
		templateUrl : "news.html"
	})
	.when('/home', {
		templateUrl : "home.html",
		controller : "userController"
	})
	.when('/home/news', {
		templateUrl : "user-posts.html",
		controller : "userController"
	})
	.when('/login', {
		templateUrl : "login.html",
		controller : "loginController"
	})
	.when('/photo', {
		templateUrl : "photo.html",
		controller : "photoController"
	})
	.when('/photo/view', {
		templateUrl : "photo.html",
		controller : "photoController"
	})
	.when('/how-it-is-work', {
		templateUrl : "how-it-works.html"
	})
	.when('/account', {
		templateUrl : "account.html",
		controller : "accountController"
	})
	.when('/auction', {
		templateUrl : "auction.html",
		controller : "auctionController"
	})
});
function connect($http, storage, $location){
	
}
APP.controller('loginController', ['$scope', '$http', 'localStorageService', '$rootScope',
function($scope, $http, storage, $rootScope) {
	if (storage.get("me")){
		$rootScope.me = storage.get("me");
	}
	FB.login(function(response) {
		alert2(response);
		if (response.status === 'connected') {
			$rootScope.me = storage.get("me");
			$rootScope.token = response.authResponse.accessToken;
			storage.add("token", $rootScope.token);
			$rootScope.$apply();
		}
	});
//	FB.Event.subscribe('auth.authResponseChange', function(response) {
//		if (response.status === 'connected') {
//			storage.add("token", response.authResponse.accessToken);
//			FB.api("/me", function (response){ 
//				storage.add("me", response);
//				$rootScope.me = response;
//				$rootScope.$apply();
//				$.unblockUI();
//			});
//		}
//	})
	$scope.login = function() {
		FB.login(function(response){
				if (response.status === 'connected') {
					storage.add("token", response.authResponse.accessToken);
					$rootScope.token = response.authResponse.accessToken;
					$http({
						method : 'POST',
						url : APP_LOGIN + storage.get("token")})
					.success(function (response){
						storage.add("me", response.data.me);
						$rootScope.me = response.data.me ;
						$rootScope.$apply();
					});					
				}
		},{scope : 'user_photos, friends_photos'});
	}
	$scope.logout = function() {
		$http.post(APP_ROOT + '/_ah/api/users/v1/logout/' + $rootScope.token).success();
		storage.add("token", undefined);
		storage.add("me", undefined);
		$rootScope.me = undefined;
		$rootScope.token = undefined;
		$location.path("/news");
	}
}]);
APP.controller('newsController', ['$scope', '$http', '$routeParams', 'localStorageService', '$sce', '$rootScope', '$location',
function($scope, $http, $routeParams, storage, $sce, $rootScope, $location){
	
}]);
APP.controller('userController', ['$scope','$http','localStorageService','$location','$route', '$rootScope',
function($scope, $http, storage, $location, $route, $rootScope) {
	
	
//	$scope.post = {};
//	if (!$rootScope.token) {
//		$rootScope.token = storage.get("token");
//	}
//	$scope.send = function() {
//		$scope.post.userId = $rootScope.me.id;
//		$http.post(
//				APP_ROOT + '/_ah/api/news/v1/createArticle/'
//						+ $rootScope.token, $scope.post).success(
//				function(response) {
//					if (!$rootScope.me.posts) {
//						$rootScope.me.posts = [];
//					}
//					if ($scope.post.id) {
//						var index = $rootScope.me.posts
//								.indexOf($scope.post);
//						$rootScope.me.posts.splice(index, 1);
//					}
//					$rootScope.me.posts.unshift(response.data.post);
//					storage.add("me", $rootScope.me);
//					$scope.post = {};
//					$scope.post.message = "Success";
//				}).error(function(data, status, headers, config) {
//			storage.add("token", undefined);
//			login($http, $scope, storage);
//		});
//	}; object_id IN (SELECT object_id FROM like WHERE user_id = me()) OR object_id IN (SELECT object_id FROM like WHERE user_id = me())
//	$scope.edit = function(post) {
//		$scope.post = post;
//	};
//	$scope.removePost = function(post) {
//		$http.post(
//				APP_ROOT + '/_ah/api/news/v1/news/'
//						+ storage.get("token"), post).success(
//				function(response) {
//					var index = $rootScope.me.posts.indexOf(post);
//					$rootScope.me.posts.splice(index, 1);
//					storage.add("me", $rootScope.me);
//					$scope.post.message = "Success";
//					$rootScope.$apply();
//				}).error(function(data, status, headers, config) {
//			alert2(data);
//		});
//	};
//	$scope.account = function() {
//		alert(0);
//	}
//	$scope.login = function() {
//		login($http, $scope, storage, function(me, token) {
//			$rootScope.me = me;
//			$rootScope.token = token;
//			storage.add("token", token);
//			storage.add("me", me);	
//			$location.path("/home");
//		});
//	}
//	FB.Event.subscribe('auth.authResponseChange', function(response) {
//		if (response.status === 'connected') {
//			$rootScope.token = response.authResponse.accessToken;
//			storage.add("token", $rootScope.token);
//		}
//	});
//	FB.api("/" + FB_APP_ID + "/subscriptions?access_token=" + storage.get("token"),
//		function (response) {
//			alert2(response)
//		});
//	FB.api('/me?fields=albums.fields(photos.fields(images),cover_photo,name)&access_token=' + storage.get("token"), function(response) {
//		$scope.albums = response.albums.data;
//		$scope.$apply();
//	});
//	
}]);
APP.controller('photoController', ['$scope', '$http', '$routeParams', 'localStorageService', '$sce', '$rootScope', '$filter', '$window',
function($scope, $http, $routeParam, storage, $sce, $rootScope, $filter, $window) {
	FACEBOOK_USER = storage.get("me");
	$scope.options = {
		multipart : false,
		url : UPLOAD_URL
	};
	$scope.loadingFiles = true;
	$http.get(UPLOAD_URL).then(function(response) {
		alert2(response);
		$scope.loadingFiles = false;
		$scope.queue = response.data.files || [];
	}, function() {
		$scope.loadingFiles = false;
	});
}]);

function loadList($http, limit, $scope, storage, callback) {
	$http.get("https://www.googleapis.com/storage/v1beta2/b/caakmn/o?maxResults=" + (limit * 2) + "&pageToken=" + token).success(function(response) {
		token = response.nextPageToken;
		callback(response.items);
	});
}
function cacheImage(cacheList) {
	for ( var index = 0; index < cacheList.length; index++) {
		loadBacground(cacheList[index], function(obj) {
			imagepool.unshift(obj);
		});
	}
}
function loadPool($http, limit, $scope, storage) {
	var data = imagepool.splice(0, limit);
	$scope.photos = $scope.photos.concat(data);
	$('.superbox').SuperBox();
	loadList($http, limit , $scope, storage, function (data){
		cacheImage(data);
	});
}
var imagepool = new Array();
var loaded = false;
var token = '';
function loadBacground(obj, callback) {
	var img = new Image();
	img.src = obj.mediaLink;
	img.onload = function() {
		callback(obj);
	}
}
function shuffle(array) {
	var currentIndex = array.length, temporaryValue, randomIndex;
	
	// While there remain elements to shuffle...
	while (0 !== currentIndex) {
	
		// Pick a remaining element...
		randomIndex = Math.floor(Math.random() * currentIndex);
		currentIndex -= 1;
	
		// And swap it with the current element.
		temporaryValue = array[currentIndex];
		array[currentIndex] = array[randomIndex];
		array[randomIndex] = temporaryValue;
	}
	
	return array;
}
APP.controller('galleryController', ['$scope', '$http', 'localStorageService', '$rootScope', '$routeParams',
function($scope, $http, storage, $rootScope, $routeParams) {
	
	$scope.photos = new Array();
	$scope.limit = 120;
	if ($routeParams.token) {
		$scope.pageToken = $routeParams.token;
	}
	$scope.$watch("photos", function (value) {
		alert(value.length);
	});
	loadList($http, $scope.limit * 2, $scope, storage, function (data){
		alert('init');
		$scope.photos = data.splice(0, $scope.limit);
		cacheImage(data);
	});
	$scope.next = function() {
		loadPool($http, $scope.limit, $scope, storage);
	}
	if ($routeParams.large) {
		$http.get($routeParams.large).success(function(response) {
			$scope.photos.unshift(response);
			$scope.$apply();
			$scope.next();
		});
	}
	else{
		$scope.next();
	}
	$scope.show = function(file) {
		$http.get('/register?post=' + file.selfLink).success();
	}
}]);