package pe.disenio.biblioteca.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import android.content.Context;

public class ConnectUtils {

	public static final String	TAG	= "ConnectUtils";
	static String urls;
	private String Content;
	private String Error = null;
	String param;
	Context context;

	public ConnectUtils(Context context, String url, String datos) {
		this.context = context;
		ConnectUtils.urls = url;
		this.param = datos;
	}

	public String getResponse() {
		BufferedReader reader = null;
		HttpURLConnection conn = null;
		try {

			// Defined URL where to send data
			URL url = new URL(urls);

			// Send POST data request
			conn =(HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			
			//conect
			conn.connect();
			
			OutputStreamWriter wr = new OutputStreamWriter(
			conn.getOutputStream());
			wr.write(param);
			wr.flush();
			
			//close
			wr.close();
			
			// Get the server response

			reader = new BufferedReader(new InputStreamReader(
					conn.getInputStream()));
			StringBuilder response = new StringBuilder();
			String line = null;

			// Read Server Response
			while ((line = reader.readLine()) != null) {
				// Append server response in string
				response.append(line);
			}
			
			Content = response.toString();
			
		} catch(Exception ex) {
			Error = ex.getMessage();
			return Error;
		} finally {
			if (conn != null) {
				conn.disconnect();
			}
			try {
				reader.close();
			}
			catch(Exception ex) {
			}
		}
		return Content;
	}
}
