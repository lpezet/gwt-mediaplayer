package net.sf.video4j.gwt.client.util;

import java.util.Map;

import com.google.web.bindery.autobean.shared.AutoBean;
import com.google.web.bindery.autobean.shared.AutoBeanFactory;

/**
 * @author Gustavo Matias
 */
public interface PluginsBeanFactory extends AutoBeanFactory {
	AutoBean<Map<String, Map<String, String>>> makeBean();
}
