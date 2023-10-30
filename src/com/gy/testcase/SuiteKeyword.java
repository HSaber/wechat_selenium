package com.gy.testcase;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
	CloseOldKeyword.class,
	KeywordText.class,
	KeywordPost.class,
	KeywordTextInteractive.class,
	KeywordPostInteractive.class,
	KeywordImage.class,
	KeywordImageInteractive.class,
	KeywordVideo.class,
	KeywordVideoInteractive.class,
	KeywordVoice.class,
	KeywordVoiceInteractive.class,
	KeywordImageCloseInteractive.class 
	
	
})

public class SuiteKeyword {

}
