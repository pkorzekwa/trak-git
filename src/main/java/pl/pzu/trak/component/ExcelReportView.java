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
			 header.createCell(0).setCellValue("ID");
			 header.createCell(1).setCellValue("Nazwisko");
			 header.createCell(2).setCellValue("Imię");
			 header.createCell(3).setCellValue("Zespół");
			 header.createCell(4).setCellValue("Stanowisko");
			 header.createCell(5).setCellValue("Umowy pracownika");
			 header.createCell(6).setCellValue("Status pracownika");
		  
		 int rowNum = 1;
		 for(Employee emp:employeeList)
		 {
			 Row row = sheet.createRow(rowNum++);
				 row.createCell(0).setCellValue(emp.getId_employee());
				 row.createCell(1).setCellValue(emp.getLast_name());
				 row.createCell(2).setCellValue(emp.getFirst_name());
				 row.createCell(3).setCellValue(emp.getTeam());
				 row.createCell(4).setCellValue(emp.getWorkplace());
				 
				 String nameString = "";
				 
				 for(EmployeeContract contrakt:emp.getEmployeeContract())
				 {
					 if(!nameString.equals(""))nameString+="\n";
					 nameString+=contrakt.getEmployeeCompanyDictionary().getName();
					 
				 }
				 CellStyle cs = workbook.createCellStyle();
		            cs.setWrapText(true);

				 Cell cell=row.createCell(5);
				 cell.setCellStyle(cs);
				 cell.setCellValue(nameString);

				 row.createCell(6).setCellValue(emp.isEmployee_status());
		 }
	 }
}
