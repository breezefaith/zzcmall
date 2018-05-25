<%@ page import="cn.breezefaith.entity.Item" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: breezefaith
  Date: 2018/5/25
  Time: 13:32
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
                        <th>IID</th>
                        <th>商品名</th>
                        <th>类别</th>
                        <th>商品描述</th>
                        <th>商品图片</th>
                        <th>价格</th>
                        <th>操作</th>
                    </tr>
                    </thead>

                    <tbody>
                        <%for (Item item:(List<Item>)session.getAttribute("items")){%>
                        <tr>

                            <td><input type="text" value="<%=item.getIid()%>"></td>
                            <td><input type="text" value="<%=item.getItemName()%>"></td>
                            <td><input type="text" value="<%=item.getItemCategory()%>"></td>
                            <td><input type="text" value="<%=item.getItemDescription()%>"></td>
                            <td><input type="text" value="<%=item.getItemImage()%>"></td>
                            <td><input type="text" value="<%=item.getItemPrice()%>"></td>
                            <td>
                                <button class="button-confirm" onclick="clickConfirm(this)">确定</button>
                                <button class="button-delete" onclick="clickDelete(this)">删除</button>
                            </td>
                        </tr>
                        <%}%>
                    </tbody>

                    <tfoot>
                        <tr>
                            <td colspan="7">
                                <button onclick="clickAddItem()">添加</button>
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


<div>
    <div class="modal fade" id="modal-add-item" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                        添加商品
                    </div>
                    <div class="modal-body">
                            <div class="form-horizontal">
                                <div class="form-group">
                                    <label class="col-sm-1 control-label">商品名</label>
                                    <div class="col-sm-6">
                                        <input type="text" class="form-control" id="input-item-name">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-1 control-label">类别</label>
                                    <div class="col-sm-6">
                                        <input type="text" class="form-control" id="input-item-category">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-1 control-label">商品描述</label>
                                    <div class="col-sm-6">
                                        <input type="text" class="form-control" id="input-item-description">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-1 control-label">商品图片</label>
                                    <div class="col-sm-6">
                                        <input type="text" class="form-control" id="input-item-image">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-1 control-label">商品价格</label>
                                    <div class="col-sm-6">
                                        <input type="text" class="form-control" id="input-item-price">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-sm-offset-1 col-sm-6">
                                        <button class="btn btn-primary" id="btn-update-password" onclick="addItem()">确认</button>
                                    </div>
                                </div>
                            </div>
                        <div class="modal-footer">
                        </div>
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
            url:"updateItem.do",
            type:"post",
            data:{
                iid:$(tr).find("td:eq(0) input").val(),
                itemName:$(tr).find("td:eq(1) input").val(),
                itemCategory:$(tr).find("td:eq(2) input").val(),
                itemDescription:$(tr).find("td:eq(3) input").val(),
                itemImage:$(tr).find("td:eq(4) input").val(),
                itemPrice:$(tr).find("td:eq(5) input").val()
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

    function clickDelete(obj) {
        var tr=$(obj).parent().parent();
        $.ajax({
            url:"deleteItem.do",
            type:"post",
            data:{
                iid:$(tr).find("td:eq(0) input").val(),
            },
            dataType:"json",
            success:function (responseText,statusText) {
                if(responseText.success==true){
                    $("#modal-info div.modal-body").html("删除成功");
                    $("#modal-info").modal('show');
                }else{
                    $("#modal-info div.modal-body").html("删除失败");
                    $("#modal-info").modal('show');
                }
            },
            error:function (XMLHttpRequest,textStatus) {
                $("#modal-info div.modal-body").html("删除失败");
                $("#modal-info").modal('show');
            }
        });
    }

    function addItem() {
        $.ajax({
            url:"addItem.do",
            type:"post",
            data:{
                itemName:$("#input-item-name").val(),
                itemDescription:$("#input-item-description").val(),
                itemCategory:$("#input-item-category").val(),
                itemImage:$("#input-item-image").val(),
                itemPrice:$("#input-item-price").val()
            },
            dataType:"json",
            success:function (responseText,statusText) {
                if(responseText.success==true){
                    $("#modal-add-item").modal('hide');
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
    function clickAddItem() {
        $("#modal-add-item").modal('show');
    }
</script>

</body>
</html>