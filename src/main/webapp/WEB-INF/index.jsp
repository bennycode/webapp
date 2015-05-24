<!DOCTYPE html>
<html data-ng-app="webapp">
  <head>
    <meta charset="UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0"/>
    <link rel="stylesheet" href="resources/libs/bootstrap/css/bootstrap.css"/>
    <title>We Love Coding</title>
    <!-- Dependencies -->
    <script src="resources/libs/es5-shim/es5-shim.js"></script>
    <script src="resources/libs/angular/angular.js"></script>
  </head>
  <body data-ng-controller="CategoryController">
    <h1>${title}</h1>
    <div>
      <div data-ng-show="vm.message">
        {{::vm.message}}
      </div>

      <table
        class="table table-striped"
        data-ng-show="vm.categories.length > 0"
        >
        <tr>
          <th><a data-ng-click="vm.setSortDirection('id')">ID</a></th>
          <th><a data-ng-click="vm.setSortDirection('name')">Name</a></th>
          <th><a data-ng-click="vm.setSortDirection('color')">Color</a></th>
          <th>Action</th>
        </tr>

        <tr
          data-ng-repeat="category in vm.categories| orderBy:vm.sortColumn:vm.sortDirection"
          data-ng-class="{ info: category.id === vm.selectedCategory.id }"
          >
          <td>{{ category.id|toDecimal}}</td>
          <td>{{ category.name}}</td>
          <td>{{ category.color}}</td>
          <td><a data-ng-click="vm.selectCategory(category)">Select</a></td>
        </tr>
      </table>
    </div>

    <!-- App -->
    <script src="resources/app/wlc/common/filter/commonFilter.js"></script>
    <script src="resources/app/module/Category.js"></script>
    <script src="resources/app/module/CategoryViewModel.js"></script>
    <script src="resources/app/module/webapp.js"></script>
  </body>
</html>
