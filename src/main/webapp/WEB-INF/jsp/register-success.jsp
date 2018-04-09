<%--
  Created by IntelliJ IDEA.
  User: breezefaith
  Date: 2018/3/1
  Time: 14:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册成功</title>
</head>
<body>
    <div id="div-info">
        <h3>注册成功，3秒后跳转至首页...</h3>
    </div>
    <script>
        setTimeout(function () {
            window.location.href="index.do";
        },3000);
    </script>
</body>
</html>
