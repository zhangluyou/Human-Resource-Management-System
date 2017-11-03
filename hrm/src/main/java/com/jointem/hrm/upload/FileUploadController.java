package com.jointem.hrm.upload;

import com.jointem.hrm.utils.OperationFileUtil;
import com.jointem.hrm.utils.ReadPropertesUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

/**
 * Created by dartagnan on 17/8/1.
 */
@Controller
@RequestMapping("/file")
public class FileUploadController {

    @RequestMapping("/upload")
    public String uploadPage(){
        return "upload";
    }

    @RequestMapping("/upload2.do")
    public @ResponseBody String upload2( HttpServletRequest request, ModelMap model) {
        System.out.println("开始");
        try {
            String path = ReadPropertesUtil.getValue("filePath");
            OperationFileUtil.multiFileUpload(request,path);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "result";
    }
}
