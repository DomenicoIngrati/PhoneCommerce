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
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script src="js/account.js" type="text/javascript"></script>

    <!-- Custom styles for this template -->
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
          <button class="btn btn-primary  btn-block" data-toggle="modal" data-target=".bd-example-modal-lg">Scegli un indirizzo</button>
          
          
          
          <div class="modal fade bd-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
  				<div class="modal-dialog modal-lg">
    				<div class="modal-content">
    				
    					<div class="boxcontainer container mt-5">
						<h2>Visualizza, modifica e aggiungi indirizzi </h2>
						<hr>
      					<div class="row">
	
							<div class="col-sm-4">
									<button id="add-address" type="button" class="btn btn-block" data-toggle="modal" data-target="#add-address-modal">
										
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
								</div>
							</c:forEach>
	
	
	
							</div>
						</div>
    				</div>
  				</div>
		  </div>
		  
		  
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
          
          
          <br>
          <br>
          <br>
          <br>
          <br>
          <br>
          <br>
          <br>
          <br>
          <br>
          
          
          
          <form class="needs-validation" novalidate>
            <div class="row">
              <div class="col-md-6 mb-3">
                <label for="firstName">First name</label>
                <input type="text" class="form-control" id="firstName" placeholder="" value="" required>
                <div class="invalid-feedback">
                  Valid first name is required.
                </div>
              </div>
              <div class="col-md-6 mb-3">
                <label for="lastName">Last name</label>
                <input type="text" class="form-control" id="lastName" placeholder="" value="" required>
                <div class="invalid-feedback">
                  Valid last name is required.
                </div>
              </div>
            </div>

            <div class="mb-3">
              <label for="username">Username</label>
              <div class="input-group">
                <div class="input-group-prepend">
                  <span class="input-group-text">@</span>
                </div>
                <input type="text" class="form-control" id="username" placeholder="Username" required>
                <div class="invalid-feedback" style="width: 100%;">
                  Your username is required.
                </div>
              </div>
            </div>

            <div class="mb-3">
              <label for="email">Email <span class="text-muted">(Optional)</span></label>
              <input type="email" class="form-control" id="email" placeholder="you@example.com">
              <div class="invalid-feedback">
                Please enter a valid email address for shipping updates.
              </div>
            </div>

            <div class="mb-3">
              <label for="address">Address</label>
              <input type="text" class="form-control" id="address" placeholder="1234 Main St" required>
              <div class="invalid-feedback">
                Please enter your shipping address.
              </div>
            </div>

            <div class="mb-3">
              <label for="address2">Address 2 <span class="text-muted">(Optional)</span></label>
              <input type="text" class="form-control" id="address2" placeholder="Apartment or suite">
            </div>

            <div class="row">
              <div class="col-md-5 mb-3">
                <label for="country">Country</label>
                <select class="custom-select d-block w-100" id="country" required>
                  <option value="">Choose...</option>
                  <option>United States</option>
                </select>
                <div class="invalid-feedback">
                  Please select a valid country.
                </div>
              </div>
              <div class="col-md-4 mb-3">
                <label for="state">State</label>
                <select class="custom-select d-block w-100" id="state" required>
                  <option value="">Choose...</option>
                  <option>California</option>
                </select>
                <div class="invalid-feedback">
                  Please provide a valid state.
                </div>
              </div>
              <div class="col-md-3 mb-3">
                <label for="zip">Zip</label>
                <input type="text" class="form-control" id="zip" placeholder="" required>
                <div class="invalid-feedback">
                  Zip code required.
                </div>
              </div>
            </div>
            <hr class="mb-4">
            <div class="custom-control custom-checkbox">
              <input type="checkbox" class="custom-control-input" id="same-address">
              <label class="custom-control-label" for="same-address">Shipping address is the same as my billing address</label>
            </div>
            <div class="custom-control custom-checkbox">
              <input type="checkbox" class="custom-control-input" id="save-info">
              <label class="custom-control-label" for="save-info">Save this information for next time</label>
            </div>
            <hr class="mb-4">

            <h4 class="mb-3">Payment</h4>

            <div class="d-block my-3">
              <div class="custom-control custom-radio">
                <input id="credit" name="paymentMethod" type="radio" class="custom-control-input" checked required>
                <label class="custom-control-label" for="credit">Credit card</label>
              </div>
              <div class="custom-control custom-radio">
                <input id="debit" name="paymentMethod" type="radio" class="custom-control-input" required>
                <label class="custom-control-label" for="debit">Debit card</label>
              </div>
              <div class="custom-control custom-radio">
                <input id="paypal" name="paymentMethod" type="radio" class="custom-control-input" required>
                <label class="custom-control-label" for="paypal">Paypal</label>
              </div>
            </div>
            <div class="row">
              <div class="col-md-6 mb-3">
                <label for="cc-name">Name on card</label>
                <input type="text" class="form-control" id="cc-name" placeholder="" required>
                <small class="text-muted">Full name as displayed on card</small>
                <div class="invalid-feedback">
                  Name on card is required
                </div>
              </div>
              <div class="col-md-6 mb-3">
                <label for="cc-number">Credit card number</label>
                <input type="text" class="form-control" id="cc-number" placeholder="" required>
                <div class="invalid-feedback">
                  Credit card number is required
                </div>
              </div>
            </div>
            <div class="row">
              <div class="col-md-3 mb-3">
                <label for="cc-expiration">Expiration</label>
                <input type="text" class="form-control" id="cc-expiration" placeholder="" required>
                <div class="invalid-feedback">
                  Expiration date required
                </div>
              </div>
              <div class="col-md-3 mb-3">
                <label for="cc-expiration">CVV</label>
                <input type="text" class="form-control" id="cc-cvv" placeholder="" required>
                <div class="invalid-feedback">
                  Security code required
                </div>
              </div>
            </div>
            <hr class="mb-4">
            <button class="btn btn-primary btn-lg btn-block" type="submit">Continue to checkout</button>
          </form>
        </div>
      </div>

      <footer class="my-5 pt-5 text-muted text-center text-small">
        <p class="mb-1">&copy; 2017-2018 Company Name</p>
        <ul class="list-inline">
          <li class="list-inline-item"><a href="#">Privacy</a></li>
          <li class="list-inline-item"><a href="#">Terms</a></li>
          <li class="list-inline-item"><a href="#">Support</a></li>
        </ul>
      </footer>
    </div>

    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery-slim.min.js"><\/script>')</script>
    <script src="../../assets/js/vendor/popper.min.js"></script>
    <script src="../../dist/js/bootstrap.min.js"></script>
    <script src="../../assets/js/vendor/holder.min.js"></script>
    <script>
      // Example starter JavaScript for disabling form submissions if there are invalid fields
      (function() {
        'use strict';

        window.addEventListener('load', function() {
          // Fetch all the forms we want to apply custom Bootstrap validation styles to
          var forms = document.getElementsByClassName('needs-validation');

          // Loop over them and prevent submission
          var validation = Array.prototype.filter.call(forms, function(form) {
            form.addEventListener('submit', function(event) {
              if (form.checkValidity() === false) {
                event.preventDefault();
                event.stopPropagation();
              }
              form.classList.add('was-validated');
            }, false);
          });
        }, false);
      })();
    </script>
