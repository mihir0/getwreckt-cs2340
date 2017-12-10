'use strict';

angular.module('ratTrack.register', ['ngRoute', 'firebase'])

// Declared route
.config(['$routeProvider', function($routeProvider) {
    $routeProvider.when('/register', {
        templateUrl: 'register/register.html',
        controller: 'RegisterCtrl'
    });
}])

// Register controller
.controller('RegisterCtrl', ['$scope', '$firebaseAuth', function($scope, $firebaseAuth) {
    var firebaseObj = new Firebase("https://cs2340getwreckt.firebaseio.com");
    var auth = $firebaseAuth(firebaseObj);
    $scope.signUp = function() {
        if (!$scope.regForm.$invalid) {
            console.log('Valid form submission');
            var email = $scope.user.email;
            var password = $scope.user.password;
            if (email && password) {
                auth.$createUser(email, password)
                    .then(function() {
                        // do things if success
                        console.log('User creation success');
                    }, function(error) {
                        // do things if failure
                        console.log(error);
                    });
            }
        }
        // Sign up implementation would be here !!

    };
}]);
