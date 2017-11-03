/**
 * Created by zhanglu on 2017/8/22.
 */
/**
 * Created by zhanglu on 2017/8/18.
 */
var psize = 5;
var pNum ;

$(function () {
    var totalPage = initInstitutionList(1);
    initPage(totalPage);
});
function initPage(total){
    $("#pagination1").pagination({
        currentPage: pNum,
        totalPage:total,
        callback: function (current) {
            pNum = current;
            $("#current1").text(current);
            initInstitutionList(pNum);
        }

    });
}
Handlebars.registerHelper('createtime', function() {
    return this.createtime.substring(0, 10);
});

function initInstitutionList(pageNum){
    var total1;
    var btn;
    var isRead=$("#isRead  option:selected").val();
    btn = $('#nameIn').val();
    $.ajax({
        type: 'POST',
        async : false,
        url: 'http://localhost:8080/hrm/institution/institutionlist',
        data: {"pageNum":pageNum , "pageSize": psize,"name":btn ,"isRead":isRead},
        success: function (data) {
            console.log(data.data)
            //total1 = (Math.ceil(Math.ceil(data.data.count) % Math.ceil(psize))) == 0 ?((Math.ceil(Math.ceil(data.data.count) / Math.ceil(psize)))):((Math.ceil(Math.ceil(data.data.count) / Math.ceil(psize))))+1;
          total1 = Math.ceil(Math.ceil(data.data.count) / Math.ceil(psize));
            initTemplate(data);
            if(data.data.size <= 0 ){
                $("#no_instution_list_data").css({"display":"block"});
            }

        }

    });

    return total1;
}
function initTemplate(data) {

    var myTemplate;
    //注册一个Handlebars模版，通过id找到某一个模版，获取模版的html框架
    //$("#table-template").html()是jquery的语法，不懂的童鞋请恶补。。。
    if(data.data.role=="0")
    {

        myTemplate = Handlebars.compile($("#institution_table").html());

    //将json对象用刚刚注册的Handlebars模版封装，得到最终的html，插入到基础table中。
    $('#institutionTable').html(myTemplate(data.data));
    }
    else {
        myTemplate = Handlebars.compile($("#institutionSmall_table").html());

        //将json对象用刚刚注册的Handlebars模版封装，得到最终的html，插入到基础table中。
        $('#institutionTableSmall').html(myTemplate(data.data));
    }
}
// 搜索
function lookup() {
    var totalPage = initInstitutionList(1);
    initPage(totalPage);
}
function delIns() {
    var obj = document.getElementsByName('isSelect');
    //console.info(obj)
    var ids = '';
    for (var i = 0; i < obj.length; i++) {
        if (obj[i].checked)
            ids += obj[i].value + ','; //如果选中，将value添加到变量中
    }
    if (ids == ''||ids==null) {
        alert("Please choose one!");
    }
    else {
        $.ajax({
            type: 'POST',
            url: 'http://localhost:8080/hrm/institution/deleteAllInstitution.do',
            async : false,
            data : {"ids":ids},
            success: function (data) {
               if(data.code=="111111"){
                   alert(data.msg);
               }
                window.location.reload();
            }
        });

    }
}