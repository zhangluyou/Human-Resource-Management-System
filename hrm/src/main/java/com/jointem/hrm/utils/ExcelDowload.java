package com.jointem.hrm.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelDowload {
	
	public void outputExcel(OutputStream outputStream,String filename, String type) throws FileNotFoundException {
		// TODO Auto-generated method stub
//		FileOutputStream outputStream = new FileOutputStream(filepath + filename + type);
		Workbook workbook = null;
		if (type.equals(".xls"))
			workbook = new HSSFWorkbook();
		HSSFFont font = (HSSFFont) workbook.createFont();
		font.setFontHeightInPoints((short) 9);
		font.setFontName("新宋体");
		font.setColor(HSSFColor.BLACK.index);
		font.setBoldweight((short) 0.8);
		// 2.生成样式对象

		HSSFCellStyle style = (HSSFCellStyle) workbook.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		style.setFont(font); // 调用字体样式对象
		style.setWrapText(true);
		HSSFCellStyle style1 = (HSSFCellStyle) workbook.createCellStyle();
		style1.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style1.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		// 调用字体样式对象
		style1.setWrapText(true);
		HSSFFont font1 = (HSSFFont) workbook.createFont();
		font1.setFontHeightInPoints((short) 15);
		font1.setFontName("新宋体");
		font1.setColor(HSSFColor.BLACK.index);
		font1.setBoldweight((short) 0.8);
		style1.setFont(font1);
		// 增加表格边框的样式 例子
		HSSFSheet sheet = (HSSFSheet) workbook.createSheet("sheet1");
		sheet.addMergedRegion(new CellRangeAddress(0, 2, 0, 0));
		sheet.addMergedRegion(new CellRangeAddress(0, 2, 1, 1));
		sheet.addMergedRegion(new CellRangeAddress(0, 2, 2, 2));
		sheet.addMergedRegion(new CellRangeAddress(0, 2, 3, 3));
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 4, 9));

		sheet.addMergedRegion(new CellRangeAddress(1, 2, 4, 4));
		sheet.addMergedRegion(new CellRangeAddress(1, 1, 5, 8));
		sheet.addMergedRegion(new CellRangeAddress(1, 2, 9, 9));

		sheet.addMergedRegion(new CellRangeAddress(0, 0, 10, 16));

		sheet.addMergedRegion(new CellRangeAddress(1, 2, 10, 10));
		sheet.addMergedRegion(new CellRangeAddress(1, 2, 11, 11));
		sheet.addMergedRegion(new CellRangeAddress(1, 2, 12, 12));
		sheet.addMergedRegion(new CellRangeAddress(1, 2, 13, 13));
		sheet.addMergedRegion(new CellRangeAddress(1, 2, 14, 14));
		sheet.addMergedRegion(new CellRangeAddress(1, 2, 15, 15));
		sheet.addMergedRegion(new CellRangeAddress(1, 2, 16, 16));

		sheet.addMergedRegion(new CellRangeAddress(0, 0, 17, 25));

		sheet.addMergedRegion(new CellRangeAddress(1, 2, 17, 17));
		sheet.addMergedRegion(new CellRangeAddress(1, 2, 18, 18));
		sheet.addMergedRegion(new CellRangeAddress(1, 2, 19, 19));
		sheet.addMergedRegion(new CellRangeAddress(1, 2, 20, 20));
		sheet.addMergedRegion(new CellRangeAddress(1, 2, 21, 21));
		sheet.addMergedRegion(new CellRangeAddress(1, 2, 22, 22));
		sheet.addMergedRegion(new CellRangeAddress(1, 2, 23, 23));
		sheet.addMergedRegion(new CellRangeAddress(1, 2, 24, 24));
		sheet.addMergedRegion(new CellRangeAddress(1, 2, 25, 25));

		sheet.addMergedRegion(new CellRangeAddress(0, 0, 26, 43));

		sheet.addMergedRegion(new CellRangeAddress(1, 2, 26, 26));
		sheet.addMergedRegion(new CellRangeAddress(1, 2, 27, 27));
		sheet.addMergedRegion(new CellRangeAddress(1, 2, 28, 28));
		sheet.addMergedRegion(new CellRangeAddress(1, 2, 29, 29));
		sheet.addMergedRegion(new CellRangeAddress(1, 2, 30, 30));
		sheet.addMergedRegion(new CellRangeAddress(1, 1, 31, 34));
		sheet.addMergedRegion(new CellRangeAddress(1, 1, 35, 40));
		sheet.addMergedRegion(new CellRangeAddress(1, 2, 41, 41));
		sheet.addMergedRegion(new CellRangeAddress(1, 2, 42, 42));
		sheet.addMergedRegion(new CellRangeAddress(1, 2, 43, 43));

		sheet.addMergedRegion(new CellRangeAddress(0, 2, 44, 44));
		sheet.addMergedRegion(new CellRangeAddress(0, 2, 45, 45));                                                      
		sheet.addMergedRegion(new CellRangeAddress(0, 2, 46, 46));
		sheet.setDefaultColumnWidth(15);
		HSSFRow rowT = sheet.createRow(0);
		rowT.createCell(0).setCellValue("序号");
		rowT.createCell(1).setCellValue("职员\n姓名");
		rowT.createCell(2).setCellValue("职位");
		rowT.createCell(3).setCellValue("部门\n名称");
		HSSFCell cell = rowT.createCell(4);
		cell.setCellValue("出勤");
		HSSFCell cell1 = rowT.createCell(5 + 5);
		cell1.setCellValue("固定部分");
		HSSFCell cell2 = rowT.createCell(5 + 5 + 7);
		cell2.setCellValue("浮动部分");
		HSSFCell cell3 = rowT.createCell(5 + 5 + 7 + 9);
		cell3.setCellValue("税前扣款");
		rowT.createCell(5 + 5 + 7 + 9 + 18).setCellValue("应付工资");
		rowT.createCell(5 + 5 + 7 + 9 + 18 + 1).setCellValue("个税");
		rowT.createCell(5 + 5 + 7 + 9 + 18 + 1 + 1).setCellValue("实发工资");
		int i = 0;
		HSSFRow row1 = sheet.createRow(1);
		i = i + 4;
		row1.createCell(i).setCellValue("应出勤\n天数");
		i = i + 1;
		row1.createCell(i).setCellValue("缺勤天数");
		i = i + 4;
		row1.createCell(i).setCellValue("实出勤\n天数");
		i = i + 1;
		row1.createCell(i).setCellValue("职务工资/\n基本工资");
		i = i + 1;
		row1.createCell(i).setCellValue("职级\n工资");
		i = i + 1;
		row1.createCell(i).setCellValue("通讯\n补贴");
		i = i + 1;
		row1.createCell(i).setCellValue("交通\n补贴");
		i = i + 1;
		row1.createCell(i).setCellValue("餐费\n补贴");
		i = i + 1;
		row1.createCell(i).setCellValue("保密费");
		i = i + 1;
		row1.createCell(i).setCellValue("固定部\n分合计");
		i = i + 1;
		row1.createCell(i).setCellValue("全勤奖");
		i = i + 1;
		row1.createCell(i).setCellValue("加班费");
		i = i + 1;
		row1.createCell(i).setCellValue("司龄\n补贴");
		i = i + 1;
		row1.createCell(i).setCellValue("绩效\n工资");
		i = i + 1;
		row1.createCell(i).setCellValue("生日\n福利");
		i = i + 1;
		row1.createCell(i).setCellValue("提成");
		i = i + 1;
		row1.createCell(i).setCellValue("奖金\n补贴");
		i = i + 1;
		row1.createCell(i).setCellValue("其他\n补款");
		i = i + 1;
		row1.createCell(i).setCellValue("浮动\n合计");
		i = i + 1;
		row1.createCell(i).setCellValue("事假\n扣除");
		i = i + 1;
		row1.createCell(i).setCellValue("病假\n扣除");
		i = i + 1;
		row1.createCell(i).setCellValue("旷工\n扣除");
		i = i + 1;
		row1.createCell(i).setCellValue("入职/离职未出\n勤扣除");
		i = i + 1;
		row1.createCell(i).setCellValue("迟到\n扣除");
		i = i + 1;
		row1.createCell(i).setCellValue("本月社\n会保险");
		i = i + 4;
		row1.createCell(i).setCellValue("补扣\n保险");
		i = i + 6;
		row1.createCell(i).setCellValue("住房公基金\n个人费用");
		i = i + 1;
		row1.createCell(i).setCellValue("其他\n扣除");
		i = i + 1;
		row1.createCell(i).setCellValue("税前扣\n款合计");
		HSSFRow row2 = sheet.createRow(2);
		i = 5;
		row2.createCell(i).setCellValue("事假");
		i = i + 1;
		row2.createCell(i).setCellValue("病假");
		i = i + 1;
		row2.createCell(i).setCellValue("旷工");
		i = i + 1;
		row2.createCell(i).setCellValue("入离职当月\n未出勤");
		i = i + 23;
		row2.createCell(i).setCellValue("养老");
		i = i + 1;
		row2.createCell(i).setCellValue("医疗");
		i = i + 1;
		row2.createCell(i).setCellValue("失业");
		i = i + 1;
		row2.createCell(i).setCellValue("小计");
		i = i + 1;
		row2.createCell(i).setCellValue("养老");
		i = i + 1;
		row2.createCell(i).setCellValue("医疗");
		i = i + 1;
		row2.createCell(i).setCellValue("失业");
		i = i + 1;
		row2.createCell(i).setCellValue("工伤");
		i = i + 1;
		row2.createCell(i).setCellValue("生育");
		i = i + 1;
		row2.createCell(i).setCellValue("小计");
		setStyle(rowT, style);
		setStyle(row1, style);
		setStyle(row2, style);
		cell.setCellStyle(style1);
		cell1.setCellStyle(style1);
		cell2.setCellStyle(style1);
		cell3.setCellStyle(style1);
		row1.setHeight((short)500);
		row2.setHeight((short)500);
		rowT.setHeight((short)500);
		try {
			workbook.write(outputStream);
			outputStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void setStyle(HSSFRow row, HSSFCellStyle style) {
		Iterator<Cell> iterator = row.cellIterator();
		while (iterator.hasNext()) {
			Cell cell = iterator.next();
			cell.setCellStyle(style);
		}
	}
}
