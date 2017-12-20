<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<body>
<h2>Hello World!</h2>

<a href="${pageContext.request.contextPath }/login.action">去登陆</a>
<input type="number" style="ime-mode:disabled;" onpaste="return false;" onkeypress="keyPress()"/>
<input type="text" id="inp"/>

<!-- style:ime-mode:disabled表示不能切换输入法  -->
<!-- onpaste="return false;" 表示不能粘贴  -->

<script>
    function keyPress() {
        var keyCode = event.keyCode;
        if ((keyCode >= 48 && keyCode <= 57)) {
            event.returnValue = true;
        } else {
            event.returnValue = false;
        }
    }


    var oInp = document.getElementById('inp');
    oInp.onblur = function () {
        if (isNaN(Number(oInp.value))) {  //当输入不是数字的时候，Number后返回的值是NaN;然后用isNaN判断。
            alert('不是数字！')
        }
    }
</script>


</body>
</html>
