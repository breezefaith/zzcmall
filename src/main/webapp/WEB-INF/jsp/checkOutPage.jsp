<%@ page import="cn.breezefaith.entity.Address" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: breezefaith
  Date: 2018/4/21
  Time: 8:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>结算</title>
    <link rel='stylesheet prefetch' href='css/bootstrap.min.css'>
    <link rel="stylesheet" href="css/login.css">
    <script src="js/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="js/bootstrap.min.js"></script>

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
                <li class=""><a href="#">全部</a></li>
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
                            <th>订单号</th>
                            <th>数量</th>
                            <th>金额</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>#</td>
                            <td><%=request.getAttribute("counts")%></td>
                            <td><%=request.getAttribute("cost")%></td>
                        </tr>
                    </tbody>
                    <tfoot>
                        <tr>
                            <td colspan="1">
                                <div class="form-group">
                                    <label>收货地址：</label>
                                </div>
                            </td>
                            <td colspan="2">
                                <select class="form-control" name="address">
                                    <%
                                        for (Address address:(List<Address>)request.getAttribute("addresses")){
                                    %>
                                    <option value="<%=address.getAid()%>"><%=address.getName()%>——<%=address.getPhone()%>——<%=address.getPostCode()%>——<%=address.getAddress()%></option>
                                    <%
                                        }
                                    %>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="3">
                                <button class="btn btn-primary" onclick="pay(this)">支付</button>
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
    });

    function pay(obj) {
        var counts=$("tbody tr td:eq(1)").text();
        var cost=$("tbody tr td:eq(2)").text()
        var aid=$("tfoot tr select").val();
        $.ajax({
            url:"pay.do",
            type:"post",
            dataType:"json",
            data:{
                counts:counts,
                cost:cost,
                aid:aid
            },
            success:function (response,status) {
                if(response.success==true){
                    $("#modal-info div.modal-body").html("支付成功，3s后跳转至订单列表页...");
                    $("#modal-info").modal('show');
                    setTimeout(function () {

                    },3000);
                    window.location.href="records.do";
                }else{
                    $("#modal-info div.modal-body").html("支付失败");
                    $("#modal-info").modal('show');
                }
            },
            error:function (XMLHttpRequest,status) {

            }
        });
    }

</script>

</body>
</html>
