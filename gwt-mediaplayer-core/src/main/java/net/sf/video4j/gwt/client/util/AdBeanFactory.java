package net.sf.video4j.gwt.client.util;

import net.sf.video4j.gwt.client.model.AdBean;

import com.google.web.bindery.autobean.shared.AutoBean;
import com.google.web.bindery.autobean.shared.AutoBeanFactory;

/**
 * @author Gustavo Matias
 *
 */
public interface AdBeanFactory extends AutoBeanFactory {
	AutoBean<AdBean> makeBean();
}
