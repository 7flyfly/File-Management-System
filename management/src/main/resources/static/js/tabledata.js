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

var tableData = {
    tableId: "",
    documentNo: "",
    documentNoIndex: "",
    addAnnex:function(obj){
        tableData.tableId = $("#tableId").val();
        tableData.documentNoIndex = parseInt($("#documentNoIndex").val())+1;
        tableData.documentNo = $(obj).parent().parent().find('td').eq(tableData.documentNoIndex).text();
        $('#uploadFile').fileinput({
             uploadUrl: "/mywork/addAnnex",  //上传的地址
             language: "zh",                 //设置语言
             uploadAsync: false,              //异步上传
             showCaption: false,              //是否显示标题
             showRemove: true,               //是否显示移除按钮
             showPreview : true,             //是否显示预览按钮
             showUpload : true,              //是否显示上传按钮
             browseClass: "btn btn-primary", //按钮样式
             dropZoneEnabled: true,         //是否显示拖拽区域
             autoReplace:true,              //是否替换当前文件
             enctype: 'multipart/form-data',
             allowedFileExtensions: ["bmp","jpeg","png","jpg","txt","doc","docx","pdf"], //接收的文件后缀
             maxFileCount: 1,                        //最大上传文件数限制
             minFileCount: 1,
             uploadExtraData: {
                 tableId: tableData.tableId,
                 documentNo: tableData.documentNo,
             },
         }).on("filebatchselected",function(event, data) {
             $(event.target).parent().siblings('.fileinput-upload').show();
        }).on("filebatchuploadsuccess", function(event, data, msg) {

        }).on('fileerror', function(event, data, msg) {  //文件上传失败
           // 清除当前的预览图
           // $(event.target).parent().siblings('.fileinput-upload').hide();
           // $(event.target).fileinput('clear').fileinput('unlock');
           // $(event.target).parent().siblings('.fileinput-remove').hide();
        });
    }
}

// 全选
function allcheck() {
     var nn = $("#allboxs").is(":checked"); //判断th中的checkbox是否被选中，如果被选中则nn为true，反之为false
     if(nn == true) {
         var namebox = $("input[name^='boxs']");  //获取name值为boxs的所有input
         for(i = 0; i < namebox.length; i++) {
             namebox[i].checked=true;    //js操作选中checkbox
         }
     }
     if(nn == false) {
         var namebox = $("input[name^='boxs']");
         for(i = 0; i < namebox.length; i++) {
             namebox[i].checked=false;
         }
     }
}
function confirmAddModal(obj){
    location.reload();
}


//新增数据
$('#addData').on('show.bs.modal',function (event) {
    var btnThis=$(event.relatedTarget);//触发事件的按钮
    var modal=$(this);//当前模态框
    var test = [$('#formData1').attr('placeholder'),$('#formData1').val()];
    console.log(test);

});


//var select = document.getElementById("menuClassification");
//var index = select.selectedIndex;
//var menuClassification = select.options[index].text;

function addData(obj) {
    var length = document.getElementById("tr1").childNodes.length;
    var value = [];
    for(i=0;i<length;i++){
        var str = "formData" + i.toString();
        console.log(document.getElementById(str));
        if(document.getElementById(str)){
            if (document.getElementById(str).childNodes.length != 1) {
                 eval("value.push($('#" + str + "').attr('placeholder'))");
                 eval("value.push($('#" + str + "').val())");
            }else{
                var select = document.getElementById("str");
                var index = select.selectedIndex;
                eval("value.push($('#" + str + "').attr('placeholder'))");
                eval("value.push(select.option[" + index + "].text)");
            }
        }
        /*if(document.getElementById(str)){
            eval("value.push($('#" + str + "').attr('placeholder'))");
            eval("value.push($('#" + str + "').val())")
        }*/
    }
    console.log(value);
    var tableId = $("#tableId").val();

    jsonObj = {
        value: value,
        tableId: tableId,
        // 最近修改时间+[是否删除]+操作+附件+[表格号]+序号+选择框
        length: length-5
    }
    eval(jsonObj);

    $.ajax({
        type: "post",
        url: "/mywork/addData",
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
    $('#addData').modal('hide');
}

//删除数据
$('#delData').on('show.bs.modal',function (event) {
    var btnThis=$(event.relatedTarget);//触发事件的按钮
    var modal=$(this);//当前模态框
    // 1是第一列全选框
    var documentNoIndex = parseInt($("#documentNoIndex").val())+1;
    console.log(documentNoIndex);
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
            alert(data.msg);
            location.reload();
        },
        error:function(data){
            console.log("errorMessage:"+JSON.stringify(data.msg));
        }
    });
    $('#delData').modal('hide');
}

