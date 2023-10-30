package com.hu.testcase;

import java.text.SimpleDateFormat;
import java.util.Timer;
import java.util.TimerTask;

import junit.framework.Test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.interactive.testcase.CouponStatusAPITest;
import com.interactive.testcase.FollowThroughScanQrcode;
import com.interactive.testcase.MenuClickAPITest;
import com.interactive.testcase.QrCodeScan;
import com.interactive.testcase.UnFollow;

import bsh.ParseException;


@RunWith(Suite.class)
@SuiteClasses(
		{	
//		LoginTest.class,
		PollCreateTest.class,
		PollUpdateTest.class,
		BackUpAllNeededCases.class,

		TagCreateTest.class,	


		CustomFieldCreateTest.class,
		SurveyCreateTest.class,
		SurveyUpdateTest.class,
		
        QrCodeTextTest.class,
        QrcodeImage.class,
		

		FormEventCreateTest.class,	
		FormEventUpdateTest.class,
		
		FollowerProfileCleanTags.class,
		FollowerProfileCustomTest.class,
		FollowerProfileTagTest.class,
		
		Html5CreateTest.class,
		Html5ActionTest.class,
		Html5UpdateTest.class,
	    FollowerOldTagBeforeTrigger.class,
		CustomDataCallBack.class,
		CustomDataReportTest.class,
//		SegmentationTest.class,
		MenuAllDeleteTest.class,
		MenuSettingTest.class,
		MenuClickAPITest.class,
		
		FormEventPartiTest.class,
		FormEventDataTest.class,
		
		SurveyJoinedDataTest.class,	
		
		TagUpdateTest.class,
		
		UnFollow.class,
//		PollParti.class,//app
//		PollAnalyticsTest.class,
        FollowThroughScanQrcode.class,
		
//		SegmentationCFDTest.class,
		
		
//		FollowerProfileTagCheck.class,
		FollowerProfileMessageTest.class,

//		ActionRecordTest.class,
	    FollowerProfileTagCheck.class,
	    
		}
		)

public class TestALL {
	/*
	public static Test suite() throws Exception{
		
		//$Junit-BEGIN$
		//$JUNIT-END$
		return suite();
	}
*/

}