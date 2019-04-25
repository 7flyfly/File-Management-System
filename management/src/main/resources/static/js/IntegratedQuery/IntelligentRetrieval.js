IntelligentRetrieval={
    keyword: "",
    tableId: "",
    queryType: "",
    result: "",
    pageSize: 10,
    pageNo: 0,
    pageNo: 0
    , keySerach: function () {
        IntelligentRetrieval.pageNo = 1;
        IntelligentRetrieval.count = 0;
        IntelligentRetrieval.keyWord=$("#keyword").val();
        IntelligentRetrieval.queryType=$("input[name='queryTypeOptionsRadios']:checked").val();
        var data = {
        "keyword":IntelligentRetrieval.keyWord,
        "queryType": IntelligentRetrieval.queryType,
        "tableId": IntelligentRetrieval.tableId
        };
        console.log(data);
        $.ajax({
            url: '/IntegratedQuery/IntelligentRetrieval/KeySerach',
            data:{"dataJson":JSON.stringify(data)},
            dataType:"JSON",
            async : false,
            type:"GET",
            success:function(msg){
                IntelligentRetrieval.result = msg
            },
            error:function(msg){
                console.log("errorMessage:"+JSON.stringify(msg))
            }
        });
        $("#intelligentRetrieval_container").empty();
        console.log(IntelligentRetrieval.result);
        $('#intelligentRetrieval_container').bootstrapTable({
                url:'/IntegratedQuery/IntelligentRetrieval/KeySerach2',  //请求后台url
                method:'get',
                showRefresh:false, //是否显示刷新按钮
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
                search : false, // 是否显示搜索框
                sidePagination : "server", // 设置在哪里进行分页，可选值为"client" 或者 "server"
                contentType: "application/json;charset=utf-8",
                queryParams: function (params) {
                    return{
                        // 说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
                        limit : params.limit,
                        pageSize :10,
                        pageNumber:params.pageNumber, //页面大小
                        offset : params.offset, //页码
                        search : params.search,
                    }
                },
                columns : [ {
                            title : 'DOCUMENT_NUMBER',
                            field : 'DOCUMENT_NUMBER',
                            align : 'center',
                            valign : 'center',
                            width : '10%',

                        }, {
                            title : 'personliable_s_c',
                            field : 'personliable_s_c',
                            align : 'center',
                            valign : 'center',
                            width : '10%'

                        }, {
                            title : '_version_',
                            field : '_version_',
                            align : 'center',
                            valign : 'center',
                            width : '10%',
                        }, {
                            title : 'partno_s',
                            field : 'partno_s',
                            align : 'center',
                            valign : 'center',
                            width : '10%'
                        }, {
                            title : 'no_s_c',
                            field : 'no_s_c',
                            align : 'center',
                            valign : 'center',
                            width : '10%'
                        }, {
                            title : 'title_ik_c',
                            field : 'title_ik_c',
                            align : 'center',
                            valign : 'center',
                            width : '30%'
                        }, {
                            title : 'id1',
                            field : 'id',
                            align : 'center',
                            valign : 'center',
                            width : '10%'
                        }, {
                            title : 'last_modified',
                            field : 'last_modified',
                            align : 'center',
                            valign : 'center',
                            width : '10%'
                        }]
            });
    }
}