IntelligentRetrieval={
    keyword: "",
    tableId: "",
    queryType: ""
    , keySerach: function () {
        IntelligentRetrieval.keyWord=$("#keyword").val();
        IntelligentRetrieval.queryType=$("input[name='queryTypeOptionsRadios']:checked").val();
//        var data = {
//        "keyword":IntelligentRetrieval.keyWord,
//        "queryType": IntelligentRetrieval.queryType,
//        "tableId": IntelligentRetrieval.tableId
//        };
//        console.log(data);
//        $.ajax({
//            url: '/IntegratedQuery/IntelligentRetrieval/KeySerach',
//            data:{"dataJson":JSON.stringify(data)},
//            dataType:"JSON",
//            async : false,
//            type:"GET",
//            success:function(msg){
//                IntelligentRetrieval.result = msg
//            },
//            error:function(msg){
//                console.log("errorMessage:"+JSON.stringify(msg))
//            }
//        });
        $("#intelligentRetrieval_bootstrapTable").empty();
        $('#intelligentRetrieval_bootstrapTable').bootstrapTable({
                url:'/IntegratedQuery/IntelligentRetrieval/KeySerach',  //请求后台url
                method:'get',
                showRefresh:false, //是否显示刷新按钮
                cardView: false,   //是否显示详细视图
                showToggle : true, // 是否显示详细视图和列表视图的切换按钮
                showColumns : true, // 是否显示列操作按钮
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
                        keyword:IntelligentRetrieval.keyWord,
                        queryType: IntelligentRetrieval.queryType,
                        tableId: IntelligentRetrieval.tableId,
                        pageSize : params.limit, // 每页显示数量
                        offset : params.offset, // 起始索引
                    }
                },
                columns : [ {
                            title : '序号',
                            field : 'row_index',
                            align : 'center',
                            valign : 'center',
                            width : '10%',
                            formatter : function (value, row, index) {
                                var pageSize = $('#intelligentRetrieval_bootstrapTable').bootstrapTable('getOptions').pageSize;     //通过table的#id 得到每页多少条
                                var pageNumber = $('#intelligentRetrieval_bootstrapTable').bootstrapTable('getOptions').pageNumber; //通过table的#id 得到当前第几页
                                return pageSize * (pageNumber - 1) + index + 1;    // 返回每条的序号： 每页条数 *（当前页 - 1 ）+ 序号
                            }
                        },{
                            title : '档案号',
                            field : 'DOCUMENT_NUMBER',
                            align : 'center',
                            valign : 'center',
                            width : '10%',

                        }, {
                            title : '案卷号/件号',
                            field : 'partno_s',
                            align : 'center',
                            valign : 'center',
                            width : '10%'
                        },  {
                            title : '题名',
                            field : 'title_ik_c',
                            align : 'center',
                            valign : 'center',
                            width : '30%'
                        },  {
                            title : '责任者',
                            field : 'personliable_s_c',
                            align : 'center',
                            valign : 'center',
                            width : '10%'
                        },
                        {
                            title : '操作',
                            field : 'Button',
                            align : 'center',
                            valign : 'center',
                            width : '10%',
                            formatter : function (value, row, index) {
                                return[ '<button id="table_amend" class="btn btn-success table_btn amend" type="button">详情</button>',
                                        '<button id="table_amend" class="btn btn-success table_btn amend" type="button">全文</button>',
                                ].join("")
                            }
                        },]
            });
    }
}