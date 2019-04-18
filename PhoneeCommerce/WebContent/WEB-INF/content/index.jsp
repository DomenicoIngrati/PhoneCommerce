<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 
 <script src="js/cart.js" type="text/javascript"></script>
 <script src="js/wishlist.js" type="text/javascript"></script>
<link rel="stylesheet" href="css/common.css">
 
 <!-- CAROSELLO -->
<div class="bd-example">
    <div id="carouselExampleCaptions" class="carousel slide" data-ride="carousel">
        <ol class="carousel-indicators">
            <c:forEach var="i" begin = "0" end = "${banners.size() -1}">
                <c:if test="${i == 0}">
                    <li data-target="#carouselExampleCaptions" data-slide-to="${i}" class="active"></li>

                </c:if>
                <c:if test="${i > 0}">
                    <li data-target="#carouselExampleCaptions" data-slide-to="${i}"></li>
                </c:if>
            </c:forEach>
        </ol>
    <div class="carousel-inner">
        <c:forEach var="i" begin = "0" end = "${banners.size() -1}">
            <c:if test="${i == 0}">
                <div class="carousel-item active" data-interval= "10000" style="background-image: url('${banners[i].imageString}');">
                    <!-- <img src="..." class="d-block w-100" alt="..."> -->
                    <div class="carousel-caption d-none d-md-block">
                        <h5 class="text-shadow">${banners[i].title}</h5>
                        <p class="text-shadow">${banners[i].description}</p>
                    </div>
                </div>
            </c:if>
            <c:if test="${i > 0}">
                <div class="carousel-item" data-interval= "10000" style="background-image: url('${banners[i].imageString}');">
                    <!-- <img src="..." class="d-block w-100" alt="..."> -->
                    <div class="carousel-caption d-none d-md-block">
                        <h5 class="text-shadow">${banners[i].title}</h5>
                        <p class="text-shadow">${banners[i].description}</p>
                    </div>
                </div>
            </c:if>

        </c:forEach>

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
		 			<a href="home?action=singleProductView&productId=${i.id}" >
                        <div style=" padding:10px;">
                            <div class="img-preview" style=" background-image: url('${i.imageString}');"> </div>
                        </div>
                    </a>
					<%--<img src="${i.imageString}">--%>
						<div class="card-body flex-fill ">
							<h5 class="card-title">${i.name} ${i.price}0 €</h5>
							<h6 class="card-subtitle mb-2 text-muted">${i.category.name}</h6> <!-- category --> 
						    <p class="card-text">${i.description}</p>
						   	<button type="button" class="btn btn-sm btn-primary add-product-on-cart" data-id="${i.id}"> <i class="fa fa-shopping-cart"></i> </button> 
				  			<button type="button" class="btn btn-sm btn-warning btn-add-product-on-wishlist" data-idproduct="${i.id}" data-idwishlist="${wishlist.id}"> <i class="fa fa-heart-o"></i> Aggiungi ai preferiti</button>
						</div>
	        	 </div>
	        </div>
	        </c:forEach>
		</div>
	</div>

<!--FINE RIGA NUOVI PRODOTTI  -->
	
</div>