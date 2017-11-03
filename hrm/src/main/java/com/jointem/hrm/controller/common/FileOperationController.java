package com.jointem.hrm.controller.common;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.jointem.hrm.utils.FTPUtils;
import com.jointem.hrm.utils.JSONUtil;
import com.jointem.hrm.utils.ReadPropertesUtil;
import com.jointem.hrm.utils.UUIDGenerator;


/**
 * 文件操作
 * @author yyf
 *
 */
@Controller
public class FileOperationController {
	private static final org.slf4j.Logger log = LoggerFactory
			.getLogger(FileOperationController.class);
	/**
	 * 登录注册上传图片到ftp服务器
	 * @param uploadFile
	 * @return
	 */
	@RequestMapping(value="/fileOperation/fileUploadWithLoginOption",produces="application/json;charset=UTF-8")
	@ResponseBody
	public  String fileUploadWithLoginOption(@RequestParam MultipartFile uploadFile) {
			try {
				log.info("上传图片开始");
				String descPath =ReadPropertesUtil.getValue("savePath");
				Map<String, Serializable> map = this.uploadFileToFtpByLoginOption(uploadFile, descPath);
				String json = JSONUtil.createJsonString(map);
				return json;
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
	}
	
	private Map<String, Serializable> uploadFileToFtpByLoginOption(MultipartFile uploadFile,String uploadPath) {
		Map<String, Serializable> resultMap = new HashMap<>();
		// 获取原来的文件名
		String oldName = uploadFile.getOriginalFilename();
		// 生成新文件名
		String newName = UUIDGenerator.generateName();
		newName = newName + oldName.substring(oldName.lastIndexOf("."));
		try {
			//生成日期目录
			String filePath =  new SimpleDateFormat("yyyyMMdd/").format(new Date());
			// 上传文件
			Integer port = Integer.parseInt(ReadPropertesUtil.getValue("FTP_PORT"));      
			
			boolean result = FTPUtils.uploadFile(ReadPropertesUtil.getValue("FTP_ADDRESS"), port,ReadPropertesUtil.getValue("FTP_USERNAME") , ReadPropertesUtil.getValue("FTP_PASSWORD"), uploadPath, 
					filePath, newName, uploadFile.getInputStream());
			if (!result) {
				resultMap.put("error", 2);
				resultMap.put("message", "文件上传失败");
			} else {
				resultMap.put("error", 0);
				resultMap.put("fileName", oldName);
				resultMap.put("url",ReadPropertesUtil.getValue("loginUrlPrefix")+filePath+newName);
				resultMap.put("urlPrefix",ReadPropertesUtil.getValue("loginUrlPathPrefix")+newName);
				resultMap.put("urlPath", ReadPropertesUtil.getValue("loginUrlPathPrefix")+ filePath+newName);
				//上传图片成功:入库
			}
		} catch (Exception e) {
			resultMap.put("error", 1);
			resultMap.put("message", "获取源文件失败");
			e.printStackTrace();
		}
		return resultMap;
	}
	/*@RequestMapping(value="/fileOperation/o_swfupload",produces="application/json;charset=utf-8")
	@ResponseBody
	public String swfUpload(@RequestParam MultipartFile uploadFile) {
		Map<String, Serializable> resultMap = new HashMap<>();
		response.setContentType("application/json;charset=utf-8");
		try {
			CmsSite site = CmsUtils.getSite(request);
			String sitePath=site.getPath();
			String frontEnclosure=PropertyUtils.getPropertyValue(new File(realPathResolver.get(com.yxgcms.cms.Constants.YXGCMS_CONFIG)),"frontEnclosure");
			String enclosureUrl=PropertyUtils.getPropertyValue(new File(realPathResolver.get(com.yxgcms.cms.Constants.YXGCMS_CONFIG)),"enclosureUrl");
			File newFile=new File(frontEnclosure+sitePath);
			if(!newFile.exists()){
				newFile.mkdir();
			}
			String root=frontEnclosure+sitePath;
			resourceMng.saveFile(request,root, uploadFile);
			log.info("file upload seccess: {}, size:{}.", uploadFile
					.getOriginalFilename(), uploadFile.getSize());  
			resultMap.put("url",enclosureUrl+sitePath+"/"+uploadFile.getOriginalFilename());
			resultMap.put("error", 0);
			resultMap.put("message", "上传成功！！");
			System.out.println(resultMap.get("url"));
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("error", 1);
			resultMap.put("message", "上传失败！！");
		}
		String json = JSONUtil.createJsonString(resultMap);
		return json;
	}*/
}
