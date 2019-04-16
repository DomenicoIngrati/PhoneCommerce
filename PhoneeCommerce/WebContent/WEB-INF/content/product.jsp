<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="css/productStyle.css">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.1.1/css/all.css" integrity="sha384-O8whS3fhG2OnA5Kas0Y9l3cfpmYjapjI0E4theH4iuMD+pLhbf6JI0jIMfYcK3yZ" crossorigin="anonymous">
<title>Insert title here</title>
<script src="js/product.js" type="text/javascript"></script>
<script src="js/cart.js" type="text/javascript"></script>
</head>
<body>

<!--Main layout-->
  <main class="boxcontainer container mt-5">
    <div class="container dark-grey-text mt-5">

      <!--Grid row-->
      <div class="row wow fadeIn">

        <!--Grid column-->
        <div class="col-md-6 mb-4">

          <img src="https://mdbootstrap.com/img/Photos/Horizontal/E-commerce/Products/14.jpg" class="img-fluid" alt="">

        </div>
        <!--Grid column-->

        <!--Grid column-->
        <div class="col-md-6 mb-4">

          <!--Content-->
          <div class="p-4">

            <div class="mb-3">
                <h2 >${selectedProduct.name}</h2>
                <h4>Apple</h4>
<!--               <a href="">
                <span class="badge red mr-1">Bestseller</span>
              </a> -->
            </div>

            <p class="lead">
<!--               <span class="mr-1">
                <del>$200</del>
              </span> -->
              <span>$${selectedProduct.price}0</span>
            </p>

            <p class="lead font-weight-bold">Description</p>

            <p>${selectedProduct.description}</p>

