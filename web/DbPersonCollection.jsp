<%-- 
    Document   : DbPerson
    Created on : Apr 13, 2018, 12:08:14 PM
    Author     : alexf
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Person Collection</title>
    </head>
    <body>
        <h1>Person Collection</h1>
        <form action="DbPersonServlet">
            <input type="submit" name="action" value="Clear List"/>                
        </form>
        <p></p>
        <form action="DbPersonServlet">
            <input type="text" name="Name"/> Name
            <br><input type="text" name="Eye Color"/> Eye Color 
            <br><input type="text" name="Hair Color"/> Hair Color
            <br><input type="text" name="Height"/> Height
            <br><input type="text" name="Weight"/> Weight
            <br><input type="submit" Name="action" value="add"/>
        </form>
        <hr>
        <h3>${errorMessage}</h3>
        
        <table border="3">
            <tr><th>Name</th><th>Eye Color</th><th>Hair Color</th><th>Height</th><th>Weight</th></tr>
            
            <c:forEach var="person" items="${PersonCollection}" varStatus="loopStatus"> 
                <tr>
                <form action="DbPersonServlet">
                    <td><input type="text" name="Name" value="${person.name}"/></td>
                    <td><input type="text" name="Eye Color" value="${person.EyeColor}"/></td>
                    <td><input type="text" name="Hair Color" value="${person.HairColor}"/></td>
                    <td><input type="text" name="Height" value="${person.Height}"/></td>
                    <td><input type="text" name="Weight" value="${person.Weight}"/></td>
                    <td><input type="submit" name="action" value="remove"/>
                        <input type="submit" name="action" value="update"/>
                        <input type="hidden" name="id" value="${person.id}"/>
                    </td>
                </form>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
