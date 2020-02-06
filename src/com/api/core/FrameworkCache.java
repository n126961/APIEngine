package com.api.core;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class FrameworkCache {
	
	
	private static Map<String,Class> classes = new ConcurrentHashMap<String, Class>();
	private List<String> allClasses = new ArrayList();

	public static void build(){
		try {
			findAllSteps("com.api");
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Class getClass(String className) {
		return classes.get(className);
	}
	
	public static void findAllSteps(String packageName) throws IOException, ClassNotFoundException {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        assert classLoader != null;
        String path = packageName.replace('.', '/');
        Enumeration resources = classLoader.getResources(path);
        List<File> dirs = new ArrayList();
        while (resources.hasMoreElements()) {
            URL resource = (URL) resources.nextElement();
            dirs.add(new File(resource.getFile()));
        }
        
        for (File directory : dirs) {
            findClasses(directory, packageName);
        }
        dirs = null;
	}
	
	/**
     * Recursive method used to find all classes in a given directory and subdirs.
     *
     * @param directory   The base directory
     * @param packageName The package name for classes found inside the base directory
     * @return The classes
     * @throws ClassNotFoundException
     */
    private static void findClasses(File directory, String packageName) throws ClassNotFoundException {
    	
    	if(directory.isDirectory()) {
    		File[] files = directory.listFiles();
    		for(File file:files) {
    			findClasses(file, packageName.concat("."+file.getName()));
    		}
    	}
    	if(directory.getName().endsWith(".class")) {
    		String className = directory.getName().substring(0, directory.getName().length() - 6);
    		Class clazz = Class.forName(packageName.replace(".class", ""));
    		System.out.println("Class for verification -> "+packageName + '.' + className);
        	
    		if(ExecutionSteps.class.isAssignableFrom(clazz)) {
    			System.out.println("Clas added -> "+packageName + '.' + className);
        		if(!classes.keySet().contains(className))
    			classes.put(className, clazz);
    		}
    		
    	}
    	
    	
    }

}
