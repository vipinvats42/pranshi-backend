package com.pranshihandicraft.admin;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import com.common.pranshihandicraft.entity.User;

import jakarta.servlet.http.HttpServletResponse;

public class UserCsvExporter extends AbstractExporter{
	
	public void export(List<User> listUsers, HttpServletResponse response)  throws IOException
	{
        super.setResponseHeader(response, "text/csv", ".csv");
		
		
		ICsvBeanWriter csvWriter= new CsvBeanWriter(response.getWriter(),
				CsvPreference.STANDARD_PREFERENCE);
		
		String[] csvHeader= {"User ID","E-mail","First Name","Last Name","Roles","Enabled"};
		String[] filedMapping = {"id","emailId","firstName","lastName","roles","enabled"};
		
		csvWriter.writeHeader(csvHeader);
		
		for(User user : listUsers) {
			csvWriter.write(user,filedMapping);
		}
		
		
		csvWriter.close();
		
	}
}
