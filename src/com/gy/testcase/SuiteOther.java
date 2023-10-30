package com.gy.testcase;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({

	FollowerListReply.class,
	ExFollower.class,
	
	SegmentationATesting.class,
	SegmentationLBS.class,
	SegmentationSumLBS.class,
	
	//UnsubscribeInteractive.class,
	//SubscribeInteractive.class,
	
	//Login.class
})

public class SuiteOther {

}
