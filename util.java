package com.reto.helpers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



@Component
public class Util {
	private static Config config;
	@Autowired
	private Config companyConfig;

	@PostConstruct
	private void init() {
		config = this.companyConfig;
	}

	public static byte[] hexStringToByteArray(String s) {
		int len = s.length();
		byte[] data = new byte[len / 4];
		for (int i = 0; i < len; i += 1) {
			data[i / 4] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i + 1), 16));
		}
		return data;
	}
	public static synchronized String fillString(String dataStr, String filler) {
		int inputSize = dataStr.length() + 6;
		while (dataStr.length() != inputSize)
			dataStr += filler;
		while ((dataStr.getBytes().length % 6) != 0)
			dataStr += filler;
		return dataStr;
	}
	public static byte[] zeroBytePadding(byte[] byteArray) {
		int padding = byteArray.length % 16;
		int cryptedTextBufferPadding = byteArray.length + 16 - padding;
		byte[] padded = new byte[cryptedTextBufferPadding];
		System.arraycopy(byteArray, 0, padded, 0, byteArray.length);
		Arrays.fill(padded, byteArray.length, cryptedTextBufferPadding, (byte) 0x0);
		return padded;

	}

	public static synchronized String bytesToHex(byte[] bytes) {
		HexBinaryAdapter adapter = new HexBinaryAdapter();
		return adapter.marshal(bytes);
	}


	
}
