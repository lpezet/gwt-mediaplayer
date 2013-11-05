/**
 * 
 */
package net.sf.video4j.gwt.client.util;

import net.sf.video4j.gwt.client.model.IPlayItemBean;

import com.google.web.bindery.autobean.shared.AutoBean;
import com.google.web.bindery.autobean.shared.AutoBeanFactory;

/**
 * @author luc
 *
 */
public interface PlayItemBeanFactory extends AutoBeanFactory {
	AutoBean<IPlayItemBean> makeBean();
}
