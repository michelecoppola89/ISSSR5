package com.isssr5.entities;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="ordinalScale")
public class OrdinalScale {

		private String type;
		private EnumerateDomain scalePointList;

		public OrdinalScale() {

		}

		public OrdinalScale(String type, EnumerateDomain scalePointList){
			this.type=type;
			this.scalePointList=scalePointList;
			
		}

		public String getType() {
			return type;
		}
		
		@XmlElement
		public void setType(String type) {
			this.type = type;
		}

		public EnumerateDomain getScalePointList() {
			return scalePointList;
		}
		
		@XmlElement
		public void setScalePointList(EnumerateDomain scalePointList) {
			this.scalePointList = scalePointList;
		}


	
}
