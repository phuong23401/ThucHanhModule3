<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 6/8/2021
  Time: 3:11 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>SEARCH RESULT</title>
</head>
<body>
<center>
    <form method="post">
        <a href="/product">Return Product List</a>
        <table border="1" cellpadding="5">
            <tr>
                <th>ID</th>
                <th>Product Name</th>
                <th>Price</th>
                <th>Quantity</th>
                <th>Color</th>
                <th>Description</th>
                <th>Category</th>
            </tr>
            <c:forEach var="product" items="${productList}">
                <tr>
                    <td><c:out value="${product.id}"/></td>
                    <td><c:out value="${product.name}"/></td>
                    <td><c:out value="${product.price}"/></td>
                    <td><c:out value="${product.number}"/></td>
                    <td><c:out value="${product.color}"/></td>
                    <td><c:out value="${product.category.name}"/></td>
                </tr>
            </c:forEach>
        </table>
    </form>
</center>
</body>
</html>
