
$(function () {
    load();
});

function load() {
  $('table').bootstrapTable({
      url:'/topic',  //请求后台url
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
      columns:[{
          title:"序号",
          field:"id",
          formatter : function (value, row, index) {
              var pageSize = $('#table').bootstrapTable('getOptions').pageSize;     //通过table的#id 得到每页多少条
              var pageNumber = $('#table').bootstrapTable('getOptions').pageNumber; //通过table的#id 得到当前第几页
              return pageSize * (pageNumber - 1) + index + 1;    // 返回每条的序号： 每页条数 *（当前页 - 1 ）+ 序号
          }
      },{
          title:"名称",
          field:"name",
      },{
          title: "内容",
          field: "contant",
      },{
          title: "创建人",
          field: "creater",
      },{
          title: "创建时间",
          field: "createtime",
      },{
          title: "发布人",
          field: "publisher"
      },{
          title: "发布时间",
          field: "publishtime"
      },{
          title:"操作",
          field:"operation",
          formatter : function (value, row, index) {
              return[ '<button id="table_edit" class="btn btn-primary btn-xs btn-info glyphicon glyphicon-pencil" ' +
              'data-toggle="modal" data-target="#editModal"  type="button">编辑</button>',
                  '<button id="del" class="btn btn-primary btn-xs btn-danger glyphicon glyphicon-remove" data-toggle="modal" data-target="#delModal" type="button">删除</button>',
              ].join("")
          }
      }],
  });
}

function save(obj) {

    //获取模态框数据
    var name = $('#name').val();
    var contant = $('#contant').val();
    var creater= $('#creater').val();
    var publisher = $('#publisher').val();
    var time = $('#r-time').val();
    var jsonObj= {
        'name': name,
        "contant": contant,
        "creater": creater,
        "publisher": publisher,
        "time": time,
    };
    $.ajax({
        type: "post",
        url: "/postSpecial",
        data: JSON.stringify(jsonObj),
        dataType: "json",
        contentType: "application/json",
        success: function(result) {
            window.location.reload();
            //showData(result);
        }
    });
    $('#specialModal').modal('hide');
}

