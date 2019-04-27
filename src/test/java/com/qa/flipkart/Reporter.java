package com.qa.flipkart;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.testng.IReporter;
import org.testng.IResultMap;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.xml.XmlSuite;

public class Reporter implements IReporter {

	public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
		 // Second parameter of this method ISuite will contain all the suite executed.
		
		for(ISuite is : suites)
		{
			//Get a map of result of a single suite at a time

			Map<String, ISuiteResult> results = is.getResults();
			Set<String> keys = results.keySet();
			for(String key : keys)
			{
				ITestContext context  = results.get(key).getTestContext();
				System.out.println("suite Name  " +context.getSuite().getName());
				
				IResultMap pt = context.getPassedTests();
				Collection<ITestNGMethod> ptm = pt.getAllMethods();
				for( ITestNGMethod iptm : ptm )
				{
					System.out.println("Passed Testcase name   "+ iptm.getMethodName() + "  " + iptm.getPriority());
				}
				
				Collection<ITestNGMethod> exclmethods = context.getExcludedMethods();
				for ( ITestNGMethod em : exclmethods)
				{
					System.out.println(" Excluded Testcase name   "+ em.getMethodName());
					System.out.println("Description   "+ em.getDescription());
					 
					
				}
		
				IResultMap t = context.getFailedTests();
				Collection<ITestNGMethod> failedmethods = t.getAllMethods();
				for(ITestNGMethod itm : failedmethods)
				{
					System.out.println("Failed Testcase name   "+ itm.getMethodName());
					System.out.println("Description   "+ itm.getDescription());
					System.out.println("Date  "+ itm.getDate()); 
					System.out.println("priority   "+ itm.getPriority());
				}
					
			}
			
		}
	}

}
