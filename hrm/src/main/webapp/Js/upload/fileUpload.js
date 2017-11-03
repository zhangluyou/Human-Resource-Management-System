/**
 * Created by guosp on 2016/7/1 0001.
 */
var fileUpload=(function(){
    /**
     * 需要用户自己触发点击事件才能进行进行这个方法
     * @param obj
     * @returns {*}
     * img_before_callback 只对图片上传做处理，如果回调函数返回false，就不上传
     */
    var upload=function(obj,callback,newUrl){
        var maxLargeSize = 209715200;//200M
        try{
            var url=newUrl||implement.uploadFtp;
            var $this=$(obj);
            var file=$($this).find(":file");
            var accept=$($this).find(":hidden").attr("accept");
            
            if($(file).length<1){
                 file=$("<input type='file' class='click f-file ' style='display: none'/>").appendTo($this);
            }
            console.info($(file));
            $(file).off("click").on("click",function(event){
                event.stopPropagation();
            });
            
            $(file).off("change").on("change",function(e){
                
                var f = e.target.files;
                 if(f[0].size > maxLargeSize){
                     $(obj).append('<div class="upload-cover"><p>请上传50M以内的文件</p><div class="s-transition upload-cover-up"></div><div class="s-transition upload-cover-down"></div></div>');
                    return;
                 }
                $(obj).append('<div class="upload-cover"><p>0%</p><div class="s-transition upload-cover-up"></div><div class="s-transition upload-cover-down"></div></div>');
                if(f[0]){
                    if(f[0].type.indexOf("image")>-1){
                        var reader = new FileReader();
                        reader.onload=function(){
                            $(obj).find("img").attr("src",this.result);
                        };
                        reader.readAsDataURL(f[0]);
                    }
                }
                var form =$('<form method="post" enctype="multipart/form-data" action="'+url+'"></form>').appendTo("body");
                $(form).append($(file).attr("name","uploadFile"));
                var onprogress= function (evt){
                    var loaded = evt.loaded;     //已经上传大小情况
                    var tot = evt.total;      //附件总大小
                    var per = Math.floor(100*loaded/tot);  //已经上传的百分比
                    per=Math.min(99,per);
                    $(obj).find(".upload-cover p").html(per+"%");
                    $(obj).find(".upload-cover .upload-cover-up").css({
                        height:(100-per)+"%"
                    });
                    $(obj).find(".upload-cover .upload-cover-down").css({
                        height:per+"%"
                    });
                };
                $(form).ajaxSubmit({
                    dataType:"json",
                    success:function(message){
                        if("0"==message.error||0==message.error){
                            $($this).find(".tip").remove();
                            $($this).find(":hidden").val(message.urlPath)
                                .attr("data-url",message.urlPrefix+message.urlPath);
                            $(obj).find(".upload-cover").remove();
                            if("function"===typeof callback){
                                callback($this,message);
                            }
                        }
                    },
                    xhr: function(){
                        var xhr = $.ajaxSettings.xhr();
                        if(onprogress && xhr.upload) {
                            xhr.upload.addEventListener("progress" , onprogress, false);
                            return xhr;
                        }
                    },
                    complete: function (XHR, TS) {
                        XHR = null;
                    }
                });
            });
        }catch(e){
            console.info(e);
        }
        return file.click();
    };

   

    return {
        upload:upload
    }
})();

