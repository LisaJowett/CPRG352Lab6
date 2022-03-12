<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Users</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    </head>
    <body>
        <div class="form">
                <div class="row" > 
                    <div class="col">
                    <form action="user" method="post">
                        <h2>Add User</h2>
                        <input type="hidden" name="action" value="add">
                        <label for="addEmail">Email:</label> 
                        <input type="text" name="addEmail" id="addEmail"><br>
                        <label for="addFirstName">First Name:</label> 
                        <input type="text" name="addFirstName" id="addFirstName"><br>
                        <label for="addLastName">Last Name:</label> 
                        <input type="text" name="addLastName" id="addLastName"><br>
                        <label for="addPassword">Password:</label> 
                        <input type="text" name="addPassword" id="addPassword"><br>
                        <label for="addRoleName">Role:</label> 
                        <select name="addRoleName" id="addRoleName">
                            <option value="Regular User">Regular User</option>
                            <option value="Company Admin">Company Admin</option>
                            <option value="System Admin">System Admin</option>
                        </select>

                        <button type="submit">Save</button>
                    </form>
                </div>
            
            <div class="row">
                <div class="col">
                    <h1>Lisa's User Management System</h1>
                    <table class="table">
                        <thead>
                            <tr>
                                <th>E-mail</th>
                                <th>First Name</th>
                                <th>Last Name</th>
                                <th>Active</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="user" items="${users}">
                                <tr>
                                    <td>${user.email}</td>
                                    <td>${user.firstName}</td>
                                    <td>${user.lastName}</td>
                                    <td>${user.active ? "Y" : "N"}</td>
                                    <td>
                                        <a href="user?action=edit&email=${user.email}">Edit</a>
                                        <a href="user?action=delete&email=${user.email}">Delete</a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>    
            </div>
                    <div class="form">
                <div class="row" > 
                    <div class="col">
                    <form action="user" method="post">
                        <h2>Edit User</h2>
                        <input type="hidden" name="action" value="add">
                        <label for="editEmail">Email:</label> 
                        <input type="text" name="editEmail" id="editEmail"><br>
                        <label for="editFirstName">First Name:</label> 
                        <input type="text" name="editFirstName" id="editFirstName"><br>
                        <label for="editLastName">Last Name:</label> 
                        <input type="text" name="editLastName" id="editLastName"><br>
                        <label for="editPassword">Password:</label> 
                        <input type="text" name="editPassword" id="editPassword"><br>
                        <label for="editRoleName">Role:</label> 
                        <select name="editRoleName" id="editRoleName">
                            <option value="Regular User">Regular User</option>
                            <option value="Company Admin">Company Admin</option>
                            <option value="System Admin">System Admin</option>
                        </select>

                        <button type="submit">Save</button>
                    </form>
                </div>
                    <p>${message}</p>
        </div>
        
    </body>
</html>
