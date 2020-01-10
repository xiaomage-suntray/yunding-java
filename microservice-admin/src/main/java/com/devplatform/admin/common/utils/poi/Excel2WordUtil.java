package com.devplatform.admin.common.utils.poi;


import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.PictureData;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.apache.xmlbeans.XmlCursor;
import org.docx4j.model.datastorage.XPathEnhancerParser.main_return;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBorder;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblBorders;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblWidth;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTc;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STBorder;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STJc;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STMerge;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STTblWidth;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STVerticalJc;
import org.springframework.util.StringUtils;

//import com.lowagie.text.Document;
//import com.lowagie.text.DocumentException;
//import com.lowagie.text.Font;
//import com.lowagie.text.Image;
//import com.lowagie.text.PageSize;
//import com.lowagie.text.Paragraph;
//import com.lowagie.text.Table;
//import com.lowagie.text.pdf.BaseFont;
//import com.lowagie.text.rtf.RtfWriter2;

/**
 *
 */
public class Excel2WordUtil {

	//	public static void main(String[] args) {
	//		String excelPath = "F:\\upload\\file\\compactTemplate\\test1.xlsx";
	//
	//		String wordPath = "F:\\upload\\file\\compactTemplate\\a.doc";
	//		excel2Word(excelPath, wordPath);
	//		String path = "F:\\upload\\file\\compactTemplate\\";
	//
	//		File file = new File(path);
	//		if (file.exists()) {
	//			if (file.isDirectory()) {
	//				File[] listFiles = file.listFiles();
	//				for (File tempFile : listFiles) {
	//					String name = tempFile.getName();
	//					if (name.contains("temp_") && name.endsWith("png")) {
	//						tempFile.delete();
	//					}
	//				}
	//			}
	//		}
	//	}

	public static void main(String[] args) throws IOException {
//		String templatePath = "F:\\upload\\file\\compactTemplate\\c.docx";
//		String excelPath = "F:\\upload\\file\\compactTemplate\\test1.xlsx";
//		InputStream in = new FileInputStream(templatePath);
//		XWPFDocument doc = new XWPFDocument(in);
//
//		Workbook wb = ReadExcelUtil.createWorkbook(excelPath);
//		insertTab("$table",doc,wb);
	}

