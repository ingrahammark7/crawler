package tools.tool.web;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Base64;

import tools.tool.takefile;

public class webresource {

	public static void doweb() throws Exception {
		String s = getcook();
		request(s);
	}

	public static String getcook() {
		String[] arr = takefile.getset();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < arr.length; ++i) {
			sb.append(arr[i] + "=" + takefile.vals.get(arr[i]) + "; ");
		}
		String s = sb.toString();
		s = s.substring(0, s.length() - 1);
		return s;
	}

	public static void request(String cookie) throws Exception {
		String site = "https://www.theeroticreview.com";
		String site2 = "/reviews/hannie-and-cosmos-2133209456-368686";
		String site1 = "/reviews/newreviewslist.asp?searchid=354229&searchreview=1&Ethnicities=4&gCity=city%2Dlos%2Dangeles%2Dca%2Dus&Transsexual=1&gCityName=Los+Angeles%2C+CA&SortBy=3&gDistance=3";
		URL url = new URL("https://www.theeroticreview.com/myter/index.asp");
		URLConnection connection = url.openConnection();
		HttpURLConnection conn = (HttpURLConnection) connection;
		cookie = Base64.getEncoder().encodeToString(cookie.getBytes());
		conn.setRequestProperty("cookie", cookie);
		conn.setRequestProperty("authority", "www.theeroticreview.com");
		conn.setRequestProperty("method", "GET");
		conn.setRequestProperty("path", "/myter/index.asp");
		conn.setRequestProperty("scheme", "https");
		conn.setRequestProperty("accept",
				"text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
		conn.setRequestProperty("accept-encoding", "gzip, deflate, br");
		conn.setRequestProperty("accept-language", "en-US,en;q=0.9");
		conn.setRequestProperty("dnt", "1");
		conn.setRequestProperty("referer", "https://www.theeroticreview.com/");
		conn.setRequestProperty("sec-ch-ua",
				"\" Not A;Brand\";v=\"99\", \"Chromium\";v=\"99\", \"Google Chrome\";v=\"99\"");
		conn.setRequestProperty("sec-ch-ua-mobile", "?0");
		conn.setRequestProperty("sec-ch-ua-platform", "Windows");
		conn.setRequestProperty("sec-fetch-dest", "document");
		conn.setRequestProperty("sec-fetch-mode", "navigate");
		conn.setRequestProperty("sec-fetch-site", "same-origin");
		conn.setRequestProperty("sec-fetch-user", "?1");
		conn.setRequestProperty("upgrade-insecure-requests", "1");
		conn.setRequestProperty("user-agent",
				"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/99.0.4844.74 Safari/537.36");
		HttpURLConnection conn2 = (HttpURLConnection) new URL(site + site2).openConnection();
		conn2.setRequestProperty("cookie",
				"MPA3=1; _ga=GA1.2.136954141.1647589240; _gid=GA1.2.163485560.1647589240; terAgreementVer=1; TERLocationResolved=1; TerTzOffset=-420; TER%5FtestCookie=; language=en; TER%5FRememberPW=1; MsTerVer=6; cookieconsent_status=dismiss; TER%5Fusername=mingraham; TER%5Fhash=8OdI913oxsEJLWRRLLYHQSUKneB8L6vGp%2Fa8SadMjdx4QzatOvcGQJSnWZs714eWQcwwvCwEn%2BaSiXouCSMPzg%3D%3D; tz=Mountain+Standard+Time; TER%5FSearchGeoCityName=Los+Angeles%2C+CA; TER%5FSearchGeoCityId=city%2Dlos%2Dangeles%2Dca%2Dus; K-GUID-pocpghie=F9EDD2A7F737708C392C6F739D13C9CD; TER%5Fsplash=1; GUID=CB9B8F63B2D4324D92D333ADF8D4E64F; Upgraded=1; MsTerCounter=6; _gat=1");
		doreq(conn2);
	}

	public static void doreq(HttpURLConnection con) throws Exception {
		int responseCode = con.getResponseCode();
		System.out.println("GET Response Code :: " + responseCode);
		if (responseCode == HttpURLConnection.HTTP_OK) { // success
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			System.out.println(response.toString());
		} else {
			System.out.println("GET request not worked");
		}

	}

}