//批量删除数据
function removeInfos(obj) {
    var tableId = $("#tableId").val();
    var documentNos = [];
    var documentNoIndex = parseInt($("#documentNoIndex").val())+1;
    $("#table_id").find(":checkbox:checked").each(function(){
        var val = $(this).parent();
        for(i=0;i<documentNoIndex;i++){
            val = val.next();
        }
        val = val.text();
        documentNos.push(val);
    });
    console.log(documentNos);
    var jsonObj= {
        "tableId": tableId,
        "documentNos": documentNos
    };
    $.ajax({
        type: "post",
        url: "/mywork/deleteDatas",
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
    $('#delDatas').modal('hide');
}

// 编辑数据
$('#editData').on('show.bs.modal',function (event) {
    var btnThis=$(event.relatedTarget);//触发事件的按钮
    var modal=$(this);//当前模态框
    // 1是第一列全选框
    var documentNoIndex = parseInt($("#documentNoIndex").val())+1;
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
        // 最近修改时间+[是否删除]+档案号+[表格号]+操作+序号+附件+选择框
        length: length-6
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

//删除附件
$('#delAnnex').on('show.bs.modal',function (event) {
    var btnThis=$(event.relatedTarget);//触发事件的按钮
    var modal=$(this);//当前模态框
    // 1是第一列全选框
    var documentNoIndex = parseInt($("#documentNoIndex").val())+1;
    var documentNo = btnThis.closest('tr').find('td').eq(documentNoIndex).text();
    modal.find("#documentNo-annexdel").val(documentNo);
});
function fileDel(obj) {
    var tableId = $("#tableId").val();
    var documentNo= $('#documentNo-annexdel').val();
    var annexName = $('#annexName').val();
    var jsonObj= {
        "tableId": tableId,
        "documentNo": documentNo,
        "annexName": annexName
    };
    $.ajax({
        type: "post",
        url: "/mywork/deleteAnnex",
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
    $('#delAnnex').modal('hide');
}

// 移交归档
$('#archiving').on('show.bs.modal',function (event) {
    var btnThis=$(event.relatedTarget);//触发事件的按钮
    var modal=$(this);//当前模态框
    // 1是第一列全选框
    var documentNoIndex = parseInt($("#documentNoIndex").val())+1;
    var documentNo = btnThis.closest('tr').find('td').eq(documentNoIndex).text();
    modal.find("#documentNo-archiving").val(documentNo);

    var length = document.getElementById("tr1").childNodes.length;
    var value = "";
    // 不需要第一列的数据
    for(i=1;i<length-1;i++){
        value += document.getElementById("tr1").childNodes[i].innerHTML + "||";
        value += btnThis.closest('tr').find('td').eq(i).text() + "||";
    }
    value += document.getElementById("tr1").childNodes[length-1].innerHTML + "||";
    value += btnThis.closest('tr').find('td').eq(length-1).text();
    modal.find("#value").val(value);
});
function archivingChange(obj) {
    var tableId = $("#tableId").val();
    var documentNo= $('#documentNo-archiving').val();
    var length = document.getElementById("tr1").childNodes.length;
    var value = $('#value').val();
    console.log(value);
    var jsonObj= {
        "tableId": tableId,
        "documentNo": documentNo,
        // 不需要第一列
        "length": length-1,
        "value": value
    };
    $.ajax({
        type: "post",
        url: "/mywork/archiving",
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
    $('#archiving').modal('hide');
}

// 批量移交归档
function archivingsChange(obj) {
    var tableId = $("#tableId").val();
    var length = document.getElementById("tr1").childNodes.length;
    var documentNos = [];
    var documentNoIndex = parseInt($("#documentNoIndex").val())+1;
    $("#table_id").find(":checkbox:checked").each(function(){
        var val = $(this).parent();
        for(i=0;i<documentNoIndex;i++){
            val = val.next();
        }
        val = val.text();
        documentNos.push(val);
    });
    console.log(documentNos);
    var values = [];
    $("#table_id").find(":checkbox:checked").each(function(){
        var value = $(this).parent();
        for(i=1;i<length;i++){
            values.push(document.getElementById("tr1").childNodes[i].innerHTML)
            value = value.next();
            values.push(value.text());
        }
    });
    console.log(length);
    console.log(values);
    var jsonObj= {
        "tableId": tableId,
        "documentNos": documentNos,
        // 不需要第一列
        "length": length-1,
        "values": values
    };
    $.ajax({
        type: "post",
        url: "/mywork/archivings",
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
    $('#archivings').modal('hide');
}