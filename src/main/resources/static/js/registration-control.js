/**
 * Created by hardeepsingh on 2/7/17.
 */
function handleRegistration() {
    $('#rSubmit').prop("disabled", true);

    var rName = $('#rName').val();
    var rEmail = $('#rEmail').val();
    var rProvider = $('#rProvider').val();
    var rNumber = $('#rNumber').val();

    if(rName && rEmail && rProvider && rNumber) {
        $.ajax(
            {
                type : "GET",
                url  : "/processRegistration/" + rName,
                data : {
                    "rEmail"    : rEmail,
                    "rProvider" : rProvider,
                    "rNumber"   : rNumber
                },
                success : function (result) {
                    if(result === 'error') {
                        $('#rSubmit').prop("disabled", true);
                        alert("Error occurred, Try again later.");
                    } else {
                        alert("Validation code sent to provided number");
                        window.location.replace("/verificationCode");
                    }
                },
                error: function (jgHXR, exception) {
                    $('#rSubmit').prop("disabled", true);
                    alert("Failed to process data, error occurred");
                }
        });
    } else {
        $('#rSubmit').prop("disabled", true);
        alert("All input fields are required");
    }
}

function checkCode() {
    var vCode = $('#vCode').val();
    if(vCode) {
        $.ajax( {
            type : "GET",
            url  : "/validateCode/" + vCode,
            data : {},
            success : function (result) {
                if(result  === 'valid') {
                    //Going back to login page once validated
                    window.location.replace("/login");
                    alert("Account successfully validated");
                } else {
                    alert("Verification code invalid, Try again!");
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
