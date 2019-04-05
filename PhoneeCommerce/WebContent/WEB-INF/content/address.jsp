<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>

<link rel="stylesheet" href="css/address.css">
<link rel="stylesheet" href="css/productsViewStyle.css">
<script src="js/account.js" type="text/javascript"></script>

 
 	
 	
 <!--  INDIDIZZI  -->
 
   	
<div class="boxcontainer container mt-5">
<h2>Visualizza, modifica e aggiungi indirizzi </h2>
<hr>

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
<!-- 	<a href="#"> -->
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
