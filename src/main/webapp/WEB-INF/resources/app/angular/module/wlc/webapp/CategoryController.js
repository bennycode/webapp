angular.module("wlc.webapp", ["wlc.common.NumberFilter"])
  .controller("CategoryController", function ($scope, $http) {
    $scope.vm = new wlc.webapp.CategoryViewModel($http);
  });
