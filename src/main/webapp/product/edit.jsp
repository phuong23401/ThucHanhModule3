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
    <title>EDIT PRODUCT INFOMATION</title>
</head>
<body>
<center>
    <h1>EDIT PRODUCT INFOMATION</h1>
    <form method="post">
        <a href="/product">Return Product List</a>
        <table border="1" cellpadding="5">
            <c:if test="${product != null}">
                <input type="hidden" name="id" value="<c:out value='${product.id}'/>"/>
            </c:if>
            <tr>
                <th>Product Name</th>
                <td>
                    <input type="text" name="name" value="<c:out value='${product.name}'/>"/>
                </td>
            </tr>
            <tr>
                <th>Price</th>
                <td>
                    <input type="text" name="name" value="<c:out value='${product.price}'/>"/>
                </td>
            </tr>
            <tr>
                <th>Quantity</th>
                <td>
                    <input type="text" name="name" value="<c:out value='${product.number}'/>"/>
                </td>
            </tr>
            <tr>
                <th>Color</th>
                <td>
                    <input type="text" name="name" value="<c:out value='${product.color}'/>"/>
                </td>
            </tr>
            <tr>
                <th>Description</th>
                <td>
                    <input type="text" name="name" value="<c:out value='${product.description}'/>"/>
                </td>
            </tr>
            <tr>
                <th>Category</th>
                <td>
                    <select name="cId">
                        <c:forEach items="${categoryList}" var="category">
                            <option value="${category.id}" >${category.name}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            </tr>
            <td colspan="2" align="center">
                <input type="submit" value="Save"/>
            </td>
            </tr>
        </table>
    </form>
</center>
</body>
</html>
