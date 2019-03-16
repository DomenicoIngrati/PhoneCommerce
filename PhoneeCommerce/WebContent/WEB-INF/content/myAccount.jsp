<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="css/myAccountStyle.css">

<div class="myAccountBox" align="center">

	<img src="img/utente.png" width="150" height="150" />
	<h1 align="center"> <c:out value="${user.name}" /> <c:out value="${user.surname}" /> </h1>
	
</div>





<div class="container" id="cardContainer" align="center">
  <div class="row">
    <div class="col-sm">
		
		<div class="card">
	        <div class="row no-gutters">
	            <div class="col-auto">
	                <img src="img/Box.png" class="img-fluid" alt="">
	            </div>
	            <div class="col">
	                <div class="card-block px-2 " >
	                    <h4 class="card-title">I miei ordini </h4>
	                    <p class="card-text">Traccia, restituisci o acquista nuovamente degli articoli</p>
	                </div>
	            </div>
	        </div>
  		</div>

    </div>
    
    <div class="col-sm">
		
		<div class="card">
	        <div class="row no-gutters">
	            <div class="col-auto">
	                <img src="img/lockMyAccount.png" class="img-fluid" alt="">
	            </div>
	            <div class="col">
	                <div class="card-block px-2" >
	                    <h4 class="card-title">Impostazioni di sicurezza</h4>
	                    <p class="card-text">  Modifica il login, il nome e il numero di cellulare </p>
	                </div>
	            </div>
	        </div>
  		</div>
    </div>
    <div class="col-sm">

		<div class="card">
			<div class="row no-gutters">
	            <div class="col-auto">
	                <img src="img/addressMapMyAccount.png" class="img-fluid" alt="">
	            </div>
	            <div class="col">
	                <div class="card-block px-2" >
	                    <h4 class="card-title">Indirizzi</h4>
	                    <p class="card-text">Modifica indirizzi e preferenze di consegna per gli ordini e i regali</p>
	                </div>
	            </div>
	        </div>
	     </div>
		
		
    </div>
  </div>
</div>