package net.sf.video4j.gwt.client.model;

import com.google.web.bindery.autobean.shared.AutoBean.PropertyName;

/**
 * @author Gustavo Matias
 *
 */
public interface ICommonBean {
	String getWidth();
	void setWidth(String pWidth);
	
	String getHeight();
	void setHeight(String pHeight);
	
	@PropertyName("auto_play")
	boolean getAutoPlay();
	void setAutoPlay(boolean pAutoPlay);
}
