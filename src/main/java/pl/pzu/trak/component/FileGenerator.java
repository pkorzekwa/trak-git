//obiekt zawieraj�cy metody tworzenia plik�w
package pl.pzu.trak.component;

import  java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
//import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import org.apache.poi.ss.usermodel.CellStyle;

public class FileGenerator {
	
	public class MyDB {
	       // deklaracje pól wykorzystywanych przez dziedziczące obiekty raportów
	       public String[][] resultRows;
	       public int rowsCount;
	       public String[] resultCols;
	       public int colsCount;
	       public String[] maskedCols;
	       public String reportName;
	       public String firstRow;

	}
	public static String timestamp() {
        String hash = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        // String hash = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new
        // Date());
        return hash;
	}
	//pozosta�o�� po serwlecie. prawdopodobnie mo�na usun��
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	}
	//metoda tworzy plik excel na podstwawie obiektu typu MyDB zawieraj�cego dane z zapytania sql
	public static String excel(HttpServletRequest request, MyDB report){
		System.out.println("report.name :"+report.reportName);
		System.out.println("report.rowsCount :"+report.rowsCount);
		System.out.println("report.colsCount :"+report.colsCount);
		System.out.println("report.firstRow :"+report.firstRow);
		//zwraca null je�eli obiekt z danymi jest pusty
		if(report.rowsCount==0){return null;}
		//pobiera obiekt sesji z requesta w parametrze wej�ciowym
		HttpSession session;
		String ulogin="trak";
		if(request!=null){
			session= request.getSession();
			if (session!=null){ulogin=(String) session.getAttribute("userid");}
		}
		//pobiera login u�ytkownika, kt�ry generuje plik z obiektu jego sesji
		
		
		//tworzy now� instancj� kalendarza
		//Calendar calendar = Calendar.getInstance();
		//�aduje do kalendarza aktualn� dat� i godzin�
		//java.util.Date now = calendar.getTime();
		//tworzy stempel daty z daty i godziny
		//java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(now.getTime());
		//deklaruje �cie�k� webow� do folderu - repozytorium z wygenerowanymi plikami
		File fld = new File(request.getServletContext().getRealPath("")+"\\repository");
		//pobiera �cie�k� fizyczn� do folderu
		String path = fld.getAbsolutePath();
					System.out.println("path: " + path);
		
		String filefolder = fld.getAbsolutePath();
		//tworzy nazw� dla pliku zawieraj�c� login i hash stempla czasu
		//String filename = ulogin+"_"+report.reportName+"_"+currentTimestamp.hashCode()+".xlsx";
		String filename = ulogin+"_"+report.reportName+"_"+timestamp()+".xlsx";
		filename=filename.replace(" ", "_").replace("-", "");
		//tworzy fizyczn� �cie�k� dost�pu do pliku z fizycznej lokalizacji repozytorium i utworzonej nazwy
		String fileloc = filefolder+"\\"+filename;
		
		//p�tla listuj�ca pliki w repozytorium
		for(File f: fld.listFiles())
			//sprawdza czy jaki� plik zawiera ju� login osoby generuj�cej nowy plik
		    if(f.getName().startsWith(ulogin))
		    	//je�eli tak to usuwa stary plik
		        f.delete();
		
		try {
			//zmienna zawieraj�ca maksymaln� liczb� wierszy na arkuszu excela
            int maxNumberRowOfSheets = 1048575; //1048575 dla xlsx,65535 dla xls liczba wierszy w skoroszycie-nag��wek. (J. Kowaczek)
            //zmienna zawieraj�ca wyliczon� liczb� arkuszy do wygenerowania (liczba wierszy raportu / max wierszy na arkusz)
            int NumberOfSheets = report.rowsCount/maxNumberRowOfSheets; //liczba skoroszyt�w (J. Kowaczek)
            //tworzy nowy pusty skoroszyt excela
            XSSFWorkbook workbook = new XSSFWorkbook();
            //deklaruje nowy arkusz skoroszytu
            XSSFSheet sheet;
            XSSFCellStyle cs = workbook.createCellStyle();
            cs.setDataFormat((short)0x31);
            //CellStyle cellStyle;
            //p�tla przez liczb� arkuszy do utworzenia
          for (int ii=0;ii<=NumberOfSheets;ii++){
        	  //tworzy nowy arkusz z kolejnym numerem
            sheet = workbook.createSheet("Strona "+(ii+1)+" z "+(NumberOfSheets+1)); 
            //zdublowana instrukcja - pobiera utworzony arkusz
            sheet=workbook.getSheetAt(ii);
            //tworzy pierwszy wiersz w arkuszu dla nag��wk�w
            XSSFRow rowhead = sheet.createRow((int)0);
            //uzupe�nia wiersz nag��wkami z obiektu raportu
            //for(int i=0;i<=report.colsCount-1;i++){
            //    rowhead.createCell(i).setCellValue(report.maskedCols[i]);
            //}
           
          //deklaracja i przypisanie zmiennej z numerem pierwszego wiersza z danymi po nag��wkach
    	    int dataRow=1;//pierwszy wiersz danych-drugi w xls (J. Kowaczek)
    	    
    	    // dodanie wiersza nad nag��wkami raportu o warto�ci r�wnej firstRow typu String 
    	    // (funkcjonalno�� dedykowana do raportu rp66)
            if(report.firstRow!=null && !report.firstRow.equals("")){
        		rowhead = sheet.createRow((int)0);
        		rowhead.createCell(0).setCellValue(report.firstRow);
        		dataRow++;
        	}
            //rowhead = sheet.createRow((int)0);
            rowhead = sheet.createRow(dataRow-1);
            
        	for(int i=0;i<=report.colsCount-1;i++){
            rowhead.createCell(i).setCellValue(report.maskedCols[i]);
            }
            
    	    //zmienna z wyliczonym numerem ostatniego wiersza w arkuszu
            int lastRowOfSheets=(ii+1)*maxNumberRowOfSheets;//ostatni wiesz danych na arkusz (J. Kowaczek)
            //sprawdza czy raport zawiera mniej wierszy ni� maksimum na arkusz i je�eli tak to tak� warto�� przypisuje
            //zmiennej z numerem ostatniego wiersza
            if (lastRowOfSheets>report.rowsCount){lastRowOfSheets=report.rowsCount;}//ostatni wiersz w raporcie (J. Kowaczek)
            //p�tla przez wiersze raportu
    	    for(int j=ii*maxNumberRowOfSheets;j<lastRowOfSheets;j++){
            	try{
            		//tworzy wiersz arkusza
            	XSSFRow row = sheet.createRow((Integer)dataRow++);
            	//p�tla wype�niaj�ca kom�rki wiersza
            	for(int k=0;k<=report.colsCount-1;k++){
            		//nowa kom�rka
            			XSSFCell cl;
            			cl= row.createCell(k);
            			//tworzy zmienn� z warto�ci� do wpisania w kom�rce
            			String val = report.resultRows[j][k];
            			//sprawdza czy warto�� jest liczb�
        				//if(val.length()>20){System.out.println("val :"+val+" [L] :"+val.length()+" [13] :"+val.substring(13,14)+" [16] :"+val.substring(16,17)+"[19] :"+val.substring(19,20));}
            			try{
            				if((val.length()==21 || val.length()==22 || val.length()==23) && val.substring(13,14).equals(":") && val.substring(16,17).equals(":") && val.substring(19,20).equals(".")){
            					val=val.substring(0,19);
            				}  
            				//val=val.replace(":00.0",":00");
            			}catch(Exception e){}
            			
            			try{
            				String sval=val.replace(" ","");
            				//pr�buje parsowa� warto�� na double i przypisa� j� do kom�rki
            				cl.setCellValue(Integer.parseInt(sval));
            			}catch(Exception e){
            				//je�eli nie uda si� parcowa� (warto�� nie jest liczb� zmiennoprzecinkow�)
            				//to przypisuje warto�� do kom�rki bez zmian
            				cl.setCellValue(val);
            			}
            			
            			//sprawdza czy warto�� w kom�rce jest liczb� zmiennoprzecinkow�
            			try{
            				String sdbl = val;
            				//zamienia przecinek na kropk� w liczbie dla zgodno�ci z polskim formatem liczb zmiennoprzecinkowych
            				sdbl=sdbl.replace(",", ".");
            				//pr�buje parsowa� warto�� na double i przypisa� j� do kom�rki
            				cl.setCellValue(Double.parseDouble(sdbl));
            			}catch(Exception e){
            				//je�eli nie uda si� parcowa� (warto�� nie jest liczb� zmiennoprzecinkow�)
            				//to przypisuje warto�� do kom�rki bez zmian
            				cl.setCellValue(val);
            			}
            			
            			try{
            				//usuwa spacje z liczby
            				long intCell = Long.parseLong(val.replace(" ", ""));
            				//sprawdza wielko�� liczby i je�eli jest wi�ksza ni� maksymalna wielko�� mo�liwa do
            				//wpisania w kom�rce excela to dodaje apostrof na pocz�tku, aby by�a formatowana jako tekst
            				//W przeciwnym wypadku excel usun��by cz�� liczby
            				if(intCell>=9999999999999L){
            					//if (k==1 && report.maskedCols[k].equals("WYNIK")){
                					cl.setCellStyle(cs);
                					val.toString();
                					cl.setCellValue(val);
            					//}else{val="'"+val;}
            				}
            			            				
            				//cl.setCellValue(val);	
            			}catch(Exception e){
            				//je�eli warto�� nie jest liczb� to nic z ni� nie robi
            			}
            			
            	}   
            	}catch(IllegalArgumentException e){
            		System.out.println("File NOT created. Too much data");
            		workbook.close();
            		return null;
            	}
            }
          }
          	//wypisuje komunikat w konsoli o ilo�ci arkuszy
            System.out.println(filename+" NumberOfSheets :"+workbook.getNumberOfSheets());
            //wykonuje polecenie autosize dla kolumn w arkuszach
			for(int ll=0;ll<workbook.getNumberOfSheets();ll++){
			sheet=workbook.getSheetAt(ll);
			 for(int l=0;l<=report.colsCount-1;l++){
				 sheet.autoSizeColumn(l);
				 }
			}
			//tworzy strumie� danych z plikiem wynikowym
            FileOutputStream fileOut = new FileOutputStream(fileloc);
            //zapisuje dane do pliku
            workbook.write(fileOut);
            //zamyka strumie�
            fileOut.close();
            //zamyka skoroszyt
            workbook.close();
            //wypisuje w konsoli komunikat o utworzeniu pliku
            System.out.println("File created: "+fileloc);
        } catch ( Exception ex ) {
            System.out.println(ex);
        }
		//zwraca �cie�k� do utworzonego pliku
		return "/repository/"+filename;
	}
	public static String excel(HttpServletRequest request, MyDB report, String filePath, String ulogin){	

	System.out.println("report.name :"+report.reportName);
	System.out.println("report.rowsCount :"+report.rowsCount);
	System.out.println("report.colsCount :"+report.colsCount);
	System.out.println("report.firstRow :"+report.firstRow);
	//zwraca null je�eli obiekt z danymi jest pusty
	if(report.rowsCount==0){return null;}
	//pobiera obiekt sesji z requesta w parametrze wej�ciowym
	
	if (ulogin==null || ulogin.equals("")){ulogin="trak";}
	
	//pobiera login u�ytkownika, kt�ry generuje plik z obiektu jego sesji
	//tworzy now� instancj� kalendarza
	//Calendar calendar = Calendar.getInstance();
	//�aduje do kalendarza aktualn� dat� i godzin�
	//java.util.Date now = calendar.getTime();
	//tworzy stempel daty z daty i godziny
	//java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(now.getTime());
	//deklaruje �cie�k� webow� do folderu - repozytorium z wygenerowanymi plikami
	//File fld = new File(request.getServletContext().getRealPath("")+"\\repository");
	File fld = new File(filePath+"\\repository");
	System.out.println("filePath: "+filePath);
	//pobiera �cie�k� fizyczn� do folderu
	String path = filePath;
				System.out.println("path: " + path);
				System.out.println("path2: " + path.replace("\\", "\\\\"));
	
	String filefolder = fld.getAbsolutePath();
	System.out.println("fld.getAbsolutePath() "+fld.getAbsolutePath());
	//String filefolder = "C:\\Users\\jkowaczek\\DEV_AREA\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\ROOT"+"\\repository";//fld.getAbsolutePath();
	//tworzy nazw� dla pliku zawieraj�c� login i hash stempla czasu
	//String filename = ulogin+"_"+report.reportName+"_"+currentTimestamp.hashCode()+".xlsx";
	String filename = ulogin+"_"+report.reportName+"_"+timestamp()+".xlsx";
	filename=filename.replace(" ", "_").replace("-", "");
	//tworzy fizyczn� �cie�k� dost�pu do pliku z fizycznej lokalizacji repozytorium i utworzonej nazwy
	String fileloc = filefolder+"\\"+filename;
	
	//p�tla listuj�ca pliki w repozytorium
	//for(File f: fld.listFiles())
		//sprawdza czy jaki� plik zawiera ju� login osoby generuj�cej nowy plik
	  //  if(f.getName().startsWith(ulogin))
	    	//je�eli tak to usuwa stary plik
	        //f.delete();
	
	try {
		//zmienna zawieraj�ca maksymaln� liczb� wierszy na arkuszu excela
        int maxNumberRowOfSheets = 1048575; //1048575 dla xlsx,65535 dla xls liczba wierszy w skoroszycie-nag��wek. (J. Kowaczek)
        //zmienna zawieraj�ca wyliczon� liczb� arkuszy do wygenerowania (liczba wierszy raportu / max wierszy na arkusz)
        int NumberOfSheets = report.rowsCount/maxNumberRowOfSheets; //liczba skoroszyt�w (J. Kowaczek)
        //tworzy nowy pusty skoroszyt excela
        XSSFWorkbook workbook = new XSSFWorkbook();
        //deklaruje nowy arkusz skoroszytu
        XSSFSheet sheet;
        XSSFCellStyle cs = workbook.createCellStyle();
        cs.setDataFormat((short)0x31);
        //CellStyle cellStyle;
        //p�tla przez liczb� arkuszy do utworzenia
      for (int ii=0;ii<=NumberOfSheets;ii++){
    	  //tworzy nowy arkusz z kolejnym numerem
        sheet = workbook.createSheet("Strona "+(ii+1)+" z "+(NumberOfSheets+1)); 
        //zdublowana instrukcja - pobiera utworzony arkusz
        sheet=workbook.getSheetAt(ii);
        //tworzy pierwszy wiersz w arkuszu dla nag��wk�w
        XSSFRow rowhead = sheet.createRow((int)0);
        //uzupe�nia wiersz nag��wkami z obiektu raportu
        //for(int i=0;i<=report.colsCount-1;i++){
        //    rowhead.createCell(i).setCellValue(report.maskedCols[i]);
        //}
       
      //deklaracja i przypisanie zmiennej z numerem pierwszego wiersza z danymi po nag��wkach
	    int dataRow=1;//pierwszy wiersz danych-drugi w xls (J. Kowaczek)
	    
	    // dodanie wiersza nad nag��wkami raportu o warto�ci r�wnej firstRow typu String 
	    // (funkcjonalno�� dedykowana do raportu rp66)
        if(report.firstRow!=null && !report.firstRow.equals("")){
    		rowhead = sheet.createRow((int)0);
    		rowhead.createCell(0).setCellValue(report.firstRow);
    		dataRow++;
    	}
        //rowhead = sheet.createRow((int)0);
        rowhead = sheet.createRow(dataRow-1);
        
    	for(int i=0;i<=report.colsCount-1;i++){
        rowhead.createCell(i).setCellValue(report.maskedCols[i]);
        }
        
	    //zmienna z wyliczonym numerem ostatniego wiersza w arkuszu
        int lastRowOfSheets=(ii+1)*maxNumberRowOfSheets;//ostatni wiesz danych na arkusz (J. Kowaczek)
        //sprawdza czy raport zawiera mniej wierszy ni� maksimum na arkusz i je�eli tak to tak� warto�� przypisuje
        //zmiennej z numerem ostatniego wiersza
        if (lastRowOfSheets>report.rowsCount){lastRowOfSheets=report.rowsCount;}//ostatni wiersz w raporcie (J. Kowaczek)
        //p�tla przez wiersze raportu
	    for(int j=ii*maxNumberRowOfSheets;j<lastRowOfSheets;j++){
        	try{
        		//tworzy wiersz arkusza
        	XSSFRow row = sheet.createRow((Integer)dataRow++);
        	//p�tla wype�niaj�ca kom�rki wiersza
        	for(int k=0;k<=report.colsCount-1;k++){
        		//nowa kom�rka
        			XSSFCell cl;
        			cl= row.createCell(k);
        			//tworzy zmienn� z warto�ci� do wpisania w kom�rce
        			String val = report.resultRows[j][k];
        			//sprawdza czy warto�� jest liczb�
    				//if(val.length()>20){System.out.println("val :"+val+" [L] :"+val.length()+" [13] :"+val.substring(13,14)+" [16] :"+val.substring(16,17)+"[19] :"+val.substring(19,20));}
        			try{
        				if((val.length()==21 || val.length()==22 || val.length()==23) && val.substring(13,14).equals(":") && val.substring(16,17).equals(":") && val.substring(19,20).equals(".")){
        					val=val.substring(0,19);
        				}  
        				//val=val.replace(":00.0",":00");
        			}catch(Exception e){}
        			
        			try{
        				String sval=val.replace(" ","");
        				//pr�buje parsowa� warto�� na double i przypisa� j� do kom�rki
        				cl.setCellValue(Integer.parseInt(sval));
        			}catch(Exception e){
        				//je�eli nie uda si� parcowa� (warto�� nie jest liczb� zmiennoprzecinkow�)
        				//to przypisuje warto�� do kom�rki bez zmian
        				cl.setCellValue(val);
        			}
        			
        			//sprawdza czy warto�� w kom�rce jest liczb� zmiennoprzecinkow�
        			try{
        				String sdbl = val;
        				//zamienia przecinek na kropk� w liczbie dla zgodno�ci z polskim formatem liczb zmiennoprzecinkowych
        				sdbl=sdbl.replace(",", ".");
        				//pr�buje parsowa� warto�� na double i przypisa� j� do kom�rki
        				cl.setCellValue(Double.parseDouble(sdbl));
        			}catch(Exception e){
        				//je�eli nie uda si� parcowa� (warto�� nie jest liczb� zmiennoprzecinkow�)
        				//to przypisuje warto�� do kom�rki bez zmian
        				cl.setCellValue(val);
        			}
        			
        			try{
        				//usuwa spacje z liczby
        				long intCell = Long.parseLong(val.replace(" ", ""));
        				//sprawdza wielko�� liczby i je�eli jest wi�ksza ni� maksymalna wielko�� mo�liwa do
        				//wpisania w kom�rce excela to dodaje apostrof na pocz�tku, aby by�a formatowana jako tekst
        				//W przeciwnym wypadku excel usun��by cz�� liczby
        				if(intCell>=9999999999999L){
        					//if (k==1 && report.maskedCols[k].equals("WYNIK")){
            					cl.setCellStyle(cs);
            					val.toString();
            					cl.setCellValue(val);
        					//}else{val="'"+val;}
        				}
        			            				
        				//cl.setCellValue(val);	
        			}catch(Exception e){
        				//je�eli warto�� nie jest liczb� to nic z ni� nie robi
        			}
        			
        	}   
        	}catch(IllegalArgumentException e){
        		System.out.println("File NOT created. Too much data");
        		workbook.close();
        		return null;
        	}
        }
      }
      	//wypisuje komunikat w konsoli o ilo�ci arkuszy
        System.out.println(filename+" NumberOfSheets :"+workbook.getNumberOfSheets());
        //wykonuje polecenie autosize dla kolumn w arkuszach
		for(int ll=0;ll<workbook.getNumberOfSheets();ll++){
		sheet=workbook.getSheetAt(ll);
		 for(int l=0;l<=report.colsCount-1;l++){
			 sheet.autoSizeColumn(l);
			 }
		}
		//tworzy strumie� danych z plikiem wynikowym
        FileOutputStream fileOut = new FileOutputStream(fileloc);
        //zapisuje dane do pliku
        workbook.write(fileOut);
        //zamyka strumie�
        fileOut.close();
        //zamyka skoroszyt
        workbook.close();
        //wypisuje w konsoli komunikat o utworzeniu pliku
        System.out.println("File created: "+fileloc);
    } catch ( Exception ex ) {
        System.out.println(ex);
    }
	//zwraca �cie�k� do utworzonego pliku
	return "/repository/"+filename;
	
}
	
	
	
	
	
	
	
