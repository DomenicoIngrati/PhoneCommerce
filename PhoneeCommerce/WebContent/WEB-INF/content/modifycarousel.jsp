<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>

<link rel="stylesheet" href="css/modifycarousel.css">
<script src="js/account.js" type="text/javascript"></script>
<script src="js/jquery_form.js" type="text/javascript"></script>
<script src="js/manage_carousel.js" type="text/javascript"></script>


<div class="boxcontainer container mt-5">
<h2>Modifica e aggiungi banner nel carosello della home page</h2>
<hr>

<div class="row">
	


	<c:forEach var="banner" items="${banners}">
		<div id="banner-${banner.id}" class="col-sm-12">
			<div class="banner-box">
				<div class="row">
					<div class="col-auto">
						<%--<div style=" background-image: url('${banner.imageString}');" > </div>--%>
						<img src="${banner.imageString}" class="img-fluid" alt="Responsive image">
					</div>
					<div class="col-auto info-banner">
						<p> <strong>Titolo: </strong> ${banner.title} </p>
						<p> <strong>Descizione: </strong> ${banner.description} </p>
					</div>
					<div class="col-auto btn-box-banner">
						<button type="button" data-idbanner="${banner.id}" class="btn btn-banner btn-sm btn-info btn-modify-img btn-panel" data-dismiss="modal" data-toggle="modal" data-target="#image-banner-modal">Modifica immagine</button>
						<button type="button" class="btn btn-banner btn-sm btn-warning" data-name="modify" data-idbanner="${banner.id}" data-title="${banner.title}" data-description="${banner.description}" data-dismiss="modal" data-toggle="modal" data-target="#modify-banner-modal" >Modifica titolo e/o descrizione</button>
						<button class="btn btn-banner btn-sm btn-danger btn-delete-banner" data-idbanner="${banner.id}">Elimina</button>
					</div>

				</div>
			</div>
		</div>
	</c:forEach>

	<div class="col-sm-12">
		<button id="add-banner" type="button" class="btn btn-block" data-name="add" data-toggle="modal" data-target="#add-banner-modal">
			<div class="row">
				<div class="col"> <!-- DIV TESTO -->
					<div class="d-flex justify-content-center"> <img src="img/plus.png" height="50" width="50"> </div>

					<div class="d-flex justify-content-center"> <h4 class="card-title">Aggiungi banner</h4> </div>
				</div>
			</div>
		</button>
	</div>
	
	
	
</div>
</div>

 <%--MODAL MODIFICA IMMAGINE--%>
<div class="modal fade" id="image-banner-modal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title">Modifica immagine del banner</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <form id="image-banner-form" class="submit-by-class">

			<div class="type-img">
				<label for="image-banner-img">Immagine</label>
				<div class="custom-file">
					<input id="image-banner-img" type="file" name="file" class="custom-file-input" accept="image/x-png,image/jpeg" required>
					<label class="custom-file-label" for="image-banner-img">Scegli immagine</label>
				</div>
			</div>

 			<div class="modal-footer">
				<button id="image-banner-close-modal" type="button" class="btn btn-secondary" data-dismiss="modal">Chiudi</button>
	        	<button id="image-banner-btn-add-banner" class="btn btn-primary">Salva banner</button>
			</div>
      	
        </form>
        
        
      </div>
     
      
    </div>
  </div>
</div>

<%--MODAL AGGIUNGI BANNER--%>

<div class="modal fade" id="add-banner-modal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title">Aggiungi banner</h5>
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<form id="add-banner-form" class="submit-by-class" enctype="multipart/form-data">
						<div class="form-group">
							<label for="add-banner-title">Titolo</label>
							<input type="text" name="title"  class="form-control" id="add-banner-title" placeholder="Inserisci titolo" required>
						</div>
						<div class="form-group">
							<label for="add-banner-description">Descrizione</label>
							<input id="add-banner-description" type="text" name="description" class="form-control"  placeholder="Inserisci descrizione" required>
						</div>


					<div class="form-group">
						<label for="add-banner-img">Immagine</label>
						<div class="custom-file">
							<input id="add-banner-img" type="file" name="file" class="custom-file-input" accept="image/x-png,image/jpeg" required>
							<label class="custom-file-label" for="add-banner-img">Scegli immagine</label>
						</div>
					</div>

					<div class="modal-footer">
						<button id="add-banner-close-modal" type="button" class="btn btn-secondary" data-dismiss="modal">Chiudi</button>
						<button id="add-banner-btn-add-banner" class="btn btn-primary">Salva banner</button>
					</div>

				</form>


			</div>


		</div>
	</div>
</div>

<%--MODAL MODIFCA BANNER--%>

<div class="modal fade" id="modify-banner-modal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title">Aggiungi banner</h5>
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<form id="modify-banner-form" class="submit-by-class">
					<div class="type-text">
						<div class="form-group">
							<label for="modify-banner-title">Titolo</label>
							<input type="text" name="title"  class="form-control" id="modify-banner-title" placeholder="Inserisci titolo" required>
						</div>
						<div class="form-group">
							<label for="modify-banner-description">Descrizione</label>
							<input id="modify-banner-description" type="text" name="description" class="form-control"  placeholder="Inserisci descrizione" required>
						</div>
					</div>

					<div class="modal-footer">
						<button id="modify-banner-close-modal" type="button" class="btn btn-secondary" data-dismiss="modal">Chiudi</button>
						<button id="modify-banner-btn-add-banner" type="submit" class="btn btn-primary">Salva banner</button>
					</div>

				</form>


			</div>


		</div>
	</div>
</div>
