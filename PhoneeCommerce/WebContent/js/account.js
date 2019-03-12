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

$( document ).ready(function() {
	
	$("#sign-up").submit(function(e) {
	    e.preventDefault();
	    alert("ciao");
	    var frm = $(this).serializeFormJSON();
	    console.log(frm);
	    $.ajax({
	        url: "account?action=signup",
	        type: "POST",
	        dataType: "JSON",
	        data: JSON.stringify(frm)
	    }).done(function(data) {
	        operation_alert(data, function() {
	            window.location.href = ""; //home

	        });

	    }).fail(function(data, status, err) {
	        alert("error: " + data + " status: " + status + " err: " + err);
	    });
	});
});




