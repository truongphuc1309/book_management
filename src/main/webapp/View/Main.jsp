<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <meta charset="utf-8" />
    <meta
            name="viewport"
            content="width=device-width, initial-scale=1, shrink-to-fit=no"
    />
    <link
            rel="stylesheet"
            href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css"
            integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn"
            crossorigin="anonymous"
    />
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" />
    <title>Book Management</title>
    <style>
        body{
            background-color: #6d44b8;
        }

        .purple{
            background-color: #c0a2ef !important;
        }


        nav {
            position: fixed;
            top: 0;
            right: 0;
            left: 0;
            height: 80px;
            background-color: #302b63ff;
            display: flex;
            align-items: center;
            justify-content: space-between;
            padding: 20px 20px;
            box-sizing: border-box;
            color: #ccc;
        }
        .title,
        .logout {
            font-size: 24px;
        }

        .logout {
            color: #ccc;
        }

        .main{
            margin-top: 100px !important;
        }
        .custom-input{
            border: none;
            background-color: transparent;
            color: #dee2e6;
        }

        .custom-input:focus{
            background-color: transparent;
            color: #dee2e6;
            outline-color: chartreuse;
        }
    </style>
</head>
<body>
<c:set var="user" value='${pageContext.session.getAttribute("user")}' />
<c:if test = "${user == null}">
    <c:redirect url = "/auth"/>
</c:if>
<c:set var="list" value='${requestScope["list"]}' />
<nav>
    <div class="title">Hello, <c:out value="${user.fullName}"/></div>
    <a href="<c:url value="/auth?action=logout"/>" class="logout">Log Out</a>
</nav>
<div class="main container my-3">
    <h1>Book Manager</h1>
    <hr />
    <form id="mylibraryform" action="<c:url value="/home"/>" method="POST">
        <div class="form-group">
            <label for="bookName">Book Name</label>
            <input type="text" class="form-control purple" id="bookName" name="name" required/>
        </div>
        <div class="form-group">
            <label for="author">Author Name</label>
            <input type="text" class="form-control purple" id="author" name="author" required/>
        </div>
        <div class="form-group">
            <label for="bookType">Book Type</label>
            <input type="text" class="form-control purple" id="bookType" name="type" required/>
        </div>
        <input type="text" name="action" value="create" style="display: none">
        <button type="submit" class="btn btn-primary">Add Book</button>
    </form>
    <table class="table table-dark my-3">
        <thead>
        <tr>
            <th scope="col">No</th>
            <th scope="col">Book Name</th>
            <th scope="col">Author</th>
            <th scope="col">Book Type</th>
            <th scope="col"></th>
        </tr>
        </thead>
        <tbody id="table-body">
        <c:forEach var="item" items="${list}" varStatus="loop">
            <form action="<c:url value="/home"/>" method="POST">
                <tr>
                    <td>${loop.index + 1}</td>
                    <td><input type="text" class="form-control custom-input"  name="name" value="${item.name}" required/></td>
                    <td><input type="text" class="form-control custom-input"  name="author" value="${item.author}" required/></td>
                    <td><input type="text" class="form-control custom-input"  name="type" value="${item.type}" required/></td>
                    <td>
                        <button type="submit" class="btn btn-warning"><i class="fa-solid fa-pen-to-square"></i></button>
                        <a href="<c:url value="/home?id=${item.id}&action=delete"/>" type="button" class ="dlt-btn btn-danger btn " id ="dlt-btn"><i class="fa-solid fa-trash"></i></a>
                    </td>
                </tr>
                <input type="text" name="id" value="${item.id}" style="visibility: hidden">
                <input type="text" name="action" value="update" style="visibility: hidden">
            </form>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
