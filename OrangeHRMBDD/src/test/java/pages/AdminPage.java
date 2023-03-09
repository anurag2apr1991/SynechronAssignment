package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

import static Utils.Utilities.randomIntGenerator;

public class AdminPage extends BasePage {
	
	protected WebDriverWait wait;
	protected String xpath;
	private String temp;
	private List<String> headers;
	private LinkedHashMap<String, String> deletedRecord;
	private List<LinkedHashMap<String, String>> allTableData;
	private WebElement cell;
	private String cellData;
	
	@FindBy (xpath="//div//label[contains(.,'Current')]//..//..//input")
	protected WebElement currentPasswordInput;
	
	@FindBy (xpath="//div//label[not(contains(.,'Confirm'))]//..//..//input[@autocomplete='off'][contains(@type,'password')]")
	protected WebElement passwordInput;
	
	@FindBy (xpath="//div//label[contains(.,'Confirm')]//..//..//input")
	protected WebElement confirmPasswordInput;
	
	@FindBy (xpath="//button[contains(.,'Cancel')]")
	protected WebElement cancelBtn;
	
	@FindBy (xpath="//button[contains(.,'Save')]")
	protected WebElement saveBtn;
	
	@FindBy (xpath="//div//p[contains(.,'Success')][contains(@class,'title')]")
	protected WebElement successMsg;
	
	@FindBy (xpath="//div//p[contains(.,'Error')][contains(@class,'title')]")
	protected WebElement errorMsg;
	
	@FindBy (xpath = "//div//label[contains(.,'Username')]//..//..//input")
	protected WebElement usernameInput;
	
	@FindBy (xpath = "//div[contains(@class,'text-input')][contains(.,'Select')]")
	protected WebElement userRoleSelect;
	
	@FindBy (xpath = "//div[contains(@class,'text-input')][contains(.,'Disabled')]")
	protected WebElement employeeName;
	
	@FindBy (xpath = "//div[@role='listbox']//div")
	protected WebElement userStatusSelect;
	
	@FindBy (xpath = "//button[contains(.,'Reset')]")
	private WebElement resetBtn;
	
	@FindBy (xpath = "//button[contains(.,'Search')]")
	private WebElement searchBtn;
	
	@FindBy (xpath = "//button[contains(.,'Delete')]")
	private WebElement yesDeleteBtn;
	
	@FindBy (xpath = "//button[contains(.,'Cancel')]")
	private WebElement noCancelDeleteBtn;
	
	@FindBy (xpath = "//button[contains(.,'Add')]")
	private WebElement addUserBtn;
	
	@FindBy (xpath = "//span[contains(.,'Records Found')]")
	private WebElement numberOfRecords;
	
	@FindBy (xpath = "//div//button[contains(@class,'action-space')]//i[contains(@class,'trash')]")
	private List<WebElement> deleteBtns;
	
	@FindBy (xpath = "//div//button[contains(@class,'action-space')]//i[contains(@class,'pencil')]")
	private List<WebElement> editBtns;
	
	@FindBy (xpath = "//div[@class='oxd-table-body']/div")
	private List<WebElement> rows;
	
	@FindBy (xpath = "(//div[@class='oxd-table-body']/div)[1]//div//div[@role='cell']")
	private List<WebElement> cols;
	
	@FindBy (xpath = "//div[contains(@class,'table-th')]")
	private List<WebElement> headersList;
	
	public AdminPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	public void clickAddUserBtn() {
		addUserBtn.click();
	}
	public void checkCounterBecomesLessByOne(String recordsBefore) {
		wait = new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.visibilityOf(numberOfRecords));
		System.out.println(recordsBefore);
		System.out.println(counterRecords());
		Assert.assertTrue(Integer.parseInt(recordsBefore)-1 == Integer.parseInt(counterRecords()));
	}

	public String pressDeleteBtnRandomRecord() {
		temp = counterRecords();
		int row = randomIntGenerator(2, (rows.size()));
		deleteBtns.get(row).click();
		return temp;
	}
	
	public void confirmDeleteRecord() {
		wait = new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.visibilityOf(yesDeleteBtn));
		yesDeleteBtn.click();
	}
	
	public LinkedHashMap<String, String> pressDeleteBtnParticularRecord() {
		int row = randomIntGenerator(2, (rows.size()));
		headers = getHeaders();
		deletedRecord = new LinkedHashMap<>();
		for (int j = 1; j <= headers.size(); j++) {
			xpath = "((//div[@class='oxd-table-body']/div)["+row+"]//div//div[contains(@style,'flex')]//div[not(contains(@class,'oxd-table-cell-actions'))])["+j+"]";
			cell = driver.findElement(By.xpath(xpath));
	        cellData = cell.getText().trim();
			deletedRecord.put(headers.get(j-1), cellData);
		}
		deleteBtns.get(row-1).click();
		return deletedRecord;
	}
	
	public void checkRecordsNotPresenetInRow(List<LinkedHashMap<String, String>> records, LinkedHashMap<String, String> deletedRecord) {
		System.out.println(records.toString());
		System.out.println(deletedRecord.toString());
		Assert.assertTrue(!records.contains(deletedRecord));
	}
	public List<LinkedHashMap<String, String>> getUserRecordsData() {
		allTableData = new LinkedList<LinkedHashMap<String, String>>();
		LinkedHashMap<String, String> eachRowData;
		headers = getHeaders();
		
		for (int i = 1; i <= rows.size(); i++) {
			eachRowData = new LinkedHashMap<>();
			for (int j = 1; j <= headers.size(); j++) {
				xpath = "((//div[@class='oxd-table-body']/div)["+i+"]//div//div[contains(@style,'flex')]//div[not(contains(@class,'oxd-table-cell-actions'))])["+j+"]";
				cell = driver.findElement(By.xpath(xpath));
		        cellData = cell.getText().trim();
		        eachRowData.put(headers.get(j-1), cellData);
			}
			allTableData.add(eachRowData);
		}
		return allTableData;
	}

	private String counterRecords() {
		System.out.println("Number of records from the website counter: " + numberOfRecords.getText().replaceAll("[^\\d.]", ""));
		return numberOfRecords.getText().replaceAll("[^\\d.]", "");
	}

	private List<String> getHeaders(){
		headers = new ArrayList<String>();
		for (WebElement header : headersList) {
			if (header.getText().isBlank() || header.getText().equalsIgnoreCase("actions"))
				continue;
			headers.add(header.getText());
		}
		return headers;
	}
}
