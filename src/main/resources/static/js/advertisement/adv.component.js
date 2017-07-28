/**
 * Created by marta on 05.07.17.
 */

'use strict';

angular.module('adv').component('adv', {
    templateUrl: '/js/advertisement/adv.template.html',
    controller:  ['$scope',function AdvController($scope) {
        var self = this;

        self.categories = ['MOTO', 'PET', 'MAN', 'WOMAN', 'CLOTHES', 'JEWELLERY', 'HOME'];

    }],
    bindings: {
        ngModel: '=',
        options: '=',
        metricId: '@'
    }
});