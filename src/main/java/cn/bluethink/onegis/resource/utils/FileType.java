package cn.bluethink.onegis.resource.utils;

import java.util.Arrays;
import java.util.List;

public enum FileType {

	PICTURE_FILE(
			Arrays.asList(new String[] {".jpg",".png"}),
			"仅支持jpg或png格式"
			),
	COMPRESSED_FILE(
			Arrays.asList(new String[] {".zip",".rar"}),
			"仅支持zip或rar格式"
			);
	
	private final List<String> supportedType;
	
	private final String message;

	FileType(List<String> supportedType, String message) {
		this.supportedType = supportedType;
		this.message = message;
	}

	public List<String> getSupportedType() {
		return supportedType;
	}
	
	public String getMessage() {
		return message;
	}
}
