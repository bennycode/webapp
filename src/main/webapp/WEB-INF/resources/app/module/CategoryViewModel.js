function arrayize(data) {
  if (angular.isArray(data)) {
    return data;
  } else {
    return [data];
  }
}

function CategoryViewModel($http) {
  this.message = "Hello World";

  // Selection
  this.categories = [];
  this.selectedCategory = null;

  this.selectCategory = function (category) {
    this.selectedCategory = category;
  };

  // Sorting
  this.sortColumn = "id";
  this.sortDirection = false;

  this.setSortDirection = function (column) {
    if (this.sortColumn === column) {
      this.sortDirection = !this.sortDirection;
    } else {
      this.sortColumn = column;
      this.sortDirection = false;
    }
  };

  var self = this;

  $http.get('api/v1/categories')
    .then(function (result) {
      var categories = arrayize(result.data);
      categories.forEach(function (categoryPayload) {
        self.categories.push(new Category(categoryPayload));
      });
    })
    .catch(function (result) {
      console.log('Error', result);
    });
}
