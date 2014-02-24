package com.isssr5.entities;

public class ScaleFactory {
	
	//private static Scale scale;
	
	public ScaleFactory(){
		
	}

	
	public static synchronized Scale getScaleInstance(String type, String domain){
		System.out.println("prima dei check");
		
		if(type.equals("nominal")){
			System.out.println("ho inserito nominal");
			return new NominalScale(type, domain);
		}
		else if(type.equals("ordinal")) {
			return new NominalScale(type, domain);
		}
		else if(type.equals("interval")) {
			return new NominalScale(type, domain);
		}
		else if(type.equals("ratio")) {
			return new NominalScale(type, domain);
		}
		else throw new IllegalStateException("bad input");
	}
}
