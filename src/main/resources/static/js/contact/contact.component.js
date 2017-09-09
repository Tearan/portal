/**
 * Created by marta on 14.08.17.
 */

angular.module("contact").component("contact", {
    templateUrl: '/js/contact/contact.template.html',
    controller:  [ 'Advertisement', '$route', function ContactController(Advertisement, $route) {
        var self = this;

         Advertisement.getDetail($route.current.params.id).$promise.then(function (result) {
             self.advertisement = result.advertisement;
             self.photos = result.files;
        });
    }],

})