<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>

<link rel="stylesheet" href="css/admin.css">
<script src="js/administrator.js" type="text/javascript"></script>
<script src="js/jquery_form.js" type="text/javascript"></script>

 <!--  RIGA NUOVI PRODOTTI  -->

<div id="managebox" class="container boxcontainer mt-5" >
	<h1> Modifica o elimina prodotti </h1>
	<hr>
	<div class="listProduct">

		<c:forEach var="product" items="${allProducts}">
			
			<div class="card mb-3 cardDelete" id="${product.id}">
				<div class="row no-gutters">
					<div class="col-md-2 image"  style=" background-image:url(${product.imageString});">
		    		</div>
		    		<div class="col-md-10">
		    			<div class="card-body">
		    	
							<div class="row">

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
									<%--<div class="row ">--%>
										<%--<div class="col">--%>
											<button  type="button" data-id="${product.id}" class="btn btn-sm btn-info btn-modify-img btn-panel" data-toggle="modal" data-target="#modifyImgModal">Modifica immagine</button>
										<%--</div>--%>
									<%--</div>--%>
									<%--<div class="row ">--%>
										<%--<div class="col">--%>
											<button  type="button" data-id="${product.id}" data-name="${product.name}" data-category="${product.category.name}" data-price="${product.price}" data-description="${product.description}" data-image="${product.image}" class="btn btn-sm btn-warning btn-panel modify" data-toggle="modal" data-target="#modifyModal">Modifica</button>
											<button  type="button" data-id="${product.id}" class="btn btn-sm btn-danger btn-panel delete">Elimina</button>
										<%--</div>--%>
									<%--</div>--%>
								</div>

			      			</div>
		      
		      			</div>
		    		</div>
		  		</div>
			</div>
			
		
		</c:forEach>
	
	
	
	</div>
	
	<!-- MODAL MODIFICA -->
	
<div class="modal fade" id="modifyModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Modifica prodotto</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <form id="modifyProduct">
          
          <div class="form-group">
          	
          	<label for="name">Nome</label>
            <input type="text" name="name"  class="form-control" id="name" placeholder="Inserisci nome">
          </div>
          <div class="form-group">
	          <label for="brand-category">Brand/Categoria</label>
	          <input id="category" type="text" name="category" id="brand-category"  class="form-control"  placeholder="Inserisci brand/category" >
          </div>
          <div class="form-group">
          	<label for="price">Prezzo</label>
                           
	        <div class="input-group">
				<input id="price"name ="price" type="text" class="form-control" placeholder="Inserisci prezzo">
				<div class="input-group-append ">
					<span class="input-group-text">€</span>
				</div>
			</div>
		</div>
							
		<div class="form-group">
			<label for="description">Descrizione</label>
			<textarea id="description" name ="description" class="form-control" rows="5" id="comment" placeholder="Inserisci descrizione"></textarea>
							
							
		</div>

		<div class="col-md-12 text-center ">
			<button id="submitModify" type="submit" class=" btn btn-block mybtn btn-primary tx-tfm">Effettua modifiche</button>
		</div>
          
        </form>
      </div>
    </div>
  </div>
</div>

	<%--MODAL MODIFICA IMMAGINE--%>
	<div class="modal fade" id="modifyImgModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="modifyImgModalLabel">Modifica immagine del prodotto</h5>
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">

					<form id="modifyImgProduct" method="post" enctype="multipart/form-data">

						<div class="form-group">
							<label for="modifyProductImage">Scegli immagine</label>
							<div class="custom-file">
								<input id="modifyProductImage" type="file" name="file" class="custom-file-input" accept="image/x-png,image/jpeg" required>
								<label class="custom-file-label" for="modifyProductImage">Scegli immagine</label>
							</div>
						</div>

						<div class="col-md-12 text-center ">
							<button id="submitImgModify" class=" btn btn-block mybtn btn-primary tx-tfm">Effettua modifiche</button>
						</div>

					</form>

				</div>
			</div>
		</div>
	</div>

</div>
