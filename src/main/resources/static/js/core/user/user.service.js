/**
 * Created by marta on 28.07.17.
 */

'use strict';

angular.
module('core.user').
factory('User', ['$resource',
    function($resource) {
        var User =  $resource('user/:condition', {
            condition: "@condition"
        }, {
            query:{
                isArray: false
            }
        });

        User.getCurrentUser = function(){
            return User.query({
                condition: "current"
            })
        };

        return User;
    }
]);