package com.hu.testcase;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.interactive.testcase.FollowThroughScanQrcode;
import com.interactive.testcase.MenuClickAPITest;
import com.interactive.testcase.MenuInfoAndSingleClick;
import com.interactive.testcase.QrCodeScan;
import com.interactive.testcase.SendMessageInteractive;


@RunWith(Suite.class)
@SuiteClasses({ 
	
	    //AnyMenuCon2016-07-05 15:53 -- Result 触发了 计数了但是没有record
	    FollowerProfileCleanTags.class,
	    FollowerOldTagBeforeTrigger.class,
	    TriggerAnyActionMultiConditionalDelay.class,
	    SendMessageInteractive.class,
	    MenuInfoAndSingleClick.class,
//	    FollowerSubscribeInfo.class,
	    FollowThroughScanQrcode.class,
	    QrCodeScan.class,
	    TriggerActionRecord.class,
	    
		TriggerAnyActionMultiOnceImmediately.class,
	    SendMessageInteractive.class,
	    MenuInfoAndSingleClick.class,
	    QrCodeScan.class,
	    SendMessageInteractive.class,
	    MenuInfoAndSingleClick.class,
	    QrCodeScan.class,
	    TriggerActionRecord.class,
	    
		TriggerAnyMenuMultiConditionalDelay.class,
		MenuInfoAndSingleClick.class,
		TriggerActionRecord.class,
		
		TriggerAnyMenuMultiImmediately.class,
		MenuInfoAndSingleClick.class,
		MenuInfoAndSingleClick.class,
		TriggerActionRecord.class,
		
		TriggerAnyQrCodeExistConditionalDelay.class,
		QrCodeScan.class,
		FollowThroughScanQrcode.class,
		TriggerActionRecord.class,
		
		TriggerAnyQrCodeExNewImmediately.class,
		QrCodeScan.class,
		FollowThroughScanQrcode.class,
		TriggerActionRecord.class,
		
		TriggerAnyQrCodeNewConditionalDelay.class,
		FollowThroughScanQrcode.class,
		TriggerActionRecord.class,
		
		TriggerSpecificMenuConditionalDelay.class,
		MenuInfoAndSingleClick.class,
		TriggerActionRecord.class,
		
		TriggerSpecificMenuOnceImmediately.class,
		MenuInfoAndSingleClick.class,
		MenuInfoAndSingleClick.class,
		TriggerActionRecord.class,
		
		TriggerSpecificQrCodeExistOnceDelay.class,
		FollowThroughScanQrcode.class,
		FollowThroughScanQrcode.class,
		QrCodeScan.class,
		QrCodeScan.class,
		TriggerActionRecord.class,
		
		TriggerSpecificQrCodeExNewConImmedia.class,
		QrCodeScan.class,
		FollowThroughScanQrcode.class,
		TriggerActionRecord.class,
		
		TriggerSpecificQrCodeNewDelay.class,
		QrCodeScan.class,
		FollowThroughScanQrcode.class,
		TriggerActionRecord.class,
/*	QrCodeScan.class,
    SendMessageInteractive.class,
    MenuInfoAndSingleClick.class,
    FollowerSubscribeInfo.class,
    FollowThroughScanQrcode.class,*/
	
	FollowerProfileTagCheck.class
   
		
		})
public class TriggerAllTests {

}
