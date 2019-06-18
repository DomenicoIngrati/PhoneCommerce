/**
 * 
 */

//$("#signup").click(function() {
//$("#first").fadeOut("fast", function() {
//$("#second").fadeIn("fast");
//});
//});
//
//$("#signin").click(function() {
//$("#second").fadeOut("fast", function() {
//$("#first").fadeIn("fast");
//});
//});


  
// $(function() {
//   $("form[name='login']").validate({
//     rules: {
//       
//       email: {
//         required: true,
//         email: true
//       },
//       password: {
//         required: true,
//         
//       }
//     },
//      messages: {
//       email: "Please enter a valid email address",
//      
//       password: {
//         required: "Please enter password",
//        
//       }
//       
//     },
//     submitHandler: function(form) {
//       form.submit();
//     }
//   });
//});
         


//$(function() {
//  
//  $("form[name='registration']").validate({
//    rules: {
//      name: "required",
//      surname: "required",
//      username: "required",
//      email: {
//        required: true,
//        email: true
//      },
//      password: {
//        required: true,
//        minlength: 5
//      }
//    },
//    
//    messages: {
//      firstname: "Please enter your firstname",
//      lastname: "Please enter your lastname",
//      password: {
//        required: "Please provide a password",
//        minlength: "Your password must be at least 5 characters long"
//      },
//      email: "Please enter a valid email address"
//    }
//  
//    
//  });
//});

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
	$("html, body").animate({ scrollTop: 0 }, "slow");
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
	
	$(".btn-address-delete").click(function(){
		var id = $(this).data('id');
		
		
		
		
		
		$.ajax({
	        url: "address?action=DELETE",
	        type: "POST",
	        dataType: "JSON",
	        data: JSON.stringify(id),
	        success: function(result){
	        	var str = result.result;
        	    if ((str.search("SUCCESS")) != -1) {
        	    	$("#address-"+id).fadeOut();
        	    }
        	    else{
        	    	operation_alert(result, function(){
    	        		
    	        	});
        	    }	
	        },
	        error: function(){	
	        	alert("Errore di richiesta al server! Riprovare.");
	        }    	
	    });
	});
	
});

$( document ).ready(function() {
	
	$('#add-address-modal').on('show.bs.modal', function (e) {

		var button = $(e.relatedTarget);
		
		var name = button.data('name'); 
		
		if(name=="modify")
		{
			
			var modal = $(this);
			modal.find('#namelastname').val(button.data('namelastname'));
			modal.find('#address').val(button.data('address'));
			modal.find('#city').val(button.data('city'));
			modal.find('#province').val(button.data('province'));
			modal.find('#zipcode').val(button.data('zipcode'));
			modal.find('#tel').val(button.data('tel'));

			
			
			$("#address-form").submit(function(e){
				
				e.preventDefault();
			    var frm = null;
			    frm = $(this).serializeFormJSON();
			    frm.id =  button.data('idaddress');
			   
			    
			    $.ajax({
			        url: "address?action=UPDATE",
			        type: "POST",
			        dataType: "JSON",
			        data: JSON.stringify(frm),
			        success: function(data){
			        	$("#add-address-modal").modal('hide');
			        	operation_alert(data, function(){
			        		window.location.reload();
			        	});
			        	
			        },
			        error: function(){	
			        	$("#add-address-modal").modal('hide');
			        	alert("Errore di richiesta al server! Riprovare.");
			        }    	
			    });
			    
				
			});
		}
		else if (name=="add"){
			$("#address-form").submit(function(e){
				
				
				e.preventDefault();
				var frm = null;
			    frm = $(this).serializeFormJSON();
			   
			  
			    
			    $.ajax({
			        url: "address?action=CREATE",
			        type: "POST",
			        dataType: "JSON",
			        data: JSON.stringify(frm),
			        success: function(data){
			        	$("#add-address-modal").modal('hide');
			        	var newData=data.comingFromCart;
			       
			        	operation_alert(data, function(){
			        		window.location.reload();

			        	});
			        	

			        	
			        },
			        error: function(){	
			        	$("#add-address-modal").modal('hide');
			        	alert("Errore di richiesta al server! Riprovare.");
			        }    	
			    });

		});
		}
	});
	
});





$(function () {
$("#add-address").on('click', function() {
  $('#general-modal').modal('hide');
});
});

$( document ).ready(function() {
	$("#close-modal").on('click',function(){
		var modal = $("#add-address-modal");
			modal.find('#namelastname').val("");
			modal.find('#address').val("");
			modal.find('#city').val("");
			modal.find('#province').val("");
			modal.find('#zipcode').val("");
			modal.find('#tel').val("");
	});
});

$( document ).ready(function() {
	
	$("#sign-up").submit(function(e) {
	    e.preventDefault();
	    
	    var frm = $(this).serializeFormJSON();
	    
	    $.ajax({
	        url: "account?action=signup",
	        type: "POST",
	        dataType: "JSON",
	        data: JSON.stringify(frm),
	        success: function(data){
	        	
	        	var newData=data.comingFromCart;
	        	
	        	if(newData == true){
		        	operation_alert(data, function(){
		        		window.location.href="?action=cartCheckout";
		        	});
	        	}
	        	else{
		        	operation_alert(data, function(){
		        		window.location.href = "?action=index";
		        	});
	        	}
	        	
	        },
	        error: function(){	
	        	alert("Errore di richiesta al server! Riprovare.");
	        }    	
	    });
	});
});

$( document ).ready(function() {

	$("#log-in").submit(function(e) {
	    e.preventDefault();
	    var frm = $(this).serializeFormJSON();
	    
	   
	    
	    
	    $.ajax({
	        url: "account?action=signin",
	        type: "POST",
	        dataType: "JSON",
	        data: JSON.stringify(frm),
	        success: function(data){

	        	
	        	operation_alert(data, function(){
	        		var str = data.result;
	        	
	                if (str.search("ORGANIZER") != -1) {
	                	
	                		
	                        window.location.href = "home?action=index";
	                } 
	                else{
	               
	                	var newData= data.comingFromCart;
	    	        	if(newData == true){
	    	        			
	    		        		window.location.href="home?action=cartCheckout";
	    	        	}
	    	        	else{
	    	        	
	    	        		window.location.href = "home?action=index";
	    	        	}
		                   
	                }
	                
	        	});
	        },
	        error: function(){	
	        	alert("Errore di richiesta al server! Riprovare.");
	        }
	    });
	});
});




