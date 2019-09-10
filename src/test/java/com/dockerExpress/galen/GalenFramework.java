package com.dockerExpress.galen;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.testng.Reporter;
import org.testng.annotations.Parameters;

import com.dockerExpress.base.TestBase;
import com.galenframework.api.Galen;
import com.galenframework.reports.GalenTestInfo;
import com.galenframework.reports.HtmlReportBuilder;
import com.galenframework.reports.model.LayoutReport;
import com.galenframework.support.GalenReportsContainer;

public class GalenFramework extends TestBase{

	List<GalenTestInfo> objGalentestsList	= new LinkedList<GalenTestInfo>();
	
    public void loginPageLayoutTest(String specFilePath, String tag )
    {
    	try{
		    String browser = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("browser");
		    String version = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("version");
			String name = new File(specFilePath).getName().split("\\.", 2)[0];
			LayoutReport objLayoutReport 			= Galen.checkLayout(getDriver(), specFilePath, Arrays.asList(tag));
			GalenTestInfo objSingleGalenTest 		= GalenTestInfo.fromString(name+"_"+browser+"_"+version+"_"+tag+"_"+"Test");
			objSingleGalenTest.getReport().layout(objLayoutReport, "Check Layout for "+name);
			objGalentestsList.add(objSingleGalenTest);
			HtmlReportBuilder htmlReportBuilder = new HtmlReportBuilder();
			htmlReportBuilder.build(objGalentestsList, "galen-results\\"+browser+"_"+version+"");
			if (objLayoutReport.errors() > 0)
	        	System.out.println("Layout test failed for "+name);
	            //Assert.fail();
        	else
				System.out.println("Layout test PASSED for "+name);
//	        File file = new File(System.getProperty("user.dir")+"\\galen-results\\report.html");
//	        if(file.exists() && !file.isDirectory()&& name.equalsIgnoreCase("limits")) { 
//	        	File newFile = new File(System.getProperty("user.dir")+"\\galen-results\\report_"+tag+".html");
//	            if(file.renameTo(newFile)){
//	                System.out.println("File rename success");;
//	            }else{
//	                System.out.println("File rename failed");
//	            }
//	        }
		}
		catch(Exception e){
			System.out.println("Exception at: "+specFilePath);
		}
    }
	
	
}
