/**
 * Created by zhanglu on 2017/9/29.
 */
var psize = 5;
var pNum;

$(function () {
    var totalPage = initUserList(1);
    initPage(totalPage);
});
Handlebars.registerHelper('createtime', function () {
    return this.createtime.substring(0,10);
});
Handlebars.registerHelper('birthday', function () {
    if(this.birthday == null  || this.birthday == undefined){
        return "";
    }
    return this.birthday.substring(0,10);
});
Handlebars.registerHelper('sex', function () {
    if (this.sex == 1) {
        return "男";
    } else {
        return "女";
    }
});
// Handlebars.registerHelper('positionId', function () {
//
//     return this.birthday.substring(0,10);
// });
function initPage(total) {
    $("#pagination1").pagination({
        currentPage: pNum,
        totalPage: total,
        callback: function (current) {
            pNum = current;
            $("#current1").text(current);
            initUserList(pNum);
        }

    });
}

function initUserList(pageNum) {
    var total1;
    var btn;
    var drop;
    var start;
    var end;
    btn = $('#nameIn').val();
    drop = $('input:radio:checked').val();
    start = $('#startTime').val();
    end = $('#endTime').val();
    $.ajax({
        type: 'POST',
        cache: false,
        async: false,
        url: 'http://localhost:8080/hrm/users/listUser',
        //contentType: "application/json; charset=utf-8",
        data: {
            "pageNum": pageNum,
            "pageSize": psize,
            "sort": drop,
            "startTime": start,
            "endTime": end,
            "username": btn
        },
        // data: {"pageNum":pageNum , "pageSize": psize},

        success: function (data) {
            total1 = Math.ceil(Math.ceil(data.data.count) / Math.ceil(psize));
            initTemplate(data);

        }

    });

    return total1;
}
function initTemplate(data) {
    //注册一个Handlebars模版，通过id找到某一个模版，获取模版的html框架
    //$("#table-template").html()是jquery的语法，不懂的童鞋请恶补。。。
    var myTemplate = Handlebars.compile($("#user_table").html());

    //将json对象用刚刚注册的Handlebars模版封装，得到最终的html，插入到基础table中。
    $('#usersTable').html(myTemplate(data.data));
}
// 搜索
function lookup() {
    var totalPage = initUserList(1);
    //  alert(totalPage);
    initPage(totalPage);

}
function delEdu() {
    var obj = document.getElementsByName('isSelect');
    //console.info(obj)
    var ids = '';
    for (var i = 0; i < obj.length; i++) {
        if (obj[i].checked)
            ids += obj[i].value + ','; //如果选中，将value添加到变量中
    }
    if (ids == '' || ids == null) {
        alert("Please choose one!");
    }
    else {
        $.ajax({
            type: 'POST',
            url: 'http://localhost:8080/hrm/users/deleteAlluser.do',
            async: false,
            data: {"ids": ids},
            success: function (data) {
                if (data.code == "111111") {
                    alert(data.msg);
                }
                window.location.reload();
            }
        });

    }
}
