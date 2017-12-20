package com.lionkwon.kwonutils.security.coder;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public abstract class CharacterDecoder {
	/**
	 * Decodes the specified InputStream and writes result onto
	 * the OutputStream.
	 * @param in                  encoded input
	 * @param out                 decoded output
	 * @throws IOException        if an I/O error has occurred.
	 */
	public abstract void decodeBuffer(InputStream in, OutputStream out) throws IOException;

	/** Decode the specified String and return result as byte array. */
	public byte[] decodeBuffer(String s) {
		byte[] buf = ByteArray.getBytes(s);
		ByteArrayInputStream bin = new ByteArrayInputStream(buf);
		ByteArrayOutputStream bout = new ByteArrayOutputStream(buf.length);
		try { decodeBuffer(bin, bout); } catch (IOException e) {}
		return bout.toByteArray();
	}

	/**
	 * Decode the InputStream and return result as byte array.
	 * @throws IOException        if an I/O error has occurred.
	 */
	public byte[] decodeBuffer(InputStream in) throws IOException {
		ByteArrayOutputStream bout = new ByteArrayOutputStream(512);
		decodeBuffer(in, bout);
		return bout.toByteArray();
	}
}
