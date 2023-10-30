package com.gy.testcase;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
	MessageInbox.class,
	DefaultMessageText.class,
	DefaultTextInteractive.class,
	DefaultMessagePost.class,
	DefaultMessagePostInteractive.class,
	DefaultVideo.class,
	DefaultVideoInteractive.class,
	DefaultVoice.class,
	DefaultVoiceInteractive.class,
	DefaultMessageImage.class,
	DefaultImageInteractive.class,
	DefaultImageOffInteractive.class,
	DefaultMessageSpace.class,
	DefaultSpaceInteractive.class,
	
	WelcomMessage.class,
	WelcomeMessageInteractive.class,
	WelcomeImage.class,
	WelcomeImageInteractive.class,
	WelcomeVideo.class,
	WelcomeVideoInteractive.class,
	WelcomeVoice.class,
	WelcomeVoiceInteractive.class,
	WelcomeMessageDefaultInteractive.class,
	WelcomePost.class,
	WelcomePostInteractive.class,
	
	FollowerListReply.class,
	ExFollower.class,
	
	SegmentationATesting.class,
	SegmentationLBS.class
	//SegmentationSumLBS.class//现在empty被去掉了，Bug改好了再运行此脚本
})

public class SuiteAuotoResponder {

}
