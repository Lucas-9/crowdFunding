<%--
  Created by IntelliJ IDEA.
  User: lucas
  Date: 2021/4/9
  Time: 20:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="UTF-8">
<head>
    <%@ include file="/WEB-INF/include-head.jsp"%>
</head>
<body>
<%@ include file="/WEB-INF/include-nav.jsp"%>
<div class="container-fluid">
    <div class="row">
        <%@ include file="/WEB-INF/include-sidebar.jsp"%>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <ol class="breadcrumb">
                <li><a href="admin/main/page.html">首页</a></li>
                <li><a href="admin/queryUser/byKeyword.html">数据列表</a></li>
                <li class="active">新增</li>
            </ol>
            <div class="panel panel-default">
                <div class="panel-heading">表单数据<div style="float:right;cursor:pointer;" data-toggle="modal" data-target="#myModal"><i class="glyphicon glyphicon-question-sign"></i></div></div>
                <div class="panel-body">
                    <form action="admin/add/user.html" method="post" role="form">
                        <div class="form-group">
                            <label for="exampleInputPassword1">登陆账号</label>
                            <input name="loginAccount" type="text" class="form-control" id="exampleInputPassword1" placeholder="请输入登陆账号">
                        </div>
                        <div class="form-group">
                            <label for="exampleInputPassword1">用户名称</label>
                            <input name="userName" type="text" class="form-control" id="exampleInputPassword1" placeholder="请输入用户名称">
                        </div>
                        <div class="form-group">
                            <label for="exampleInputPassword1">用户密码</label>
                            <input name="userPassword" type="text" class="form-control" id="exampleInputPassword1" placeholder="请输入用户密码">
                        </div>
                        <div class="form-group">
                            <label for="exampleInputEmail1">邮箱地址</label>
                            <input name="email" type="email" class="form-control" id="exampleInputEmail1" placeholder="请输入邮箱地址">
                        </div>
                        <button type="submit" class="btn btn-success"><i class="glyphicon glyphicon-plus"></i> 新增</button>
                        <button type="reset" class="btn btn-danger"><i class="glyphicon glyphicon-refresh"></i> 重置</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
