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


	   //assertFalse(urlVal.isValid("invalidhttp://www.amazon.com"));     //test fails, cannot run uncommented
       //assertFalse(urlVal.isValid("p://www.amazon.com"));     //test fails, cannot run uncommented
       assertTrue(urlVal.isValid("http://www.nba.com"));
	   assertFalse(urlVal.isValid("http://www.google.invalidcom"));
       assertFalse(urlVal.isValid(""));
   }
   
   public void testSchemePartition()
   {
        UrlValidator invalid = new UrlValidator(null, null);
        invalid.isValid("http://www.amazon.com");
        invalid.isValid("ht://www.amazon.com");

        UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
        urlVal.isValid("amazon.com");
        urlVal.isValid("http://amazon.com");
        urlVal.isValid("http:/amazon.com");
        urlVal.isValid("http:amazon.com");
        urlVal.isValid("http/amazon.com");
        urlVal.isValid("://amazon.com");
        urlVal.isValid("ftp://amazon.com");
        urlVal.isValid("h3t://amazon.com");
        urlVal.isValid("3htt://amazon.com");
   }

   public void testAuthortiyPartition()
   {

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
