var id="";

 $('#myTab a').click(function () {
     $(this).addClass('active').siblings().removeClass('active');
     id = $(this).attr('data-id');
     $('#myTab a:first').tab('show');
    switch(id){
        case "1":
            $('#myTab a:first').tab('show');
            $('#rootTable').bootstrapTable({
                url: '/showCase',  //请求后台url
                cache: false,
                method: 'get',
                showRefresh: false, //是否显示刷新按钮
                cardView: false,   //是否显示详细视图
                showToggle: false, // 是否显示详细视图和列表视图的切换按钮
                showColumns: false, // 是否显示列操作按钮
                detailView: false, // 是否显示详细视图
                striped: true, // 设置为true会有隔行变色效果
                dataType: "json", // 服务器返回的数据类型
                pagination: true, // 设置为true会在底部显示分页条
                singleSelect: true, // 设置为true将禁止多选
                clickToSelect: true, // 是否启用点击选中行
                pageSize: 10, // 如果设置了分页，每页数据条数
                pageNumber: 1, // 如果设置了分布，首页页码
                pageList: [5, 10, 15, 20],  //可供选择的每页的行数
                search: false, // 是否显示搜索框
                sidePagination: "server", // 设置在哪里进行分页，可选值为"client" 或者 "server"
                contentType: "application/json;charset=utf-8",
                queryParams: function (params) {
                    return {
                        pageSize: params.limit, // 每页显示数量
                        offset: params.offset, // 起始索引
                    }
                },
                columns: [{
                    title: '序号',
                    field: 'id',
                    align: 'center',
                    valign: 'center',
                    formatter: function (value, row, index) {
                        var pageSize = $('#rootTable').bootstrapTable('getOptions').pageSize;     //通过table的#id 得到每页多少条
                        var pageNumber = $('#rootTable').bootstrapTable('getOptions').pageNumber; //通过table的#id 得到当前第几页
                        return pageSize * (pageNumber - 1) + index + 1;    // 返回每条的序号： 每页条数 *（当前页 - 1 ）+ 序号
                    }

                }, {
                    title: '代码',
                    field: 'code',
                    align: 'center',
                    valign: 'center',
                }, {
                    title: '名称',
                    field: 'name',
                    align: 'center',
                    valign: 'center',
                }, {
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
                }, {
                    title: '操作',
                    field: 'operation',
                    formatter: function (value, row, index) {
                        return ['<button id="table_edit" class="btn btn-primary btn-xs btn-info glyphicon glyphicon-pencil" ' +
                        'data-toggle="modal" data-target="#editModal" type="button">编辑</button>',
                            '<button id="del" class="btn btn-primary btn-xs btn-danger glyphicon glyphicon-remove" data-toggle="modal" data-target="#delModal" type="button">删除</button>',
                        ].join("")
                    }
                },]
            });
            break;
        case "2":
            $('#myTab li:eq(1)a').tab('show');
            $('#table2').bootstrapTable({
                url: '/showClassfiy',  //请求后台url
                method: 'get',
                cache: false,
                showRefresh: false, //是否显示刷新按钮
                cardView: false,   //是否显示详细视图
                showToggle: false, // 是否显示详细视图和列表视图的切换按钮
                showColumns: false, // 是否显示列操作按钮
                detailView: false, // 是否显示详细视图
                striped: true, // 设置为true会有隔行变色效果
                dataType: "json", // 服务器返回的数据类型
                pagination: true, // 设置为true会在底部显示分页条
                singleSelect: true, // 设置为true将禁止多选
                clickToSelect: true, // 是否启用点击选中行
                pageSize: 10, // 如果设置了分页，每页数据条数
                pageNumber: 1, // 如果设置了分布，首页页码
                pageList: [5, 10, 15, 20],  //可供选择的每页的行数
                search: false, // 是否显示搜索框
                sidePagination: "server", // 设置在哪里进行分页，可选值为"client" 或者 "server"
                contentType: "application/json;charset=utf-8",
                queryParams: function (params) {
                    return {
                        pageSize: params.limit, // 每页显示数量
                        offset: params.offset, // 起始索引
                    }
                },
                columns: [{
                    title: '序号',
                    field: 'id',
                    align: 'center',
                    valign: 'center',
                    formatter: function (value, row, index) {
                        var pageSize = $('#table2').bootstrapTable('getOptions').pageSize;     //通过table的#id 得到每页多少条
                        var pageNumber = $('#table2').bootstrapTable('getOptions').pageNumber; //通过table的#id 得到当前第几页
                        return pageSize * (pageNumber - 1) + index + 1;    // 返回每条的序号： 每页条数 *（当前页 - 1 ）+ 序号
                    }
                }, {
                    title: '代码',
                    field: 'code',
                    align: 'center',
                    valign: 'center',
                }, {
                    title: '名称',
                    field: 'name',
                    align: 'center',
                    valign: 'center',
                }, {
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
                }, {
                    title: '操作',
                    field: 'operation',
                    width: "200px",
                    formatter: function (value, row, index) {
                        return ['<button id="table_edit" class="btn btn-primary btn-xs btn-info glyphicon glyphicon-pencil" ' +
                        'data-toggle="modal" data-target="#editModal"  type="button">编辑</button>',
                            '<button id="del" class="btn btn-primary btn-xs btn-danger glyphicon glyphicon-remove" data-toggle="modal" data-target="#delModal" type="button">删除</button>',
                        ].join("")
                    }
                },]
            });
            break;
        case "3":
            $('#myTab li:eq(2)a').tab('show');
            $('#table3').bootstrapTable({
                url: '/showArchive',  //请求后台url
                method: 'get',
                showRefresh: false, //是否显示刷新按钮
                cardView: false,   //是否显示详细视图
                showToggle: false, // 是否显示详细视图和列表视图的切换按钮
                showColumns: false, // 是否显示列操作按钮
                detailView: false, // 是否显示详细视图
                striped: true, // 设置为true会有隔行变色效果
                dataType: "json", // 服务器返回的数据类型
                pagination: true, // 设置为true会在底部显示分页条
                singleSelect: true, // 设置为true将禁止多选
                clickToSelect: true, // 是否启用点击选中行
                pageSize: 10, // 如果设置了分页，每页数据条数
                pageNumber: 1, // 如果设置了分布，首页页码
                pageList: [5, 10, 15, 20],  //可供选择的每页的行数
                search: false, // 是否显示搜索框
                sidePagination: "server", // 设置在哪里进行分页，可选值为"client" 或者 "server"
                contentType: "application/json;charset=utf-8",
                queryParams: function (params) {
                    return {
                        pageSize: params.limit, // 每页显示数量
                        offset: params.offset, // 起始索引
                    }
                },
                columns: [{
                    title: '序号',
                    field: 'id',
                    align: 'center',
                    valign: 'center',
                    formatter: function (value, row, index) {
                        var pageSize = $('#table3').bootstrapTable('getOptions').pageSize;     //通过table的#id 得到每页多少条
                        var pageNumber = $('#table3').bootstrapTable('getOptions').pageNumber; //通过table的#id 得到当前第几页
                        return pageSize * (pageNumber - 1) + index + 1;    // 返回每条的序号： 每页条数 *（当前页 - 1 ）+ 序号
                    }

                }, {
                    title: '代码',
                    field: 'code',
                    align: 'center',
                    valign: 'center',
                }, {
                    title: '名称',
                    field: 'name',
                    align: 'center',
                    valign: 'center',
                }, {
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
                }, {
                    title: '操作',
                    field: 'operation',
                    width: "200px",
                    formatter: function (value, row, index) {
                        return ['<button id="table_edit" class="btn btn-primary btn-xs btn-info glyphicon glyphicon-pencil" ' +
                        'data-toggle="modal" data-target="#editModal"  type="button">编辑</button>',
                            '<button id="del" class="btn btn-primary btn-xs btn-danger glyphicon glyphicon-remove" data-toggle="modal" data-target="#delModal" type="button">删除</button>',
                        ].join("")
                    }
                },]
            });
            break;
        case "4":
            $('#myTab li:eq(3)a').tab('show');
            $('#table4').bootstrapTable({
                url: '/showType',  //请求后台url
                method: 'get',
                showRefresh: false, //是否显示刷新按钮
                cardView: false,   //是否显示详细视图
                showToggle: false, // 是否显示详细视图和列表视图的切换按钮
                showColumns: false, // 是否显示列操作按钮
                detailView: false, // 是否显示详细视图
                striped: true, // 设置为true会有隔行变色效果
                dataType: "json", // 服务器返回的数据类型
                pagination: true, // 设置为true会在底部显示分页条
                singleSelect: true, // 设置为true将禁止多选
                clickToSelect: true, // 是否启用点击选中行
                pageSize: 10, // 如果设置了分页，每页数据条数
                pageNumber: 1, // 如果设置了分布，首页页码
                pageList: [5, 10, 15, 20],  //可供选择的每页的行数
                search: false, // 是否显示搜索框
                sidePagination: "server", // 设置在哪里进行分页，可选值为"client" 或者 "server"
                contentType: "application/json;charset=utf-8",
                queryParams: function (params) {
                    return {
                        pageSize: params.limit, // 每页显示数量
                        offset: params.offset, // 起始索引
                    }
                },
                columns: [{
                    title: '序号',
                    field: 'id',
                    align: 'center',
                    valign: 'center',
                    formatter: function (value, row, index) {
                        var pageSize = $('#table4').bootstrapTable('getOptions').pageSize;     //通过table的#id 得到每页多少条
                        var pageNumber = $('#table4').bootstrapTable('getOptions').pageNumber; //通过table的#id 得到当前第几页
                        return pageSize * (pageNumber - 1) + index + 1;    // 返回每条的序号： 每页条数 *（当前页 - 1 ）+ 序号
                    }

                }, {
                    title: '代码',
                    field: 'code',
                    align: 'center',
                    valign: 'center',
                }, {
                    title: '名称',
                    field: 'name',
                    align: 'center',
                    valign: 'center',
                }, {
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
                }, {
                    title: '操作',
                    field: 'operation',
                    width: "200px",
                    formatter: function (value, row, index) {
                        return ['<button id="table_edit" class="btn btn-primary btn-xs btn-info glyphicon glyphicon-pencil" ' +
                        'data-toggle="modal" data-target="#editModal"  type="button">编辑</button>',
                            '<button id="del" class="btn btn-primary btn-xs btn-danger glyphicon glyphicon-remove" data-toggle="modal" data-target="#delModal" type="button">删除</button>',
                        ].join("")
                    }
                },]
            });
            break;
        case "5":
            $('#myTab li:eq(4)a').tab('show');
            $('#table5').bootstrapTable({
                url: '/showStatus',  //请求后台url
                method: 'get',
                showRefresh: false, //是否显示刷新按钮
                cardView: false,   //是否显示详细视图
                showToggle: false, // 是否显示详细视图和列表视图的切换按钮
                showColumns: false, // 是否显示列操作按钮
                detailView: false, // 是否显示详细视图
                striped: true, // 设置为true会有隔行变色效果
                dataType: "json", // 服务器返回的数据类型
                pagination: true, // 设置为true会在底部显示分页条
                singleSelect: true, // 设置为true将禁止多选
                clickToSelect: true, // 是否启用点击选中行
                pageSize: 10, // 如果设置了分页，每页数据条数
                pageNumber: 1, // 如果设置了分布，首页页码
                pageList: [5, 10, 15, 20],  //可供选择的每页的行数
                search: false, // 是否显示搜索框
                sidePagination: "server", // 设置在哪里进行分页，可选值为"client" 或者 "server"
                contentType: "application/json;charset=utf-8",
                queryParams: function (params) {
                    return {
                        pageSize: params.limit, // 每页显示数量
                        offset: params.offset, // 起始索引
                    }
                },
                columns: [{
                    title: '序号',
                    field: 'id',
                    align: 'center',
                    valign: 'center',
                    formatter: function (value, row, index) {
                        var pageSize = $('#table5').bootstrapTable('getOptions').pageSize;     //通过table的#id 得到每页多少条
                        var pageNumber = $('#table5').bootstrapTable('getOptions').pageNumber; //通过table的#id 得到当前第几页
                        return pageSize * (pageNumber - 1) + index + 1;    // 返回每条的序号： 每页条数 *（当前页 - 1 ）+ 序号
                    }

                }, {
                    title: '代码',
                    field: 'code',
                    align: 'center',
                    valign: 'center',
                }, {
                    title: '名称',
                    field: 'name',
                    align: 'center',
                    valign: 'center',
                }, {
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
                }, {
                    title: '操作',
                    field: 'operation',
                    width: "200px",
                    formatter: function (value, row, index) {
                        return ['<button id="table_edit" class="btn btn-primary btn-xs btn-info glyphicon glyphicon-pencil" ' +
                        'data-toggle="modal" data-target="#editModal"  type="button">编辑</button>',
                            '<button id="del" class="btn btn-primary btn-xs btn-danger glyphicon glyphicon-remove" data-toggle="modal" data-target="#delModal" type="button">删除</button>',
                        ].join("")
                    }
                },]
            });
            break;
        default:
            $('#myTab a:first').tab('show');
            $('#rootTable').bootstrapTable({
                url: '/showCase',  //请求后台url
                cache: false,
                method: 'get',
                showRefresh: false, //是否显示刷新按钮
                cardView: false,   //是否显示详细视图
                showToggle: false, // 是否显示详细视图和列表视图的切换按钮
                showColumns: false, // 是否显示列操作按钮
                detailView: false, // 是否显示详细视图
                striped: true, // 设置为true会有隔行变色效果
                dataType: "json", // 服务器返回的数据类型
                pagination: true, // 设置为true会在底部显示分页条
                singleSelect: true, // 设置为true将禁止多选
                clickToSelect: true, // 是否启用点击选中行
                pageSize: 10, // 如果设置了分页，每页数据条数
                pageNumber: 1, // 如果设置了分布，首页页码
                pageList: [5, 10, 15, 20],  //可供选择的每页的行数
                search: false, // 是否显示搜索框
                sidePagination: "server", // 设置在哪里进行分页，可选值为"client" 或者 "server"
                contentType: "application/json;charset=utf-8",
                queryParams: function (params) {
                    return {
                        pageSize: params.limit, // 每页显示数量
                        offset: params.offset, // 起始索引
                    }
                },
                columns: [{
                    title: '序号',
                    field: 'id',
                    align: 'center',
                    valign: 'center',
                    formatter: function (value, row, index) {
                        var pageSize = $('#rootTable').bootstrapTable('getOptions').pageSize;     //通过table的#id 得到每页多少条
                        var pageNumber = $('#rootTable').bootstrapTable('getOptions').pageNumber; //通过table的#id 得到当前第几页
                        return pageSize * (pageNumber - 1) + index + 1;    // 返回每条的序号： 每页条数 *（当前页 - 1 ）+ 序号
                    }

                }, {
                    title: '代码',
                    field: 'code',
                    align: 'center',
                    valign: 'center',
                }, {
                    title: '名称',
                    field: 'name',
                    align: 'center',
                    valign: 'center',
                }, {
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
                }, {
                    title: '操作',
                    field: 'operation',
                    formatter: function (value, row, index) {
                        return ['<button id="table_edit" class="btn btn-primary btn-xs btn-info glyphicon glyphicon-pencil" ' +
                        'data-toggle="modal" data-target="#editModal" type="button">编辑</button>',
                            '<button id="del" class="btn btn-primary btn-xs btn-danger glyphicon glyphicon-remove" data-toggle="modal" data-target="#delModal" type="button">删除</button>',
                        ].join("")
                    }
                },]
            });
            break;
    }
 });

