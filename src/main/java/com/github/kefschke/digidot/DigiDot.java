package com.github.kefschke.digidot;

import java.io.IOException;

import com.pi4j.io.spi.SpiChannel;
import com.pi4j.io.spi.SpiDevice;
import com.pi4j.io.spi.SpiFactory;

public class DigiDot {
	
	private static SpiDevice spi = null;
	
	private static int ledCount;
	private static LEDType ledType = LEDType.RGB;
	
	public static void init(int spiChannel, int gpio, int count, LEDType type) {
		ledType = type;
		ledCount = DigiDotUtil.checkLedCount(count, type);
		
		try {
			spi = SpiFactory.getInstance(SpiChannel.getByNumber(spiChannel), SpiDevice.DEFAULT_SPI_SPEED, SpiDevice.DEFAULT_SPI_MODE);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		DigiDotUtil.sendInit();
	}
	
	public static void show() {
		DigiDotUtil.sendShow();
	}
	
	public static void setRGB(int r, int g, int b) {
		DigiDotUtil.sendRGB(r, g, b);
	}
	
	public static void setRGB(int led, int r, int g, int b) {
		DigiDotUtil.sendRGB(r, g, b);
		DigiDotUtil.sendSetLed(led);
	}
	
	public static void fillRGB(int r, int g, int b) {
		DigiDotUtil.sendRGB(r, g, b);
		DigiDotUtil.sendSetAll();
	}
	
	public static void setRGBW(int r, int g, int b, int w) {
		DigiDotUtil.sendRGBW(r, g, b, w);
	}
	
	public static void setRGBW(int led, int r, int g, int b, int w) {
		DigiDotUtil.sendRGBW(r, g, b, w);
		DigiDotUtil.sendSetLed(led);
	}
	
	public static void fillRGBW(int r, int g, int b, int w) {
		DigiDotUtil.sendRGBW(r, g, b, w);
		DigiDotUtil.sendSetAll();
	}
	
	public static void setHSV(int hl, int hh, int s, int v) {
		DigiDotUtil.sendHSV(hl, hh, s, v);
	}
	
	public static void setHSV(int led, int hl, int hh, int s, int v) {
		DigiDotUtil.sendHSV(hl, hh, s, v);
		DigiDotUtil.sendSetLed(led);
	}
	
	public static void fillHSV(int hl, int hh, int s, int v) {
		DigiDotUtil.sendHSV(hl, hh, s, v);
		DigiDotUtil.sendSetAll();
	}
	
	public static void fillRange(int ledBeg, int ledEnd) {
		DigiDotUtil.sendFillRange(ledBeg, ledEnd);
	}
	
	public static void fillRangeRGB(int ledBeg, int ledEnd, int r, int g, int b) {
		DigiDotUtil.sendRGB(r, g, b);
		DigiDotUtil.sendFillRange(ledBeg, ledEnd);
	}
	
	public static void fillRangeRGBW(int ledBeg, int ledEnd, int r, int g, int b, int w) {
		DigiDotUtil.sendRGBW(r, g, b, w);
		DigiDotUtil.sendFillRange(ledBeg, ledEnd);
	}
	
	public static void fillRangeHSV(int ledBeg, int ledEnd, int hl, int hh, int s, int v) {
		DigiDotUtil.sendHSV(hl, hh, s, v);
		DigiDotUtil.sendFillRange(ledBeg, ledEnd);
	}
	
	public static void rainbow(int hl, int hh, int s, int v, int beg, int end, int inc) {
		DigiDotUtil.sendRainbow(hl, hh, s, v, beg, end, inc);
	}
	
	public static int getLedCount() {
		return ledCount;
	}
	
	public static LEDType getLedType() {
		return ledType;
	}
	
	public static SpiDevice getSpi() {
		return spi;
	}
	
}
