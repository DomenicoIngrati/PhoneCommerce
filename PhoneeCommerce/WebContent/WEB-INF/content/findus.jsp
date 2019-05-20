<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<style>
      #map {
        width: 100%;
        height: 400px;
        background-color: grey;
      }
    </style>


<div class="container boxcontainer mt-5">
    <h3>My Google Maps Demo</h3>
    <br>
     <div id="map"></div>
</div>
      <script>
            // Initialize and add the map
            function initMap() {
            // The location of Uluru
            var uluru = {lat: 39.3108, lng: 16.2508};
            // The map, centered at Uluru
            var map = new google.maps.Map(
            document.getElementById('map'), {zoom: 4, center: uluru});
            // The marker, positioned at Uluru
            var marker = new google.maps.Marker({position: uluru, map: map});
}
    </script>
    <script async defer
    src="https://maps.googleapis.com/maps/api/js?key=AIzaSyD4s1N--JAUBowgkjt2G6vsWV8nnA027f0&callback=initMap">
    </script>