//var id="";
//添加字典函数

 // $('#myTab a').click(function () {
 //     $(this).addClass('active').siblings().removeClass('active');
 //     id = $(this).attr('data-id');
 //     $('#myTab a:first').tab('show');
 //    switch(id){
 //        case "1":
 //            $('#myTab a:first').tab('show');
 //            $('#rootTable').bootstrapTable({
 //                url: '/showCase',  //请求后台url
 //                cache: false,
 //                method: 'get',
 //                showRefresh: false, //是否显示刷新按钮
 //                cardView: false,   //是否显示详细视图
 //                showToggle: false, // 是否显示详细视图和列表视图的切换按钮
 //                showColumns: false, // 是否显示列操作按钮
 //                detailView: false, // 是否显示详细视图
 //                striped: true, // 设置为true会有隔行变色效果
 //                dataType: "json", // 服务器返回的数据类型
 //                pagination: true, // 设置为true会在底部显示分页条
 //                singleSelect: true, // 设置为true将禁止多选
 //                clickToSelect: true, // 是否启用点击选中行
 //                pageSize: 10, // 如果设置了分页，每页数据条数
 //                pageNumber: 1, // 如果设置了分布，首页页码
 //                pageList: [5, 10, 15, 20],  //可供选择的每页的行数
 //                search: false, // 是否显示搜索框
 //                sidePagination: "server", // 设置在哪里进行分页，可选值为"client" 或者 "server"
 //                contentType: "application/json;charset=utf-8",
 //                queryParams: function (params) {
 //                    return {
 //                        pageSize: params.limit, // 每页显示数量
 //                        offset: params.offset, // 起始索引
 //                    }
 //                },
 //                columns: [{
 //                    title: '序号',
 //                    field: 'id',
 //                    align: 'center',
 //                    valign: 'center',
 //                    formatter: function (value, row, index) {
 //                        var pageSize = $('#rootTable').bootstrapTable('getOptions').pageSize;     //通过table的#id 得到每页多少条
 //                        var pageNumber = $('#rootTable').bootstrapTable('getOptions').pageNumber; //通过table的#id 得到当前第几页
 //                        return pageSize * (pageNumber - 1) + index + 1;    // 返回每条的序号： 每页条数 *（当前页 - 1 ）+ 序号
 //                    }
 //
 //                }, {
 //                    title: '代码',
 //                    field: 'code',
 //                    align: 'center',
 //                    valign: 'center',
 //                }, {
 //                    title: '名称',
 //                    field: 'name',
 //                    align: 'center',
 //                    valign: 'center',
 //                }, {
 //                    title: '父级',
 //                    field: 'parent',
 //                    align: 'center',
 //                    valign: 'center',
 //                }, {
 //                    title: '顺序',
 //                    field: 'sequence',
 //                    align: 'center',
 //                    valign: 'center',
 //                }, {
 //                    title: '备注',
 //                    field: 'comment',
 //                    align: 'center',
 //                    valign: 'center',
 //                }, {
 //                    title: '操作',
 //                    field: 'operation',
 //                    formatter: function (value, row, index) {
 //                        return ['<button id="table_edit" class="btn btn-primary btn-xs btn-info glyphicon glyphicon-pencil" ' +
 //                        'data-toggle="modal" data-target="#editModal" type="button">编辑</button>',
 //                            '<button id="del" class="btn btn-primary btn-xs btn-danger glyphicon glyphicon-remove" data-toggle="modal" data-target="#delModal" type="button">删除</button>',
 //                        ].join("")
 //                    }
 //                },]
 //            });
 //            break;
 //        case "2":
 //            $('#myTab li:eq(1)a').tab('show');
 //            $('#table2').bootstrapTable({
 //                url: '/showClassfiy',  //请求后台url
 //                method: 'get',
 //                cache: false,
 //                showRefresh: false, //是否显示刷新按钮
 //                cardView: false,   //是否显示详细视图
 //                showToggle: false, // 是否显示详细视图和列表视图的切换按钮
 //                showColumns: false, // 是否显示列操作按钮
 //                detailView: false, // 是否显示详细视图
 //                striped: true, // 设置为true会有隔行变色效果
 //                dataType: "json", // 服务器返回的数据类型
 //                pagination: true, // 设置为true会在底部显示分页条
 //                singleSelect: true, // 设置为true将禁止多选
 //                clickToSelect: true, // 是否启用点击选中行
 //                pageSize: 10, // 如果设置了分页，每页数据条数
 //                pageNumber: 1, // 如果设置了分布，首页页码
 //                pageList: [5, 10, 15, 20],  //可供选择的每页的行数
 //                search: false, // 是否显示搜索框
 //                sidePagination: "server", // 设置在哪里进行分页，可选值为"client" 或者 "server"
 //                contentType: "application/json;charset=utf-8",
 //                queryParams: function (params) {
 //                    return {
 //                        pageSize: params.limit, // 每页显示数量
 //                        offset: params.offset, // 起始索引
 //                    }
 //                },
 //                columns: [{
 //                    title: '序号',
 //                    field: 'id',
 //                    align: 'center',
 //                    valign: 'center',
 //                    formatter: function (value, row, index) {
 //                        var pageSize = $('#table2').bootstrapTable('getOptions').pageSize;     //通过table的#id 得到每页多少条
 //                        var pageNumber = $('#table2').bootstrapTable('getOptions').pageNumber; //通过table的#id 得到当前第几页
 //                        return pageSize * (pageNumber - 1) + index + 1;    // 返回每条的序号： 每页条数 *（当前页 - 1 ）+ 序号
 //                    }
 //                }, {
 //                    title: '代码',
 //                    field: 'code',
 //                    align: 'center',
 //                    valign: 'center',
 //                }, {
 //                    title: '名称',
 //                    field: 'name',
 //                    align: 'center',
 //                    valign: 'center',
 //                }, {
 //                    title: '父级',
 //                    field: 'parent',
 //                    align: 'center',
 //                    valign: 'center',
 //                }, {
 //                    title: '顺序',
 //                    field: 'sequence',
 //                    align: 'center',
 //                    valign: 'center',
 //                }, {
 //                    title: '备注',
 //                    field: 'comment',
 //                    align: 'center',
 //                    valign: 'center',
 //                }, {
 //                    title: '操作',
 //                    field: 'operation',
 //                    width: "200px",
 //                    formatter: function (value, row, index) {
 //                        return ['<button id="table_edit" class="btn btn-primary btn-xs btn-info glyphicon glyphicon-pencil" ' +
 //                        'data-toggle="modal" data-target="#editModal"  type="button">编辑</button>',
 //                            '<button id="del" class="btn btn-primary btn-xs btn-danger glyphicon glyphicon-remove" data-toggle="modal" data-target="#delModal" type="button">删除</button>',
 //                        ].join("")
 //                    }
 //                },]
 //            });
 //            break;
 //        case "3":
 //            $('#myTab li:eq(2)a').tab('show');
 //            $('#table3').bootstrapTable({
 //                url: '/showArchive',  //请求后台url
 //                method: 'get',
 //                showRefresh: false, //是否显示刷新按钮
 //                cardView: false,   //是否显示详细视图
 //                showToggle: false, // 是否显示详细视图和列表视图的切换按钮
 //                showColumns: false, // 是否显示列操作按钮
 //                detailView: false, // 是否显示详细视图
 //                striped: true, // 设置为true会有隔行变色效果
 //                dataType: "json", // 服务器返回的数据类型
 //                pagination: true, // 设置为true会在底部显示分页条
 //                singleSelect: true, // 设置为true将禁止多选
 //                clickToSelect: true, // 是否启用点击选中行
 //                pageSize: 10, // 如果设置了分页，每页数据条数
 //                pageNumber: 1, // 如果设置了分布，首页页码
 //                pageList: [5, 10, 15, 20],  //可供选择的每页的行数
 //                search: false, // 是否显示搜索框
 //                sidePagination: "server", // 设置在哪里进行分页，可选值为"client" 或者 "server"
 //                contentType: "application/json;charset=utf-8",
 //                queryParams: function (params) {
 //                    return {
 //                        pageSize: params.limit, // 每页显示数量
 //                        offset: params.offset, // 起始索引
 //                    }
 //                },
 //                columns: [{
 //                    title: '序号',
 //                    field: 'id',
 //                    align: 'center',
 //                    valign: 'center',
 //                    formatter: function (value, row, index) {
 //                        var pageSize = $('#table3').bootstrapTable('getOptions').pageSize;     //通过table的#id 得到每页多少条
 //                        var pageNumber = $('#table3').bootstrapTable('getOptions').pageNumber; //通过table的#id 得到当前第几页
 //                        return pageSize * (pageNumber - 1) + index + 1;    // 返回每条的序号： 每页条数 *（当前页 - 1 ）+ 序号
 //                    }
 //
 //                }, {
 //                    title: '代码',
 //                    field: 'code',
 //                    align: 'center',
 //                    valign: 'center',
 //                }, {
 //                    title: '名称',
 //                    field: 'name',
 //                    align: 'center',
 //                    valign: 'center',
 //                }, {
 //                    title: '父级',
 //                    field: 'parent',
 //                    align: 'center',
 //                    valign: 'center',
 //                }, {
 //                    title: '顺序',
 //                    field: 'sequence',
 //                    align: 'center',
 //                    valign: 'center',
 //                }, {
 //                    title: '备注',
 //                    field: 'comment',
 //                    align: 'center',
 //                    valign: 'center',
 //                }, {
 //                    title: '操作',
 //                    field: 'operation',
 //                    width: "200px",
 //                    formatter: function (value, row, index) {
 //                        return ['<button id="table_edit" class="btn btn-primary btn-xs btn-info glyphicon glyphicon-pencil" ' +
 //                        'data-toggle="modal" data-target="#editModal"  type="button">编辑</button>',
 //                            '<button id="del" class="btn btn-primary btn-xs btn-danger glyphicon glyphicon-remove" data-toggle="modal" data-target="#delModal" type="button">删除</button>',
 //                        ].join("")
 //                    }
 //                },]
 //            });
 //            break;
 //        case "4":
 //            $('#myTab li:eq(3)a').tab('show');
 //            $('#table4').bootstrapTable({
 //                url: '/showType',  //请求后台url
 //                method: 'get',
 //                showRefresh: false, //是否显示刷新按钮
 //                cardView: false,   //是否显示详细视图
 //                showToggle: false, // 是否显示详细视图和列表视图的切换按钮
 //                showColumns: false, // 是否显示列操作按钮
 //                detailView: false, // 是否显示详细视图
 //                striped: true, // 设置为true会有隔行变色效果
 //                dataType: "json", // 服务器返回的数据类型
 //                pagination: true, // 设置为true会在底部显示分页条
 //                singleSelect: true, // 设置为true将禁止多选
 //                clickToSelect: true, // 是否启用点击选中行
 //                pageSize: 10, // 如果设置了分页，每页数据条数
 //                pageNumber: 1, // 如果设置了分布，首页页码
 //                pageList: [5, 10, 15, 20],  //可供选择的每页的行数
 //                search: false, // 是否显示搜索框
 //                sidePagination: "server", // 设置在哪里进行分页，可选值为"client" 或者 "server"
 //                contentType: "application/json;charset=utf-8",
 //                queryParams: function (params) {
 //                    return {
 //                        pageSize: params.limit, // 每页显示数量
 //                        offset: params.offset, // 起始索引
 //                    }
 //                },
 //                columns: [{
 //                    title: '序号',
 //                    field: 'id',
 //                    align: 'center',
 //                    valign: 'center',
 //                    formatter: function (value, row, index) {
 //                        var pageSize = $('#table4').bootstrapTable('getOptions').pageSize;     //通过table的#id 得到每页多少条
 //                        var pageNumber = $('#table4').bootstrapTable('getOptions').pageNumber; //通过table的#id 得到当前第几页
 //                        return pageSize * (pageNumber - 1) + index + 1;    // 返回每条的序号： 每页条数 *（当前页 - 1 ）+ 序号
 //                    }
 //
 //                }, {
 //                    title: '代码',
 //                    field: 'code',
 //                    align: 'center',
 //                    valign: 'center',
 //                }, {
 //                    title: '名称',
 //                    field: 'name',
 //                    align: 'center',
 //                    valign: 'center',
 //                }, {
 //                    title: '父级',
 //                    field: 'parent',
 //                    align: 'center',
 //                    valign: 'center',
 //                }, {
 //                    title: '顺序',
 //                    field: 'sequence',
 //                    align: 'center',
 //                    valign: 'center',
 //                }, {
 //                    title: '备注',
 //                    field: 'comment',
 //                    align: 'center',
 //                    valign: 'center',
 //                }, {
 //                    title: '操作',
 //                    field: 'operation',
 //                    width: "200px",
 //                    formatter: function (value, row, index) {
 //                        return ['<button id="table_edit" class="btn btn-primary btn-xs btn-info glyphicon glyphicon-pencil" ' +
 //                        'data-toggle="modal" data-target="#editModal"  type="button">编辑</button>',
 //                            '<button id="del" class="btn btn-primary btn-xs btn-danger glyphicon glyphicon-remove" data-toggle="modal" data-target="#delModal" type="button">删除</button>',
 //                        ].join("")
 //                    }
 //                },]
 //            });
 //            break;
 //        case "5":
 //            $('#myTab li:eq(4)a').tab('show');
 //            $('#table5').bootstrapTable({
 //                url: '/showStatus',  //请求后台url
 //                method: 'get',
 //                showRefresh: false, //是否显示刷新按钮
 //                cardView: false,   //是否显示详细视图
 //                showToggle: false, // 是否显示详细视图和列表视图的切换按钮
 //                showColumns: false, // 是否显示列操作按钮
 //                detailView: false, // 是否显示详细视图
 //                striped: true, // 设置为true会有隔行变色效果
 //                dataType: "json", // 服务器返回的数据类型
 //                pagination: true, // 设置为true会在底部显示分页条
 //                singleSelect: true, // 设置为true将禁止多选
 //                clickToSelect: true, // 是否启用点击选中行
 //                pageSize: 10, // 如果设置了分页，每页数据条数
 //                pageNumber: 1, // 如果设置了分布，首页页码
 //                pageList: [5, 10, 15, 20],  //可供选择的每页的行数
 //                search: false, // 是否显示搜索框
 //                sidePagination: "server", // 设置在哪里进行分页，可选值为"client" 或者 "server"
 //                contentType: "application/json;charset=utf-8",
 //                queryParams: function (params) {
 //                    return {
 //                        pageSize: params.limit, // 每页显示数量
 //                        offset: params.offset, // 起始索引
 //                    }
 //                },
 //                columns: [{
 //                    title: '序号',
 //                    field: 'id',
 //                    align: 'center',
 //                    valign: 'center',
 //                    formatter: function (value, row, index) {
 //                        var pageSize = $('#table5').bootstrapTable('getOptions').pageSize;     //通过table的#id 得到每页多少条
 //                        var pageNumber = $('#table5').bootstrapTable('getOptions').pageNumber; //通过table的#id 得到当前第几页
 //                        return pageSize * (pageNumber - 1) + index + 1;    // 返回每条的序号： 每页条数 *（当前页 - 1 ）+ 序号
 //                    }
 //
 //                }, {
 //                    title: '代码',
 //                    field: 'code',
 //                    align: 'center',
 //                    valign: 'center',
 //                }, {
 //                    title: '名称',
 //                    field: 'name',
 //                    align: 'center',
 //                    valign: 'center',
 //                }, {
 //                    title: '父级',
 //                    field: 'parent',
 //                    align: 'center',
 //                    valign: 'center',
 //                }, {
 //                    title: '顺序',
 //                    field: 'sequence',
 //                    align: 'center',
 //                    valign: 'center',
 //                }, {
 //                    title: '备注',
 //                    field: 'comment',
 //                    align: 'center',
 //                    valign: 'center',
 //                }, {
 //                    title: '操作',
 //                    field: 'operation',
 //                    width: "200px",
 //                    formatter: function (value, row, index) {
 //                        return ['<button id="table_edit" class="btn btn-primary btn-xs btn-info glyphicon glyphicon-pencil" ' +
 //                        'data-toggle="modal" data-target="#editModal"  type="button">编辑</button>',
 //                            '<button id="del" class="btn btn-primary btn-xs btn-danger glyphicon glyphicon-remove" data-toggle="modal" data-target="#delModal" type="button">删除</button>',
 //                        ].join("")
 //                    }
 //                },]
 //            });
 //            break;
 //        default:
 //            $('#myTab a:first').tab('show');
 //            $('#rootTable').bootstrapTable({
 //                url: '/showCase',  //请求后台url
 //                cache: false,
 //                method: 'get',
 //                showRefresh: false, //是否显示刷新按钮
 //                cardView: false,   //是否显示详细视图
 //                showToggle: false, // 是否显示详细视图和列表视图的切换按钮
 //                showColumns: false, // 是否显示列操作按钮
 //                detailView: false, // 是否显示详细视图
 //                striped: true, // 设置为true会有隔行变色效果
 //                dataType: "json", // 服务器返回的数据类型
 //                pagination: true, // 设置为true会在底部显示分页条
 //                singleSelect: true, // 设置为true将禁止多选
 //                clickToSelect: true, // 是否启用点击选中行
 //                pageSize: 10, // 如果设置了分页，每页数据条数
 //                pageNumber: 1, // 如果设置了分布，首页页码
 //                pageList: [5, 10, 15, 20],  //可供选择的每页的行数
 //                search: false, // 是否显示搜索框
 //                sidePagination: "server", // 设置在哪里进行分页，可选值为"client" 或者 "server"
 //                contentType: "application/json;charset=utf-8",
 //                queryParams: function (params) {
 //                    return {
 //                        pageSize: params.limit, // 每页显示数量
 //                        offset: params.offset, // 起始索引
 //                    }
 //                },
 //                columns: [{
 //                    title: '序号',
 //                    field: 'id',
 //                    align: 'center',
 //                    valign: 'center',
 //                    formatter: function (value, row, index) {
 //                        var pageSize = $('#rootTable').bootstrapTable('getOptions').pageSize;     //通过table的#id 得到每页多少条
 //                        var pageNumber = $('#rootTable').bootstrapTable('getOptions').pageNumber; //通过table的#id 得到当前第几页
 //                        return pageSize * (pageNumber - 1) + index + 1;    // 返回每条的序号： 每页条数 *（当前页 - 1 ）+ 序号
 //                    }
 //
 //                }, {
 //                    title: '代码',
 //                    field: 'code',
 //                    align: 'center',
 //                    valign: 'center',
 //                }, {
 //                    title: '名称',
 //                    field: 'name',
 //                    align: 'center',
 //                    valign: 'center',
 //                }, {
 //                    title: '父级',
 //                    field: 'parent',
 //                    align: 'center',
 //                    valign: 'center',
 //                }, {
 //                    title: '顺序',
 //                    field: 'sequence',
 //                    align: 'center',
 //                    valign: 'center',
 //                }, {
 //                    title: '备注',
 //                    field: 'comment',
 //                    align: 'center',
 //                    valign: 'center',
 //                }, {
 //                    title: '操作',
 //                    field: 'operation',
 //                    formatter: function (value, row, index) {
 //                        return ['<button id="table_edit" class="btn btn-primary btn-xs btn-info glyphicon glyphicon-pencil" ' +
 //                        'data-toggle="modal" data-target="#editModal" type="button">编辑</button>',
 //                            '<button id="del" class="btn btn-primary btn-xs btn-danger glyphicon glyphicon-remove" data-toggle="modal" data-target="#delModal" type="button">删除</button>',
 //                        ].join("")
 //                    }
 //                },]
 //            });
 //            break;
 //    }
 // });
