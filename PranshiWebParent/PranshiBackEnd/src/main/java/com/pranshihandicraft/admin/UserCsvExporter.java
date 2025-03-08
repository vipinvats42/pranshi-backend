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

public class UserCsvExporter {
	
	public void export(List<User> listUsers, HttpServletResponse response) throws IOException
	{
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
		String timeStamp = dateFormatter.format(new Date());
		String fileName="users_"+timeStamp+".csv";
		
		response.setContentType("text/csv");
		String headerKey="Content-Disposition";
		String headerValue="attachment; fileName"+fileName;
		response.setHeader(headerKey, headerValue);
		
		
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
