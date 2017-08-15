/**
 * Created by marta on 05.07.17.
 */

'use strict';

angular.module('adv').component('adv', {
    templateUrl: '/js/advertisement/adv.template.html',
    controller:  ['$scope','Advertisement', '$window',function AdvController($scope, Advertisement, $window) {
        var self = this;
        self.advertisement = {};
        self.advertisement.categories = [];
        self.pictures = [];
        self.categories = ['MOTO', 'PET', 'MAN', 'WOMAN', 'CLOTHES', 'JEWELLERY', 'HOME'];

        self.save = function () {
            var parameters  = new FormData();
            for (var i=0; i < self.pictures.length; i++){
                //todo oAle dlaczego to tak działa?
                parameters.append("pictures", self.pictures[i]);

            }

            parameters.append("advertisement",
                new Blob([
                    JSON.stringify(self.advertisement)
                ], {
                type: "application/json"
            }));

            Advertisement.save(parameters).then(function (response) {
                var url = "http://" + $window.location.host + "/#!/adv/detail/"+response.data.id;
                console.log(url);
                console.log(response);
                $window.location.href = url;
            },function (res) {
                console.log(res);
            })
        };

        self.addRemoveCategory = function (category) {
            var index = self.advertisement.categories.indexOf(category);
            if(index !== -1) {
                self.advertisement.categories.splice(index,1);
            }else{
                self.advertisement.categories.push(category);
            }
        }

        self.write = function () {
            console.log(self.pictures);
        }
    }],
    bindings: {
        ngModel: '=',
        options: '=',
        metricId: '@'
    }
});