/**
 * Created by marta on 05.07.17.
 */
'use strict';

angular.module('dashboard').component( 'dashboard',{
    templateUrl: '/js/dashboard/dashboard.template.html',
    controller: [ 'Advertisement', 'User','$http' ,function DashboardController(Advertisement, User, $http) {
        var self = this;
        self.listAdv = {};
        self.search = {};
        self.statuses = {};
        self.types = {};
        self.currentUser = User.getCurrentUser();

        self.currentUser.$promise.then(function success(response) {

            Advertisement.getByAuthor(response.id).$promise.then(function (res) {
                self.listAdv = res;
                console.log(self.listAdv[0]);
            })
        });

        Advertisement.getStatuses().$promise.then(function (res) {
            self.statuses = res;
        });
        Advertisement.getTypes().$promise.then(function (res) {
            console.log(res)
            self.types = res;
        })

    }]
});