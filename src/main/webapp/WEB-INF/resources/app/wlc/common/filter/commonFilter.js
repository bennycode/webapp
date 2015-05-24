/* global angular */

angular.module("wlc.common.filter", [])
  .filter('toDecimal', function () {
    return function (input) {
      if (input < 10) {
        input = '0' + input;
      }
      return input;
    };
  });