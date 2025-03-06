<%-- 
    Document   : VerifyCode
    Created on : Feb 25, 2025, 3:49:12 AM
    Author     : default
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <span>WE sent</span>

    <c:if test="${not empty mess}">
        <p style="color: red;">${mess}</p>
    </c:if>
    <form action="verify_otp" method="post">
        <input type="text" name="otp" placeholder="Enter OTP" required>
        <button type="submit">Verify</button>
    </form>
</body>
</html>
