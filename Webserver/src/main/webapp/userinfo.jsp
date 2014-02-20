<%-- 
    Document   : index
    Created on : 20-Jan-2013, 22:16:54
    Author     : anton
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Information</title>
    </head>
    <body>
        <h1>User Information</h1>
        <form action="./UserServlet" method="POST">
            <table>
                <tr>
                    <td>User ID</td>
                    <td><input type="text" name="userId" value="${user.userId}" /></td>
                </tr>
                <tr>
                    <td>Username</td>
                    <td><input type="text" name="username" value="${user.username}" /></td>
                </tr>
                <tr>
                    <td>Password</td>
                    <td><input type="text" name="password" value="${user.password}" /></td>
                </tr>
                <tr>
                    <td>Role</td>
                    <td><input type="text" name="role" value="${user.role}" /></td>
                </tr>
                <tr>
                    <td colspan="2">
                        <input type="submit" name="action" value="Add" />
                        <input type="submit" name="action" value="Edit" />
                        <input type="submit" name="action" value="Delete" />
                        <input type="submit" name="action" value="Search" />
                    </td>                
                </tr>            
            </table>
        </form>        
        <br>
        <table border="1">
            <th>User ID</th>
            <th>Username</th>
            <th>Password</th>
            <th>Role</th>
            <c:forEach items="${allUsers}" var="user">
                <tr>
                    <td>${user.userId}</td>
                    <td>${user.username}</td>
                    <td>${user.password}</td>
                    <td>${user.role}</td>
                </tr>
            </c:forEach>
        </table>  
    </body>
</html>
