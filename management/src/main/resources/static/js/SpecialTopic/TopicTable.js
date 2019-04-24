
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
      contentType: "application/x-www-form-urlencoded",
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
          field:"id"
      },{
          title:"名称",
          field:"name",
      },{
          title: "内容",
          field: "content",
      },{
          title: "创建人",
          field: "creator ",
      },{
          title: "创建时间",
          field: "createDate",
      },{
          title: "发布人",
          field: "issuer"
      },{
          title: "发布时间",
          field: "releaseTime"
      },{
          title:"操作",
          field:"operation"
      }],
  });
}