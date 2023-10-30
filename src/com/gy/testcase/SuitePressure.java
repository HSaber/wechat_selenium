package com.gy.testcase;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
	PressKeywordImage.class,
	PressKeywordPost.class,
	PressKeywordText.class,
	PressKeywordVoice.class,
	PressDirectFollowSegmentationA.class,
	PressDirectFollowSegmentationB.class,
	PressDirectFollowSegmentationC.class,
	PressDirectFollowTriggerA.class,
	PressDirectFollowTriggerB.class,
	PressDirectFollowTriggerC.class,
	PressScanFollowQRcode.class,
	PressScanFollowTrigger.class,
	
})

public class SuitePressure {

}
