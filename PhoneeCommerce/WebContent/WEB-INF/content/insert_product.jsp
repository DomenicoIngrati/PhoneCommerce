<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<link rel="stylesheet" href="bootstrap-4.3.1-dist/css/bootstrap.min.css" >
<script src="bootstrap-4.3.1-dist/js/bootstrap.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>

<link href="https://fonts.googleapis.com/css?family=Kaushan+Script" rel="stylesheet">
<link rel="stylesheet" href="css/insertProduct.css">

<div class="container" id="sign-box">
        <div class="row">
			<div class="col-md-5 mx-auto">
			<div >
				<div class="myform form ">
					 <div class="logo mb-3">
						 <div class="col-md-12 text-center">
							<h1>Aggiungi Prodotto</h1>
						 </div>
					</div>
                   <form id="add-product" action="#" method="post" name="login">
                           <div class="form-group">
                              <label for="name">Nome</label>
                              <input type="text" name="email"  class="form-control" id="email" aria-describedby="emailHelp" placeholder="Inserisci email" required="required">
                           </div>
                           <div class="form-group">
                              <label for="exampleInputEmail1">Brand/Categoria</label>
                              <input type="text" name="brand-category" id="brand-category"  class="form-control"  placeholder="Inserisci brand/category">
                           </div>
                           <div class="form-group">
                           
                           		<label for="price">Prezzo</label>
                           
	                           <div class="input-group">
								  <input type="text" class="form-control">
								  <div class="input-group-append ">
								    <span class="input-group-text">€</span>
								  </div>
								</div>
							</div>
							
							<div class="form-group">
								<label for="Descrizione">Descrizione</label>
								<textarea class="form-control" rows="5" id="comment"></textarea>
							
							
							</div>
							
							<div class="form-group">
								<label for="photos">Immagini</label>
								<div class="custom-file">
									    
							
							
							</div>
							
							
                           
                           <div class="col-md-12 text-center ">
                              <button type="submit" class=" btn btn-block mybtn btn-primary tx-tfm">Accedi</button>
                           </div>
                           
                           
                        </form>
				</div>
			</div>
			</div>
		</div>
      </div>   