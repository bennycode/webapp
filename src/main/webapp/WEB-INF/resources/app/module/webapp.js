var app = angular.module("webapp", ["wlc.common.filter"]);

app.controller("CategoryController", function ($scope, $http) {
  $scope.vm = new CategoryViewModel($http);
});
