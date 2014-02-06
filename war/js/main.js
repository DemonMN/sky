var token = '';
//
// FB.Event.subscribe('edge.create', function(response) {
// alert(JSON.stringify(response));
// });

// var APP_ROOT = 'http://localhost:8888';
var APP_ROOT = 'https://shagai1111.appspot.com';
// var ROOT = 'http://localhost:8888/_ah/api';
var APP_LOGIN = APP_ROOT + '/_ah/api/users/v1/login/';
var FACEBOOK_USER;
var app = angular.module("app", [ 'ngRoute', 'ngCookies', 'ngSanitize', 'LocalStorageModule', 'blueimp.fileupload' ]);
var UPLOAD_URL = 'https://www.googleapis.com/upload/storage/v1beta2/b/tsagmn1/o?uploadType=media&key=AIzaSyCtKbqAK-tCOEiwiRfSnfjFxnt00APHGpg';
$(function() {
	$(".navbar-nav a").click(function() {
		$(".navbar-nav .active").removeClass("active");
		$(this).parent().addClass("active");
	});
});
//app.config(function($routeProvider) {
//	$routeProvider.when('/', {
//		templateUrl : "how-it-works.html",
//	}).when('/news', {
//		templateUrl : "news.html"
//	}).when('/news/:postId', {
//		templateUrl : "news.html"
//	}).when('/home', {
//		templateUrl : "home.html",
//		controller : "userController"
//	}).when('/home/news', {
//		templateUrl : "user-posts.html",
//		controller : "userController"
//	}).when('/login', {
//		templateUrl : "login.html",
//		controller : "userController"
//	}).when('/photo', {
//		templateUrl : "photo.html",
//		controller : "photoController"
//	}).when('/photo/view', {
//		templateUrl : "photo.html",
//		controller : "photoController"
//	}).when('/how-it-is-work', {
//		templateUrl : "how-it-works.html"
//	}).when('/account', {
//		templateUrl : "account.html",
//		controller : "accountController"
//	}).when('/auction', {
//		templateUrl : "auction.html",
//		controller : "auctionController"
//	})
//});
//app.config([
//		'$httpProvider',
//		'fileUploadProvider',
//		function($httpProvider, fileUploadProvider) {
//			delete $httpProvider.defaults.headers.common['X-Requested-With'];
//			fileUploadProvider.defaults.redirect = window.location.href
//					.replace(/\/[^\/]*$/, '/cors/result.html?%s');
//			angular.extend(fileUploadProvider.defaults, {
//				// Enable image resizing, except for Android and Opera,
//				// which actually support image resizing, but fail to
//				// send Blob objects via XHR requests:
//				disableImageResize : /Android(?!.*Chrome)|Opera/
//						.test(window.navigator.userAgent),
//				maxFileSize : 5000000,
//				acceptFileTypes : /(\.|\/)(gif|jpe?g|png)$/i
//			});
//		} ]);
//app.directive('lazy', function($timeout) {
//	return {
//		restrict : 'C',
//		link : function(scope, elm, attrs) {
//			$timeout(function() {
//				$(elm).lazyload()
//			}, 0);
//		}
//	}
//});
//function alert2(object) {
//	alert(JSON.stringify(object));
//}
app.controller('newsController', [
		'$scope',
		'$http',
		'$routeParams',
		'localStorageService',
		'$sce',
		'$rootScope',
		'$location',
		function($scope, $http, $routeParams, storage, $sce, $rootScope,
				$location) {
			$scope.post = {};
			$scope.posts = [];
			if (!$rootScope.me) {
				$rootScope.me = storage.get("me");
			}
			if (!$rootScope.token) {
				$rootScope.token = storage.get("token");
			}
			$http({
				method : 'GET',
				url : APP_ROOT + '/_ah/api/news/v1/postcollection'
			}).success(function(response) {
				$scope.posts = response.items;
				angular.forEach($scope.posts, function(post) {
					post.date = prettyDate(post.insertDate);
				});
			});
			

			$http({
				method : 'GET',
				url : APP_ROOT + '/_ah/api/news/v1/message'
			}).success(function(response) {
				// var channel = new
				// goog.appengine.Channel(response.data.token);
				// var socket = channel.open();
				// socket.onopen = function() {
				//
				// }
				// socket.onmessage = function(message) {
				// var post = JSON.parse(message.data);
				// post.date = prettyDate(post.insertDate);
				// $scope.posts.unshift(post);
				// $scope.$apply();
				// }
			});

			$scope.read = function(post) {
				$http.get('/register?post=' + post.id).success(
						function(response) {
							post.visited = response;
						});
				post.date = prettyDate(post.insertDate);
				if ($scope.current) {
					$scope.current.read = null;
				}
				$scope.current = post;
				post.read = post.summary.value.replace(/\n/g, "<br/>");
			}
		}]);
