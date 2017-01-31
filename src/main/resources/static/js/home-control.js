// This is the version used for regular HTML + FreeMarker with jQuery
//show date picker
function healthCheck() {
	$.ajax(
			{
				type : "GET",
				url  : "/cs580/ping",
				data : {
				},
				success : function(result) {
					$('#status').text(result);
				},
				error: function (jqXHR, exception) {
					$('#status').text("Failed to get the status");
				}
			});
}

function deleteUser(userId) {
	$.ajax(
			{
				type : "DELETE",
				url  : "/cs580/user/" + userId,
				data : {
				},
				success : function(result) {
					location.reload();
				},
				error: function (jqXHR, exception) {
					alert("Failed to delete the photo.");
				}
			});
}

function addUser() {

	var userId = $('#input_id').val();
	var userName = $('#input_name').val();
	var userMajor = $('#input_major').val();

	if (userId) {
		$.ajax(
				{
					type : "POST",
					url  : "/cs580/user/" + userId,
					data : {
						"name" : userName,
						"major" : userMajor
					},
					success : function(result) {
						location.reload();
					},
					error: function (jqXHR, exception) {
						alert("Failed to add the user. Please check the inputs.");
					}
				});
	} else {
		alert("Invalid user Id");
	}
}

function getUser(userId) {
	var userId = $('#query_id').val();
	if (userId) {
		$.ajax(
				{
					type : "GET",
					url  : "/cs580/user/" + userId,
					data : {
					},
					success : function(result) {
						$('#result_id').text(result.id);
						$('#result_name').text(result.name);
						$('#result_major').text(result.major);
					},
					error: function (jqXHR, exception) {
						alert("Failed to get the user.");
					}
				});
	} else {
		alert("Invalid user Id");
	}
}


//Reminder Group
//Hardeep
function validateInput(uName) {
    var uName = $('#username').val();
    if(uName) {
        $.ajax(
            {
                type : "GET",
                url  : "/valid/" + uName,
                data : {
                },
                success : function (result) {
                    alert("Username: " + uName + " is " + result);
                    //$('#checkResult').text(result);

                },
                error: function (jqXHR, exception) {
                    alert("Failed to get validation");
                }
            });
    } else {
        alert("Username field is empty");
    }
}

function encrypt() {
    var password = $('#password').val();
    if(password) {
        $.ajax(
            {
                type : "GET",
                url  : "/encrypt/" + password,
                data : {
                },
                success : function (result) {
                    alert(result)
                },
                error: function (jqXHR, exception) {
                    alert("Failed to get encrypt");
                }
            });
    } else {
        alert("Password field is empty");
    }
}

function sendSMS() {
    $('#send').prop("disabled", true);
    var number = $('#number').val();
    var provider = $('#networkProvider').val();
    var subject = $('#subject').val();
    var message = $('#message').val();

    //alert("JQuery: " + number + provider + message + subject);
    if (number && provider && message) {
        $.ajax(
            {
                type : "GET",
                url  : "/processSMS/" + number,
                data : {
                    "provider" : provider,
                    "subject"  : subject,
                    "message"  : message
                },
                success : function(result) {
                    if(result === 'error') {
                        $('#send').prop("disabled", false);
                        alert("Error: Please information and try again.")
                    } else {
                        //Reset fields
                        $('#number').val("");
                        $('#subject').val("");
                        $('#message').val("");

                        //Show success alert
                        alert("Text successfully sent to " + number);
                        $('#send').prop("disabled", false);
                    }
                },
                error: function (jqXHR, exception) {
                    $('#send').prop("disabled", false);
                    alert("Failed to send text. Try again later");
                }
            });
    } else {
        alert("Input fields required");
    }
}