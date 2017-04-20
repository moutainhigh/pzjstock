package com.pzj.core.common.utils;

import java.util.zip.CRC32;

public class CRCUtils {

	public static Long convertUniqueLong(String bussName) {
		Long uniq = 0L;
		if (bussName == null || "".equals(bussName.trim())) {
			return uniq;
		}
		CRC32 crc32 = new CRC32();
		crc32.update(bussName.getBytes());
		uniq = crc32.getValue();
		return uniq;
	}

	public static void main(String[] args) {
		//3143679811 3143679811	"2216619741563799" + "6" + "20"
		//2660656306 2660656306 "2216619741563799" + "6" + "18"
		String bussName = "2216619741563799" + "6" + "18";
		System.out.println(convertUniqueLong(bussName));

		//2132076769
		System.out.println("max:" + Integer.MAX_VALUE);

	}
}
