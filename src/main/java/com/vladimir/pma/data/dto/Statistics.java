package com.vladimir.pma.data.dto;

import java.io.Serializable;


public class Statistics implements Serializable {

	private static final long serialVersionUID = -2402059452731399119L;
	
	private int testId;
    private int numberOfMenPositive;
    private int numberOfMenNegative;
    private int numberOfWomenPositive;
    private int numberOfWomenNegative;

    private int numberOfMenPositive_30;
    private int numberOfMenNegative_30;
    private int numberOfMenPositive31_50;
    private int numberOfMenNegative31_50;
    private int numberOfMenPositive51_;
    private int numberOfMenNegative51_;
    
    
    private double averageResponseTime;
    private double averageResponseTimeMen;
    private double averageResponseTimeWomen;
    
    private double sdResponseTime;
    private double sdResponseTimeMen;
    private double sdResponseTimeWomen;

    private double minResponseTime;
    private double minResponseTimeMen;
    private double minResponseTimeWomen;
    
    private double maxResponseTime;
    private double maxResponseTimeMen;
    private double maxResponseTimeWomen;
    
    private double averageResponseTimeMen_30;
    private double averageResponseTimeMen31_50;
    private double averageResponseTimeMen51_;    
    private double averageResponseTimeWomen_30;
    private double averageResponseTimeWomen31_50;
    private double averageResponseTimeWomen51_;
    

    
	public Statistics() {
	}


	public int getTestId() {
		return testId;
	}


	public void setTestId(int testId) {
		this.testId = testId;
	}


	public int getNumberOfMenPositive() {
		return numberOfMenPositive;
	}


	public void setNumberOfMenPositive(int numberOfMenPositive) {
		this.numberOfMenPositive = numberOfMenPositive;
	}


	public int getNumberOfMenNegative() {
		return numberOfMenNegative;
	}


	public void setNumberOfMenNegative(int numberOfMenNegative) {
		this.numberOfMenNegative = numberOfMenNegative;
	}


	public int getNumberOfWomenPositive() {
		return numberOfWomenPositive;
	}


	public void setNumberOfWomenPositive(int numberOfWomenPositive) {
		this.numberOfWomenPositive = numberOfWomenPositive;
	}


	public int getNumberOfWomenNegative() {
		return numberOfWomenNegative;
	}


	public void setNumberOfWomenNegative(int numberOfWomenNegative) {
		this.numberOfWomenNegative = numberOfWomenNegative;
	}


	public int getNumberOfMenPositive_30() {
		return numberOfMenPositive_30;
	}


	public void setNumberOfMenPositive_30(int numberOfMenPositive_30) {
		this.numberOfMenPositive_30 = numberOfMenPositive_30;
	}


	public int getNumberOfMenNegative_30() {
		return numberOfMenNegative_30;
	}


	public void setNumberOfMenNegative_30(int numberOfMenNegative_30) {
		this.numberOfMenNegative_30 = numberOfMenNegative_30;
	}


	public int getNumberOfMenPositive31_50() {
		return numberOfMenPositive31_50;
	}


	public void setNumberOfMenPositive31_50(int numberOfMenPositive31_50) {
		this.numberOfMenPositive31_50 = numberOfMenPositive31_50;
	}


	public int getNumberOfMenNegative31_50() {
		return numberOfMenNegative31_50;
	}


	public void setNumberOfMenNegative31_50(int numberOfMenNegative31_50) {
		this.numberOfMenNegative31_50 = numberOfMenNegative31_50;
	}


	public int getNumberOfMenPositive51_() {
		return numberOfMenPositive51_;
	}


	public void setNumberOfMenPositive51_(int numberOfMenPositive51_) {
		this.numberOfMenPositive51_ = numberOfMenPositive51_;
	}


	public int getNumberOfMenNegative51_() {
		return numberOfMenNegative51_;
	}


	public void setNumberOfMenNegative51_(int numberOfMenNegative51_) {
		this.numberOfMenNegative51_ = numberOfMenNegative51_;
	}


	public double getAverageResponseTime() {
		return averageResponseTime;
	}


	public void setAverageResponseTime(double averageResponseTime) {
		this.averageResponseTime = averageResponseTime;
	}


	public double getAverageResponseTimeMen() {
		return averageResponseTimeMen;
	}


	public void setAverageResponseTimeMen(double averageResponseTimeMen) {
		this.averageResponseTimeMen = averageResponseTimeMen;
	}


