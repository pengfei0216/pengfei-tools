package org.yelong.tools.file.word;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * word转换为PDF文件工具类
 * 
 * @author PengFei
 * @date 2021年4月22日 下午5:34:36
 */
public interface WordToPDFTools {

	// ==================================================word转PDF==================================================

	/**
	 * 将word文件转换为PDF文件
	 * 
	 * @date 2021年4月22日 下午5:35:55
	 * @param inputStream  word文件流
	 * @param outputStream 转换为PDF后输入到的流
	 */
	default void docToPDF(InputStream inputStream, OutputStream outputStream) throws IOException {
		wordToPDF("doc", inputStream, outputStream);
	}

	/**
	 * 将word文件转换为PDF文件
	 * 
	 * @date 2021年4月22日 下午5:35:55
	 * @param inputStream  word文件流
	 * @param outputStream 转换为PDF后输入到的流
	 */
	default void docxToPDF(InputStream inputStream, OutputStream outputStream) throws IOException {
		wordToPDF("docx", inputStream, outputStream);
	}

	/**
	 * 将word文件转换为PDF文件
	 * 
	 * @date 2021年4月22日 下午5:35:55
	 * @param wordType     word文件格式。一般分为 doc、docx
	 * @param inputStream  word文件流
	 * @param outputStream 转换为PDF后输入到的流
	 */
	void wordToPDF(String wordType, InputStream inputStream, OutputStream outputStream) throws IOException;

}
