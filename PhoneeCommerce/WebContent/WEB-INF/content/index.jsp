<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 
 <script src="js/cart.js" type="text/javascript"></script>
 <script src="js/wishlist.js" type="text/javascript"></script>
 
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
  
 
 <div id="newbox" class="boxcontainer container">
 
 	<h2 class="text-center" >Scopri i nuovi prodotti</h2>
        
 
 <!--  RIGA NUOVI PRODOTTI  -->
  <div class="container mt-4">
	    <div class="row justify-content-center">
	        <c:forEach items="${newproducts}" var = "i" begin = "0" end = "5">
	        <div class="col-auto mb-3">
	            <div class="card" style="width: 20rem;">
		 			<a href="home?action=singleProductView&productName=${i.name}" >
					
					</a>
					<div class="image"  style=" background-image:url(https://source.unsplash.com/category/nature/);"> </div>
						<div class="card-body flex-fill ">
							<h5 class="card-title">${i.name} ${i.price}0 €</h5>
							<h6 class="card-subtitle mb-2 text-muted">${i.category.name}</h6> <!-- category --> 
						    <p class="card-text">${i.description}</p>
						   	<button type="button" class="btn btn-sm btn-primary add-product-on-cart" data-name="${i.name}"> carrello </button> 
				  			<button type="button" class="btn btn-sm btn-warning btn-add-product-on-wishlist" data-idproduct="${i.id}" data-idwishlist="${wishlist.id}">Preferiti </button>
						</div>
	        	 </div>
	        </div>
	        </c:forEach>
		</div>
	</div>

<!--FINE RIGA NUOVI PRODOTTI  -->
	
</div>