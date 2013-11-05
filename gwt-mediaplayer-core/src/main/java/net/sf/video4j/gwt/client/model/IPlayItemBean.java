package net.sf.video4j.gwt.client.model;

import com.google.web.bindery.autobean.shared.AutoBean.PropertyName;


/**
 * @author Gustavo Matias
 *
 */
public interface IPlayItemBean {
	@PropertyName("url")
	void setURL(String pURL);
	@PropertyName("url")
	String getURL();
}
