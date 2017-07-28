/**
 * Created by marta on 02.07.17.
 */
'use.strict'

App
    .config(['$locationProvider','$routeProvider',
        function ($locationProvider,$routeProvider) {
            $routeProvider
                .when('/user', {
                    templateUrl: '/js/users/show.html',
                    controller: 'UsersController'})
                // .when('/adv', {
                //     templateUrl: '/js/advertisement/create.html',
                //     controller: 'AdvController'})
                .when('/adv', {
                template: '<adv/>'})
                .when('/', {
                    templateUrl: '/js/dashboard/dashboard.template.html',
                    controller: 'DashboardController'})
                .otherwise("/");

            // $locationProvider.html5Mode({
            //     enabled: true
            // });
}]);
