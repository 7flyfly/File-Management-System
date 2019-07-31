//向模态框传值
$('#searchModal').on('show.bs.modal',function (event) {
    var btnThis = $(event.relatedTarget);//触发事件的按钮
    var modal = $(this);//当前模态框
    var name= btnThis.closest('tr').find('td').eq(0).text();
    modal.find("#role-name").val(name);
    console.log(name);
});
function saveSearch(obj) {
    var roleName=$("#role-name").val();
    var type = $("#type").val();
    var department = $("#department").val();
    var purpose = $("#purpuse").val();
    var jsonObj= {
        'roleName':roleName,
        'type': type,
        'department': department,
        "purpose": purpose,
    };
    $.ajax({
        type: "post",
        url: "/saveSerach",
        data: JSON.stringify(jsonObj),
        dataType: "json",
        contentType: "application/json",
        success: function(result) {
            window.location.reload();
        }
    });
}


$('#liModal').on('show.bs.modal',function (event) {
    var btnThis = $(event.relatedTarget);//触发事件的按钮
    var modal = $(this);//当前模态框
    var name= btnThis.closest('tr').find('td').eq(0).text();
    modal.find("#roleName").val(name);
    console.log(name);
});
$('#liModal').on('hidden.bs.modal',function () {
    var objSelect = document.getElementsByName("way");
    var len = objSelect.length;
    for (k in objSelect) {
        if (objSelect[k].checked) {
            objSelect[k].checked = false;
        }
    }
});

function savedata(obj){
    var username = $('#roleName').val();
    var objSelect = document.getElementsByName("way");
    var way= new Array();
    way.push(username);//首位保存用户名
    for (k in objSelect){
        if(objSelect[k].checked){
            way.push(objSelect[k].value);
        }
    }
    //清空数据
    var select = document.getElementsByName("way");
    var len = select.length;
    for (k in select) {
        if (select[k].checked) {
            select[k].checked = false;
        }
    }
    console.log(way);
    $.ajax({
        type: "post",
        url: "/saveData",
        data: JSON.stringify(way),
        dataType: "json",
        contentType: "application/json",
        success: function(result) {
            //window.location.reload();
        }
    });
}