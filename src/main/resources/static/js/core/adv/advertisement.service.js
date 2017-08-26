/**
 * Created by marta on 28.07.17.
 */

'use strict';

angular.
module('Advertisement').
factory('Advertisement', ['$resource', '$http',
    function($resource, $http) {
        var Advertisement =  $resource('adv/:author/:id', {
            author: "@author",
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
            },
            post:{
                method : 'POST',
                isArray: false
            }
        });

        Advertisement.save = function (fd) {

            return $http({
                url: "/adv",
                method: "POST",
                data: fd,
                headers: {
                    'Content-Type': undefined
                },
                arrayKey: ''

            })
        }

        Advertisement.getByAuthor = function(author_id){
            return Advertisement.get({
                author: "me",
                id: author_id
            })
        };

        Advertisement.getAll = function(){
            return Advertisement.get({
                author: "all"
            })
        };

        Advertisement.getStatuses = function(){
            return Advertisement.get({
                author: "statuses"
            })
        };

        Advertisement.getTypes = function(){
            return Advertisement.get({
                author: "types"
            })
        };

        Advertisement.getDetail = function(id){
            return Advertisement.query({
                author: "detail",
                id: id
            })
        };

        Advertisement.addToWatch = function (id) {
            return Advertisement.post({
                author: "watch",
                id: id
            })
        }

        Advertisement.removeWatch = function (id) {
            return Advertisement.post({
                author: "unwatch",
                id: id
            })
        }

        Advertisement.iWatchThis = function (id) {
            return Advertisement.query({
                author: "watched",
                id: id
            })
        }

        return Advertisement;
    }
]);