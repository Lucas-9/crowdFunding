<%--
  Created by IntelliJ IDEA.
  User: lucas
  Date: 2021/4/22
  Time: 9:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="roleAssignAuthModal" class="modal fade" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">权限分配</h4>
            </div>
            <div class="modal-body">
                <ul id="treeDemo" class="ztree"></ul>
            </div>
            <div class="modal-footer">
                <button id="roleAssignAuthBtn" type="button" class="btn btn-primary">分配</button>
            </div>
        </div>
    </div>
</div>

