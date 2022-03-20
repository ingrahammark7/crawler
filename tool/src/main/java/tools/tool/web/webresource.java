package tools.tool.web;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;

import tools.tool.mousetools.mousetools;

public class webresource {

	public static int wait = 500;

	public static void doweb() throws Exception {
		String site1 = "/reviews/newreviewsList.asp?Valid=1&mp=0&searchid=354229&SortBy=3&searchreview=1&Ethnicities=4&Transsexual=1&gCity=city-los-angeles-ca-us&gDistance=3&gCityName=Los%20Angeles,%20CA";
		for (int i = 0; i < 10; ++i) {
			System.out.println("top level loop is " + i);
			int bar = i + 1;
			String r = request(site1 + "&page=" + bar);
			HashMap<String, String> links = getlink(r);
			Iterator<String> it = links.keySet().iterator();
			while (it.hasNext()) {
				String t = (String) it.next();
				System.out.println("doing sublinks " + t);
				String count = links.get(t);
				dosublinks(t, count);
			}
		}
	}

	public static HashMap<String, String> removelinks(HashMap<String, String> in) {
		HashMap<String, String> res = new HashMap<String, String>();
		Iterator<String> keys = in.keySet().iterator();
		int i = 0;
		while (keys.hasNext()) {
			if (i < 5) {
				keys.next();
				i = i + 1;
				continue;
			}
			i = i + 1;
			String s = keys.next();
			res.put(s, in.get(s));
		}
		return res;
	}

	public static void dosublinks(String t, String count) throws Exception {
		String originalt = t;
		count = count.replace(" ", "");
		int pages = (Integer.parseInt(count)) / 10;
		pages = pages + 1;
		for (int i = 0; i < pages; ++i) {
			int bar = i + 1;
			String tempr = originalt + "?page=" + bar + "#Review";
			System.out.println("request is " + tempr);
			tempr = request(tempr);
			HashMap<String, String> test = getsublink(tempr);
			System.out.println("page " + i + " of " + pages);
			Iterator<String> it = test.keySet().iterator();
			while (it.hasNext()) {
				String foo = (String) it.next();
				System.out.println("requesting " + foo);
				foo = request(foo);
				String stamp = String.valueOf(System.currentTimeMillis());
				writefile(foo, stamp + ".txt");
			}
		}
	}

	public static void writefile(String data, String name) throws Exception {
		BufferedWriter writer = new BufferedWriter(new FileWriter(name));
		writer.write(data);
		writer.close();
	}

	public static HashMap<String, String> getsublink(String r) {
		String[] links = r.split("/reviews/");
		HashMap<String, String> map = new HashMap<String, String>();
		for (int i = 1; i < links.length; ++i) {
			String t = links[i];
			String[] t1 = t.split("\"");
			t = t1[0];
			if (t.contains(".asp") || t.contains("city")) {
				continue;
			}
			for (int i1 = 0; i1 < t1.length; ++i1) {
				String temp = t1[i1];
				if (temp.contains("detail")) {
					map.put("/reviews/" + t, "");
				} else {

				}
			}

		}
		return map;
	}

	public static HashMap<String, String> getlink(String r) {
		String[] links = r.split("/reviews/");
		HashMap<String, String> map = new HashMap<String, String>();
		for (int i = 1; i < links.length; ++i) {
			String t = links[i];
			String[] t1 = t.split("\"");
			t = t1[0];
			if (t.contains(".asp"))
				continue;
			String getrev = "";
			for (int i1 = 0; i1 < t1.length; ++i1) {
				String temp = t1[i1];
				if (temp.contains("reviews")) {
					getrev = temp.split("reviews")[0];
					getrev = getrev.replace(">", "");
					map.put("/reviews/" + t, getrev);
				}
			}

		}
		return map;

	}

	public static String request(String url) throws Exception {
		String site = "https://www.theeroticreview.com";
		String site2 = url;
		String logouturl = "https://www.theeroticreview.com/memberlaunch/logout.asp?logout=yes";
		HttpURLConnection conn2 = (HttpURLConnection) new URL(site + site2).openConnection();
		HttpURLConnection logoutconn = (HttpURLConnection) new URL(logouturl).openConnection();
		doreq(logoutconn);
		dologin();
		return doreq(conn2);
	}

	public static void dologin() throws Exception {
		mousetools.dologin();
	}

	public static String doreq(HttpURLConnection con) throws Exception {
		int responseCode = con.getResponseCode();
		if (responseCode == HttpURLConnection.HTTP_OK) { // success
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			String str = response.toString();
			return str;
		} else {
			System.out.println("failed request");
		}
		return "";

	}

}