package net.sf.video4j.gwt.client.util;

import net.sf.video4j.gwt.client.model.IAdBean;

import com.google.web.bindery.autobean.shared.AutoBean;
import com.google.web.bindery.autobean.shared.AutoBeanFactory;

/**
 * @author Gustavo Matias
 *
 */
public interface IAdBeanFactory extends AutoBeanFactory {
	AutoBean<IAdBean> makeBean();
}
