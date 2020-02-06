package com.api.rnd;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

public class MapSize {

	private boolean sizeCheck() {
		
		System.gc();

		int mb = 1024 * 1024; 
		 
		// get Runtime instance
		Runtime instance = Runtime.getRuntime();
		
		Map<String,String> data = new ConcurrentHashMap<String, String>();
		
		Random r = new Random();
		long start=System.currentTimeMillis(); 
		System.out.println("Used Memory 1: "
				+ (instance.totalMemory() - instance.freeMemory()) / mb);
		for(int i =0;i< 2000;i++) {
			StringBuilder sb = new StringBuilder();
			for(int j =0;j< 30;j++) {	
			
			
			sb.append(j+"System.out.println(\"***** Heap utilization statistics [MB] *****\\n\");System.out.println(\"***** Heap utilization statistics [MB] *****\\n\");System.out.println(\"***** Heap utilization statistics [MB] *****\\n\");System.out.println(\"***** Heap utilization statistics [MB] *****\\n\");System.out.println(\"***** Heap utilization statistics [MB] *****\\n\");System.out.println(\"***** Heap utilization statistics [MB] *****\\n\");System.out.println(\"***** Heap utilization statistics [MB] *****\\n\");System.out.println(\"***** Heap utilization statistics [MB] *****\\n\");System.out.println(\"***** Heap utilization statistics [MB] *****\\n\");");
			}
			data.put("keyPageName"+i, sb.toString());
			
		}
		System.out.println("SIZE:"+data.size());
		System.out.println("Used Memory 2: "
				+ (instance.totalMemory() - instance.freeMemory()) / mb);
		long dataPrepared=System.currentTimeMillis();
		boolean status = false;
		for(int i =0;i< 1000;i++) {
			r.nextInt(1000);
			if(data.containsKey("keyPageName"+r)) {
				String str = data.get("keyPageName"+r);
				if(str.contains("[MB]")) {
					status = true;
				}
			}
		}
		long end=System.currentTimeMillis();
		
		System.out.println("Dat Prepared :"+(dataPrepared-start) +"| "+(end-dataPrepared) );
		System.out.println(status);
		System.out.println("Used Memory 3: "
				+ (instance.totalMemory() - instance.freeMemory()) / mb);
		
		return status;
		
			}
	
	public static void main(String[] args) {
		MapSize m = new MapSize();
		boolean status = m.sizeCheck();
		System.out.println("FINAL:"+status);
		
	}
}
