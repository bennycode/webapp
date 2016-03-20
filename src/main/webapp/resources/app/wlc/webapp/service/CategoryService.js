angular.module("wlc.webapp.service", ["ngResource"])
  .constant("baseUrl", "/api/v1/categories/:slug")
  .factory("CategoryService", function ($resource, baseUrl) {
    var endpoint = $resource(baseUrl);
    var categoryResources = [];

    var fetchAll = function () {
      endpoint.query(function (data) {
        categoryResources = data;
        console.log('Fetched all category resources.', categoryResources);
      });
    };

    var saveCategory = function (categoryResource) {
      console.log('Saving category', categoryResource);
      categoryResource.$save();
    };

    return {
      fetchAll: fetchAll,
      getAll: function () {
        return categoryResources;
      },
      save: saveCategory
    };

  });
