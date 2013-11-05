package net.sf.video4j.gwt.client.util;

import java.util.List;

import net.sf.video4j.gwt.client.model.IPlayItemBean;

import com.google.web.bindery.autobean.shared.AutoBean;
import com.google.web.bindery.autobean.shared.AutoBeanFactory;

/**
 * @author Gustavo Matias
 */
public interface PlaylistBeanFactory extends AutoBeanFactory {
	AutoBean<List<IPlayItemBean>> makeBean();
}
