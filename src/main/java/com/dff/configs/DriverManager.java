package com.dff.configs;

import org.openqa.selenium.WebDriver;

public class DriverManager {
	private static DriverManager DRIVER_MANAGER;
	private static ThreadLocal<WebDriver> tDriver = new ThreadLocal<>();

	private DriverManager() {
		
	}
	
	public static DriverManager getInstance(){
		if(DRIVER_MANAGER == null) {
			synchronized (DriverManager.class) {
				if(DRIVER_MANAGER == null) {
					DRIVER_MANAGER  = new DriverManager();
				}
			}			
		}
		return DRIVER_MANAGER;
	}
	
	public synchronized void setDriver (WebDriver driver) {
		tDriver.set(driver);
	}

	public synchronized WebDriver getDriver () {
		WebDriver driver = tDriver.get();
		if (driver == null) {
			throw new IllegalStateException("Driver should not be null!!");
		}
		return driver;
	}
}
