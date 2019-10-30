package pl.pzu.trak.component;

import java.util.List;
import java.util.Map;
 
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import pl.pzu.trak.domain.Employee;
 

 
 
public class ExcelReportView extends AbstractXlsView
{
 
	 @Override
	 protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
	 HttpServletResponse response) throws Exception 
	 {
	  
		 response.setHeader("Content-Disposition", "attachment;filename=\"employeeList.xls\"");
		 List<Employee> employeeList = (List<Employee>) model.get("employeeList");
		 Sheet sheet = workbook.createSheet("Employee Sheet");
		 Row header = sheet.createRow(0);
			 header.createCell(0).setCellValue("ID");
			 header.createCell(1).setCellValue("Nazwisko");
			 header.createCell(2).setCellValue("Imię");
			 header.createCell(3).setCellValue("Zespół");
			 header.createCell(4).setCellValue("Stanowisko");
			 header.createCell(5).setCellValue("Status pracownika");
		  
		 int rowNum = 1;
		 for(Employee emp:employeeList)
		 {
			 Row row = sheet.createRow(rowNum++);
				 row.createCell(0).setCellValue(emp.getId_employee());
				 row.createCell(1).setCellValue(emp.getLast_name());
				 row.createCell(2).setCellValue(emp.getFirst_name());
				 row.createCell(3).setCellValue(emp.getTeam());
				 row.createCell(4).setCellValue(emp.getWorkplace());
				 row.createCell(5).setCellValue(emp.isEmployee_status());
		 }
	 }
}
