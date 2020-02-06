package com.api.es;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.api.core.Constants;

public class ESClient {
	
	private static long tracker;
	//private static HttpURLConnection conn = null;
	
	private static Map<String, HttpURLConnection> connectionPool = new ConcurrentHashMap<String, HttpURLConnection>();
	
	public static HttpURLConnection getConn(String indexName) throws Exception {
		HttpURLConnection conn = null;
		if(connectionPool.containsKey(indexName)) {
			conn = connectionPool.get(indexName);
			if(conn.getAllowUserInteraction()) {
				return conn;
			}
		}
		
		
		synchronized(indexName) {
			URL url = new URL(Constants.ES_HOST+indexName+"/_search?filter_path=hits.hits._source.formatted");
			
			conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");
			connectionPool.put(indexName, conn);
		}
			
		
		return conn;
	}
	
	
	public static String executeSearch(String indexName, StringBuilder searchQuery) throws Exception {
		HttpURLConnection conn = getConn(indexName);
		System.out.println(searchQuery.toString());
		OutputStream os = conn.getOutputStream();
		os.write(searchQuery.toString().getBytes());
		os.flush();

		BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

		// System.out.println("Output from Server .... \n");
		String output = br.readLine();

		conn.disconnect();

		return output;

	}
	
	public static String executePut(String indexName, String query) throws Exception {
		HttpURLConnection conn = getConn(indexName, "PUT");
		System.out.println(query.toString());
		OutputStream os = conn.getOutputStream();
		os.write(query.toString().getBytes());
		os.flush();

		BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

		// System.out.println("Output from Server .... \n");
		String output = br.readLine();

		conn.disconnect();

		return output;

	}
	
	
	private static HttpURLConnection getConn(String indexName, String connType) throws IOException {
		URL url = new URL(Constants.ES_HOST+indexName);
		
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setDoOutput(true);
		conn.setRequestMethod(connType);
		conn.setRequestProperty("Content-Type", "application/json");
		return conn;
	}


	/*
	 * public HttpURLConnection getConn2() throws Exception { URL url = new
	 * URL("http://localhost:9200/mob1/_search"); HttpURLConnection conn =
	 * (HttpURLConnection) url.openConnection(); conn.setDoOutput(true);
	 * conn.setRequestMethod("POST"); conn.setRequestProperty("Content-Type",
	 * "application/json");
	 * 
	 * return conn; }
	 */

	protected  String init() throws Exception {
		String output="";
		
		HttpURLConnection conn = getConn("mob1");
			

			//String input = "{\"query\": { \"match_all\": {} }}";
			String input = "{\"query\": { \"multi_match\" : { \"query\":\"keyword1\",  \"fields\": [ \"keys^3\", \"formatted^2\"]  }  } }";
			//String input = "{\"query\": { \"match\" : {\"formatted\" : \"h3\"  }  } }";

			OutputStream os = conn.getOutputStream();
			os.write(input.getBytes());
			os.flush();

			/*
			 * if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) { throw new
			 * RuntimeException("Failed : HTTP error code : " + conn.getResponseCode()); }
			 */

			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

			
			//System.out.println("Output from Server .... \n");
			output = br.readLine();
			
			conn.disconnect();
			
		  
		return output;
	}
	
	public String createClient() {
		
		CloseableHttpClient httpclient = HttpClients.createDefault();
		
		HttpPost post = new HttpPost(Constants.ES_HOST+"/mobs/_search");
		CloseableHttpResponse response = null;
		 try {
		/*
		 * ResponseHandler<String> responseHandler = new ResponseHandler<String>() {
		 * 
		 * @Override public String handleResponse( final HttpResponse response) throws
		 * ClientProtocolException, IOException { int status =
		 * response.getStatusLine().getStatusCode(); if (status >= 200 && status < 300)
		 * { HttpEntity entity = response.getEntity(); return entity != null ?
		 * EntityUtils.toString(entity) : null; } else { throw new
		 * ClientProtocolException("Unexpected response status: " + status); } }
		 * 
		 * };
		 */
        
       
		post.addHeader("Content-Type", "application/json");
		StringEntity entity;
			entity = new StringEntity("{\"query\": { \"match_all\": {} }}");
		entity.setContentType("application/json");
		post.setEntity(entity);
		
			response = httpclient.execute(post);
			return EntityUtils.toString(response.getEntity());
			
		 }catch(Exception e) {
			System.out.println(e); 
		 }
			
		return null;
	}
	
	public static void main(String[] args) {
		long faliedOn = -1;
		try {
		long start = System.currentTimeMillis();
		ESClient client = new ESClient();
		for(int i =0;i<20000;i++) {
			//tracker = i;
				//System.out.println(client.init());
				client.init();
		}
		
		long end = System.currentTimeMillis();
		long one = end-start;
		System.out.println(one/1000);
			/*
			 * for(int i =0;i<1000;i++) { client.createClient();
			 * //System.out.println(client.createClient()); }
			 */
		
		long newend = System.currentTimeMillis();
		System.out.println(one+":"+(newend-end));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			if(faliedOn<0) {
				faliedOn = tracker;
			}
			System.out.println("faliedOn:"+faliedOn);
			e.printStackTrace();
		}
	}


	public static String  executePost(String indexName, StringBuilder query) throws IOException {
		HttpURLConnection conn = getConn(indexName, "POST");
		System.out.println(query.toString());
		OutputStream os = conn.getOutputStream();
		os.write(query.toString().getBytes());
		os.flush();

		BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

		// System.out.println("Output from Server .... \n");
		String output = br.readLine();

		conn.disconnect();

		return output;
		
	}


	public static String searchAll(String indexName) throws IOException {
		StringBuilder sb = new StringBuilder("{\"query\": { \"match_all\": {} }}");
		return executePost(indexName+"_search?filter_path=hits.hits",sb);
		
	}
	
	
}
