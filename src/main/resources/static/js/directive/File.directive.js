/**
 * Created by marta on 04.08.17.
 */

App.directive('fileUpload', ['$parse', function ($parse) {
    return {
        restrict: 'EA',
        require: 'ngModel',
        link: function (scope, element, attr, ngModel) {
            var model = $parse(attr.fileUpload);
            console.log(model);
            element.bind("change", function (changeEvent) {

                var size = _.reduce(element[0].files, function (memo, file) {
                    return memo + file.size;
                }, 0);

                if (size > 10485760) {
                    console.log("Bigger");

                    ngModel.$setValidity('filesToBig', false);
                }else{
                    console.log("Not Bigger");

                    ngModel.$setValidity('filesToBig', true);
                    ngModel.$setViewValue(element[0].files)
                }
            })
        }
    }
    
}])