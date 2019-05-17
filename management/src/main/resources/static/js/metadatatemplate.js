$('#table_id').DataTable({
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

window.a = 0;
window.arr = [];

function add_tr(obj){
    var tr = "<div class=\"form-group\"><tr>";
    tr += "<td><input type=\"text\" placeholder=\"字段名称\" id=\"fieldName" + a + "\" class=\"form-control\" />";
    tr += "<input type=\"text\" placeholder=\"字段英文名\" id=\"fieldEnglishName" + a + "\" class=\"form-control\" />";
    tr += "<select class=\"form-control\" id=\"fieldType" + a + "\"><option value=\"\">字段类型</option><option value=\"varchar\">varchar</option><option value=\"int\">int</option><option value=\"data\">data</option></select>";
    tr += "<input type=\"text\" placeholder=\"字段最大长度\" id=\"fieldLength" + a + "\" class=\"form-control\" />";
    tr += "<select class=\"form-control\" id=\"fieldIndex" + a + "\"><option value=\"\">字段是否建索引</option><option value=\"true\">true</option><option value=\"false\">false</option></select>";
    tr += "<select class=\"form-control\" id=\"fieldIk" + a + "\"><option value=\"\">字段是否分词</option><option value=\"true\">true</option><option value=\"false\">false</option></select>";
    tr += "<button type=\"button\" class=\"btn bg-primary\"  id=\"addTable\" onclick=\"add_tr(this)\" style=\"margin-top:10px; margin-bottom:10px\">";
    tr += "<span class=\"glyphicon glyphicon-plus\" aria-hidden=\"true\"></span>添加字段";

    $(obj).parent().append(tr);
    arr.push(a);
    a++;
}

//提交更改
function update(obj) {
    //获取模态框数据
    var templateName = $('#templateName').val();
    var templateDescription = $('#templateDescription').val();

    // 获取复选框值
    var obj = document.getElementsByName("fieldSelected");
    var fieldSelected="";
    for (k in obj){
        if(obj[k].checked){
            fieldSelected += obj[k].value + "||";
        }
    }

    var fieldNames = [];
    var fieldEnglishNames = [];
    var fieldTypes = [];
    var fieldLengths = [];
    var fieldIndexes = [];
    var fieldIks = [];

    for(var i=0;i<a;i++){
        eval("fieldNames.push($('#fieldName" + i.toString() + "').val());");
        eval("fieldEnglishNames.push($('#fieldEnglishName" + i.toString() + "').val());");
        eval("var selectType" + i.toString() + " = document.getElementById(\"fieldType" + i.toString() +"\");");
        eval("var indexType" + i.toString() + " = selectType" + i.toString() + ".selectedIndex;");
        eval("var fieldType" + i.toString() + " = selectType" + i.toString() + ".options[indexType" + i.toString() + "].text");
        eval("fieldTypes.push(fieldType" + i.toString() + ");");

        eval("fieldLengths.push($('#fieldLength" + i.toString() + "').val());");

        eval("var selectIndex" + i.toString() + " = document.getElementById(\"fieldIndex" + i.toString() +"\");");
        eval("var indexIndex" + i.toString() + " = selectIndex" + i.toString() + ".selectedIndex;");
        eval("var fieldIndex" + i.toString() + " = selectIndex" + i.toString() + ".options[indexIndex" + i.toString() + "].text");
        eval("fieldIndexes.push(fieldIndex" + i.toString() + ");");

        eval("var selectIk" + i.toString() + " = document.getElementById(\"fieldIk" + i.toString() +"\");");
        eval("var indexIk" + i.toString() + " = selectIndex" + i.toString() + ".selectedIndex;");
        eval("var fieldIk" + i.toString() + " = selectIndex" + i.toString() + ".options[indexIk" + i.toString() + "].text;");
        eval("fieldIks.push(fieldIk" + i.toString() + ");");
    }

    var stringA = a.toString();

    var jsonObj ={
        'templateName': templateName,
        'templateDescription': templateDescription,
        "fieldNames": fieldNames,
        "fieldEnglishNames": fieldEnglishNames,
        "fieldTypes": fieldTypes,
        "fieldLengths": fieldLengths,
        "fieldIndexes": fieldIndexes,
        "fieldIks": fieldIks,
        "stringA": stringA,
        "fieldSelected": fieldSelected
    };
    console.log(jsonObj);

    $.ajax({
        type: "post",
        url: '/metadata/postTemplateData',
        data: JSON.stringify(jsonObj),
        dataType: "json",
        contentType: "application/json",
        success:function(data) {
            alert(data);
            location.reload();
        }
    });
    $('#modal').modal('hide');
}

