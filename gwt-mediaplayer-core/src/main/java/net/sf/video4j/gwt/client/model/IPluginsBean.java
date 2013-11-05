package net.sf.video4j.gwt.client.model;

import java.util.Map;

import com.google.web.bindery.autobean.shared.AutoBean.PropertyName;

/**
 * @author Gustavo Matias
 *
 */
public interface IPluginsBean {
	@PropertyName("plugins")
	Map<String, Map<String, String>> getPlugins();
	@PropertyName("plugins")
	void setPlugins(Map<String, Map<String, String>> pPlugins);
}
