
//$("#DepartmentTable").empty();
$('#DepartmentTable').bootstrapTable({
    url:'/departmentSerach',  //请求后台url
    method:'get',
    showRefresh:false, //是否显示刷新按钮
    cardView: false,   //是否显示详细视图
    showToggle : false, // 是否显示详细视图和列表视图的切换按钮
    showColumns : false, // 是否显示列操作按钮
    detailView : false, // 是否显示详细视图
    striped : true, // 设置为true会有隔行变色效果
    dataType : "json", // 服务器返回的数据类型
    pagination : true, // 设置为true会在底部显示分页条
    singleSelect : true, // 设置为true将禁止多选
    clickToSelect : true, // 是否启用点击选中行
    pageSize : 10, // 如果设置了分页，每页数据条数
    pageNumber : 1, // 如果设置了分布，首页页码
    pageList: [5, 10, 15, 20],  //可供选择的每页的行数
    search : false, // 是否显示搜索框
    sidePagination : "server", // 设置在哪里进行分页，可选值为"client" 或者 "server"
    contentType: "application/json;charset=utf-8",
    queryParams: function (params) {
        return{
            pageSize : params.limit, // 每页显示数量
            offset : params.offset, // 起始索引
        }
    },
    columns : [ {
        title : '序号',
        field : 'id',
        align : 'center',
        valign : 'center',
        width:"100px",
        formatter : function (value, row, index) {
            var pageSize = $('#DepartmentTable').bootstrapTable('getOptions').pageSize;     //通过table的#id 得到每页多少条
            var pageNumber = $('#DepartmentTable').bootstrapTable('getOptions').pageNumber; //通过table的#id 得到当前第几页
            return pageSize * (pageNumber - 1) + index + 1;    // 返回每条的序号： 每页条数 *（当前页 - 1 ）+ 序号
        }

    },{
        title : '代码',
        field : 'code',
        align : 'center',
        valign : 'center',
        width:"100px",
    }, {
        title : '名称',
        field : 'name',
        align : 'center',
        valign : 'center',
        width:"150px",
    },  {
        title : '父级',
        field : 'parent',
        align : 'center',
        valign : 'center',
        width:"150px",
    },  {
        title : '主管',
        field : 'charger',
        align : 'center',
        valign : 'center',
        width:"150px",
    }, {
        title : '联系电话',
        field : 'phone',
        align : 'center',
        valign : 'center',
        width:"150px",
    }, {
        title : '传真',
        field : 'fax',
        align : 'center',
        valign : 'center',
        width:"150px",
    }, {
        title : '备注',
        field : 'comment',
        align : 'center',
        valign : 'center',
        width:"150px",
    }, {
        title : '操作',
        field : 'operation',
        width:"200px",
        formatter : function (value, row, index) {
            return[ '<button id="table_edit" class="btn btn-primary btn-xs btn-info glyphicon glyphicon-pencil" ' +
            'data-toggle="modal" data-target="#editModal"  type="button">编辑</button>',
                '<button id="del" class="btn btn-primary btn-xs btn-danger glyphicon glyphicon-remove" data-toggle="modal" data-target="#delModal" type="button">删除</button>',
            ].join("")
        }
    },]
});

//把数据提交到编辑模态框
function editInfo(obj) {
    var id=$(obj).attr("id");
    var code=document.getElementById("DepartmentTable").rows[id].cells[1].innerText;
    var name=document.getElementById("DepartmentTable").rows[id].cells[1].innerText;
    $('#codeEdit').val(code);
    $('#nameEdit').val(name);
    $('#parentEdit').val("计算机");
    $('#chargerEdit').val(tds.eq(4).text());
    $('#phoneEdit').val(tds.eq(5).text());
    $('#faxEdit').val(tds.eq(6).text());
    $('#commentEdit').val(tds.eq(7).text());
    // var sex = $('#sex').val(tds.eq(2).text());
    // if (sex == '女') {
    //     document.getElementById('women').checked = true;
    // } else {
    //     document.getElementById('man').checked = true;
    //
    //     $('#modal').modal('show');
    // }
    $('#editModal').modal('show');
}

//提交更改
function save(obj) {
    //获取模态框数据
    var code = $('#code').val();
    var name = $('#name').val();
    var parent= $('#parent').val();
    var charger = $('#charger').val();
    var phone = $('#phone').val();
    var fax = $('#fax').val();
    var comment = $('#comment').val();
    //var sex = $('input:radio[name="sex"]:checked').val();
    var jsonObj= {
        'code':code,
        'name': name,
        "parent": parent,
        "charger": charger,
        "phone": phone,
        "fax": fax,
        "comment": comment,
    }
    $.ajax({
        type: "post",
        url: "/postDepartementData",
        data: JSON.stringify(jsonObj),
        dataType: "json",
        contentType: "application/json",
        success: function(result) {
            window.location.reload();
            //showData(result);
        }
    });
    $('#addModal').modal('hide');
}

