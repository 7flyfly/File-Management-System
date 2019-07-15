
// $('#userRoleTable').bootstrapTable({
//     url:'/UserRoleTable',  //请求后台url
//     method:'get',
//     showRefresh:false, //是否显示刷新按钮
//     cardView: false,   //是否显示详细视图
//     showToggle : true, // 是否显示详细视图和列表视图的切换按钮
//     showColumns : true, // 是否显示列操作按钮
//     detailView : false, // 是否显示详细视图
//     striped : true, // 设置为true会有隔行变色效果
//     dataType : "json", // 服务器返回的数据类型
//     pagination : true, // 设置为true会在底部显示分页条
//     singleSelect : true, // 设置为true将禁止多选
//     clickToSelect : true, // 是否启用点击选中行
//     pageSize : 10, // 如果设置了分页，每页数据条数
//     pageNumber : 1, // 如果设置了分布，首页页码
//     pageList: [5, 10, 15, 20],  //可供选择的每页的行数
//     search : false, // 是否显示搜索框
//     showRefresh:true,
//     sidePagination : "server", // 设置在哪里进行分页，可选值为"client" 或者 "server"
//     contentType: "application/json;charset=utf-8",
//     queryParams: function (params) {
//         return{
//             pageSize : params.limit, // 每页显示数量
//             offset : params.offset, // 起始索引
//         }
//     },
//     columns : [
//         {
//         title : '序号',
//         field : 'id',
//         align : 'center',
//         valign : 'center',
//         formatter : function (value, row, index) {
//             var pageSize = $('#userRoleTable').bootstrapTable('getOptions').pageSize;     //通过table的#id 得到每页多少条
//             var pageNumber = $('#userRoleTable').bootstrapTable('getOptions').pageNumber; //通过table的#id 得到当前第几页
//             return pageSize * (pageNumber - 1) + index + 1;    // 返回每条的序号： 每页条数 *（当前页 - 1 ）+ 序号
//         }
//     },{
//         title : '姓名',
//         field : 'name',
//         align : 'center',
//         valign : 'center',
//     }, {
//         title : '账号',
//         field : 'username',
//         align : 'center',
//         valign : 'center',
//     },
//         {
//             title : '部门',
//             field : 'department',
//             align : 'center',
//             valign : 'center',
//         },  {
//             title : '角色',
//             field : 'role',
//             align : 'center',
//             valign : 'center',
//         }, {
//             title : '授权人',
//             field : 'authorizer',
//             align : 'center',
//             valign : 'center',
//         },
//         {
//             title : '授权时间',
//             field : 'authorize_time',
//             align : 'center',
//             valign : 'center',
//         }, {
//             title : '操作',
//             field : 'operation',
//             width:"150px",
//             formatter : function (value, row, index) {
//                 return['<button id="add"   class="btn btn-primary btn-xs btn-default glyphicon glyphicon-user" data-toggle="modal" data-target="#giveRoleModal"  type="button">角色分配</button>',
//                     '<button id="del" class="btn btn-primary btn-xs btn-danger glyphicon glyphicon-remove" data-toggle="modal" data-target="#delModal" type="button">删除</button>',
//                 ].join("")
//             }
//         },],
// });

$('#giveRoleModal').on('show.bs.modal',function (event) {
    var btnThis=$(event.relatedTarget);//触发事件的按钮
    var modal=$(this);//当前模态框
    var username= btnThis.closest('tr').find('td').eq(2).text();
    modal.find("#userid").val(username);
    var user_role = btnThis.closest('tr').find('td').eq(4).text();
    //var role  = user_role.split("/");
    console.log(user_role);
    $('#user_role').bootstrapTable({
        url:'/UserRole',  //请求后台url
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
        clickToSelect:true,
        trimOnSearch:true,
        singleSelect:false,
        queryParams: function (params) {
            return{
                pageSize : params.limit, // 每页显示数量
                offset : params.offset, // 起始索引
            }
        },
        columns : [{
          checkbox: true,
            formatter:function (value, row, index) {
              console.log(row.code);
              if (user_role.indexOf(row.code)>0){
                  return {
                      checked:true
                  }
              } else {
                  return {
                      checked: false
                  }
              }
            }
        },{
            title : '序号',
            field : 'id',
            align : 'center',
            valign : 'center',
            formatter : function (value, row, index) {
                var pageSize = $('#user_role').bootstrapTable('getOptions').pageSize;     //通过table的#id 得到每页多少条
                var pageNumber = $('#user_role').bootstrapTable('getOptions').pageNumber; //通过table的#id 得到当前第几页
                return pageSize * (pageNumber - 1) + index + 1;    // 返回每条的序号： 每页条数 *（当前页 - 1 ）+ 序号
            }
        },
            {
            title : '角色',
            field : 'name',
            align : 'center',
            valign : 'center',
        }, {
            title : '代码',
            field : 'code',
            align : 'center',
            valign : 'center',

        },
            {
                title : '添加人员',
                field : 'person',
                align : 'center',
                valign : 'center',
            },  {
                title : '添加时间',
                field : 'time',
                align : 'center',
                valign : 'center',
            }],
    })
});

$("#save").click(function () {
    var userid = $("#userid").val();
    var  b = $("#user_role").bootstrapTable("getSelections");
    var a = JSON.stringify(b);
    $.ajax({
        dataType:"json",
        traditional:true,//这使json格式的字符不会被转码
        data:{
            "username":userid,
            "role":a
        },
        type:"post",
        url:"/saveRole",
    });
});