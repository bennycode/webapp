angular.module("wlc.webapp.service", ["ngResource"])
  .constant("baseUrl", "/api/v1/categories/:slug")
  .factory("CategoryService", function ($resource, baseUrl) {
    var CategoryResource = $resource(baseUrl);

    var createCategory = function (category) {
      category.$save();
      console.log('Saved category.');
    };

    var deleteCategory = function (category) {
      CategoryResource.delete({slug: category.slug}, function () {
        console.log('Deleted category.');
      });
    };

    return {
      createCategory: createCategory,
      deleteCategory: deleteCategory
    };

  });