$('#dictionaryTable').bootstrapTable('destroy');
$('#dictionaryTable').bootstrapTable({
    url:'/getDictionary',  //请求后台url
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
        formatter : function (value, row, index) {
            var pageSize = $('#dictionaryTable').bootstrapTable('getOptions').pageSize;     //通过table的#id 得到每页多少条
            var pageNumber = $('#dictionaryTable').bootstrapTable('getOptions').pageNumber; //通过table的#id 得到当前第几页
            return pageSize * (pageNumber - 1) + index + 1;    // 返回每条的序号： 每页条数 *（当前页 - 1 ）+ 序号
        }

    },{
        title : '代码',
        field : 'dictionarycode',
        align : 'center',
        valign : 'center',
    }, {
        title : '名称',
        field : 'dictionaryname',
        align : 'center',
        valign : 'center',
        formatter: function (value,row,index) {
            return ['<a href="" id="a"  data-toggle="modal" data-target="#searchDictionary2">'+value+'</a>'].join("")
        }
    },  {
        title : '操作',
        field : 'operation',
        formatter : function (value, row, index) {
            return['<button id="add" class="btn btn-primary btn-xs btn-default glyphicon glyphicon-plus" data-toggle="modal" data-target="#searchDictionaryModal"  type="button">添加</button>',
                '<button id="del" class="btn btn-primary btn-xs btn-danger glyphicon glyphicon-remove" data-toggle="modal" data-target="#delModal" type="button">删除</button>',
            ].join("")
        }
    },]
});

