import java.io.FileWriter;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class CompareDataMain {
	public static final String FILE_PATH = "Files/data.xlsx";
	public static List<String> list = new ArrayList<String>();
	public static void main(String[] args) {
		DataFromExcel de = new DataFromExcel();
		List<InputData> readExcelFile = de.readExcelFile(FILE_PATH);
		List<InputData> matchList = new LinkedList<>(readExcelFile);
		List<InputData> diffList = new LinkedList<>(readExcelFile);
		DataFromDB db = new DataFromDB();
		List<InputData> dataFromDb = db.dataFromDb();
		
		StringBuilder sb = new StringBuilder();
		matchList.retainAll(dataFromDb); // Common elements
		System.out.println("Matching Data are :: "+matchList);
		sb.append("Matching Data are :: "+matchList);
		diffList.removeAll(dataFromDb); // Difference elements
		System.out.println("Unmatched Data are :: "+diffList);
		System.out.println("Please check file for more details");
		sb.append("\n");
		sb.append("Unmatched Data are :: "+diffList);
		for(InputData d : diffList) {
			for(InputData d1 : dataFromDb) {
				if(d.getId().equalsIgnoreCase(d1.getId())) {
					if(!d.getName().equalsIgnoreCase(d1.getName())) {
						list.add("\n Name failed  Excel data : "+d.getName()+" DB Data : "+d1.getName());
						break;
					}
					if(!d.getPinCode().equalsIgnoreCase(d1.getPinCode())) {
						list.add("\n Pin code failed Excel data : "+d.getPinCode()+" DB Data : "+d1.getPinCode());
						break;
					}
					if(!d.getGender().equalsIgnoreCase(d1.getGender())) {
						list.add("\n Gender failed  Excel data : "+d.getGender()+" DB Data : "+d1.getGender());
						break;
					}
					if(!d.getCity().equalsIgnoreCase(d1.getCity())) {
						list.add("\n City failed  Excel data : "+d.getCity()+" DB Data : "+d1.getCity());
						break;
					}
				}
				
			}
		}
		sb.append("\n");
		sb.append("Reason for unmatched data : ");
		list.forEach(e -> sb.append(e));
		Format formatter = new SimpleDateFormat("MM-dd-yyyy_hh-mm-ss");
		String s = formatter.format(new Date());
		try(FileWriter fw = new FileWriter("Files/compareOutput"+s+".txt")){
			fw.write(sb.toString());
		}catch(Exception e) {
			e.printStackTrace();
		}

	}

}
