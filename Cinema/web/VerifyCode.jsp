<%-- 
    Document   : VerifyCode
    Created on : Feb 25, 2025, 3:49:12 AM
    Author     : default
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <span>WE sent</span>
        
        <form action="VerifyCode" method="post">
            <input type="text" name="authcode_2">
            <input type="submit" value="verify">
        </form>
    </body>
</html>
