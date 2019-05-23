var yljData = "[" + $("#jsondataYlj").val() + "]";
var zlkData = "[" + $("#jsondataZlk").val() + "]";
var dakData = "[" + $("#jsondataDak").val() + "]";
$(function() {
    $('#treeYlj').treeview({
        color: "#428bca",
        //backColor: "black",背景色
        //borderColor:'green',
        collapseIcon: "glyphicon glyphicon-minus",//可收缩的节点图标
        nodeIcon: "glyphicon glyphicon-user",
        //emptyIcon: "glyphicon glyphicon-ban-circle",//设置列表树中没有子节点的节点的图标
        expandIcon: "glyphicon glyphicon-plus",  //设置列表上中有子节点的图标
        highlightSearchResults:true,//是否高亮搜索结果 默认true
        highlightSelected:true,     //是否选中高亮显示
        onhoverColor: "#f5f5f5",    //鼠标滑过的颜色
        levels: 3 ,                 //设置初始化展开几级菜单 默认为3
        selectedIcon: 'glyphicon glyphicon-tint',
        // selectedBackColor: 'black',  //设置被选中的节点背景颜色
        //selectedColor : 'red',      //设置被选择节点的字体、图标颜色
        showBorder:true,                //是否显示边框
        showCheckbox:false,              //是否显示多选框
        //uncheckedIcon:'',             //设置未选择节点的图标
        //data:$('#jsondata').val()
        data:yljData
    });

    // 设置点击事件触发节点的收缩展开
    $('#treeYlj').on('nodeSelected',function(event,data){
        if(data.state.expanded){
            $('#treeYlj').treeview('collapseNode', data.nodeId);
        }else{
            $('#treeYlj').treeview('expandNode', data.nodeId);
        }
    });

    $('#treeYlj').on('nodeSelected', function(event, data) {
        if(data.href!=null){
            document.getElementById("iframepage").src="/mywork/" + data.href;
            // document.getElementById("iframepage").src="http://www.baidu.com";
        }
        var arr = $('#treeYlj').treeview('getSelected');
    });

    $('#treeZlk').treeview({
        color: "#428bca",
        //backColor: "black",背景色
        //borderColor:'green',
        collapseIcon: "glyphicon glyphicon-minus",//可收缩的节点图标
        nodeIcon: "glyphicon glyphicon-user",
        //emptyIcon: "glyphicon glyphicon-ban-circle",//设置列表树中没有子节点的节点的图标
        expandIcon: "glyphicon glyphicon-plus",  //设置列表上中有子节点的图标
        highlightSearchResults:true,//是否高亮搜索结果 默认true
        highlightSelected:true,     //是否选中高亮显示
        onhoverColor: "#f5f5f5",    //鼠标滑过的颜色
        levels: 3 ,                 //设置初始化展开几级菜单 默认为3
        selectedIcon: 'glyphicon glyphicon-tint',
        // selectedBackColor: 'black',  //设置被选中的节点背景颜色
        //selectedColor : 'red',      //设置被选择节点的字体、图标颜色
        showBorder:true,                //是否显示边框
        showCheckbox:false,              //是否显示多选框
        //uncheckedIcon:'',             //设置未选择节点的图标
        //data:$('#jsondata').val()
        data:zlkData
    });

    // 设置点击事件触发节点的收缩展开
    $('#treeZlk').on('nodeSelected',function(event,data){
        if(data.state.expanded){
            $('#treeZlk').treeview('collapseNode', data.nodeId);
        }else{
            $('#treeZlk').treeview('expandNode', data.nodeId);
        }
    });

    $('#treeZlk').on('nodeSelected', function(event, data) {
        if(data.href!=null){
            document.getElementById("iframepage").src="/mywork/" + data.href;
            // document.getElementById("iframepage").src="http://www.baidu.com";
        }
        var arr = $('#treeZlk').treeview('getSelected');
    });

    $('#treeDak').treeview({
        color: "#428bca",
        //backColor: "black",背景色
        //borderColor:'green',
        collapseIcon: "glyphicon glyphicon-minus",//可收缩的节点图标
        nodeIcon: "glyphicon glyphicon-user",
        //emptyIcon: "glyphicon glyphicon-ban-circle",//设置列表树中没有子节点的节点的图标
        expandIcon: "glyphicon glyphicon-plus",  //设置列表上中有子节点的图标
        highlightSearchResults:true,//是否高亮搜索结果 默认true
        highlightSelected:true,     //是否选中高亮显示
        onhoverColor: "#f5f5f5",    //鼠标滑过的颜色
        levels: 3 ,                 //设置初始化展开几级菜单 默认为3
        selectedIcon: 'glyphicon glyphicon-tint',
        // selectedBackColor: 'black',  //设置被选中的节点背景颜色
        //selectedColor : 'red',      //设置被选择节点的字体、图标颜色
        showBorder:true,                //是否显示边框
        showCheckbox:false,              //是否显示多选框
        //uncheckedIcon:'',             //设置未选择节点的图标
        //data:$('#jsondata').val()
        data:dakData
    });

    // 设置点击事件触发节点的收缩展开
    $('#treeDak').on('nodeSelected',function(event,data){
        if(data.state.expanded){
            $('#treeDak').treeview('collapseNode', data.nodeId);
        }else{
            $('#treeDak').treeview('expandNode', data.nodeId);
        }
    });

    $('#treeDak').on('nodeSelected', function(event, data) {
        if(data.href!=null){
            document.getElementById("iframepage").src="/mywork/" + data.href;
            document.getElementById("iframepage").scrolling="yes";
            // document.getElementById("iframepage").src="http://www.baidu.com";
        }
        var arr = $('#treeDak').treeview('getSelected');
    });

});