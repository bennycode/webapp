var webapp = angular.module("webapp", []);

webapp.controller("CategoryController", function($scope, $http){
  $scope.vm = new CategoryViewModel($http);
});
