/**
 * 
 */
package de.adorsys.amp.gcm.client;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author sso
 *
 */
public class GCMMessage {

	private Map<String, String> data = new HashMap<>();
	@JsonInclude(Include.NON_NULL)
	private String to;
	@JsonInclude(Include.NON_NULL)
	@JsonProperty("registration_ids")
	private String[] registrationIds;
	@JsonInclude(Include.NON_NULL)
	@JsonProperty("collapse_key")
	private String collapseKey;
	@JsonProperty("dry_run")
	private boolean dryRun;
	@JsonProperty("content_available")
	private boolean contentAvailable;
	@JsonProperty("priority")
	private String priority;

	@JsonInclude(Include.NON_NULL)
	@JsonProperty("time_to_live")
	private int timeToLive;

	@JsonInclude(Include.NON_NULL)
	private GCMNotification notification;

	public Map<String, String> getData() {
		return data;
	}

	public void setData(Map<String, String> data) {
		this.data = data;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getCollapseKey() {
		return collapseKey;
	}

	public void setCollapseKey(String collapseKey) {
		this.collapseKey = collapseKey;
	}

	public boolean isDryRun() {
		return dryRun;
	}

	public void setDryRun(boolean dryRun) {
		this.dryRun = dryRun;
	}

	public int getTimeToLive() {
		return timeToLive;
	}

	public void setTimeToLive(int timeToLive) {
		this.timeToLive = timeToLive;
	}

	public GCMNotification getNotification() {
		return notification;
	}

	public void setNotification(GCMNotification notification) {
		this.notification = notification;
	}

	public String[] getRegistrationIds() {
		return registrationIds;
	}

	public void setRegistrationIds(String[] registrationIds) {
		this.registrationIds = registrationIds;
	}

	public boolean isContentAvailable() {
		return contentAvailable;
	}

	public void setContentAvailable(boolean contentAvailable) {
		this.contentAvailable = contentAvailable;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public static class GCMNotification {
		private String title;
		@JsonInclude(Include.NON_NULL)
		private String body;
		private String icon;
		@JsonInclude(Include.NON_NULL)
		private String sound;
		@JsonInclude(Include.NON_NULL)
		private String badge;
		@JsonInclude(Include.NON_NULL)
		private String tag;
		@JsonInclude(Include.NON_NULL)
		private String color;

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getBody() {
			return body;
		}

		public void setBody(String body) {
			this.body = body;
		}

		public String getIcon() {
			return icon;
		}

		public void setIcon(String icon) {
			this.icon = icon;
		}

		public String getSound() {
			return sound;
		}

		public void setSound(String sound) {
			this.sound = sound;
		}

		public String getBadge() {
			return badge;
		}

		public void setBadge(String badge) {
			this.badge = badge;
		}

		public String getTag() {
			return tag;
		}

		public void setTag(String tag) {
			this.tag = tag;
		}

		public String getColor() {
			return color;
		}

		public void setColor(String color) {
			this.color = color;
		}
	}
}
