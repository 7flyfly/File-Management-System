
$('#registTable').bootstrapTable({
    url:'/registTable',  //请求后台url
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
    },
        {
        title : '操作',
        field : 'operation',
            formatter : function (value, row, index) {
              var status= row["state"];
              if (status =="归还") {
                  return[
                      '<button id="table_del" class="btn btn-primary btn-xs btn-danger glyphicon glyphicon-remove"data-toggle="modal" data-target="#delModal" type="button">删除</button>',
                  ].join("");
              }else {
                  return[
                      '<button id="table_del" class="btn btn-primary btn-xs btn-return glyphicon glyphicon-hand-up"data-toggle="modal" data-target="#backModal" type="button">归还</button>',
                  ].join("");
              }
            }
    },]
});
//提交更改
function save(obj) {
    //获取模态框数据

    var select = document.getElementById("getType");
    var index = select.selectedIndex;
    var type= select.options[index].text;//选中的值

    var registrant = $('#register').val();
    var beginDate = $('#borrowerDate').val();
    var unit= $('#unit').val();
    var name = $('#name').val();
    var phone = $('#phone').val();

    var select = document.getElementById("certType");
    var index = select.selectedIndex;
    var certificateType= select.options[index].text;//选中的值

    var certificateNumber = $('#borrowerIdno').val();
    var day = $('#borrowday').val();

    var obj = document.getElementsByName("way");
    var way="";
    for (k in obj){
        if(obj[k].checked){
            way = way+obj[k].value;
        }
    }
    var select = document.getElementById("reason");
    var index = select.selectedIndex;
    var purpose= select.options[index].text;//选中的值

    //var sex = $('input:radio[name="sex"]:checked').val();
    var jsonObj= {
        'type':type,
        'name': name,
        "registrant": registrant,
        "beginDate": beginDate,
        "phone": phone,
        "unit": unit,
        "certificateType": certificateType,
        "certificateNumber": certificateNumber,
        "day": day,
        "purpose": purpose,
        "way":way,
    };
    $.ajax({
        type: "post",
        url: "/postRegist",
        data: JSON.stringify(jsonObj),
        dataType: "json",
        contentType: "application/json",
        success: function(result) {
            window.location.reload();
        }
    });
    $('#addModal').modal('hide');
}

//模态框中内容表
$('#contentTable').bootstrapTable({
    url:'',  //请求后台url
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
            title : '档案库',
            field : 'filestore',
        },{
            title : '档案条目',
            field : 'fileEntry',

        }, {
            title : '操作',
            field : 'operation',
            formatter : function (value, row, index) {
                return[
                    '<button id="table_del" class="btn btn-primary btn-xs btn-danger glyphicon glyphicon-remove"  type="button">删除</button>'
                ].join("")
            }
        },]
});

//查询
function searchR(obj) {
    var select = document.getElementById("query-type");
    var index = select.selectedIndex;
    var query_type= select.options[index].text;//选中的值
    var  query_keward=  $('#dd').val(); //查询利用人姓名

    $("#registTable").bootstrapTable('destroy');
    $('#registTable').bootstrapTable({
        url:'/searchRegist',  //请求后台url
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
                query_type: query_type,//返回给控制层
                query_keward:query_keward,
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
            }, {
                title : '登记日期',
                field : 'recordDate',
            },{
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
            },
            {
                title : '归还经办人',
                field : 'turn',
            },
            {
                title : '归还日期',
                field : 'returnData',
            },
            {
                title : '操作',
                field : 'operation',
                formatter : function (value, row, index) {
                    var status= row["state"];
                    if (status =="归还") {
                        return[
                            '<button id="table_del" class="btn btn-primary btn-xs btn-danger glyphicon glyphicon-remove"data-toggle="modal" data-target="#delModal" type="button">删除</button>',
                        ].join("");
                    }else {
                        return[
                            '<button id="table_del" class="btn btn-primary btn-xs btn-return glyphicon glyphicon-hand-up"data-toggle="modal" data-target="#backModal" type="button">归还</button>',
                        ].join("");
                    }
                }
            },]
    });
}

//归还操作
$('#backModal').on('show.bs.modal',function (event) {
    var btnThis=$(event.relatedTarget);//触发事件的按钮
    var modal=$(this);//当前模态框
    var num= btnThis.closest('tr').find('td').eq(1).text();
    modal.find("#num").val(num);

});