	public static void insertTab(String key, XWPFDocument doc2,Workbook wb,String outPath) throws IOException {
		List<XWPFParagraph> paragraphList = doc2.getParagraphs(); 
		if (paragraphList != null && paragraphList.size() > 0) {
			for (XWPFParagraph paragraph : paragraphList) {
				List<XWPFRun> runs = paragraph.getRuns(); 
				for (XWPFRun run : runs) {
					String text = run.getText(0); 
					if (text != null) {
						if (text.indexOf(key) >= 0) {
							XmlCursor cursor = paragraph.getCTP().newCursor(); 
							XWPFTable tableOne = doc2.insertNewTbl(cursor);// ---这个是关键  

							// 添加边框 
							CTTblBorders borders = tableOne.getCTTbl().getTblPr().addNewTblBorders(); 
							CTBorder hBorder = borders.addNewInsideH(); 
							hBorder.setVal(STBorder.Enum.forString("single")); 
							hBorder.setSz(new BigInteger("1")); hBorder.setColor("000000"); 
							CTBorder vBorder = borders.addNewInsideV(); 
							vBorder.setVal(STBorder.Enum.forString("single")); 
							vBorder.setSz(new BigInteger("1")); 
							vBorder.setColor("000000"); CTBorder lBorder = borders.addNewLeft(); lBorder.setVal(STBorder.Enum.forString("single")); 
							lBorder.setSz(new BigInteger("1")); lBorder.setColor("000000"); CTBorder rBorder = borders.addNewRight(); 
							rBorder.setVal(STBorder.Enum.forString("single")); rBorder.setSz(new BigInteger("1")); rBorder.setColor("000000"); 
							CTBorder tBorder = borders.addNewTop(); tBorder.setVal(STBorder.Enum.forString("single")); tBorder.setSz(new BigInteger("1")); 
							tBorder.setColor("000000"); CTBorder bBorder = borders.addNewBottom(); bBorder.setVal(STBorder.Enum.forString("single")); 
							bBorder.setSz(new BigInteger("1")); bBorder.setColor("000000"); 

							Sheet sheet = wb.getSheetAt(0);
							int lastRowNum = sheet.getLastRowNum();
							Row row = null;
							for (int j = 0; j < lastRowNum+1; j++) {
								row = sheet.getRow(j);
//								int Width = sheet.getColumnWidth(j);
								XWPFTableRow tableOneRowOne;
								if(j==0){
									tableOneRowOne = tableOne.getRow(j);
								}else{
									tableOneRowOne = tableOne.createRow();
								}
								int lastCellNum = 0;
								if (row != null) {
									lastCellNum = row.getLastCellNum();
								}
								for(int k = 0; k < lastCellNum; k++){
									Cell excelCell = row.getCell(k);
									String cellValue = ReadExcelUtil.getCellValue(excelCell);

									int colSpan = 1;					
									int rowSpan = 1;
									Map<String, String>[] map = ReadExcelUtil.getRowSpanColSpanMap(sheet);
									for (Map.Entry<String, String> entry : map[0].entrySet()) {
										String keys = entry.getKey();
										String pointString = entry.getValue();
										int startRowNum = Integer.valueOf(keys.split(",")[0]);
										int startCellNum = Integer.valueOf(keys.split(",")[1]);
										int endRow = Integer.valueOf(pointString.split(",")[0]);
										int endCol = Integer.valueOf(pointString.split(",")[1]);
										if (j == startRowNum && startCellNum == k) {
											rowSpan = endRow - startRowNum + 1;
											colSpan = endCol - startCellNum + 1;
										}
									}

									XWPFTableCell cell;
									if(j==0){
										if(k == 0){
											cell = tableOneRowOne.getCell(j); 
										}else{
											cell = tableOneRowOne.addNewTableCell(); 
										}
									}else{
										cell = tableOneRowOne.getCell(k); 
									}
									if(colSpan>1){
										mergeCellsHorizontal(tableOne,j,k,k+colSpan-1);
										setCellWidthAndVAlign(cell,String.valueOf(1500*colSpan), STVerticalJc.CENTER,STJc.CENTER);
									}else{
										setCellWidthAndVAlign(cell,String.valueOf(1500), STVerticalJc.CENTER,STJc.CENTER);
									}
									cell.setText(cellValue);
								}
							}
						} 
					} 
				} 
			} 
		} 
		OutputStream out = new FileOutputStream(outPath);
		doc2.write(out);
	}
	
	
	// word跨列合并单元格  
	public static void mergeCellsHorizontal(XWPFTable table, int row, int fromCell, int toCell) {
		for (int cellIndex = fromCell; cellIndex <= toCell; cellIndex++) {
			XWPFTableCell cell = table.getRow(row).getCell(cellIndex);    
			if ( cellIndex == fromCell ) {
				// The first merged cell is set with RESTART merge value    
				cell.getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.RESTART);    
			} else {
				// Cells which join (merge) the first one, are set with CONTINUE    
				cell.getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.CONTINUE);    
			}
		}
	}
	
	//设置单元格样式
	public static void setCellWidthAndVAlign(XWPFTableCell cell, String width, STVerticalJc.Enum typeEnum, STJc.Enum vAlign) {
		CTTc cttc = cell.getCTTc(); 
		CTTcPr cellPr = cttc.isSetTcPr() ? cttc.getTcPr() : cttc.addNewTcPr(); 
		cellPr.addNewVAlign().setVal(typeEnum); 
		cttc.getPList().get(0).addNewPPr().addNewJc().setVal(vAlign); 
		CTTblWidth tblWidth = cellPr.isSetTcW() ? cellPr.getTcW() : cellPr.addNewTcW(); 
		if(!StringUtils.isEmpty(width)){
			tblWidth.setW(new BigInteger(width)); 
			tblWidth.setType(STTblWidth.DXA); 
		} 
	}


