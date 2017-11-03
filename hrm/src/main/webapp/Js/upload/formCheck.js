/******表单验证*********/
$(function(){
/*修改密码表单验证 */ 
	 $("#s-changePassword").validate({
    	 success:"right",
    	 errorElement:"em",
        rules: {
        	password:{
                required:true,
                minlength:6,
                maxlength:18,
                remote: {
                    url: "/gov/user/validateUserPassword",     //后台处理程序
                    type: "post",               //数据发送方式
                    dataType: "json",           //接受数据格式   
                    data: {                     //要传递的数据
                    	password: function() {
                            return $("#oldPassword").val();
                        }
                    }
                }
            },
            newPassword: {
                required: true,
                minlength:6,
                maxlength:18,
            },
            passwordSure: {
            	required: true,
                equalTo: "#newPassword",
                maxlength:18,
            }
        },
        messages:{
        	password:{
                required:"请输入原密码",
                minlength:"请输入6-18位的数字、字母、特殊符号的密码",
                maxlength:"请输入6-18位的数字、字母、特殊符号的密码",
                remote: "原密码错误！"
            },
            newPassword: {
                required: "请输入新密码",
                minlength:"请输入6-18位的数字、字母、特殊符号的密码",
                maxlength:"请输入6-18位的数字、字母、特殊符号的密码",
            },
            passwordSure: {
            	required: "请输入确认密码",
                equalTo: "两次输入密码不一致",
                maxlength:"请输入6-18位的数字、字母、特殊符号的密码",
            }
        }
        
    })
});

$(".changPassword-btn").click(function (){
    if($("#s-changePassword").valid()){
    	Utils.getRequestData("/gov/user/updatePassword",$("#s-changePassword").serialize(),function(data){
    		if(data.code=="000000"){
    			 dialog.initDialog($("#f-dialog-container"),{
    				  content:'修改成功',
    				  ok_callback:function(html){
    					 window.location.href="/gov/logout"
    				  }
    			  })
    		}
			},"POST",{})
     }
   
    
/*账号管理表单验证 */ 
    
});