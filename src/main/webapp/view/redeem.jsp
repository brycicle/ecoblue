<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.neil.ecoblue.model.Account" %>
<%@ page import="java.util.List" %>
<%@ page import="com.neil.ecoblue.model.Redeem" %>
<%
    Account account = (Account) session.getAttribute("account");
    List<Redeem> list = (List<Redeem>)request.getAttribute("items");
%>
<!doctype html>
<html lang="en">
<head>
    <title>Title</title>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css" integrity="sha384-UHRtZLI+pbxtHCWp1t77Bi1L4ZtiqrqD80Kn4Z8NTSRyMA2Fd33n5dQ8lWUE00s/" crossorigin="anonymous">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-light bg-light shadow">
    <a href="/login" class="navbar-brand">Ecoblue</a>
    <button class="navbar-toggler" data-target="#my-nav" data-toggle="collapse">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div id="my-nav" class="collapse navbar-collapse">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="/convert">Convert</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/redeem">Redeem</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/history">History</a>
            </li>
        </ul>
        <div class="form-inline my-2 my-lg-0">
            <span class="badge badge-pill badge-warning mr-3"><i class="fas fa-coins"></i> <%=account.getTotalPoints()%></span>
            <input type="hidden" id="accountPoints" name="accountPoints" value="<%=account.getTotalPoints()%>"/>
            <a href="/logout" class="btn btn-outline-danger">Logout</a>
        </div>
    </div>
</nav>

<div class="container">
    <div class="row justify-content-center m-5">
        <div class="card col-6 shadow p-3 mb-5 bg-white rounded">
            <h5 class="card-title">Redeem</h5>
            <div class="card-body">
                <form action="/redeem" method="POST">
                    <div class="form-row">
                        <div class="col-md-3 mb-3">
                            <label for="qty"># of Item</label>
                            <input type="number" min="0" class="form-control" name="qty" id="qty" value="1" required>
                        </div>
                        <div class="col-md-9 mb-3">
                            <label for="itemType">Item Type</label>
                            <select class="custom-select" name="itemType" id="itemType" required>
                                <c:forEach var="redeem" items="${items}">
                                    <option class="itemOptions" value="${redeem.redeemId}">${redeem.redeemName}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="col-md-6mb-3">
                            <label>Total Points to Spend :</label>
                        </div>
                        <div class="col-md-6 mb-3">
                            <input type="text" id="totalPoints" name="totalPoints" value="69" disabled>
                        </div>
                        <div class="col-md-12">
                            <button id="btnSubmit" type="submit" class="btn btn-block btn-success">Convert</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
        <ul class="list-group invisible">
            <c:forEach var="redeem" items="${items}">
                <li class="list-group-item itemList" value="${redeem.redeemValue}">
                </li>
            </c:forEach>
        </ul>
    </div>
</div>
<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>

<script>
    checkPrice();
    $("#qty").change(function() {
        var items = document.getElementsByClassName('itemList');
        $("#totalPoints").val($("#qty").val() * items[$("#itemType").val()-1].value);
        checkPrice();
    });
    function checkPrice(){
        if( parseFloat($("#totalPoints").val()) > parseFloat($("#accountPoints").val())){
            $("#btnSubmit").addClass("disabled");
        }
        else{
            $("#btnSubmit").removeClass("disabled");
        }
    }
    $("#itemType").change(function() {
        var items = document.getElementsByClassName('itemList');
        $("#totalPoints").val($("#qty").val() * items[$("#itemType").val()-1].value);
        checkPrice();
    });
</script>
</body>
</html>