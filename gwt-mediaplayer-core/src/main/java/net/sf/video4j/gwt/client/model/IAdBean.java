package net.sf.video4j.gwt.client.model;

import com.google.web.bindery.autobean.shared.AutoBean.PropertyName;

/**
 * @author Gustavo Matias
 *
 */
public interface IAdBean {
	@PropertyName("url")
	String getURL();
	@PropertyName("url")
	void setURL(String pURL);
	
	@PropertyName("lr_publisher_id")
	String getPublisherId();
	@PropertyName("lr_publisher_id")
	void setPublisherId(String pId);
	
	@PropertyName("lr_video_position")
	String getVideoPosition();
	@PropertyName("lr_video_position")
	void setVideoPosition(String pPos);
	
	@PropertyName("lr_ad_unit")
	String getAdUnit();
	@PropertyName("lr_ad_unit")
	void setAdUnit(String pValue);
	
	@PropertyName("lr_ad_message")
	String getAdMessage();
	@PropertyName("lr_ad_message")
	void setAdMessage(String pMessage);
	
	@PropertyName("lr_verticals")
	String getVerticals();
	@PropertyName("lr_verticals")
	void setVerticals(String pVerticals);
	
	@PropertyName("lr_video_id")
	String getVideoId();
	@PropertyName("lr_video_id")
	void setVideoId(String pId);
	
	@PropertyName("lr_autoplay")
	boolean getAutoplay();
	@PropertyName("lr_autoplay")
	void setAutoplay(boolean pAutoplay);
	
	@PropertyName("lr_tags")
	String getTags();
	@PropertyName("lr_tags")
	void setTags(String pTag);
	
}
