var IntelligentRetrieval={
    keyWord: "",
    tableId: "",
    queryType: "",
    imgSearchCheck:function(){
        alert("敬请期待!");
    },
    pictureView:function(document_number,fileAddressStr,fileNameStr){
//        console.log(document_number);
//        console.log(fileNameStr);
//        console.log(fileAddressStr);
        $("#detailModal_confirm").trigger("click");
        $('#pictureViewModal').modal("show");
        $('#picture_carousel_inner').empty();
        $('#picture_carousel-indicators').empty();
        var fileNameArr = fileNameStr.split(";");
        var fileAddressArr = fileAddressStr.split(";");
        $('#picture_carousel-indicators').append('<li data-target="#myCarousel" data-slide-to="0" class="active"></li>')
        $('#picture_carousel_inner').append('<div class="item active" align="center">'+
                                '<a href="'+ fileNameArr[0] +'">'+
                                ' <img src="'+ fileAddressArr[0] + '" style="height:400; width:400;"'+
                                ' alt="通用的占位符缩略图" >'+
                                '</a> </div>')
        for ( var i = 1; i <fileNameArr.length-1; i++){  //末尾多一个分号
                    $('#picture_carousel-indicators').append('<li data-target="#myCarousel" data-slide-to="0" ></li>')
                    $('#picture_carousel_inner').append('<div class="item" align="center">'+
                                            '<a href="'+ fileNameArr[i] +'">'+
                                            ' <img src="'+ fileAddressArr[i] +'"style="height:400; width:400;"'+
                                            ' alt="通用的占位符缩略图" >'+
                                            '</a> </div>')
        }

    },
    keySearchCheck:function(){
            IntelligentRetrieval.keyWord=$("#keyword").val();
            IntelligentRetrieval.queryType=$("input[name='queryTypeOptionsRadios']:checked").val();
            if(IntelligentRetrieval.keyWord!=null&&IntelligentRetrieval.keyWord!=""){
                if("fullTextSearch"==IntelligentRetrieval.queryType){
                    IntelligentRetrieval.keySearch();
                }else{
                    alert("敬请期待!");
                }
            }else{
                alert("关键字不能为空!");
            }
    },
    keySearch: function () {
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
        $("#intelligentRetrieval_container_head").remove();
        $("#intelligentRetrieval_container_tail").remove();
        $("#intelligentRetrieval_container_row").css({"border-bottom":"2px solid #337ab7"});
        $("#intelligentRetrieval_bootstrapTable").bootstrapTable('destroy');
        $("#intelligentRetrieval_bootstrapTable").css({"table-layout":"fixed"});
        $('#intelligentRetrieval_bootstrapTable').bootstrapTable({
                url:'/IntegratedQuery/IntelligentRetrieval/KeySearch',  //请求后台url
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