function search(obj) {
     $("#DepartmentTable").bootstrapTable('destroy');
     var text=$("#nametext").val();
    $('#DepartmentTable').bootstrapTable({
        url:'/search',  //请求后台url
        method:'get',
        showRefresh:false, //是否显示刷新按钮
        cardView: false,   //是否显示详细视图
        showToggle : false, // 是否显示详细视图和列表视图的切换按钮
        showColumns : false, // 是否显示列操作按钮
        detailView : false, // 是否显示详细视图
        striped : true, // 设置为true会有隔行变色效果
        dataType : "json", // 服务器返回的数据类型
        pagination : true, // 设置为true会在底部显示分页条
        singleSelect : true, // 设置为true将禁止多选
        clickToSelect : true, // 是否启用点击选中行
        pageSize : 10, // 如果设置了分页，每页数据条数
        pageNumber : 1, // 如果设置了分布，首页页码
        pageList: [5, 10, 15, 20],  //可供选择的每页的行数
        search : false, // 是否显示搜索框
        sidePagination : "server", // 设置在哪里进行分页，可选值为"client" 或者 "server"
        contentType: "application/json;charset=utf-8",
        queryParams: function (params) {
            return{
                text: text,//返回给控制层
                pageSize : params.limit, // 每页显示数量
                offset : params.offset, // 起始索引
            }
        },
        columns : [ {
            title : '序号',
            field : 'id',
            align : 'center',
            valign : 'center',
            width:"100px",
            formatter : function (value, row, index) {
                var pageSize = $('#DepartmentTable').bootstrapTable('getOptions').pageSize;     //通过table的#id 得到每页多少条
                var pageNumber = $('#DepartmentTable').bootstrapTable('getOptions').pageNumber; //通过table的#id 得到当前第几页
                return pageSize * (pageNumber - 1) + index + 1;    // 返回每条的序号： 每页条数 *（当前页 - 1 ）+ 序号
            }

        },{
            title : '代码',
            field : 'code',
            align : 'center',
            valign : 'center',
            width:"100px",
        }, {
            title : '名称',
            field : 'name',
            align : 'center',
            valign : 'center',
            width:"150px",
        },  {
            title : '父级',
            field : 'parent',
            align : 'center',
            valign : 'center',
            width:"150px",
        },  {
            title : '主管',
            field : 'charger',
            align : 'center',
            valign : 'center',
            width:"150px",
        }, {
            title : '联系电话',
            field : 'phone',
            align : 'center',
            valign : 'center',
            width:"150px",
        }, {
            title : '传真',
            field : 'fax',
            align : 'center',
            valign : 'center',
            width:"150px",
        }, {
            title : '备注',
            field : 'comment',
            align : 'center',
            valign : 'center',
            width:"150px",
        }, {
            title : '操作',
            field : 'operation',
            width:"200px",
            formatter : function (value, row, index) {
                return[ '<button id="table_edit" class="btn btn-primary btn-xs btn-info glyphicon glyphicon-pencil" ' +
                'data-toggle="modal" data-target="#editModal"  type="button">编辑</button>',
                    '<button id="table_del" class="btn btn-primary btn-xs btn-danger glyphicon glyphicon-remove" type="button">删除</button>',
                ].join("")
            }
        },]
    });
 }

//向模态框传值
$('#editModal').on('show.bs.modal',function (event) {
    var btnThis=$(event.relatedTarget);//触发事件的按钮
    var modal=$(this);//当前模态框
    //var modalId = btnThis.data('id');   //解析出data-id的内容
    var code= btnThis.closest('tr').find('td').eq(1).text();
    var name= btnThis.closest('tr').find('td').eq(2).text();
    var parent= btnThis.closest('tr').find('td').eq(3).text();
    var charger= btnThis.closest('tr').find('td').eq(4).text();
    var phone= btnThis.closest('tr').find('td').eq(5).text();
    var fax= btnThis.closest('tr').find('td').eq(6).text();
    var comment=btnThis.closest('tr').find('td').eq(7).text();
    modal.find('#codeEdit').val(code);
    modal.find('#nameEdit').val(name);
    modal.find('#parentEdit').val(parent);
    modal.find('#chargerEdit').val(charger);
    modal.find('#phoneEdit').val(phone);
    modal.find('#faxEdit').val(fax);
    modal.find('#commentEdit').val(comment);
});

//保存修改
function saveEdit(obj) {
    //获取模态框数据
    var code = $('#codeEdit').val();
    var name = $('#nameEdit').val();
    var parent=$('#parentEdit').val();
    var charger= $('#chargerEdit').val();
    var phone = $('#phoneEdit').val();
    var fax = $('#faxEdit').val();
    var comment = $('#commentEdit').val();

    var jsonObj= {
        'code':code,
        'name': name,
        'parent': parent,
        "charger": charger,
        "fax": fax,
        "phone": phone,
        "comment": comment,
    };
    $.ajax({
        type: "post",
        url: "/saveDepartmentEdit",
        data: JSON.stringify(jsonObj),
        dataType: "json",
        contentType: "application/json",
        success: function(result) {
            window.location.reload();
        }
    });
    $('#editModal').modal('hide');
}

//删除行
$('#delModal').on('show.bs.modal',function (event) {
    var btnThis=$(event.relatedTarget);//触发事件的按钮
    var modal=$(this);//当前模态框
    var name= btnThis.closest('tr').find('td').eq(2).text();
    var charger= btnThis.closest('tr').find('td').eq(4).text();
    modal.find("#name-del").val(name);
    modal.find("#charger-del").val(charger);
});
function removeInfo(obj) {
    var name= $('#name-del').val();
    var charger=$('#charger-del').val();
    console.log(name);
    var jsonObj= {
        "name": name,
        "charger": charger
    };
    $.ajax({
        type: "post",
        url: "/deleteInfo",
        data: JSON.stringify(jsonObj),
        dataType: "json",
        contentType: "application/json",
        success: function(result) {
            window.location.reload();
        }
    });
    $('#delModal').modal('hide');
}