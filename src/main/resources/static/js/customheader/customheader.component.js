/**
 * Created by marta on 27.07.17.
 */
'use_strict';


angular.module('customheader').component('customheader', {
    templateUrl: '/js/customheader/customheader.template.html',
    controller:  ['User',function HeaderController(User) {
            var self = this;
            self.loggedUser = User.getCurrentUser();
            console.log(self.loggedUser);
        }]
});