function saveboot(obj){
        var code = $('#code').val();
        var name = $("#name").val();
        var dictionary = id;
        var order = $("#charger").val();
        var comment = $("#comment").val();
        console.log(id);
        if (id=="1"){
            $("#rootTable").bootstrapTable('destroy');
            $('#rootTable').bootstrapTable({
                url:'/saveboot',  //请求后台url
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
                        order:order,
                        dictionary:dictionary,
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
                        var pageSize = $('#rootTable').bootstrapTable('getOptions').pageSize;     //通过table的#id 得到每页多少条
                        var pageNumber = $('#rootTable').bootstrapTable('getOptions').pageNumber; //通过table的#id 得到当前第几页
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
                },  {
                    title : '父级',
                    field : 'parent',
                    align : 'center',
                    valign : 'center',
                },  {
                    title : '顺序',
                    field : 'sequence',
                    align : 'center',
                    valign : 'center',
                }, {
                    title : '备注',
                    field : 'content',
                    align : 'center',
                    valign : 'center',
                },  {
                    title : '操作',
                    field : 'operation',
                    formatter : function (value, row, index) {
                        return[ '<button id="table_edit" class="btn btn-primary btn-xs btn-info glyphicon glyphicon-pencil" ' +
                        'data-toggle="modal" data-target="#editModal"  type="button">编辑</button>',
                            '<button id="del" class="btn btn-primary btn-xs btn-danger glyphicon glyphicon-remove" data-toggle="modal" data-target="#delModal" type="button">删除</button>',
                        ].join("")
                    }
                },]
            });
        } else if (id=="2"){
            $("#table2").bootstrapTable('destroy');
            $('#table2').bootstrapTable({
                url: '/saveboot',  //请求后台url
                method: 'get',
                showRefresh: false, //是否显示刷新按钮
                cardView: false,   //是否显示详细视图
                showToggle: false, // 是否显示详细视图和列表视图的切换按钮
                showColumns: false, // 是否显示列操作按钮
                detailView: false, // 是否显示详细视图
                striped: true, // 设置为true会有隔行变色效果
                dataType: "json", // 服务器返回的数据类型
                pagination: true, // 设置为true会在底部显示分页条
                singleSelect: true, // 设置为true将禁止多选
                clickToSelect: true, // 是否启用点击选中行
                pageSize: 10, // 如果设置了分页，每页数据条数
                pageNumber: 1, // 如果设置了分布，首页页码
                pageList: [5, 10, 15, 20],  //可供选择的每页的行数
                search: false, // 是否显示搜索框
                sidePagination: "server", // 设置在哪里进行分页，可选值为"client" 或者 "server"
                contentType: "application/json;charset=utf-8",
                queryParams: function (params) {
                    return {
                        code: code,
                        name: name,
                        order: order,
                        dictionary:dictionary,
                        comment: comment,
                        pageSize: params.limit, // 每页显示数量
                        offset: params.offset, // 起始索引
                    }
                },
                columns: [{
                    title: '序号',
                    field: 'id',
                    align: 'center',
                    valign: 'center',
                    formatter: function (value, row, index) {
                        var pageSize = $('#table2').bootstrapTable('getOptions').pageSize;     //通过table的#id 得到每页多少条
                        var pageNumber = $('#table2').bootstrapTable('getOptions').pageNumber; //通过table的#id 得到当前第几页
                        return pageSize * (pageNumber - 1) + index + 1;    // 返回每条的序号： 每页条数 *（当前页 - 1 ）+ 序号
                    }

                }, {
                    title: '代码',
                    field: 'code',
                    align: 'center',
                    valign: 'center',
                }, {
                    title: '名称',
                    field: 'name',
                    align: 'center',
                    valign: 'center',
                }, {
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
                    field: 'content',
                    align: 'center',
                    valign: 'center',
                }, {
                    title: '操作',
                    field: 'operation',
                    formatter: function (value, row, index) {
                        return ['<button id="table_edit" class="btn btn-primary btn-xs btn-info glyphicon glyphicon-pencil" ' +
                        'data-toggle="modal" data-target="#editModal"  type="button">编辑</button>',
                            '<button id="del" class="btn btn-primary btn-xs btn-danger glyphicon glyphicon-remove" data-toggle="modal" data-target="#delModal" type="button">删除</button>',
                        ].join("")
                    }
                },]
            });
        } else if (id=="3"){
            $("#table3").bootstrapTable('destroy');
            $('#table3').bootstrapTable({
                url:'/saveboot',  //请求后台url
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
                        order:order,
                        dictionary:dictionary,
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
                        var pageSize = $('#table3').bootstrapTable('getOptions').pageSize;     //通过table的#id 得到每页多少条
                        var pageNumber = $('#table3').bootstrapTable('getOptions').pageNumber; //通过table的#id 得到当前第几页
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
                },  {
                    title : '父级',
                    field : 'parent',
                    align : 'center',
                    valign : 'center',
                },  {
                    title : '顺序',
                    field : 'sequence',
                    align : 'center',
                    valign : 'center',
                }, {
                    title : '备注',
                    field : 'comment',
                    align : 'center',
                    valign : 'center',
                },  {
                    title : '操作',
                    field : 'operation',
                    formatter : function (value, row, index) {
                        return[ '<button id="table_edit" class="btn btn-primary btn-xs btn-info glyphicon glyphicon-pencil" ' +
                        'data-toggle="modal" data-target="#editModal"  type="button">编辑</button>',
                            '<button id="del" class="btn btn-primary btn-xs btn-danger glyphicon glyphicon-remove" data-toggle="modal" data-target="#delModal" type="button">删除</button>',
                        ].join("")
                    }
                },]
            });
        }else if (id=="4"){
            $("#table4").bootstrapTable('destroy');
            $('#table4').bootstrapTable({
                url:'/saveboot',  //请求后台url
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
                        order:order,
                        dictionary:dictionary,
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
                        var pageSize = $('#table4').bootstrapTable('getOptions').pageSize;     //通过table的#id 得到每页多少条
                        var pageNumber = $('#table4').bootstrapTable('getOptions').pageNumber; //通过table的#id 得到当前第几页
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
                },  {
                    title : '父级',
                    field : 'parent',
                    align : 'center',
                    valign : 'center',
                },  {
                    title : '顺序',
                    field : 'sequence',
                    align : 'center',
                    valign : 'center',
                }, {
                    title : '备注',
                    field : 'comment',
                    align : 'center',
                    valign : 'center',
                },  {
                    title : '操作',
                    field : 'operation',
                    formatter : function (value, row, index) {
                        return[ '<button id="table_edit" class="btn btn-primary btn-xs btn-info glyphicon glyphicon-pencil" ' +
                        'data-toggle="modal" data-target="#editModal"  type="button">编辑</button>',
                            '<button id="del" class="btn btn-primary btn-xs btn-danger glyphicon glyphicon-remove" data-toggle="modal" data-target="#delModal" type="button">删除</button>',
                        ].join("")
                    }
                },]
            });
        }else if (id=="5"){
            $("#table5").bootstrapTable('destroy');
            $('#table5').bootstrapTable({
                url:'/saveboot',  //请求后台url
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
                        order:order,
                        dictionary:dictionary,
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
                        var pageSize = $('#table5').bootstrapTable('getOptions').pageSize;     //通过table的#id 得到每页多少条
                        var pageNumber = $('#table5').bootstrapTable('getOptions').pageNumber; //通过table的#id 得到当前第几页
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
                },  {
                    title : '父级',
                    field : 'parent',
                    align : 'center',
                    valign : 'center',
                },  {
                    title : '顺序',
                    field : 'sequence',
                    align : 'center',
                    valign : 'center',
                }, {
                    title : '备注',
                    field : 'comment',
                    align : 'center',
                    valign : 'center',
                },  {
                    title : '操作',
                    field : 'operation',
                    formatter : function (value, row, index) {
                        return[ '<button id="table_edit" class="btn btn-primary btn-xs btn-info glyphicon glyphicon-pencil" ' +
                        'data-toggle="modal" data-target="#editModal"  type="button">编辑</button>',
                            '<button id="del" class="btn btn-primary btn-xs btn-danger glyphicon glyphicon-remove" data-toggle="modal" data-target="#delModal" type="button">删除</button>',
                        ].join("")
                    }
                },]
            });
        }

}

