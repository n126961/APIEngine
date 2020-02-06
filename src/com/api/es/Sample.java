package com.api.es;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;

public class Sample {
	
	public static void main(String[] args) {
		Sample sam = new Sample();
		try {
			sam.getConn("https://www.google.com/search");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void getConn(String link) throws IOException {
		
		URL url = new URL(link);
		
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setDoOutput(true);
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-Type", "text/html; charset=UTF-8");
		
		System.setProperty("http.proxyHost", "http://airsymproxyasg.lntinfotech.com/");
		System.setProperty("http.proxyPort", "8080");
		
		OutputStream os = conn.getOutputStream();
		String input = "q=m&a due diligence&rlz=1C1GCEU_enIN873IN873&oq=m&a due diligence&aqs=chrome..69i57j33.11294j0j9&sourceid=chrome&ie=UTF-8" ;
		
		os.write(input.getBytes());
		os.flush();

		/*
		 * if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) { throw new
		 * RuntimeException("Failed : HTTP error code : " + conn.getResponseCode()); }
		 */

		BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

		
		//System.out.println("Output from Server .... \n");
		String output = br.readLine();
		System.out.println(output);
		conn.disconnect();
	}
	
	

}
