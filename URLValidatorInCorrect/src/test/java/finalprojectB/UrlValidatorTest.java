/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package finalprojectB;

import java.util.Calendar;
import junit.framework.TestCase;
import java.util.Random;
import org.junit.Test;
/**
 * Performs Validation Test for url validations.
 *
 * @version $Revision: 1128446 $ $Date: 2011-05-27 13:29:27 -0700 (Fri, 27 May 2011) $
 */
public class UrlValidatorTest extends TestCase {

   private boolean printStatus = false;
   private boolean printIndex = false;//print index that indicates current scheme,host,port,path, query test were using.

   public UrlValidatorTest(String testName) {
      super(testName);
   }

   
   
   public void testManualTest()
   {
	   UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
	   System.out.println(urlVal.isValid("http://www.amazon.com"));

	   //--valid and invalid ASCII/Regular Expressions--//
	   assertFalse(urlVal.isValid("http://www.göögle.com"));
	   assertTrue(urlVal.isValid("http://www.amazon.com"));
	   assertFalse(urlVal.isValid("http://www.tacotüesday.com"));
	   assertTrue(urlVal.isValid("http://www.oregonstate.edu"));

       //--testing valid and invalid scheme--//
	   //assertFalse(urlVal.isValid("invalidhttp://www.amazon.com"));     //test fails, UNEXPECTED, cannot run uncommented
       //assertFalse(urlVal.isValid("p://www.amazon.com"));     //test fails, UNEXPECTED, cannot run uncommented
       assertTrue(urlVal.isValid("http://www.nba.com"));
       assertTrue(urlVal.isValid("https://mail.google.com"));

       //--testing valid and invalid domain names--//
       assertTrue(urlVal.isValid("https://www.navy.mil"));
       assertTrue(urlVal.isValid("https://www.energy.gov"));
       assertTrue(urlVal.isValid("http://www.ontariocolleges.ca"));
       //assertTrue(urlVal.isValid("https://www.gov.uk"));              //test fails, UNEXPECTED, cannot run uncommented

	   assertFalse(urlVal.isValid("http://www.google.invalidcom"));
       assertFalse(urlVal.isValid(""));
       //assertTrue(urlVal.isValid("http://255.255.255.255//"));      //test fails, UNEXPECTED, cannot run uncommented
       assertFalse(urlVal.isValid("http://256.256.256.256//"));
       //assertTrue(urlVal.isValid("http://0.0.0.0//"));          //test fails, UNEXPECTED, cannot run uncommented
       assertTrue(urlVal.isValid("http://www.google.com/800/"));       //test fails, UNEXPECTED, cannot run uncommented
       assertFalse(urlVal.isValid("http://localhost:4$3"));
       //assertTrue(urlVal.isValid("http://localhost:400/"));       //test fails, UNEXPECTED, cannot run uncommented
       //assertTrue(urlVal.isValid("http://localhost:40/"));        //test fails, UNEXPECTED, cannot run uncommented
       //assertTrue(urlVal.isValid("http://localhost:8"));
   }
   
   public void testSchemePartition()
   {
        UrlValidator invalid = new UrlValidator(null, null,0);
        assertTrue(invalid.isValid("http://www.amazon.com"));
        assertFalse(invalid.isValid("ht://www.amazon.com"));

        UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
        //assertTrue(urlVal.isValid("amazon.com"));
        assertTrue(urlVal.isValid("http://amazon.com"));
        assertFalse(urlVal.isValid("http:/amazon.com"));
        assertFalse(urlVal.isValid("http:amazon.com"));
        assertFalse(urlVal.isValid("http/amazon.com"));
        assertFalse(urlVal.isValid("://amazon.com"));
        assertTrue(urlVal.isValid("ftp://amazon.com"));
        assertTrue(urlVal.isValid("h3t://amazon.com"));
        assertFalse(urlVal.isValid("3ht://amazon.com"));
   }

