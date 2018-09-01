package decisiontree.model;

import java.io.File;
import java.io.FileWriter;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class FileIO {
	public FileIO() {

	}

	public void PrintDoubleArray(double[][] input, String filename, ProjectInfo pInfo) {
		try {
			String rootname = "C:\\Users\\multiangle\\Desktop\\";
			String path = rootname + filename + ".xls";
			File file = new File(path);
			WritableSheet sheet;
			WritableWorkbook book;
			if (file.exists()) {
				Workbook wb = Workbook.getWorkbook(file);
				book = Workbook.createWorkbook(file, wb);
				int sheetnum = book.getNumberOfSheets();
				sheet = book.createSheet("第" + sheetnum + "页", sheetnum);
				System.out.println("正在第" + sheetnum + "页打印double数组");
			} else {
				book = Workbook.createWorkbook(new File(path));
				sheet = book.createSheet("第0页", 0);
				System.out.println("正在第0页打印double数组");
			}
			// System.out.println("已获取到需要的表单");

			String[] name = new String[3 + pInfo.AttrNum];
			name[0] = "SetNum";
			name[1] = "Type";
			name[2] = "tempType";
			for (int i = 0; i < pInfo.AttrNum; i++) {
				name[3 + i] = pInfo.AttrDescription[i];
			}
			for (int i = 0; i < 3 + pInfo.AttrNum; i++) {
				Label temp = new Label(i, 0, name[i]);
				sheet.addCell(temp);
			}

			int len = input.length;
			int row = 1;
			for (int i = 0; i < len; i++) {
				for (int j = 0; j < 3 + pInfo.AttrNum; j++) {
					Label temp = new Label(j, row + i, String.valueOf(input[i][j]));
					sheet.addCell(temp);
				}
			}
			book.write();
			book.close();
		} catch (Exception e) {
			System.out.println(e);
			System.out.println("ERROR:ExcelPrint");
		}
	}

	public void PrintDoubleArray(double[][] input, String filename, String description, ProjectInfo pInfo) {
		try {
			String rootname = "C:\\Users\\multiangle\\Desktop\\";
			String path = rootname + filename + ".xls";
			File file = new File(path);
			WritableSheet sheet;
			WritableWorkbook book;
			if (file.exists()) {
				Workbook wb = Workbook.getWorkbook(file);
				book = Workbook.createWorkbook(file, wb);
				int sheetnum = book.getNumberOfSheets();
				sheet = book.createSheet("第" + sheetnum + "页", sheetnum);
				System.out.println("正在第" + sheetnum + "页打印double数组");
			} else {
				book = Workbook.createWorkbook(new File(path));
				sheet = book.createSheet("第0页", 0);
				System.out.println("正在第0页打印double数组");
			}
			// System.out.println("已获取到需要的表单");

			Label descrip = new Label(0, 0, description);
			sheet.addCell(descrip);
			String[] name = new String[3 + pInfo.AttrNum];
			name[0] = "SetNum";
			name[1] = "Type";
			name[2] = "tempType";
			for (int i = 0; i < pInfo.AttrNum; i++) {
				name[3 + i] = pInfo.AttrDescription[i];
			}
			for (int i = 0; i < 3 + pInfo.AttrNum; i++) {
				Label temp = new Label(i, 1, name[i]);
				sheet.addCell(temp);
			}

			int len = input.length;
			int row = 2;
			for (int i = 0; i < len; i++) {
				for (int j = 0; j < 3 + pInfo.AttrNum; j++) {
					Label temp = new Label(j, row + i, String.valueOf(input[i][j]));
					sheet.addCell(temp);
				}
			}
			book.write();
			book.close();
		} catch (Exception e) {
			System.out.println(e);
			System.out.println("ERROR:ExcelPrint");
		}
	}

	public double[][] getArray(String filename, int sheetnum, int[] nodea, int[] nodeb) {
		int left = nodea[0];
		int top = nodea[1];
		int right = nodeb[0];
		int bottom = nodeb[1];
		int len = bottom - top + 1;
		int width = right - left + 1;
		double[][] rs = new double[len][width];

		String root = "C:\\Users\\Administrator\\Desktop\\"; // 基本目录为桌面
		String path = root + filename + ".xls";
		File file = new File(path);
		if (!file.exists()) {
			System.out.println("ERROR:ExcelIO_getArray_File not exists");
			return null;
		} else {
			try {
				Workbook book = Workbook.getWorkbook(file);
				Sheet sheet = book.getSheet(sheetnum);
				for (int i = 0; i < len; i++) {
					for (int j = 0; j < width; j++) {
						Cell cell = sheet.getCell(j, i);
						double temp = Double.parseDouble(cell.getContents());
						rs[i][j] = temp;
					}
				}
				return rs;
			} catch (Exception e) {
				System.out.println("ERROR:ExcelIO_getArray");
				System.out.println(e);
				return null;
			}
		}
	}

	public static void PrintTxtln(String filename, String line) {
		try {
			String root = "C:\\Users\\Administrator\\Desktop\\"; // 基本目录为桌面
			String path = root + filename + ".txt";
			FileWriter out = new FileWriter(path, true);
			out.write(line + "\r\n");
			out.close();
		} catch (Exception e) {
			System.out.println(e);
		}

	}
}