var name="";
//向模态框传值
$('#searchDictionaryModal').on('show.bs.modal',function (event) {
    var btnThis=$(event.relatedTarget);//触发事件的按钮
    var modal=$(this);//当前模态框
    name= btnThis.closest('tr').find('td').eq(2).text();
    var code = btnThis.closest('tr').find('td').eq(1).text();
    modal.find('#name1').val(name);
    modal.find('#code1').val(code);
    $("#d-table").bootstrapTable('destroy');
    $('#d-table').bootstrapTable({
        url:'/getDetail',  //请求后台url
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
                name:name,
                pageSize : params.limit, // 每页显示数量
                offset : params.offset, // 起始索引
            }
        },
        columns : [ {
            title : '序号',
            field : 'id',
            align : 'center',
            valign : 'center',
            formatter : function (value, row, index) {
                var pageSize = $('#d-table').bootstrapTable('getOptions').pageSize;     //通过table的#id 得到每页多少条
                var pageNumber = $('#d-table').bootstrapTable('getOptions').pageNumber; //通过table的#id 得到当前第几页
                return pageSize * (pageNumber - 1) + index + 1;    // 返回每条的序号： 每页条数 *（当前页 - 1 ）+ 序号
            }
        },{
            title : '代码',
            field : 'code',
            align : 'center',
            valign : 'center',
        }, {
            title : '名称',
            field : 'name',
            align : 'center',
            valign : 'center',
        },{
            title: '父级',
            field: 'parent',
            align: 'center',
            valign: 'center',
        }, {
            title: '顺序',
            field: 'sequence',
            align: 'center',
            valign: 'center',
        }, {
            title: '备注',
            field: 'comment',
            align: 'center',
            valign: 'center',
        },  {
            title : '操作',
            field : 'operation',
            formatter : function (value, row, index) {
                return['<button id="table_edit" class="btn btn-primary btn-xs btn-info glyphicon glyphicon-pencil" ' +
                'data-toggle="modal" data-target="#editModal"  type="button">编辑</button>',
                    '<button id="del" class="btn btn-primary btn-xs btn-danger glyphicon glyphicon-remove" data-toggle="modal" data-target="#deleteModal" type="button">删除</button>',
                ].join("")
            }
        },]
    });
});

