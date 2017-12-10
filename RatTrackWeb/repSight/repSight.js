'use strict';

angular.module('ratTrack.repSight', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
    $routeProvider.when('/repSight', {
        templateUrl: 'repSight/repSight.html',
        controller: 'RepSightCtrl'
    });
}])

.controller('RepSightCtrl', ['$scope', function($scope) {

}]);
