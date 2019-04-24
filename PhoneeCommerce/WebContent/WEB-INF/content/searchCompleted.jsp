<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>

<link rel="stylesheet" href="css/productsViewStyle.css">
 <script src="js/cart.js" type="text/javascript"></script>
 <script src="js/wishlist.js" type="text/javascript"></script>

 
 	
 	
 <!--  RIGA NUOVI PRODOTTI  -->
 

			    
			    
                <div class="boxcontainer container mt-5">
                
                			<c:if test="${numOfResults!=1}">
						   	<h2 class="text-center" style="margin-bottom:30px;"> <c:out value="${numOfResults}" /> risultati trovati </h2>
							</c:if>
							
							<c:if test="${numOfResults==1}">
						   	<h2 class="text-center" style="margin-bottom:30px;"> <c:out value="${numOfResults}" /> risultato trovato </h2>
							</c:if>
							
							   <c:choose>
							    
								   	<c:when test="${numOfResults==0}">
									    <div class="boxcontainer container mt-5">
									    		
												<h2 align="center">Siamo spiacenti, nessun risultato trovato</h2>
												<br>
												<br>
												<a href="home?action=index" >
						                        <button type="button" class="btn btn-sm btn-warning justify-content-center" style="margin:auto;display:block"  > Torna alla home </button>
						                    	</a>
						                </div>
				    				</c:when>
			    				
								    <c:when test="${numOfResults != 0}">
								    <div class="row justify-content-center">
								       <c:forEach var="searchResultProduct" items="${searchResult}">
								        <div class="col-auto mb-3">
								            <div class="card" style="width: 20rem;">
									 			<a href="home?action=singleProductView&productName=${searchResultProduct.name}" >
												<img src="#" class="card-img-top" alt="...">
												</a>
													<div class="card-body flex-fill ">
														<h5 class="card-title">${searchResultProduct.name} ${searchResultProduct.price}0 €</h5>
														<h6 class="card-subtitle mb-2 text-muted">${searchResultProduct.category.name}</h6> <!-- category --> 
													    <p class="card-text">${searchResultProduct.description}</p>
														<button type="button" class="btn btn-sm btn-primary add-product-on-cart" data-name="${searchResultProduct.name}"> <i class="fa fa-shopping-cart"></i> </button> 
											  			<button type="button" class="btn btn-sm btn-warning btn-add-product-on-wishlist" data-idproduct="${searchResultProduct.id}" data-idwishlist="${wishlist.id}"> <i class="fa fa-heart-o"></i> Aggiungi ai preferiti</button>
													</div>
								        	 </div>
								        </div>
								        </c:forEach>
									</div>
									
									</c:when>
							</c:choose>
											
						</div>	
			    


