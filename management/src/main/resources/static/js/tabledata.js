$('#table_id').DataTable({
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

$('#uploadFile').fileinput({
     showUpload : true,
     showRemove : false,
     uploadUrl:"${ctx}/upload/one/8",
     language : 'zh',
     allowedPreviewTypes : [ 'pdf' ],
     allowedFileTypes : ['pdf'],
     autoReplace:true,
     maxFileCount:1,
     browseClass: "btn btn-primary", //按钮样式
     dropZoneEnabled: true,//shidou显示拖拽
     initialPreviewAsData: true,
     initialPreviewFileType: 'pdf',
     <c:if test="${periodicalResource.attachment!=null&&periodicalResource.attachment!=''}">initialPreview:["${periodicalResource.attachment}"]</c:if>
 }).on("fileuploaded", function (event, data) {
     $("#attachment").val(data.response.attachment);
 });

//新增下级菜单
$('#addData').on('show.bs.modal',function (event) {
    var btnThis=$(event.relatedTarget);//触发事件的按钮
    var modal=$(this);//当前模态框
    var test = [$('#formData1').attr('placeholder'),$('#formData1').val()];
    console.log(test);

});
function addData(obj) {
    var length = document.getElementById("tr1").childNodes.length;
    var value = [];
    for(i=0;i<length;i++){
        var str = "formData" + i.toString();
        if(document.getElementById(str)){
            eval("value.push($('#" + str + "').attr('placeholder'))");
            eval("value.push($('#" + str + "').val())")
        }
    }
    console.log(value);
    var tableId = $("#tableId").val();

    jsonObj = {
        value: value,
        tableId: tableId,
        // 最近修改时间+是否删除+操作+附件
        length: length-4
    }
    eval(jsonObj);

    $.ajax({
        type: "post",
        url: "/mywork/addData",
        data: JSON.stringify(jsonObj),
        dataType: "json",
        contentType: "application/json",
        success:function(data){
            alert("成功新增数据");
            location.reload();
        },
        error:function(data){
            console.log("errorMessage:"+JSON.stringify(data.msg));
        }
    });
    $('#addData').modal('hide');
}

//删除数据
$('#delData').on('show.bs.modal',function (event) {
    var btnThis=$(event.relatedTarget);//触发事件的按钮
    var modal=$(this);//当前模态框
    var documentNoIndex = $("#documentNoIndex").val();
    var documentNo = btnThis.closest('tr').find('td').eq(documentNoIndex).text();
    modal.find("#documentNo-del").val(documentNo);
});
function removeInfo(obj) {
    var tableId = $("#tableId").val();
    var documentNo= $('#documentNo-del').val();
    console.log(documentNo);
    var jsonObj= {
        "tableId": tableId,
        "documentNo": documentNo
    };
    $.ajax({
        type: "post",
        url: "/mywork/deleteData",
        data: JSON.stringify(jsonObj),
        dataType: "json",
        contentType: "application/json",
        success:function(data){
            alert("成功删除该数据");
            location.reload();
        },
        error:function(data){
            console.log("errorMessage:"+JSON.stringify(data.msg));
        }
    });
    $('#delData').modal('hide');
}

// 编辑菜单
$('#editData').on('show.bs.modal',function (event) {
    var btnThis=$(event.relatedTarget);//触发事件的按钮
    var modal=$(this);//当前模态框
    var documentNoIndex = $("#documentNoIndex").val();
    var documentNo = btnThis.closest('tr').find('td').eq(documentNoIndex).text();
    modal.find("#documentNo-edit").val(documentNo);

});

function saveEdit(obj) {
    var length = document.getElementById("tr1").childNodes.length;
    var value = [];
    var documentNo= $('#documentNo-edit').val();
    for(i=0;i<length;i++){
        var str = "edit" + i.toString();
        if(document.getElementById(str)){
            eval("value.push($('#" + str + "').attr('placeholder'))");
            eval("value.push($('#" + str + "').val())");
        }
    }
    var tableId = $("#tableId").val();
    console.log(length);
    console.log(documentNo);
    console.log(value);
    console.log(tableId);


    jsonObj = {
        documentNo: documentNo,
        value: value,
        tableId: tableId,
        // 最近修改时间+是否删除+档案号+表格号+操作
        length: length-5
    }
    eval(jsonObj);

    $.ajax({
        type: "post",
        url: "/mywork/editData",
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
    $('#editData').modal('hide');
}