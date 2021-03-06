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
        self.search.type = '';
        self.search.title = '';

        self.statuses = [];
        self.types = [];
        self.currentUser = User.getCurrentUser();
        self.categories = Advertisement.getCategories();
        self.types = Advertisement.getTypes();
        self.currentUser.$promise.then(function success(response) {

            Advertisement.getByAuthor(response.id).$promise.then(function (res) {
                self.listAdv = res;
            })
        });

        // Advertisement.getTypes().$promise.then(function (res) {
        //     console.log(res);
        //     self.types = _.toArray(res);
        // })


    }]
});