$('#editModal').on('show.bs.modal',function (event) {
    var btnThis=$(event.relatedTarget);//触发事件的按钮
    var modal=$(this);//当前模态框
    var code= btnThis.closest('tr').find('td').eq(1).text();
    var name= btnThis.closest('tr').find('td').eq(2).text();
    var sequence = btnThis.closest('tr').find('td').eq(4).text();
    var comment = btnThis.closest('tr').find('td').eq(5).text();
    modal.find('#edit-code').val(code);
    modal.find('#edit-name').val(name);
    modal.find('#edit-sequence').val(sequence);
    modal.find('#edit-comment').val(comment);
});

//删除词典及其所有子属性
$('#delModal').on('show.bs.modal',function (event) {
    var btnThis=$(event.relatedTarget);//触发事件的按钮
    var modal=$(this);//当前模态框
    var name= btnThis.closest('tr').find('td').eq(2).text();
    modal.find("#delname").val(name);
});
function deleteInfo() {
    var name = $("#delname").val();
    $("#dictionaryTable").bootstrapTable('destroy');
    $('#dictionaryTable').bootstrapTable({
        url:'/delDictionary',  //请求后台url
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
                name:name,
                pageSize : params.limit, // 每页显示数量
                offset : params.offset, // 起始索引
            }
        },
        columns : [ {
            title : '序号',
            field : 'id',
            align : 'center',
            valign : 'center',
            formatter : function (value, row, index) {
                var pageSize = $('#dictionaryTable').bootstrapTable('getOptions').pageSize;     //通过table的#id 得到每页多少条
                var pageNumber = $('#dictionaryTable').bootstrapTable('getOptions').pageNumber; //通过table的#id 得到当前第几页
                return pageSize * (pageNumber - 1) + index + 1;    // 返回每条的序号： 每页条数 *（当前页 - 1 ）+ 序号
            }

        },{
            title : '代码',
            field : 'dictionarycode',
            align : 'center',
            valign : 'center',
        }, {
            title : '名称',
            field : 'dictionaryname',
            align : 'center',
            valign : 'center',
            formatter: function (value,row,index) {
                return ['<a href="" id="a" onclick="showDictionary2(this)" data-toggle="modal" data-target="#searchDictionary2">'+value+'</a>'].join("")
            }
        },  {
            title : '操作',
            field : 'operation',
            formatter : function (value, row, index) {
                return['<button id="add" class="btn btn-primary btn-xs btn-default glyphicon glyphicon-plus" data-toggle="modal" data-target="#searchDictionaryModal"  type="button">添加</button>',
                    '<button id="del" class="btn btn-primary btn-xs btn-danger glyphicon glyphicon-remove" data-toggle="modal" data-target="#delModal" type="button">删除</button>',
                ].join("")
            }
        },]
    });
}

