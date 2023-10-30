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
	DefaultSpaceInteractive.class
	
})

public class SuiteDefaultMessage {

}