//值传给模态框
$('#editModal').on('show.bs.modal',function (event) {
    var btnThis=$(event.relatedTarget);//触发事件的按钮
    var modal=$(this);//当前模态框
    var code= btnThis.closest('tr').find('td').eq(1).text();
    var name= btnThis.closest('tr').find('td').eq(2).text();
    var parent= btnThis.closest('tr').find('td').eq(3).text();
    var order= btnThis.closest('tr').find('td').eq(4).text();
    var comment= btnThis.closest('tr').find('td').eq(5).text();

    if (comment =='-'){
        comment="";
    }
    if (parent == '-'){
        parent = "";
    }
    modal.find('#editcode').val(code);
    modal.find('#editname').val(name);
    modal.find('#editparent').val(parent);
    modal.find('#editcharger').val(order);
    modal.find('#editcomment').val(comment);
});

function saveEdit(obj){
    var code = $('#editcode').val();
    var name = $("#editname").val();
    var dictionary = id;
    var order = $("#editcharger").val();
    var comment = $("#editcomment").val();

    if (id =="1") {
        $("#rootTable").bootstrapTable('destroy');
        $('#rootTable').bootstrapTable({
            url:'/editboot',  //请求后台url
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
                    order:order,
                    dictionary:dictionary,
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
                    var pageSize = $('#rootTable').bootstrapTable('getOptions').pageSize;     //通过table的#id 得到每页多少条
                    var pageNumber = $('#rootTable').bootstrapTable('getOptions').pageNumber; //通过table的#id 得到当前第几页
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
            },  {
                title : '父级',
                field : 'parent',
                align : 'center',
                valign : 'center',
            },  {
                title : '顺序',
                field : 'sequence',
                align : 'center',
                valign : 'center',
            }, {
                title : '备注',
                field : 'content',
                align : 'center',
                valign : 'center',
            },  {
                title : '操作',
                field : 'operation',
                formatter : function (value, row, index) {
                    return[ '<button id="table_edit" class="btn btn-primary btn-xs btn-info glyphicon glyphicon-pencil" ' +
                    'data-toggle="modal" data-target="#editModal"  type="button">编辑</button>',
                        '<button id="del" class="btn btn-primary btn-xs btn-danger glyphicon glyphicon-remove" data-toggle="modal" data-target="#delModal" type="button">删除</button>',
                    ].join("")
                }
            },]
        });
    }else if (id == "2"){
        $("#table2").bootstrapTable('destroy');
        $('#table2').bootstrapTable({
            url:'/editboot',  //请求后台url
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
                    order:order,
                    dictionary:dictionary,
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
                    var pageSize = $('#table2').bootstrapTable('getOptions').pageSize;     //通过table的#id 得到每页多少条
                    var pageNumber = $('#table2').bootstrapTable('getOptions').pageNumber; //通过table的#id 得到当前第几页
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
            },  {
                title : '父级',
                field : 'parent',
                align : 'center',
                valign : 'center',
            },  {
                title : '顺序',
                field : 'sequence',
                align : 'center',
                valign : 'center',
            }, {
                title : '备注',
                field : 'content',
                align : 'center',
                valign : 'center',
            },  {
                title : '操作',
                field : 'operation',
                formatter : function (value, row, index) {
                    return[ '<button id="table_edit" class="btn btn-primary btn-xs btn-info glyphicon glyphicon-pencil" ' +
                    'data-toggle="modal" data-target="#editModal"  type="button">编辑</button>',
                        '<button id="del" class="btn btn-primary btn-xs btn-danger glyphicon glyphicon-remove" data-toggle="modal" data-target="#delModal" type="button">删除</button>',
                    ].join("")
                }
            },]
        });
    } else if (id=="3"){
        $("#table3").bootstrapTable('destroy');
        $('#table3').bootstrapTable({
            url:'/editboot',  //请求后台url
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
                    order:order,
                    dictionary:dictionary,
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
                    var pageSize = $('#table3').bootstrapTable('getOptions').pageSize;     //通过table的#id 得到每页多少条
                    var pageNumber = $('#table3').bootstrapTable('getOptions').pageNumber; //通过table的#id 得到当前第几页
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
            },  {
                title : '父级',
                field : 'parent',
                align : 'center',
                valign : 'center',
            },  {
                title : '顺序',
                field : 'sequence',
                align : 'center',
                valign : 'center',
            }, {
                title : '备注',
                field : 'comment',
                align : 'center',
                valign : 'center',
            },  {
                title : '操作',
                field : 'operation',
                formatter : function (value, row, index) {
                    return[ '<button id="table_edit" class="btn btn-primary btn-xs btn-info glyphicon glyphicon-pencil" ' +
                    'data-toggle="modal" data-target="#editModal"  type="button">编辑</button>',
                        '<button id="del" class="btn btn-primary btn-xs btn-danger glyphicon glyphicon-remove" data-toggle="modal" data-target="#delModal" type="button">删除</button>',
                    ].join("")
                }
            },]
        });
    }else if (id=="4"){
        $("#table4").bootstrapTable('destroy');
        $('#table4').bootstrapTable({
            url:'/editboot',  //请求后台url
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
                    order:order,
                    dictionary:dictionary,
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
                    var pageSize = $('#table4').bootstrapTable('getOptions').pageSize;     //通过table的#id 得到每页多少条
                    var pageNumber = $('#table4').bootstrapTable('getOptions').pageNumber; //通过table的#id 得到当前第几页
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
            },  {
                title : '父级',
                field : 'parent',
                align : 'center',
                valign : 'center',
            },  {
                title : '顺序',
                field : 'sequence',
                align : 'center',
                valign : 'center',
            }, {
                title : '备注',
                field : 'comment',
                align : 'center',
                valign : 'center',
            },  {
                title : '操作',
                field : 'operation',
                formatter : function (value, row, index) {
                    return[ '<button id="table_edit" class="btn btn-primary btn-xs btn-info glyphicon glyphicon-pencil" ' +
                    'data-toggle="modal" data-target="#editModal"  type="button">编辑</button>',
                        '<button id="del" class="btn btn-primary btn-xs btn-danger glyphicon glyphicon-remove" data-toggle="modal" data-target="#delModal" type="button">删除</button>',
                    ].join("")
                }
            },]
        });
    }else if (id=="5"){
        $("#table5").bootstrapTable('destroy');
        $('#table5').bootstrapTable({
            url:'/editboot',  //请求后台url
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
                    order:order,
                    dictionary:dictionary,
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
                    var pageSize = $('#table5').bootstrapTable('getOptions').pageSize;     //通过table的#id 得到每页多少条
                    var pageNumber = $('#table5').bootstrapTable('getOptions').pageNumber; //通过table的#id 得到当前第几页
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
            },  {
                title : '父级',
                field : 'parent',
                align : 'center',
                valign : 'center',
            },  {
                title : '顺序',
                field : 'sequence',
                align : 'center',
                valign : 'center',
            }, {
                title : '备注',
                field : 'comment',
                align : 'center',
                valign : 'center',
            },  {
                title : '操作',
                field : 'operation',
                formatter : function (value, row, index) {
                    return[ '<button id="table_edit" class="btn btn-primary btn-xs btn-info glyphicon glyphicon-pencil" ' +
                    'data-toggle="modal" data-target="#editModal"  type="button">编辑</button>',
                        '<button id="del" class="btn btn-primary btn-xs btn-danger glyphicon glyphicon-remove" data-toggle="modal" data-target="#delModal" type="button">删除</button>',
                    ].join("")
                }
            },]
        });
    }

}

