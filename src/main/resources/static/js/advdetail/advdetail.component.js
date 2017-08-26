/**
 * Created by marta on 14.08.17.
 */

angular.module("advdetail").component("advdetail", {
    templateUrl: '/js/advdetail/advdetail.template.html',
    controller:  [ 'Advertisement', '$route', function AdvDetailController(Advertisement, $route) {
        var self = this;
        self.iWatchThis = false;


        self.addToWatch = function(id){
            Advertisement.addToWatch(id).$promise.then(function () {
                iWatchThis(id);
            });
        };

        self.removeWatch = function(id){
            Advertisement.removeWatch(id).$promise.then(function () {
                iWatchThis(id);
            });

        };

        function iWatchThis(id) {
            Advertisement.iWatchThis(id).$promise.then(function (res) {
                self.iWatchThis =res.result;
            });
        }


        this.$onInit = function () {
            Advertisement.getDetail($route.current.params.id).$promise.then(function (result) {
                self.advertisement = result.advertisement;
                self.photos = result.files;
                iWatchThis(self.advertisement.id);
            })
        }

    }]

})