
function operation_alert(result, callback) {
	$("html, body").animate({ scrollTop: 0 }, "slow");
    $("#login-auto-close-alert").focus();
    
    var str = result.result;
    if ((str.search("SUCCESS")) != -1) {
        $("#login-alert-text").html("<strong>" + result.message + "</strong>");
        $("#login-auto-close-alert").removeClass("hidden").addClass("alert-success fade in");
        $("#login-auto-close-alert").fadeTo(1500, 750).slideUp(600, function() {
            $(this).removeClass("alert-success fade in");

        });
    } else {
        $("#login-alert-text").html("<strong>" + result.reason + "</strong>");
        $("#login-auto-close-alert").removeClass("hidden").addClass("alert-danger fade in");
        $("#login-auto-close-alert").fadeTo(1500, 750).slideUp(600, function() {
            $(this).removeClass("alert-danger fade in");

        });
    }  
}

$(document).ready(function(){
	
	$(".add-product-on-cart").on('click',function(){
		
		var prodotto = {
				id : $(this).data('id'),
				quantity: $('#qty_input').val()
			};
		
		if(prodotto.quantity==undefined){
			prodotto.quantity=1;
		}
		
	
		
		$.ajax({
			url: "cart?action=addProductOnCart",
			type: "POST",
			datatype: "json",
			data: JSON.stringify(prodotto),
	        success: function(result){
	        	var data = JSON.parse(result);
	        	var size = data.size;
	        	
	        	$("#countCart").text(size);
	        	
	        	
	        },
	        error: function(){	
	        	alert("Errore di richiesta al server! Riprovare.");
	        }
		});
	});
});





$(document).ready(function () {

	
	
	$(".delete-Product").on('click', function() {
	
	
	var productName = $(this).data('id');
	
	
	
	$.ajax({
		url: "cart?action=deleteProduct",
		type: "POST",
		datatype: "json",
		data: JSON.stringify(productName),
        success: function(result){   	
        	$("#"+productName).slideUp();
        	
        	window.location.reload();
        },
        error: function(){	
        	alert("Errore di richiesta al server! Riprovare.");
        } 
	});
  });
});

$(document).ready(function () {
	
	$(".step-up").on('click',function(e){
		var id = $(this).data('id');
		
		var prodotto = {
				id : $(this).data('id'),
				quantity: 1
			};
		
		
		$.ajax({
			url: "cart?action=addProductOnCart",
			type: "POST",
			datatype: "json",
			data: JSON.stringify(prodotto),
	        success: function(result){
	        	var data = JSON.parse(result);
	        	var size = data.size;
	        	var totalcart = data.total;
	        	$("#countCart").text(size);
	        	$("#total-cart").text(totalcart);
	        	$("#subtotal-" + id).text("€ " + ($("#qty-" + id).val() * $("#qty-" + id).data('price')));
	        	
	        },
	        error: function(){	
	        	alert("Errore di richiesta al server! Riprovare.");
	        }
		});
		
		
		
		
	});
	
});

$(document).ready(function () {
	
	$(".step-down").on('click',function(e){
		var id = $(this).data('id');
		var qty = $("#qty-" + id).val();
		
			var prodotto = {
					id : $(this).data('id'),
					quantity: 1
			}
			$.ajax({
				url: "cart?action=decreaseQuantity",
				type: "POST",
				datatype: "json",
				data: JSON.stringify(prodotto),
		        success: function(result){
		        	var data = JSON.parse(result);
		        	var size = data.size;
		        	var totalcart = data.total;
		        	$("#countCart").text(size);
		        	$("#total-cart").text(totalcart);
		        	$("#subtotal-" + id).text("€ " + ($("#qty-" + id).val() * $("#qty-" + id).data('price')));
		        	
		        },
		        error: function(){	
		        	alert("Errore di richiesta al server! Riprovare.");
		        }
			});
		
		
		
		
		
	});
	
});







