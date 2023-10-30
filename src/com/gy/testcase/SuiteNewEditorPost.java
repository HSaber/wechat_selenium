package com.gy.testcase;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
	NewEditorSinglePost.class,
	NewEditorSingleReplyPost.class,
	NewEditorMultiReplyPost.class,
	NewEditorMultiPost.class
})

public class SuiteNewEditorPost {

}
