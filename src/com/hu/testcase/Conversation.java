package com.hu.testcase;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.Assert;
import org.testng.AssertJUnit;

import java.awt.MouseInfo;
import java.awt.Point;
import java.io.File;
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;

import org.junit.*;

import static org.hamcrest.CoreMatchers.*;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class Conversation {
  WebDriver driver; 
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

	@BeforeClass
	public void setUpBeforeClass() throws Exception {
		driver= new ChromeDriver();
		methods.account_login(driver);
	}

	@AfterClass
	public void tearDownAfterClass() throws Exception {
			driver.close();
	}

  @Test
  public void test() throws InterruptedException,Exception {
	/* Actions builder=new Actions(driver);
	 builder.moveToElement(driver.findElement(By.linkText("Engagement"))).click().perform();
	 try{
	  driver.findElement(By.linkText("Conversation")).click();
      while(true){
    	  if(methods.isElementPresent(driver, By.id("conversation-grid")))
    		  break;
      }
    }catch(Exception e){
    	System.out.println("Fail to open");
    }*/
	  driver.get("https://dev.jingsocial.com/Manager/conversation/admin/category/conversation");
	  while(true){
    	  if(methods.isElementPresent(driver, By.id("conversation-grid")))
    		  break;
      }
	  //NormalConversation Don't Edit this one.2016-11-25 12:01
	 driver.findElement(By.name("Conversation[name]")).sendKeys("Conversation探索");
	 driver.findElement(By.name("Conversation[name]")).sendKeys(Keys.ENTER);
	 Thread.sleep(2000);
	 driver.findElement(By.className("view")).click();
  /*  driver.findElement(By.className("btn")).click();
    while(true){
    	if(methods.isElementPresent(driver, By.id("Conversation_name")))
    		break;
    }
    driver.findElement(By.id("Conversation_name")).clear();
    driver.findElement(By.id("Conversation_name")).sendKeys("NormalConversation Don't Edit this one."+methods.timeDate());*/
  //  driver.findElement(By.cssSelector("span.switch-label.Conversation_status")).click();
    //out of option
 /*   new Select(driver.findElement(By.id("ConversationRepliesOutrangeReplyTypeSwi"))).selectByVisibleText("Only Send Message");
    driver.findElement(By.cssSelector("#ConversationRepliesOutrange1editor > div.reply-content-type-tabs.tabarea > ul.replyContentType.relpyType > li.seleted > a.mpnews")).click();  
    driver.findElement(By.id("ConversationRepliesOutrange1replayContent")).click();
    driver.findElement(By.id("ConversationRepliesOutrange1replayContent")).clear();
    driver.findElement(By.id("ConversationRepliesOutrange1replayContent")).sendKeys("您的回答不在我们可以接受的范围内 麻烦再重新看下");
    driver.findElement(By.xpath("//div[@id='outRangeAdd']/span[2]")).click();
    Thread.sleep(1000);
    driver.findElement(By.xpath(".//*[@id='ConversationRepliesOutrange2editor']/div[1]/ul/li[1]/a")).click();
    new Select(driver.findElement(By.id("ConversationRepliesOutrange2Selector"))).selectByVisibleText("multi'post多图文“测试”selenium");
    Thread.sleep(1000);
    // no answer
    new Select(driver.findElement(By.id("ConversationRepliesNoanswer1Time"))).selectByVisibleText("15");
    driver.findElement(By.id("ConversationRepliesNoanswer1replayContent")).clear();
    driver.findElement(By.id("ConversationRepliesNoanswer1replayContent")).sendKeys("Basic Setting No answer:  {{nickname}},{{time}}好，您已经在十五秒后都没有理我们了哦。\n          麻烦跟我们沟通完呗，这样好让我们更好的服务于我们的客户。");
    driver.findElement(By.id("noAnswerAdd")).click();
    Thread.sleep(1000);
    driver.findElement(By.xpath(".//*[@id='ConversationRepliesNoanswer2editor']/div[1]/ul/li[2]/a")).click();
    new Select(driver.findElement(By.id("ConversationRepliesNoanswer2Time"))).selectByVisibleText("5");
    driver.findElement(By.id("ConversationRepliesNoanswer2replayContent")).clear();
    driver.findElement(By.id("ConversationRepliesNoanswer2replayContent")).sendKeys("Please ！！！！！\n                                 (ಥ _ ಥ)  \n                                                                   _(:зゝ∠)_");
    driver.findElement(By.id("noAnswerAdd")).click();
    Thread.sleep(1000);
    driver.findElement(By.xpath(".//*[@id='ConversationRepliesNoanswer3editor']/div[1]/ul/li[1]")).click();
    new Select(driver.findElement(By.id("ConversationRepliesNoanswer3Time"))).selectByVisibleText("5");
//   driver.findElement(By.cssSelector("#ConversationRepliesNoanswer3editor > div.reply-content-type-tabs.tabarea > ul.replyContentType.relpyType > li > a.mpnews")).click();
    driver.findElement(By.id("ConversationRepliesNoanswer3Selector")).click();
    new Select(driver.findElement(By.id("ConversationRepliesNoanswer3Selector"))).selectByVisibleText("single'post单图文“测试” selenium");*/
       
    //isonce
/*    driver.findElement(By.xpath(".//*[@id='conversation-form']/div/div[3]/div/label/span[1]")).click();
    driver.findElement(By.id("ConversationRepliesAlready1replayContent")).clear();
    driver.findElement(By.id("ConversationRepliesAlready1replayContent")).sendKeys("您已经参加过该Conversation了 如果有其他疑问可以点击 下面的链接\nhttp://www.baidu.com\n谢谢您{{nickname}} 参加。\n五秒后我们会给您发送一个post 可以提供一些您可能需要的信息。");
    driver.findElement(By.xpath("//div[@id='alreadyParticipatedAdd']/span[2]")).click();
    driver.findElement(By.id("ConversationRepliesAlready21replyContentType")).click();
    new Select(driver.findElement(By.id("ConversationRepliesAlready2Time"))).selectByVisibleText("5");
    driver.findElement(By.cssSelector("#ConversationRepliesAlready2EditorItem > div.reply-content-type-tabs.tabarea > ul.replyContentType.relpyType > li > a.mpnews")).click();
    driver.findElement(By.id("ConversationRepliesAlready22replyContentType")).click();
    new Select(driver.findElement(By.id("ConversationRepliesAlready2Selector"))).selectByVisibleText("multi'post多图文“测试”selenium");
    */
    
  /*  driver.findElement(By.id("ConversationRepliesOutrange1jsEditor")).sendKeys("Test Out");
    driver.findElement(By.id("ConversationRepliesNoanswer1jsEditor")).sendKeys("Test No Answer");
   driver.findElement(By.name("Conversation[next]")).click();*/
//    basic setting 结束
    Thread.sleep(3000);
   // Actions action = new Actions(driver);
  //  action.doubleClick(driver.findElement(By.cssSelector("canvas")));
    String styleStr=driver.findElement(By.xpath(".//*[@id='myDiagramDiv']/canvas")).getAttribute("style");
    System.out.println(styleStr);
    String[] NumArr=methods.Getnum(styleStr);
    int width = 0,height = 0;
    for(int i=0;i<NumArr.length;i++){
    	int result=Integer.parseInt(NumArr[i]);
    	if(result>1000)
    		width=result;
    	else if(result>100)
    		height=result;
    }
    System.out.println(width+"  "+height);
/*    org.openqa.selenium.Point str=driver.findElement(By.linkText("EIP Management")).getLocation();
    System.out.println(str.x+" "+str.y);
    Actions action = new Actions(driver);
     action.moveByOffset(0,0).contextClick().perform();
    Thread.sleep(3000);
    action.moveToElement(driver.findElement(By.id("myDiagramDiv")), width/2, height/2+5).doubleClick();
    Point point=MouseInfo.getPointerInfo().getLocation();
    System.out.println(width/2+"  "+height/2);
    System.out.println(point.x+"  "+point.y);*/
    driver.findElement(By.linkText("hello")).click();
/*    String add=driver.getCurrentUrl();
    String add1="https://dev.jingsocial.com/Manager/conversation/tree/id/";
    System.out.println(add1.length());
    String top=add.substring(56);
    top="https://dev.jingsocial.com/Manager/cquestion/create/cid/"+top+"/top/1";
    driver.get(top);
    Thread.sleep(5000);*/
  
    driver.findElement(By.className("msgName")).sendKeys("喜欢的手机品牌");

    driver.findElement(By.id("ConversationMessageAlready1replayContent")).clear();
    driver.findElement(By.id("ConversationMessageAlready1replayContent")).sendKeys("{{nickname}} 我们通过微信已经获取了您的基本信息，然后呢 下面我们会有一个对话形式的调研");
    driver.findElement(By.cssSelector("section.addMobileCon.add_question")).click();
    new Select(driver.findElement(By.id("ConversationMessage_0_time"))).selectByVisibleText("5");
    driver.findElement(By.id("ConversationMessageAlready1replayContent")).clear();
    driver.findElement(By.id("ConversationMessageAlready1replayContent")).sendKeys("如果您想买手机或更换手机，您会选哪个品牌");
    driver.findElement(By.cssSelector("section.addMobileCon.add_question > svg.svgIcon")).click();
    driver.findElement(By.cssSelector("div.treeBox.on > div.converBox")).click();
    driver.findElement(By.linkText("Image")).click();
	String parentPath = getClass().getResource("../../").getFile().toString();

	String parentPath1 = parentPath + "/material/21.JPG";
	String parentPath2 = parentPath + "/material/20.JPG";
	String parentPath3 = parentPath + "/material/22.JPG";
	String parentPath4 = parentPath + "/material/23.JPG";
	File f1 = new File(parentPath1);
	File f2 = new File(parentPath2);
	File f3 = new File(parentPath3);
	File f4 = new File(parentPath4);
    driver.findElement(By.id("ConversationMessage_Already_1_file")).sendKeys(f1.toString());   
    Thread.sleep(4000);
    

    
    driver.findElement(By.cssSelector("section.addMobileCon.add_question > svg.svgIcon")).click();
    driver.findElement(By.linkText("Image")).click();
    driver.findElement(By.id("ConversationMessage_Already_1_file")).sendKeys(f3.toString());
    Thread.sleep(3000);
    
    driver.findElement(By.cssSelector("section.addMobileCon.add_question > svg.svgIcon")).click();
    driver.findElement(By.linkText("Image")).click();
    driver.findElement(By.id("ConversationMessage_Already_1_file")).sendKeys(f4.toString());
    Thread.sleep(3000);
    
    driver.findElement(By.cssSelector("section.addMobileCon.add_question")).click();
    driver.findElement(By.cssSelector("div.treeBox.on > div.time")).click();
    driver.findElement(By.linkText("Message")).click();
    driver.findElement(By.id("ConversationMessageAlready1replayContent")).clear();
    driver.findElement(By.id("ConversationMessageAlready1replayContent")).sendKeys("这是我们网站的链接\n您可以参照一下 看看您喜欢的手机\nhttp://www.baidu.com");

    //出错
/*    new Select(driver.findElement(By.id("ConversationMessage_0_time"))).selectByVisibleText("5");
    driver.findElement(By.xpath("//div[@id='message_template']/div[7]/div[2]")).click();
    driver.findElement(By.id("ConversationMessageAlready11replyContentType")).click();

    driver.findElement(By.linkText("Post")).click();
    new Select(driver.findElement(By.id("ConversationMessage_0_time"))).selectByVisibleText("5");
    new Select(driver.findElement(By.id("ConversationMessageAlready1Selector"))).selectByVisibleText("multi'post多图文“测试”selenium");

    

   */ 

    driver.findElement(By.linkText("Setting")).click();
    Thread.sleep(100000);
    driver.findElement(By.name("yt1")).click();
    Thread.sleep(30000);



    driver.findElement(By.linkText("Image")).click();
    driver.findElement(By.id("ConversationMessageAlready13replyContentType")).click();
    driver.findElement(By.id("ConversationMessage_Already_1_file")).clear();
    driver.findElement(By.id("ConversationMessage_Already_1_file")).sendKeys("");
    driver.findElement(By.cssSelector("section.addMobileCon.add_question")).click();
    driver.findElement(By.cssSelector("div.treeBox.on > div.converBox")).click();
    driver.findElement(By.linkText("Message")).click();
    driver.findElement(By.id("ConversationMessageAlready11replyContentType")).click();
    driver.findElement(By.id("ConversationMessageAlready1replayContent")).clear();
    driver.findElement(By.id("ConversationMessageAlready1replayContent")).sendKeys("{{nickname}} 你喜欢这款手机吗？\n\nhttp://product.suning.com/0000000000/151939313.html?srcpoint=shouji_newpage_97723392_prod02");
    driver.findElement(By.cssSelector("div.treeBox.on > div.converBox > div.questionMessage")).click();
    driver.findElement(By.cssSelector("section.addMobileCon.add_question > svg.svgIcon > use")).click();
    new Select(driver.findElement(By.id("ConversationMessage_0_time"))).selectByVisibleText("5");
    driver.findElement(By.linkText("Image")).click();
    driver.findElement(By.id("ConversationMessageAlready13replyContentType")).click();
    driver.findElement(By.id("ConversationMessage_Already_1_file")).clear();
    driver.findElement(By.id("ConversationMessage_Already_1_file")).sendKeys("");
    driver.findElement(By.linkText("Path")).click();
    driver.findElement(By.id("QuestionPath_0_keyword")).clear();
    driver.findElement(By.id("QuestionPath_0_keyword")).sendKeys("门店");
    new Select(driver.findElement(By.name("QuestionPath[0][matchType]"))).selectByVisibleText("Full Matching");
    driver.findElement(By.xpath("//form[@id='conversation-question-form']/div/ul/li[2]/div[3]/span[2]")).click();
    driver.findElement(By.id("QuestionPath_1_keyword")).clear();
    driver.findElement(By.id("QuestionPath_1_keyword")).sendKeys("网站购买");
    driver.findElement(By.xpath("(//a[contains(text(),'Tags')])[2]")).click();
    driver.findElement(By.linkText("Any Reply Tag")).click();
    driver.findElement(By.cssSelector("li.seleted > #ConversationQuestion_tag_type")).click();
    driver.findElement(By.xpath("(//input[@type='text'])[6]")).click();
    driver.findElement(By.linkText("Setting")).click();
    driver.findElement(By.xpath("//form[@id='conversation-question-form']/div/ul/li[4]/div[2]/ul/img")).click();
    driver.findElement(By.id("ConversationQuestion_reply_time")).clear();
    driver.findElement(By.id("ConversationQuestion_reply_time")).sendKeys("10");
    driver.findElement(By.name("yt1")).click();
    driver.findElement(By.cssSelector("canvas")).click();
    driver.findElement(By.id("ConversationMessageAlready1replayContent")).clear();
    driver.findElement(By.id("ConversationMessageAlready1replayContent")).sendKeys("您选择门店的话我们这边可以帮忙提供服务。");
    driver.findElement(By.cssSelector("section.addMobileCon.add_question > svg.svgIcon > use")).click();
    driver.findElement(By.id("ConversationMessageAlready1replayContent")).clear();
    driver.findElement(By.id("ConversationMessageAlready1replayContent")).sendKeys("您的姓名是？");
    driver.findElement(By.linkText("Path")).click();
    driver.findElement(By.linkText("Continue Without Keywords")).click();
    driver.findElement(By.cssSelector("li.seleted > #ConversationQuestion_path_type")).click();
    driver.findElement(By.name("yt1")).click();
    driver.findElement(By.id("ConversationMessageAlready1replayContent")).clear();
    driver.findElement(By.id("ConversationMessageAlready1replayContent")).sendKeys("您的手机号码？");
    driver.findElement(By.linkText("Path")).click();
    driver.findElement(By.linkText("Continue Without Keywords")).click();
    driver.findElement(By.cssSelector("li.seleted > #ConversationQuestion_path_type")).click();
    driver.findElement(By.name("yt1")).click();
    driver.findElement(By.id("ConversationMessageAlready1replayContent")).clear();
    driver.findElement(By.id("ConversationMessageAlready1replayContent")).sendKeys("我们已经知道您的联系方式 会最快联系您\n\n\n谢谢您的时间。");
    driver.findElement(By.cssSelector("section.addMobileCon.add_question")).click();
    driver.findElement(By.cssSelector("time")).click();
    new Select(driver.findElement(By.id("ConversationMessage_0_time"))).selectByVisibleText("5");
    driver.findElement(By.linkText("Image")).click();
    driver.findElement(By.id("ConversationMessageAlready13replyContentType")).click();
    driver.findElement(By.id("ConversationMessage_Already_1_file")).clear();
    driver.findElement(By.id("ConversationMessage_Already_1_file")).sendKeys("");
    driver.findElement(By.linkText("Path")).click();
    driver.findElement(By.linkText("End The Conversation")).click();
    driver.findElement(By.cssSelector("li.seleted > #ConversationQuestion_path_type")).click();
    driver.findElement(By.xpath("(//a[contains(text(),'Tags')])[2]")).click();
    driver.findElement(By.linkText("Setting")).click();
    driver.findElement(By.name("yt1")).click();
    driver.findElement(By.cssSelector("div.questionMessage")).click();
    driver.findElement(By.id("ConversationMessageAlready1replayContent")).clear();
    driver.findElement(By.id("ConversationMessageAlready1replayContent")).sendKeys("网店购买的话 您是倾向于下面哪些网站呢\n1. 官网\n2. 淘宝\n3. 京东\n4. 苏宁");
    driver.findElement(By.cssSelector("section.addMobileCon.add_question")).click();
    driver.findElement(By.cssSelector("div.time")).click();
    new Select(driver.findElement(By.id("ConversationMessage_0_time"))).selectByVisibleText("5");
    driver.findElement(By.linkText("Image")).click();
    driver.findElement(By.id("ConversationMessageAlready13replyContentType")).click();
    driver.findElement(By.id("ConversationMessage_Already_1_file")).clear();
    driver.findElement(By.id("ConversationMessage_Already_1_file")).sendKeys("");
    driver.findElement(By.linkText("Path")).click();
    new Select(driver.findElement(By.name("QuestionPath[0][matchType]"))).selectByVisibleText("Full Matching");
    driver.findElement(By.id("QuestionPath_0_keyword")).clear();
    driver.findElement(By.id("QuestionPath_0_keyword")).sendKeys("1");
    driver.findElement(By.xpath("//form[@id='conversation-question-form']/div/ul/li[2]/div[3]/span[2]")).click();
    new Select(driver.findElement(By.name("QuestionPath[1][matchType]"))).selectByVisibleText("Full Matching");
    driver.findElement(By.id("QuestionPath_1_keyword")).clear();
    driver.findElement(By.id("QuestionPath_1_keyword")).sendKeys("2");
    driver.findElement(By.cssSelector("div.path_next")).click();
    new Select(driver.findElement(By.name("QuestionPath[2][matchType]"))).selectByVisibleText("Full Matching");
    driver.findElement(By.id("QuestionPath_2_keyword")).clear();
    driver.findElement(By.id("QuestionPath_2_keyword")).sendKeys("3");
    driver.findElement(By.xpath("//form[@id='conversation-question-form']/div/ul/li[2]/div[3]/span[2]")).click();
    new Select(driver.findElement(By.name("QuestionPath[3][matchType]"))).selectByVisibleText("Full Matching");
    driver.findElement(By.id("QuestionPath_3_keyword")).clear();
    driver.findElement(By.id("QuestionPath_3_keyword")).sendKeys("4");
    driver.findElement(By.linkText("Setting")).click();
    driver.findElement(By.cssSelector("ul.radioUl > img")).click();
    driver.findElement(By.name("yt1")).click();
    driver.findElement(By.id("ConversationMessageAlready1replayContent")).clear();
    driver.findElement(By.id("ConversationMessageAlready1replayContent")).sendKeys("OK ，您选择了小米，并且是网站，而且选择了1这个方式进行购买。\n\nThankYouForYourTime!");
    driver.findElement(By.linkText("Path")).click();
    driver.findElement(By.linkText("End The Conversation")).click();
    driver.findElement(By.cssSelector("li.seleted > #ConversationQuestion_path_type")).click();
    driver.findElement(By.name("yt1")).click();
    driver.findElement(By.id("ConversationMessageAlready1replayContent")).clear();
    driver.findElement(By.id("ConversationMessageAlready1replayContent")).sendKeys("OK ，您选择了小米，并且是网站，而且选择了2这个方式进行购买。\n\nThankYouForYourTime!");
    driver.findElement(By.linkText("Path")).click();
    driver.findElement(By.linkText("End The Conversation")).click();
    driver.findElement(By.cssSelector("li.seleted > #ConversationQuestion_path_type")).click();
    driver.findElement(By.name("yt1")).click();
    driver.findElement(By.id("ConversationMessageAlready1replayContent")).clear();
    driver.findElement(By.id("ConversationMessageAlready1replayContent")).sendKeys("OK ，您选择了小米，并且是网站，而且选择了3这个方式进行购买。\n\nThankYouForYourTime!");
    driver.findElement(By.linkText("Path")).click();
    driver.findElement(By.linkText("End The Conversation")).click();
    driver.findElement(By.cssSelector("li.seleted > #ConversationQuestion_path_type")).click();
    driver.findElement(By.name("yt1")).click();
    driver.findElement(By.cssSelector("canvas")).click();
    driver.findElement(By.id("ConversationMessageAlready1replayContent")).clear();
    driver.findElement(By.id("ConversationMessageAlready1replayContent")).sendKeys("OK ，您选择了小米，并且是网站，而且选择了4这个方式进行购买。\n\nThankYouForYourTime!");
    driver.findElement(By.linkText("Path")).click();
    driver.findElement(By.linkText("End The Conversation")).click();
    driver.findElement(By.cssSelector("li.seleted > #ConversationQuestion_path_type")).click();
    driver.findElement(By.name("yt1")).click();
    driver.findElement(By.id("ConversationMessageAlready1replayContent")).clear();
    driver.findElement(By.id("ConversationMessageAlready1replayContent")).sendKeys("OK ，您选择了小米，并且是网站\n但是您的选项出乎我们的选项了 麻烦您重新选择");
    driver.findElement(By.cssSelector("section.addMobileCon.add_question")).click();
    driver.findElement(By.cssSelector("div.treeBox.on > div.converBox")).click();
    driver.findElement(By.linkText("Image")).click();
    driver.findElement(By.id("ConversationMessageAlready13replyContentType")).click();
    new Select(driver.findElement(By.id("ConversationMessage_0_time"))).selectByVisibleText("5");
    driver.findElement(By.id("ConversationMessage_Already_1_file")).clear();
    driver.findElement(By.id("ConversationMessage_Already_1_file")).sendKeys("");
    driver.findElement(By.linkText("Path")).click();
    driver.findElement(By.linkText("End The Conversation")).click();
    driver.findElement(By.cssSelector("li.seleted > #ConversationQuestion_path_type")).click();
    driver.findElement(By.name("yt1")).click();
    driver.findElement(By.name("yt1")).click();
    driver.findElement(By.linkText("Setting")).click();
    driver.findElement(By.cssSelector("ul.radioUl > img")).click();
    driver.findElement(By.name("yt1")).click();
    driver.findElement(By.id("ConversationMessageAlready1replayContent")).clear();
    driver.findElement(By.id("ConversationMessageAlready1replayContent")).sendKeys("您的回答不在我们的选项中，我们会记录下您的渠道并且作为参考 \n谢谢您的合作。\n\n\n{{nickname}}");
    driver.findElement(By.cssSelector("section.addMobileCon.add_question")).click();
    driver.findElement(By.cssSelector("div.treeBox.on > div.converBox")).click();
    new Select(driver.findElement(By.id("ConversationMessage_0_time"))).selectByVisibleText("5");
    driver.findElement(By.linkText("Post")).click();
    driver.findElement(By.id("ConversationMessageAlready12replyContentType")).click();
    new Select(driver.findElement(By.id("ConversationMessageAlready1Selector"))).selectByVisibleText("multi'post多图文“测试”selenium");
    driver.findElement(By.linkText("Path")).click();
    driver.findElement(By.linkText("End The Conversation")).click();
    driver.findElement(By.cssSelector("li.seleted > #ConversationQuestion_path_type")).click();
    driver.findElement(By.name("yt1")).click();
    driver.findElement(By.id("ConversationMessageAlready1replayContent")).clear();
    driver.findElement(By.id("ConversationMessageAlready1replayContent")).sendKeys("您已经十秒钟没有回答了。\n特设");
    driver.findElement(By.cssSelector("section.addMobileCon.add_question")).click();
    driver.findElement(By.cssSelector("div.treeBox.on > div.converBox")).click();
    driver.findElement(By.linkText("Image")).click();
    driver.findElement(By.id("ConversationMessageAlready13replyContentType")).click();
    new Select(driver.findElement(By.id("ConversationMessage_0_time"))).selectByVisibleText("5");
    driver.findElement(By.id("ConversationMessage_Already_1_file")).clear();
    driver.findElement(By.id("ConversationMessage_Already_1_file")).sendKeys("");
    driver.findElement(By.cssSelector("section.addMobileCon.add_question > svg.svgIcon")).click();
    new Select(driver.findElement(By.id("ConversationMessage_0_time"))).selectByVisibleText("5");
    driver.findElement(By.linkText("Message")).click();
    driver.findElement(By.id("ConversationMessageAlready11replyContentType")).click();
    driver.findElement(By.id("ConversationMessageAlready1replayContent")).clear();
    driver.findElement(By.id("ConversationMessageAlready1replayContent")).sendKeys("那您喜欢其他的系统的手机吗？");
    driver.findElement(By.linkText("Path")).click();
    new Select(driver.findElement(By.name("QuestionPath[0][matchType]"))).selectByVisibleText("Full Matching");
    driver.findElement(By.id("QuestionPath_0_keyword")).clear();
    driver.findElement(By.id("QuestionPath_0_keyword")).sendKeys("ios");
    driver.findElement(By.xpath("//form[@id='conversation-question-form']/div/ul/li[2]/div[3]/span[2]")).click();
    driver.findElement(By.id("QuestionPath_1_keyword")).clear();
    driver.findElement(By.id("QuestionPath_1_keyword")).sendKeys("塞班");
    driver.findElement(By.xpath("(//a[contains(text(),'Tags')])[2]")).click();
    driver.findElement(By.id("QuestionTag_0_name")).clear();
    driver.findElement(By.id("QuestionTag_0_name")).sendKeys("ios");
    driver.findElement(By.linkText("Setting")).click();
    driver.findElement(By.name("yt1")).click();
    driver.findElement(By.name("yt1")).click();
    driver.findElement(By.id("ConversationMessageAlready1replayContent")).clear();
    driver.findElement(By.id("ConversationMessageAlready1replayContent")).sendKeys("您的调查结果是\n\n\n选择了小米却喜欢ios系统");
    driver.findElement(By.linkText("Path")).click();
    driver.findElement(By.linkText("End The Conversation")).click();
    driver.findElement(By.cssSelector("li.seleted > #ConversationQuestion_path_type")).click();
    driver.findElement(By.name("yt1")).click();
    driver.findElement(By.id("ConversationMessageAlready1replayContent")).clear();
    driver.findElement(By.id("ConversationMessageAlready1replayContent")).sendKeys("您的调查结果是\n\n\n选择了小米却喜欢塞班系统");
    driver.findElement(By.linkText("Path")).click();
    driver.findElement(By.linkText("End The Conversation")).click();
    driver.findElement(By.cssSelector("li.seleted > #ConversationQuestion_path_type")).click();
    driver.findElement(By.name("yt1")).click();
    driver.findElement(By.linkText("Image")).click();
    driver.findElement(By.id("ConversationMessageAlready13replyContentType")).click();
    driver.findElement(By.id("ConversationMessage_Already_1_file")).clear();
    driver.findElement(By.id("ConversationMessage_Already_1_file")).sendKeys("");
    driver.findElement(By.cssSelector("div.questionMessage > img")).click();
    driver.findElement(By.cssSelector("section.addMobileCon.add_question")).click();
    new Select(driver.findElement(By.id("ConversationMessage_0_time"))).selectByVisibleText("5");
    driver.findElement(By.linkText("Message")).click();
    driver.findElement(By.id("ConversationMessageAlready11replyContentType")).click();
    driver.findElement(By.id("ConversationMessageAlready1replayContent")).clear();
    driver.findElement(By.id("ConversationMessageAlready1replayContent")).sendKeys("{{nickname}} \n  您喜欢这款华为手机吗，详细的您可以查看一下这个网址：\nhttp://product.suning.com/0000000000/153589053.html?srcpoint=shouji_newpage_97723392_prod01");
    driver.findElement(By.cssSelector("div.questionMessage > img")).click();
    driver.findElement(By.id("ConversationMessageAlready13replyContentType")).click();
    driver.findElement(By.cssSelector("section.addMobileCon.add_question")).click();
    driver.findElement(By.cssSelector("div.treeBox.on > div.converBox")).click();
    new Select(driver.findElement(By.id("ConversationMessage_0_time"))).selectByVisibleText("5");
    driver.findElement(By.linkText("Image")).click();
    driver.findElement(By.id("ConversationMessageAlready13replyContentType")).click();
    driver.findElement(By.linkText("Image")).click();
    driver.findElement(By.id("ConversationMessageAlready13replyContentType")).click();
    driver.findElement(By.id("ConversationMessage_Already_1_file")).clear();
    driver.findElement(By.id("ConversationMessage_Already_1_file")).sendKeys("");
    driver.findElement(By.cssSelector("div.treeBox.on > div.converBox > div.questionMessage")).click();
    driver.findElement(By.cssSelector("section.addMobileCon.add_question > svg.svgIcon > use")).click();
    driver.findElement(By.linkText("Post")).click();
    driver.findElement(By.id("ConversationMessageAlready12replyContentType")).click();
    new Select(driver.findElement(By.id("ConversationMessageAlready1Selector"))).selectByVisibleText("reply'post多图文“测试” selenium");
    driver.findElement(By.linkText("Path")).click();
    driver.findElement(By.linkText("Continue Without Keywords")).click();
    driver.findElement(By.cssSelector("li.seleted > #ConversationQuestion_path_type")).click();
    driver.findElement(By.xpath("(//a[contains(text(),'Tags')])[2]")).click();
    driver.findElement(By.id("QuestionTag_0_name")).clear();
    driver.findElement(By.id("QuestionTag_0_name")).sendKeys("实体店");
    driver.findElement(By.xpath("(//input[@type='text'])[4]")).click();
    driver.findElement(By.linkText("Setting")).click();
    driver.findElement(By.xpath("//form[@id='conversation-question-form']/div/ul/li[4]/div[2]/ul/img")).click();
    driver.findElement(By.id("ConversationQuestion_reply_time")).clear();
    driver.findElement(By.id("ConversationQuestion_reply_time")).sendKeys("10");
    driver.findElement(By.name("yt1")).click();
    driver.findElement(By.cssSelector("section.addMobileCon.add_question")).click();
    driver.findElement(By.cssSelector("div.converBox")).click();
    driver.findElement(By.linkText("Post")).click();
    driver.findElement(By.id("ConversationMessageAlready12replyContentType")).click();
    new Select(driver.findElement(By.id("ConversationMessageAlready1Selector"))).selectByVisibleText("single'post单图文“测试” selenium");
    driver.findElement(By.cssSelector("section.addMobileCon.add_question > svg.svgIcon")).click();
    driver.findElement(By.cssSelector("div.time")).click();
    new Select(driver.findElement(By.id("ConversationMessage_0_time"))).selectByVisibleText("5");
    driver.findElement(By.linkText("Message")).click();
    driver.findElement(By.id("ConversationMessageAlready1replayContent")).clear();
    driver.findElement(By.id("ConversationMessageAlready1replayContent")).sendKeys("华为手机您最看好的是它的性能吗？");
    driver.findElement(By.linkText("Path")).click();
    driver.findElement(By.id("QuestionPath_0_keyword")).clear();
    driver.findElement(By.id("QuestionPath_0_keyword")).sendKeys("是");
    driver.findElement(By.xpath("//form[@id='conversation-question-form']/div/ul/li[2]/div[3]/span[2]")).click();
    driver.findElement(By.id("QuestionPath_1_keyword")).clear();
    driver.findElement(By.id("QuestionPath_1_keyword")).sendKeys("不是");
    driver.findElement(By.linkText("Editor")).click();
    driver.findElement(By.name("yt1")).click();
    driver.findElement(By.cssSelector("div.converBox")).click();
    driver.findElement(By.cssSelector("li.seleted")).click();
    driver.findElement(By.linkText("Post")).click();
    driver.findElement(By.id("ConversationMessageAlready12replyContentType")).click();
    new Select(driver.findElement(By.id("ConversationMessageAlready1Selector"))).selectByVisibleText("single'post单图文“测试” selenium");
    driver.findElement(By.cssSelector("section.addMobileCon.add_question")).click();
    driver.findElement(By.cssSelector("div.time")).click();
    new Select(driver.findElement(By.id("ConversationMessage_0_time"))).selectByVisibleText("5");
    driver.findElement(By.linkText("Message")).click();
    driver.findElement(By.id("ConversationMessageAlready1replayContent")).clear();
    driver.findElement(By.id("ConversationMessageAlready1replayContent")).sendKeys("华为手机您最看好的是它的性能吗？");
    driver.findElement(By.cssSelector("div.converBox")).click();
    driver.findElement(By.cssSelector("li.seleted")).click();
    driver.findElement(By.linkText("Message")).click();
    driver.findElement(By.cssSelector("img.i-img")).click();
    driver.findElement(By.cssSelector("img.i-img")).click();
    driver.findElement(By.linkText("Message")).click();
    driver.findElement(By.cssSelector("img.i-img")).click();
    driver.findElement(By.xpath("//div[@id='message_template']/div[2]/div[2]")).click();
    driver.findElement(By.cssSelector("img.i-img")).click();
    driver.findElement(By.xpath("//div[@id='message_template']/div[2]/div[2]")).click();
    driver.findElement(By.linkText("Message")).click();
    driver.findElement(By.name("yt1")).click();
    driver.findElement(By.name("yt1")).click();
    driver.findElement(By.linkText("Path")).click();
    driver.findElement(By.id("QuestionPath_0_keyword")).clear();
    driver.findElement(By.id("QuestionPath_0_keyword")).sendKeys("是");
    driver.findElement(By.xpath("//form[@id='conversation-question-form']/div/ul/li[2]/div[3]/span[2]")).click();
    driver.findElement(By.id("QuestionPath_1_keyword")).clear();
    driver.findElement(By.id("QuestionPath_1_keyword")).sendKeys("不是");
    driver.findElement(By.xpath("(//a[contains(text(),'Tags')])[2]")).click();
    driver.findElement(By.name("yt1")).click();
    driver.findElement(By.name("yt0")).click();
    driver.findElement(By.id("ConversationMessageAlready1replayContent")).clear();
    driver.findElement(By.id("ConversationMessageAlready1replayContent")).sendKeys("华为性能");
    driver.findElement(By.cssSelector("div.questionMessage")).click();
    driver.findElement(By.linkText("Post")).click();
    driver.findElement(By.id("ConversationMessageAlready12replyContentType")).click();
    new Select(driver.findElement(By.id("ConversationMessageAlready1Selector"))).selectByVisibleText("single'post单图文“测试” selenium");
    driver.findElement(By.cssSelector("section.addMobileCon.add_question")).click();
    new Select(driver.findElement(By.id("ConversationMessage_0_time"))).selectByVisibleText("5");
    driver.findElement(By.linkText("Message")).click();
    driver.findElement(By.linkText("Message")).click();
    driver.findElement(By.cssSelector("div.time")).click();
    driver.findElement(By.id("ConversationMessageAlready1replayContent")).clear();
    driver.findElement(By.id("ConversationMessageAlready1replayContent")).sendKeys("华为手机您最看好的是它的性能吗？");
    driver.findElement(By.cssSelector("div.converBox > span.del_message")).click();
    driver.findElement(By.cssSelector("section.addMobileCon.add_question > svg.svgIcon > use")).click();
    driver.findElement(By.linkText("Message")).click();
    driver.findElement(By.linkText("Path")).click();
    driver.findElement(By.id("QuestionPath_0_keyword")).clear();
    driver.findElement(By.id("QuestionPath_0_keyword")).sendKeys("是");
    driver.findElement(By.xpath("//form[@id='conversation-question-form']/div/ul/li[2]/div[3]/span[2]")).click();
    driver.findElement(By.id("QuestionPath_1_keyword")).clear();
    driver.findElement(By.id("QuestionPath_1_keyword")).sendKeys("不是");
    driver.findElement(By.name("yt1")).click();
    driver.findElement(By.name("yt0")).click();
    driver.findElement(By.id("ConversationMessageAlready1replayContent")).click();
    driver.findElement(By.id("ConversationMessageAlready1replayContent")).clear();
    driver.findElement(By.id("ConversationMessageAlready1replayContent")).sendKeys("华为手机您最看好的是它的性能吗？");
    driver.findElement(By.cssSelector("section.addMobileCon.add_question")).click();
    driver.findElement(By.cssSelector("div.treeBox.on > div.converBox")).click();
    new Select(driver.findElement(By.id("ConversationMessage_0_time"))).selectByVisibleText("5");
    driver.findElement(By.cssSelector("li.seleted")).click();
    driver.findElement(By.linkText("Post")).click();
    new Select(driver.findElement(By.id("ConversationMessageAlready1Selector"))).selectByVisibleText("single'post单图文“测试” selenium");
    driver.findElement(By.cssSelector("div.converBox > span.del_message")).click();
    driver.findElement(By.linkText("Path")).click();
    driver.findElement(By.id("QuestionPath_0_keyword")).clear();
    driver.findElement(By.id("QuestionPath_0_keyword")).sendKeys("是");
    driver.findElement(By.xpath("//form[@id='conversation-question-form']/div/ul/li[2]/div[3]/span[2]")).click();
    driver.findElement(By.id("QuestionPath_1_keyword")).clear();
    driver.findElement(By.id("QuestionPath_1_keyword")).sendKeys("不是");
    driver.findElement(By.xpath("(//a[contains(text(),'Tags')])[2]")).click();
    driver.findElement(By.name("yt1")).click();
    driver.findElement(By.name("yt1")).click();
    driver.findElement(By.linkText("Conversation")).click();
    driver.findElement(By.cssSelector("img[alt=\"View\"]")).click();
    driver.findElement(By.id("ConversationMessageAlready1replayContent")).clear();
    driver.findElement(By.id("ConversationMessageAlready1replayContent")).sendKeys("华为性能");
    driver.findElement(By.id("ConversationMessageAlready1replayContent")).clear();
    driver.findElement(By.id("ConversationMessageAlready1replayContent")).sendKeys("华为手机您最看好的是它的性能吗？");
    driver.findElement(By.linkText("Path")).click();
    driver.findElement(By.id("QuestionPath_0_keyword")).clear();
    driver.findElement(By.id("QuestionPath_0_keyword")).sendKeys("是");
    driver.findElement(By.xpath("//form[@id='conversation-question-form']/div/ul/li[2]/div[3]/span[2]")).click();
    driver.findElement(By.id("QuestionPath_1_keyword")).clear();
    driver.findElement(By.id("QuestionPath_1_keyword")).sendKeys("不是");
    driver.findElement(By.name("yt1")).click();
    driver.findElement(By.cssSelector("div.converBox")).click();
    driver.findElement(By.id("ConversationMessageAlready1replayContent")).clear();
    driver.findElement(By.id("ConversationMessageAlready1replayContent")).sendKeys("选择了华为 并且觉得性能不错");
    driver.findElement(By.cssSelector("section.addMobileCon.add_question")).click();
    new Select(driver.findElement(By.id("ConversationMessage_0_time"))).selectByVisibleText("5");
    driver.findElement(By.linkText("Message")).click();
    driver.findElement(By.id("ConversationMessageAlready11replyContentType")).click();
    driver.findElement(By.linkText("Post")).click();
    driver.findElement(By.id("ConversationMessageAlready12replyContentType")).click();
    new Select(driver.findElement(By.id("ConversationMessageAlready1Selector"))).selectByVisibleText("multi'post多图文“测试” selenium");
    driver.findElement(By.linkText("Path")).click();
    driver.findElement(By.linkText("End The Conversation")).click();
    driver.findElement(By.cssSelector("li.seleted > #ConversationQuestion_path_type")).click();
    driver.findElement(By.linkText("End The Conversation")).click();
    driver.findElement(By.cssSelector("li.seleted > #ConversationQuestion_path_type")).click();
    driver.findElement(By.name("yt1")).click();
    driver.findElement(By.cssSelector("canvas")).click();
    driver.findElement(By.cssSelector("div.converBox")).click();
    driver.findElement(By.linkText("Message")).click();
    driver.findElement(By.id("ConversationMessageAlready11replyContentType")).click();
    driver.findElement(By.id("ConversationMessageAlready1replayContent")).clear();
    driver.findElement(By.id("ConversationMessageAlready1replayContent")).sendKeys("选择了华为 并且觉得性能不咋的\n那您图啥？");
    driver.findElement(By.cssSelector("section.addMobileCon.add_question > svg.svgIcon")).click();
    new Select(driver.findElement(By.id("ConversationMessage_0_time"))).selectByVisibleText("5");
    driver.findElement(By.xpath("//div[@id='ConversationMessageAlready1EditorItem']/div/ul/li[3]")).click();
    driver.findElement(By.linkText("Image")).click();
    AssertJUnit.assertEquals("Unsupported file size!", closeAlertAndGetItsText());
    driver.findElement(By.id("ConversationMessage_Already_1_file")).clear();
    driver.findElement(By.id("ConversationMessage_Already_1_file")).sendKeys("");
    driver.findElement(By.id("ConversationMessage_Already_1_file")).clear();
    driver.findElement(By.id("ConversationMessage_Already_1_file")).sendKeys("");
    driver.findElement(By.cssSelector("div.time")).click();
    driver.findElement(By.cssSelector("div.converBox > span.del_message")).click();
    driver.findElement(By.linkText("Path")).click();
    driver.findElement(By.linkText("End The Conversation")).click();
    driver.findElement(By.cssSelector("li.seleted > #ConversationQuestion_path_type")).click();
    driver.findElement(By.name("yt1")).click();
    // ERROR: Caught exception [ERROR: Unsupported command [selectWindow | null | ]]
    driver.findElement(By.xpath("//div[@id='page']/div[2]/div[2]/div[2]/div/div/div[2]/div[2]/div")).click();
    driver.findElement(By.xpath("//div[@id='post_analytics']/div/div/div[2]/div[3]/div[2]/div")).click();
    driver.findElement(By.linkText("Conversation")).click();
    driver.findElement(By.cssSelector("img[alt=\"View\"]")).click();
    driver.findElement(By.cssSelector("canvas")).click();
    driver.findElement(By.cssSelector("div.breadCrumb > div.container")).click();
    driver.findElement(By.id("ConversationMessageAlready1replayContent")).clear();
    driver.findElement(By.id("ConversationMessageAlready1replayContent")).sendKeys("选择了华为 并且觉得性能不咋的\n那您图啥？");
    driver.findElement(By.cssSelector("section.addMobileCon.add_question")).click();
    driver.findElement(By.cssSelector("time")).click();
    new Select(driver.findElement(By.id("ConversationMessage_0_time"))).selectByVisibleText("5");
    driver.findElement(By.id("ConversationMessageAlready1replayContent")).clear();
    driver.findElement(By.id("ConversationMessageAlready1replayContent")).sendKeys("选择了华为 并且觉得性能不咋的\n那您图啥？");
    driver.findElement(By.linkText("Image")).click();
    driver.findElement(By.id("ConversationMessageAlready13replyContentType")).click();
    driver.findElement(By.id("ConversationMessage_Already_1_file")).clear();
    driver.findElement(By.id("ConversationMessage_Already_1_file")).sendKeys("");
    driver.findElement(By.id("ConversationMessage_Already_1_file")).clear();
    driver.findElement(By.id("ConversationMessage_Already_1_file")).sendKeys("");
    driver.findElement(By.linkText("Path")).click();
    driver.findElement(By.linkText("End The Conversation")).click();
    driver.findElement(By.cssSelector("li.seleted > #ConversationQuestion_path_type")).click();
    driver.findElement(By.name("yt1")).click();
    driver.findElement(By.linkText("Message")).click();
    driver.findElement(By.id("ConversationMessageAlready11replyContentType")).click();
    driver.findElement(By.id("ConversationMessageAlready1replayContent")).clear();
    driver.findElement(By.id("ConversationMessageAlready1replayContent")).sendKeys("Thank you for your time .\n\n你选择了华为然后没有了回答。");
    driver.findElement(By.linkText("Path")).click();
    driver.findElement(By.linkText("End The Conversation")).click();
    driver.findElement(By.cssSelector("li.seleted > #ConversationQuestion_path_type")).click();
    driver.findElement(By.name("yt1")).click();
    driver.findElement(By.cssSelector("section.addMobileCon.add_question > svg.svgIcon > use")).click();
    driver.findElement(By.cssSelector("div.converBox")).click();
    driver.findElement(By.xpath("//div[@id='ConversationMessageAlready1EditorItem']/div/ul/li[3]")).click();
    driver.findElement(By.linkText("Image")).click();
    driver.findElement(By.id("ConversationMessageAlready13replyContentType")).click();
    driver.findElement(By.id("ConversationMessage_Already_1_file")).clear();
    driver.findElement(By.id("ConversationMessage_Already_1_file")).sendKeys("");
    driver.findElement(By.cssSelector("div.questionMessage > img")).click();
    driver.findElement(By.cssSelector("section.addMobileCon.add_question")).click();
    driver.findElement(By.linkText("Message")).click();
    driver.findElement(By.id("ConversationMessageAlready11replyContentType")).click();
    driver.findElement(By.id("ConversationMessageAlready1replayContent")).clear();
    driver.findElement(By.id("ConversationMessageAlready1replayContent")).sendKeys("面对变成街机的iPhone 您有什么想法吗？");
    driver.findElement(By.cssSelector("div.treeBox.on > div.converBox > div.questionMessage")).click();
    driver.findElement(By.cssSelector("section.addMobileCon.add_question")).click();
    new Select(driver.findElement(By.id("ConversationMessage_0_time"))).selectByVisibleText("5");
    driver.findElement(By.linkText("Image")).click();
    driver.findElement(By.id("ConversationMessageAlready13replyContentType")).click();
    AssertJUnit.assertEquals("Unsupported file size!", closeAlertAndGetItsText());
    driver.findElement(By.id("ConversationMessage_Already_1_file")).clear();
    driver.findElement(By.id("ConversationMessage_Already_1_file")).sendKeys("");
    driver.findElement(By.id("ConversationMessage_Already_1_file")).clear();
    driver.findElement(By.id("ConversationMessage_Already_1_file")).sendKeys("");
    driver.findElement(By.cssSelector("section.addMobileCon.add_question > svg.svgIcon > use")).click();
    driver.findElement(By.cssSelector("div.treeBox.on > div.converBox")).click();
    driver.findElement(By.linkText("Message")).click();
    driver.findElement(By.id("ConversationMessageAlready11replyContentType")).click();
    driver.findElement(By.id("ConversationMessageAlready1replayContent")).clear();
    driver.findElement(By.id("ConversationMessageAlready1replayContent")).sendKeys("您喜欢IPhone是因为：\n1. 朋友介绍\n2. 路人手捧\n3. 各种广告\n4. 有钱没处花\n\n----{{time}}好");
    new Select(driver.findElement(By.id("ConversationMessage_0_time"))).selectByVisibleText("5");
    driver.findElement(By.linkText("Path")).click();
    driver.findElement(By.linkText("Continue Without Keywords")).click();
    driver.findElement(By.cssSelector("li.seleted > #ConversationQuestion_path_type")).click();
    driver.findElement(By.xpath("(//a[contains(text(),'Tags')])[2]")).click();
    driver.findElement(By.id("QuestionTag_0_name")).clear();
    driver.findElement(By.id("QuestionTag_0_name")).sendKeys("4");
    driver.findElement(By.id("tag_handler_0")).click();
    driver.findElement(By.id("ui-id-375")).click();
    driver.findElement(By.cssSelector("div.tags_next")).click();
    driver.findElement(By.xpath("(//span[@onclick='deleteKeywords(this)'])[3]")).click();
    driver.findElement(By.linkText("Setting")).click();
    driver.findElement(By.cssSelector("ul.radioUl > img")).click();
    driver.findElement(By.name("yt1")).click();
    driver.findElement(By.cssSelector("div.converBox")).click();
    driver.findElement(By.id("ConversationMessageAlready1replayContent")).clear();
    driver.findElement(By.id("ConversationMessageAlready1replayContent")).sendKeys("IPhone 作为街机\nPhone，是美国苹果公司研发的智能手机，它搭载iOS操作系统。第一代iPhone于2007年1月9日由苹果公司前首席执行官史蒂夫·乔布斯发布，并在2007年6月29日正式发售。");
    driver.findElement(By.cssSelector("section.addMobileCon.add_question")).click();
    driver.findElement(By.cssSelector("div.treeBox.on > div.converBox")).click();
    new Select(driver.findElement(By.id("ConversationMessage_0_time"))).selectByVisibleText("5");
    driver.findElement(By.id("ConversationMessageAlready1replayContent")).clear();
    driver.findElement(By.id("ConversationMessageAlready1replayContent")).sendKeys("苹果首次进入手机市场是在2005年，当时苹果与摩托罗拉合作推出了一款iTunes手机——Motorola Rockr。尽管理论上，这款设备将移动与音乐进行了融合，但实际上，它并没有引起消费者多大兴趣。\n当乔布斯在2007年1月发布iPhone时，人们对Motorola Rockr的记忆全部抹去。时任谷歌CEO的埃里克-施密特（Eric Schmidt）也前去给乔布斯助阵，共同揭晓了第一代iPhone手机。");
    driver.findElement(By.cssSelector("div.treeBox.on > div.converBox > div.questionMessage")).click();
    driver.findElement(By.cssSelector("section.addMobileCon.add_question > svg.svgIcon > use")).click();
    new Select(driver.findElement(By.id("ConversationMessage_0_time"))).selectByVisibleText("5");
    driver.findElement(By.id("ConversationMessageAlready1replayContent")).clear();
    driver.findElement(By.id("ConversationMessageAlready1replayContent")).sendKeys("您是不是并不希望看到这些简介？");
    driver.findElement(By.cssSelector("section.addMobileCon.add_question > svg.svgIcon")).click();
    driver.findElement(By.linkText("Image")).click();
    driver.findElement(By.id("ConversationMessageAlready13replyContentType")).click();
    driver.findElement(By.id("ConversationMessage_Already_1_file")).clear();
    driver.findElement(By.id("ConversationMessage_Already_1_file")).sendKeys("");
    driver.findElement(By.cssSelector("div.treeBox.on > div.converBox > span.del_message")).click();
    driver.findElement(By.id("ConversationMessageAlready11replyContentType")).click();
    driver.findElement(By.cssSelector("section.addMobileCon.add_question")).click();
    driver.findElement(By.linkText("Image")).click();
    driver.findElement(By.id("ConversationMessageAlready13replyContentType")).click();
    driver.findElement(By.id("ConversationMessage_Already_1_file")).clear();
    driver.findElement(By.id("ConversationMessage_Already_1_file")).sendKeys("");
    driver.findElement(By.linkText("Path")).click();
    driver.findElement(By.id("QuestionPath_0_keyword")).clear();
    driver.findElement(By.id("QuestionPath_0_keyword")).sendKeys("是");
    driver.findElement(By.xpath("//form[@id='conversation-question-form']/div/ul/li[2]/div[3]/span[2]")).click();
    driver.findElement(By.id("QuestionPath_1_keyword")).clear();
    driver.findElement(By.id("QuestionPath_1_keyword")).sendKeys("不是");
    driver.findElement(By.xpath("(//a[contains(text(),'Tags')])[2]")).click();
    driver.findElement(By.name("yt1")).click();
    driver.findElement(By.cssSelector("div.converBox")).click();
    driver.findElement(By.linkText("Message")).click();
    driver.findElement(By.id("ConversationMessageAlready11replyContentType")).click();
    driver.findElement(By.id("ConversationMessageAlready1replayContent")).clear();
    driver.findElement(By.id("ConversationMessageAlready1replayContent")).sendKeys("{{nickname}} 选择了IPhone 并且不喜欢看简介。");
    driver.findElement(By.linkText("Path")).click();
    driver.findElement(By.linkText("End The Conversation")).click();
    driver.findElement(By.cssSelector("li.seleted > #ConversationQuestion_path_type")).click();
    driver.findElement(By.name("yt1")).click();
    driver.findElement(By.cssSelector("div.converBox")).click();
    driver.findElement(By.id("ConversationMessageAlready1replayContent")).clear();
    driver.findElement(By.id("ConversationMessageAlready1replayContent")).sendKeys("{{nickname}} 选择了IPhone 并且喜欢看简介\n您可自行百度 。");
    driver.findElement(By.linkText("Path")).click();
    driver.findElement(By.linkText("End The Conversation")).click();
    driver.findElement(By.cssSelector("li.seleted > #ConversationQuestion_path_type")).click();
    driver.findElement(By.name("yt1")).click();
    driver.findElement(By.name("yt0")).click();
    driver.findElement(By.name("yt1")).click();
    driver.findElement(By.id("ConversationMessageAlready1replayContent")).clear();
    driver.findElement(By.id("ConversationMessageAlready1replayContent")).sendKeys("选择了IPhone之后 回答不在我们的范围内了。\nsay goodbye。");
    driver.findElement(By.linkText("Path")).click();
    driver.findElement(By.linkText("End The Conversation")).click();
    driver.findElement(By.cssSelector("li.seleted > #ConversationQuestion_path_type")).click();
    driver.findElement(By.cssSelector("section.addMobileCon.add_question")).click();
    driver.findElement(By.cssSelector("div.converBox > span.del_message")).click();
    driver.findElement(By.id("ConversationMessageAlready11replyContentType")).click();
    driver.findElement(By.name("yt1")).click();
    driver.findElement(By.id("ConversationMessageAlready1replayContent")).clear();
    driver.findElement(By.id("ConversationMessageAlready1replayContent")).sendKeys("{{nickname}}喜欢这款手机的原因是啥呢？");
    driver.findElement(By.cssSelector("section.addMobileCon.add_question")).click();
    driver.findElement(By.xpath("//div[@id='ConversationMessageAlready1EditorItem']/div/ul/li[3]")).click();
    driver.findElement(By.linkText("Image")).click();
    driver.findElement(By.id("ConversationMessageAlready13replyContentType")).click();
    driver.findElement(By.id("ConversationMessage_Already_1_file")).clear();
    driver.findElement(By.id("ConversationMessage_Already_1_file")).sendKeys("");
    driver.findElement(By.cssSelector("section.addMobileCon.add_question")).click();
    driver.findElement(By.cssSelector("div.treeBox.on > div.converBox")).click();
    new Select(driver.findElement(By.id("ConversationMessage_0_time"))).selectByVisibleText("5");
    driver.findElement(By.linkText("Post")).click();
    driver.findElement(By.id("ConversationMessageAlready12replyContentType")).click();
    new Select(driver.findElement(By.id("ConversationMessageAlready1Selector"))).selectByVisibleText("multi'post多图文“测试”selenium");
    driver.findElement(By.cssSelector("section.addMobileCon.add_question > svg.svgIcon > use")).click();
    new Select(driver.findElement(By.id("ConversationMessage_0_time"))).selectByVisibleText("5");
    driver.findElement(By.linkText("Message")).click();
    driver.findElement(By.id("ConversationMessageAlready1replayContent")).clear();
    driver.findElement(By.id("ConversationMessageAlready1replayContent")).sendKeys("请点击下方的链接 \n查看更多这方面手机的资料\n\n  如果您喜欢的话可以留下您的手机号  方便我们跟您联系。\nhttp://product.suning.com/0000000000/146158971.html?srcpoint=shouji_newpage_97723392_prod03");
    driver.findElement(By.linkText("Path")).click();
    driver.findElement(By.id("QuestionPath_0_keyword")).clear();
    driver.findElement(By.id("QuestionPath_0_keyword")).sendKeys("1830210");
    driver.findElement(By.name("yt1")).click();
    driver.findElement(By.xpath("(//img[@alt='Manager'])[6]")).click();
    // ERROR: Caught exception [ERROR: Unsupported command [selectWindow | name=blank | ]]
    driver.findElement(By.xpath("//div[@id='page']/div[2]/div[2]/div[2]/div/div/div[2]/div[2]/div")).click();
    driver.findElement(By.xpath("//div[@id='post_analytics']/div/div/div[2]/div[3]/div[2]/div")).click();
    driver.findElement(By.xpath("//div[@id='post_analytics']/div/div/div/div[3]/div[2]/div")).click();
    driver.findElement(By.linkText("Conversation")).click();
    driver.findElement(By.cssSelector("img[alt=\"View\"]")).click();
    driver.findElement(By.id("ConversationMessageAlready1replayContent")).clear();
    driver.findElement(By.id("ConversationMessageAlready1replayContent")).sendKeys("{{nickname}}喜欢这款手机的原因是啥呢？");
    driver.findElement(By.cssSelector("section.addMobileCon.add_question > svg.svgIcon")).click();
    driver.findElement(By.linkText("Image")).click();
    driver.findElement(By.id("ConversationMessageAlready13replyContentType")).click();
    driver.findElement(By.id("ConversationMessage_Already_1_file")).clear();
    driver.findElement(By.id("ConversationMessage_Already_1_file")).sendKeys("");
    driver.findElement(By.cssSelector("section.addMobileCon.add_question > svg.svgIcon > use")).click();
    driver.findElement(By.cssSelector("div.treeBox.on > div.converBox")).click();
    new Select(driver.findElement(By.id("ConversationMessage_0_time"))).selectByVisibleText("5");
    driver.findElement(By.linkText("Post")).click();
    driver.findElement(By.id("ConversationMessageAlready12replyContentType")).click();
    new Select(driver.findElement(By.id("ConversationMessageAlready1Selector"))).selectByVisibleText("multi'post多图文“测试”selenium");
    driver.findElement(By.cssSelector("section.addMobileCon.add_question > svg.svgIcon")).click();
    new Select(driver.findElement(By.id("ConversationMessage_0_time"))).selectByVisibleText("5");
    driver.findElement(By.xpath("//div[@id='ConversationMessageAlready1EditorItem']/div/ul/li[2]")).click();
    driver.findElement(By.linkText("Message")).click();
    driver.findElement(By.id("ConversationMessageAlready1replayContent")).clear();
    driver.findElement(By.id("ConversationMessageAlready1replayContent")).sendKeys("请点击下方的链接 \n查看更多这方面手机的资料\n\n  如果您喜欢的话可以留下您的手机号  方便我们跟您联系。\nhttp://product.suning.com/0000000000/146158971.html?srcpoint=shouji_newpage_97723392_prod03");
    driver.findElement(By.cssSelector("div.treeBox.on > div.converBox")).click();
    driver.findElement(By.cssSelector("div.treeBox.on > div.converBox > span.del_message")).click();
    driver.findElement(By.xpath("//div[@id='message_template']/div[3]/div[2]/div/div/div/div/div[3]")).click();
    driver.findElement(By.cssSelector("section.addMobileCon.add_question")).click();
    driver.findElement(By.linkText("Message")).click();
    driver.findElement(By.id("ConversationMessageAlready1replayContent")).clear();
    driver.findElement(By.id("ConversationMessageAlready1replayContent")).sendKeys("请点击下方的链接 \n查看更多这方面手机的资料\n\n  如果您喜欢的话可以留下您的手机号  方便我们跟您联系。\nhttp://product.suning.com/0000000000/146158971.html?srcpoint=shouji_newpage_97723392_prod03");
    driver.findElement(By.cssSelector("div.treeBox.on > div.converBox > span.del_message")).click();
    driver.findElement(By.cssSelector("img.i-img")).click();
    driver.findElement(By.cssSelector("div.questionMessage > img")).click();
    driver.findElement(By.cssSelector("img.i-img")).click();
    driver.findElement(By.cssSelector("section.addMobileCon.add_question > svg.svgIcon")).click();
    driver.findElement(By.cssSelector("li.seleted")).click();
    driver.findElement(By.linkText("Message")).click();
    driver.findElement(By.cssSelector("div.treeBox.on > div.converBox > span.del_message")).click();
    driver.findElement(By.name("yt1")).click();
    driver.findElement(By.linkText("Path")).click();
    driver.findElement(By.id("QuestionPath_0_keyword")).clear();
    driver.findElement(By.id("QuestionPath_0_keyword")).sendKeys("1830210");
    driver.findElement(By.name("yt1")).click();
    // ERROR: Caught exception [ERROR: Unsupported command [selectWindow | null | ]]
    driver.findElement(By.xpath("(//img[@alt='Manager'])[6]")).click();
    // ERROR: Caught exception [ERROR: Unsupported command [selectWindow | name=blank | ]]
    driver.findElement(By.xpath("//div[@id='page']/div[2]/div[2]/div[2]/div/div/div[2]/div[2]/div")).click();
    driver.findElement(By.xpath("//div[@id='post_analytics']/div/div/div[2]/div[3]/div[2]/div")).click();
    // ERROR: Caught exception [ERROR: Unsupported command [selectWindow | null | ]]
    driver.findElement(By.xpath("(//img[@alt='Manager'])[6]")).click();
    // ERROR: Caught exception [ERROR: Unsupported command [selectWindow | name=blank | ]]
    driver.findElement(By.xpath("//div[@id='page']/div[2]/div[2]/div[2]/div/div/div[2]/div[2]/div")).click();
    driver.findElement(By.xpath("//div[@id='post_analytics']/div/div/div[2]/div[3]/div[2]/div")).click();
    driver.findElement(By.linkText("Conversation")).click();
    driver.findElement(By.cssSelector("img[alt=\"View\"]")).click();
    driver.findElement(By.cssSelector("div.converBox")).click();
    driver.findElement(By.id("ConversationMessageAlready1replayContent")).clear();
    driver.findElement(By.id("ConversationMessageAlready1replayContent")).sendKeys("{{nickname}}喜欢这款手机的原因是啥呢？");
    driver.findElement(By.cssSelector("section.addMobileCon.add_question")).click();
    driver.findElement(By.cssSelector("div.treeBox.on > div.converBox")).click();
    driver.findElement(By.linkText("Image")).click();
    driver.findElement(By.id("ConversationMessageAlready13replyContentType")).click();
    driver.findElement(By.id("ConversationMessage_Already_1_file")).clear();
    driver.findElement(By.id("ConversationMessage_Already_1_file")).sendKeys("");
    driver.findElement(By.cssSelector("div.treeBox.on")).click();
    driver.findElement(By.id("ConversationMessageAlready11replyContentType")).click();
    driver.findElement(By.cssSelector("section.addMobileCon.add_question > svg.svgIcon")).click();
    driver.findElement(By.linkText("Post")).click();
    driver.findElement(By.id("ConversationMessageAlready12replyContentType")).click();
    new Select(driver.findElement(By.id("ConversationMessageAlready1Selector"))).selectByVisibleText("multi'post多图文“测试”selenium");
    driver.findElement(By.cssSelector("section.addMobileCon.add_question > svg.svgIcon")).click();
    driver.findElement(By.cssSelector("img.i-img")).click();
    new Select(driver.findElement(By.id("ConversationMessage_0_time"))).selectByVisibleText("5");
    driver.findElement(By.xpath("//div[@id='message_template']/div[4]/div[2]")).click();
    driver.findElement(By.linkText("Message")).click();
    driver.findElement(By.id("ConversationMessageAlready1replayContent")).clear();
    driver.findElement(By.id("ConversationMessageAlready1replayContent")).sendKeys("请点击下方的链接 \n查看更多这方面手机的资料\n\n  如果您喜欢的话可以留下您的手机号  方便我们跟您联系。\nhttp://product.suning.com/0000000000/146158971.html?srcpoint=shouji_newpage_97723392_prod03");
    driver.findElement(By.id("ConversationMessageAlready1replayContent")).clear();
    driver.findElement(By.id("ConversationMessageAlready1replayContent")).sendKeys("请点击下方的链接 \n查看更多这方面手机的资料\n\n  如果您喜欢的话可以留下您的手机号  方便我们跟您联系。\nhttp://product.suning.com/0000000000/146158971.html?srcpoint=shouji_newpage_97723392_prod03");
    driver.findElement(By.cssSelector("section.addMobileCon.add_question")).click();
    driver.findElement(By.id("ConversationMessageAlready1replayContent")).clear();
    driver.findElement(By.id("ConversationMessageAlready1replayContent")).sendKeys("请点击下方的链接 \n查看更多这方面手机的资料\n\n  如果您喜欢的话可以留下您的手机号  方便我们跟您联系。\nhttp://product.suning.com/0000000000/146158971.html?srcpoint=shouji_newpage_97723392_prod03");
    driver.findElement(By.cssSelector("section.addMobileCon.add_question > svg.svgIcon > use")).click();
    driver.findElement(By.cssSelector("div.treeBox.on > div.time > time")).click();
    driver.findElement(By.id("ConversationMessageAlready1replayContent")).clear();
    driver.findElement(By.id("ConversationMessageAlready1replayContent")).sendKeys("请点击下方的链接 \n查看更多这方面手机的资料\n\n  如果您喜欢的话可以留下您的手机号  方便我们跟您联系。\nhttp://product.suning.com/0000000000/146158971.html?srcpoint=shouji_newpage_97723392_prod03");
    driver.findElement(By.cssSelector("section.addMobileCon.add_question")).click();
    driver.findElement(By.id("ConversationMessageAlready1replayContent")).clear();
    driver.findElement(By.id("ConversationMessageAlready1replayContent")).sendKeys("请点击下方的链接 \n查看更多这方面手机的资料\n\n  如果您喜欢的话可以留下您的手机号  方便我们跟您联系。\nhttp://product.suning.com/0000000000/146158971.html?srcpoint=shouji_newpage_97723392_prod03");
    driver.findElement(By.cssSelector("section.addMobileCon.add_question")).click();
    driver.findElement(By.id("ConversationMessageAlready1replayContent")).clear();
    driver.findElement(By.id("ConversationMessageAlready1replayContent")).sendKeys("请点击下方的链接 \n查看更多这方面手机的资料\n\n  如果您喜欢的话可以留下您的手机号  方便我们跟您联系。\nhttp://product.suning.com/0000000000/146158971.html?srcpoint=shouji_newpage_97723392_prod03");
    driver.findElement(By.cssSelector("div.questionMessage")).click();
    driver.findElement(By.id("ConversationMessageAlready11replyContentType")).click();
    driver.findElement(By.id("ConversationMessageAlready1replayContent")).clear();
    driver.findElement(By.id("ConversationMessageAlready1replayContent")).sendKeys("{{nickname}}喜欢这款手机的原因是啥呢？");
    driver.findElement(By.xpath("//div[@id='message_template']/div[2]/div[2]/div")).click();
    driver.findElement(By.id("ConversationMessageAlready11replyContentType")).click();
    driver.findElement(By.linkText("Image")).click();
    driver.findElement(By.id("ConversationMessageAlready13replyContentType")).click();
    driver.findElement(By.id("ConversationMessage_Already_1_file")).clear();
    driver.findElement(By.id("ConversationMessage_Already_1_file")).sendKeys("");
    driver.findElement(By.xpath("//div[@id='message_template']/div[3]/div[2]/div")).click();
    driver.findElement(By.id("ConversationMessageAlready11replyContentType")).click();
    new Select(driver.findElement(By.id("ConversationMessage_0_time"))).selectByVisibleText("5");
    driver.findElement(By.linkText("Post")).click();
    driver.findElement(By.id("ConversationMessageAlready12replyContentType")).click();
    new Select(driver.findElement(By.id("ConversationMessageAlready1Selector"))).selectByVisibleText("multi'post多图文“测试”selenium");
    driver.findElement(By.xpath("//div[@id='message_template']/div[4]/div[2]")).click();
    driver.findElement(By.id("ConversationMessageAlready11replyContentType")).click();
    new Select(driver.findElement(By.id("ConversationMessage_0_time"))).selectByVisibleText("5");
    driver.findElement(By.xpath("//div[@id='message_template']/div[5]/div[2]/span[2]")).click();
    driver.findElement(By.id("ConversationMessageAlready11replyContentType")).click();
    driver.findElement(By.linkText("Path")).click();
    driver.findElement(By.id("QuestionPath_0_keyword")).clear();
    driver.findElement(By.id("QuestionPath_0_keyword")).sendKeys("1830210");
    driver.findElement(By.linkText("Setting")).click();
    driver.findElement(By.name("yt1")).click();
    driver.findElement(By.name("yt1")).click();
    driver.findElement(By.cssSelector("div.converBox")).click();
    driver.findElement(By.id("ConversationMessageAlready1replayContent")).clear();
    driver.findElement(By.id("ConversationMessageAlready1replayContent")).sendKeys("那您的住址是？");
    driver.findElement(By.cssSelector("section.addMobileCon.add_question")).click();
    driver.findElement(By.cssSelector("div.treeBox.on > div.converBox")).click();
    new Select(driver.findElement(By.id("ConversationMessage_0_time"))).selectByVisibleText("5");
    driver.findElement(By.id("ConversationMessageAlready1replayContent")).clear();
    driver.findElement(By.id("ConversationMessageAlready1replayContent")).sendKeys("住址可以是任何地方 包括外太空。我们都可以给您送到货。");
    driver.findElement(By.linkText("Path")).click();
    driver.findElement(By.linkText("Continue Without Keywords")).click();
    driver.findElement(By.cssSelector("li.seleted > #ConversationQuestion_path_type")).click();
    driver.findElement(By.name("yt1")).click();
    driver.findElement(By.cssSelector("div.converBox")).click();
    driver.findElement(By.id("ConversationMessageAlready1replayContent")).clear();
    driver.findElement(By.id("ConversationMessageAlready1replayContent")).sendKeys("您选择了魅族并且给了一些您的个人信息给我们\n\n\n感想您的信任 我们会毫无保留的给您公布出来。");
    driver.findElement(By.cssSelector("section.addMobileCon.add_question")).click();
    new Select(driver.findElement(By.id("ConversationMessage_0_time"))).selectByVisibleText("5");
    driver.findElement(By.linkText("Post")).click();
    driver.findElement(By.id("ConversationMessageAlready12replyContentType")).click();
    new Select(driver.findElement(By.id("ConversationMessageAlready1Selector"))).selectByVisibleText("single'post单图文“测试” selenium");
    driver.findElement(By.linkText("Path")).click();
    driver.findElement(By.linkText("End The Conversation")).click();
    driver.findElement(By.cssSelector("li.seleted > #ConversationQuestion_path_type")).click();
    driver.findElement(By.name("yt1")).click();
    driver.findElement(By.name("yt1")).click();
    driver.findElement(By.cssSelector("textarea.start")).clear();
    driver.findElement(By.cssSelector("textarea.start")).sendKeys("3");
    driver.findElement(By.cssSelector("textarea.start")).clear();
    driver.findElement(By.cssSelector("textarea.start")).sendKeys("2");
    driver.findElement(By.cssSelector("textarea.start")).clear();
    driver.findElement(By.cssSelector("textarea.start")).sendKeys("1");
    driver.findElement(By.cssSelector("textarea.start")).clear();
    driver.findElement(By.cssSelector("textarea.start")).sendKeys("2");
    driver.findElement(By.cssSelector("textarea.start")).clear();
    driver.findElement(By.cssSelector("textarea.start")).sendKeys("3");
    driver.findElement(By.cssSelector("textarea.start")).clear();
    driver.findElement(By.cssSelector("textarea.start")).sendKeys("1");
    driver.findElement(By.cssSelector("textarea.start")).clear();
    driver.findElement(By.cssSelector("textarea.start")).sendKeys("2");
    driver.findElement(By.cssSelector("textarea.start")).clear();
    driver.findElement(By.cssSelector("textarea.start")).sendKeys("3");
    driver.findElement(By.cssSelector("textarea.start")).clear();
    driver.findElement(By.cssSelector("textarea.start")).sendKeys("4");
    driver.findElement(By.cssSelector("textarea.start")).clear();
    driver.findElement(By.cssSelector("textarea.start")).sendKeys("2");
    driver.findElement(By.cssSelector("textarea.start")).clear();
    driver.findElement(By.cssSelector("textarea.start")).sendKeys("1");
    driver.findElement(By.cssSelector("textarea.start")).clear();
    driver.findElement(By.cssSelector("textarea.start")).sendKeys("2");
    driver.findElement(By.cssSelector("textarea.start")).clear();
    driver.findElement(By.cssSelector("textarea.start")).sendKeys("3");
    driver.findElement(By.cssSelector("textarea.start")).clear();
    driver.findElement(By.cssSelector("textarea.start")).sendKeys("1");
    driver.findElement(By.cssSelector("textarea.start")).clear();
    driver.findElement(By.cssSelector("textarea.start")).sendKeys("2");
    driver.findElement(By.cssSelector("textarea.start")).clear();
    driver.findElement(By.cssSelector("textarea.start")).sendKeys("1");
    driver.findElement(By.cssSelector("textarea.start")).clear();
    driver.findElement(By.cssSelector("textarea.start")).sendKeys("2");
    driver.findElement(By.cssSelector("textarea.start")).clear();
    driver.findElement(By.cssSelector("textarea.start")).sendKeys("3");
    driver.findElement(By.linkText("Conversation")).click();
  }

  @AfterMethod
public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      Assert.fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
