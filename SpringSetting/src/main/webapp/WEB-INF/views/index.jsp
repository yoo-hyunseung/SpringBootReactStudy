<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<h1>helloㅓㅓㅓsss</h1>
서버<%=application.getServerInfo() %><br>
서플릿<%= application.getMajorVersion() %>.<%= application.getMinorVersion() %><br>
jsp<%= JspFactory.getDefaultFactory().getEngineInfo().getSpecificationVersion() %>
${abc}
</body>
</html>
