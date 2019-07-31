
$('#roleModal').on('show.bs.modal',function (event) {
    var btnThis = $(event.relatedTarget);//触发事件的按钮
    var modal = $(this);//当前模态框
    var username = btnThis.closest('tr').find('td').eq(0).text(); //获取角色
    modal.find("#username-role").val(username);
    console.log(username);
    //模态框显示时，显示已经拥有角色
    var jsonObj = {
        'username':username
    };
    $.ajax({
        type: "post",
        url: "/getRole",
        data: JSON.stringify(jsonObj),
        dataType: "json",
        contentType: "application/json",
        success: function(result) {
            var objSelect = document.getElementsByName("role");
            for (var i=0;i<result.length;i++){
                for (j in objSelect){
                    if (result[i] == objSelect[j].value){
                        objSelect[j].checked = true;
                    }
                }
            }
        }
    });
});

$('#roleModal').on('hidden.bs.modal',function () {
    var objSelect = document.getElementsByName("role");
    var len = objSelect.length;
    for (k in objSelect) {
        if (objSelect[k].checked) {
            objSelect[k].checked = false;
        }
    }
});

function saveUserRole(){
    var username = $('#username-role').val();
    var objSelect = document.getElementsByName("role");
    var way= new Array();
    way.push(username);//首位保存用户
    for (k in objSelect){
        if(objSelect[k].checked){
            way.push(objSelect[k].value);
        }
    }
    //清空数据
    var select = document.getElementsByName("role");
    var len = select.length;
    for (k in select) {
        if (select[k].checked) {
            select[k].checked = false;
        }
    }
    console.log(way);
    $.ajax({
        type: "post",
        url: "/saveUserRole",
        data: JSON.stringify(way),
        dataType: "json",
        contentType: "application/json",
        success: function(result) {
            //window.location.reload();
        }
    });
}

//添加用户
function saveUser() {
    //获取模态框数据
    var username = $('#user-username').val();
    var name = $('#user-name').val();
    var password=$('#user-password').val();
    var department= $('#user-department').val();
    var mail = $('#user-mail').val();
    var phone = $('#user-phone').val();
    var select = document.getElementById("status");
    var index = select.selectedIndex;
    var status= select.options[index].text;//选中的值

    //var sex = $('input:radio[name="sex"]:checked').val();
    var jsonObj= {
        'username':username,
        'name': name,
        'password': password,
        "department": department,
        "mail": mail,
        "phone": phone,
        "status": status,
    }
    $.ajax({
        type: "post",
        url: "/saveUser",
        data: JSON.stringify(jsonObj),
        dataType: "json",
        contentType: "application/json",
        success: function(result) {
            window.location.reload();
            //showData(result);
        }
    });

}