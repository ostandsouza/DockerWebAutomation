package com.dockerExpress.base;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

import org.testng.annotations.Test;

//import java.io.File;
//import java.io.OutputStream;
//import java.io.OutputStreamWriter;
//import java.io.PrintWriter;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.net.URLConnection;
//import java.nio.file.Files;
//import java.util.concurrent.CompletionService;
//import java.util.concurrent.ExecutorService;
//@Test
public class GetParamters {
//	CSVReader reader = new CSVReader(new FileReader(FILE_PATH));
//	int concurrencyCount = 5;
//	ExecutorService executorService = Executors.newFixedThreadPool(concurrencyCount);
//	CompletionService<Boolean> completionService = new ExecutorCompletionService<Boolean>(executorService);
//	String[] nextLine;
	protected static Properties prop;
	 String response;
	
//	public GetParamters(){
//		try{
//			prop = new Properties();
//			FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\com\\dockerExpress\\config\\config.properties");
//			prop.load(ip);
//		}
//		catch(FileNotFoundException e){
//			e.printStackTrace();
//		}
//		catch(IOException e){
//			e.printStackTrace();
//		}
//	}
	

//	
	public String SendFile(String path){
	try{
	prop = new Properties();
	FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\com\\dockerExpress\\config\\config.properties");
	prop.load(ip);
	String charset = "UTF-8";
	File File = new File(System.getProperty("user.dir")+ path);
	String url = prop.getProperty("upload");
	String boundary = Long.toHexString(System.currentTimeMillis()); // Just generate some unique random value.
	String CRLF = "\r\n"; // Line separator required by multipart/form-data.prop.getProperty("api")
	URLConnection connection = new URL(url).openConnection();
	connection.setDoOutput(true);
	connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
	try (
		    OutputStream output = connection.getOutputStream();
		    PrintWriter writer = new PrintWriter(new OutputStreamWriter(output, charset), true);
		) {
		
	    writer.append("--" + boundary).append(CRLF);
	    writer.append("Content-Disposition: form-data; name=\"file\"; filename=\"" + File.getName() + "\"").append(CRLF);
	    writer.append("Content-Type: text/html").append(CRLF); // Text file itself must be saved in this charset!
	    writer.append(CRLF).flush();
	    Files.copy(File.toPath(), output);
	    output.flush(); // Important before continuing with writer!
	    writer.append(CRLF).flush(); // CRLF is important! It indicates end of boundary.
	    
	    // End of multipart/form-data.
	    writer.append("--" + boundary + "--").append(CRLF).flush();
	}

	// Request is lazily fired whenever you need to obtain information about response.
	BufferedReader br;
	if (200 <= ((HttpURLConnection) connection).getResponseCode() && ((HttpURLConnection) connection).getResponseCode() <= 299) {
	    br = new BufferedReader(new InputStreamReader(((HttpURLConnection) connection).getInputStream()));
	} else {
	    br = new BufferedReader(new InputStreamReader(((HttpURLConnection) connection).getErrorStream()));
	}
	response = br.lines().collect(Collectors.joining());
	System.out.println(response);
//	System.out.println(responseCode); // Should be 200
	return response;
	}
	catch(Exception e){
		System.out.print(e);
	}
	return response;
}

	
	public String sendVal(String testng, String galen){
		try{
			prop = new Properties();
			FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\com\\dockerExpress\\config\\config.properties");
			prop.load(ip);
		String uri = prop.getProperty("create");
		URL url = new URL(uri);
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date = new Date();
		
	    final String value = "{\"api\": \""+prop.getProperty("api")+"\",\"insertDoc\":{\"time\": \""+dateFormat.format(date)+"\" , \"suitename\": \""+prop.getProperty("api")+"\" , \"device\": \""+prop.getProperty("device")+"\", \"notes\": \""+prop.getProperty("notes")+"\" , "
			+ "\"FailedTestCases\": \"50\" , \"PassedTestCases\": \"150\" , \"SkippedTestCases\": \"50\" , \"TotalTestCases\": \"250\", \"RunType\": \""+prop.getProperty("RunType")+"\", \"uiReport\": "+galen+" ,"
			+ "\"functionalReport\": "+testng+" , \"version\": \""+prop.getProperty("version")+"\"}}";
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("POST");
		con.setRequestProperty("Content-Type", "application/json");
		System.out.println(value);
		// For POST only - START
		con.setDoOutput(true);
		OutputStream os = con.getOutputStream();
		os.write(value.getBytes());
		os.flush();
		os.close();

	     BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
	     System.out.println(in.lines().collect(Collectors.joining()));

	     StringBuilder sb = new StringBuilder();
	     for (int c; (c = in.read()) >= 0; )
	         sb.append((char) c);
	     response = sb.toString();
	     System.out.println(response);


		 return  response;
		 }catch (Exception excep){
		     excep.printStackTrace();}
		 return response;
	}
	
	String testng = SendFile("\\test-output\\Extent.html");
	String galen = SendFile("\\galen-results\\chrome_72.0\\report.html");
	String res = sendVal(testng , galen);
	
	
}
	