function confirm(obj){
    var num= $('#num').val();
    $('#backModal').modal('hide');
    $("#registTable").bootstrapTable('destroy');
    $('#registTable').bootstrapTable({
        url:'/backFile',  //请求后台url
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
                num: num,//返回给控制层
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
            }, {
                title : '登记日期',
                field : 'recordDate',
            },{
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
            },
            {
                title : '归还经办人',
                field : 'turn',
            },
            {
                title : '归还日期',
                field : 'returnData',
            },
            {
                title : '操作',
                field : 'operation',
                formatter : function (value, row, index) {
                    var status= row["state"];
                    if (status =="归还") {
                        return[
                            '<button id="table_del" class="btn btn-primary btn-xs btn-danger glyphicon glyphicon-remove"data-toggle="modal" data-target="#delModal" type="button">删除</button>',
                        ].join("");
                    }else {
                        return[
                            '<button id="table_del" class="btn btn-primary btn-xs btn-return glyphicon glyphicon-hand-up"data-toggle="modal" data-target="#backModal" type="button">归还</button>',
                        ].join("");
                    }
                }
            },]
    });
}

//删除行
$('#delModal').on('show.bs.modal',function (event) {
    var btnThis=$(event.relatedTarget);//触发事件的按钮
    var modal=$(this);//当前模态框
    var num= btnThis.closest('tr').find('td').eq(1).text();
    modal.find("#approveNum").val(num);
});
function removeForm(obj) {
    var num= $('#approveNum').val();
    var jsonObj= {
        "approveNum": num,
    };
    $.ajax({
        type: "post",
        url: "/deleteRegister",
        data: JSON.stringify(jsonObj),
        dataType: "json",
        contentType: "application/json",
        success: function(result) {
            window.location.reload();
        }
    });
    $('#delModal').modal('hide');
}

//库模态框表格
$('#mt-database').bootstrapTable({
    url:'/getDatabase',  //请求后台url
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
    clickToSelect: true,
    queryParams: function (params) {
        return{
            pageSize : params.limit, // 每页显示数量
            offset : params.offset, // 起始索引
        }
    },
    columns : [
        {
            checkbox: true,
        }, {
            title : '序号',
            field : 'id',
            formatter : function (value, row, index) {
                var pageSize = $('#registTable').bootstrapTable('getOptions').pageSize;     //通过table的#id 得到每页多少条
                var pageNumber = $('#registTable').bootstrapTable('getOptions').pageNumber; //通过table的#id 得到当前第几页
                return pageSize * (pageNumber - 1) + index + 1;    // 返回每条的序号： 每页条数 *（当前页 - 1 ）+ 序号
            }
        },{
            title : '文件数',
            field : 'docunmentNum',

        }, {
            title : '全宗号',
            field : 'caseNo',
        },  {
            title : '归档年度',
            field : 'ryear',
        },  {
            title : '分类号',
            field : 'classification',
        }, {
            title : '档号',
            field : 'documentNo',
        }, {
            title : '题名',
            field : 'title',
        }, {
            title : '主题词',
            field : 'subject',
        }, {
            title : '归档单位',
            field : 'unit',
        }, {
            title : '归档日期',
            field : 'rdate',
        }, {
            title : '开始日期',
            field : 'begindate',
        }, {
            title : '结束日期',
            field : 'enddate',
        }, {
            title : '密级',
            field : 'security',
        }, {
            title : '保管期限',
            field : 'deadline',
        },{
            title : '描述',
            field : 'description',
        },{
            title : '盘号',
            field : 'reel',
        },
            {
            title : '电子存址',
            field : 'eaddress',
        },{
            title : '盒号',
            field : 'boxno',
        },
        {
            title : '纸质存址',
            field : 'paddress',
        },{
            title : '业务状态',
            field : 'status',
        },
        {
            title : '创建者',
            field : 'creator',
        },
        {
            title : '创建时间',
            field : 'createtime',
        },]
});

function confirm_D(obj) {
    var getSeectRows = $('#mt-database').bootstrapTable('getSelections',function (row) {
        return row;
    });
    var documentNo= getSeectRows[0].documentNo;

    var select = document.getElementById("database");
    var index = select.selectedIndex;
    var database= select.options[index].text;//选中的值


    $("#contentTable").bootstrapTable('destroy');
    $('#contentTable').bootstrapTable({
        url:'/showDatabase',  //请求后台url
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
                documentNo: documentNo,
                database : database,
                pageSize : params.limit, // 每页显示数量
                offset : params.offset, // 起始索引
            }
        },
        columns : [
            {
                title : '档案库',
                field : 'filestore',
            },{
                title : '档案条目',
                field : 'documentNo',

            }, {
                title : '操作',
                field : 'operation',
                formatter : function (value, row, index) {
                    return[
                        '<button id="table_del" class="btn btn-primary btn-xs btn-danger glyphicon glyphicon-remove"  type="button">删除</button>'
                    ].join("")
                }
            },]
    });
}

