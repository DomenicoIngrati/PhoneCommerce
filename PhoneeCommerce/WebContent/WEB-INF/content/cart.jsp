<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
 
<!DOCTYPE html>

<script src="js/administrator.js" type="text/javascript"></script>
<script src="js/cart.js" type="text/javascript"></script>

<link rel="stylesheet" href="css/cart.css">

<div class="container boxcontainer mt-5 cart" >
	<h1> Carrello </h1>
	<hr>
	<div class="listProduct">
	
		<c:choose>
			    <c:when test="${fn:length(cart.products) eq 0}">
			    		<br>
						<h2>Il tuo carrello è vuoto</h2>
						<br>
						<br>
						<a href="home?action=index" >
                        <button type="button" class="btn btn-sm btn-warning justify-content-center" style="margin:auto;display:block"  > Torna alla home </button>
                    	</a>
			    </c:when>
			    
			    <c:when test="${fn:length(cart.products) gt 0}">
			    	
			    		<table id="cart" class="table table-hover table-condensed">
					 				<thead>
								<tr>
									<th style="width:50%">Product</th>
									<th style="width:10%">Price</th>
									<th style="width:10%">Quantity</th>
									<th style="width:22%" class="text-center">Subtotal</th>
									<th style="width:8%"></th>
								</tr>
									</thead>
							
							<tbody>
							<c:forEach var="item" items="${cart.products}">
									<tr>
									<td data-th="Product">
										<div class="row">
											<div class="col-sm-4 image"  style=" background-image:url(http://placehold.it/100x100); background-repeat:no-repeat; background-position: center center; background-size: cover;" > </div>
											<!-- <div class="col-sm-2 hidden-xs"><img src="http://placehold.it/100x100" alt="..." class="img-responsive"/></div> -->
											<div class="col-sm-8">
												<h4 class="nomargin">${item.product.name}</h4>
												<p>${item.product.description }</p>
											</div>
										</div>
									</td>
									<td data-th="Price">€ ${item.product.price}0</td>
									<td data-th="Quantity">
										
									<div class="input-group mb-3">
					                             
					                             <%-- <input value="${item.quantity}" id="qty_input" type="number" min="0" max="10" step="1"/> --%>
										<div class="number-input">
											<button class="step-down" onclick="this.parentNode.querySelector('input[type=number]').stepDown()" data-id="${item.product.id}"></button>
											<input id="qty-${item.product.id}" data-price="${item.product.price}" value="${item.quantity}" class="quantity" min="1" max="10" name="quantity" value="1" type="number">
											<button onclick="this.parentNode.querySelector('input[type=number]').stepUp()" class="plus step-up" data-id="${item.product.id}"></button>
										</div>
					                </div>
									</td>
									<td data-th="Subtotal" class="text-center"> <span id="subtotal-${item.product.id}" >${item.quantity * item.product.price } </span></td>
									<td class="actions" data-th="">
										<button class="btn btn-danger btn-sm delete-Product" data-id="${item.product.name}" ><i class="fa fa-trash-o"></i></button>
									</td>
								   </tr>
					
								
							</c:forEach> 
							</tbody>
							<tfoot>
								<tr>
									<td><a href="#" class="custom-close btn btn-warning"><i class="fa fa-angle-left"></i> Continue Shopping</a></td>
									<td colspan="2" class="hidden-xs"></td>
									<td class="hidden-xs text-center"><strong>Total € <span id="total-cart"> <c:out value="${cart.total}" /> </span> </strong></td>
									<td><a href="home?action=cartCheckout" class="btn btn-success btn-block">Checkout <i class="fa fa-angle-right"></i></a></td>
								</tr>
							</tfoot>
						 </table>
			    
			    </c:when>
		</c:choose>
		
	
	</div>
    
</div>
