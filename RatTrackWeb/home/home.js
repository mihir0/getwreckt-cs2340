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
.controller('HomeCtrl', ['$scope', '$signInWithEmailAndPassword', function($scope, $signInWithEmailAndPassword) {
    // var firebaseObj = new Firebase("https://cs2340getwreckt.firebaseio.com");
    var auth = firebase.auth();

    //  On successful authentication, we get a success callback and
    //  on an unsuccessful authentication, we get an error callback.

    $scope.user = [];
    $scope.SignIn = function(event) {
        event.preventDefault();  // To prevent form refresh
        var email = $scope.user.email;
        var password = $scope.user.password;
        if (email && password) {
            auth.$signInWithEmailAndPassword(email, password).catch(function(error) {
              // Handle Errors here.
              console.log('got here');

              var errorCode = error.code;
              var errorMessage = error.message;
              console.log(errorMessage);

              // ...
            });
        }

        // loginObj.$authWithPassword('password', {
        //         email: username,
        //         password: password
        //     })
        //     .then(function(user) {
        //         // Success callback
        //         console.log('Authentication successful');
        //     }, function(error) {
        //         // Failure callback
        //         console.log('Authentication failure');
        //     });
    }

}]);
