/**
 * Created by marta on 14.08.17.
 */
'use strict';

angular.module("mail").component("mail", {
    templateUrl: '/js/mail/mail.template.html',
    controller: ['$http',function MailController($http) {
        var self = this;
        self.mail = {};

        self.sandEmail = function () {
            console.log(self.mail);
            // $http({
            //     method: "POST",
            //     url: "/sand",
            //     data: JSON.stringify(self.mail)
            // })
            $http.post("/sand", self.mail).then(
                function (res) {
                    console.log(res)},

                function (res) {
                    console.log(res);

            })
        }

    }]

})