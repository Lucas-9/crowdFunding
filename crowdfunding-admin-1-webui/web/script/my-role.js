function initGlobalVariable() {
    window.pageSize = 10;
    window.pageNum = 1;
    window.keyword = "";
}

function showPage() {
    $("#generalBox").prop("checked", false);
    // 给服务器发送请求获取分页数据：PageInfo
    var pageInfo = getPageInfo();
    // 在页面上的表格中tbody标签内显示分页的主体数据
    generateTableBody(pageInfo);
    // 在页面上的表格中tfoot标签内显示分页的页码导航条
    initPagination(pageInfo);
}

function getPageInfo() {
    // 以同步请求方式调用$.ajax()函数并获取返回值（返回值包含全部响应数据）
    var ajaxResult = $.ajax({
        url: "role/queryRole/byKeyword.json",
        type: "post",
        data: {
            "pageNum": window.pageNum,
            "pageSize": window.pageSize,
            "keyword": window.keyword
        },
        dataType: "json",
        async: false	// 为了保证getPageInfo()函数能够在Ajax请求拿到响应后获取PageInfo，需要设置为同步操作
    });
    // 从全部响应数据中获取JSON格式的响应体数据
    var resultEntity = ajaxResult.responseJSON;
    // 从响应体数据中获取result，判断当前请求是否成功
    var result = resultEntity.result;
    // 如果成功获取PageInfo
    if(result == "SUCCESS") {
        return resultEntity.data;
    }
    if(result == "FAILED") {
        layer.msg(resultEntity.message);
    }
    return null;
}

// 使用PageInfo数据在tbody标签内显示分页数据
function generateTableBody(pageInfo) {
    // 执行所有操作前先清空
    $("#roleTableBody").empty();
    // 获取数据集合
    var list = pageInfo.list;
    // 判断list是否有效
    if (list == null || list.length == 0) {
        $("#roleTableBody")
            .append("<tr><td colspan='4' style='text-align:center;'>没有查询到数据！</td></tr>");
        return;
    }
    for (var i = 0; i < list.length; i++) {
        var role = list[i];
        var checkBtn = "<button roleId='" + role.id  + "' type='button' class='checkBtn btn btn-success btn-xs'><i class=' glyphicon glyphicon-check'></i></button>";
        var pencilBtn = "<button roleId='" + role.id  + "' type='button' class='editBtn btn btn-primary btn-xs'><i class=' glyphicon glyphicon-pencil'></i></button>";
        var removeBtn = "<button roleId='" + role.id  + "' type='button' class='removeBtn btn btn-danger btn-xs'><i class=' glyphicon glyphicon-remove'></i></button>";
        var numberTd = "<td>" + (i + 1) + "</td>";
        var checkBoxTd = "<td><input roleId='" + role.id + "' class='itemBox' type='checkbox'></td>";
        var roleNameTd = "<td>" + role.name + "</td>";
        var btnTd = "<td>" + checkBtn + " " + pencilBtn + " " + removeBtn + "</td>";
        var tr = "<tr>" + numberTd + checkBoxTd + roleNameTd + btnTd + "</tr>";
        // 将前面拼好的HTML代码追加到#roleTableBody中
        $("#roleTableBody").append(tr);
    }
}

//声明函数封装导航条初始化操作
function initPagination(pageInfo) {
    // 声明变量存储分页导航条显示时的属性设置
    var paginationProperties = {
        num_edge_entries: 3,			//边缘页数
        num_display_entries: 5,		//主体页数
        callback: pageselectCallback,	//回调函数
        items_per_page: window.pageSize,	//每页显示数据数量，就是pageSize
        current_page: (window.pageNum - 1),//当前页页码
        prev_text: "上一页",			//上一页文本
        next_text: "下一页"			//下一页文本
    };
    // 显示分页导航条
    $("#Pagination").pagination(pageInfo.total, paginationProperties);
}

// 在每一次点击“上一页”、“下一页”、“页码”时执行这个函数跳转页面
function pageselectCallback(pageIndex, jq) {
    // 将全局变量中的pageNum修改为最新值
    // pageIndex从0开始，pageNum从1开始
    window.pageNum = pageIndex + 1;
    // 调用分页函数重新执行分页
    showPage();
    return false;
}
function showBatchRemoveConfirmModal() {
    $("#confirmModal").modal("show");
    // 执行所有操作前先清空
    $("#confirmModalTableBody").empty();
    // 获取数据集合
    var list = getRoleListByRoleId(window.roleIdArray);
    for (var i = 0; i < list.length; i++) {
        var role = list[i];
        var trHTML = "<tr><td>"+role.id+"</td><td>"+role.name+"</td></tr>";
        $("#confirmModalTableBody").append(trHTML);
    }
}
function getRoleListByRoleId(roleIdArray) {
    var ajaxResult = $.ajax({
        url:"role/getList/byRoleId.json",
        type:"post",
        data:JSON.stringify(roleIdArray),
        contentType:"application/json;charset=UTF-8",
        dataType:"json",
        async:false
    });
    var resultEntity = ajaxResult.responseJSON;
    var result = resultEntity.result;
    if(result == "SUCCESS") {
        return resultEntity.data;
    }
    if(result == "FAILED") {
        layer.msg(resultEntity.message);
        return null;
    }
    return null;
}
