package com.hu.testcase;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.interactive.testcase.HCSpecialMessage;
import com.interactive.testcase.MenuPersonalClickAPI;
import com.interactive.testcase.MenuPersonalInfoQuery;

@RunWith(Suite.class)
@SuiteClasses(
		{	
		//	BackUpAllNeededCases.class,
			MenuPerCleanOldVersion.class,
			MenuPersonalGY.class,
			MenuPersonalHC.class,
			MenuPersonalCopyDefault.class,	
			MenuEditDefaultCopyMenu.class,
			HCSpecialMessage.class,
			MenuPersonalPublish.class,
			FollowerProfileCleanTags.class,
		    FollowerOldTagBeforeTrigger.class,
			FollowerProfileTagCheck.class,
			MenuPersonalInfoQuery.class,
			MenuPersonalClickAPI.class
		}
		)
public class PersonalMenuAllTests {

}
