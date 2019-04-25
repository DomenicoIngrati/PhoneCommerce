<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
    
    <link rel="canonical" href="https://getbootstrap.com/docs/4.0/examples/checkout/">

    <!-- Bootstrap core CSS -->
	<script src="js/account.js" type="text/javascript"></script>
	<script src="js/checkout.js" type="text/javascript"></script>
	

    <!-- Custom styles for this template -->
    <link href="css/checkOutStyle.css" rel="stylesheet">
<!--     <link rel="stylesheet" href="css/productsViewStyle.css"> -->
    <link rel="stylesheet" href="css/address.css">

    <div class="container boxcontainer mt-5">
	<h2>Checkout</h2>
	<hr>

      <div class="row">
      
        <div class="col-md-4 order-md-2 mb-4">
          <h4 class="d-flex justify-content-between align-items-center mb-3">
            <span class="text-muted">Il tuo carrello</span>
          </h4>
          <ul class="list-group mb-3">
          
          	<c:forEach var="productOnCart" items="${cart.products}">
	            <li class="list-group-item d-flex justify-content-between lh-condensed">
	              <div>
	                <h6 class="my-0">${productOnCart.product.name} x ${productOnCart.quantity}</h6>
	              </div>
	              <span class="text-muted">${productOnCart.quantity * productOnCart.product.price}0</span>
	            </li>
            </c:forEach> 
            
            <li class="list-group-item d-flex justify-content-between">
              <strong><span>Totale (USD)</span></strong>
              <strong><c:out value=" $${cart.total}0" /></strong>
            </li>
            
           <c:choose>
	           	<c:when test="${addressChosen != null}">
		            <li class="list-group-item d-flex justify-content-between">
		             	<button class="btn btn-primary  btn-block" id="new-order"> Paga</button>
		            </li>
	            </c:when>
	            
	            <c:when test="${addressChosen == null}">
		            <li class="list-group-item d-flex justify-content-between">
		             	<button class="btn btn-primary  btn-block" disabled> Paga</button>
		            </li>
	            </c:when>
           </c:choose>
            
           </ul>
        </div>
        
        
        <div class="col-md-8 order-md-1">
          <button class="btn btn-primary  btn-block" data-toggle="modal" data-target="#general-modal">Scegli un indirizzo</button>
        	
          <hr class="mr-4">
        
          <c:if test="${addressChosen != null}">
          <h4><strong>Riepilogo indirizzo</strong></h4>
          <div class="col-auto"> <!-- DIV IMMAGINE -->
				<p> <strong>Nome e cognome: </strong><c:out value="${addressChosen.namelastname}" />  </p> 
				<p> <strong>Indirizzo: </strong><c:out value="${addressChosen.address}" /></p>
				<p> <strong>Città: </strong><c:out value="${addressChosen.city}" />,<c:out value="${addressChosen.province}" /> ,<c:out value="${addressChosen.zipcode}" /> </p>
				<p> <strong>Paese: </strong>Italia </p>
				<p> <strong>Numero di telefono: </strong><c:out value="${addressChosen.tel}" /></p>
		  </div>  
		  
		  <hr class="mr-4">
		  <h4 class="mb-3"><strong>Scegli un metodo di pagamento</strong></h4>
		  
		  <div class="d-block my-3">
              <div class="custom-control custom-radio">
                <input id="credit" name="paymentMethod" type="radio" class="custom-control-input" checked="" required="">
                <label class="custom-control-label" for="credit">Carta di credito</label>
              </div>
              <div class="custom-control custom-radio">
                <input id="debit" name="paymentMethod" type="radio" class="custom-control-input" required="">
                <label class="custom-control-label" for="debit">Carta di debito</label>
              </div>
              <div class="custom-control custom-radio">
                <input id="paypal" name="paymentMethod" type="radio" class="custom-control-input" required="">
                <label class="custom-control-label" for="paypal">PayPal</label>
              </div>
          </div>
          
          <div class="row">
              <div class="col-md-6 mb-3">
                <label for="cc-name">Nome instestatario</label>
                <input type="text" class="form-control" id="cc-name" placeholder="" required="">
                <small class="text-muted">Specifica l'intero nome scritto sulla carta</small>
                <div class="invalid-feedback">
                  Name on card is required
                </div>
              </div>
              <div class="col-md-6 mb-3">
                <label for="cc-number">Numero carta</label>
                <input type="text" class="form-control" id="cc-number" placeholder="" required="">
                <div class="invalid-feedback">
                  Credit card number is required
                </div>
              </div>
          </div>
          
          <div class="row">
              <div class="col-md-3 mb-3">
                <label for="cc-expiration">Scadenza</label>
                <input type="text" class="form-control" id="cc-expiration" placeholder="" required="">
                <div class="invalid-feedback">
                  Expiration date required
                </div>
              </div>
              <div class="col-md-3 mb-3">
                <label for="cc-cvv">CVV</label>
                <input type="text" class="form-control" id="cc-cvv" placeholder="" required="">
                <div class="invalid-feedback">
                  Security code required
                </div>
              </div>
          </div>
          
          
          <hr class="mr-4">
		  <h4 class="mb-3"><strong>Scegli una modalità di spedizione</strong></h4>
		  
		  <div class="d-block my-3">
              <div class="custom-control custom-radio">
                <input id="normal-delivery" name="deliveryMethod" type="radio" class="custom-control-input" checked="" required="">
                <label class="custom-control-label" for="normal-delivery">Normale <small>(dai 3 ai 5 giorni lavorativi)</small></label>
              </div>
              <div class="custom-control custom-radio">
                <input id="express-delivery" name="deliveryMethod" type="radio" class="custom-control-input" required="">
                <label class="custom-control-label" for="express-delivery">Express <small>(2 giorni lavorativi)</small></label>
              </div>
          </div>
		  
		  
		  </c:if>
		  
        </div>
 	  </div>
 	</div>
 	
 	
 	<!-- INIZIO MODAL GENERALEEEEE-->
 	<div class="modal fade" id="general-modal" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
		  <div class="modal-dialog modal-lg">
		    <div class="modal-content">
			    <div class="modal-header">
			        <h5 class="modal-title" id="exampleModalLabel">Seleziona indirizzo</h5>
			        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
			          <span aria-hidden="true">&times;</span>
			        </button>
			    </div>
		      
		        <!-- INIZIOO CONTAINER ADDRESS -->
		        
		        
		        <div class="boxcontainer container mt-0">
					
					<div class="row">
						
						<div class="col-sm-4">
							<button id="add-address" type="button" class="btn btn-block" style="min-height: 220px;" data-name="add" data-toggle="modal" data-target="#add-address-modal">
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
										<div class="clickable col-auto" data-id="${address.id}"> <!-- DIV IMMAGINE -->
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
		  <div class="modal-dialog modal-dialog-scrollable" role="document">
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
		          	<label for="namelastname">Nome </label>
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
	
	
