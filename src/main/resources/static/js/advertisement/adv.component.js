/**
 * Created by marta on 05.07.17.
 */

'use strict';

angular.module('adv').component('adv', {
    templateUrl: '/js/advertisement/adv.template.html',
    controller: ['$scope', 'Advertisement', '$window', '$uibModal',
        function AdvController($scope, Advertisement, $window, $uibModal) {
            var self = this;
            self.advertisement = {};
            self.advertisement.type = "";

            self.advertisement.categories = [];
            self.pictures = [];
            self.categories = ['MOTO', 'PET', 'MAN', 'WOMAN', 'CLOTHES', 'JEWELLERY', 'HOME'];
            self.globalError = false;
            self.errorMessage = "";

            self.save = function () {
                var parameters = new FormData();
                for (var i = 0; i < self.pictures.length; i++) {
                    parameters.append("pictures", self.pictures[i]);
                }

                parameters.append("advertisement",
                    new Blob([
                        JSON.stringify(self.advertisement)
                    ], {
                        type: "application/json"
                    }));

                Advertisement.save(parameters).then(function (response) {
                    var url = "http://" + $window.location.host + "/#!/adv/detail/" + response.data.id;

                    var modal = $uibModal.open({
                        animation: true,
                        ariaLabelledBy: 'modal-title',
                        ariaDescribedBy: 'modal-body',
                        size: 'sm',
                        component: 'adsuccess',
                        controllerAs: '$ctrl',
                        resolve: {
                            url: function () {
                                return url;
                            }
                        },

                    });
                    self.advertisement = {};
                    self.pictures = [];

                }, function (res) {
                    self.globalError = true;
                    self.errorMessage = res;
                })
            };

            self.addRemoveCategory = function (category) {
                var index = self.advertisement.categories.indexOf(category);

                if(!$scope.advertisement.categories.$touched)
                    $scope.advertisement.categories.$touched=true;

                if (index > -1) {
                    //todo but why???
                    if (self.advertisement.categories.length == 1) {
                        self.advertisement.categories = [];
                    } else {
                        self.advertisement.categories.splice(index, 1);
                    }
                } else {
                    self.advertisement.categories.push(category);
                }
            };

            self.someCategorySelected = function (object) {
                return Object.keys(object).some(function (key) {
                    return object[key];
                });
            }
        }],
    bindings: {
        ngModel: '=',
        options: '=',
        metricId: '@'
    }
});