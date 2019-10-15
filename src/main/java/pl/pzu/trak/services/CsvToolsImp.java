package pl.pzu.trak.services;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.springframework.stereotype.Service;

@Service
public class CsvToolsImp implements CsvTools
{
	@Override
	public String LoadFileCsv(String fileName)
	{
		try
		{
			CsvReader file = new CsvReader(fileName);
			file.readHeaders();
			while (file.readRecord())
			{
				String productID = file.get("ProductID");
				String productName = file.get("ProductName");
				String supplierID = file.get("SupplierID");
				String categoryID = file.get("CategoryID");
				String quantityPerUnit = file.get("QuantityPerUnit");
				String unitPrice = file.get("UnitPrice");
				String unitsInStock = file.get("UnitsInStock");
				String unitsOnOrder = file.get("UnitsOnOrder");
				String reorderLevel = file.get("ReorderLevel");
				String discontinued = file.get("Discontinued");

				// perform program logic here
				System.out.println(productID + ":" + productName);
			}

			file.close();

		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		}

		return "komunikat";
	}
	
	
}
