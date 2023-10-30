package com.gy.testcase;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import com.hu.testcase.*;

@RunWith(Suite.class)
@SuiteClasses({
				
			WelcomMessage.class,
			WelcomeMessageInteractive.class,
  	
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
	
			NewEditorSinglePost.class,
			NewEditorSingleReplyPost.class,//暂时internal name去掉引号，等bug改好了再替换下来
			NewEditorMultiReplyPost.class,//暂时internal name去掉引号，等bug改好了再替换下来
			NewEditorMultiPost.class,
	
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
			KeywordImageCloseInteractive.class, //callback账号上就算关了也能拿到返回值，但是实际上并没有触发
	
			WelcomePost.class,
			WelcomePostInteractive.class,
			WelcomeImage.class,
			WelcomeImageInteractive.class,
			WelcomeVideo.class,
			WelcomeVideoInteractive.class,
			WelcomeVoice.class,
			WelcomeVoiceInteractive.class,
			WelcomeMessageDefaultInteractive.class,
	
			CloseOldTrigger.class,                                                          
			/*LBSTriggerOnce.class,
			LBSTriggerOnceInteractive.class,
			UpdateLBSTriggerToNoOnce.class,
			CloseLBSTrigger.class,  */                                                 
			Segmentation48.class,
			MessageTriggerPMI.class,
			MessageTriggerPMInteractive.class,
			SegmentationChina.class,
			UpdateTriggerPMISegmentation.class,
			UpdatePMINoCondition.class,
			CloseTriggerPMI.class,
			DirectFollowTriggerIMP.class,
			DirectFollowTriggerInteractive.class,
			SegmentationTag.class,
			DirectFollowConditionTrigger.class,
			DirectFollowNotInConditionTrigger.class,
			AnyFollowTrigger.class,
			AnyFollowTriggerSearchFollowInteractive.class,
			AnyFollowTriggerQRcodeFollow.class,                                            
	
			FollowerListReply.class,
			ExFollower.class,
			SendPreviewText.class,
			//SendGroupPost.class,//all follower群发
			//SchedulePost.class,  //segmentation定时群发post
			Schedule48Text.class,
			SegmentationATesting.class,
			SegmentationLBS.class,
			//SegmentationSumLBS.class,
				
				})
public class SuiteSubscription {

}
