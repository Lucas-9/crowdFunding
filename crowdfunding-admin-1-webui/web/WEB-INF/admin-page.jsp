<%--
  Created by IntelliJ IDEA.
  User: lucas
  Date: 2021/4/9
  Time: 20:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<head>
    <%@ include file="/WEB-INF/include-head.jsp" %>
    <link rel="stylesheet" href="css/pagination.css"/>
</head>
<html lang="UTF-8">
<body>
<%@ include file="/WEB-INF/include-nav.jsp" %>
<div class="container-fluid">
    <div class="row">
        <%@ include file="/WEB-INF/include-sidebar.jsp" %>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title"><i class="glyphicon glyphicon-th"></i> 数据列表</h3>
                </div>
                <div class="panel-body">
                    <form action="admin/queryUser/byKeyword.html" method="post" class="form-inline" role="form"
                          style="float:left;">
                        <div class="form-group has-feedback">
                            <div class="input-group">
                                <div class="input-group-addon">查询条件</div>
                                <input name="keyword" class="form-control has-success" type="text"
                                       placeholder="请输入查询条件">
                            </div>
                        </div>
                        <button type="submit" class="btn btn-warning"><i class="glyphicon glyphicon-search"></i> 查询
                        </button>
                    </form>
                    <button id="batchRemoveBtn" type="button" class="btn btn-danger"
                            style="float:right;margin-left:10px;"><i class=" glyphicon glyphicon-remove"></i> 删除
                    </button>
                    <a href="admin/add/user/page.html" class="btn btn-primary" style="float:right;"/><i
                        class="glyphicon glyphicon-plus"></i> 新增</a>
                    <br>
                    <hr style="clear:both;">
                    <div class="table-responsive">
                        <table class="table  table-bordered">
                            <thead>
                            <tr>
                                <th width="30">#</th>
                                <th width="30"><input id="generalBox" type="checkbox"></th>
                                <th>账号</th>
                                <th>名称</th>
                                <th>邮箱地址</th>
                                <th width="100">操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:if test="${empty requestScope['PAGE-INFO'].list }">
                                <tr>
                                    <td style="text-align: center;" colspan="6">抱歉！没有符合您要求的查询结果！</td>
                                </tr>
                            </c:if>
                            <c:if test="${!empty requestScope['PAGE-INFO'].list }">
                                <c:forEach items="${requestScope['PAGE-INFO'].list }" var="admin" varStatus="myStatus">
                                    <tr>
                                        <td>${myStatus.count }</td>
                                        <td><input adminId="${admin.id }" class="itemBox" type="checkbox"></td>
                                        <td>${admin.loginAccount }</td>
                                        <td>${admin.userName }</td>
                                        <td>${admin.email }</td>
                                        <td>
                                            <a href="assign/to/assign/role/page.html?adminId=${admin.id }&pageNum=${requestScope['PAGE-INFO'].pageNum}"
                                               class="btn btn-success btn-xs"><i class=" glyphicon glyphicon-check"></i></a>
                                            <a href="/admin/edit/user.html?adminId=${admin.id }&pageNum=${requestScope['PAGE-INFO'].pageNum}"
                                               class="btn btn-primary btn-xs">
                                                <i class=" glyphicon glyphicon-pencil"></i></a>
                                            </button>
                                            <button adminId="${admin.id }" type="button"
                                                    class="btn btn-danger btn-xs radioRemoveBtn">
                                                <i class=" glyphicon glyphicon-remove"></i>
                                            </button>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </c:if>
                            </tbody>

                            <tfoot>
                            <tr>
                                <td colspan="6" align="center">
                                    <div id="Pagination" class="pagination"><!-- 这里显示分页 --></div>
                                </td>
                            </tr>
                            </tfoot>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript" src="script/jquery.pagination.js"></script>
<script type="text/javascript" charset="UTF-8" src="script/my-admin.js"></script>
<script>
    $(function () {
        window.totalRecord = ${requestScope['PAGE-INFO'].total};
        window.pageSize = ${requestScope['PAGE-INFO'].pageSize};
        window.pageNum = ${requestScope['PAGE-INFO'].pageNum};
        window.keyword = "${param.keyword}";
        // 对分页导航条显示进行初始化
        initPagination();
        $("#generalBox").click(function () {
            $(".itemBox").prop("checked", this.checked);
        });
        $(".radioRemoveBtn").click(function () {
            var adminIdArray = new Array();
            adminIdArray.push($(this).attr("adminId"));
            batchRemove(adminIdArray);
        });
        $("#batchRemoveBtn").click(function () {
            var adminIdArray = new Array();
            $(".itemBox:checked").each(function () {
                var adminId = $(this).attr("adminId");
                adminIdArray.push(adminId);
            });
            batchRemove(adminIdArray);
        });
    });
</script>

</html>
