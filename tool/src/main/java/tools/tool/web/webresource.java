package tools.tool.web;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;

public class webresource {

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
				String count = links.get(t);
				dosublinks(t, count);
			}
		}
	}

	public static void dosublinks(String t, String count) throws Exception {
		String r = request(t);
		count = count.replace(" ", "");
		int pages = (Integer.parseInt(count)) / 10;
		pages = pages + 1;
		for (int i = 0; i < pages; ++i) {
			int bar = i + 1;
			String tempr = r + "?page=" + bar + "#Review";
			HashMap<String, String> test = getsublink(tempr);
			Iterator<String> it = test.keySet().iterator();
			System.out.println("pages of review is " + i);
			while (it.hasNext()) {
				String foo = (String) it.next();
				foo = request(foo);
				// System.out.println("final result " + foo);
				String stamp = String.valueOf(System.currentTimeMillis());
				writefile(foo, stamp);
				Thread.sleep(10000);
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
			System.out.println("top link is " + i);
			String t = links[i];
			String[] t1 = t.split("\"");
			t = t1[0];
			if (t.contains(".asp") || t.contains("city")) {
				continue;
			}
			for (int i1 = 0; i1 < t1.length; ++i1) {
				System.out.println("sublink is " + i1);
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
			System.out.println("first link is " + i);
			String t = links[i];
			String[] t1 = t.split("\"");
			t = t1[0];
			if (t.contains(".asp"))
				continue;
			String getrev = "";
			for (int i1 = 0; i1 < t1.length; ++i1) {
				System.out.println("intermeidate link is " + i1);
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
		HttpURLConnection conn2 = (HttpURLConnection) new URL(site + site2).openConnection();
		conn2.setRequestProperty("cookie",
				"MPA3=1; _ga=GA1.2.136954141.1647589240; _gid=GA1.2.163485560.1647589240; terAgreementVer=1; TERLocationResolved=1; TerTzOffset=-420; TER%5FtestCookie=; language=en; TER%5FRememberPW=1; MsTerVer=6; cookieconsent_status=dismiss; TER%5Fusername=mingraham; TER%5Fhash=8OdI913oxsEJLWRRLLYHQSUKneB8L6vGp%2Fa8SadMjdx4QzatOvcGQJSnWZs714eWQcwwvCwEn%2BaSiXouCSMPzg%3D%3D; tz=Mountain+Standard+Time; TER%5FSearchGeoCityName=Los+Angeles%2C+CA; TER%5FSearchGeoCityId=city%2Dlos%2Dangeles%2Dca%2Dus; Upgraded=1; MsTerCounter=6; GUID=3FD5F08F2976EF4B865BD569A27AE8D2; K-GUID-pocpghie=EF52F02D2E1F7BF552CF4BAD934A6471; TER%5Fsplash=1; _gat=1");
		return doreq(conn2);
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
			return response.toString();
		} else {
			System.out.println("failed request");
		}
		return "";

	}

}