package org.ekspertsoft.zakool.dto;

import java.io.Serializable;

public class School implements Serializable{

	private String schoolCode;
	private String name;
	private String street;
	private String city;
	private String state;
	private String zipcode;
	/* Institutional category (HD2014)
	"1","Degree-granting, graduate with no undergraduate degrees"
	"2","Degree-granting, primarily baccalaureate or above"
	"3","Degree-granting, not primarily baccalaureate or above"
	"4","Degree-granting, associate's and certificates 
	"5","Nondegree-granting, above the baccalaureate"
    "6","Nondegree-granting, sub-baccalaureate"
	 */	
	private String category;
	private int totalPriceInStateOnCampus;
	private int totalPriceInStateOffCampus;
	private int totalPriceOffStateOnCampus;
	private int totalPriceOffStateOffCampus;
	private String url;
	private String phone;
	
	/*" Type
	Control of institution (HD2014)",
	"1","Public"
	"2","Private not-for-profit"
	"3","Private for-profit"
	"-3","{Not available}"
	 */
	private String type;
	
	/*
	"Institution size category (HD2014)",
	"1","Under 1,000"
	"2","1,000 - 4,999"
	"3","5,000 - 9,999"
	"4","10,000 - 19,999"
	"5","20,000 and above"
	"-1","Not reported"
	"-2","Not applicable"
	 */
	private String size;
	
	/* Level
	"Level of institution (HD2014)",
	"1","Four or more years"
	"2","At least 2 but less than 4 years"
	"3","Less than 2 years (below associate)"
	"-3","{Not available}"
	 */
	private String level;
	
	private String accreditation;
	
	public String getSchoolCode() {
		return schoolCode;
	}
	public void setSchoolCode(String schoolCode) {
		this.schoolCode = schoolCode;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public int getTotalPriceInStateOnCampus() {
		return totalPriceInStateOnCampus;
	}
	public void setTotalPriceInStateOnCampus(int totalPriceInStateOnCampus) {
		this.totalPriceInStateOnCampus = totalPriceInStateOnCampus;
	}
	public int getTotalPriceInStateOffCampus() {
		return totalPriceInStateOffCampus;
	}
	public void setTotalPriceInStateOffCampus(int totalPriceInStateOffCampus) {
		this.totalPriceInStateOffCampus = totalPriceInStateOffCampus;
	}
	public int getTotalPriceOffStateOnCampus() {
		return totalPriceOffStateOnCampus;
	}
	public void setTotalPriceOffStateOnCampus(int totalPriceOffStateOnCampus) {
		this.totalPriceOffStateOnCampus = totalPriceOffStateOnCampus;
	}
	public int getTotalPriceOffStateOffCampus() {
		return totalPriceOffStateOffCampus;
	}
	public void setTotalPriceOffStateOffCampus(int totalPriceOffStateOffCampus) {
		this.totalPriceOffStateOffCampus = totalPriceOffStateOffCampus;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getAccreditation() {
		return accreditation;
	}
	public void setAccreditation(String accreditation) {
		this.accreditation = accreditation;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	
	
}
