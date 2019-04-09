<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>

<link rel="stylesheet" href="css/address.css">
<link rel="stylesheet" href="css/productsViewStyle.css">

<script src="js/account.js" type="text/javascript"></script>
 <script src="js/cart.js" type="text/javascript"></script>
 <script src="js/wishlist.js" type="text/javascript"></script>
 <!--  WISHLIST  -->
 
   	
<div class="boxcontainer container mt-5">
	<div class="row">
		<h2 class="title">Lista dei desideri </h2>
		
		<c:choose>
			    <c:when test="${fn:length(wishlist.products) eq 0}">
			        <button type="button" class="btn btn-info ml-auto btn-add-all-cart" disabled>Aggiungi tutto al carrello</button>       
			    </c:when>
			    
			    <c:when test="${fn:length(wishlist.products) gt 0}">
			        <button type="button" class="btn btn-info ml-auto btn-add-all-cart">Aggiungi tutto al carrello </button>         
			    </c:when>
		</c:choose>
		
		
	</div>
<hr>

<div class="row">
	

<c:forEach var="i" items="${wishlist.products}">
	<div class="col-auto mb-3" id="card-${ i.id }">
           <div class="card" style="width: 20rem;">
 			<a href="home?action=singleProductView&productName=${i.name}" >
			</a>
			<div class="image"  style=" background-image:url(https://source.unsplash.com/category/nature/);"> </div>
				<div class="card-body flex-fill ">
					<h5 class="card-title">${i.name} ${i.price}0 €</h5>
					<h6 class="card-subtitle mb-2 text-muted">${i.category.name}</h6> <!-- category --> 
				    <p class="card-text">${i.description}</p>
				    <div class="button-footer">
					   	<button type="button" class="btn btn-sm btn-primary add-product-on-cart" data-id="${i.id}">Carrello</button> 
					  	<button type="button" class="btn btn-sm btn-danger btn-rmv-product" data-idproduct="${i.id}" data-idwishlist="${wishlist.id}">Rimuovi dai preferiti </button>
					</div>
				</div>
       	 </div>
       </div>
</c:forEach>
	
	
	
</div>
</div>
