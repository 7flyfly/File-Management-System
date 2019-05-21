

$('#ActionTable').bootstrapTable('destroy').css({"table-layout":"fixed"}).bootstrapTable({
    url:'/actionSearch',  //请求后台url
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
            var pageSize = $('#ActionTable').bootstrapTable('getOptions').pageSize;     //通过table的#id 得到每页多少条
            var pageNumber = $('#ActionTable').bootstrapTable('getOptions').pageNumber; //通过table的#id 得到当前第几页
            return pageSize * (pageNumber - 1) + index + 1;    // 返回每条的序号： 每页条数 *（当前页 - 1 ）+ 序号
        }

    },{
        title : '动作名',
        field : 'name',
        align : 'center',
        valign : 'center',
        width:"100px",
    }, {
        title : '类型',
        field : 'type',
        align : 'center',
        valign : 'center',
        width:"100px",
    },  {
        title : '解释',
        field : 'exp',
        align : 'center',
        valign : 'center',
        width:"300px",
    },  {
        title : '返回信息',
        field : 'message',
        align : 'center',
        valign : 'center',
        width:"300px",
    }, {
        title : '插件名称',
        field : 'plug',
        align : 'center',
        valign : 'center',
        width:"200px",
    },{
        title : '操作',
        field : 'operation',
        width:"100px",
        formatter : function (value, row, index) {
            return[ '<button id="table_edit" class="btn btn-primary btn-xs btn-info glyphicon glyphicon-pencil" ' +
            'data-toggle="modal" data-target="#editModal"  type="button">编辑</button>',
                '<button id="del" class="btn btn-primary btn-xs btn-danger glyphicon glyphicon-remove" data-toggle="modal" data-target="#delModal" type="button">删除</button>',
            ].join("")
        }
    },{
        title : '安装插件',
        field : 'operation',
        width:"100px",
        formatter : function (value, row, index) {
            return[
                '<button id="install" class="btn btn-primary btn-xs btn-info glyphicon glyphicon-pencil" data-toggle="modal" data-target="#installModal" type="button">安装</button>',
            ].join("")
        }
    }]
});

//把数据提交到编辑模态框
function editInfo(obj) {
    var id=$(obj).attr("id");
    var name=document.getElementById("ActionTable").rows[id].cells[1].innerText;
    $('#nameEdit').val(name);
    $('#typeEdit').val(tds.eq(2).text());
    $('#explainEdit').val(tds.eq(3).text());
    $('#messageEdit').val(tds.eq(4).text());
    $('#plugEdit').val(tds.eq(5).text());

    $('#editModal').modal('show');
}

//提交更改
function save(obj) {
    //获取模态框数据
    var name = $('#name').val();
    var type = $('#type').val();
    var explain = $('#explain').val();
    var message = $('#message').val();
    var plug = $('#plug').val();

    var jsonObj= {
        'name': name,
        "type": type,
        "explain": explain,
        "message": message,
        "plug": plug
    }
    $.ajax({
        type: "post",
        url: "/postActionData",
        data: JSON.stringify(jsonObj),
        dataType: "json",
        contentType: "application/json",
        async:false,
        success: function(result) {
            window.location.reload();
        }
    });
    $('#addModal').modal('hide');
}

