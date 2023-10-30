package com.gy.testcase;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
	CloseOldTrigger.class,                                                          
	LBSTriggerOnce.class,
	LBSTriggerOnceInteractive.class,
	UpdateLBSTriggerToNoOnce.class,
	LBSTriggerNoOnceInteractive.class,
	CloseLBSTrigger.class,                                                  
	Segmentation48.class,
	MessageTriggerPMI.class,               //暂时改成1s，等bug改好了再恢复成5s
	MessageTriggerPMInteractive.class,
	SegmentationChina.class,
	UpdateTriggerPMISegmentation.class,
	ChinaPMITriggerInteractive.class,
	UpdatePMINoCondition.class,
	PMINoConditionTriggerInteravtive.class,
	CloseTriggerPMI.class,
	DirectFollowTriggerIMP.class,
	DirectFollowTriggerInteractive.class,
	SegmentationTag.class,
	DirectFollowConditionTrigger.class,
	DirectFollowConditionTriggerInteractive.class,
	DirectFollowNotInConditionTrigger.class,
	DirectFollowNotInCondtionInteractive.class,
	AnyFollowTrigger.class,
	AnyFollowTriggerSearchFollowInteractive.class,
	AnyFollowTriggerQRcodeFollow.class,
	ImageTriggerMIP.class,
	ImageTriggerMIPInteractive.class,
	ImageTriggerUpdate48.class,
	ImageTrigger48Interactive.class,
	ImageTriggerUpdateChina.class,
	ImageTriggerChinaInteractive.class,
	CloseImageTriggerInteractive.class
})

public class SuiteTrigger {

}
