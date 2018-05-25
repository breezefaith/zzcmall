<%@ page import="cn.breezefaith.entity.Item" %>
<%@ page import="cn.breezefaith.entity.Record" %>
<%@ page import="cn.breezefaith.util.JSONUtil" %>
<%@ page import="com.fasterxml.jackson.core.type.TypeReference" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: breezefaith
  Date: 2018/5/25
  Time: 12:09
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
            <div>
                <table class="table table table-striped">
                    <thead>
                    <tr>
                        <th>订单号</th>
                        <th>收件人</th>
                        <th>联系电话</th>
                        <th>收货地址</th>
                        <th>邮编</th>
                        <th>商品列表</th>
                        <th>金额</th>
                        <th>快递号</th>
                    </tr>
                    </thead>

                    <tbody>
                    <% for(Record record : (List<Record>)session.getAttribute("records")){%>
                    <tr>
                        <td><%=record.getRid()%></td>
                        <td><%=record.getAddress().getName()%></td>
                        <td><%=record.getAddress().getPhone()%></td>
                        <td><%=record.getAddress().getAddress()%></td>
                        <td><%=record.getAddress().getPostCode()%></td>
                        <td>
                            <%for(Item item:(List<Item>) JSONUtil.decode(record.getItems(), new TypeReference<List<Item>>() {})){%>
                            <%=item.getItemName()%>,
                            <%}%>
                        </td>
                        <td><%=record.getCost()%></td>
                        <td><input type="text" value="<%=record.getCourierNumber()%>"><button class="button-confirm-courier" onclick="clickConfirm(this)">确定</button></td>
                    </tr>
                    <%}%>
                    </tbody>

                    <tfoot>

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
        $.ajax({
            url:'isLogged.do',
            type:'get',
            dataType:"json",
            success:function (responseText,statusText) {
                if(responseText.success==true){
                    $("div.login-state").html("<a href='logout.do'>注销</a>");
                }else{
                    window.location.href="login-page.do";
                }
            },
            error:function (XMLHttpRequest,textStatus) {

            }
        });
    });

    function clickConfirm(obj) {
        var tr=$(obj).parent().parent();
        $.ajax({
            url:"updateCourierNumber.do",
            type:"post",
            data:{
                rid:$(tr).find("td:first").text(),
                courier:$(obj).parent().find("input").val()
            },
            dataType:"json",
            success:function (responseText,statusText) {
                if(responseText.success==true){
                    $("#modal-info div.modal-body").html("修改成功");
                    $("#modal-info").modal('show');
                }else{
                    $("#modal-info div.modal-body").html("修改失败");
                    $("#modal-info").modal('show');
                }
            },
            error:function (XMLHttpRequest,textStatus) {
                $("#modal-info div.modal-body").html("修改失败");
                $("#modal-info").modal('show');
            }
        });
    }
</script>

</body>
</html>