<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE script PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="app" >
<head>
	<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.2.3/angular.min.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.2.3/angular-resource.min.js"></script>
	<script src="//ajax.googleapis.com/ajax/libs/angularjs/1.2.3/angular-route.js"></script>
	
	<script src="/js/angular-storage.js"></script>
</head>
<script>
	var APP = angular.module("app", [ 'ngRoute', 'LocalStorageModule']);
	var APP_ROOT = 'http://localhost:8888';
	var CREATE_URL = APP_ROOT + '/_ah/api/survey/v1/createSurvey/';
	var SAVE_URL = APP_ROOT + '/_ah/api/survey/v1/save/';
	var ADD_QUESTION_URL = APP_ROOT + '/_ah/api/survey/v1/addQuestion/';
	var UPDATE_QUESTION_URL = APP_ROOT + '/_ah/api/survey/v1/survey/';
	var ADD_CHOICE_URL = APP_ROOT + '/_ah/api/survey/v1/addChoice/';
	var FIND_SURVEY_URL = APP_ROOT + '/_ah/api/survey/v1/survey/';
	var ALL_SURVEY_URL = APP_ROOT + '/_ah/api/survey/v1/surveycollection';
	var FIND_QUESTION_URL = APP_ROOT + '/_ah/api/survey/v1/question/';
	var DELETE_CHOICE_URL = APP_ROOT + '/_ah/api/survey/v1/choice/';
	
	APP.config(function($routeProvider) {
		$routeProvider
		.when('/', {
			templateUrl : "main.html",
			controller : 'mainController'
		})
		.when('/survey/', {
			templateUrl : "survey-edit.html",
			controller : 'surveyController'
		})
		.when('/question/', {
			templateUrl : "question.html",
			controller : 'questionController'
		})
	});
	function alert2(object) {
		alert(JSON.stringify(object));
	}
	APP.controller('mainController', ['$scope', '$http', 'localStorageService', '$rootScope', '$location', '$routeParams',
		function($scope, $http, storage, $rootScope, $location, $routeParams) {
			
			$http.get(ALL_SURVEY_URL).then(function(response) {
				$scope.surveys = response.data.items;
			});
			$scope.create = function (){
				$http.post(CREATE_URL + $scope.survey.name).then(function(response) {
					$scope.survey = response.data;
					$location = "/survey/" + survey.key.id;
				}, function() {
					$scope.loadingFiles = false;
				});
			}
		}
	]);
	APP.controller('surveyController', ['$scope', '$http', 'localStorageService', '$rootScope', '$location', '$routeParams',
		function($scope, $http, storage, $rootScope, $location, $routeParams) {
			$scope.survey = {};
			if ($routeParams.survey){
				$http.get(FIND_SURVEY_URL + $routeParams.survey).then(function(response) {
					$scope.survey = response.data;
				});
			}
			$scope.active = {};
			$scope.addQuestion = function (){
				$http.post(ADD_QUESTION_URL + $scope.survey.key.id, $scope.active).then(function(response) {
					$scope.survey = response.data;
				}, function() {
					$scope.loadingFiles = false;
				});
			}
			$scope.save = function (){
				$http.post(SAVE_URL, $scope.survey).then(function(response) {
					$scope.survey = response.data;
					
				}, function() {
					$scope.loadingFiles = false;
				});
			}
			$scope.updateQuestion = function (question){
				$http.put(UPDATE_QUESTION_URL + $scope.survey.key.id + "/" + question.key.id, question).then(function(response) {
					$scope.survey = response.data;
				}, function() {
					$scope.loadingFiles = false;
				});
			}
			$scope.addChoice = function (question){
				$http.post(ADD_CHOICE_URL + $scope.survey.key.id + "/" + question.key.id, question.active).then(function(response) {
					$scope.survey = response.data;
				}, function() {
					$scope.loadingFiles = false;
				});
			}
			$scope.deleteChoice = function (question, choice){
				$http.delete(DELETE_CHOICE_URL + $scope.survey.key.id + "/" + question.key.id + "/" + choice.key.id).then(function(response) {
					$scope.survey = response.data;
				}, function() {
					$scope.loadingFiles = false;
				});
			}
		}]);
	APP.controller('questionController', ['$scope', '$http', 'localStorageService', '$rootScope', '$location', '$routeParams',
		function($scope, $http, storage, $rootScope, $location, $routeParams) {
			$scope.question = {};
			if ($routeParams.question){
				$http.get(FIND_QUESTION_URL + $routeParams.survey + "/" + $routeParams.question).then(function(response) {
					$scope.question = response.data;
				});
			}
			$scope.active = {};
			
			$scope.addChoice = function (){
				$http.post(ADD_CHOICE_URL + $routeParams.survey + "/" + $routeParams.question, $scope.active).then(function(response) {
					$scope.question = response.data;
				}, function() {
					$scope.loadingFiles = false;
				});
			}
			$scope.save = function (){
				$http.post(SAVE_URL, $scope.survey).then(function(response) {
					$scope.survey = response.data;
				}, function() {
					$scope.loadingFiles = false;
				});
			}
		}]);
</script>
<body>
	<div ng-view></div>
</body>
</html>