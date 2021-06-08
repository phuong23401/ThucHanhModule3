<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 6/8/2021
  Time: 1:38 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>PRODUCT LIST</title>
</head>
<body>
<center>
    <h1>PRODUCT LIST</h1>
    <form method="post">
        <input type="text" name="search" placeholder="Search by product name"/>
        <button>
            <a href="/product?action=search">Search</a>
        </button>
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
                    <td>
                        <a href="/product?action=edit&id=${product.id}">Edit</a>
                        <a href="/product?action=delete&id=${product.id}">Delete</a>
                    </td>
                </tr>
            </c:forEach>
            <tr>
                <a href="/product?action=create">Add new product</a>
            </tr>
        </table>
    </form>
</center>
</body>
</html>
