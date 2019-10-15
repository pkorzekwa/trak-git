package pl.pzu.trak.services;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.springframework.stereotype.Service;

import pl.pzu.trak.domain.BulkCsv;

@Service
public class CsvToolsServiceImp implements CsvToolsService
{
	@Override
	public String LoadFileCsv(String fileName)
	{
		
//		StringBuilder response = new StringBuilder();
		
		try
		{
			CsvReader file = new CsvReader(fileName,';');
			file.readHeaders();
			BulkCsv bulkCsv = new BulkCsv();
	
			while (file.readRecord())
			{
				bulkCsv.setK01(file.get("Numer_zadania"));
				bulkCsv.setK02(file.get("Utworzyl_login"));
				bulkCsv.setK03(file.get("Utworzyl"));
				bulkCsv.setK04(file.get("Utworzyl Grupa 4"));
				bulkCsv.setK05(file.get("Kod zakladowy"));
				bulkCsv.setK06(file.get("Przypisane do login"));
				bulkCsv.setK07(file.get("Przypisane do"));
				bulkCsv.setK08(file.get("Przypisane do"));
				bulkCsv.setK09(file.get("Nazwa zadania"));

				// perform program logic here
				//response.append(bulkInsertBazaPc.toString()).append("<br>");
				System.out.println(bulkCsv.toString());
			}

			file.close();

		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		}

		return "<br>ok<br>";
	}
	
	
}
