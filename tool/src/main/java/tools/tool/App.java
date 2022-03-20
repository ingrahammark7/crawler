package tools.tool;

import java.awt.MouseInfo;
import java.awt.Point;

import tools.tool.web.webresource;

public class App {
	public static void main(String[] args) throws Exception {
		webresource.doweb();
	}

	public static void getpos() throws InterruptedException {
		Thread.sleep(1000);
		Point spot = MouseInfo.getPointerInfo().getLocation();
		System.out.println(String.valueOf(spot.getX()) + "," + String.valueOf(spot.getY()));
	}

}