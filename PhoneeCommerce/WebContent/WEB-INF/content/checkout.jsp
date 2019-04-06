<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../../../favicon.ico">

    <title>Checkout example for Bootstrap</title>

    <link rel="canonical" href="https://getbootstrap.com/docs/4.0/examples/checkout/">

    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" href="bootstrap-4.3.1-dist/css/bootstrap.min.css" >
	<script src="bootstrap-4.3.1-dist/js/bootstrap.min.js"></script>
	<script src="js/account.js" type="text/javascript"></script>

    <!-- Custom styles for this templatekj -->
    <link href="css/checkOutStyle.css" rel="stylesheet">
    <link rel="stylesheet" href="css/productsViewStyle.css">
    <link rel="stylesheet" href="css/address.css">


    <div class="container">
      <div class="py-5 text-center">
        <img class="d-block mx-auto mb-4" src="https://getbootstrap.com/docs/4.0/assets/brand/bootstrap-solid.svg" alt="" width="72" height="72">
        <h2>Checkout form</h2>
        <p class="lead">Below is an example form built entirely with Bootstrap's form controls. Each required form group has a validation state that can be triggered by attempting to submit the form without completing it.</p>
      </div>

      <div class="row">
      
        <div class="col-md-4 order-md-2 mb-4">
          <h4 class="d-flex justify-content-between align-items-center mb-3">
            <span class="text-muted">Il tuo carrello</span>
          </h4>
          <ul class="list-group mb-3">
          
          	<c:forEach var="productOnCart" items="${productsOnCart}">
	            <li class="list-group-item d-flex justify-content-between lh-condensed">
	              <div>
	                <h6 class="my-0">${productOnCart.product.name} x ${productOnCart.quantity}</h6>
	              </div>
	              <span class="text-muted">$${productOnCart.product.price}0</span>
	            </li>
            </c:forEach> 
            
            <li class="list-group-item d-flex justify-content-between">
              <strong><span>Totale (USD)</span></strong>
              <strong><c:out value=" $${cart.total}0" /></strong>
            </li>
            
            <li class="list-group-item d-flex justify-content-between">
             	<button class="btn btn-primary  btn-block" >Paga</button>
            </li>
           </ul>
        </div>
        
        
        <div class="col-md-8 order-md-1">
          <button class="btn btn-primary  btn-block" data-toggle="modal" data-target="#general-modal">Scegli un indirizzo</button>
        </div>
 	  </div>
 	</div>
 	
 	
 	<!-- INIZIO MODAL GENERALEEEEE-->
 	<div class="modal fade" id="general-modal" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
		  <div class="modal-dialog modal-lg">
		    <div class="modal-content">
		      
		        <!-- INIZIOO CONTAINER ADDRESS -->
		        
		        
		        <div class="boxcontainer container mt-5">
					
					<div class="row">
						
						<div class="col-sm-4">
							<button id="add-address" type="button" class="btn btn-block" data-name="add" data-toggle="modal" data-target="#add-address-modal">
									<div class="row">
										<div class="col"> <!-- DIV TESTO -->
											<div class="d-flex justify-content-center"> <img src="img/plus.png" height="50" width="50"> </div>
											
											<div class="d-flex justify-content-center"> <h4 class="card-title">Aggiungi indirizzo</h4> </div>
										</div>  			
									</div>
							</button>
						</div>
					
						<c:forEach var="address" items="${allAddress}">
							<div id="address-${address.id}" class="col-sm-4">
								<div class="address-box">
									<div class="row">
										<div class="col-auto"> <!-- DIV IMMAGINE -->
											<p> <strong>${address.namelastname}</strong> </p> 
											<p> ${address.address}</p>
											<p> ${address.city}, ${address.province}, ${address.zipcode}</p>
											<p> Italia </p>
											<p> Numero di telefono: ${address.tel} </p>
										</div>  
										<div class="col-auto btn-box-address">
											<button type="button" class="btn btn-sm btn-address btn-address-modify" data-name="modify" data-idaddress="${address.id}" data-namelastname="${address.namelastname}" data-address="${address.address}" data-city="${address.city}" data-province="${address.province}" data-zipcode="${address.zipcode}" data-tel="${address.tel}" data-dismiss="modal" data-toggle="modal" data-target="#add-address-modal" >modifica</button>
							        		<button type="submit" class="btn btn-sm btn-address btn-address-delete" data-id="${address.id}">elimina</button>
										</div>
													
									</div>
								</div>
						<!-- 	</a> -->
							</div>
						</c:forEach>
						
						
						
					</div>
				</div>
		        
		        
		        <!-- FINE CONTAINER ADDRESS -->
		      
		    </div>
		  </div>
	</div>
	
	<!-- FINE MODAL GENERALEEEEE-->
	
	
	
	<!-- INIZIO MODAL AGGIUNGI/MODIFICA INDIRIZZO  -->
	
	
		<div class="modal fade" id="add-address-modal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
		  <div class="modal-dialog" role="document">
		    <div class="modal-content">
		      <div class="modal-header">
		        <h5 class="modal-title" id="exampleModalLabel">Aggiungi indirizzo</h5>
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
		          <span aria-hidden="true">&times;</span>
		        </button>
		      </div>
		      <div class="modal-body">
		        <form id="address-form">
		          
		          <div class="form-group">
		          	
		          	<label for="namelastname">Nome</label>
		            <input type="text" name="namelastname"  class="form-control" id="namelastname" placeholder="Inserisci nome e cognome" required>
		          </div>
		          <div class="form-group">
			          <label for="address">Indirizzo</label>
			          <input id="address" type="text" name="address" class="form-control"  placeholder="Inserisci indirizzo" required>
		          </div>
		          <div class="form-group">
			          <label for="city">Citta'</label>
			          <input id="city" type="text" name="city" class="form-control"  placeholder="Inserisci citta"required >
		          </div>
									
				<div class="form-group">
			          <label for="province">Provincia</label>
			          <input id="province" type="text" name="province" class="form-control"  placeholder="Inserisci provincia" required>
		          </div>
									
				<div class="form-group">
			          <label for="zipcode">Codice postale</label>
			          <input id="zipcode" type="text" name="zipcode" class="form-control"  placeholder="Inserisci codice postale" required>
		          </div>
									
				<div class="form-group">
			          <label for="tel">Telefono</label>
			          <input id="tel" type="text" name="tel" class="form-control"  placeholder="Inserisci numero di telefono" required>
		        </div>
		        
		        <div class="modal-footer">
			        <button type="button" class="btn btn-secondary" data-dismiss="modal">Chiudi</button>
			        <button id="btn-add-address" type="submit" class="btn btn-primary">Salva indirizzo</button>
		      	</div>
		      	
		        </form>
		        
		      </div>
		      
		    </div>
		  </div>
		</div>

	
	<!-- FINE MODAL AGGIUNGI/MODIFICA INDIRIZZO  -->
	
	