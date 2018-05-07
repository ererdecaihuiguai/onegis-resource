package cn.bluethink.onegis.resource.utils;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;

public class BtResult implements Serializable {

	private static final long serialVersionUID = 4361994691695377374L;
	
	private int status;
	
	private String message;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Object data;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String[] exception;
	
	private Date timestamp;
	
	public BtResult() { }

	public BtResult(int status, String message) {
		this.status = status;
		this.message = message;
		this.timestamp = new Date();
	}

	public BtResult(int status, String message, Object data) {
		this(status, message);
		this.data = data;
		
	}

	public BtResult(int status, String message, String[] exception) {
		this(status, message);
		this.exception = exception;
	}
	
	public static BtResult OK(){
		return new BtResult(200, "执行成功");
	}
	
	public static BtResult OK(Object data){
		return new BtResult(200, "执行成功", data);
	}
	
	public static BtResult ERROR(){
		return new BtResult(500, "服务器错误");
	}
	
	public static BtResult ERROR(String[] exception){
		return new BtResult(500, "服务器错误", exception);
	}
	
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String[] getException() {
		return exception;
	}

	public void setException(String[] exception) {
		this.exception = exception;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	
}
