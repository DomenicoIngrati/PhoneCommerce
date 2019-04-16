<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="css/myAccountStyle.css">

<div class="boxcontainer container mt-5">
	<div class="myAccountBox" align="center">
	
		<img src="img/utente.png" width="150" height="150" />
		<h1 align="center"> <c:out value="${user.name}" /> <c:out value="${user.surname}" /> </h1>
		
	</div>
	
	
	<div class="row account-button-box">
		
		<div class="col-sm">
		<a href="home?action=myOrdersView">
			<div class="account-button">
				<div class="row">
					<div class="col-auto"> <!-- DIV IMMAGINE -->
						<img src="img/Box.png" class="img-fluid">
					</div>
					<div class="col"> <!-- DIV TESTO -->
						<h4 class="card-title">I miei ordini </h4>
		            	<p>Visualizza i tuoi articoli</p>
					</div>  			
				</div>
			</div>
		</a>
		</div>
	
		
		<div class="col-sm">
		<a href="home?action=list">
			<div class="account-button">
				<div class="row">
					<div class="col-auto"> <!-- DIV IMMAGINE -->
						<img src="img/lockMyAccount.png" class="img-fluid">
					</div>
					<div class="col"> <!-- DIV TESTO -->
						<h4 class="card-title">Wishlist</h4>
		                 <p>  Crea e visualizza le tue liste dei desideri </p>
					</div>  			
				</div>
			</div>
		</a>
		</div>
		
		<div class="col-sm">
		<a href="home?action=address">
				<div class="account-button">
				<div class="row">
					<div class="col-auto"> <!-- DIV IMMAGINE -->
						<img src="img/addressMapMyAccount.png" class="img-fluid">
					</div>
					<div class="col"> <!-- DIV TESTO -->
						<h4 class="card-title">Indirizzi</h4>
		                    <p>Visualizza e modifica indirizzi per gli ordini </p>
					</div>  			
				</div>
			</div>
		</a>
		</div>
		
	</div>
</div>