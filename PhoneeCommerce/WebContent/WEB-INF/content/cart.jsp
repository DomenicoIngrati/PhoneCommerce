<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 
<!DOCTYPE html>

<script src="js/administrator.js" type="text/javascript"></script>
<script src="js/cart.js" type="text/javascript"></script>

<div class="container boxcontainer mt-5" >
	<h1> Carrello </h1>
	<hr>
	<div class="listProduct">
	
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
		<c:forEach var="productOnCart" items="${cart.products}">
				<tr>
				<td data-th="Product">
					<div class="row">
						<div class="col-sm-4 image"  style=" background-image:url(http://placehold.it/100x100); background-repeat:no-repeat; background-position: center center; background-size: cover;" > </div>
						<!-- <div class="col-sm-2 hidden-xs"><img src="http://placehold.it/100x100" alt="..." class="img-responsive"/></div> -->
						<div class="col-sm-8">
							<h4 class="nomargin">${productOnCart.product.name}</h4>
							<p>${productOnCart.product.description }</p>
						</div>
					</div>
				</td>
				<td data-th="Price">$${productOnCart.product.price}0</td>
				<td data-th="Quantity">
					
				<div class="input-group mb-3">
                             <!-- <div class="input-group-prepend">
                                 <button class="btn btn-dark " id="minus-btn"><i class="fa fa-minus"></i></button>
                             </div>
                             <input type="number" id="qty_input" class="form-control form-control-mb" value="1" min="1">
                             <div class="input-group-prepend">
                                 <button class="btn btn-dark " id="plus-btn"><i class="fa fa-plus"></i></button>
                             </div> -->
                             <input value="${productOnCart.quantity}" type="number" min="0" max="10" step="1"/>
                </div>
				</td>
				<td data-th="Subtotal" class="text-center">NON SO COS'è</td>
				<td class="actions" data-th="">
					<button class="btn btn-danger btn-sm delete-Product" data-id="${productOnCart.product.name}" ><i class="fa fa-trash-o"></i></button>
				</td>
			   </tr>

			
		</c:forEach> 
		</tbody>
		<tfoot>
			<tr>
				<td><a href="#" class="custom-close btn btn-warning"><i class="fa fa-angle-left"></i> Continue Shopping</a></td>
				<td colspan="2" class="hidden-xs"></td>
				<td class="hidden-xs text-center"><strong>Total $<c:out value="${cart.total}0" /></strong></td>
				<td><a href="home?action=cartCheckout" class="btn btn-success btn-block">Checkout <i class="fa fa-angle-right"></i></a></td>
			</tr>
		</tfoot>
	  </table>
	
	</div>
    
</div>