//	public static void excel2Word(String excelPath, String wordPath) {
//		Workbook wb = ReadExcelUtil.createWorkbook(excelPath);
//		FileOutputStream out = null;
//		try {
//			// 创建word文档,并设置纸张的大小
//			out = new FileOutputStream(new File(wordPath));
//
//			InputStream is = new FileInputStream(wordPath);
//			XWPFDocument doc2 = new XWPFDocument(is);
//
//			//			insertTab("表格",doc2);
//			//			doc2.createTable(rows, cols);
//			Document document = new Document(PageSize.A4);
//			RtfWriter2.getInstance(document, out);
//
//			document.open();
//
//			int numberOfSheets = wb.getNumberOfSheets();
//			List<Position> arrayList = new ArrayList<>();
//			for (int i = 0; i < numberOfSheets; i++) {
//				Sheet sheet = wb.getSheetAt(i);
//				arrayList.clear();
//				Map<String, String>[] map = ReadExcelUtil.getRowSpanColSpanMap(sheet);
//				int lastRowNum = sheet.getLastRowNum();
//				Row row = null;
//				for (int j = 0; j < lastRowNum; j++) {
//					row = sheet.getRow(j);
//					if (row != null) {
//						break;
//					}
//				}
//				short lastCellNum = 10;
//				if (row != null) {
//					lastCellNum = row.getLastCellNum();
//				}
//				Table table = new Table(lastCellNum, lastRowNum);
//				table.setBorderWidth(1);
//				table.setBorderColor(Color.BLACK);
//				table.setPadding(0);
//				table.setSpacing(0);
//
//				for (int rowNum = sheet.getFirstRowNum(); rowNum <= lastRowNum; rowNum++) {
//					Row excelRow = sheet.getRow(rowNum);
//					if (excelRow == null) {
//						continue;
//					}
//					lastCellNum = excelRow.getLastCellNum();
//					int rowSpan = 1;
//					boolean isFirst = true;
//					short firstCellNum = excelRow.getFirstCellNum();
//					if (firstCellNum == -1) {
//						break;
//					}
//					for (int cellNum = firstCellNum; cellNum <= lastCellNum; cellNum++) {
//						Cell excelCell = excelRow.getCell(cellNum);
//						if (excelCell == null) {
//							continue;
//						}
//						CellStyle cellStyle = excelCell.getCellStyle();
//
//						short foregroundColorIndex = cellStyle.getFillForegroundColor();
//
//						short fontIndex = cellStyle.getFontIndex();
//						int colorIndex = wb.getFontAt(fontIndex).getColor();
//						String cellValue = ReadExcelUtil.getCellValue(excelCell);
//
//						int colSpan = 1;
//						for (Map.Entry<String, String> entry : map[0].entrySet()) {
//
//							String key = entry.getKey();
//							String pointString = entry.getValue();
//							int startRowNum = Integer.valueOf(key.split(",")[0]);
//							int startCellNum = Integer.valueOf(key.split(",")[1]);
//							int endRow = Integer.valueOf(pointString.split(",")[0]);
//							int endCol = Integer.valueOf(pointString.split(",")[1]);
//							if (rowNum == startRowNum && startCellNum == cellNum) {
//								rowSpan = endRow - startRowNum + 1;
//								colSpan = endCol - startCellNum + 1;
//							}
//						}
//
//						if (isCovered(arrayList, rowNum, cellNum)) {
//							continue;
//						}
//						Image image = null;
//
//						Map<ClientAnchor, PictureData> pictureDate = ReadExcelUtil.getPictures(sheet);
//						if (pictureDate != null) {
//							for (Map.Entry<ClientAnchor, PictureData> entry : pictureDate.entrySet()) {
//								if (entry.getKey().getRow1() == rowNum && entry.getKey().getCol1() == cellNum) {
//									PictureData pictureData = entry.getValue();
//									byte[] data = pictureData.getData();
//									String fileName = UUID.randomUUID().toString();
//									String name = "d:\\temp_" + fileName + ".png";
//									writeFile(data, name);
//
//									image = Image.getInstance(name);
//									int[] imageWH = ImageUtil.getImageWH(name, 280);
//									image.scaleAbsolute(imageWH[0], imageWH[1]);
//									break;
//								}
//							}
//						}
//						com.lowagie.text.Cell cell = null;
//						if (image != null) {
//							image.setAlignment(Image.ALIGN_CENTER);// 设置图片显示位置
//							cell = new com.lowagie.text.Cell(image);// 单元格
//						} else {
//							cellValue = removeEnter(cellValue);
//							Color color = createColorByIndex(colorIndex);
//							Font font = createFont(color);
//							cell = new com.lowagie.text.Cell(new Paragraph(cellValue, font));// 单元格
//
//						}
//						cell.setColspan(colSpan);
//						cell.setRowspan(rowSpan);
//
//						Color createColorByIndex = createColorByIndex(foregroundColorIndex);
//						cell.setBackgroundColor(createColorByIndex);
//						// 其中1为居中对齐，2为右对齐，3为左对齐
//						cell.setVerticalAlignment(1);
//						short alignment = cellStyle.getAlignment();
//						short borderBottom = cellStyle.getBorderBottom();
//						short borderLeft = cellStyle.getBorderLeft();
//						short borderRight = cellStyle.getBorderRight();
//						short borderTop = cellStyle.getBorderTop();
//						if (borderBottom == 0 && borderLeft == 0 && borderRight == 0 && borderTop == 0) {
//							cell.setBorder(0);
//						}
//						cell.setHorizontalAlignment(alignment - 1);
//						cell.setWidth(100);
//
//						// 同一行中只添加一次空行，避免读到下一列时重复添加空行
//						if (isFirst) {
//
//							int nextPage = isNextPage(rowNum, rowSpan);
//							if (nextPage != 0) {
//								com.lowagie.text.Cell cell2 = new com.lowagie.text.Cell("");
//								cell2.setColspan(lastCellNum);
//								cell2.setRowspan(nextPage);
//								cell2.setBorder(1);
//								table.addCell(cell2);
//								isFirst = false;
//							}
//						}
//						table.addCell(cell);
//						if (rowSpan * colSpan != 1) {
//							arrayList.add(new Position(rowNum, rowNum + rowSpan - 1, cellNum, cellNum + colSpan - 1));
//						}
//					}
//				}
//				document.add(table);
//				if (i != numberOfSheets - 1) {
//					document.newPage();
//				}
//			}
//			document.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			if (out != null) {
//				try {
//					out.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//		}
//	}

	/**
	 * 通过Excel表格中颜色index获取AWT中Color
	 * 
	 * @param colorIndex
	 * @return
	 */
	private static Color createColorByIndex(int colorIndex) {
		Map<Integer, HSSFColor> indexHash = HSSFColor.getIndexHash();
		HSSFColor hssfColor = indexHash.get(colorIndex);
		if (hssfColor != null) {
			short[] triplet = hssfColor.getTriplet();
			Color color = new Color(triplet[0], triplet[1], triplet[2]);
			return color;
		}
		return null;

	}

	/**
	 * 去除字符串中的\r\n
	 * \r\n在word中会显示成问号
	 * 
	 * @param cellValue
	 * @return
	 */
	private static String removeEnter(String cellValue) {
		if (cellValue.contains("\r\n")) {
			StringBuilder builder = new StringBuilder();
			char[] charArray = cellValue.toCharArray();
			for (char c : charArray) {
				// 去掉换行符
				if (Integer.valueOf(c) != 13) {
					builder.append(c);
				}
			}
			return builder.toString();
		} else {
			return cellValue;
		}
	}

