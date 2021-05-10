<%--
  Created by IntelliJ IDEA.
  User: lucas
  Date: 2021/4/11
  Time: 15:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <div><a class="navbar-brand" href="index.html" style="font-size:32px;">尚筹网-创意产品众筹平台</a></div>
        </div>
    </div>
</nav>
<h1>${requestScope.exception.message }</h1>
<button id="backBtn" class="btn btn-lg btn-success btn-block">后退</button>
<%@ include file="include-head.jsp"%>
<script type="text/javascript">
    $(function(){
        $("#backBtn").click(function(){
            window.history.back();
        });
    });
</script>
</body>
</html>
