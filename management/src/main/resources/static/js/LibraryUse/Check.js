

//添加数据
$(document).on("click",".que_add",function () {
    var lastId=$("table tr:last").data("id")+1
    var type=$("#getType").val()
    var register=$("#register").val()
    $.ajax({
        type:"get",
        url: "LibraryUse/Check",
        async:true,
        data:{
            action: "add",
            id: lastId,
            type: type,
            registrant:register
        },
        success:function (res) {
            var str='<tr data-id='+lastId+'><td>'+lastId+'</td><td>'+type+'</td><td>'+registrant+'</td>'
            $("table").append(str)
            $("#addModal").modal("hide")
        }
    })
})