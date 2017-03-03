/**
 * Created by hardeepsingh on 2/7/17.
 */
function handleRegistration() {
    //$('#rButton').prop("disabled", true);
    $("#rButton").prop("disabled",true);

    var rName = $('#rName').val();
    var rEmail = $('#rEmail').val();
    var rPassword = $('#rPassword').val();
    var rConfirmPassword = $('#rConfirmPassword').val();
    var rProvider = $('#rProvider').val();
    var rNumber = $('#rNumber').val();
    
    if(rName && rEmail && rProvider && rNumber && rPassword && rConfirmPassword) {
        if(rPassword === rConfirmPassword) {
            $.ajax(
                {
                    type : "GET",
                    url  : "/processRegistration/" + rName,
                    data : {
                        "rEmail"    : rEmail,
                        "rPassword" : rPassword,
                        "rProvider" : rProvider,
                        "rNumber"   : rNumber
                    },
                    success : function (result) {
                        if(result === 'error') {
                            $("#rButton").prop("disabled", false);
                            alert("Error occurred, Try again later.");
                        } else {
                            alert("Validation code sent to provided number");
                            window.location.replace("/verification");
                        }
                    },
                    error: function (jgHXR, exception) {
                        $("#rButton").prop("disabled", false);
                        alert("Failed to process data, error occurred");
                    }
                });
        } else {
            $("#rButton").prop("disabled", false);
            alert("Password fields do not match, Try again!");
        }
    } else {
        $("#rButton").prop("disabled", false);
        alert("All input fields are required");
    }
}

function checkCode() {
    var vCode = $('#verificationCode').val();
    if(vCode) {
        $.ajax( {
            type : "GET",
            url  : "/validateCode/" + vCode,
            data : {},
            success : function (result) {
                if(result  === 'invalid') {
                    alert("Verification code invalid, Try again!");
                } else {
                    //Going back to login page once validated
                    window.location.replace("/home");
                    alert(result + " : Account successfully validated");
                }
            },
            error : function (jgHXR, exception) {
                alert("Failed to process data, Error Occurred");
            }
        });
    } else {
        alert("Please enter the code");
    }
}

