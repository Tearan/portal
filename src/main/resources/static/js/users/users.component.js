/**
 * Created by marta on 05.07.17.
 */

'use strict';

angular.module("users").component("users", {
    templateUrl: '/js/users/users.template.html',
    controller: ['User',
        function UsersController(User) {
            var self = this;
            self.search = {};
            self.users = User.getAllUsers();
            self.usersLength = Object.keys(self.users).length + 1;
            self.itemsPerPage = 2;
            self.currentPage = 1;
            self.maxSize = 4;

            console.log(self.usersLength);
        }
    ]

});