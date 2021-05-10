<%--
  Created by IntelliJ IDEA.
  User: lucas
  Date: 2021/4/16
  Time: 13:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="confirmModal" class="modal fade" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">信息确认</h4>
            </div>
            <div class="modal-body">
                <p>请确认以下信息</p>
                <table class="table  table-bordered">
                    <thead>
                    <tr >
                        <th width="30">#</th>
                        <th>角色</th>
                    </tr>
                    </thead>
                    <tbody id="confirmModalTableBody">
                    </tbody>
                </table>
            </div>
            <div class="modal-footer">
                <button id="confirmModalBtn" type="button" class="btn btn-primary">已确认</button>
            </div>
        </div>
    </div>
</div>