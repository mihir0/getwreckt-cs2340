
angular.module("myApp", [
    'ngRoute', 'myApp.home'
]).
config(['$routeProvider', function($routeProvider) {
    //Routes will be here



    //set default view of our app to home
    $routeProvider.otherwise({
        redirectTo: '/home'
    });
}])