//保存
function saveInfo(obj) {
    var code = $('#code').val();
    var name = $("#name").val();
    $("#dictionaryTable").bootstrapTable('destroy');
    $('#dictionaryTable').bootstrapTable({
        url:'/addDictionary',  //请求后台url
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
                code:code,
                name:name,
                pageSize : params.limit, // 每页显示数量
                offset : params.offset, // 起始索引
            }
        },
        columns : [ {
            title : '序号',
            field : 'id',
            align : 'center',
            valign : 'center',
            formatter : function (value, row, index) {
                var pageSize = $('#dictionaryTable').bootstrapTable('getOptions').pageSize;     //通过table的#id 得到每页多少条
                var pageNumber = $('#dictionaryTable').bootstrapTable('getOptions').pageNumber; //通过table的#id 得到当前第几页
                return pageSize * (pageNumber - 1) + index + 1;    // 返回每条的序号： 每页条数 *（当前页 - 1 ）+ 序号
            }

        },{
            title : '代码',
            field : 'dictionarycode',
            align : 'center',
            valign : 'center',
        }, {
            title : '名称',
            field : 'dictionaryname',
            align : 'center',
            valign : 'center',
            formatter: function (value,row,index) {
                return ['<a href="" id="a" onclick="showDictionary2(this)" data-toggle="modal" data-target="#searchDictionary2">'+value+'</a>'].join("")
            }
        },  {
            title : '操作',
            field : 'operation',
            formatter : function (value, row, index) {
                return['<button id="add" class="btn btn-primary btn-xs btn-default glyphicon glyphicon-plus" data-toggle="modal" data-target="#searchDictionaryModal"  type="button">添加</button>',
                    '<button id="del" class="btn btn-primary btn-xs btn-danger glyphicon glyphicon-remove" data-toggle="modal" data-target="#delModal" type="button">删除</button>',
                ].join("")
            }
        },]
    });
}

