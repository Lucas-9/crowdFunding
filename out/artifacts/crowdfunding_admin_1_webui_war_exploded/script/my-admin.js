function batchRemove(adminIdArray) {
    if (adminIdArray.length == 0) {
        alert("请选择删除对象！");
        return;
    }
    var confirmResult = confirm("是否确认删除，此操作不可逆！");
    if (!confirmResult) {
        return;
    }
    $.ajax({
        url:"admin/batch/remove.json",
        contentType:"application/json;charset=UTF-8",
        type:"post",
        dataType:'json',
        data: JSON.stringify(adminIdArray),
        success(data) {
            var result = data.result;
            if(result == "SUCCESS") {
                // 跳转页面
                window.location.href = "admin/queryUser/byKeyword.html?pageNum=" + window.pageNum + "&keyword=" + window.keyword;
            }
            if(result == "FAILED") {
                alert(data.message);
                return ;
            }
        },
        error(data) {
            alert(data.message);
            return ;
        }
    });
}
// 声明函数封装导航条初始化操作
function initPagination() {
    // 声明变量存储分页导航条显示时的属性设置
    var paginationProperties = {
        num_edge_entries : 3,			//边缘页数
        num_display_entries : 5,		//主体页数
        callback : pageselectCallback,	//回调函数
        items_per_page : window.pageSize,	//每页显示数据数量，就是pageSize
        current_page : window.pageNum - 1,//当前页页码
        prev_text : "上一页",			//上一页文本
        next_text : "下一页"			//下一页文本
    };
    // 显示分页导航条
    $("#Pagination").pagination(window.totalRecord, paginationProperties);
}
// 在每一次点击“上一页”、“下一页”、“页码”时执行这个函数跳转页面
function pageselectCallback(pageIndex, jq) {
    window.pageNum = pageIndex + 1;
    // 跳转页面
    window.location.href = "admin/queryUser/byKeyword.html?pageNum=" + window.pageNum + "&keyword=" + window.keyword;
    return false;
}