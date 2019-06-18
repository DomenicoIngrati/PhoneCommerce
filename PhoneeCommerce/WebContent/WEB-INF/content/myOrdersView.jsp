<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
 
<!DOCTYPE html>
<link rel="stylesheet" href="css/myOrdersViewStyle.css">


<div class="boxcontainer container mt-5">
<h2>Visualizza ordini</h2>
<hr>



		<c:choose>
			    <c:when test="${fn:length(myOrders) eq 0}">
						<h2>Non hai effettuato nessun ordine.</h2>
						<a href="home?action=index" >
                        <button type="button" class="btn btn-sm btn-warning justify-content-center" style="margin:auto;display:block"  > Torna alla home </button>
                    	</a>
			    </c:when>
			    
			    <c:when test="${fn:length(myOrders) gt 0}">
			    
			    	<div class="row">
						<c:forEach var="myOrder" items="${myOrders}">
							<div id="address-${myOrder.id}" class="col-sm-4">
								<div class="address-box">
									<div class="row">
										<div class="col-auto"> <!-- DIV IMMAGINE -->
											<p> <strong>Data: </strong> ${myOrder.date}</p> 
											<p> <strong>Totale ordine: </strong> €${myOrder.total}0</p>
											<p> <strong>Destinatario: </strong>${myOrder.address.namelastname}</p>
											<p> <strong>Indirizzo: </strong>${myOrder.address.address}</p>
										</div> 
										<div class="col-auto btn-box-address">
							        		<button type="button" class="btn btn-sm btn-warning show-Order" data-id="${myOrder.id}">Riepilogo ordine</button>
										</div> 			
									</div>
								</div>
							</div>
						</c:forEach>
					</div>
			    </c:when>
		</c:choose>

		
		
</div>

