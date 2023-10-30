package com.gy.testcase;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
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
})

public class SuiteWelcomeMessage {

}
