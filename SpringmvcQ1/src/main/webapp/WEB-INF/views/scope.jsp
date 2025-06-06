<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Bean Scopes</title>
</head>
<body>
<h2>Bean Scopes in Spring MVC</h2>
<ul>
    <li>Singleton: ${singleton}</li>
    <li>Prototype: ${prototype}</li>
    <li>Request: ${request}</li>
    <li>Session: ${session}</li>
</ul>
</body>
</html>
