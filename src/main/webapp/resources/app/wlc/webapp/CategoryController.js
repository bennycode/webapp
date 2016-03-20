angular.module("wlc.webapp", ["wlc.common.NumberFilter", "wlc.webapp.service"])
  .controller("CategoryController", function ($scope, CategoryService) {
    $scope.service = CategoryService;
    $scope.vm = new wlc.webapp.CategoryViewModel();
    $scope.service.fetchAll();
  });
