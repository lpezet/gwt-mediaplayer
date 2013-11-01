package net.sf.video4j.gwt.client.util;

import net.sf.video4j.gwt.client.model.ApplicationBean;

import com.google.web.bindery.autobean.shared.AutoBean;
import com.google.web.bindery.autobean.shared.AutoBeanFactory;

/**
 * @author Gustavo Matias
 *
 */
public interface ApplicationBeanFactory extends AutoBeanFactory {
	AutoBean<ApplicationBean> makeBean();
}
