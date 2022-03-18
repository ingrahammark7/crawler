package tools.tool.web;

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
		URL url = new URL("https://www.theeroticreview.com/main.asp");
		URLConnection connection = url.openConnection();
		HttpURLConnection conn = (HttpURLConnection) connection;
		cookie = Base64.getEncoder().encodeToString(cookie.getBytes());
		conn.setRequestProperty("cookie", cookie);
		conn.setRequestProperty("authority", "www.theeroticreview.com");
		conn.setRequestProperty("method", "GET");
		conn.setRequestProperty("path", "/myter/index.asp");
		conn.setRequestProperty("scheme", "https");
		conn.setRequestProperty("accept", "text/html,application/xhtml+xml");
		conn.setRequestProperty("accept-encoding", "gzip, deflate, br");
		conn.setRequestProperty("accept-language", "en-US,en;q=0.9");
		conn.setRequestProperty("dnt", "1");
		conn.setRequestProperty("referer", "https://www.theeroticreview.com/vip/buyVIP.asp?option=yearly");
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

	}

}