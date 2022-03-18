package tools.tool;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class takefile {

	public static HashMap<String, String> vals = new HashMap<String, String>();

	public static void dofile() throws Exception {
		new takefile().getfile();
	}

	public void getfile() throws Exception {
		Class clazz = takefile.class;
		InputStream inputStream = clazz.getResourceAsStream("/templates/c.txt");
		String s = readFromInputStream(inputStream);
		String[] ss = s.split("/", 0);
		String one = ss[0].split("\t")[0];
		String two = ss[0].split("\t")[1];
		vals.put(one, two);
		for (int i = 1; i < ss.length; ++i) {
			String[] arr = ss[i].split("\t");
			if (arr.length > 10)
				for (int j = 0; j < arr.length; ++j) {
					vals.put(arr[9], arr[10]);
				}
		}
	}

	public static String[] getset() {
		Set<String> values = vals.keySet();
		String[] arr = new String[values.size()];
		Iterator<String> it = values.iterator();
		int i = 0;
		while (it.hasNext()) {
			arr[i] = it.next();
			i = i + 1;
		}
		return arr;
	}

	private String readFromInputStream(InputStream inputStream) throws IOException {
		StringBuilder resultStringBuilder = new StringBuilder();
		try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
			String line;
			while ((line = br.readLine()) != null) {
				resultStringBuilder.append(line).append("\n");
			}
		}
		return resultStringBuilder.toString();
	}

}