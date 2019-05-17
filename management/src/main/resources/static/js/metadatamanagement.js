//$("#MenuTable").empty();

$('#MenuTable').DataTable({
    // 初始显示25条数据
    pageLength: 25,
    // 禁止排序
    ordering : false,
    // 国际化
    "language": {
        "sProcessing": "处理中...",
        "sLengthMenu": "显示 _MENU_ 项结果",
        "sZeroRecords": "没有匹配结果",
        "sInfo": "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
        "sInfoEmpty": "显示第 0 至 0 项结果，共 0 项",
        "sInfoFiltered": "(由 _MAX_ 项结果过滤)",
        "sInfoPostFix": "",
        "sSearch": "搜索:",
        "sUrl": "",
        "sEmptyTable": "表中数据为空",
        "sLoadingRecords": "载入中...",
        "sInfoThousands": ",",
        "oPaginate": {
            "sFirst": "首页",
            "sPrevious": "上页",
            "sNext": "下页",
            "sLast": "末页"
        }
    }

});

//删除菜单节点
$('#delMenu').on('show.bs.modal',function (event) {
    var btnThis=$(event.relatedTarget);//触发事件的按钮
    var modal=$(this);//当前模态框
    var uuid = btnThis.closest('tr').find('td').eq(0).text();
    modal.find("#uuid-del").val(uuid);
});
function removeInfo(obj) {
    var uuid= $('#uuid-del').val();
    console.log(uuid);
    var jsonObj= {
        "uuid": uuid
    };
    $.ajax({
        type: "post",
        url: "/metadata/deleteMenu",
        data: JSON.stringify(jsonObj),
        dataType: "json",
        contentType: "application/json",
        success:function(data){
            alert(data.msg);
            location.reload();
        },
        error:function(data){
            console.log("errorMessage:"+JSON.stringify(data.msg));
        }
    });
    $('#delMenu').modal('hide');
}

//新增下级菜单
$('#addMenu').on('show.bs.modal',function (event) {
    var btnThis=$(event.relatedTarget);//触发事件的按钮
    var modal=$(this);//当前模态框
    var uuid = btnThis.closest('tr').find('td').eq(0).text();
    console.log(uuid);
    modal.find("#uuid-add").val(uuid);

});
function addMenu(obj) {
    var uuid= $('#uuid-add').val();

    //获取模态框数据
    var menuName = $('#menuName').val();
    var menuDescription = $('#menuDescription').val();

    // 获取选择框值
    var select = document.getElementById("menuClassification");
    var index = select.selectedIndex;
    var menuClassification = select.options[index].text;
    console.log(uuid);
    console.log(menuName);
    console.log(menuDescription);
    console.log(menuClassification);

    var jsonObj= {
        "uuid": uuid,
        "menuName": menuName,
        "menuDescription": menuDescription,
        "menuClassification": menuClassification
    };
    $.ajax({
        type: "post",
        url: "/metadata/addMenu",
        data: JSON.stringify(jsonObj),
        dataType: "json",
        contentType: "application/json",
        success:function(data){
            alert("成功添加下级菜单");
            location.reload();
        }
    });
    $('#addMenu').modal('hide');
}

// 编辑菜单
$('#editMenu').on('show.bs.modal',function (event) {
    var btnThis=$(event.relatedTarget);//触发事件的按钮
    var modal=$(this);//当前模态框
    var uuid = btnThis.closest('tr').find('td').eq(0).text();
    var menuName = btnThis.closest('tr').find('td').eq(1).text();
    var menuOrder = btnThis.closest('tr').find('td').eq(3).text();
    var menuDescription = btnThis.closest('tr').find('td').eq(4).text();
    modal.find('#nameEdit').val(menuName);
    modal.find('#orderEdit').val(menuOrder);
    modal.find('#descriptionEdit').val(menuDescription);
    modal.find('#uuid-edit').val(uuid);

});

function saveEdit(obj) {
    //获取模态框数据
    /*var menuName = $('#nameEdit').val();*/
    var menuOrder = $('#orderEdit').val();
    var menuDescription = $('#descriptionEdit').val();
    var uuid = $('#uuid-edit').val();


    // 获取选择框值
    var select = document.getElementById("classificationEdit");
    var index = select.selectedIndex;
    var menuClassification = select.options[index].text;

    var jsonObj= {
        /*'menuName':menuName,*/
        "uuid": uuid,
        "menuOrder": menuOrder,
        "menuDescription": menuDescription,
        "menuClassification": menuClassification,
    };
    $.ajax({
        type: "post",
        url: "/metadata/saveMenuEdit",
        data: JSON.stringify(jsonObj),
        dataType: "json",
        contentType: "application/json",
        success:function(data){
            alert("成功修改菜单");
            location.reload();
        }
    });
    $('#editMenu').modal('hide');
}

// 引入模板
$('#addTemplate').on('show.bs.modal',function (event) {
    var btnThis=$(event.relatedTarget);//触发事件的按钮
    var modal=$(this);//当前模态框
    var uuid = btnThis.closest('tr').find('td').eq(0).text();
    modal.find("#uuid-template").val(uuid);

});

function saveAdd(obj) {
    var uuid = $('#uuid-template').val();
    console.log(uuid);
    var tableName = $('#tableName').val();
    console.log(tableName);

    // 获取选择框值
    var select = document.getElementById("templateName");
    var index = select.selectedIndex;
    var templateName = select.options[index].text;
    console.log(templateName);

    var jsonObj= {
        "uuid": uuid,
        "templateName": templateName,
        "tableName": tableName
    };
    $.ajax({
        type: "post",
        url: "/metadata/addTemplate",
        data: JSON.stringify(jsonObj),
        dataType: "json",
        contentType: "application/json",
        success:function(data){
            alert(data.msg);
            location.reload();
        },
        error:function(data){
            console.log("errorMessage:"+JSON.stringify(data.msg));
        }
    });
    $('#addTemplate').modal('hide');
}

$(document).ready(function(){

});
