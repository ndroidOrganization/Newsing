package com.utils;

import android.text.TextUtils;
import android.util.Log;

public class Base64Util
{
	private final static Class<Base64Util> TAG = Base64Util.class;
	private final static char[] ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".toCharArray();

	private static int[] toInt = new int[128];

	static
	{
		for (int i = 0; i < ALPHABET.length; i++)
		{
			toInt[ALPHABET[i]] = i;
		}
	}

	/**
	 * Translates the specified byte array into Base64 string.
	 *
	 * @param s the byte array (not null)
	 * @return the translated Base64 string (not null)
	 */
	public static String encode(String s)
	{
		if (TextUtils.isEmpty(s))
			return null;

		byte[] buf = s.getBytes();
		int size = buf.length;
		char[] ar = new char[((size + 2) / 3) * 4];
		int a = 0;
		int i = 0;
		while (i < size)
		{
			byte b0 = buf[i++];
			byte b1 = (i < size) ? buf[i++] : 0;
			byte b2 = (i < size) ? buf[i++] : 0;

			int mask = 0x3F;
			ar[a++] = ALPHABET[(b0 >> 2) & mask];
			ar[a++] = ALPHABET[((b0 << 4) | ((b1 & 0xFF) >> 4)) & mask];
			ar[a++] = ALPHABET[((b1 << 2) | ((b2 & 0xFF) >> 6)) & mask];
			ar[a++] = ALPHABET[b2 & mask];
		}
		switch (size % 3)
		{
		case 1:
			ar[--a] = '=';
		case 2:
			ar[--a] = '=';
		}
		return new String(ar);
	}

	/**
	 * Translates the specified Base64 string into a byte array.
	 *
	 * @param s the Base64 string (not null)
	 * @return the byte array (not null)
	 */
	private static byte[] decode_(String s)
	{
		int delta = s.endsWith("==") ? 2 : s.endsWith("=") ? 1 : 0;
		byte[] buffer = new byte[s.length() * 3 / 4 - delta];
		int mask = 0xFF;
		int index = 0;
		for (int i = 0; i < s.length(); i += 4)
		{
			int c0 = toInt[s.charAt(i)];
			int c1 = toInt[s.charAt(i + 1)];
			buffer[index++] = (byte) (((c0 << 2) | (c1 >> 4)) & mask);
			if (index >= buffer.length)
			{
				return buffer;
			}
			int c2 = toInt[s.charAt(i + 2)];
			buffer[index++] = (byte) (((c1 << 4) | (c2 >> 2)) & mask);
			if (index >= buffer.length)
			{
				return buffer;
			}
			int c3 = toInt[s.charAt(i + 3)];
			buffer[index++] = (byte) (((c2 << 6) | c3) & mask);
		}
		return buffer;
	}

	public static String decode(String s)
	{
		if (TextUtils.isEmpty(s))
			return null;

		String ss = new String(Base64Util.decode_(s));
		return ss;
	}

	public static void main(String[] args)
	{
		String a = Base64Util.encode("1460");
		Log.i("TAG", a);
		Log.i("TAG", Base64Util.decode(a));
	}
}