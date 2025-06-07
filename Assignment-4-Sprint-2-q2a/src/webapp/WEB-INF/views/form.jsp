<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><title>Simple Form</title></head>
<body>
  <h2>Enter User Details</h2>
  <form action="submitForm" method="post">
    Name: <input type="text" name="name" /><br>
    Age:  <input type="number" name="age" /><br>
    <input type="submit" value="Submit" />
  </form>
  <hr>
  <a href="upload">Go to File Upload</a><br>
  <a href="userJson?name=Bob&age=25">Get JSON Response (userJson)</a><br>
  <a href="redirectExample">Redirect to Form</a>
</body>
</html>
