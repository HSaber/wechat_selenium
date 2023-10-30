package com.hu.testcase;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.interactive.testcase.MenuClickAPITest;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;


	@RunWith(Suite.class)
	@SuiteClasses(
			{	
				CustomFieldCreateTest.class,
				SurveyCreateTest.class,
				SurveyUpdateTest.class,
				FormEventCreateTest.class,	
				Html5CreateTest.class,
				MenuAllDeleteTest.class,
				MenuSettingTest.class,
				FollowerProfileCleanTags.class,
			    FollowerOldTagBeforeTrigger.class,
				MenuClickAPITest.class,
			    FollowerProfileTagCheck.class,
			    FormEventPartiTest.class
			}
			)

	public class MenuAllTests {
	
	}
	