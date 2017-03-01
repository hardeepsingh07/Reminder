<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <meta name="description" content=""/>
    <meta name="author" content=""/>

    <title>SB Admin - Bootstrap Admin Template</title>

    <!-- Bootstrap Core CSS -->
    <link href="/css/main/bootstrap.min.css" rel="stylesheet"/>

    <!-- Custom CSS -->
    <link href="/css/main/sb-admin.css" rel="stylesheet"/>
    <link href="/css/main/maincss.css" rel="stylesheet"/>


    <!-- Custom Fonts -->
    <link href="/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

    <!-- jQuery -->
    <script src="/js/main/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="/js/main/bootstrap.min.js"></script>
    <script src="/js/bill-control.js"></script>

    <!-- Include jQuery -->
    <script type="text/javascript" src="https://code.jquery.com/jquery-1.11.3.min.js"></script>

    <!-- Include Date Range Picker -->
    <script type="text/javascript"
            src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/js/bootstrap-datepicker.min.js"></script>
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/css/bootstrap-datepicker3.css"/>
</head>

<body>

<div id="wrapper">
    <!-- Navigation -->
    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="">Reminder</a>
        </div>

        <!-- Top Menu Items -->
        <ul class="nav navbar-right top-nav">
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i
                        class="fa fa-user"></i> ${currentuser.name} <b
                        class="caret"></b></a>
                <ul class="dropdown-menu">
                    <#--<li>-->
                        <#--<a><i class="fa fa-fw fa-user"></i>-->
                            <#--Profile-->
                        <#--</a>-->
                        <#--<button class="btn btn-primary btn-xs" data-toggle="modal"-->
                                <#--data-target="#myModalNorm">-->
                            <#--Launch Normal Form-->
                        <#--</button>-->
                    <#--</li>-->
                    <#--<li class="divider"></li>-->
                    <li>
                        <a href="" id="logout"><i class="fa fa-fw fa-power-off"></i> Log Out</a>
                        <script type="text/javascript">
                            document.getElementById("logout").onclick = function () {
                                location.href = "/logout";
                            }; </script>
                    </li>
                </ul>
            </li>
        </ul>

        <!-- Sidebar Menu Items - These collapse to the responsive navigation menu on small screens -->
        <div class="collapse navbar-collapse navbar-ex1-collapse">
            <ul class="nav navbar-nav side-nav">
                <li class="active">
                    <a href="/update"><i class="fa fa-fw fa-dashboard"></i>Dashboard</a>
                </li>
                <li>
                    <a data-toggle="modal" data-target="#myModalNorm"><i class="fa fa-fw fa-bar-chart-o"></i>Profile</a>
                </li>
                <li>
                    <a href="/registration"><i class="fa fa-fw fa-table"></i>Registration</a>
                </li>
            </ul>
        </div>
    </nav>

    <#--update profile model-->
    <div class="modal fade" id="myModalNorm" tabindex="-1" role="dialog"
         aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <!-- Modal Header -->
                <div class="modal-header">
                    <button type="button" class="close"
                            data-dismiss="modal">
                        <span aria-hidden="true">&times;</span>
                        <span class="sr-only">Close</span>
                    </button>
                    <h4 class="modal-title" id="myModalLabel">
                    ${currentuser.name}'s Profile
                    </h4>
                </div>

                <!-- Modal Body -->
                <div class="modal-body">

                    <form role="form">
                        <div class="row">
                            <div class="col-lg-6">
                                <div class="form-group">
                                    <label for="nName">Name</label>
                                    <input type="text" class="form-control"
                                           id="mName" placeholder="Enter Name"
                                           value="${currentuser.name}"/>
                                </div>

                                <div class="form-group">
                                    <label for="mEmail">Email address</label>
                                    <input type="email" class="form-control"
                                           id="mEmail" placeholder="Enter Email"
                                           value="${currentuser.email}"/>
                                </div>
                                <div class="form-group">
                                    <label for="mPassword">Password</label>
                                    <input type="password" class="form-control"
                                           id="mPassword" placeholder="Enter Password"/>
                                </div>
                                <div class="form-group">
                                    <label for="mConfirmPassword">Confirm Password</label>
                                    <input type="password" class="form-control"
                                           id="mConfirmPassword" placeholder="Confirm Password"/>
                                </div>
                            </div>

                            <div class="col-lg-6">
                                <div class="form-group">
                                    <label for="mNumber">Number</label>
                                    <input type="number" class="form-control"
                                           id="mNumber" placeholder="Enter Mobile Number"
                                           value="${currentuser.number}"/>
                                </div>
                                <div class="form-group">
                                    <label for="mServiceProvider">Password</label>
                                    <select class="form-control" id="mServiceProvider">
                                        <option>Verizon</option>
                                        <option>Sprint</option>
                                        <option>ATT</option>
                                        <option>TMobile</option>
                                        <option>Virgin Mobile</option>
                                    </select>
                                </div>

                                <div class="form-group">
                                    <button class="btn btn-danger btn-md btn-block"
                                            onclick="clearBills()">Clear All Bills
                                    </button>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>

                <!-- Modal Footer -->
                <div class="modal-footer">
                    <button type="button" class="btn btn-default"
                            data-dismiss="modal">
                        Close
                    </button>
                    <button type="button" class="btn btn-primary" onclick="updateProfile()">
                        Save changes
                    </button>
                </div>
            </div>
        </div>
    </div>

    <div id="page-wrapper">

        <div class="container-fluid">

            <!-- Page Heading -->
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">
                        Dashboard
                    </h1>
                </div>
            </div>
            <!-- /.row -->

            <div class="row">
                <div class="col-lg-6">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <h3 class="panel-title">Add Bill</h3>
                        </div>
                        <div class="panel-body">
                            <div class="col-lg-6">
                                <div class="form-group input-group">
                                    <label>Bill Name</label>
                                    <input id="billName" class="form-control"
                                           placeholder="Bill Name/Description">
                                </div>

                                <div class="form-group input-group">
                                    <label>Bill Amount</label>
                                    <input id="billAmount" class="form-control" placeholder="Bill Amount $">
                                </div>

                                <div class="form-group input-group">
                                    <label>Bill Due Date</label>
                                    <input id="billDate" class="form-control"
                                           placeholder="Select from Calender">
                                </div>

                                <div class="form-group">
                                    <button class="btn btn-success btn-block" type="button"
                                            onclick="addBill()">
                                        Add Bill!
                                    </button>
                                </div>
                            </div>

                            <div class="col-lg-6">
                                <div class="date"></div>
                                <script>
                                    $('.date').datepicker({
                                        format: 'mm/dd/yyyy',
                                        todayHighlight: true,
                                        startDate: '+0d'
                                    });

                                    $('.date').on('changeDate', function () {
                                        $('#billDate').val(
                                                $('.date').datepicker('getFormattedDate')
                                        );
                                    });
                                </script>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-lg-6">
                    <h2>Bills</h2>
                    <div class="table-responsive">
                        <table class="table table-bordered table-hover table-striped">
                            <thead align="center">
                            <tr align="center">
                                <th>Bill Name</th>
                                <th>Amount</th>
                                <th>Due Date</th>
                                <th>Status</th>
                                <th colspan="2">Action</th>
                            </tr>
                            </thead>
                            <tbody align="center" valign="center">
                            <#list bills as bill>
                            <tr>
                            <#--<td>${bill.id}</td>-->
                                <td>${bill.name}</td>
                                <td>$${bill.amount}</td>
                                <td>${bill.duedate}</td>
                                <td>${bill.status?string('Paid', 'Unpaid')}</td>
                                <td>
                                    <button class="btn-success btn-xs" onclick="paidBill('${bill.id}')">Paid</button>
                                </td>
                                <td>
                                    <button class="btn-danger btn-xs" onclick="deleteBill('${bill.id}')">Delete</button>
                                </td>
                            </tr>
                            </#list>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>

        </div>
    </div>
</div>
</body>
</html>
