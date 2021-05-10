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
    <%@ include file="/WEB-INF/include-head.jsp"%>
    <link rel="stylesheet" href="ztree/zTreeStyle.css">
    <script type="text/javascript" src="ztree/jquery.ztree.all-3.5.min.js"></script>
    <script type="text/javascript" src="script/my-menu.js"></script>
</head>
<body>
<%@ include file="/WEB-INF/include-nav.jsp"%>
<div class="container-fluid">
    <div class="row">
        <%@ include file="/WEB-INF/include-sidebar.jsp"%>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <i class="glyphicon glyphicon-th-list"></i> 权限菜单列表
                    <div style="float: right; cursor: pointer;" data-toggle="modal"
                         data-target="#myModal">
                        <i class="glyphicon glyphicon-question-sign"></i>
                    </div>
                </div>
                <div class="panel-body">
                    <ul id="ztree" class="ztree"></ul>
                </div>
            </div>
        </div>
    </div>
</div>
<%@ include file="include-add-menu-modal.jsp" %>
<%@ include file="include-edit-menu-modal.jsp" %>
</body>
<script>
        $(function(){
            loadZTree();
            $("#menuAddBtn").click(function(){
                // 收集表单填写的数据
                var name = $.trim($("#menuAddModal [name='name']").val());
                var url = $.trim($("#menuAddModal [name='url']").val());
                var icon = $("#menuAddModal [name='icon']:checked").val();
                if(name == null || name == "") {
                    layer.msg("请填写菜单项名称！");
                    return ;
                }
                if(url == null || url == "") {
                    layer.msg("请填写菜单项对应的访问地址！");
                    return ;
                }
                // 发送Ajax请求
                $.ajax({
                    "url":"menu/add/menu.json",
                    "type":"post",
                    "dataType":"json",
                    "data":{
                        "name":name,
                        "url":url,
                        "pid":window.menuId,	// 当前操作的节点是新节点的父节点
                        "icon":icon
                    },
                    "success":function(response){
                        var result = response.result;
                        if(result == "SUCCESS") {
                            layer.msg("操作成功！");

                            loadZTree();
                        }
                        if(result == "FAILED") {
                            layer.msg(response.message);
                        }
                    },
                    "error":function(response){
                        layer.msg(response.message);
                    }
                });

                $("#menuAddModal").modal("hide");
            });
            $("#menuEditBtn").click(function(){
                // 收集表单填写的数据
                var name = $.trim($("#menuEditModal [name='name']").val());
                var url = $.trim($("#menuEditModal [name='url']").val());
                var icon = $("#menuEditModal [name='icon']:checked").val();
                if(name == null || name == "") {
                    layer.msg("请填写菜单项名称！");
                    return ;
                }
                if(url == null || url == "") {
                    layer.msg("请填写菜单项对应的访问地址！");
                    return ;
                }
                // 发送Ajax请求
                $.ajax({
                    "url":"menu/update/menu.json",
                    "type":"post",
                    "dataType":"json",
                    "data":{
                        "id":window.menuId,
                        "name":name,
                        "url":url,
                        "icon":icon
                    },
                    "success":function(response){
                        var result = response.result;
                        if(result == "SUCCESS") {
                            layer.msg("操作成功！");
                            loadZTree();
                        }
                        if(result == "FAILED") {
                            layer.msg(response.message);
                        }
                    },
                    "error":function(response){
                        layer.msg(response.message);
                    }
                });
                $("#menuEditModal").modal("hide");
            });
        });
</script>
</html>
