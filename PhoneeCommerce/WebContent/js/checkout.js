/**
 * 
 */


$( document ).ready(function() {
	
	$('.address-box').on('click', function () {
		
		var idProduct= $(this).data('id');
		console.log(idProduct);
		
		
		$.ajax({
			url: "address?action=choseAddress",
			type: "POST",
			datatype: "json",
			data: JSON.stringify(idProduct),
	        success: function(result){
	        	window.location.reload();
	        },
	        error: function(){	
	        	alert("Errore di richiesta al server! Riprovare.");
	        }
		});
	});	
});

$( document ).ready(function() {
	
	$('#new-order').on('click', function () {
		
		
		$.ajax({
			url: "order?action=newOrder",
			type: "POST",
	        success: function(result){
        		window.location.href="home?action=orderCompleted";
	        },
	        error: function(){	
	        	alert("Errore di richiesta al server! Riprovare.");
	        }
		});
	});
	
});