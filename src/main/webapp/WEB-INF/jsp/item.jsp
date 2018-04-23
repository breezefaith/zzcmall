<%@ page import="cn.breezefaith.entity.Item" %><%--
  Created by IntelliJ IDEA.
  User: breezefaith
  Date: 2018/4/15
  Time: 8:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>商品详情</title>
    <link rel='stylesheet prefetch' href='css/bootstrap.min.css'>
    <link rel="stylesheet" href="css/login.css">
    <script src="js/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="js/bootstrap.min.js"></script>

    <!--自定义js文件-->
    <script src="js/basic.js" type="text/javascript"></script>


    <link rel="stylesheet" href="css/item.css">
</head>
<body>
<% Item item=(Item) request.getAttribute("item");%>
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
                <li><a href="records.do">订单</a></li>
            </ul>
        </div>
        <div style="float:right;" class="login-state">
            <a href="login-page.do" class="navbar-text login-or-person">登录</a>
            <a href="register-page.do" class="navbar-text register-or-logout">注册</a>
        </div>
    </div>
</nav>

<div class="container-fluid" style="">
    <div class="row-fluid">
        <div class="col-md-2">
            <ul>
                <li>百货</li>
                <li>服饰</li>
                <li>数码</li>
            </ul>
        </div>
        <div class="col-md-10">
            <div class="row-fluid">
                <div class="col-md-6">
                    <div class="carousel slide" id="carousel-288307">
                        <ol class="carousel-indicators">
                            <li class="active" data-slide-to="0" data-target="#carousel-288307">
                            </li>
                        </ol>
                        <div class="carousel-inner">
                            <div class="item active">
                                <img alt="" src="getImage.do?itemId=<%=item.getIid()%>" />
                                <div class="carousel-caption">

                                </div>
                            </div>
                        </div>
                        <a data-slide="prev" href="#carousel-288307" class="left carousel-control">‹</a>
                        <a data-slide="next" href="#carousel-288307" class="right carousel-control">›</a>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="form-horizontal" role="form">
                        <input type="hidden" id="iid" value="<%=item.getIid()%>">
                        <div class="form-group">
                            <label class="col-sm-2 col-md-3 control-label">商品名：</label>
                            <div class="col-sm-10 col-md-9">
                                <p class="form-control-static"><%=item.getItemName()%></p>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 col-md-3 control-label">分类：</label>
                            <div class="col-sm-10 col-md-9">
                                <p class="form-control-static"><%=item.getItemCategory()%></p>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 col-md-3 control-label">价格：</label>
                            <div class="col-sm-10 col-md-9">
                                <p class="form-control-static"><span>￥</span><%=item.getItemPrice()%></p>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 col-md-3 control-label">商品描述：</label>
                            <div class="col-sm-10 col-md-9">
                                <p class="form-control-static"><%=item.getItemDescription()%></p>
                            </div>
                        </div>

                        <div class="form-group" style="margin: 40px 0 30px 0;">
                            <div class="col-md-offset-3">
                                <button class="btn btn-primary btn-purchase" onclick="addToCart()">加入购物车</button>
                            </div>

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="row-fluid">
        <footer class="footer navbar-fixed-bottom">
            <div class="center-block" style="text-align: center;">
                Designed By ZZCoder
            </div>
        </footer>
    </div>
</div>


<!--模态框设计-->
<div>
    <div class="modal fade" id="modal-info" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
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
        isLogged();
    });
    
    function addToCart() {
        $.ajax({
            url:"addToCart.do",
            type:"get",
            data:{
                itemId:$("input#iid").val()
            },
            dataType:"json",
            success:function (response,status) {
                if(response.success==true){
                    $("#modal-info div.modal-body").html("添加成功");
                    $("#modal-info").modal('show');
                }else{
                    $("#modal-info div.modal-body").html("添加失败");
                    $("#modal-info").modal('show');
                }
            },
            error:function (XMLHttpRequest,textStatus) {
                $("#modal-info div.modal-body").html("添加失败");
                $("#modal-info").modal('show');
            }
        });
    }

    function isLogged() {
        $.ajax({
            url:'isLogged.do',
            type:'get',
            dataType:"json",
            success:function (responseText,statusText) {
                if(responseText.success==true){
                    $("div.login-state").html("<a class='navbar-text login-or-person' href='person-center.do'>"+responseText.data.username+"</a><a class='navbar-text register-or-logout' href='logout.do'>注销</a>");
                    $("span.span-log-status").html("<a href='person-center.do'>"+responseText.data.username+"</a><a href='logout.do'>注销</a>");
                }else{
                    window.location.href="login-page.do";
                }
            },
            error:function (XMLHttpRequest,textStatus) {

            }
        });
    }
</script>

</body>
</html>