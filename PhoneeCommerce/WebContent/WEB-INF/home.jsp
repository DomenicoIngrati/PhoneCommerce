
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="bootstrap-4.3.1-dist/css/bootstrap.min.css" >
        <link rel="stylesheet" href="css/indexStyle.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <script src="bootstrap-4.3.1-dist/js/bootstrap.min.js"></script>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta charset="UTF-8">
        <title>PhoneCommerce</title>
    </head>
    
    <body>
    
<!--NAVBAR-->

    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
      <a class="navbar-brand" href="#"> PhoneCommerce </a>
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>

      <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">



          <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="#" id="prodottiDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
              Brands
            </a>
            <div class="dropdown-menu" aria-labelledby="prodottiDropdown">
            
              <!-- <a class="dropdown-item" href="#">Apple</a>
              <a class="dropdown-item" href="#">Samsung</a>
              <a class="dropdown-item" href="#">Hawai</a>
              <a class="dropdown-item" href="#">Waiai</a> -->
              
              <c:forEach var="brand" items="${brands}">
              	<a class="dropdown-item" href="#">${brand.name}</a>
              </c:forEach> 
             
              
            </div>
          </li>

          <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="#" id="accountDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
              Account
            </a>
            <div class="dropdown-menu" aria-labelledby="accountDropdown">
              <a class="dropdown-item" href="#">Accedi</a>
              <a class="dropdown-item" href="#">Registrati</a>
            </div>
          </li>

          <li class="nav-item">
            <a class="nav-link" href="#">Chi siamo?</a>
          </li>

          <li class="nav-item">
            <a class="nav-link" href="FAQ.html">FAQ</a>
          </li>
        </ul>

        <form class="form-inline my-2 my-lg-0">
          <input class="form-control mr-sm-2" type="search" placeholder="Cosa cerchi?" aria-label="Search">
          <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Cerca</button>
        </form>
      </div>
    </nav>

  <!-- FINE NAVBAR-->
  
 	<!-- PAGINA CARICATA -->
	<jsp:include page="${page}" />
	
    

    </body>
</html>