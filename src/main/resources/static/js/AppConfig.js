/**
 * Created by marta on 02.07.17.
 */
'use.strict'

angular.module('myApp')
    .config(["$locationProvider","$routeProvider",function ($locationProvider,$routeProvider) {
    $routeProvider
        .when("/users", {
        controller: 'UsersController',
        templateUrl: './js/users/show.html'})
        .otherwise("/");

    $locationProvider.html5Mode({
        enabled: true,
        requireBase: false
    });
}]);