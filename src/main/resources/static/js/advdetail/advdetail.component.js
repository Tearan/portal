/**
 * Created by marta on 14.08.17.
 */

angular.module("advdetail").component("advdetail", {
    templateUrl: '/js/advdetail/advdetail.template.html',
    controller:  [ 'Advertisement', '$route', function AdvDetailController(Advertisement, $route) {
        var self = this;

         Advertisement.getDetail($route.current.params.id).$promise.then(function (result) {
             self.advertisement = result.advertisement;
             self.photos = result.files;
        });
    }],

})