//******************************************************************************************************************************************************************
	//metoda tworzy plik csv na podstwawie obiektu typu MyDB zawieraj�cego dane z zapytania sql
	public static String csv(HttpServletRequest request, MyDB report){
		//zwraca null je�eli obiekt z danymi jest pusty
		if(report.rowsCount==0){return null;}
		//pobiera obiekt sesji z requesta w parametrze wej�ciowym
		HttpSession session = request.getSession();
		//pobiera login u�ytkownika, kt�ry generuje plik z obiektu jego sesji
		String ulogin = (String) session.getAttribute("userid");
		//tworzy now� instancj� kalendarza
		//Calendar calendar = Calendar.getInstance();
		//�aduje do kalendarza aktualn� dat� i godzin�
		//java.util.Date now = calendar.getTime();
		//tworzy stempel daty z daty i godziny
		//java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(now.getTime());
		//deklaruje �cie�k� webow� do folderu - repozytorium z wygenerowanymi plikami
		File fld = new File(request.getServletContext().getRealPath("")+"\\repository");
		//pobiera �cie�k� fizyczn� do folderu
		String filefolder = fld.getAbsolutePath();
		//tworzy nazw� dla pliku zawieraj�c� login i hash stempla czasu
		//String filename = ulogin+"_"+report.reportName+"_"+currentTimestamp.hashCode()+".csv";
		String filename = ulogin+"_"+report.reportName+"_"+timestamp()+".csv";
		filename=filename.replace(" ", "_").replace("-", "");
		//tworzy fizyczn� �cie�k� dost�pu do pliku z fizycznej lokalizacji repozytorium i utworzonej nazwy
		String fileloc = filefolder+"\\"+filename;
		//p�tla listuj�ca pliki w repozytorium
		for(File f: fld.listFiles())
			//sprawdza czy jaki� plik zawiera ju� login osoby generuj�cej nowy plik
		    if(f.getName().startsWith(ulogin))
		    	//je�eli tak to usuwa stary plik
		        f.delete();
		try {
			FileOutputStream fout=new FileOutputStream(fileloc); 
			String s;			
			//uzupe�nia wiersz nag��wkami z obiektu raportu
            for(int i=0;i<=report.colsCount-1;i++){
            	s="\""+report.maskedCols[i]+"\"";
            	if(i!=report.colsCount-1){s=s+";";}
            	byte b[]=s.getBytes();//converting string into byte array
            	fout.write(b);
            }
            fout.write(13);
            fout.write(10);
           	for(int i=0;i<report.rowsCount;i++){
            	try{
            		for(int j=0;j<=report.colsCount-1;j++){
            			s="\""+report.resultRows[i][j]+"\"";
            			if(j!=report.colsCount-1){s=s+";";}
            			byte b[]=s.getBytes();//converting string into byte array
            			fout.write(b);
            		}
                if(i!=report.rowsCount-1){
                	fout.write(13);
                    fout.write(10);                	
                }
            }catch(IllegalArgumentException e){
            		System.out.println("File NOT created. Too much data");
            		return null;
            	}
            }
          
            //zamyka strumie�
            fout.close();
            //wypisuje w konsoli komunikat o utworzeniu pliku
            System.out.println("File created: "+fileloc);
        } catch ( Exception ex ) {
            System.out.println(ex);
        } 
		//zwraca �cie�k� do utworzonego pliku
		return "/repository/"+filename;
	}

	/*
	public static String excel2(HttpServletRequest request, TaskCalendarEventsNumber report){
		//zwraca null je�eli obiekt z danymi jest pusty
		System.out.println("report.name :"+report.reportName);
		System.out.println("report.rowsCount :"+report.rowsCount);
		System.out.println("report.colsCount :"+report.colsCount);
		if(report.rowsCount==0){return null;}
		//pobiera obiekt sesji z requesta w parametrze wej�ciowym
		HttpSession session = request.getSession();
		//pobiera login u�ytkownika, kt�ry generuje plik z obiektu jego sesji
		String ulogin = (String) session.getAttribute("userid");
		//tworzy now� instancj� kalendarza
		Calendar calendar = Calendar.getInstance();
		//�aduje do kalendarza aktualn� dat� i godzin�
		java.util.Date now = calendar.getTime();
		//tworzy stempel daty z daty i godziny
		java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(now.getTime());
		//deklaruje �cie�k� webow� do folderu - repozytorium z wygenerowanymi plikami
		File fld = new File(request.getServletContext().getRealPath("")+"\\repository");
		//pobiera �cie�k� fizyczn� do folderu
		String filefolder = fld.getAbsolutePath();
		//tworzy nazw� dla pliku zawieraj�c� login i hash stempla czasu
		String filename = ulogin+"_"+report.reportName+"_"+currentTimestamp.hashCode()+".xlsx";
		//tworzy fizyczn� �cie�k� dost�pu do pliku z fizycznej lokalizacji repozytorium i utworzonej nazwy
		String fileloc = filefolder+"\\"+filename;
		
		//p�tla listuj�ca pliki w repozytorium
		for(File f: fld.listFiles())
			//sprawdza czy jaki� plik zawiera ju� login osoby generuj�cej nowy plik
		    if(f.getName().startsWith(ulogin))
		    	//je�eli tak to usuwa stary plik
		        f.delete();
		
		try {
			//zmienna zawieraj�ca maksymaln� liczb� wierszy na arkuszu excela
            int maxNumberRowOfSheets = 1048575; //1048575 dla xlsx,65535 dla xls liczba wierszy w skoroszycie-nag��wek. (J. Kowaczek)
            //zmienna zawieraj�ca wyliczon� liczb� arkuszy do wygenerowania (liczba wierszy raportu / max wierszy na arkusz)
            int NumberOfSheets = report.rowsCount/maxNumberRowOfSheets; //liczba skoroszyt�w (J. Kowaczek)
            //tworzy nowy pusty skoroszyt excela
            XSSFWorkbook workbook = new XSSFWorkbook();
            //deklaruje nowy arkusz skoroszytu
            XSSFSheet sheet;
            XSSFCellStyle cs = workbook.createCellStyle();
            cs.setDataFormat((short)0x31);
            //CellStyle cellStyle;
            //p�tla przez liczb� arkuszy do utworzenia
          for (int ii=0;ii<=NumberOfSheets;ii++){
        	  //tworzy nowy arkusz z kolejnym numerem
            sheet = workbook.createSheet("Strona "+(ii+1)+" z "+(NumberOfSheets+1)); 
            //zdublowana instrukcja - pobiera utworzony arkusz
            sheet=workbook.getSheetAt(ii);
            //tworzy pierwszy wiersz w arkuszu dla nag��wk�w
            XSSFRow rowhead = sheet.createRow((int)0);
            //uzupe�nia wiersz nag��wkami z obiektu raportu
            //for(int i=0;i<=report.colsCount-1;i++){
            //    rowhead.createCell(i).setCellValue(report.maskedCols[i]);
            //}
           
        	rowhead = sheet.createRow((int)0);
            for(int i=0;i<=report.colsCount-1;i++){
            rowhead.createCell(i).setCellValue(report.maskedCols[i]);
            }
            //deklaracja i przypisanie zmiennej z numerem pierwszego wiersza z danymi po nag��wkach
    	    int dataRow=1;//pierwszy wiersz danych-drugi w xls (J. Kowaczek)
    	    //zmienna z wyliczonym numerem ostatniego wiersza w arkuszu
            int lastRowOfSheets=(ii+1)*maxNumberRowOfSheets;//ostatni wiesz danych na arkusz (J. Kowaczek)
            //sprawdza czy raport zawiera mniej wierszy ni� maksimum na arkusz i je�eli tak to tak� warto�� przypisuje
            //zmiennej z numerem ostatniego wiersza
            if (lastRowOfSheets>report.rowsCount){lastRowOfSheets=report.rowsCount;}//ostatni wiersz w raporcie (J. Kowaczek)
            //p�tla przez wiersze raportu
    	    for(int j=ii*maxNumberRowOfSheets;j<lastRowOfSheets;j++){
            	try{
            		//tworzy wiersz arkusza
            	XSSFRow row = sheet.createRow((Integer)dataRow++);
            	//p�tla wype�niaj�ca kom�rki wiersza
            	for(int k=0;k<=report.colsCount-1;k++){
            		//nowa kom�rka
            			XSSFCell cl;
            			cl= row.createCell(k);
            			//tworzy zmienn� z warto�ci� do wpisania w kom�rce
            			String val = report.resultRows[j][k];
            			//sprawdza czy warto�� jest liczb�
        				//if(val.length()>20){System.out.println("val :"+val+" [L] :"+val.length()+" [13] :"+val.substring(13,14)+" [16] :"+val.substring(16,17)+"[19] :"+val.substring(19,20));}
            			try{
            				if((val.length()==21 || val.length()==22 || val.length()==23) && val.substring(13,14).equals(":") && val.substring(16,17).equals(":") && val.substring(19,20).equals(".")){
            					val=val.substring(0,19);
            				}  
            				//val=val.replace(":00.0",":00");
            			}catch(Exception e){}
            			
            			try{
            				String sval=val.replace(" ","");
            				//pr�buje parsowa� warto�� na double i przypisa� j� do kom�rki
            				cl.setCellValue(Integer.parseInt(sval));
            			}catch(Exception e){
            				//je�eli nie uda si� parcowa� (warto�� nie jest liczb� zmiennoprzecinkow�)
            				//to przypisuje warto�� do kom�rki bez zmian
            				cl.setCellValue(val);
            			}
            			
            			//sprawdza czy warto�� w kom�rce jest liczb� zmiennoprzecinkow�
            			try{
            				String sdbl = val;
            				//zamienia przecinek na kropk� w liczbie dla zgodno�ci z polskim formatem liczb zmiennoprzecinkowych
            				sdbl=sdbl.replace(",", ".");
            				//pr�buje parsowa� warto�� na double i przypisa� j� do kom�rki
            				cl.setCellValue(Double.parseDouble(sdbl));
            			}catch(Exception e){
            				//je�eli nie uda si� parcowa� (warto�� nie jest liczb� zmiennoprzecinkow�)
            				//to przypisuje warto�� do kom�rki bez zmian
            				cl.setCellValue(val);
            			}
            			
            			try{
            				//usuwa spacje z liczby
            				long intCell = Long.parseLong(val.replace(" ", ""));
            				//sprawdza wielko�� liczby i je�eli jest wi�ksza ni� maksymalna wielko�� mo�liwa do
            				//wpisania w kom�rce excela to dodaje apostrof na pocz�tku, aby by�a formatowana jako tekst
            				//W przeciwnym wypadku excel usun��by cz�� liczby
            				if(intCell>=9999999999999L){
            					//if (k==1 && report.maskedCols[k].equals("WYNIK")){
                					cl.setCellStyle(cs);
                					val.toString();
                					cl.setCellValue(val);
            					//}else{val="'"+val;}
            				}
            			            				
            				//cl.setCellValue(val);	
            			}catch(Exception e){
            				//je�eli warto�� nie jest liczb� to nic z ni� nie robi
            			}
            			
            	}   
            	}catch(IllegalArgumentException e){
            		System.out.println("File NOT created. Too much data");
            		workbook.close();
            		return null;
            	}
            }
          }
          	//wypisuje komunikat w konsoli o ilo�ci arkuszy
            System.out.println(filename+" NumberOfSheets :"+workbook.getNumberOfSheets());
            //wykonuje polecenie autosize dla kolumn w arkuszach
			for(int ll=0;ll<workbook.getNumberOfSheets();ll++){
			sheet=workbook.getSheetAt(ll);
			 for(int l=0;l<=report.colsCount-1;l++){
				 sheet.autoSizeColumn(l);
				 }
			}
			//tworzy strumie� danych z plikiem wynikowym
            FileOutputStream fileOut = new FileOutputStream(fileloc);
            //zapisuje dane do pliku
            workbook.write(fileOut);
            //zamyka strumie�
            fileOut.close();
            //zamyka skoroszyt
            workbook.close();
            //wypisuje w konsoli komunikat o utworzeniu pliku
            System.out.println("File created: "+fileloc);
        } catch ( Exception ex ) {
            System.out.println(ex);
        }
		//zwraca �cie�k� do utworzonego pliku
		return "/repository/"+filename;
	}
	*/

}

