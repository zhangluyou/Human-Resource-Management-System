package com.jointem.hrm.controller.converter;

import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

//自定义转换器
public class CustomGlobalStrToDateConverter  implements Converter<String, Date>{
	@Override
	public Date convert(String source) {

		try {
			Date date=new SimpleDateFormat("yy-MM-dd").parse(source);

			return date;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
