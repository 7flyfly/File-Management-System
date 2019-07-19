//三级联动
var provice = [
    {
        name: "北京市",
        city: [
            {
                name: "北京市",
                districtAndCounty: ["东城区", "西城区", "崇文区", "宣武区", "朝阳区", "丰台区", "石景山区", "海淀区", "门头沟区", "房山区", "通州区", "顺义区", "昌平区", "大兴区", "怀柔区", "平谷区", "密云县", "延庆县", "延庆镇"]
            }
        ]
    },
    {
        name: "天津市",
        city: [
            {
                name: "天津市",
                districtAndCounty: ["和平区", "河东区", "河西区", "南开区", "河北区", "红桥区", "塘沽区", "汉沽区", "大港区", "东丽区", "西青区", "津南区", "北辰区", "武清区", "宝坻区", "蓟县", "宁河县", "芦台镇", "静海县", "静海镇"]
            }
        ]
    },
    {
        name: "上海市",
        city: [
            {
                name: "上海市",
                districtAndCounty: ["黄浦区", "卢湾区", "徐汇区", "长宁区", "静安区", "普陀区", "闸北区", "虹口区", "杨浦区", "闵行区", "宝山区?", "嘉定区", "浦东新区", "金山区", "松江区", "青浦区", "南汇区", "奉贤区", "崇明县", "城桥镇"]
            }
        ]
    }];

//根据id获取对象
// function $(str) {
//     return document.getElementById(str);
// }
$('#fileModal').on('show.bs.modal',function (event) {
    function $(str) {
        console.log(event);
        console.log(str);
        return document.getElementById(str);
    }
});
var addrShow = $('addr-show');
var btn = document.getElementsByClassName('met1')[0];
var prov = $('prov');
var city = $('city');
var country = $('country');

/*用于保存当前所选的省市区*/
var current = {
    prov: '',
    city: '',
    country: '',
};

/*自动加载省份列表*/
(function showProv() {
    btn.disabled =true;
    var  len = provice.length;
    for (var i=0;i<len;i++){
        var provOpt = document.createElement('option');
        provOpt.innerText = provice[i]['name'];
        provOpt.value = i;
        prov.appendChild(provOpt);

    }
})();

/*根据所选的省份来显示城市列表*/
function showCity(obj) {
    var val = obj.options[obj.selectedIndex].value;
    if (val != current.prov) {
        current.prov = val;
        addrShow.value = '';
        btn.disabled = true;
    }
    //console.log(val);
    if (val != null) {
        city.length = 1;
        var cityLen = provice[val]["city"].length;
        for (var j = 0; j < cityLen; j++) {
            var cityOpt = document.createElement('option');
            cityOpt.innerText = provice[val]["city"][j].name;
            cityOpt.value = j;
            city.appendChild(cityOpt);
        }
    }
}

/*根据所选的城市来显示县区列表*/
function showCountry(obj) {
    var val = obj.options[obj.selectedIndex].value;
    current.city = val;
    if (val != null) {
        country.length = 1; //清空之前的内容只留第一个默认选项
        var countryLen = provice[current.prov]["city"][val].districtAndCounty.length;
        if(countryLen == 0){
            addrShow.value = provice[current.prov].name + '-' + provice[current.prov]["city"][current.city].name;
            return;
        }
        for (var n = 0; n < countryLen; n++) {
            var countryOpt = document.createElement('option');
            countryOpt.innerText = provice[current.prov]["city"][val].districtAndCounty[n];
            countryOpt.value = n;
            country.appendChild(countryOpt);
        }
    }
}

/*选择县区之后的处理函数*/
function selecCountry(obj) {
    current.country = obj.options[obj.selectedIndex].value;
    if ((current.city != null) && (current.country != null)) {
        btn.disabled = false;
    }
}
/*点击确定按钮显示用户所选的地址*/
function showAddr() {
    addrShow.value = provice[current.prov].name + '-' + provice[current.prov]["city"][current.city].name + '-' + provice[current.prov]["city"][current.city].districtAndCounty[current.country];
}