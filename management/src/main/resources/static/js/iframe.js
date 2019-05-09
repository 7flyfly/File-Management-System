function changeFrameHeight(){
    var ifm= document.getElementById("iframepage"); 
    ifm.height=document.documentElement.clientHeight;

}

window.onresize=function(){  
     changeFrameHeight();  

} 