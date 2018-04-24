<%@ page import="cn.breezefaith.entity.Item" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: breezefaith
  Date: 2018/4/16
  Time: 13:07
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

    <style>
        body {
            background: #eee !important;
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
                <img src="image/logo2.png" style="max-width:100px;margin-top:-4px;"/>
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
            <div>
                <table class="table">
                    <thead>
                        <tr>
                            <th>IID</th>
                            <th>商品名</th>
                            <th>价格</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            int count=0;
                            double total=0;
                            for (Item item:(List<Item>)request.getAttribute("cart")) {
                                count++;
                                total+=item.getItemPrice();
                        %>
                        <tr>
                            <td><%=item.getIid()%></td>
                            <td><%=item.getItemName()%></td>
                            <td><%=item.getItemPrice()%></td>
                            <td><button class="btn btn-danger" onclick="deleteItem(this)">X</button></td>
                        </tr>
                        <%}%>
                    </tbody>
                    <tfoot>
                        <tr class="warning">
                            <td>合计</td>
                            <td><%=count%>件</td>
                            <td><%=total%>元</td>
                            <td></td>
                        </tr>
                        <tr>
                            <td colspan="3"></td>
                            <td>
                                <form action="checkOutPage.do" method="post">
                                    <input type="hidden" name="counts" value=""/>
                                    <input type="hidden" name="cost" value=""/>
                                    <button class="btn btn-primary" style="float: left;" type="submit">结算</button>
                                </form>
                            </td>
                        </tr>
                    </tfoot>
                </table>
            </div>

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
                    $("div.login-state").html("<a class='navbar-text login-or-person' href='person-center.do'>"+responseText.data.username+"</a><a class='navbar-text register-or-logout' href='logout.do'>注销</a>");
                    $("span.span-log-status").html("<a href='person-center.do'>"+responseText.data.username+"</a><a href='logout.do'>注销</a>");
                }else{
                    window.location.href="login-page.do";
                }
            },
            error:function (XMLHttpRequest,textStatus) {

            }
        });

        $("form input[name='counts']").val(parseInt($("tfoot tr:first td:eq(1)").text()));
        $("form input[name='cost']").val(parseFloat($("tfoot tr:first td:eq(2)").text()));
    });
    function deleteItem(obj) {
        var trSelected=$(obj).parent().parent();
        var sub=parseFloat($(trSelected).children("td:eq(2)").text());
        $.ajax({
            url:"deleteItem.do",
            type:"post",
            dataType:"json",
            data:{
                itemId:$(trSelected).children("td:first").text()
            },
            success:function (response,status) {
                if(response.success==true){
                    $(trSelected).remove();
                    var tds=$("tfoot tr:first");
                    $(tds).children("td:eq(1)").html((parseInt($(tds).children("td:eq(1)").text())-1)+"件");
                    $("table tfoot tr td:eq(2)").html(parseFloat(parseFloat($(tds).children("td:eq(2)").text())-sub)+"元")
                    $("form input[name='counts']").val(parseInt($(tds).children("td:eq(1)").text()));
                    $("form input[name='cost']").val(parseFloat($(tds).children("td:eq(2)").text()));
                }
            },
            error:function (XMLHttpRequest,status) {

            }
        });
    }

    function checkOut(obj){
        $("form").submit();
    }
</script>

</body>
</html>