
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="model.Type" %>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="bootstrap-4.3.1-dist/css/bootstrap.min.css" >
        <link rel="stylesheet" href="css/indexStyle.css">
        <link rel="stylesheet" href="css/common.css">
        <link href="//maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">
        
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <script src="bootstrap-4.3.1-dist/js/bootstrap.min.js"></script>
        <script src="js/order.js" type="text/javascript"></script>
        <script src="js/search.js" type="text/javascript"></script>
        
        <!-- <script src="js/cart.js" type="text/javascript"></script> -->

        
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta charset="UTF-8">
        <title>PhoneCommerce</title>
    </head>
    
    <body>
    
<!--NAVBAR-->

    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
      <a class="navbar-brand" href="home?action=index"> PhoneCommerce </a>
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>

      <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">



          <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="#" id="prodottiDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
              Brands
            </a>
            <div class="dropdown-menu" aria-labelledby="prodottiDropdown">

              <c:forEach var="brand" items="${brands}">
              	<a id="${brand.id}" class="dropdown-item" href="home?action=productsView&brandName=${brand.name}&brandId=${brand.id}"> ${brand.name} </a>
              </c:forEach> 

            </div>
          </li>

          <li class="nav-item dropdown">
         
          	
          	<c:choose>
			    <c:when test="${user == null}">
			        <a class="nav-link dropdown-toggle" href="#" id="accountDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> Account </a>
			        <div class="dropdown-menu" aria-labelledby="accountDropdown">
			          <a class="dropdown-item" href="home?action=signin">Accedi</a>
			          <a class="dropdown-item" href="home?action=registration&fromCart=no">Registrati</a>
			        </div>        
			    </c:when>
			    <c:when test="${user.type eq Type.Customer}">
			        <a class="nav-link dropdown-toggle" href="#" id="accountDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> <c:out value="${user.name}" /> <c:out value="${user.surname}" /> </a>
			        <div class="dropdown-menu" aria-labelledby="accountDropdown">
			          <a class="dropdown-item" href="home?action=myAccount">Il mio account</a>
			          <a class="dropdown-item" href="home?action=myOrdersView">I miei ordini</a>
			          <a class="dropdown-item" href="account?action=logout">Esci</a>
			          
			        </div>          
			    </c:when>
			    <c:when test="${user.type eq Type.Organizer}">
			        <a class="nav-link dropdown-toggle" href="#" id="accountDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> <c:out value="${user.name}" /> <c:out value="${user.surname}" /> </a>
			        <div class="dropdown-menu" aria-labelledby="accountDropdown">
                        <a class="dropdown-item" href="home?action=addProduct">Aggiungi prodotto</a>
			            <a class="dropdown-item" href="home?action=modifydelete">Modifica o elimina prodotti</a>
                        <a class="dropdown-item" href="home?action=modifycarousel">Modifica Banner/Carosello</a>
			            <a class="dropdown-item" href="account?action=logout">Esci</a>
			        </div>          
			    </c:when>
			</c:choose>
            
            
          </li>

          <li class="nav-item">
            <a class="nav-link" href="#">Chi siamo?</a>
          </li>
          
          <li class="nav-item">
            <a class="nav-link" href="home?action=faq">FAQ</a>
          </li>
          
          <li class="nav-item">
            <a class="nav-link" href="home?action=cart">Carrello <span id="countCart" class="badge badge-danger">${cart.size}</span> </a>
            
          </li>
        </ul>

        <form class="form-inline my-2 my-lg-0" id="search">
          <input class="form-control mr-sm-2" id="uniqueID" type="search" placeholder="Cosa cerchi?" aria-label="Search">
          <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Cerca</button>
        </form>
      </div>
    </nav>

  <!-- FINE NAVBAR-->
  
  <div id="login-auto-close-alert" class="alert hidden" role="alert">
  <p id="login-alert-text"> </p>
  </div>
  
 	<!-- PAGINA CARICATA -->
	<jsp:include page="${page}" />
	
	
	
  <!-- Footer -->
	<%@ include file="content/footer.html"%>
   </body>
   
   

</html>