<%--
  Created by IntelliJ IDEA.
  User: breezefaith
  Date: 2018/5/3
  Time: 14:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="cn.breezefaith.entity.Item" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.LinkedList" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <meta charset="utf8">
    <link rel='stylesheet prefetch' href='css/bootstrap.min.css'>
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
<div class="container-fluid">
    <div class="row-fluid">
        <div class="col-md-2">
            <ul>
                <li>百货</li>
                <li>服饰</li>
                <li>数码</li>
            </ul>
        </div>
        <div class="col-md-10 item-list">
            <%
                for (Item item:(List<Item>) request.getAttribute("itemList")) {
            %>
            <div class='col-sm-6 col-md-3'>
                <div class='thumbnail' >
                    <%--<img style='height: 30%;width: 90%;' src='getImage.do?itemId=<%=item.getIid()%>' alt='商品图片'/>--%>
                    <img style='height: 30%;width: 90%;' src='getImageByUrl.do?url=<%=item.getItemImage()%>' alt='商品图片'/>
                    <%--<img style='height: 30%;width: 90%;' src='getImageInMySQL.do?iid=<%=item.getIid()%>' alt='商品图片'/>--%>
                    <%--<img style='height: 30%;width: 90%;' src='getImageInRedis.do?iid=<%=item.getIid()%>' alt='商品图片'/>--%>

                    <div class='caption'>
                        <h4><%=item.getItemName()%></h4>
                        <p><%=item.getItemDescription()%></p>
                        <p>
                            <a href='#' class='btn btn-default' role='button'>
                                <%=item.getItemCategory()%>
                            </a>
                            <a href='#' class='btn btn-default' role='button'>
                                ￥<%=item.getItemPrice()%>
                            </a>
                        </p>
                    </div>
                </div>
            </div>
            <%}%>
        </div>
    </div>
</div>
<script>
    $(function () {

    });
</script>
</body>
</html>
