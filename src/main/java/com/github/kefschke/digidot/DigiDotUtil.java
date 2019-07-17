package com.github.kefschke.digidot;

import java.io.IOException;
import java.util.logging.Logger;

public class DigiDotUtil {
	
	private static final Logger logger = Logger.getLogger("DigiDot");
	
	public static int checkLedCount(int ledCount, LEDType type) {
		if (ledCount > type.getMaxLeds())
			ledCount = 256;
		if (ledCount <= 1)
			ledCount = 2;
		if (ledCount % 2 != 0)
			ledCount += 1;
		return ledCount;
	}

	protected static void sendInit() {
		short[] data = new short[] {
				(short) 0xB1,
				(short) DigiDot.getLedCount(),
				(short) DigiDot.getLedType().getBits()
		};
		writeBytes(data);
	}

	protected static void sendShow() {
		short[] data = new short[] {
				(short) 0xB2
		};
		writeBytes(data);
	}

	protected static void sendRGB(int r, int g, int b) {
		short[] data = new short[] {
				(short) 0xA1,
				(short) r,
				(short) g,
				(short) b
		};
		writeBytes(data);
	}
	
	protected static void sendSetRGB(int led, int r, int g, int b) {
		short[] data = new short[] {
				(short) 0xA2,
				(short) r,
				(short) g,
				(short) b,
				(short) 0xA4,
				(short) led
		};
		writeBytes(data);
	}
	
	protected static void sendFillRGB(int r, int g, int b) {
		short[] data = new short[] {
				(short) 0xA1,
				(short) r,
				(short) g,
				(short) b,
				(short) 0xA5
		};
		writeBytes(data);
	}

	protected static void sendRGBW(int r, int g, int b, int w) {
		short[] data = new short[] {
				(short) 0xA2,
				(short) r,
				(short) g,
				(short) b,
				(short) w
		};
		writeBytes(data);
	}

	protected static void sendSetRGBW(int led, int r, int g, int b, int w) {
		short[] data = new short[] {
				(short) 0xA2,
				(short) r,
				(short) g,
				(short) b,
				(short) w,
				(short) 0xA4,
				(short) led
		};
		writeBytes(data);
	}

	protected static void sendFillRGBW(int r, int g, int b, int w) {
		short[] data = new short[] {
				(short) 0xA1,
				(short) r,
				(short) g,
				(short) b,
				(short) w,
				(short) 0xA5
		};
		writeBytes(data);
	}
	
	protected static void sendHSV(int hl, int hh, int s, int v) {
		short[] data = new short[] {
				(short) 0xA3,
				(short) hl,
				(short) hh,
				(short) s,
				(short) v
		};
		writeBytes(data);
	}

	protected static void sendSetHSV(int led, int hl, int hh, int s, int v) {
		short[] data = new short[] {
				(short) 0xA3,
				(short) hl,
				(short) hh,
				(short) s,
				(short) v,
				(short) 0xA4,
				(short) led
		};
		writeBytes(data);
	}

	protected static void sendFillHSV(int hl, int hh, int s, int v) {
		short[] data = new short[] {
				(short) 0xA3,
				(short) hl,
				(short) hh,
				(short) s,
				(short) v,
				(short) 0xA5
		};
		writeBytes(data);
	}

	protected static void sendSetLed(int led) {
		short[] data = new short[] {
				(short) 0xA4,
				(short) led
		};
		writeBytes(data);
	}
	
	protected static void sendSetAll() {
		short[] data = new short[] {
				(short) 0xA5
		};
		writeBytes(data);
	}

	protected static void sendFillRange(int ledBeg, int ledEnd) {
		short[] data = new short[] {
				(short) 0xA6,
				(short) ledBeg,
				(short) ledEnd
		};
		writeBytes(data);
	}
	
	protected static void sendRainbow(int hl, int hh, int s, int v, int beg, int end, int inc) {
		short[] data = new short[] {
				(short) 0xA7,
				(short) hl,
				(short) hh,
				(short) s,
				(short) v,
				(short) beg,
				(short) end,
				(short) inc
		};
		writeBytes(data);
	}

	protected static void sendClear() {
		short[] data = new short[] {
				(short) 0xA1,
				(short) 0,
				(short) 0,
				(short) 0,
				(short) 0xA5,
				(short) 0xB2
		};
		writeBytes(data);
	}
	
	protected static void writeBytes(short[] data) {
		try {
			if (DigiDot.getSpi() != null) {
				DigiDot.getSpi().write(data);
			}
			else
				logger.warning("Error while sending data. SpiDevice not initialised. Data: " + data.toString());
			
			Thread.sleep(100);
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
