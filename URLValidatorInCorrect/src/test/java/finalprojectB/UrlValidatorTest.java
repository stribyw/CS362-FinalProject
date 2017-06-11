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

import junit.framework.TestCase;





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
       //assertTrue(urlVal.isValid("http://localhost:8000/"));       //test fails, UNEXPECTED, cannot run uncommented
       assertFalse(urlVal.isValid("http://localhost:4$3"));
   }
   
   public void testSchemePartition()
   {
        UrlValidator invalid = new UrlValidator(null, null,0);
        assertTrue(invalid.isValid("http://www.amazon.com"));
        assertFalse(invalid.isValid("ht://www.amazon.com"));

        UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
        assertFalse(urlVal.isValid("amazon.com"));
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
   }

   public void testPortPartition()
   {
	
   }

   public void testPathPartition()
   {

   }

   public void testQueryPartition()
   {

   }

   public void testUrlPartition()
   {

   }
   
   public void testIsValid()
   {

	   for(int i = 0;i<10000;i++)
	   {
		   
	   }
   }
   
   public void testAnyOtherUnitTest()
   {
	   
   }
   /**
    * Create set of tests by taking the testUrlXXX arrays and
    * running through all possible permutations of their combinations.
    *
    * @param testObjects Used to create a url.
    */
   

}
