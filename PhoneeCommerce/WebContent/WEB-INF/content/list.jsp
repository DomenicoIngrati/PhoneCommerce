<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>

<link rel="stylesheet" href="css/address.css">
<link rel="stylesheet" href="css/productsViewStyle.css">
<script src="js/account.js" type="text/javascript"></script>

 
 	
 	
 <!--  INDIDIZZI  -->
 
   	
<div class="boxcontainer container mt-5">
<h2>Lista dei desideri </h2>
<hr>

<div class="row">
	

<c:forEach var="i" items="${wishlist.products}">
	<div class="col-sm-4 mb-3" id="card-${ i.id }">
           <div class="card" style="width: 20rem;">
 			<a href="home?action=singleProductView&productName=${i.name}" >
			</a>
			<div class="image"  style=" background-image:url(https://source.unsplash.com/category/nature/);"> </div>
				<div class="card-body flex-fill ">
					<h5 class="card-title">${i.name} ${i.price}0 €</h5>
					<h6 class="card-subtitle mb-2 text-muted">${i.category.name}</h6> <!-- category --> 
				    <p class="card-text">${i.description}</p>
				   	<button type="button" class="btn btn-sm btn-primary add-product-on-cart" data-name="${i.name}"> Aggiungi al carrello </button> 
				  	<button type="button" class="btn btn-sm btn-danger btn-rmv-product" data-idproduct="${i.id}">Rimuovi dai preferiti </button>
				</div>
       	 </div>
       </div>
</c:forEach>
	
	
	
</div>
</div>
