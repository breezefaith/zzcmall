<%@ page import="cn.breezefaith.entity.Item" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: breezefaith
  Date: 2018/2/27
  Time: 13:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ZZCMall</title>
    <meta charset="utf-8">
    <link rel='stylesheet prefetch' href='css/bootstrap.min.css'>
    <link rel="stylesheet" href="css/login.css">
    <script src="js/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
    <script src="js/jquery.form.min.js"></script>

    <style type="text/css">
        body{
            padding-top: 60px;
            padding-bottom: 30px;
        }
    </style>
</head>
<body>
<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <a href="#" class="navbar-brand navbar-left">
                <img src="image/logo2.png" style="max-width:100px;margin-top:-4px;cursor: pointer;" href="index.do"/>
            </a>
        </div>
        <div>
            <ul class="nav navbar-nav">
                <li class=""><a href="index.do">全部</a></li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        分类
                        <b class="caret"></b>
                    </a>
                    <ul class="dropdown-menu">
                        <li><a href="#">百货</a></li>
                        <li><a href="#">服饰</a></li>
                        <li><a href="#">数码</a></li>
                        <li class="divider"></li>
                        <li><a href="#">分离的链接</a></li>
                        <li class="divider"></li>
                        <li><a href="#">另一个分离的链接</a></li>
                    </ul>
                </li>
                <li><a href="cart.do">购物车</a></li>
                <li><a href="#">订单</a></li>
            </ul>
        </div>
        <div style="float:right;" class="login-state">
            <a href="login-page.do" class="navbar-text login-or-person">登录</a>
            <a href="register-page.do" class="navbar-text register-or-logout">注册</a>
        </div>
    </div>
</nav>

<div class="container-fluid">
    <div class="row-fluid">
        <div class="col-md-2">
            <ul>
                <li>百货</li>
                <li>服饰</li>
                <li>数码</li>
            </ul>
        </div>
        <div class="col-md-10">

                <%
                for (Item item:(List<Item>) request.getAttribute("itemList")){%>
                    <%--<div class="col-sm-6 col-md-3">--%>
                    <div class="col-sm-6 col-md-3">
                        <div class="thumbnail div-item" style="cursor:pointer;">
                            <input type="hidden" value="<%=item.getIid()%>">
                            <img style="height: 30%;width: 90%;" src="<%=item.getItemImage()%>" alt="商品图片">
                            <div class="caption">
                                <h4><%=item.getItemName()%></h4>
                                <p>
                                    <a href="#" class="btn btn-default" role="button">
                                    <%=item.getItemCategory()%>
                                    </a>
                                    <a href="#" class="btn btn-default" role="button">
                                    ￥<%=item.getItemPrice()%>
                                    </a>
                                </p>
                            </div>
                        </div>
                    </div>
                <%}%>
                <%--<div class="thumbnail">--%>
                    <%--<img src="image/logo.png"--%>
                         <%--alt="通用的占位符缩略图">--%>
                    <%--<div class="caption">--%>
                        <%--<h3>缩略图标签</h3>--%>
                        <%--<p>一些示例文本。一些示例文本。dddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd</p>--%>
                        <%--<p>--%>
                            <%--<a href="#" class="btn btn-primary" role="button">--%>
                                <%--按钮--%>
                            <%--</a>--%>
                            <%--<a href="#" class="btn btn-default" role="button">--%>
                                <%--按钮--%>
                            <%--</a>--%>
                        <%--</p>--%>
                    <%--</div>--%>
                <%--</div>--%>
        </div>
    </div>
    <div class="row-fluid">
        <%--<footer class="footer navbar-fixed-bottom ">--%>
        <footer class="footer">
            <div class="center-block" style="text-align: center;">
                Designed By ZZCoder
            </div>
        </footer>
    </div>
</div>
<script>
    $(function () {
        isLogged();
        $("div.div-item").click(function () {
            window.open("item.do?itemId="+$(this).children("div.div-item input[type='hidden']").val());
        });
    });
    function isLogged() {
        $.ajax({
            url:'isLogged.do',
            type:'get',
            dataType:"json",
            success:function (responseText,statusText) {
                if(responseText.success==true){
                    $("div.login-state").html("<a class='navbar-text login-or-person' href='person-center.do'>"+responseText.data.username+"</a><a class='navbar-text register-or-logout' href='logout.do'>注销</a>");
                    $("span.span-log-status").html("<a href='person-center.do'>"+responseText.data.username+"</a><a href='logout.do'>注销</a>");
                }
            },
            error:function (XMLHttpRequest,textStatus) {

            }
        });
    }
</script>
</body>
</html>
