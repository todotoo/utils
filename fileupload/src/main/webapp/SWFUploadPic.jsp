<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>My JSP 'form2.jsp' starting page</title>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <!--
    <link rel="stylesheet" type="text/css" href="styles.css">
    -->

    <script type="text/javascript" src="${pageContext.request.contextPath}/asset/js/jquery-1.10.2.js"></script>
    <script type="text/javascript" src="/asset/js/control/SWFUpload/swfupload.js"></script>
    <script type="text/javascript" src="/asset/js/common/uploadImage.js"></script>
</head>

<body>
<h1>SWFUpload实现上传</h1>
<h3>${msg }</h3>
    照　片：
        <%--<div id="swfu-placeholder"></div><!--swfupload文件选择按钮占位符，执行下面的js后，这里将被替换成swfupload上传按钮-->
        <div><input type="button" onclick="swfu.startUpload();" value="上传" /></div>--%>
        <input type="button" value="浏览..."  id="thumbnailBtn">
        <input type="hidden" id="thumbnail" name="thumbnail" value="${items.pic}" >
        <img id="thumbnail_img" src="" width=100 height=100 />
        <%--
        <div class="pic-show" id="goodthumbmailImage">
            <div id="goodparemes">
                <ul>
                    <li class="alert"><img id="thumbnail_img" src="${(thumbnail_img)}" /><a href="#" onclick="delThubnailImage()" class="close" data-dismiss="alert">&times;</a> </li>
                </ul>
            </div>
        </div>
        --%>
</body>
<script>
    $(document).ready(function () {
        thumbnailUploadImg("thumbnailBtn");
    });

    //按钮绑定图片上传事件
    function thumbnailUploadImg(button_id) {
        initUploadImage(thumbnailShowImg, "commodity_icon_img_type", button_id, 100);
    }
    //图片上传成功业务处理事件
    function thumbnailShowImg(data) {
        var res = eval('(' + data + ')');
        if (res.status == "0") {
            $("#goodthumbmailImage").empty();
            $("#delthumbImage").val("");
            $("#thumbnail").val(res.path);		//path  图片地址用于数据库存储
            $("#thumbnail_img").attr("src", "/pic/" + res.imgPath);	//imgPath 用户图片显示
            /*$('<ul>'
             + '<li class="alert"><img id="thumbnail_img" src="' + res.imgPath.replace("_size", "_400X400") + '" />'
             + '<a href="#"  onclick="delThubnailImage()" class="close" data-dismiss="alert">&times;</a> </li>'
             + '</ul>').appendTo('#goodthumbmailImage');*/
        }
        //上传成功
        alert(res.infoStr);
    }
</script>
</html>
