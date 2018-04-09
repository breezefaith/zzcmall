<%--
  Created by IntelliJ IDEA.
  User: breezefaith
  Date: 2018/2/27
  Time: 13:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en" >

<head>
    <meta charset="UTF-8">
    <title>登录</title>


    <link rel='stylesheet prefetch' href='css/bootstrap.min.css'>
    <link rel="stylesheet" href="css/login.css">
    <script src="js/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
    <script src="js/jquery.form.min.js"></script>

</head>

<body>

<div class="wrapper">
    <form class="form-signin" action="login.do" method="post">
        <h2 class="form-signin-heading">ZZCMall</h2>
        <input type="text" class="form-control" name="username" placeholder="用户名/邮箱/手机号" required="" autofocus="" />
        <input type="password" class="form-control" name="password" placeholder="密码" required=""/>
        <p class="checkbox">
            <input type="checkbox" value="remember-me" id="rememberMe" name="rememberMe"> 记住密码
            <span style="float: right"><a href="forget-page.do">忘记密码</a></span>
        </p>
        <button class="btn btn-lg btn-primary btn-block" id="btn-login" type="submit">登录</button>
        <button class="btn btn-lg btn-primary btn-block" id="btn-register" type="button" onclick="location.href='register-page.do'">注册</button>
    </form>
    <script>
        $(function () {
            $("form.form-signin").ajaxForm({
                success:function (responseText, statusText) {
                    if(responseText.errorCode!=0){
                        $("span.span-error-note:first").remove();
                        $("input:first").before("<span class='span-error-note' style='color:red;'>用户名或密码错误</span>");
                    }else{
                        window.location.href="index.do";
                    }
                },
                error:function (XMLHttpRequest, textStatus, errorThrown) {

                },
                resetForm:true
            });
        });
    </script>
</div>



</body>

</html>
