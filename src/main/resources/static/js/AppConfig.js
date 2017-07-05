/**
 * Created by marta on 02.07.17.
 */
'use.strict'

App.config(function ($routeProvider) {
    $routeProvider.when("/users/:id", {
        controller: 'UserController',
        templateUrl: '/templates/user/show.html'
    }).when("/users", {
        controller: 'UsersController',
        templateUrl: '/js/user/show.html'
    });

})