package com.github.kefschke.digidot;

public enum LEDType {
	
	RGB(24, 256),
	RGBW(32, 192);
	
	private int bits;
	private int maxLeds;
	
	private LEDType(int bits, int maxLeds) {
		this.bits = bits;
		this.maxLeds = maxLeds;
	}
	
	public int getBits() {
		return bits;
	}
	
	public int getMaxLeds() {
		return maxLeds;
	}
}
