/**
 * Created by marta on 02.07.17.
 */
'use.strict'

App.config(['$locationProvider','$routeProvider',
        function ($locationProvider,$routeProvider) {
            $routeProvider
                .when('/adv', {
                    template: '<adv/>'})
                .when('/adv/detail/:id', {
                    template: '<advdetail/>'})
                .when('/', {
                    template: '<dashboard/>'})
                .when('/users', {
                    template: '<users/>'})
                .otherwise("/");


}]);
