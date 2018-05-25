<%--
  Created by IntelliJ IDEA.
  User: breezefaith
  Date: 2018/5/24
  Time: 19:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>后台管理系统</title>
    <link rel='stylesheet prefetch' href='../css/bootstrap.min.css'>
    <link rel="stylesheet" href="../css/login.css">
    <script src="../js/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="../js/bootstrap.min.js"></script>
</head>
<body>
<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <a href="#" class="navbar-brand navbar-left">
                <img src="../image/logo2.png" style="max-width:100px;margin-top:-4px;"/>
            </a>
        </div>
        <div>
            <ul class="nav navbar-nav">
                <li><a href="items.do">商品管理</a></li>
                <li><a href="records.do">订单管理</a></li>
            </ul>
        </div>
        <div style="float:right;" class="login-state">

        </div>
    </div>
</nav>

<div class="container-fluid">
    <div class="row-fluid">
        <div class="col-md-2">
            <ul>
                <li>侧边栏</li>
            </ul>
        </div>
        <div class="col-md-10">

        </div>
    </div>
    <div class="row-fluid">
        <footer class="footer">
            <div class="center-block" style="text-align: center;">
                Designed By ZZCoder
            </div>
        </footer>
    </div>
</div>

<!--模态框设计-->
<div>
    <div class="modal fade" id="modal-update-person-info" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                        提示
                    </div>
                    <div class="modal-body">

                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script>
    $(function () {
        $.ajax({
            url:'isLogged.do',
            type:'get',
            dataType:"json",
            success:function (responseText,statusText) {
                if(responseText.success==true){
                    $("div.login-state").html("<a href='logout.do'>注销</a>");
                    window.location.href="records.do";
                }else{
                    window.location.href="login-page.do";
                }
            },
            error:function (XMLHttpRequest,textStatus) {

            }
        });
    });
</script>

</body>
</html>