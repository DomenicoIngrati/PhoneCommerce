(function($) {

    $.fn.serializeFormJSON = function() {
        var disabled = this.find(':input:disabled').removeAttr('disabled');
        var o = {};
        var a = this.serializeArray();
        disabled.attr('disabled', 'disabled');
        $.each(a, function() {
            if (o[this.name]) {
                if (!o[this.name].push) {
                    o[this.name] = [o[this.name]];
                }
                o[this.name].push(this.value || '');
            } else {
                o[this.name] = this.value || '';
            }
        });
        return o;
    };
})(jQuery);

function operation_alert(result, callback) {
    $("body").scrollTop(0);
    $("#login-auto-close-alert").focus();
    
    var str = result.result;
    if ((str.search("SUCCESS")) != -1) {
        $("#login-alert-text").html("<strong>" + result.message + "</strong>");
        $("#login-auto-close-alert").removeClass("hidden").addClass("alert-success fade in");
        $("#login-auto-close-alert").fadeTo(1500, 750).slideUp(600, function() {
            $(this).removeClass("alert-success fade in");
            callback();
        });
    } else {
        $("#login-alert-text").html("<strong>" + result.reason + "</strong>");
        $("#login-auto-close-alert").removeClass("hidden").addClass("alert-danger fade in");
        $("#login-auto-close-alert").fadeTo(1500, 750).slideUp(600, function() {
            $(this).removeClass("alert-danger fade in");
            callback();
        });
    }
    
}

$( document ).ready(function() {
	
	// mette il nome del file nel label quando selezioniamo il file
	$(".custom-file-input").on("change", function() {
	  var fileName = $(this).val().split("\\").pop();
	  $(this).siblings(".custom-file-label").addClass("selected").html(fileName);
	});
	
});

$( document ).ready(function() {
	
	$("#add-product").submit(function(e) {
	    e.preventDefault();
	    
	    var frm = $(this).serializeFormJSON();
	    
	    var filename = $('input[type=file]').val().replace(/C:\\fakepath\\/i, '')
	    frm.image = filename;
	    console.log(frm);
	    
	    $.ajax({
	        url: "product?action=CREATE",
	        type: "POST",
	        dataType: "JSON",
	        data: JSON.stringify(frm),
	        success: function(result){
	        	operation_alert(result, function(){
	        	    	window.location.href = "home?action=aggiungiProdotto";
	        	});
	        },
	        error: function(){	
	        	alert("Errore di richiesta al server! Riprovare.");
	        }
	    	
	    });
	});
});