//删除数据
$('#delModal').on('show.bs.modal',function (event) {
    var btnThis=$(event.relatedTarget);//触发事件的按钮
    var modal=$(this);//当前模态框
    var code= btnThis.closest('tr').find('td').eq(1).text();
    modal.find("#code-del").val(code);
});
function removeInfo(obj){
    var code = $('#code-del').val();
    var dictionary = id;
    if (id=="1"){
        $("#rootTable").bootstrapTable('destroy');
        $('#rootTable').bootstrapTable({
            url:'/delboot',  //请求后台url
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
                    dictionary:dictionary,
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
                    var pageSize = $('#rootTable').bootstrapTable('getOptions').pageSize;     //通过table的#id 得到每页多少条
                    var pageNumber = $('#rootTable').bootstrapTable('getOptions').pageNumber; //通过table的#id 得到当前第几页
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
            },  {
                title : '父级',
                field : 'parent',
                align : 'center',
                valign : 'center',
            },  {
                title : '顺序',
                field : 'sequence',
                align : 'center',
                valign : 'center',
            }, {
                title : '备注',
                field : 'comment',
                align : 'center',
                valign : 'center',
            },  {
                title : '操作',
                field : 'operation',
                formatter : function (value, row, index) {
                    return[ '<button id="table_edit" class="btn btn-primary btn-xs btn-info glyphicon glyphicon-pencil" ' +
                    'data-toggle="modal" data-target="#editModal"  type="button">编辑</button>',
                        '<button id="del" class="btn btn-primary btn-xs btn-danger glyphicon glyphicon-remove" data-toggle="modal" data-target="#delModal" type="button">删除</button>',
                    ].join("")
                }
            },]
        });
    } else if (id=="2"){
        $("#table2").bootstrapTable('destroy');
        $('#table2').bootstrapTable({
            url:'/delboot',  //请求后台url
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
                    dictionary:dictionary,
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
                    var pageSize = $('#table2').bootstrapTable('getOptions').pageSize;     //通过table的#id 得到每页多少条
                    var pageNumber = $('#table2').bootstrapTable('getOptions').pageNumber; //通过table的#id 得到当前第几页
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
            },  {
                title : '父级',
                field : 'parent',
                align : 'center',
                valign : 'center',
            },  {
                title : '顺序',
                field : 'sequence',
                align : 'center',
                valign : 'center',
            }, {
                title : '备注',
                field : 'comment',
                align : 'center',
                valign : 'center',
            },  {
                title : '操作',
                field : 'operation',
                formatter : function (value, row, index) {
                    return[ '<button id="table_edit" class="btn btn-primary btn-xs btn-info glyphicon glyphicon-pencil" ' +
                    'data-toggle="modal" data-target="#editModal"  type="button">编辑</button>',
                        '<button id="del" class="btn btn-primary btn-xs btn-danger glyphicon glyphicon-remove" data-toggle="modal" data-target="#delModal" type="button">删除</button>',
                    ].join("")
                }
            },]
        });
    } else if (id=="3"){
        $("#table3").bootstrapTable('destroy');
        $('#table3').bootstrapTable({
            url:'/delboot',  //请求后台url
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
                    dictionary:dictionary,
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
                    var pageSize = $('#table3').bootstrapTable('getOptions').pageSize;     //通过table的#id 得到每页多少条
                    var pageNumber = $('#table3').bootstrapTable('getOptions').pageNumber; //通过table的#id 得到当前第几页
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
            },  {
                title : '父级',
                field : 'parent',
                align : 'center',
                valign : 'center',
            },  {
                title : '顺序',
                field : 'sequence',
                align : 'center',
                valign : 'center',
            }, {
                title : '备注',
                field : 'comment',
                align : 'center',
                valign : 'center',
            },  {
                title : '操作',
                field : 'operation',
                formatter : function (value, row, index) {
                    return[ '<button id="table_edit" class="btn btn-primary btn-xs btn-info glyphicon glyphicon-pencil" ' +
                    'data-toggle="modal" data-target="#editModal"  type="button">编辑</button>',
                        '<button id="del" class="btn btn-primary btn-xs btn-danger glyphicon glyphicon-remove" data-toggle="modal" data-target="#delModal" type="button">删除</button>',
                    ].join("")
                }
            },]
        });
    }else if (id=="4"){
        $("#table4").bootstrapTable('destroy');
        $('#table4').bootstrapTable({
            url:'/delboot',  //请求后台url
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
                    dictionary:dictionary,
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
                    var pageSize = $('#table4').bootstrapTable('getOptions').pageSize;     //通过table的#id 得到每页多少条
                    var pageNumber = $('#table4').bootstrapTable('getOptions').pageNumber; //通过table的#id 得到当前第几页
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
            },  {
                title : '父级',
                field : 'parent',
                align : 'center',
                valign : 'center',
            },  {
                title : '顺序',
                field : 'sequence',
                align : 'center',
                valign : 'center',
            }, {
                title : '备注',
                field : 'comment',
                align : 'center',
                valign : 'center',
            },  {
                title : '操作',
                field : 'operation',
                formatter : function (value, row, index) {
                    return[ '<button id="table_edit" class="btn btn-primary btn-xs btn-info glyphicon glyphicon-pencil" ' +
                    'data-toggle="modal" data-target="#editModal"  type="button">编辑</button>',
                        '<button id="del" class="btn btn-primary btn-xs btn-danger glyphicon glyphicon-remove" data-toggle="modal" data-target="#delModal" type="button">删除</button>',
                    ].join("")
                }
            },]
        });
    }else if (id=="5"){
        $("#table5").bootstrapTable('destroy');
        $('#table5').bootstrapTable({
            url:'/delboot',  //请求后台url
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
                    dictionary:dictionary,
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
                    var pageSize = $('#table5').bootstrapTable('getOptions').pageSize;     //通过table的#id 得到每页多少条
                    var pageNumber = $('#table5').bootstrapTable('getOptions').pageNumber; //通过table的#id 得到当前第几页
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
            },  {
                title : '父级',
                field : 'parent',
                align : 'center',
                valign : 'center',
            },  {
                title : '顺序',
                field : 'sequence',
                align : 'center',
                valign : 'center',
            }, {
                title : '备注',
                field : 'comment',
                align : 'center',
                valign : 'center',
            },  {
                title : '操作',
                field : 'operation',
                formatter : function (value, row, index) {
                    return[ '<button id="table_edit" class="btn btn-primary btn-xs btn-info glyphicon glyphicon-pencil" ' +
                    'data-toggle="modal" data-target="#editModal"  type="button">编辑</button>',
                        '<button id="del" class="btn btn-primary btn-xs btn-danger glyphicon glyphicon-remove" data-toggle="modal" data-target="#delModal" type="button">删除</button>',
                    ].join("")
                }
            },]
        });
    }

}

