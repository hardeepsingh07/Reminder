<!DOCTYPE html>
<head>
    <script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <script src="/js/bill-control.js"></script>
    <title>Add/Delete Bill</title>
</head>
<body>

<div>
    <input type="text" id="billName" placeholder="Bill Name"/>
    <input type="text" id="billAmount" placeholder="Bill Amount"/>
    <input type="text" id="billDueDate" placeholder="Bill Due Date"/>
    <button onclick="addBill()">Add Bill</button>
</div>

<div>
    <div>
        <label>Bill List</label>
        <table border="1">
            <tr>
                <td>Id</td>
                <td>Name</td>
                <td>Amount</td>
                <td>Due Date</td>
                <td>Status</td>
            </tr>
            <#list bills as bill>
                <tr>
                    <td>${bill.id}</td>
                    <td>${bill.name}</td>
                    <td>${bill.amount}</td>
                    <td>${bill.duedate}</td>
                    <td>${bill.status?string('true', 'false')}</td>
                    <td><button onclick="paidBill('${bill.id}')">Paid</button> </td>
                    <td><button onclick="deleteBill('${bill.id}')">Delete</button></td>
                </tr>
            </#list>
        </table>
    </div>

</body>
</html>