//	/**
//	 * 根据颜色创建字体
//	 * 
//	 * @param color
//	 * @return
//	 * @throws DocumentException
//	 * @throws IOException
//	 */
//	private static Font createFont(Color color) throws DocumentException, IOException {
//		BaseFont bfSun = BaseFont.createFont("c:\\WINDOWS\\fonts\\SIMSUN.TTC,1", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
//
//		Font font = new Font(bfSun, 11);
//		font.setColor(color);
//		return font;
//	}

	private static void writeFile(byte[] data, String name) throws FileNotFoundException, IOException {
		File file = new File(name);
		FileOutputStream picOut = new FileOutputStream(file);
		picOut.write(data);
		picOut.close();
	}

	/**
	 * 判断是否需要添加空行
	 * 
	 * @param startRow
	 *            当前行号
	 * @param occupyRow
	 *            当前内容所占用的行数
	 * @return 需要添加空行的行数
	 */
	private static int isNextPage(int startRow, int occupyRow) {
		// 每页所占行数，word每页可容纳Excel 52行，默认高度
		int count = 52;
		int b = startRow / count;
		int endRow = startRow + occupyRow;
		// 当前页最后一行行号
		int i = count * (b + 1);
		if (endRow > i) {
			return i - startRow;
		}
		return 0;
	}

	private static boolean isCovered(List<Position> arrayList, int rowNum, int cellNum) {
		for (Position content : arrayList) {
			if (rowNum >= content.getStartRow() && rowNum <= content.getEndRow() && cellNum >= content.getStartCol()
					&& cellNum <= content.getEndCol()) {
				return true;
			}
		}
		return false;
	}
}
