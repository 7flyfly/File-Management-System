$(function () {
    load();
});

function load() {
    $('#table').bootstrapTable({
        url:'/queryTable',  //请求后台url
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
            title : '序号',
            field : 'id',
            align : 'center',
            valign : 'center',
            formatter : function (value, row, index) {
                var pageSize = $('#table').bootstrapTable('getOptions').pageSize;     //通过table的#id 得到每页多少条
                var pageNumber = $('#table').bootstrapTable('getOptions').pageNumber; //通过table的#id 得到当前第几页
                return pageSize * (pageNumber - 1) + index + 1;    // 返回每条的序号： 每页条数 *（当前页 - 1 ）+ 序号
            }

        }, {
            title : '单号',
            field : 'approvalNumber',
            align : 'center',
            valign : 'center',
            //width : '15%'

        },  {
            title : '利用类型',
            field : 'type',
            align : 'center',
            valign : 'center',
            //width : '10%'
        }, {
            title : '登记人',
            field : 'registrant',
            align : 'center',
            valign : 'center',
            //width : '8%'
        }, {
            title : '登记日期',
            field : 'recordDate',
            align : 'center',
            valign : 'center',
            //width : '8%'
        }, {
            title : '单位',
            field : 'unit',
            align : 'center',
            valign : 'center',
           // width : '15%'
        }, {
            title : '姓名',
            field : 'name',
            align : 'center',
            valign : 'center',
            //width : '5%'
        }, {
            title : '利用始日期',
            field : 'date',
            align : 'center',
            valign : 'center',
            //width : '8%'
        }, {
            title : '联系电话',
            field : 'telphone',
            align : 'center',
            valign : 'center',
            //width : '8%'
        }, {
            title : '证件类型',
            field : 'certificateType',
            align : 'center',
            valign : 'center',
            //width : '5%'
        }, {
            title : '证件号码',
            field : 'certificateNumber',
            align : 'center',
            valign : 'center',
            //width : '5%'
        }, {
            title : '目的',
            field : 'purpose',
            align : 'center',
            valign : 'center',
            //width : '5%'
        }, {
            title : '方式',
            field : 'way',
            align : 'center',
            valign : 'center',
            //width : '8%'
        }, {
            title : '内容',
            field : 'contant',
            align : 'center',
            valign : 'center',
            //width : '10%'
        }, {
            title : '状态',
            field : 'state',
            align : 'center',
            valign : 'center',
            //width : '8%'
        }, {
            title : '出借经办人',
            field : 'stloanAgentate',
            align : 'center',
            valign : 'center',
            //width : '8%'
        }, {
            title : '出借提交日期',
            field : 'loanUbmissionDate',
            align : 'center',
            valign : 'center',
            //width : '10%'
        }, {
            title : '出借日期',
            field : 'loanDate',
            align : 'center',
            valign : 'center',
           // width : '10%'
        }, {
            title : '出借天数',
            field : 'day',
            align : 'center',
            valign : 'center',
            //width : '10%'
        }, {
            title : '归还经办人',
            field : 'ReturnPerson',
            align : 'center',
            valign : 'center',
           // width : '10%'
        }, {
            title : '归还日期',
            field : 'reDate',
            align : 'center',
            valign : 'center',
            //width : '10%'
        }
        ]
    });
}


//获取状态
function getStatus(obj){
    $.ajax({
        url: "/getSearchStatus",
        async: true,
        type:"get",
        dataType: "json",
        success: function (data,htmlData) {
            var array = new Array();
            $("#searchStatus option").each(function () {
                var txt = $(this).text();
                if (txt != "所有状态")
                    array.push(txt);
            });
            for(var i=0;i<data.length;i++){
                var value = data[i].sequence;
                var lable = data[i].name;
                if (array.indexOf(lable)<0 )
                    $('#searchStatus').append("<option value="+value+">"+lable+"</option>");
            }
            $('#searchStatus').selectpicker('refresh');
        },
        error: function (data) {
            alert("没数据");
        } });
}

