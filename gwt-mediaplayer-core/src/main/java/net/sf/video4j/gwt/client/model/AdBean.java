package net.sf.video4j.gwt.client.model;

import com.google.web.bindery.autobean.shared.AutoBean.PropertyName;

/**
 * @author Gustavo Matias
 *
 */
public interface AdBean {
	@PropertyName("vast_tag")
	String getVastTag();
	@PropertyName("vast_tag")
	void setVastTag(String pVastTag);
}
