<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<script src="js/map.js" type="text/javascript"></script>

  <meta name="viewport" content="initial-scale=1.0, width=device-width" />

    <script src="http://js.api.here.com/v3/3.0/mapsjs-core.js"
      type="text/javascript" charset="utf-8"></script>
    <script src="http://js.api.here.com/v3/3.0/mapsjs-service.js" 
      type="text/javascript" charset="utf-8"></script>
    <script src="http://js.api.here.com/v3/3.0/mapsjs-ui.js" 
      type="text/javascript" charset="utf-8"></script>
    <script src="http://js.api.here.com/v3/3.0/mapsjs-mapevents.js" 
     type="text/javascript" charset="utf-8"></script>
    <link rel="stylesheet" type="text/css" 
      href="http://js.api.here.com/v3/3.0/mapsjs-ui.css" />


<div class="container boxcontainer mt-5">
<h2> Trova uno store</h2>
  <br>
  <div style="width: 640px; height: 480px" id="mapContainer"></div>
</div>