$('#addContent').on('show.bs.modal',function (event) {
    //var btnThis = $(event.relatedTarget);//触发事件的按钮
    var modal = $(this);//当前模态框
    var dictionaryname= $('#name1').val();
    var dictionarycode = $('#code1').val();
    modal.find('#dictionary-name').val(dictionaryname);
    modal.find('#dictionary-code').val(dictionarycode);
});

function  saveDictionary(){
    var dictionary_name = $('#dictionary-name').val();
    var dictionary_code = $('#dictionary-code').val();
    var code = $('#add-code').val();
    var name = $('#add-name').val();
    var parent = $('#add-parent').val();
    var sequence = $('#add-sequence').val();
    var comment = $('#add-comment').val();
    $("#d-table").bootstrapTable('destroy');
    $('#d-table').bootstrapTable({
        url:'/addContent',  //请求后台url
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
                dictionary_name:dictionary_name,
                dictionary_code:dictionary_code,
                code:code,
                name:name,
                sequence:sequence,
                parent:parent,
                comment:comment,
                pageSize : params.limit, // 每页显示数量
                offset : params.offset, // 起始索引
            }
        },
        columns : [ {
            title : '序号',
            field : 'id',
            align : 'center',
            valign : 'center',
            formatter : function (value, row, index) {
                var pageSize = $('#d-table').bootstrapTable('getOptions').pageSize;     //通过table的#id 得到每页多少条
                var pageNumber = $('#d-table').bootstrapTable('getOptions').pageNumber; //通过table的#id 得到当前第几页
                return pageSize * (pageNumber - 1) + index + 1;    // 返回每条的序号： 每页条数 *（当前页 - 1 ）+ 序号
            }
        },{
            title : '代码',
            field : 'code',
            align : 'center',
            valign : 'center',
        }, {
            title : '名称',
            field : 'name',
            align : 'center',
            valign : 'center',
        },{
            title: '父级',
            field: 'parent',
            align: 'center',
            valign: 'center',
        }, {
            title: '顺序',
            field: 'sequence',
            align: 'center',
            valign: 'center',
        }, {
            title: '备注',
            field: 'comment',
            align: 'center',
            valign: 'center',
        },  {
            title : '操作',
            field : 'operation',
            formatter : function (value, row, index) {
                return['<button id="table_edit" class="btn btn-primary btn-xs btn-info glyphicon glyphicon-pencil" ' +
                'data-toggle="modal" data-target="#editModal"  type="button">编辑</button>',
                    '<button id="del" class="btn btn-primary btn-xs btn-danger glyphicon glyphicon-remove" data-toggle="modal" data-target="#delModal" type="button">删除</button>',
                ].join("")
            }
        },]
    });
}

