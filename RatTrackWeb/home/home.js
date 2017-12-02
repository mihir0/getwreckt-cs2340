'use strict';

angular.module('ratTrack.home', ['ngRoute', 'firebase'])

// Declared route
.config(['$routeProvider', function($routeProvider) {
    $routeProvider.when('/home', {
        templateUrl: 'home/home.html',
        controller: 'HomeCtrl'
    });
}])

// Home controller
.controller('HomeCtrl', ['$scope','$firebaseSimpleLogin',function($scope, $firebaseSimpleLogin) {
    var firebaseObj = new Firebase("https://blistering-heat-2473.firebaseio.com/");
    var loginObj = $firebaseSimpleLogin(firebaseObj);

    $scope.SignIn = function(event) {
        event.preventDefault(); //prevent form refresh
        var username = $scope.user.email;
        var password = $scope.user.password;

        loginObj.$login('password', {
            email: username,
            password: password
        })
        .then(function(user) {
            //success callback
            console.log('Authentication successful');
        }, function(error) {
            //failure callback
            console.log('Authentication failure');
        });
    }
}]);
