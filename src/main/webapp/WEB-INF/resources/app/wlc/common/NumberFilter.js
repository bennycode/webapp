angular.module("wlc.common.NumberFilter", [])
  .filter('toDecimal', function () {
    return function (input) {
      if (input < 10) {
        input = '0' + input;
      }
      return input;
    };
  });
  