	public double getAverageResponseTimeWomen() {
		return averageResponseTimeWomen;
	}


	public void setAverageResponseTimeWomen(double averageResponseTimeWomen) {
		this.averageResponseTimeWomen = averageResponseTimeWomen;
	}


	public double getAverageResponseTimeMen_30() {
		return averageResponseTimeMen_30;
	}


	public void setAverageResponseTimeMen_30(double averageResponseTimeMen_30) {
		this.averageResponseTimeMen_30 = averageResponseTimeMen_30;
	}


	public double getAverageResponseTimeMen31_50() {
		return averageResponseTimeMen31_50;
	}


	public void setAverageResponseTimeMen31_50(double averageResponseTimeMen31_50) {
		this.averageResponseTimeMen31_50 = averageResponseTimeMen31_50;
	}


	public double getAverageResponseTimeMen51_() {
		return averageResponseTimeMen51_;
	}


	public void setAverageResponseTimeMen51_(double averageResponseTimeMen51_) {
		this.averageResponseTimeMen51_ = averageResponseTimeMen51_;
	}


	public double getAverageResponseTimeWomen_30() {
		return averageResponseTimeWomen_30;
	}


	public void setAverageResponseTimeWomen_30(double averageResponseTimeWomen_30) {
		this.averageResponseTimeWomen_30 = averageResponseTimeWomen_30;
	}


	public double getAverageResponseTimeWomen31_50() {
		return averageResponseTimeWomen31_50;
	}


	public void setAverageResponseTimeWomen31_50(double averageResponseTimeWomen31_50) {
		this.averageResponseTimeWomen31_50 = averageResponseTimeWomen31_50;
	}


	public double getAverageResponseTimeWomen51_() {
		return averageResponseTimeWomen51_;
	}


	public void setAverageResponseTimeWomen51_(double averageResponseTimeWomen51_) {
		this.averageResponseTimeWomen51_ = averageResponseTimeWomen51_;
	}    
		
	
	public double getSdResponseTime() {
		return sdResponseTime;
	}


	public void setSdResponseTime(double sdResponseTime) {
		this.sdResponseTime = sdResponseTime;
	}


	public double getSdResponseTimeMen() {
		return sdResponseTimeMen;
	}


	public void setSdResponseTimeMen(double sdResponseTimeMen) {
		this.sdResponseTimeMen = sdResponseTimeMen;
	}


	public double getSdResponseTimeWomen() {
		return sdResponseTimeWomen;
	}


	public void setSdResponseTimeWomen(double sdResponseTimeWomen) {
		this.sdResponseTimeWomen = sdResponseTimeWomen;
	}


	public double getMinResponseTime() {
		return minResponseTime;
	}


	public void setMinResponseTime(double minResponseTime) {
		this.minResponseTime = minResponseTime;
	}


	public double getMinResponseTimeMen() {
		return minResponseTimeMen;
	}


	public void setMinResponseTimeMen(double minResponseTimeMen) {
		this.minResponseTimeMen = minResponseTimeMen;
	}


	public double getMinResponseTimeWomen() {
		return minResponseTimeWomen;
	}


	public void setMinResponseTimeWomen(double minResponseTimeWomen) {
		this.minResponseTimeWomen = minResponseTimeWomen;
	}


	public double getMaxResponseTime() {
		return maxResponseTime;
	}


	public void setMaxResponseTime(double maxResponseTime) {
		this.maxResponseTime = maxResponseTime;
	}


	public double getMaxResponseTimeMen() {
		return maxResponseTimeMen;
	}


	public void setMaxResponseTimeMen(double maxResponseTimeMen) {
		this.maxResponseTimeMen = maxResponseTimeMen;
	}


	public double getMaxResponseTimeWomen() {
		return maxResponseTimeWomen;
	}


	public void setMaxResponseTimeWomen(double maxResponseTimeWomen) {
		this.maxResponseTimeWomen = maxResponseTimeWomen;
	}


	public double getPercentMenPositive(){
		return numberOfMenPositive/(numberOfMenPositive+numberOfMenNegative);
	}
    
	
	public double getPercentMenNegative(){
		return numberOfMenNegative/(numberOfMenPositive+numberOfMenNegative);
	}
	
	public double getPercentWomenPositive(){
		return numberOfWomenPositive/(numberOfWomenPositive+numberOfWomenNegative);
	}
    
	
	public double getPercentWomenNegative(){
		return numberOfWomenNegative/(numberOfWomenPositive+numberOfWomenNegative);
	}

	

}

