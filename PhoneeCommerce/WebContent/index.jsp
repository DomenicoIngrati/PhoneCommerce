<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
      <a class="navbar-brand" href="#"> SitoDiPiloWaiai </a>
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
              <a class="dropdown-item" href="#">Apple</a>
              <a class="dropdown-item" href="#">Samsung</a>
              <a class="dropdown-item" href="#">Hawai</a>
              <a class="dropdown-item" href="#">Waiai</a>
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
            <a class="nav-link" href="#">FAQ</a>
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
      <div class="carousel-item active"  style="background-image: url(https://source.unsplash.com/category/nature/);">
        <img src="..." class="d-block w-100" alt="...">
        <div class="carousel-caption d-none d-md-block">
          <h5>IPHONE XS</h5>
          <p>Che cazzo campi a fare se non ti compri un Iphone XS nel nostro sito sfigato di merda comprati il nostro telefono</p>
        </div>
      </div>
      <div class="carousel-item"  style="background-image: url(https://source.unsplash.com/category/nature/);">
        <img src="..." class="d-block w-100" alt="...">
        <div class="carousel-caption d-none d-md-block">
          <h5>XIAOMI 8</h5>
          <p>Stupida bitch che minchia di chinese di merda sei se non ti compri lo Xiamo 8 prodotto direttamente dai bimbi schiavi egiziani </p>
        </div>
      </div>
      <div class="carousel-item"  style="background-image: url(https://source.unsplash.com/category/nature/);">
        <img src="..." class="d-block w-100" alt="...">
        <div class="carousel-caption d-none d-md-block">
          <h5>SAMSUNG S9</h5>
          <p>Si quanto cazzo sei British senza il Samsung S9? Siamo qui per farti diventare la persona più sfigata al mondo che compra ancora i Samsung.</p>
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
        
 
    <div class="row big-padding">

      <div class="col-md-3 col-sm-6">
        <h3> Title </h3>
        <p> Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum. </p>
      </div>



      <div class="col-md-3 col-sm-6">
      <h3> Title </h3>
        <p> Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum. </p>
      </div>


      <div class="col-md-3 col-sm-6">
      <h3> Title </h3>
        <p> Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum. </p>
      </div>


      <div class="col-md-3 col-sm-6">
      <h3> Title </h3>
        <p> Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum. </p>
      </div>

    </div>
    
</div>
  
  
  

    </body>
</html>