package com.gy.testcase;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import com.hu.testcase.*;

@RunWith(Suite.class)
@SuiteClasses({
	WaterMark.class,
	ImportPost.class,
	PostItemsLimit.class,
	
	CreateReplysPost.class,
	CreateSinglePost.class,
	SendPreview.class,
	Send48Post.class,
	CreateReplymPost.class,
	Verify48Post.class,
	
	//PollCreateTest.class,//huhuan的poll
	CreateMultiPost.class,
	AddMultiPostSummary.class,
	SendPreviewPost.class,
	SendTagPreviewPost.class,
	SendTagPreviewVerify.class,
	
	SendPreviewText.class,
	//SendGroupPost.class,//all follower群发
	//SchedulePost.class,  //segmentation定时群发post
	Schedule48Text.class,
	SendPreviewImage.class,
})

public class SuitePost {

}
