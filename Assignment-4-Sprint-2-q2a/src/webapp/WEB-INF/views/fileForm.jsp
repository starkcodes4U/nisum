<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><title>File Upload</title></head>
<body>
  <h2>Upload a File</h2>
  <form action="doUpload" method="post" enctype="multipart/form-data">
    Select file: <input type="file" name="file" /><br>
    <input type="submit" value="Upload" />
  </form>
  <a href="<c:url value='/' />">Back to Form</a>
</body>
</html>
