/**
 * Created by marta on 02.07.17.
 */
'use.strict'

App
    .config(['$locationProvider','$routeProvider',
        function ($locationProvider,$routeProvider) {
            $routeProvider
                .when('/user', {
                    controller: 'UsersController',
                    templateUrl: '/js/users/show.html'})
                // .when('/adv', {
                //     templateUrl: '/js/advertisement/create.html',
                //     controller: 'AdvController'})
                .when('/adv', {
                    template: '<adv/>'})
                .when('/adv/detail/:id', {
                    template: '<advdetail/>'})
                .when('/', {
                    template: '<dashboard/>'})
                .otherwise("/");

            // $locationProvider.html5Mode({
            //     enabled: true
            // });
}]);
