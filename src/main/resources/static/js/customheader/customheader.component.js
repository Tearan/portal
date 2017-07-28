/**
 * Created by marta on 27.07.17.
 */
'use_strict';


angular.module('customheader').component('customheader', {
    templateUrl: '/js/customheader/customheader.template.html',
    controller:  ['$scope',function HeaderController($scope) {

        }],
    bindings: {
        ngModel: '=',
        options: '=',
        metricId: '@'
    }
});