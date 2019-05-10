

$('#userTable').bootstrapTable({
    url:'/UserTable',  //请求后台url
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
        width:"100px",
        formatter : function (value, row, index) {
            var pageSize = $('#userTable').bootstrapTable('getOptions').pageSize;     //通过table的#id 得到每页多少条
            var pageNumber = $('#userTable').bootstrapTable('getOptions').pageNumber; //通过table的#id 得到当前第几页
            return pageSize * (pageNumber - 1) + index + 1;    // 返回每条的序号： 每页条数 *（当前页 - 1 ）+ 序号
        }
    },{
        title : '姓名',
        field : 'name',
        align : 'center',
        valign : 'center',
        width:"100px",
    }, {
        title : '账号',
        field : 'account',
        align : 'center',
        valign : 'center',
        width:"150px",
    },  {
        title : '部门',
        field : 'department',
        align : 'center',
        valign : 'center',
        width:"200px",
    },  {
        title : '邮箱',
        field : 'mail',
        align : 'center',
        valign : 'center',
        width:"150px",
    }, {
        title : '电话',
        field : 'phone',
        align : 'center',
        valign : 'center',
        width:"100px",
    }, {
        title : '角色',
        field : 'role',
        align : 'center',
        valign : 'center',
        width:"150px",
    }, {
        title : '状态',
        field : 'status',
        align : 'center',
        valign : 'center',
        width:"80px",
    }, {
        title : '操作',
        field : 'operation',
        width:"150px",
        formatter : function (value, row, index) {
            return[ '<button id="table_edit" class="btn btn-primary btn-xs btn-default glyphicon glyphicon-pencil" ' +
            'data-toggle="modal" data-target="#editModal"   type="button">编辑</button>',
                '<button id="table_del" class="btn btn-primary btn-xs btn-default glyphicon glyphicon-cog"data-toggle="modal" data-target="#kuModal" type="button" >库权限</button>',
            ].join("")
        }
    },]
});

$('#editModal').on('show.bs.modal',function (event) {
    var btnThis=$(event.relatedTarget);//触发事件的按钮
    var modal=$(this);//当前模态框
    //var modalId = btnThis.data('id');   //解析出data-id的内容
    var name= btnThis.closest('tr').find('td').eq(1).text();
    var account= btnThis.closest('tr').find('td').eq(2).text();
    var department= btnThis.closest('tr').find('td').eq(3).text();
    var mail= btnThis.closest('tr').find('td').eq(4).text();
    var phone= btnThis.closest('tr').find('td').eq(5).text();
    var role= btnThis.closest('tr').find('td').eq(6).text();

    if (btnThis.closest('tr').find('td').eq(7).text() == '正常') {
             modal.find("#statusEdit").select('#NORMAL');
         } else {
             modal.find("#statusEdit").select("#LOCK");
         }
    modal.find('#nameEdit').val(name);
    modal.find('#accountEdit').val(account);
    modal.find('#departmentEdit').val(department);
    modal.find('#mailEdit').val(mail);
    modal.find('#phoneEdit').val(phone);
    modal.find('#roleEdit').val(role);
});

//保存修改
function saveEdit(obj) {
    //获取模态框数据
    var name = $('#nameEdit').val();
    var account = $('#accountEdit').val();
    var password=$('#passwordEdit').val();
    var department= $('#departmentEdit').val();
    var mail = $('#mailEdit').val();
    var phone = $('#phoneEdit').val();
    var role = $('#roleEdit').val();
    var select = document.getElementById("statusEdit");
    var index = select.selectedIndex;
    var status= select.options[index].text;//选中的值
    //var sex = $('input:radio[name="sex"]:checked').val();
    var jsonObj= {
        'account':account,
        'name': name,
        'password': password,
        "department": department,
        "mail": mail,
        "phone": phone,
        "role": role,
        "status": status,
    }
    $.ajax({
        type: "post",
        url: "/saveEdit",
        data: JSON.stringify(jsonObj),
        dataType: "json",
        contentType: "application/json",
        success: function(result) {
            window.location.reload();
            //showData(result);
        }
    });
    $('#editModal').modal('hide');
}

//提交更改
function save(obj) {
    //获取模态框数据
    var name = $('#name').val();
    var account = $('#account').val();
    var password=$('#password').val();
    var department= $('#department').val();
    var mail = $('#mail').val();
    var phone = $('#phone').val();
    var role = $('#role').val();

    var select = document.getElementById("status");
    var index = select.selectedIndex;
    var status= select.options[index].text;//选中的值

    //var sex = $('input:radio[name="sex"]:checked').val();
    var jsonObj= {
        'account':account,
        'name': name,
        'password': password,
        "department": department,
        "mail": mail,
        "phone": phone,
        "role": role,
        "status": status,
    }
    $.ajax({
        type: "post",
        url: "/saveUsers",
        data: JSON.stringify(jsonObj),
        dataType: "json",
        contentType: "application/json",
        success: function(result) {
            window.location.reload();
            //showData(result);
        }
    });
    $('#addModal').modal('hide');
}