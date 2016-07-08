'use strict';

/**
 * @ngdoc function
 * @name exampleApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the exampleApp
 */
angular.module('exampleApp')
  .controller('MainCtrl', function ($scope) {
    this.awesomeThings = [
      'HTML5 Boilerplate',
      'AngularJS',
      'Karma'
    ];
	
	var ll = new google.maps.LatLng(13.0810, 80.2740);
    $scope.mapOptions = {
        center: ll,
        zoom: 15,
        mapTypeId: google.maps.MapTypeId.ROADMAP
    };

    //Markers should be added after map is loaded
    $scope.onMapIdle = function() {
        if ($scope.myMarkers === undefined){    
            var marker = new google.maps.Marker({
                map: $scope.myMap,
                position: ll
            });
            $scope.myMarkers = [marker, ];
        }
    };

    $scope.markerClicked = function(m) {
        window.alert("clicked");
    };

	
  });
