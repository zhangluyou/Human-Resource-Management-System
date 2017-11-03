/**
 * Created by zhanglu on 2017/8/29.
 */
//加载弹出选择用户框的数据
function chooseUser(currPage)
{

    //发送ajax请求
    var url="http://localhost:8080/hrm/institution/getStudentsList";
    var params=
        {
            currPage:currPage
        };
    $("#dynamicBox").html("");
    $.post(url,params,function(result){

        $.each(result.list,function(n,value)
        {

            var userName=$("#userName").val();
            if(userName!="")
            {
                var userNameList=userName.split(",");
                if($.inArray(value.username,userNameList)!=-1)
                {
                    var content = "<tr><td>";
                    content += "<label><input type='checkbox' checked='checked'  id='userIds' name='userIds' data-value='"+value.username+"' value='"+value.id+"' onclick=javascript:window.isChecked(this) >"+value.username+"</label>";
                    content +="</td></tr>";
                    $("#dynamicBox").append(content);
                }
                else
                {
                    var content = "<tr><td>";
                    content += "<label><input type='checkbox'   id='userIds' name='userIds' data-value='"+value.username+"' value='"+value.id+"' onclick=javascript:window.isChecked(this) >"+value.username+"</label>";
                    content +="</td></tr>";
                    $("#dynamicBox").append(content);
                }


            }
            else
            {
                var content = "<tr><td>";
                content += "<label><input type='checkbox'   id='userIds' name='userIds' data-value='"+value.username+"' value='"+value.id+"' onclick=javascript:window.isChecked(this) >"+value.username+"</label>";
                content +="</td></tr>";
                $("#dynamicBox").append(content);
            }



        });
        if(result.currPage!=1)
        {
            page=result.currPage-1;
            var content="<a onclick=chooseUser('"+page+"')>上一页</a>";
            $("#dynamicBox").append(content);
        }


        for (var i=1;i<=result.totalPage;i++)
        {
            var content="<a onclick=chooseUser('"+i+"')>"+i+"</a>";
            $("#dynamicBox").append(content);
            $("#dynamicBox").append("   ");
        }
        if(result.currPage!=result.totalPage)
        {
            page=result.currPage+1;
            var content="<a onclick=chooseUser('"+page+"')>下一页</a>";
            $("#dynamicBox").append(content);
        }
    },"json");


}
//选中用户之后添加到隐藏域中
function isChecked(obj)
{
    if($(obj).is(":checked"))
    {
        var tmp=$("#userIdTemp").val();
        var tmpUserName=$("#usernameTemp").val();
        if(tmpUserName!="")
        {
            $("#usernameTemp").val(tmpUserName+","+$(obj).attr('data-value'));
        }
        else
        {
            $("#usernameTemp").val($(obj).attr('data-value'));
        }
        if(tmp!="")
        {
            $("#userIdTemp").val(tmp+","+$(obj).val());
        }
        else
        {
            $("#userIdTemp").val($(obj).val());
        }
    }
    else
    {
        var tmpUserName=$("#usernameTemp").val();
        var tmp=$("#userIdTemp").val();
        console.info(tmp);
        var tmplist=tmp.split(",");
        var tmpUserNameList=tmpUserName.split(",");
        if($.inArray($(obj).attr('data-value'),tmpUserNameList)!=-1)
        {
            if(tmpUserNameList.length==1)
            {
                var tmpUserName=tmpUserName.replace($(obj).attr('data-value'),"");
            }
            var tmpUserName=tmpUserName.replace(","+$(obj).attr('data-value'),"");
        }
        if($.inArray($(obj).val() ,tmplist)!=-1)
        {
            console.info(tmplist.length);
            if(tmplist.length==1)
            {
                var tmp=tmp.replace($(obj).val(),"");
            }
            var tmp=tmp.replace(","+$(obj).val(),"");

        }
        console.info($(obj).val());
        console.info(tmplist);
        console.info(tmp);
        $("#userIdTemp").val(tmp);
        $("#usernameTemp").val(tmpUserName);
    }
}

//全选,将复选框全部选中，并将所有选中的值加入隐藏域中
function selectAll(obj)
{
    var arr=document.getElementsByName("userIds");

    //遍历数组 修改选中状态
    for(var i=0;i<arr.length;i++)
    {
        arr[i].checked=obj.checked;
        isChecked(arr[i]);
    }
}

//将选择的用户加入输入框中，并将模态框隐藏
function addUsersToInput()
{
    $("#userName").val($("#usernameTemp").val());
    $("#myModal").modal('hide');
}