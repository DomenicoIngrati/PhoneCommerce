<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<meta charset="ISO-8859-1">
<title>Insert title here</title>

<link rel="stylesheet" href="bootstrap-4.3.1-dist/css/bootstrap.min.css" >
<script src="bootstrap-4.3.1-dist/js/bootstrap.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="js/account.js" type="text/javascript"></script>
<link href="https://fonts.googleapis.com/css?family=Kaushan+Script" rel="stylesheet">
<link rel="stylesheet" href="css/accediStyle.css">



  <div class="container" id="sign-box">
        <div class="row">
			<div class="col-md-5 mx-auto">
			<div >
				<div class="myform form ">
					 <div class="logo mb-3">
						 <div class="col-md-12 text-center">
							<h1>Accedi</h1>
						 </div>
					</div>
                   <form id="log-in" action="#" method="post" name="login">
                           <div class="form-group">
                              <label for="exampleInputEmail1">Indirizzo email</label>
                              <input type="email" name="email"  class="form-control" id="email" aria-describedby="emailHelp" placeholder="Enter email">
                           </div>
                           <div class="form-group">
                              <label for="exampleInputEmail1">Password</label>
                              <input type="password" name="password" id="password"  class="form-control" aria-describedby="emailHelp" placeholder="Enter Password">
                           </div>
                           <div class="form-group">
                              <p class="text-center">Accedendo accetti i <a href="#">Termini d'Uso</a></p>
                           </div>
                           <div class="col-md-12 text-center ">
                              <button type="submit" class=" btn btn-block mybtn btn-primary tx-tfm">Accedi</button>
                           </div>
                           <div class="form-group">
                              <p class="text-center">Non hai un account? <a href="?action=registration&fromCart=yes" id="signup">Registrati qui</a></p>
                           </div>
                           
                           <div class="col-md-12 ">
                              <div class="login-or">
                                 <hr class="hr-or">
                                 <span class="span-or">or</span>
                              </div>
                           </div>
                           
                           <div class="form-group">
                           	  <!--  LINKARE LA HOME -->
                              <p class="text-center">Continua senza accedere. <a href="?action=index">Home</a></p>
                           </div>
                        </form>
				</div>
			</div>
			</div>
		</div>
      </div>   