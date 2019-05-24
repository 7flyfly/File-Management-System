

$('#registTable').bootstrapTable({
    url:'/getApplication',  //请求后台url
    method:'get',
    showRefresh:false, //是否显示刷新按钮
    cardView: false,   //是否显示详细视图
    showToggle : false, // 是否显示详细视图和列表视图的切换按钮
    sScrollY : true,
    sScrollX : true,
    showColumns : false, // 是否显示列操作按钮
    bPaginate : true, //是否显示（应用）分页器
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
    columns : [
        {
            title : '序号',
            field : 'id',
            formatter : function (value, row, index) {
                var pageSize = $('#registTable').bootstrapTable('getOptions').pageSize;     //通过table的#id 得到每页多少条
                var pageNumber = $('#registTable').bootstrapTable('getOptions').pageNumber; //通过table的#id 得到当前第几页
                return pageSize * (pageNumber - 1) + index + 1;    // 返回每条的序号： 每页条数 *（当前页 - 1 ）+ 序号
            }
        },{
            title : '单号',
            field : 'oddNumbers',

        }, {
            title : '类型',
            field : 'type',
        },  {
            title : '登记人',
            field : 'registrant',
        },  {
            title : '登记日期',
            field : 'recordDate',
        }, {
            title : '单位',
            field : 'unit',
        }, {
            title : '姓名',
            field : 'name',
        }, {
            title : '利用始日期',
            field : 'date',
        }, {
            title : '联系电话',
            field : 'telphone',
        }, {
            title : '证件类型',
            field : 'certificateType',
        }, {
            title : '证件号码',
            field : 'certificateNumber',
        }, {
            title : '目的',
            field : 'purpose',
        }, {
            title : '方式',
            field : 'way',
        }, {
            title : '内容',
            field : 'contant',
        },{
            title : '状态',
            field : 'state',
        },{
            title : '出借经办人',
            field : 'stloanAgentate',
        },
        //     {
        //     title : '出借提交日期',
        //     field : 'loanUbmissionDate',
        // },{
        //     title : '出借日期',
        //     field : 'loanDate',
        // },
        {
            title : '借阅天数',
            field : 'day',
        },{
            title : '归还经办人',
            field : 'turn',
        },
        {
            title : '归还日期',
            field : 'returnData',
        }]
});