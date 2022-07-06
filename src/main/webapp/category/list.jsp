<%--
  Created by IntelliJ IDEA.
  User: Tran Hiep
  Date: 05/07/2022
  Time: 8:16 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:forEach var="product" items="${products}">
  <h1>${product.name}</h1>
</c:forEach>
</body>
</html>
