<%@page contentType="text/html" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html data-ng-app="webapp">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>We Love Coding</title>
  <link rel="stylesheet" href="resources/css/main.css"/>
  <link rel="stylesheet" href="resources/libs/bootstrap/bootstrap.css"/>
  <!-- Dependencies -->
  <script src="resources/libs/angular/angular.js"></script>
</head>
<body>
<div data-ng-app="webapp">
  <div data-ng-controller="CategoryController">
    <div data-ng-show="vm.message">
      {{ vm.message }}
    </div>

    <table data-ng-show="vm.categories.length > 0" class="table table-striped">
      <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Color</th>
      </tr>

      <tr data-ng-repeat="category in vm.categories">
        <td>{{ category.id }}</td>
        <td>{{ category.name }}</td>
        <td>{{ category.color }}</td>
      </tr>
    </table>
  </div>
</div>
<!-- App -->
<script src="resources/app/module/Category.js"></script>
<script src="resources/app/module/CategoryViewModel.js"></script>
<script src="resources/app/module/webapp.js"></script>
</body>
</html>