//删除子属性
$('#deleteModal').on('show.bs.modal',function (event) {
    var btnThis=$(event.relatedTarget);//触发事件的按钮
    var modal=$(this);//当前模态框
    var code= btnThis.closest('tr').find('td').eq(1).text();
    var name= $('#name1').val();
    modal.find("#delete-code").val(code);
    modal.find("#d-name").val(name);
});

function deleteConfirm(){
    var code = $("#delete-code").val();
    var name = $("#d-name").val();
    $("#d-table").bootstrapTable('destroy');
    $('#d-table').bootstrapTable({
        url:'/deleteField',  //请求后台url
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
                code:code,
                name:name,
                pageSize : params.limit, // 每页显示数量
                offset : params.offset, // 起始索引
            }
        },
        columns : [ {
            title : '序号',
            field : 'id',
            align : 'center',
            valign : 'center',
            formatter : function (value, row, index) {
                var pageSize = $('#d-table').bootstrapTable('getOptions').pageSize;     //通过table的#id 得到每页多少条
                var pageNumber = $('#d-table').bootstrapTable('getOptions').pageNumber; //通过table的#id 得到当前第几页
                return pageSize * (pageNumber - 1) + index + 1;    // 返回每条的序号： 每页条数 *（当前页 - 1 ）+ 序号
            }
        },{
            title : '代码',
            field : 'code',
            align : 'center',
            valign : 'center',
        }, {
            title : '名称',
            field : 'name',
            align : 'center',
            valign : 'center',
        },{
            title: '父级',
            field: 'parent',
            align: 'center',
            valign: 'center',
        }, {
            title: '顺序',
            field: 'sequence',
            align: 'center',
            valign: 'center',
        }, {
            title: '备注',
            field: 'comment',
            align: 'center',
            valign: 'center',
        },  {
            title : '操作',
            field : 'operation',
            formatter : function (value, row, index) {
                return['<button id="table_edit" class="btn btn-primary btn-xs btn-info glyphicon glyphicon-pencil" ' +
                'data-toggle="modal" data-target="#editModal"  type="button">编辑</button>',
                    '<button id="del" class="btn btn-primary btn-xs btn-danger glyphicon glyphicon-remove" data-toggle="modal" data-target="#deleteModal" type="button">删除</button>',
                ].join("")
            }
        },]
    });
}