<!-- 			<div class="input-group mb-3">
			  <input type="text" class="form-control" placeholder="Quantità" aria-label="Quantità" aria-describedby="button-addon2">
			  <div class="input-group-append">
			    <button class="btn btn-outline-secondary" type="button" id="button-addon2">Add to cart</button>
			  </div>
			</div> -->
			
                            <div class="input-group mb-3">
                                <div class="input-group-prepend">
                                    <button class="btn btn-dark " id="minus-btn"><i class="fa fa-minus"></i></button>
                                </div>
                                <input type="number" id="qty_input" class="form-control form-control-mb" value="1" min="1">
                                <div class="input-group-prepend">
                                    <button class="btn btn-dark " id="plus-btn"><i class="fa fa-plus"></i></button>
                                </div>
                                
                               <div class="input-group-append" id="add-product">
                               	<!-- Da modificare la quantità passata alla servlet. Va preso il valore contenuto in value qui sopra  -->
			    					<input class="btn btn-outline-secondary add-product-on-cart" type="button" value="Add to cart" data-id="${selectedProduct.id}" /> 
			 				   </div>	
                            </div>

          </div>
          <!--Content-->

        </div>
        <!--Grid column-->

      </div>
      <!--Grid row-->

      <hr>

      <!--Grid row-->
      <div class="row d-flex justify-content-center wow fadeIn">

        <!--Grid column-->
        <div class="col-md-6 text-center">

          <h4 class="my-4 h4">Additional information</h4>

          <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Natus suscipit modi sapiente illo soluta odit
            voluptates,
            quibusdam officia. Neque quibusdam quas a quis porro? Molestias illo neque eum in laborum.</p>

        </div>
        <!--Grid column-->

      </div>
      <!--Grid row-->

      <!--Grid row-->
      <div class="row wow fadeIn">

        <!--Grid column-->
        <div class="col-lg-4 col-md-12 mb-4">

          <img src="https://mdbootstrap.com/img/Photos/Horizontal/E-commerce/Products/11.jpg" class="img-fluid" alt="">

        </div>
        <!--Grid column-->

        <!--Grid column-->
        <div class="col-lg-4 col-md-6 mb-4">

          <img src="https://mdbootstrap.com/img/Photos/Horizontal/E-commerce/Products/12.jpg" class="img-fluid" alt="">

        </div>
        <!--Grid column-->

        <!--Grid column-->
        <div class="col-lg-4 col-md-6 mb-4">

          <img src="https://mdbootstrap.com/img/Photos/Horizontal/E-commerce/Products/13.jpg" class="img-fluid" alt="">

        </div>
        <!--Grid column-->

      </div>
      <!--Grid row-->

    </div>

		<c:choose>
			    <c:when test="${user != null and productAlreadyBought==true}">
			        <hr>
    				<br></br>
						<h4>
						Ciao ${user.name}, hai acquistato questo prodotto in precedenza! Vuoi lasciare una recensione per aiutarci a migliorare il servizio?
						</h4>
					<button class="btn btn-outline-secondary" data-toggle="modal" data-target="#add-review-modal" > Lascia una recensione </button>     
			    </c:when>
		</c:choose>
    
    <hr>
    <br></br>
    
    <!-- ////////////////////////////////////////////////////////////////////////// --> 	
    
    <c:choose>
			    <c:when test="${fn:length(productReviews) eq 0}">
						<h3> Mi dispiace, nessun cliente ha lasciato una recensione relativa a questo prodotto! </h3>
			    </c:when>
			    
			    <c:when test="${fn:length(productReviews) gt 0}">
			    
			    		<div class="container" >

							<div class="row justify-content-center">
								<div class="col-sm-3">
									<div class="rating-block">
										<h4>Feedback</h4>
										<h2 class="bold padding-bottom-7">${feedbackAverage}<small>/ 5</small></h2>
													      <c:forEach var = "i" begin = "1" end = "${feedbackAverageInt}">
													         		<button type="button" class="btn btn-warning btn-sm" aria-label="Left Align">
																	  <i class="fa fa-star" aria-hidden="true"> </i>
																	</button>
													      </c:forEach>
													      
													      <c:forEach var = "i" begin = "${feedbackAverageInt+1}" end = "5">
      																<button type="button" class="btn btn-default btn-grey btn-sm" width="12" height="12" aria-label="Left Align">
													  					<i class="fa fa-star" aria-hidden="true"> </i>
																	</button>
													      </c:forEach>
									</div>
								</div>
								<div class="col-sm-3">
									<h4>Rating breakdown</h4>
									<div class="pull-left">
										<div class="pull-left" style="width:35px; line-height:1;">
											<div style="height:9px; margin:5px 0;">5 <span class="glyphicon glyphicon-star"></span></div>
										</div>
										<div class="pull-left" style="width:180px;">
											<div class="progress" style="height:9px; margin:8px 0;">
											  <div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="5" aria-valuemin="0" aria-valuemax="5" style="width: 1000%">
												<span class="sr-only">80% Complete (danger)</span>
											  </div>
											</div>
										</div>
										<div class="pull-right" style="margin-left:10px;">1</div>
									</div>
									<div class="pull-left">
										<div class="pull-left" style="width:35px; line-height:1;">
											<div style="height:9px; margin:5px 0;">4 <span class="glyphicon glyphicon-star"></span></div>
										</div>
										<div class="pull-left" style="width:180px;">
											<div class="progress" style="height:9px; margin:8px 0;">
											  <div class="progress-bar progress-bar-primary" role="progressbar" aria-valuenow="4" aria-valuemin="0" aria-valuemax="5" style="width: 80%">
												<span class="sr-only">80% Complete (danger)</span>
											  </div>
											</div>
										</div>
										<div class="pull-right" style="margin-left:10px;">1</div>
									</div>
									<div class="pull-left">
										<div class="pull-left" style="width:35px; line-height:1;">
											<div style="height:9px; margin:5px 0;">3 <span class="glyphicon glyphicon-star"></span></div>
										</div>
										<div class="pull-left" style="width:180px;">
											<div class="progress" style="height:9px; margin:8px 0;">
											  <div class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="3" aria-valuemin="0" aria-valuemax="5" style="width: 60%">
												<span class="sr-only">80% Complete (danger)</span>
											  </div>
											</div>
										</div>
										<div class="pull-right" style="margin-left:10px;">0</div>
									</div>
									<div class="pull-left">
										<div class="pull-left" style="width:35px; line-height:1;">
											<div style="height:9px; margin:5px 0;">2 <span class="glyphicon glyphicon-star"></span></div>
										</div>
										<div class="pull-left" style="width:180px;">
											<div class="progress" style="height:9px; margin:8px 0;">
											  <div class="progress-bar progress-bar-warning" role="progressbar" aria-valuenow="2" aria-valuemin="0" aria-valuemax="5" style="width: 40%">
												<span class="sr-only">80% Complete (danger)</span>
											  </div>
											</div>
										</div>
										<div class="pull-right" style="margin-left:10px;">0</div>
									</div>
									<div class="pull-left">
										<div class="pull-left" style="width:35px; line-height:1;">
											<div style="height:9px; margin:5px 0;">1 <span class="glyphicon glyphicon-star"></span></div>
										</div>
										<div class="pull-left" style="width:180px;">
											<div class="progress" style="height:9px; margin:8px 0;">
											  <div class="progress-bar progress-bar-danger" role="progressbar" aria-valuenow="1" aria-valuemin="0" aria-valuemax="5" style="width: 20%">
												<span class="sr-only">80% Complete (danger)</span>
											  </div>
											</div>
										</div>
										<div class="pull-right" style="margin-left:10px;">0</div>
									</div>
								</div>			
							</div>			
							
							<div class="row justify-content-center">
								<div class="col-sm-7">
									<hr/>
									<div class="review-block">
										
									  <c:forEach var="productReview" items="${productReviews}">
										<div class="row">
											<div class="col-sm-3">
												<img src="img/utente.png" height="60" width="60"class="img-rounded">
												<div class="review-block-name">${productReview.user.name} ${productReview.user.surname}</div>
												<div class="review-block-date">January 29, 2016<br/>1 day ago</div>
											</div>
											<div class="col-sm-9">
												<div class="review-block-rate">
												
													      <c:forEach var = "i" begin = "1" end = "${productReview.feedback}">
													         		<button type="button" class="btn btn-warning btn-sm" aria-label="Left Align">
																	  <i class="fa fa-star" aria-hidden="true"> </i>
																	</button>
													      </c:forEach>
													      
													      <c:forEach var = "i" begin = "${productReview.feedback+1}" end = "5">
      																<button type="button" class="btn btn-default btn-grey btn-sm" width="12" height="12" aria-label="Left Align">
													  					<i class="fa fa-star" aria-hidden="true"> </i>
																	</button>
													      </c:forEach>
											
												</div>
												<div class="review-block-title">${productReview.title}</div>
												<div class="review-block-description">${productReview.text}</div>
											</div>
										</div>
										<hr/>
									</c:forEach>
									</div>
								</div>
							</div>
	 					 <!-- Page content -->
						</div>
						
			    </c:when>
	</c:choose>
    
    
	
    
    <!-- //////////////////////////////////////////////////////////////////////// -->
    
  </main>
  <!--Main layout-->
  
  <!--MODALLLLLLLL START  -->
  <div class="modal fade" id="add-review-modal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Lascia una recensione </h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <form id="review-form" class="submit-by-class">
          
          <div class="form-group">	
          	<label for="title">Titolo</label>
            <input type="text" name="title"  class="form-control" id="title" placeholder="Inserisci il titolo della recensione" required>
          </div>
          <div class="form-group">
	          <label for="text">Testo</label>
	          <textarea id="text" name="text" class="form-control " style="height:200px;" placeholder="Inserisci il testo della tua recensione" required> </textarea>
          </div>
		  <div class="form-group">
		    <label for="feedback">FeedBack</label>
		    <select class="form-control" id="feedback">
		      <option value="1">1</option>
		      <option value="2">2</option>
		      <option value="3">3</option>
		      <option value="4">4</option>
		      <option value="5">5</option>
		    </select>
		  </div>
        
 		<div class="modal-footer">
	        <button id="close-modal" type="button" class="btn btn-secondary" data-dismiss="modal">Chiudi</button>
	        <button id="btn-add-review" type="submit" class="btn btn-primary" data-idproduct="${selectedProduct.id}" data-iduser="${user.id}">Lascia la recensione</button>
      	</div>
      	
        </form>
        
        
      </div>
     
      
    </div>
  </div>
</div>

<!--MODALLLLLLLL END  -->
</body>
</html>