//var callBackQueue = new Array();
//var status = 'IDLE';
//function appConnect($http, $scope, storage, callback) {
//	callBackQueue.push(callback);
//	if (status != 'IDLE') {
//		return;
//	}
//	status = 'SENDING';
//	$http({
//		method : 'POST',
//		url : APP_LOGIN + storage.get("token")
//	}).success(function(response) {
//		$scope.message = 'welcome';
//		for ( var i = 0; i < callBackQueue.length; i++) {
//			callBackQueue[i](response.data.me, storage.get("token"));
//		}
//		status = 'IDLE';
//		callBackQueue = new Array();
//	}).error(function(response) {
//		FB.getLoginStatus(function(response) {
//			if (response.status === 'connected') {
//				storage.add("token", response.authResponse.accessToken);
//				appConnect($http, $scope, storage, callback);
//			}
//		});
//	});
//}
//function login($http, $scope, storage, callback) {
//	if (storage.get("token")) {
//		appConnect($http, $scope, storage, callback);
//	} else {
//		FB.login(function(response){
//			if (response.status === 'connected') {
//				FB.Event.subscribe('auth.authResponseChange');
//				storage.add("token", response.authResponse.accessToken);
//				login($http, $scope, storage, callback);
//			}
//		}, 
//		{
//			scope : 'user_photos, friends_photos'
//		});
//	}
//}
//app.controller('userController', [
//		'$scope',
//		'$http',
//		'localStorageService',
//		'$location',
//		'$route',
//		'$rootScope',
//		function($scope, $http, storage, $location, $route, $rootScope) {
//
//			$scope.post = {};
//			if (!$rootScope.me) {
//				$rootScope.me = storage.get("me");
//				$rootScope.$watch('me.posts', function() {
//					storage.add("me", $rootScope.me);
//				});
//			}
//			if (!$rootScope.token) {
//				$rootScope.token = storage.get("token");
//			}
//			$scope.send = function() {
//				$scope.post.userId = $rootScope.me.id;
//				$http.post(
//						APP_ROOT + '/_ah/api/news/v1/createArticle/'
//								+ $rootScope.token, $scope.post).success(
//						function(response) {
//							if (!$rootScope.me.posts) {
//								$rootScope.me.posts = [];
//							}
//							if ($scope.post.id) {
//								var index = $rootScope.me.posts
//										.indexOf($scope.post);
//								$rootScope.me.posts.splice(index, 1);
//							}
//							$rootScope.me.posts.unshift(response.data.post);
//							storage.add("me", $rootScope.me);
//							$scope.post = {};
//							$scope.post.message = "Success";
//						}).error(function(data, status, headers, config) {
//					storage.add("token", undefined);
//					login($http, $scope, storage);
//				});
//			};
//			$scope.edit = function(post) {
//				$scope.post = post;
//			};
//			$scope.removePost = function(post) {
//				$http.post(
//						APP_ROOT + '/_ah/api/news/v1/news/'
//								+ storage.get("token"), post).success(
//						function(response) {
//							var index = $rootScope.me.posts.indexOf(post);
//							$rootScope.me.posts.splice(index, 1);
//							storage.add("me", $rootScope.me);
//							$scope.post.message = "Success";
//							$rootScope.$apply();
//						}).error(function(data, status, headers, config) {
//					alert2(data);
//				});
//			};
//			$scope.account = function() {
//				alert(0);
//			}
//			$scope.login = function() {
//				login($http, $scope, storage, function(me, token) {
//					$rootScope.me = me;
//					$rootScope.token = token;
//					storage.add("token", token);
//					storage.add("me", me);	
//					$location.path("/home");
//				});
//			}
//			FB.api('/me', function(response) {
//				  alert2(response);
//			});
//			FB.api('/me?fields=albums.fields(photos.fields(images))', function(response) {
//				$scope.albums = response;
//			});
//		}]);
//
//app.controller('homeController', function($scope, $http, $routeParams) {
//	$scope.post = {};
//	$scope.registerUrl = APP_ROOT + '/register';
//	$scope.$watch('posts', function(newValue, oldValue) {
//		FB.XFBML.parse();
//	});
//
//	$scope.$on('$routeChangeSuccess', function() {
//		var $container = $('#photoset-grid-basic'), $imgs = $container.find(
//				'img').hide(), totalImgs = $imgs.length, cnt = 0;
//		var imgarr = new Array();
//		$('#loadmore').show().bind('click', function() {
//			// var len = imgarr.length;
//			// for ( var i = 0, newimgs = ''; i < 15; ++i) {
//			// var pos = Math.floor(Math.random() * len), src = imgarr[pos];
//			// newimgs += '<a href="#"><img src="' + src + '"/></a>';
//			// }
//			//
//			// var $newimages = $(newimgs);
//			// $newimages.imagesLoaded(function() {
//			// $container.append($newimages).montage('add', $newimages);
//			// });
//		});
//		$imgs.each(function(i) {
//			var $img = $(this);
//			$('<img/>').load(function() {
//				++cnt;
//				if (cnt === totalImgs) {
//					$imgs.show();
//					$container.montage({
//						minsize : true,
//						margin : 2,
//						alternateHeight : true,
//						alternateHeightRange : {
//							min : 90,
//							max : 240
//						},
//					});
//
//					/*
//					 * just for this demo:
//					 */
//					$('#overlay').fadeIn(500);
//				}
//				;
//				imgarr.push($img.attr('src'));
//			}).attr('src', $img.attr('src'));
//		});
//	});
//});
//
//app.controller('auctionController', [
//		'$scope',
//		'$http',
//		'$routeParams',
//		'localStorageService',
//		'$sce',
//		function($scope, $http, $routeParams, storage, $sce) {
//			$scope.bids = [];
//			$http({
//				method : 'GET',
//				url : APP_ROOT + '/_ah/api/auction/v1/message'
//			}).success(function(response) {
//				var channel = new goog.appengine.Channel(response.data.token);
//				var socket = channel.open();
//				socket.onopen = function() {
//
//				}
//				socket.onmessage = function(message) {
//					$scope.bids.unshift(message);
//					$scope.$apply();
//				}
//			});
//			$scope.bid = function() {
//				$http.post(
//						APP_ROOT + '/_ah/api/auction/v1/createArticle/'
//								+ storage.get("token")).success(
//						function(response) {
//							$scope.message = response;
//						});
//			};
//		} ]);
//function loadBacground(obj, callback) {
//	var img = new Image();
//	img.src = obj.mediaLink;
//	img.onload = function() {
//		callback(obj);
//	}
//}
//var imagepool = new Array();
//var token = '';
//var loaded = false;
//function shuffle(array) {
//	var currentIndex = array.length, temporaryValue, randomIndex;
//
//	// While there remain elements to shuffle...
//	while (0 !== currentIndex) {
//
//		// Pick a remaining element...
//		randomIndex = Math.floor(Math.random() * currentIndex);
//		currentIndex -= 1;
//
//		// And swap it with the current element.
//		temporaryValue = array[currentIndex];
//		array[currentIndex] = array[randomIndex];
//		array[randomIndex] = temporaryValue;
//	}
//
//	return array;
//}
//function loadPool($http, limit, $scope, storage) {
//	var count = imagepool.length - limit;
//	if (count < 0) {
//		$scope.photos = $scope.photos.concat(imagepool);
//		var size = 1000;
//		if (!loaded) {
//			size = 200;
//			loaded = true;
//		}
//		$http.get("https://www.googleapis.com/storage/v1beta2/b/caakmn/o?maxResults=" + size + "&pageToken=" + token)
//		.success(function(response) {
//			token = response.nextPageToken;
//			for ( var index = 0; index < response.items.length; index++) {
//				loadBacground(response.items[index], function(
//						obj) {
//					if (count++ < 0) {
//						$scope.photos.unshift(obj);
//						$scope.$apply();
//					} else {
//						imagepool.unshift(obj);
//					}
//				});
//			}
//			imagepool = shuffle(imagepool);
//		});
//	} else {
//		var data = imagepool.splice(0, limit);
//		$scope.photos = $scope.photos.concat(data);
//	}
//}
//
//function show(obj) {
//	var image = $('img', obj);
//	var width = $(image)[0].naturalWidth;
//	if (width > 420) {
//		width = 420;
//	}
//	FB.XFBML.parse($(obj)[0], function() {
//		$('img', obj).width(width);
//		$(obj).css('height', 'auto');
//		var height = $(obj).height();
//		$(".border-less", obj).hide();
//		$(obj).css("display", "block");
//		$(obj).animate({
//			width : $(obj).parent().width()
//		}, 300, function() {
//			$(obj).animate({
//				height : height,
//				width : $(obj).parent().width()
//			}, function() {
//				$(obj).parent().height({
//					height : $(this).height() + $(obj).height()
//				});
//				$(".border-less", obj).show();
//				$('html, body').animate({
//					scrollTop : $(obj).offset().top
//				});
//				$(".border-less", obj).removeClass("border-less");
//			});
//		});
//	});
//}
//
//app.controller('galleryController', [
//		'$scope',
//		'$http',
//		'$routeParams',
//		'localStorageService',
//		'$sce',
//		'$rootScope',
//		'$filter',
//		'$window',
//		function($scope, $http, $routeParam, storage, $sce, $rootScope, $filter, $window) {
//			$scope.photos = new Array();
//			$scope.token = "";
//			$scope.limit = 50;
//			if ($routeParam.token) {
//				$scope.pageToken = $routeParam.token;
//			}
//			$scope.next = function() {
//				loadPool($http, $scope.limit, $scope, storage);
//			}
//			if ($routeParam.large) {
//				$http.get($routeParam.large).success(function(response) {
//					$scope.photos.unshift(response);
//					$scope.$apply();
//					show($("#photoset-grid-basic:first-child"));
//					$scope.next();
//				});
//			}
//			else{
//				$scope.next();
//			}
//			$scope.show = function(file) {
//				$http.get('/register?post=' + file.selfLink).success();
//			}
//		}]);
//app.controller('photoController', [
//		'$scope',
//		'$http',
//		'$routeParams',
//		'localStorageService',
//		'$sce',
//		'$rootScope',
//		'$filter',
//		'$window',
//		function($scope, $http, $routeParam, storage, $sce, $rootScope, $filter, $window) {
//			FACEBOOK_USER = storage.get("me");
//			$scope.options = {
//				multipart : false,
//				url : UPLOAD_URL
//			};
//			$scope.loadingFiles = true;
//			$http.get(UPLOAD_URL).then(function(response) {
//				alert2(response);
//				$scope.loadingFiles = false;
//				$scope.queue = response.data.files || [];
//			}, function() {
//				$scope.loadingFiles = false;
//			});
//			
//			
//
//		}]);
//app.controller('FileDestroyController', [ '$scope', '$http',
//		function($scope, $http) {
//			var file = $scope.file, state;
//			if (file.url) {
//				file.$state = function() {
//					return state;
//				};
//				file.$destroy = function() {
//					state = 'pending';
//					return $http({
//						url : file.deleteUrl,
//						method : file.deleteType
//					}).then(function() {
//						state = 'resolved';
//						$scope.clear(file);
//					}, function() {
//						state = 'rejected';
//					});
//				};
//			} else if (!file.$cancel && !file._index) {
//				file.$cancel = function() {
//					$scope.clear(file);
//				};
//			}
//		} ]);
