package com.bjb.common;

import java.util.ResourceBundle;

/**
 * 常量
 * 
 * @author CAOZQ
 * @since 2016/08/17
 */
public class Constants {
	public static final ResourceBundle bundle = ResourceBundle
			.getBundle("application");
	public static final String API_RETURN_STATUS_SUCCESS = "1";
	public static final String API_RETURN_STATUS_ERROR = "0";
	public static final String DATASOURCE_DRIVER = bundle
			.getString("datasource_driver");
	public static final String DATASOURCE_URL = bundle
			.getString("datasource_url");
	public static final String DATASOURCE_USERNAME = bundle
			.getString("datasource_username");
	public static final String DATASOURCE_PASSWORD = bundle
			.getString("datasource_password");
	public static final String SCREENSHOT_PATH = bundle
			.getString("screenshot_path");
	// {后缀名，MIME类型}
	public static final String[] MIME_Book = { "doc", "docx", "xls", "xlsx",
			"pdf", "ppt", "pptx", "txt", "epub" };
	// {后缀名，MIME类型}
	public static final String[] MIME_Video = { "mp4", "avi", "flv", "mov",
			"ts", "rmvb", "mkv" };
	// {后缀名，MIME类型}
	public static final String[] MIME_Audio = { "mp3", "wma", "wav", "flac",
			"m4a" };
	// {后缀名，MIME类型}
	public static final String[] MIME_Img = { "bmp", "gif", "png", "jpeg",
			"jpg" };
}
