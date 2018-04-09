<%--
  Created by IntelliJ IDEA.
  User: breezefaith
  Date: 2018/4/2
  Time: 14:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>个人中心</title>

    <link rel='stylesheet prefetch' href='css/bootstrap.min.css'>
    <script src="js/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="js/bootstrap.min.js"></script>

    <link rel="stylesheet" href="css/person-center.css">
</head>
<body>
<!--导航栏-->
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
                <li><a href="#">购物车</a></li>
                <li><a href="#">订单</a></li>
            </ul>
        </div>
        <div style="float:right;" class="login-state">
            <a href="login-page.do" class="navbar-text login-or-person">登录</a>
            <a href="register-page.do" class="navbar-text register-or-logout">注册</a>
        </div>
    </div>
</nav>

<!--主内容-->
<div class="container-fluid">
    <div class="row-fluid">
        <div class="col-md-2">
            <ul id="myTab" class="nav nav-pills nav-tabs nav-stacked">
                <li class="active" id="li-basic-info"><a href="#basic-info" data-toggle="tab">基本信息</a></li>
                <li class="" id="li-address"><a href="#address" data-toggle="tab">收货地址</a></li>
                <li class="" id="li-reset-password"><a href="#reset-password" data-toggle="tab">修改密码</a></li>
            </ul>
        </div>
        <div class="col-md-10 table-bordered div-main-right">
            <div id="myTabContent" class="tab-content">
                <div id="basic-info" class="tab-pane fade in active">
                    <div class="form-horizontal">
                        <div class="form-group">
                            <label class="col-sm-1 control-label">用户名</label>
                            <div class="col-sm-6">
                                <input type="text" class="form-control" id="username" placeholder="用户名" readonly>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-1 control-label">手机</label>
                            <div class="col-sm-6">
                                <input type="text" class="form-control" id="phone" placeholder="手机号码">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-1 control-label">邮箱</label>
                            <div class="col-sm-6">
                                <input type="email" class="form-control" id="email" placeholder="Email">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-offset-1 col-sm-6">
                                <button class="btn btn-primary btn-update-info" id="btn-update-info" onclick="updatePersonInfo()">修改</button>
                            </div>
                        </div>
                    </div>
                </div>
                <div id="address" class="tab-pane fade">
                    <table class="table table-hover table-responsive">
                        <thead>
                        <tr>
                            <th>#</th>
                            <th>收件人</th>
                            <th>手机号</th>
                            <th>邮编</th>
                            <th>详细地址</th>
                            <th colspan="2">操作</th>
                        </tr>
                        </thead>
                        <tbody>

                        </tbody>
                    </table>
                    <div>
                        <div class="form-group">
                            <div class="col-sm-offset-0 col-sm-6">
                                <button class="btn btn-primary btn-update-info" id="btn-add-address" onclick="">添加</button>
                            </div>
                        </div>
                    </div>
                </div>
                <div id="reset-password" class="tab-pane fade">
                    <div class="form-horizontal">
                        <div class="form-group">
                            <label class="col-sm-1 control-label">原密码</label>
                            <div class="col-sm-6">
                                <input type="password" class="form-control" id="origin-password" placeholder="原密码">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-1 control-label">新密码</label>
                            <div class="col-sm-6">
                                <input type="password" class="form-control" id="new-password" placeholder="新密码">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-1 control-label">确认密码</label>
                            <div class="col-sm-6">
                                <input type="password" class="form-control" id="confirm-password" placeholder="确认密码" onblur="checkPassword()">
                            </div>
                            <span id="errorpwd" style="display:none;color:red;" >两次输入密码不一致</span>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-offset-1 col-sm-6">
                                <button class="btn btn-primary" id="btn-update-password" onclick="updatePassword()">确认</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
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

    <!--开发者信息-->
    <div class="row-fluid">
        <footer class="footer navbar-fixed-bottom">
            <div class="center-block" style="text-align: center;">
                Designed By ZZCoder
            </div>
        </footer>
    </div>

    <script>
        $(function () {
            isLogged();

            personInfo();

            $("#li-address").click(function () {
                addressManagement();
            });

            $("#li-basic-info").click(function () {
                personInfo();
            });

            //添加地址按钮点击事件
            $('#btn-add-address').click(function () {
                addAddress();
            });
        });

        //检验是否已登录，若已登录加载个人信息
        function isLogged() {
            $.ajax({
                url:'isLogged.do',
                type:'get',
                dataType:"json",
                success:function (responseText,statusText) {
                    if(responseText.success==true){
                        //已登录
                        $("div.login-state").html("<a class='navbar-text login-or-person' href='person-center.do'>"+responseText.data.username+"</a><a class='navbar-text register-or-logout' href='logout.do'>注销</a>");
                        $("span.span-log-status").html("<a href='person-center.do'>"+responseText.data.username+"</a><a href='logout.do'>注销</a>");
                    }else{
                        //未登录则跳转至登录页
                        window.location.href="login-page.do";
                    }
                },
                error:function (XMLHttpRequest,textStatus) {

                }
            });
        }

        //获取用户信息
        function personInfo() {
            $.ajax({
                url:"getPersonInfo.do",
                type:"get",
                dataType:"json",
                success:function (responseText,statusText) {
                    if(responseText.success==true){
                        $("#username").val(responseText.data.username);
                        $("#phone").val(responseText.data.phone);
                        $("#email").val(responseText.data.email);
                    }
                },
                error:function (XMLHttpRequest,textStatus) {

                }
            });
        }

        //获取用户收货地址
        function addressManagement() {
            $.ajax({
                url:"getPersonInfo.do",
                type:"get",
                dataType:"json",
                success:function (responseText,statusText) {
                    if(responseText.success==true){
                        $("#address table tbody").html("");
                        var addrList=responseText.data.addresses;
                        for(var i=0;i<addrList.length;i++){
                            $("#address table tbody").append("<tr>" +
                                "<td>"+(i+1)+"<input type='hidden' name='aid' id='aid' value='"+addrList[i].aid+"'>"+"</td>"+
                                "<td>"+addrList[i].name+"</td>"+
                                "<td>"+addrList[i].phone+"</td>"+
                                "<td>"+addrList[i].postCode+"</td>"+
                                "<td>"+addrList[i].address+"</td>"+
                                "<td><button class=\"btn btn-primary btn-address-update\">修改</button></td><td><button class=\"btn btn-danger btn-address-delete\">删除</button></td>"+
                                "</tr>");
                        }

                        $("button.btn-address-update").click(function () {
                            updateAddress(this);
                        });

                        $("button.btn-address-delete").click(function () {
                            deleteAddress(this);
                        });

                    }
                },
                error:function (XMLHttpRequest,textStatus) {
                    return false;
                }
            });
        }

        //修改用户信息
        function updatePersonInfo() {
            $.ajax({
                url:"updatePersonInfo.do",
                type:"post",
                dataType:"json",
                data:{
                    phone:$("input#phone").val(),
                    email:$("input#email").val()
                },
                success:function (responseText,statusText) {
                    if(responseText.success==true){
                        $("#modal-update-person-info div.modal-body").html("修改成功");
                        $("#modal-update-person-info").modal('show');
                    }else{
                        $("#modal-update-person-info div.modal-body").html("修改失败");
                        $("#modal-update-person-info").modal('show');
                    }
                },
                error:function (XMLHttpRequest,textStatus) {
                    $("#modal-update-person-info div.modal-body").html("修改失败");
                    $("#modal-update-person-info").modal('show');
                }
            });
        }

        //检验两个密码框输入是否一致
        function checkPassword() {
            if($("#new-password").val()!=$("#confirm-password").val()){
                $("#errorpwd").css("display","block");
                return false;
            }else{
                $("#errorpwd").css("display","none");
                return true;
            }
        }

        //修改密码
        function updatePassword() {
            if(checkPassword()==true){
                $.ajax({
                    url:"updatePassword.do",
                    type:"post",
                    dataType:"json",
                    data:{
                        origin:$("#origin-password").val(),
                        new:$("#new-password").val()
                    },
                    success:function (responseText,statusText) {
                        if(responseText.success==true){
                            $("#modal-update-person-info div.modal-body").html("修改成功");
                            $("#modal-update-person-info").modal('show');
                        }else{
                            $("#modal-update-person-info div.modal-body").html("修改成功");
                            $("#modal-update-person-info").modal('show');
                        }
                    },
                    error:function (XMLHttpRequest,textStatus) {
                        $("#modal-update-person-info div.modal-body").html("修改失败");
                        $("#modal-update-person-info").modal('show');
                    }
                });
            }
        }

        //添加收货地址
        function addAddress() {
            var appendHtml="<tr>"+
                "<td>&rightrightarrows;</td>"+
                "<td><input type='text' class='form-control' id='address-name' required/></td>"+
                "<td><input type='text' class='form-control' id='address-phone' required/></td>"+
                "<td><input type='text' class='form-control' id='address-post-code' required/></td>"+
                "<td><input type='text' class='form-control' id='address-detail' required/></td>"+
                "<td><button class=\"btn btn-primary btn-address-confirm-add\">确定</button></td><td><button class=\"btn btn-danger btn-address-cancel-add\">取消</button></td>"+
                "</tr>";
            $("#address table tbody").append(appendHtml);
            $(".btn-address-confirm-add").click(function () {
                addConfirmAddress();
            });
            $(".btn-address-cancel-add").click(function () {
                addCancelAddress(this);
            });
        }
        //添加地址确认按钮
        function addConfirmAddress() {
            var name=$("#address-name").val();
            var phone=$("#address-phone").val();
            var postCode=$("#address-post-code").val();
            var address=$("#address-detail").val();
            $.ajax({
                url:"addAddress.do",
                type:"post",
                dataType:"json",
                data:{
                    name:name,
                    phone:phone,
                    postCode:postCode,
                    address:address
                },
                success:function (responseText,statusText) {
                    if(responseText.success==true){
                        $("#modal-update-person-info div.modal-body").html("添加成功");
                        $("#modal-update-person-info").modal('show');
                        $("#li-address").click();
                    }else{
                        $("#modal-update-person-info div.modal-body").html("添加失败");
                        $("#modal-update-person-info").modal('show');
                    }
                },
                error:function (XMLHttpRequest,textStatus) {
                    $("#modal-update-person-info div.modal-body").html("添加失败");
                    $("#modal-update-person-info").modal('show');
                }
            });
        }
        //添加地址取消按钮
        function addCancelAddress(obj) {
            $(obj).parent().parent().remove();
        }
        
        
        //删除收货地址
        function deleteAddress(obj) {
            var trSelected=$(obj).parent().parent();
            $.ajax({
                url:"deleteAddress.do",
                type:"post",
                dataType:"json",
                data:{
                    aid:trSelected.find("input[type='hidden']").val()
                },
                success:function (responseText,statusText) {
                    if(responseText.success==true){
                        $("#modal-update-person-info div.modal-body").html("删除成功");
                        $("#modal-update-person-info").modal('show');
                        $("#li-address").click();
                    }else{
                        $("#modal-update-person-info div.modal-body").html("删除失败");
                        $("#modal-update-person-info").modal('show');
                    }
                },
                error:function (XMLHttpRequest,textStatus) {
                    $("#modal-update-person-info div.modal-body").html("删除失败");
                    $("#modal-update-person-info").modal('show');
                }
            });
        }
        //修改收货地址
        function updateAddress(obj) {
            var trSelected=$(obj).parent().parent();
            var tds=trSelected.children("td:gt(0):lt(4)");
            for(var i=0;i<$(tds).length;i++){
                $(tds[i]).html("<input type='text' class='form-control' value='"+$(tds[i]).html()+"'/>");
            }
            trSelected.children().children("button.btn-address-update").replaceWith("<button class='btn btn-primary btn-address-update-confirm'>确定</button>");
            $("button.btn-address-update-confirm").click(function () {
                updateConfirmAddress(this);
            });
        }
        //修改收货地址确定按钮
        function updateConfirmAddress(obj) {
            var trSelected=$(obj).parent().parent();
            var tds=trSelected.children("td:lt(5)");
//            alert($(tds[0]).html());
//            var aid=$(tds[0]).children("input").val();
//            var name=$(tds[1]).children("input").val();
//            var phone=$(tds[2]).children("input").val();
//            var postCode=$(tds[3]).children("input").val();
//            var address=$(tds[4]).children("input").val();
//            alert(aid+" "+name+" "+phone+" "+postCode+" "+address);
//            return false;
            $.ajax({
                url:"updateAddress.do",
//                url:"test.do",
                type:"post",
                dataType:"json",
                data:{
                    aid:$(tds[0]).children("input").val(),
                    name:$(tds[1]).children("input").val(),
                    phone:$(tds[2]).children("input").val(),
                    postCode:$(tds[3]).children("input").val(),
                    address:$(tds[4]).children("input").val()
                },
                success:function (responseText,statusText) {
                    if(responseText.success==true){
                        $("#modal-update-person-info div.modal-body").html("修改成功");
                        $("#modal-update-person-info").modal('show');
                    }else{
                        $("#modal-update-person-info div.modal-body").html("修改失败");
                        $("#modal-update-person-info").modal('show');
                    }
                    $("#li-address").click();
                },
                error:function (XMLHttpRequest,textStatus) {
                    $("#modal-update-person-info div.modal-body").html("修改失败");
                    $("#modal-update-person-info").modal('show');
                    $("#li-address").click();
                }
            });
        }
    </script>
</body>
</html>