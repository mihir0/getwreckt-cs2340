'use strict';

angular.module('ratTrack', [
    'ngRoute',
    'ratTrack.home', //home module
    'ratTrack.register', //register module
    'ratTrack.welcome',
    'ratTrack.repSight'
]).
config(['$routeProvider', function($routeProvider) {
    //Routes will be here



    //set default view of our app to home
    $routeProvider.otherwise({
        redirectTo: '/home'
    });
}]);
