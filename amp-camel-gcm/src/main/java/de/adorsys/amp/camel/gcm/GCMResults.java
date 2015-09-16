package de.adorsys.amp.camel.gcm;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GCMResults {
	public static class GCMResult {
		@JsonProperty("message_id")
		private String messageId;
		
		@JsonProperty("error")
		private String error;

		public String getMessageId() {
			return messageId;
		}

		public void setMessageId(String messageId) {
			this.messageId = messageId;
		}

		public String getError() {
			return error;
		}

		public void setError(String error) {
			this.error = error;
		}
		
		
	}
	
	@JsonProperty("multicast_id")
	private long multicastId;
	@JsonProperty("success")
	private int success;
	@JsonProperty("failure")
	private int failure;
	@JsonProperty("canonical_ids")
	private int canonicalIds;
	private List<GCMResult> results;
	public long getMulticastId() {
		return multicastId;
	}
	public void setMulticastId(long multicastId) {
		this.multicastId = multicastId;
	}
	public int getSuccess() {
		return success;
	}
	public void setSuccess(int success) {
		this.success = success;
	}
	public int getFailure() {
		return failure;
	}
	public void setFailure(int failure) {
		this.failure = failure;
	}
	public int getCanonicalIds() {
		return canonicalIds;
	}
	public void setCanonicalIds(int canonicalIds) {
		this.canonicalIds = canonicalIds;
	}
	public List<GCMResult> getResults() {
		return results;
	}
	public void setResults(List<GCMResult> results) {
		this.results = results;
	}
	
	
}
