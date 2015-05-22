<%@page contentType="text/html" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html ng-app="app">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>We Love Coding</title>
  <link rel="stylesheet" type="text/css" href="resources/css/main.css"/>

</head>
<body ng-controller="SomeController">
<div id="wrapper">


  <p>Enter Name: <input type="text" ng-model="myname"></p>

  <p>Hello {{myname}}!!</p>

  <hr class=""/>
  <div ui-view></div>
  <hr class=""/>
</div>

<script src="resources/js/angular.js/angular.js"></script>
<script src="resources/js/angular.js/angular-resource.js"></script>
<script src="resources/js/angular-ui-router/angular-ui-router.min.js"></script>
<script src="resources/js/angular-ui-bootstrap/ui-bootstrap.js"></script>
<script src="resources/js/app/app.js"></script>

</body>
</html>
