

$('#StateTable').bootstrapTable({
    url:'/stateSearch',  //请求后台url
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
            var pageSize = $('#StateTable').bootstrapTable('getOptions').pageSize;     //通过table的#id 得到每页多少条
            var pageNumber = $('#StateTable').bootstrapTable('getOptions').pageNumber; //通过table的#id 得到当前第几页
            return pageSize * (pageNumber - 1) + index + 1;    // 返回每条的序号： 每页条数 *（当前页 - 1 ）+ 序号
        }

    },{
        title : '状态名',
        field : 'name',
        align : 'center',
        valign : 'center',
        width:"300px",
    }, {
        title : '状态来源',
        field : 'source',
        align : 'center',
        valign : 'center',
        width:"500px",
    },  {
        title : '状态解释',
        field : 'exp',
        align : 'center',
        valign : 'center',
        width:"500px",
    },  {
        title : '范围下限',
        field : 'min',
        align : 'center',
        valign : 'center',
        width:"100px",
    },  {
        title : '范围上限',
        field : 'max',
        align : 'center',
        valign : 'center',
        width:"100px",
    },  {
        title : '真实数值',
        field : 'num',
        align : 'center',
        valign : 'center',
        width:"100px",
    },  {
        title : '过低动作',
        field : 'less',
        align : 'center',
        valign : 'center',
        width:"200px",
    },  {
        title : '适合动作',
        field : 'fit',
        align : 'center',
        valign : 'center',
        width:"200px",
    },  {
        title : '过高动作',
        field : 'more',
        align : 'center',
        valign : 'center',
        width:"200px",
    },  {
        title : '是否执行',
        field : 'bool',
        align : 'center',
        valign : 'center',
        width:"100px",
    }, {
        title : '插件名称',
        field : 'plug',
        align : 'center',
        valign : 'center',
        width:"200px",
    }, {
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
    var name=document.getElementById("StateTable").rows[id].cells[1].innerText;
    $('#nameEdit').val(name);
    $('#sourceEdit').val(tds.eq(2).text());
    $('#explainEdit').val(tds.eq(3).text());
    $('#minEdit').val(tds.eq(4).text());
    $('#maxEdit').val(tds.eq(5).text());
    $('#numEdit').val(tds.eq(6).text());
    $('#lessEdit').val(tds.eq(7).text());
    $('#fitEdit').val(tds.eq(8).text());
    $('#moreEdit').val(tds.eq(9).text());
    $('#boolEdit').val(tds.eq(10).text());
    $('#plugEdit').val(tds.eq(11).text());

    $('#editModal').modal('show');
}

//提交添加
function save(obj) {
    //获取模态框数据
    var name = $('#name').val();
    var source = $('#source').val();
    var explain = $('#explain').val();
    var min = $('#min').val();
    var max = $('#max').val();
    var num = $('#num').val();
    var less = $('#less').val();
    var fit = $('#fit').val();
    var more = $('#more').val();
    var bool = $('#bool').val();
    var plug = $('#plug').val();


    var jsonObj= {
        'name': name,
        "source": source,
        "explain": explain,
        "min": min,
        "max": max,
        "num": num,
        "less": less,
        "fit": fit,
        "more": more,
        "bool": bool,
        "plug": plug
    };
    $.ajax({
        type: "post",
        url: "/postStateData",
        data: JSON.stringify(jsonObj),
        dataType: "json",
        contentType: "application/json",
        success: function(result) {
            window.location.reload();
        }
    });
    $('#addModal').modal('hide');
}

