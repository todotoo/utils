<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>My JSP 'form2.jsp' starting page</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <!--
    <link rel="stylesheet" type="text/css" href="styles.css">
    -->
</head>

<body>
<h1>SpringMVC实现上传</h1>
<h3>${msg }</h3>
<form action="<c:url value='/fileUpload/upload.action'/>" method="post" enctype="multipart/form-data">
    <%--用户名；<input type="text" name="username"/><br/>--%>
    照　片：<input type="file" name="image"/><br/>
    <input type="submit" value="上传"/>
</form>
</body>
</html>
