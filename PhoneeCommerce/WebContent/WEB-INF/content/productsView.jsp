<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>

<link rel="stylesheet" href="css/productsViewStyle.css">
<script src="js/cart.js" type="text/javascript"></script>

 
 	
 	
 <!--  RIGA NUOVI PRODOTTI  -->
 
   	
   	<div class="boxcontainer container mt-4">
   	<h2 class="text-center" style="margin-bottom:40px;"> ${pageTitle} </h1>
	    <div class="row justify-content-center">
	        <c:forEach var="brandProduct" items="${brandProducts}">
	        <div class="col-auto mb-3">
	            <div class="card" style="width: 20rem;">
		 			<a href="home?action=singleProductView&productName=${brandProduct.name}" >
					<img src="https://source.unsplash.com/category/nature/" class="card-img-top" alt="...">
					</a>
						<div class="card-body flex-fill ">
							<h5 class="card-title">${brandProduct.name} ${brandProduct.price}0 €</h5>
							<h6 class="card-subtitle mb-2 text-muted">${brandProduct.category.name}</h6> <!-- category --> 
						    <p class="card-text">${brandProduct.description}</p>
						   	<input class="btn btn-primary add-product-on-cart" type="button" value="Add to cart" data-name="${brandProduct.name}" /> 
						  	<a href="#" class="btn btn-primary">Preferiti</a>
						</div>
	        	 </div>
	        </div>
	        </c:forEach>
		</div>
	</div>
