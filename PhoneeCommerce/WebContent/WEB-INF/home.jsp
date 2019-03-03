
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
              
              <a class="dropdown-item" href="#">${gatta}</a>
              
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
 
 <!-- CAROSELLO -->
<div class="bd-example">
  <div id="carouselExampleCaptions" class="carousel slide" data-ride="carousel">
    <ol class="carousel-indicators">
      <li data-target="#carouselExampleCaptions" data-slide-to="0" class="active"></li>
      <li data-target="#carouselExampleCaptions" data-slide-to="1"></li>
      <li data-target="#carouselExampleCaptions" data-slide-to="2"></li>
    </ol>
    <div class="carousel-inner">
      <div class="carousel-item active"   data-interval= "10000" style="background-image: url(https://source.unsplash.com/category/nature/);">
        <!-- <img src="..." class="d-block w-100" alt="..."> -->
        <div class="carousel-caption d-none d-md-block">
          <h5>Offerta n1</h5>
          <p>Desing, potenza e cancioffoli allo stato puro.</p>
        </div>
      </div>
      <div class="carousel-item" data-interval= "10000" style="background-image: url(https://source.unsplash.com/category/food/);">
        <!-- <img src="..." class="d-block w-100" alt="..."> -->
        <div class="carousel-caption d-none d-md-block">
          <h5>Offerta n2</h5>
          <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>
        </div>
      </div>
      <div class="carousel-item" style="background-image: url(https://source.unsplash.com/category/car/);">
        <!-- <img src="..." class="d-block w-100" alt="..."> -->
        <div class="carousel-caption d-none d-md-block">
          <h5>Offerta n3</h5>
          <p>Praesent commodo cursus magna, vel scelerisque nisl consectetur.</p>
        </div>
      </div>
    </div>
    <a class="carousel-control-prev" href="#carouselExampleCaptions" role="button" data-slide="prev">
      <span class="carousel-control-prev-icon" aria-hidden="true"></span>
      <span class="sr-only">Previous</span>
    </a>
    <a class="carousel-control-next" href="#carouselExampleCaptions" role="button" data-slide="next">
      <span class="carousel-control-next-icon" aria-hidden="true"></span>
      <span class="sr-only">Next</span>
    </a>
  </div>
</div>

 <!-- FINE CAROSELLO -->
  
 
 <div class="container">
 
 	<h2 class="newProductString">Scopri i nuovi prodotti</h2>
        
 
 <!--  RIGA NUOVI PRODOTTI  -->
  <div class="card-deck">
      	<div class="card" style="width: 20rem;">
			  <img src="https://source.unsplash.com/category/nature/" class="card-img-top" alt="...">
			  <div class="card-body">
			    <h5 class="card-title">iPhone XS</h5>
			    <h6 class="card-subtitle mb-2 text-muted">Apple</h6> <!-- category --> 
			    <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
			    <a href="#" class="btn btn-primary">Scopri di più ></a>
			  </div>
      </div>



	       	<div class="card" style="width: 20rem;">
				  <img src="https://source.unsplash.com/category/nature/" class="card-img-top" alt="...">
				  <div class="card-body">
				    <h5 class="card-title">Huawei P15</h5>
				    <h6 class="card-subtitle mb-2 text-muted">Huawei</h6> <!-- category --> 
				    <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
				    <a href="#" class="btn btn-primary">Scopri di più ></a>
				  </div>
      </div>


	       	<div class="card" style="width: 20rem;">
			  <img src="https://source.unsplash.com/category/nature/" class="card-img-top" alt="...">
			  <div class="card-body">
			    <h5 class="card-title">Mi 9</h5>
			    <h6 class="card-subtitle mb-2 text-muted">Xiaomi</h6> <!-- category --> 
			    <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
			    <a href="#" class="btn btn-primary">Scopri di più ></a>
			  </div>
      </div>
	</div>

<!--FINE RIGA NUOVI PRODOTTI  -->
	
</div>
    

    </body>
</html>