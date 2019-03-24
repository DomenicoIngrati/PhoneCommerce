<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<link rel="stylesheet" href="css/productsViewStyle.css">

<div class="container">
 
 	<h1 class="newProductString"> ${pageTitle} </h1>
 	
 <!--  RIGA NUOVI PRODOTTI  -->
 
	  <div class="row mt-3">
 		<c:forEach var="brandProduct" items="${brandProducts}">
 
             <div class="col-sm-4 d-flex">
             	 <!-- SBAGLIATO METTERE PASSARE PAGE TITLE MA LO FACCIO PER PROVA  -->
             	 <div class="productsCards">

	 			 <div class="card" style="width: 20rem;">
	 			 	  <a href="home?action=singleProductView&productName=${brandProduct.name}" >
					  <img src="https://source.unsplash.com/category/nature/" class="card-img-top" alt="...">
					  <div class="card-body flex-fill ">
					    <h5 class="card-title">${brandProduct.name} ${brandProduct.price}0 $</h5>
					  </a>
					    <h6 class="card-subtitle mb-2 text-muted">${brandProduct.category.name}</h6> <!-- category --> 
					    <p class="card-text">${brandProduct.description}</p>
					   	<a href="#" class="btn btn-primary">Aggiungi al carrello</a>
					  	<a href="#" class="btn btn-primary">Preferiti</a>
					  </div>
					  	        	 
					  
	        	 </div>

	        	 </div>
	        	 
 			</div>
   		</c:forEach>
   	   </div>

	
</div>
