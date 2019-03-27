<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<link rel="stylesheet" href="css/admin.css">
<script src="js/administrator.js" type="text/javascript"></script>

 
 	
 <!--  RIGA NUOVI PRODOTTI  -->
 

<div id="managebox" class="mx-md-5 my-md-5" >
	<h1> Modifica o elimina prodotti </h1>
	<hr>
	<div class="listProduct">

		<c:forEach var="product" items="${allProducts}">
			
			<div class="card mb-3 cardDelete" id="${product.id}">
				<div class="row no-gutters">
					<div class="col-md-2 image"  style=" background-image:url(https://source.unsplash.com/category/nature/);">
		    		</div>
		    		<div class="col-md-10">
		    			<div class="card-body">
		    	
							<div class="row ">

								<div class="col-sm-3 ">
								<h4>${product.name}</h4>
								</div>
								
								<div class="col-sm-3">
								<h4>${product.category.name}</h4>
								</div>
								
								<div class="col-sm-3">
								<h4>${product.price}0 €</h4>
								</div>
								
								<div class="col-sm-3">
									<button  type="button" data-id="${product.id}" class="btn btn-warning modify">Modifica</button>
									<button  type="button" data-id="${product.id}" class="btn btn-danger delete">Elimina</button>
								</div>

			      			</div>
		      
		      			</div>
		    		</div>
		  		</div>
			</div>
			
		
		</c:forEach>
	
	
	
	</div>
	
    
</div>
