/**
 * Created by marta on 28.07.17.
 */

'use strict';

angular.
module('Advertisement').
factory('Advertisement', ['$resource', '$http',
    function($resource, $http) {
        var Advertisement =  $resource('adv/:owner/:id', {
            owner: "@owner",
            id: "@id"
        }, {
            'update': {
                method: 'PATCH'
            },
            get:{
                method : 'GET',
                isArray: true
            },
            query:{
                method : 'GET',
                isArray: false
            }
        });

        Advertisement.save = function (fd) {

            return $http({
                url: "/adv",
                method: "POST",
                data: fd,
                // transformRequest: angular.identity,
                headers: {
                    'Content-Type': undefined
                },
                arrayKey: ''

            })
        }

        Advertisement.getByAuthor = function(author_id){
            return Advertisement.get({
                owner: "author",
                id: author_id
            })
        };

        Advertisement.getAll = function(){
            return Advertisement.get({
                owner: "all"
            })
        };

        Advertisement.getStatuses = function(){
            return Advertisement.get({
                owner: "statuses"
            })
        };

        Advertisement.getTypes = function(){
            return Advertisement.get({
                owner: "types"
            })
        };

        Advertisement.getDetail = function(id){
            return Advertisement.query({
                owner: "detail",
                id: id
            })
        };

        return Advertisement;
    }
]);