<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
  <main class="boxcontainer container mt-5 pt-4">
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
			    					<input class="btn btn-outline-secondary" type="button" value="Add to cart" onclick="addProductOnCart('${selectedProduct.name}','1');"/>
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
  </main>
  <!--Main layout-->
</body>
</html>