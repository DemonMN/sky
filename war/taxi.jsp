<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE script PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="app" >
<head>
	<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.2.3/angular.min.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.2.3/angular-resource.min.js"></script>
	<script src="//ajax.googleapis.com/ajax/libs/angularjs/1.2.3/angular-route.js"></script>
	<script src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false&language=mn"></script>
	<script src="/js/pretty-date.js"></script>
	<script src="/js/map-style.js"></script>
	<script src="/js/markercluster.js"></script>
	<script src="//connect.facebook.net/en_US/all.js"></script>
	<script src="/js/angular-storage.js"></script>
</head>
<style>
	#map-canvas, #directions {
		height: 400px;
		width: 900px;
		margin: 0px;
		padding: 0px
	}
</style>
<script>
	var APP = angular.module("app", [ 'ngRoute', 'LocalStorageModule']);
	var FB_APP_ID = '236027393115554';
	FB.init({
		appId : FB_APP_ID,
		status : true, // check login status
		cookie : true, // enable cookies to allow the server to access the session
		xfbml : true
	});
	var APP_ROOT = 'http://localhost:8888';
	var CREATE_URL = APP_ROOT + '/_ah/api/survey/v1/createSurvey/';
	var SAVE_URL = APP_ROOT + '/_ah/api/trip/v1/save/';
	var UPDATE_URL = APP_ROOT + '/_ah/api/trip/v1/trip/';
	var GET_ALL_URL = APP_ROOT + '/_ah/api/trip/v1/allTrip/';
	var GET_ADDRESS_URL = 'http://maps.googleapis.com/maps/api/geocode/json?sensor=false&latlng=';
	var MAP;
	var geocoder;
	var directionsDisplay;
	var brooklyn = new google.maps.LatLng(47.9200, 106.9200);
	var directionsService = new google.maps.DirectionsService();
	var origin = null;
	var destination = null;
	var waypoints = [];
	var markers = [];
	function initialize(callback) {
		var mapOptions = {
			zoom: 13,
			center: new google.maps.LatLng(47.9200, 106.9200),
			styles : mapStyle
		};
		MAP = new google.maps.Map(document.getElementById('map-canvas'), mapOptions);
		geocoder = new google.maps.Geocoder();
		directionsDisplay = new google.maps.DirectionsRenderer({
			  draggable: true
		});
		directionsDisplay.setMap(MAP);
	    directionsDisplay.setPanel(document.getElementById("directions"));
	    callback();
	}
	function addMarker(event){
		var marker = new google.maps.Marker({
		    position: event.latLng,
		    map: MAP,
		    draggable: true
		});
		google.maps.event.addListener(marker, 'dragend', function(event) {  
			geocoder.geocode({'latLng': event.latLng}, function(results, status) {
			    if (status == google.maps.GeocoderStatus.OK) {
			    	alert2(results);
			    }
			});
		});
		return marker;
	}
	function calculateRoute($scope){
		var request = {
		        origin: origin.position,
		        destination: destination.position,
		        waypoints: waypoints,
		        travelMode: google.maps.DirectionsTravelMode.DRIVING
	    	};
		directionsService.route(request, function(response, status) {
			if (status == google.maps.DirectionsStatus.OK) {
				$scope.direction = response;
				directionsDisplay.setDirections(response);
			}
	    });
	}
	
	APP.config(function($routeProvider) {
		$routeProvider
		.when('/', {
			templateUrl : "main.html",
			controller : 'mainController'
		})
	});
	function alert2(object) {
		alert(JSON.stringify(object));
	}
	function authMethod(callback){
		FB.getLoginStatus(function(response) {
			if (response.status === 'connected') {
				callback(response.authResponse.accessToken);
			}
			else{
				FB.login(function (response){
					authMethod(callback);
				});
			}
		});
	}
	APP.controller('mainController', ['$scope', '$http', 'localStorageService', '$rootScope', '$location', '$routeParams',
		function($scope, $http, storage, $rootScope, $location, $routeParams) {
			initialize(function (){
				google.maps.event.addListener(MAP, 'click', function(event) {
					if (!origin){
						origin = addMarker(event);
						$scope.trip.orgin = event.latLng;
					}
					else if (!destination){
						destination = addMarker(event);
						origin.setMap(null);
						destination.setMap(null);
						$scope.trip.destination = event.latLng;
						calculateRoute($scope);
					}
				});
				
				authMethod(function (token){
					$http.post(GET_ALL_URL + token).then(function(response) {
						$scope.trips = response.data.items;
						markers = [];
						for (var i=0; i < $scope.trips.length; i++){
							$scope.trips[i].prettyDate =  prettyDate($scope.trips[i].date);
							var trip = $scope.trips[i].orgin;
							var latLng  = new google.maps.LatLng(trip.d, trip.e);
							var marker = new google.maps.Marker({
							    position: latLng,
							    icon: 'http://graph.facebook.com/'+ $scope.trips[i].fbUser.id +'/picture?type=square',
							    map: MAP
							});
							markers.push(marker);
							google.maps.event.addListener(marker, "click", function () {
								MAP.setCenter(this.getPosition()); 
								MAP.setZoom(20);
						    });
						}
						var markerCluster = new MarkerClusterer(MAP, markers);
					});
				});
			});
			
			$scope.create = function (){
				authMethod(function (token){
					$http.post(SAVE_URL + token, $scope.trip).then(function(response) {
						$scope.trip = {};						
					});
				});
			}
			if (storage.get("me")){
		 		$scope.user = storage.get("me")
		 	}
			$scope.direction = {};
			$scope.trip = {};
			$scope.trip.date = new Date();
			$scope.trips = [];
		}
	]);
	
</script>
<body ng-controller="mainController">
	
	<div id="map-canvas"></div>
	<div id="directions"></div>
	<input type="button" ng-click="create()" value="add"/>
	<input type="text" ng-model="trip.date" />
	
	<br/>
	<ul class="list-group">
		<li ng-repeat="trip in trips" class="list-group-item">
			<input type="text" ng-model="trip.date" />
			{{trip.fbUser.id}}
			{{trip.prettyDate}}
		</li>
	</ul>
</body>
</html>