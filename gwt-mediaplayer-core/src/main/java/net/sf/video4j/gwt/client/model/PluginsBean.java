package net.sf.video4j.gwt.client.model;

import java.util.List;
import java.util.Map;

import com.google.web.bindery.autobean.shared.AutoBean.PropertyName;

/**
 * @author Gustavo Matias
 *
 */
public interface PluginsBean {
	@PropertyName("plugins")
	List<Map<String, Map<String, String>>> getPlugins();
	@PropertyName("plugins")
	void setPlugins(List<Map<String, Map<String, String>>> pPlugins);
}