function search(obj) {
     $("#ActionTable").bootstrapTable('destroy');
     var text=$("#nametext").val();
    $('#ActionTable').bootstrapTable({
        url:'/searchAction',  //请求后台url
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
                var pageSize = $('#ActionTable').bootstrapTable('getOptions').pageSize;     //通过table的#id 得到每页多少条
                var pageNumber = $('#ActionTable').bootstrapTable('getOptions').pageNumber; //通过table的#id 得到当前第几页
                return pageSize * (pageNumber - 1) + index + 1;    // 返回每条的序号： 每页条数 *（当前页 - 1 ）+ 序号
            }

        },{
            title : '动作名',
            field : 'name',
            align : 'center',
            valign : 'center',
            width:"100px",
        }, {
            title : '类型',
            field : 'type',
            align : 'center',
            valign : 'center',
            width:"100px",
        },  {
            title : '解释',
            field : 'exp',
            align : 'center',
            valign : 'center',
            width:"300px",
        },  {
            title : '返回信息',
            field : 'message',
            align : 'center',
            valign : 'center',
            width:"300px",
        },  {
            title : '插件名称',
            field : 'plug',
            align : 'center',
            valign : 'center',
            width:"200px",
        },{
            title : '操作',
            field : 'operation',
            width:"100px",
            formatter : function (value, row, index) {
                return[ '<button id="table_edit" class="btn btn-primary btn-xs btn-info glyphicon glyphicon-pencil" ' +
                'data-toggle="modal" data-target="#editModal"  type="button">编辑</button>',
                    '<button id="table_del" class="btn btn-primary btn-xs btn-danger glyphicon glyphicon-remove" type="button">删除</button>',
                ].join("")
            }
        },{
            title : '安装插件',
            field : 'operation',
            width:"100px",
            formatter : function (value, row, index) {
                return[
                    '<button id="install" class="btn btn-primary btn-xs btn-info glyphicon glyphicon-pencil" data-toggle="modal" data-target="#installModal" type="button">安装</button>',
                ].join("")
            }
        }]
    });
 }

//向模态框传值
$('#editModal').on('show.bs.modal',function (event) {
    var btnThis=$(event.relatedTarget);//触发事件的按钮
    var modal=$(this);//当前模态框
    //var modalId = btnThis.data('id');   //解析出data-id的内容
    var name= btnThis.closest('tr').find('td').eq(1).text();
    var type= btnThis.closest('tr').find('td').eq(2).text();
    var explain= btnThis.closest('tr').find('td').eq(3).text();
    var message= btnThis.closest('tr').find('td').eq(4).text();
    var plug= btnThis.closest('tr').find('td').eq(5).text();
    modal.find('#nameEdit').val(name);
    modal.find('#typeEdit').val(type);
    modal.find('#explainEdit').val(explain);
    modal.find('#messageEdit').val(message);
    modal.find('#plugEdit').val(plug);
});

//保存修改
function saveEdit(obj) {
    //获取模态框数据
    var name = $('#nameEdit').val();
    var type=$('#typeEdit').val();
    var explain= $('#explainEdit').val();
    var message = $('#messageEdit').val();
    var plug = $('#plugEdit').val();

    var jsonObj= {
        'name': name,
        "type": type,
        "explain": explain,
        "message": message,
        "plug": plug
    };
    $.ajax({
        type: "post",
        url: "/saveActionEdit",
        data: JSON.stringify(jsonObj),
        dataType: "json",
        async:false,
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
    var name= btnThis.closest('tr').find('td').eq(1).text();
    var type= btnThis.closest('tr').find('td').eq(2).text();
    modal.find("#name-del").val(name);
    modal.find("#type-del").val(type);
});
function removeInfo(obj) {
    var name= $('#name-del').val();
    console.log(name);
    var jsonObj= {
        "name": name,
    };
    $.ajax({
        type: "post",
        url: "/deleteActionInfo",
        data: JSON.stringify(jsonObj),
        dataType: "json",
        contentType: "application/json",
        async:false,
        success: function(result) {
            window.location.reload();
        }
    });
    $('#delModal').modal('hide');
}

//安装插件
$('#installModal').on('show.bs.modal',function (event) {
    var btnThis=$(event.relatedTarget);//触发事件的按钮
    var modal=$(this);//当前模态框
    var name= btnThis.closest('tr').find('td').eq(1).text();
    var type= btnThis.closest('tr').find('td').eq(2).text();
    modal.find("#name-install").val(name);
    modal.find("#type-del").val(type);
});
function install(obj) {
    var name= $('#name-install').val();
    console.log(name);
    var jsonObj= {
        "name": name,
    };
    $.ajax({
        type: "post",
        url: "/installAction",
        data: JSON.stringify(jsonObj),
        dataType: "json",
        contentType: "application/json",
        async:false,
        success: function(result) {
            window.location.reload();
        }
    });
    $('#installModal').modal('hide');
}