function search(obj) {
    var select = document.getElementById("type");
    var index = select.selectedIndex;
    var query_type= select.options[index].text;//选中类型的值

    var select = document.getElementById("searchStatus");
    var index = select.selectedIndex;
    var query_status= select.options[index].text;//选中状态的值
    $('#table').bootstrapTable('deatory')
    $('#table').bootstrapTable({
        url:'/searchTypeAndStatus',  //请求后台url
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
                query_type:query_type,
                query_status:query_status,
                // 说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
                limit : params.limit,
                pageSize :10,
                pageNumber:params.pageNumber, //页面大小
                offset : params.offset, //页码
                search : params.search,
            }
        },
        columns : [ {
            title : '序号',
            field : 'id',
            align : 'center',
            valign : 'center',
            formatter : function (value, row, index) {
                var pageSize = $('#table').bootstrapTable('getOptions').pageSize;     //通过table的#id 得到每页多少条
                var pageNumber = $('#table').bootstrapTable('getOptions').pageNumber; //通过table的#id 得到当前第几页
                return pageSize * (pageNumber - 1) + index + 1;    // 返回每条的序号： 每页条数 *（当前页 - 1 ）+ 序号
            }

        }, {
            title : '单号',
            field : 'approvalNumber',
            align : 'center',
            valign : 'center',
            //width : '15%'

        },  {
            title : '利用类型',
            field : 'type',
            align : 'center',
            valign : 'center',
            //width : '10%'
        }, {
            title : '登记人',
            field : 'registrant',
            align : 'center',
            valign : 'center',
            //width : '8%'
        }, {
            title : '登记日期',
            field : 'recordDate',
            align : 'center',
            valign : 'center',
            //width : '8%'
        }, {
            title : '单位',
            field : 'unit',
            align : 'center',
            valign : 'center',
            // width : '15%'
        }, {
            title : '姓名',
            field : 'name',
            align : 'center',
            valign : 'center',
            //width : '5%'
        }, {
            title : '利用始日期',
            field : 'date',
            align : 'center',
            valign : 'center',
            //width : '8%'
        }, {
            title : '联系电话',
            field : 'telphone',
            align : 'center',
            valign : 'center',
            //width : '8%'
        }, {
            title : '证件类型',
            field : 'certificateType',
            align : 'center',
            valign : 'center',
            //width : '5%'
        }, {
            title : '证件号码',
            field : 'certificateNumber',
            align : 'center',
            valign : 'center',
            //width : '5%'
        }, {
            title : '目的',
            field : 'purpose',
            align : 'center',
            valign : 'center',
            //width : '5%'
        }, {
            title : '方式',
            field : 'way',
            align : 'center',
            valign : 'center',
            //width : '8%'
        }, {
            title : '内容',
            field : 'contant',
            align : 'center',
            valign : 'center',
            //width : '10%'
        }, {
            title : '状态',
            field : 'state',
            align : 'center',
            valign : 'center',
            //width : '8%'
        }, {
            title : '出借经办人',
            field : 'stloanAgentate',
            align : 'center',
            valign : 'center',
            //width : '8%'
        }, {
            title : '出借提交日期',
            field : 'loanUbmissionDate',
            align : 'center',
            valign : 'center',
            //width : '10%'
        }, {
            title : '出借日期',
            field : 'loanDate',
            align : 'center',
            valign : 'center',
            // width : '10%'
        }, {
            title : '出借天数',
            field : 'day',
            align : 'center',
            valign : 'center',
            //width : '10%'
        }, {
            title : '归还经办人',
            field : 'ReturnPerson',
            align : 'center',
            valign : 'center',
            // width : '10%'
        }, {
            title : '归还日期',
            field : 'reDate',
            align : 'center',
            valign : 'center',
            //width : '10%'
        }
        ]
    });
}