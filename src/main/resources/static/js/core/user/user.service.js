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
            },
            get:{
                isArray: true
            },
            post:{
                method: 'POST'
            }

        });

        User.getCurrentUser = function(){
            if(!self.currentUser) {
                self.currentUser = User.query({
                    condition: "current"
                });
            }
            return self.currentUser;
        };


        User.getAllUsers = function () {
            return User.get({
                condition: "all"
            });
        }

        User.addFriend = function (id) {
            return User.post({'id': id},{
                condition: "add"
            });
        }



        return User;
    }
]);