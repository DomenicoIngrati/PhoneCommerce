<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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