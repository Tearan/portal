/**
 * Created by marta on 23.08.17.
 */
'use strict';

angular.module('adsuccess').component('adsuccess', {
    templateUrl: 'js/advertisement/success/success.template.html',
    bindings: {
        resolve: '<',
        close: '&',
        dismiss: '&'
    },
    controller: [ '$window',
        function AdSuccessController($window){
            var self = this;

            self.toAd = function () {
                $window.location.href = self.resolve.url;
                self.close({$value: "OK"});
            };

            self.stay = function () {
                self.close({$value: "OK"});

            };
        }
    ]
});