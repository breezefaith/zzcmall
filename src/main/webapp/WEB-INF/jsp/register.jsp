<%--
  Created by IntelliJ IDEA.
  User: breezefaith
  Date: 2018/2/27
  Time: 13:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册</title>
    <link rel='stylesheet prefetch' href='css/bootstrap.min.css'>
    <link rel="stylesheet" href="css/register.css">
    <script src="js/jquery-3.3.1.min.js"></script>
    <script src="js/jquery.form.min.js"></script>
</head>
<body>
<div class="wrapper">
    <form class="form-signin" method="post" action="register.do"  onsubmit="return check()">
        <h2 class="form-signin-heading">注册</h2>
        <input type="text" class="form-control" name="username" placeholder="用户名" required="" autofocus="" />
        <input type="password" class="form-control" id="password" name="password" placeholder="密码" required=""/>
        <input type="password" class="form-control" id="confirm-password" name="confirm_password" placeholder="确认密码" required=""/>
        <input type="text" class="form-control" name="phone" id="phone" placeholder="11位手机号" required=""/>
        <input type="text" class="form-control" name="email" placeholder="邮箱" required=""/>
        <input class="btn btn-lg btn-primary btn-block btn-register" type="submit" value="注册">
    </form>
</div>

<script>
    function check() {
        if($('#password').val()!=$('#confirm-password').val()){
            $("span.span-error-note").remove();
            $("#confirm-password").after("<span class='span-error-note' style='font-size:0.5em;color:red;'>两次密码不一致</span>");
            return false;
        }
        if(/^[1][3,4,5,7,8][0-9]{9}$/.test($("#phone").val())==false){
            $("span.span-error-note").remove();
            $("#phone").after("<span class='span-error-note' style='font-size:0.5em;color:red;'>手机号格式不正确</span>");
            return false;
        }
        return true;
    }
    $(function () {
        $("form").ajaxForm({
            success:function (responseText, statusText) {
                if(responseText.success==false){
                    alert("注册失败，请确认信息后重试！");
                }else{

                    window.location.href="register-success.do";
                }
            },
            error:function (XMLHttpRequest, textStatus, errorThrown) {

            },
            resetForm:false
        });
    });
</script>

</body>
</html>
