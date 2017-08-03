/**
 * Created by marta on 05.07.17.
 */

'use strict';

angular.module('adv').component('adv', {
    templateUrl: '/js/advertisement/adv.template.html',
    controller:  ['$scope','Advertisement',function AdvController($scope, Advertisement) {
        var self = this;
        self.advertisement = {};

        self.categories = ['MOTO', 'PET', 'MAN', 'WOMAN', 'CLOTHES', 'JEWELLERY', 'HOME'];

        self.save = function () {
            var newAdvertisement = new Advertisement(self.advertisement);
            newAdvertisement.$save(function (response) {
                console.log("nowe ogłoszenie"+response.id);

            })

        }
    }],
    bindings: {
        ngModel: '=',
        options: '=',
        metricId: '@'
    }
});