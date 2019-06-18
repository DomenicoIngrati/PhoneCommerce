/**
 * 
 */



$( document ).ready(function() {
	
	$('.show-my-orders').on('click', function () {
		
		
		$.ajax({
			url: "order?action=myOrdersView",
			type: "POST",
	        success: function(result){
        		window.location.href="home?action=myOrdersView";
	        },
	        error: function(){	
	        	alert("Errore di richiesta al server! Riprovare.");
	        }
		});
	});	
});

$( document ).ready(function() {
	
	$('.show-Order').on('click', function () {
		
		var idOrder= $(this).data('id');
		
		$.ajax({
			url: "order?action=summaryOrder",
			type: "POST",
			datatype: "json",
			data: JSON.stringify(idOrder),
	        success: function(result){
        		window.location.href="home?action=singleOrderView";
	        },
	        error: function(){	
	        	alert("Errore di richiesta al server! Riprovare.");
	        }
		});
	});	
});