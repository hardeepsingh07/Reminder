// This is the version used for regular HTML + FreeMarker with jQuery

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
    var uName = $('#rUserName').val();
    if(uName) {
        $.ajax(
            {
                type : "GET",
                url  : "/cs580/reminder/" + uName,
                data : {
                },
                success : function (result) {
                    $('#checkResult').text(result)

                },
                error: function (jqXHR, exception) {
                    alert("Failed to get validation");
                }
            });
    } else {
        alert("Username field is empty");
    }
}