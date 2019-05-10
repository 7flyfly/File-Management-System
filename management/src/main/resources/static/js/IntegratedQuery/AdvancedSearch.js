var AdvancedSearch={
    allKeyWord: "",
    keyWord: "",
    anyKeyWord: "",
    noKeyWord: "",
    nodeName: "",
    keyWordPosition: "",
    SearchCheck:function(){
            AdvancedSearch.allKeyWord=$("#contain_allKeyWord").val();
            AdvancedSearch.documentNumber=$("#contian_documentNumber").val();
            AdvancedSearch.anyKeyWord=$("#contian_anyKeyWord").val();
            AdvancedSearch.noKeyWord=$("#contian_noKeyWord").val();
            AdvancedSearch.nodeName=$("#contian_nodeName").val();
            AdvancedSearch.keyWordPosition=$("input[name='keyWordPositionOptionsRadios']:checked").val();
            if(AdvancedSearch.allKeyWord==null&&AdvancedSearch.allKeyWord==""&&
            AdvancedSearch.documentNumber==null&&AdvancedSearch.documentNumber==""&&
            AdvancedSearch.nodeName==null&&AdvancedSearch.nodeName==""&&
            AdvancedSearch.anyKeyWord==null&&AdvancedSearch.anyKeyWord==""&&
            AdvancedSearch.noKeyWord==null&&AdvancedSearch.noKeyWord==""){
                alert("关键字不能为空!");
            }else{
                console.log(AdvancedSearch.allKeyWord);
                console.log(AdvancedSearch.documentNumber);
                console.log(AdvancedSearch.anyKeyWord);
                console.log(AdvancedSearch.noKeyWord);
                console.log(AdvancedSearch.nodeName);
                console.log(AdvancedSearch.keyWordPosition);
                AdvancedSearch.conditionalSearch()
            }
    },
    conditionalSearch: function () {
        $("#advancedSearch_head").remove();
        $("#advancedSearch_div").css({"border-bottom":"2px solid #337ab7"});
        $("#advancedSearch_bootstrapTable").bootstrapTable('destroy');
        $("#advancedSearch_bootstrapTable").css({"table-layout":"fixed"});
        $('#advancedSearch_bootstrapTable').bootstrapTable({
                url:'/IntegratedQuery/AdvancedSearch/conditionalSearch',  //请求后台url
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
                        allKeyWord: AdvancedSearch.allKeyWord,
                        documentNumber: AdvancedSearch.documentNumber,
                        anyKeyWord: AdvancedSearch.anyKeyWord,
                        noKeyWord: AdvancedSearch.noKeyWord,
                        keyWordPosition: AdvancedSearch.keyWordPosition,
                        nodeName: AdvancedSearch.nodeName,
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
                                var pageSize = $('#advancedSearch_bootstrapTable').bootstrapTable('getOptions').pageSize;     //通过table的#id 得到每页多少条
                                var pageNumber = $('#advancedSearch_bootstrapTable').bootstrapTable('getOptions').pageNumber; //通过table的#id 得到当前第几页
                                return pageSize * (pageNumber - 1) + index + 1;    // 返回每条的序号： 每页条数 *（当前页 - 1 ）+ 序号
                            }
                        },{
                            title : '档案号',
                            field : 'document_number',
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
                            cellStyle:{
                                css:{
                                    "overflow": "hidden",
                                    "text-overflow": "ellipsis",
                                    "white-space": "nowrap"
                                }
                            },
                            width : '30%',
                            formatter:paramsMatter
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
                            events : operateEvents,
                            formatter : function (value, row, index) {
                                return[ '<button id="table_btn_detail" class="btn btn-info table_btn amend btn-sm" data-toggle="modal" data-target="#detailModal" >详情</button>&nbsp;&nbsp;&nbsp;&nbsp;',
                                        '<button id="table_btn_fullText" class="btn btn-info table_btn amend btn-sm" type="button">全文</button>',
                                ].join("")
                            }
                        },]
            });
    }
};

function paramsMatter(value, row, index) {
    //替换空格，因为字符串拼接的时候如果遇到空格，会自动将后面的部分截掉，所有这里用html的转义符
    //&nbsp;代替
    var value1 = value.replace(/\s+/g,'&nbsp;');
    return "<span title="+value1+">"+value1+"</span>";
}

window.operateEvents = {
    "click #table_btn_detail":function(e, row, index){
            if(typeof(index)!="undefined"){
                var data = {
                            "table_id":index.table_id_s,
                            "document_number": index.document_number,
                            };
                            console.log(data);
                            $.ajax({
                                url: '/IntegratedQuery/IntelligentRetrieval/getDetail',
                                data:{"dataJson":JSON.stringify(data)},
                                dataType:"JSON",
                                type:"GET",
                                success:function(msg){
                                    IntelligentRetrieval.result = msg;
                                    $('#detailModal').modal("show");
                                    $("#detailList").empty();
                                        var str = ""
                                        for(var p in msg){
//                                          console.log(p + " " + msg[p]);
                                          str = str + '<tr><td width="100px">'+ p +':</td><td>'+ msg[p] +'</td></tr>'
                                        }
                                    $("#detailList").append(str);
                                },
                                error:function(msg){
                                    console.log("errorMessage:"+JSON.stringify(msg));
                                }
                            });
            }else{
                console.log("index is undefined!")
            }
    },

    "click #table_btn_fullText":function(e, index){
            console.log("table_btn_detail")
            console.log(index);
    }
}