function saveEdit() {
    var code = $("#edit-code").val();
    var name = $("#edit-dictionary-name").val(); //获取字典名称
    var sequence = $("#edit-sequence").val();
    var comment = $("#edit-comment").val();
    $("#d-table").bootstrapTable('destroy');
    $('#d-table').bootstrapTable({
        url:'/editInfo',  //请求后台url
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
                code:code,
                name:name,
                sequence:sequence,
                comment:comment,
                pageSize : params.limit, // 每页显示数量
                offset : params.offset, // 起始索引
            }
        },
        columns : [ {
            title : '序号',
            field : 'id',
            align : 'center',
            valign : 'center',
            formatter : function (value, row, index) {
                var pageSize = $('#d-table').bootstrapTable('getOptions').pageSize;     //通过table的#id 得到每页多少条
                var pageNumber = $('#d-table').bootstrapTable('getOptions').pageNumber; //通过table的#id 得到当前第几页
                return pageSize * (pageNumber - 1) + index + 1;    // 返回每条的序号： 每页条数 *（当前页 - 1 ）+ 序号
            }
        },{
            title : '代码',
            field : 'code',
            align : 'center',
            valign : 'center',
        }, {
            title : '名称',
            field : 'name',
            align : 'center',
            valign : 'center',
        },{
            title: '父级',
            field: 'parent',
            align: 'center',
            valign: 'center',
        }, {
            title: '顺序',
            field: 'sequence',
            align: 'center',
            valign: 'center',
        }, {
            title: '备注',
            field: 'comment',
            align: 'center',
            valign: 'center',
        },  {
            title : '操作',
            field : 'operation',
            formatter : function (value, row, index) {
                return['<button id="table_edit" class="btn btn-primary btn-xs btn-info glyphicon glyphicon-pencil" ' +
                'data-toggle="modal" data-target="#editModal"  type="button">编辑</button>',
                    '<button id="del" class="btn btn-primary btn-xs btn-danger glyphicon glyphicon-remove" data-toggle="modal" data-target="#deleteModal" type="button">删除</button>',
                ].join("")
            }
        },]
    });
}
