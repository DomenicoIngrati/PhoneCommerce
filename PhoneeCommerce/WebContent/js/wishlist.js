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

$( document ).ready(function() {
	
	$(".btn-add-product-on-wishlist").click(function(){
		
		var info = {
				idproduct: $(this).data('idproduct'),
				idwishlist: $(this).data('idwishlist')
		}
		
		
		$.ajax({
	        url: "wishlist?action=AddProductOnList",
	        type: "POST",
	        dataType: "JSON",
	        data: JSON.stringify(info),
	        success: function(result){
	        	operation_alert(result, function(){
	        	});
	        },
	        error: function(){	
	        	alert("Errore di richiesta al server! Riprovare.");
	        }    	
	    });
	});
	
});

$( document ).ready(function() {
	
	$(".btn-rmv-product").click(function(){
		
		var info = {
				idproduct: $(this).data('idproduct'),
				idwishlist: $(this).data('idwishlist')
		}	
		
		
		$.ajax({
	        url: "wishlist?action=deleteProductFromList",
	        type: "POST",
	        dataType: "JSON",
	        data: JSON.stringify(info),
	        success: function(result){
	        	$("#card-"+info.idproduct).fadeOut();
	        	if(result.size == 0){
	        		$(".btn-add-all-cart").attr("disabled", true);
	        	}
	        },
	        error: function(){	
	        	alert("Errore di richiesta al server! Riprovare.");
	        }    	
	    });
	});
	
});

$( document ).ready(function() {
	
	$(".btn-add-all-cart").click(function(){
		
		$.ajax({
	        url: "wishlist?action=addAllToCart",
	        type: "POST",
	        success: function(data){
	        	result = JSON.parse(data);
	        	var size = result.size;
	        	$("#countCart").text(size);
	        	operation_alert(result, function(){
	        	});
	        },
	        error: function(){	
	        	alert("Errore di richiesta al server! Riprovare.");
	        }    	
	    });
	});
	
});