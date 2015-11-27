package com.miniProjet.outils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
/*
 * read data from node and arc files
 */
public class ReadData {
		//the path of node and arc files
		public static final String NODE_PATH = "Data/sf_noeuds.csv";
		public static final String ARC_PATH = "Data/paris_arcs.csv";
		
		//two arraylists for saving the values of longitude and latitude
		public static ArrayList<Double> longitude = new ArrayList<Double>();
        public static ArrayList<Double> latitude = new ArrayList<Double>();
        
		//four arraylists for saving the values of source,target,distance and dang 
        public static ArrayList<Integer> source = new ArrayList<Integer>();
        public static ArrayList<Integer> target = new ArrayList<Integer>();
        public static ArrayList<Integer> distance = new ArrayList<Integer>();
        public static ArrayList<Integer> dang = new ArrayList<Integer>();
 		
		//read node file
		public static void readNode(String filename){       
			try {
	            BufferedReader reader = new BufferedReader(new FileReader(filename));
	           reader.readLine();//The first line isn't useful,there isn't data.
	            String line = null;
	          
	            while((line=reader.readLine())!=null){
	            	//read data to sting array
	            	String item[] = line.split("\t");
	            	
	                //change the value type from sting to double
	                double dLongitude = Double.parseDouble(item[item.length-2]);
	                double dLatitude = Double.parseDouble(item[item.length-1]);
	                
	                //add value to arraylist
	               longitude.add(dLongitude);
	               latitude.add(dLatitude); 
	            }
	            reader.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }		
		}
		
		//read arc file
		public static void readArc(String filename){       
			try {
	            BufferedReader reader = new BufferedReader(new FileReader(filename));
	            reader.readLine();
	            String line = null;
	          
	            while((line=reader.readLine())!=null){
	            	
	            	String item[] = line.split("\t"); 
	                //change the value type from sting to int
	                int iSource = Integer.parseInt(item[item.length-4]);
	                int iTarget = Integer.parseInt(item[item.length-3]);
	                int iDistance = Integer.parseInt(item[item.length-2]);
	                int iDang = Integer.parseInt(item[item.length-1]);
	                
	                //add value to arraylist
	                source.add(iSource);
	                target.add(iTarget);
	                distance.add(iDistance);
	                dang.add(iDang);
	            }
	            reader.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }		
		}
		
}
