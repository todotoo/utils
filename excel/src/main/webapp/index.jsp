<%@ page language="java" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">
    <title>POI</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <!--
    <link rel="stylesheet" type="text/css" href="styles.css">
    -->
    <script type="text/javascript">
        function formSubmit(mapping) {
            var postForm = document.getElementById("excelForm");
            postForm.action = "<%=basePath%>" + mapping;
            postForm.submit();
        }
    </script>
</head>
<body>
<h2>POI</h2>
<hr>
<br>
<form id="excelForm" action="excel/readExcel.action" method="post" enctype="multipart/form-data">
    <input type="file" name="file" value="请选择excel文件">
    <br>
    <button type="submit">提交</button>
</form>
</body>
</html>
