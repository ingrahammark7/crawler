package tools.tool.mousetools;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class mousetools {

	public static int wait = 500;
	public static int shortwait = 100;

	public static void dologin() throws Exception {
		Robot bot = new Robot();
		String loginurl = "https://www.theeroticreview.com/memberlaunch/login.asp?dest=/myter/index.asp?";
		StringSelection ss = new StringSelection(loginurl);
		Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();
		clip.setContents(ss, null);
		cli(bot, 596, 47, wait);
		doCombo(bot, KeyEvent.VK_CONTROL, KeyEvent.VK_A);
		doCombo(bot, KeyEvent.VK_CONTROL, KeyEvent.VK_V);
		dopress(bot, KeyEvent.VK_ENTER);
		Thread.sleep(3000);
		cli(bot, 1022, 560, wait);
		dopress(bot, KeyEvent.VK_F12);
		doCombo(bot, KeyEvent.VK_CONTROL, KeyEvent.VK_R);
		cli(bot, 1909, 324, wait);
		cli(bot, 1909, 324, wait);
		cli(bot, 1198, 324, wait);
		cli(bot, 1198, 324, wait);
		cli(bot, 1500, 524, wait);
		cli(bot, 1500, 524, wait);
		doCombo(bot, KeyEvent.VK_CONTROL, KeyEvent.VK_A);
		doCombo(bot, KeyEvent.VK_CONTROL, KeyEvent.VK_C);
		dopress(bot, KeyEvent.VK_F12);
		String foo = "";
		foo = (String) clip.getData(DataFlavor.stringFlavor);
		System.out.println(foo);
	}

	public static void dopress(Robot bot, int event) throws InterruptedException {
		bot.keyPress(event);
		bot.keyRelease(event);
		Thread.sleep(wait);
	}

	public static void doCombo(Robot bot, int one, int two) throws InterruptedException {
		bot.keyPress(one);
		bot.keyPress(two);
		Thread.sleep(shortwait);
		bot.keyRelease(one);
		bot.keyRelease(two);
	}

	public static void cli(Robot bot, int x, int y, int dur) throws InterruptedException {
		bot.mouseMove(x, y);
		doclick(bot, dur);
	}

	public static void doclick(Robot bot, int dur) throws InterruptedException {
		bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		Thread.sleep(dur);
		bot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
	}

}