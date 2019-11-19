/**
 * 
 */
package com.course.practicaljava.rest.domain;

/**
 * @author steph
 *
 */
public class ErrorResponse {
	
	private String message;
	
	private long timestamp;

	public ErrorResponse() {
		super();
	}

	public ErrorResponse(String message, long timestamp) {
		super();
		this.message = message;
		this.timestamp = timestamp;
	}

	public String getMessage() {
		return message;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	
	@Override
	public String toString() {
		return "ErrorResponse [message=" + message + ", timestamp=" + timestamp + "]";
	}

}
