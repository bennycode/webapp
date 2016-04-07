var wlc = window.wlc || {};
wlc.webapp = wlc.webapp || {};
wlc.webapp.CategoryViewModel = function () {
  this.message = "Hello World";

  // Selection
  this.categories = [];
  this.selectedCategory = undefined;

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
};
