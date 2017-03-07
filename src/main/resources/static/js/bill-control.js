/**
 * Created by hardeepsingh on 2/15/17.
 */

//Global Variable
var gSP;
var gNumber;

function addBill() {
    var name = $('#billName').val();
    var amount = $('#billAmount').val();
    var duedate = $('#billDate').val();

    if (name && duedate) {
        $.ajax(
            {
                type: "GET",
                url: "/bill/" + name,
                data: {
                    "amount": amount,
                    "duedate": duedate
                },
                success: function (result) {
                    if (result === "error") {
                        alert("Error occurred while saving, Try again!");
                    } else {
                        alert("Data Saved!");
                        location.reload();
                    }
                },
                error: function (jgHXR, Exception) {
                    alert("Error: Couldn't process the request");
                }
            }
        )
    } else {
        alert("Please provide all the information!");
    }
}

function deleteBill(id) {
    $.ajax(
        {
            type: "DELETE",
            url: "/bill/" + id,
            data: {},
            success: function (result) {
                if (result === "error") {
                    alert("Error occurred while deleting, Try again!");
                } else {
                    location.reload();
                }
            },
            error: function (jgHXR, Exception) {
                alert("Error: Couldn't process the request");
            }
        }
    )
}

function paidBill(id) {
    $.ajax(
        {
            type: "POST",
            url: "/bill/" + id,
            data: {},
            success: function (result) {
                if (result === "error") {
                    alert("Error occurred while updating, Try again!");
                } else {
                    location.reload();
                }
            },
            error: function (jgHXR, Exception) {
                alert("Error: Couldn't process the request");
            }
        }
    )
}

function updateProfile(currentNumber) {
    var name = $('#mName').val();
    var email = $('#mEmail').val();
    var password = $('#mPassword').val();
    var confirmpassword = $('#mConfirmPassword').val();
    var number = $('#mNumber').val();
    var serviceprovider = $('#mServiceProvider').val();

    if (currentNumber != number) {
        $("#dialog").dialog();
    }


    if (password === confirmpassword) {
        $.ajax(
            {
                type: "POST",
                url: "/userupdate/" + name,
                data: {
                    "email": email,
                    "password": password,
                    "number": number,
                    "serviceprovider": serviceprovider
                },
                success: function (result) {
                    if (result === "error") {
                        alert("Error occurred while updating, Try again!");
                    } else {
                        alert("Profile Updated!!");
                        location.reload();
                    }
                },
                error: function (jgHXR, Exception) {
                    alert("Error: Couldn't process the request");
                }
            }
        )
    } else {
        alert("Password and Confirm Password do not match!!");
    }
}


//clear all bills
function clearBills() {
    $.ajax(
        {
            type: "GET",
            url: "/clearbills",
            data: {},
            success: function (result) {
                if (result === "error") {
                    console.log("Error occurred while deleting, Try again!");
                } else {
                    console.log("All Bills Deleted!!");
                    setTimeout(
                        function () {
                            location.reload();
                        }, 1500);
                }
            },
            error: function (jgHXR, Exception) {
                console.log("Error: Couldn't process the request");
            }
        }
    )
}

function sendCode() {
    var number = $('#newNumber').val();
    var sp = $("#newSP").val();

    if (number && sp) {
        $("#vNewButton").prop("disabled", true);
        $("#vNewButton").attr('value', 'Sending Code...');
        $.ajax(
            {
                type: "GET",
                url: "/validateNewNumber/" + number,
                data: {
                    "sp": sp
                },
                success: function (result) {
                    if (result === "error") {
                        console.log("Error occurred while sending code, Try again!");
                        $("#vNewButton").prop("disabled", false);
                    } else {
                        $("#vNewButton").prop('value', 'Code Sent!');
                        console.log("Verification Code Sent");
                    }
                },
                error: function (jgHXR, Exception) {
                    $("#vNewButton").prop("disabled", false);
                    console.log("Error: Couldn't process the request");
                }
            }
        )
    } else {
        alert("Please provide all the information and Try again!");
    }
}

function verifyCode() {
    var code = $('#newVCode').val();

    if(code) {
        $("#vNewCodeB").prop("disabled", true);
        $.ajax( {
            type : "GET",
            url  : "/validateCode/" + code,
            data : {},
            success : function (result) {
                if(result  === 'invalid') {
                    $("#vNewCodeB").prop("disabled", false);
                    alert("Verification code invalid, Try again!");
                } else {
                    gNumber = $('#newNumber').val();
                    gSP = $("#newSP").val();
                    $("#vNewCodeB").prop('value', 'Verified!!');
                }
            },
            error : function (jgHXR, exception) {
                $("#vNewCodeB").prop("disabled", false);
                alert("Failed to process data, Error Occurred");
            }
        });
    } else {
        alert("Please provide all the information and Try again!");
    }
}

function passData() {
    gNumber = $('#newNumber').val();
    gSP = $("#newSP").val();
    $("#mNumber").attr('value', gNumber);
}
