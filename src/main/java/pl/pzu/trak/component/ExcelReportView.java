package pl.pzu.trak.component;

import java.util.List;
import java.util.Map;
 
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import org.springframework.web.servlet.view.document.AbstractXlsxView;

import pl.pzu.trak.domain.Employee;

import pl.pzu.trak.domain.EmployeeContract;
import pl.pzu.trak.domain.EmployeeSystems;
 

 
 
public class ExcelReportView extends AbstractXlsxView
{
 
	 @Override
	 protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
	 HttpServletResponse response) throws Exception 
	 {
	  
		 response.setHeader("Content-Disposition", "attachment;filename=\"employeeList.xlsx\"");
		 List<Employee> employeeList = (List<Employee>) model.get("employeeList");

		 Sheet sheet = workbook.createSheet("Employee Sheet");
		 Row header = sheet.createRow(0);
			 header.createCell(0).setCellValue("Lp.");
			 header.createCell(1).setCellValue("Nazwisko");
			 header.createCell(2).setCellValue("Imię");
			 header.createCell(3).setCellValue("Zespół");
			 header.createCell(4).setCellValue("Stanowisko");
			 header.createCell(5).setCellValue("Umowy pracownika");
			 header.createCell(6).setCellValue("Systemy pracownika");
			 header.createCell(7).setCellValue("Status pracownika");
		  
		 int rowNum = 1;
		 int lp = 1;
		 for(Employee emp:employeeList)
		 {
			 Row row = sheet.createRow(rowNum++);
				 row.createCell(0).setCellValue(lp++);
				 row.createCell(1).setCellValue(emp.getLast_name());
				 row.createCell(2).setCellValue(emp.getFirst_name());
				 row.createCell(3).setCellValue(emp.getTeam());
				 row.createCell(4).setCellValue(emp.getWorkplace());
				 
				 String nameContractString = "";
	
				 for(EmployeeContract contrakt:emp.getEmployeeContract())
				 {
					 if(!nameContractString.equals(""))
					 {
						 nameContractString+="\n";
					 }
						 
					 nameContractString+=contrakt.getEmployeeCompanyDictionary().getName();
					 
				 }
				 
				 
				 CellStyle cs = workbook.createCellStyle();
		            cs.setWrapText(true);

				 Cell cellContract=row.createCell(5);
				 cellContract.setCellStyle(cs);
				 cellContract.setCellValue(nameContractString);
				 
				 String nameSystemString = "";
				 
				 for(EmployeeSystems system:emp.getEmployeeSystems())
				 {
					 if(!nameSystemString.equals(""))
					 {
						 nameSystemString+="\n";
					 }
					 
					 nameSystemString+=system.getEmployeeSystemsDictionary().getName();
				 }
				 
				 Cell cellSystem = row.createCell(6);
				 cellSystem.setCellStyle(cs);
				 cellSystem.setCellValue(nameSystemString);

				 row.createCell(7).setCellValue(emp.isEmployee_status());
		 }
	 }
}