   public void testAuthortiyPartition()
   {
	UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
	assertTrue(urlVal.isValid("file:///etc"));
	assertTrue(urlVal.isValid("http://www.amazon.com"));
	assertFalse(urlVal.isValid(""));
	assertFalse(urlVal.isValid("4.3.5.2."));
	assertFalse(urlVal.isValid("1.5.7.5.7"));
	assertFalse(urlVal.isValid("3.5.9"));
	assertFalse(urlVal.isValid("am.zn"));
	assertFalse(urlVal.isValid("am.3j"));
	assertTrue(urlVal.isValid("http://0.0.0.0"));
	assertTrue(urlVal.isValid("http://amazon.cc"));
	assertTrue(urlVal.isValid("http://amazon.com"));
	assertTrue(urlVal.isValid("http://amazon.com:80"));
	//assertFalse(urlVal.isValid("http://amazon.com:-1"));
	assertTrue(urlVal.isValid("http://amazon.com:0"));
	//assertTrue(urlVal.isValid("http://amazon.com:65565"));
   }

   public void testPathPartition()
   {
	UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
	assertTrue(urlVal.isValid("http://www.amazon.com/file"));
	assertTrue(urlVal.isValid("http://www.amazon.com/file/file2"));
	//assertTrue(urlVal.isValid("http://www.amazon.com/$file4"));
	//assertTrue(urlVal.isValid("http://www.amazon.com/file//file3"));
	assertTrue(urlVal.isValid("http://www.amazon.com/file/"));
	//assertFalse(urlVal.isValid("http://amazon.com/#"));
	//assertFalse(urlVal.isValid("http://amazon.com/#/file"));
	assertFalse(urlVal.isValid("http://amazon.com/.."));
	assertFalse(urlVal.isValid("http://amazon.com/../file"));
	assertFalse(urlVal.isValid("http://amazon.com/..//file"));
		
   }

   public void testQueryPartition()
   {
	UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
	//assertTrue(urlVal.isValid("http://amazon.com?action=hide"));
	//assertTrue(urlVal.isValid("http://amazon.com?action=hide&align=center"));
	assertTrue(urlVal.isValid("http://amazon.com"));
   }

   private static final long TestTimeout = 60 * 500 * 1;

   public void testIsValid()
   {
	String[] schemes = {"http://","ftp://","h3t://","://","htp://","","http:/","file://"}; //8
	String[] auths = {"","www.amazon.com","0.0.0.0","amazon.com:80","am.3n","amazon.cc","amazon.com:jhs"}; //7
	String[] paths = {"/file","/file/file2","/..","/#","/file//file3","/etc"}; //6
	String[] queries = {"?action=hide","","?action=hide&mode=up"}; //3
	long startTime = Calendar.getInstance().getTimeInMillis();
	long elapsed = Calendar.getInstance().getTimeInMillis() - startTime;
	System.out.println("Start testing...");
	for (int iteration = 0; elapsed < TestTimeout; iteration++) 
	{
		long randomseed = 10;//System.currentTimeMillis();
		Random random = new Random(randomseed);
		for(int i = 0; i < 500; i++)
		{
			UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
			int random1 = (int )(Math.random() * 5 + 1);
			if(random1 == 1) //test a scheme
			{
				int random2 = (int )(Math.random() * 8 + 0);
				String temp = schemes[random2] + auths[1];
				urlVal.isValid(temp);
			}
			else if(random1 == 2) //test an authority+port
			{
				int random2 = (int )(Math.random() * 7 + 0);
				String temp = schemes[0] + auths[random2];
                                urlVal.isValid(temp);
			}
			else if(random1 == 3) //test a path
                        {
				int random2 = (int )(Math.random() * 6 + 0);
				String temp = schemes[0] + auths[1] + paths[random2];
                                urlVal.isValid(temp);
                        }
			else if(random1 == 4) //test a query
                        {
				int random2 = (int )(Math.random() * 3 + 0);
				String temp = schemes[0] + auths[1] + queries[random2];
                                urlVal.isValid(temp);
                        }
		}
		elapsed = (Calendar.getInstance().getTimeInMillis() - startTime);
		if ((iteration % 10000) == 0 && iteration != 0)
			System.out.println("elapsed time: " + elapsed + " of " + TestTimeout);
	}
	System.out.println("Done testing...");
   }
}
