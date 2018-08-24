<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">

    <title>My JSP 'index.jsp' starting page</title>
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
This is my JSP page. <br>
<a href="upload1.jsp">上传1</a> <br>
<a href="upload2.jsp">上传2</a> <br>
<a href="upload3.jsp">上传3</a> <br>
<a href="upload4.jsp">上传4</a> <br>
<a href="SWFUploadPic.jsp">上传5</a>

<hr />
<a href="<c:url value='/download/xkl.jpg'/>">下载jpg</a>
<br />
<a href="<c:url value='/download/test.csv'/>">下载csv</a>
<br />
</body>
</html>
