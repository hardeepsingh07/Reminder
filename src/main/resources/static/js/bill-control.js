/**
 * Created by hardeepsingh on 2/15/17.
 */
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
                    // alert("Bill Deleted!");
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
                    // alert("Bill Updated!");
                    location.reload();
                }
            },
            error: function (jgHXR, Exception) {
                alert("Error: Couldn't process the request");
            }
        }
    )
}

//Date Picker
$(function () {
    $(".date").datepicker();
});