function search(obj) {
     $("#StateTable").bootstrapTable('destroy');
     var text=$("#nametext").val();
    $('#StateTable').bootstrapTable({
        url:'/searchState',  //请求后台url
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
                var pageSize = $('#StateTable').bootstrapTable('getOptions').pageSize;     //通过table的#id 得到每页多少条
                var pageNumber = $('#StateTable').bootstrapTable('getOptions').pageNumber; //通过table的#id 得到当前第几页
                return pageSize * (pageNumber - 1) + index + 1;    // 返回每条的序号： 每页条数 *（当前页 - 1 ）+ 序号
            }

        },{
            title : '状态名',
            field : 'name',
            align : 'center',
            valign : 'center',
            width:"300px",
        }, {
            title : '状态来源',
            field : 'source',
            align : 'center',
            valign : 'center',
            width:"500px",
        },  {
            title : '状态解释',
            field : 'exp',
            align : 'center',
            valign : 'center',
            width:"500px",
        },  {
            title : '范围下限',
            field : 'min',
            align : 'center',
            valign : 'center',
            width:"100px",
        },  {
            title : '范围上限',
            field : 'max',
            align : 'center',
            valign : 'center',
            width:"100px",
        },  {
            title : '真实数值',
            field : 'num',
            align : 'center',
            valign : 'center',
            width:"100px",
        },  {
            title : '过低动作',
            field : 'less',
            align : 'center',
            valign : 'center',
            width:"200px",
        },  {
            title : '适合动作',
            field : 'fit',
            align : 'center',
            valign : 'center',
            width:"200px",
        },  {
            title : '过高动作',
            field : 'more',
            align : 'center',
            valign : 'center',
            width:"200px",
        },  {
            title : '是否执行',
            field : 'bool',
            align : 'center',
            valign : 'center',
            width:"100px",
        },   {
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
    var source= btnThis.closest('tr').find('td').eq(2).text();
    var explain= btnThis.closest('tr').find('td').eq(3).text();
    var min= btnThis.closest('tr').find('td').eq(4).text();
    var max= btnThis.closest('tr').find('td').eq(5).text();
    var num= btnThis.closest('tr').find('td').eq(6).text();
    var less= btnThis.closest('tr').find('td').eq(7).text();
    var fit= btnThis.closest('tr').find('td').eq(8).text();
    var more= btnThis.closest('tr').find('td').eq(9).text();
    var bool= btnThis.closest('tr').find('td').eq(10).text();
    var plug= btnThis.closest('tr').find('td').eq(11).text();
    modal.find('#nameEdit').val(name);
    modal.find('#sourceEdit').val(source);
    modal.find('#explainEdit').val(explain);
    modal.find('#minEdit').val(min);
    modal.find('#maxEdit').val(max);
    modal.find('#numEdit').val(num);
    modal.find('#lessEdit').val(less);
    modal.find('#fitEdit').val(fit);
    modal.find('#moreEdit').val(more);
    modal.find('#boolEdit').val(bool);
    modal.find('#plugEdit').val(plug);
});

//保存修改
function saveEdit(obj) {
    //获取模态框数据
    var name = $('#nameEdit').val();
    var source = $('#sourceEdit').val();
    var explain = $('#explainEdit').val();
    var min = $('#minEdit').val();
    var max = $('#maxEdit').val();
    var num = $('#numEdit').val();
    var less = $('#lessEdit').val();
    var fit = $('#fitEdit').val();
    var more = $('#moreEdit').val();
    var bool = $('#boolEdit').val();
    var plug = $('#plugEdit').val();

    var jsonObj= {
        'name': name,
        "source": source,
        "explain": explain,
        "min": min,
        "max": max,
        "num": num,
        "less": less,
        "fit": fit,
        "more": more,
        "bool": bool,
        "plug": plug
    };
    $.ajax({
        type: "post",
        url: "/saveStateEdit",
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
    var name= btnThis.closest('tr').find('td').eq(1).text();
    var type= btnThis.closest('tr').find('td').eq(2).text();
    modal.find("#name-install").val(name);
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
        url: "/deleteStateInfo",
        data: JSON.stringify(jsonObj),
        dataType: "json",
        contentType: "application/json",
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
        url: "/installState",
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