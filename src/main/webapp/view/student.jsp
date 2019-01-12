<%@ page import="com.neil.ecoblue.model.Account" %><%
    Account account = (Account) session.getAttribute("account");
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
            <a href="/logout" class="btn btn-outline-danger">Logout</a>
        </div>
    </div>
</nav>

<div class="container">
    <div class="row justify-content-center mt-5">
        <div class="card col-5 shadow p-3 mb-5 bg-white rounded">
            <h5 class="card-title">Account Details</h5>
            <div class="card-body">
                <ul class="list-group">
                    <li class="list-group-item"><i class="fas fa-user"></i> Student Name : <%=account.getFirstName()%> <%=account.getLastName()%></li>
                    <li class="list-group-item"><i class="fas fa-envelope"></i> Email : <%=account.getEmail()%></li>
                    <li class="list-group-item"><i class="fas fa-coins"></i> Total Points : <%=account.getTotalPoints()%></li>
                    <li class="list-group-item"><i class="fas fa-calendar-alt"></i> Date Created : <%=account.getDateCreated()%></li>
                </ul>
            </div>
        </div>
    </div>
</div>

<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
</body>
</html>