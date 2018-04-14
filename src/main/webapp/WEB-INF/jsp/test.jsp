<%@ page import="cn.breezefaith.entity.Item" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.LinkedList" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: breezefaith
  Date: 2018/4/12
  Time: 12:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <meta charset="utf8">
</head>
<body>
    <div class="col-md-10">


    <% for (Item item:(List<Item>)request.getAttribute("itemListInRedis")){%>
        <div class="col-sm-6 col-md-3">
            <div class="thumbnail" >
                <img style="height: 30%;width: 90%;" src="getImage.do?itemId=<%=item.getIid()%>" alt="商品图片">
                <div class="caption">
                    <h4><%=item.getItemName()%></h4>
                    <p><%=item.getItemDescription()%></p>
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
    </div>
<%--<img src="getImage.do?itemId=3" alt=""/>--%>

<%--<img src="http://192.168.181.134/zzcmall/img/3.jpg"/>--%>
</body>
</html>
