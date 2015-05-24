var app = angular.module("webapp", []);

app.controller("CategoryController", function($scope, $http){
  $scope.vm = new CategoryViewModel($http);
});
