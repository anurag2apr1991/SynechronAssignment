import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;


public class DataFromExcel {

	public DataFromExcel() {
		//readExcelFile(CompareDataMain.FILE_PATH);
	}

	public static void main(String[] args) {

	}
	
	public  List<InputData> readExcelFile(String file) {
		boolean firstLine =true;
		Row nextRow = null;
		List<InputData> lstInputData = new LinkedList<InputData>();
		try(Workbook wb = new XSSFWorkbook(new File(file))){
			
			Sheet firstSheet = wb.getSheetAt(0);
			Iterator<Row> itr = firstSheet.rowIterator();
			int noOfColumns = firstSheet.getRow(0).getPhysicalNumberOfCells();
			System.out.println("No of columns "+noOfColumns);
			while(itr.hasNext()) {
				nextRow = itr.next();
				if(firstLine) {
					firstLine = false;
					continue;
				}
				InputData inputData = new InputData();
				inputData.setId(String.valueOf(nextRow.getCell(0)).replaceAll("[0]+$", "").replaceAll("(\\.)(?!.*?[1-9]+)", "").trim());
				inputData.setName(String.valueOf(nextRow.getCell(1)).trim());
				inputData.setPinCode(String.valueOf(nextRow.getCell(2)).replaceAll("[0]+$", "").replaceAll("(\\.)(?!.*?[1-9]+)", "").trim());
				inputData.setGender(String.valueOf(nextRow.getCell(3)).trim());
				inputData.setCity(String.valueOf(nextRow.getCell(4)).trim());
				lstInputData.add(inputData);
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return lstInputData;
	}
	
}
