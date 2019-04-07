<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>


<div class="boxcontainer container mt-4">
    <div class="row justify-content-center">
        <div class="col-xs-12">
            <div class="text-center">
                <i class="fa fa-search-plus pull-left icon"></i>
                <h2>L'ordine è stato completato</h2>
            </div>
            <hr>
            <div class="row">
                <div class="col-xs-12 col-md-3 col-lg-3 pull-left">
                    <div class="panel panel-default height">
                        <div class="panel-heading"><strong>Indirizzo di fatturazione</strong></div>
                        <div class="panel-body">
                            <c:out value="${orderCompleted.address.namelastname}" /><br>
                            <c:out value="${orderCompleted.address.address}" /><br>
                            <c:out value="${orderCompleted.address.city}" /><br>
                            <c:out value="${orderCompleted.address.province}" /><br>
                            <c:out value="${orderCompleted.address.zipcode}" /><br>
                        </div>
                    </div>
                </div>
                <div class="col-xs-12 col-md-3 col-lg-3">
                    <div class="panel panel-default height">
                        <div class="panel-heading"><strong> Pagamento </strong></div>
                        <div class="panel-body">
                            <strong>Card Name:</strong> Visa<br>
                            <strong>Card Number:</strong> ***** 332<br>
                            <strong>Exp Date:</strong> 09/2020<br>
                        </div>
                    </div>
                </div>
                <div class="col-xs-12 col-md-3 col-lg-3">
                    <div class="panel panel-default height">
                        <div class="panel-heading"><strong>Preferenze</strong></div>
                        <div class="panel-body">
                            <strong>Gift:</strong> No<br>
                            <strong>Express Delivery:</strong> Yes<br>
                            <strong>Insurance:</strong> No<br>
                            <strong>Coupon:</strong> No<br>
                        </div>
                    </div>
                </div>
                <div class="col-xs-12 col-md-3 col-lg-3 pull-right">
                    <div class="panel panel-default height">
                        <div class="panel-heading"><strong>Indirizzo di spedizione</strong></div>
                        <div class="panel-body">
                            <c:out value="${orderCompleted.address.namelastname}" /><br>
                            <c:out value="${orderCompleted.address.address}" /><br>
                            <c:out value="${orderCompleted.address.city}" /><br>
                            <c:out value="${orderCompleted.address.province}" /><br>
                            <c:out value="${orderCompleted.address.zipcode}" /><br>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="text-center"><strong>Riepilogo ordine</strong></h3>
                </div>
                <div class="panel-body">
                    <div class="table-responsive">
                        <table class="table table-condensed">
                            <thead>
                                <tr>
                                    <td><strong>Nome Prodotto</strong></td>
                                    <td class="text-center"><strong>Prezzo</strong></td>
                                    <td class="text-center"><strong>Quantità</strong></td>
                                    <td class="text-right"><strong>Totale</strong></td>
                                </tr>
                            </thead>
                            <tbody>
                           	
                           	<c:forEach var="productOnOrder" items="${orderCompleted.products}">
                                <tr>
                                    <td>${productOnOrder.product.name}</td>
                                    <td class="text-center">${productOnOrder.product.price}</td>
                                    <td class="text-center">${productOnOrder.quantity}</td>
                                    <td class="text-right">${productOnOrder.product.price}</td>
                                </tr>
            				</c:forEach> 
                                <tr>
                                    <td class="highrow"></td>
                                    <td class="highrow"></td>
                                    <td class="highrow text-center"><strong>Subtotale</strong></td>
                                    <td class="highrow text-right">$<c:out value="${orderCompleted.total}" /></td>
                                </tr>
                                <tr>
                                    <td class="emptyrow"></td>
                                    <td class="emptyrow"></td>
                                    <td class="emptyrow text-center"><strong>Spedizione</strong></td>
                                    <td class="emptyrow text-right">$20</td>
                                </tr>
                                <tr>
                                    <td class="emptyrow"><i class="fa fa-barcode iconbig"></i></td>
                                    <td class="emptyrow"></td>
                                    <td class="emptyrow text-center"><strong>Totale</strong></td>
                                    <td class="emptyrow text-right">$<c:out value="${orderCompleted.total}" /></td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<style>
.height {
    min-height: 200px;
}

.icon {
    font-size: 47px;
    color: #5CB85C;
}

.iconbig {
    font-size: 77px;
    color: #5CB85C;
}

.table > tbody > tr > .emptyrow {
    border-top: none;
}

.table > thead > tr > .emptyrow {
    border-bottom: none;
}

.table > tbody > tr > .highrow {
    border-top: 3px solid;
}
</style>
