/**
 * 
 */
package net.sf.video4j.gwt.client.util;

import com.google.gwt.json.client.JSONObject;
import com.google.web.bindery.autobean.shared.AutoBean;
import com.google.web.bindery.autobean.shared.AutoBeanCodex;
import com.google.web.bindery.autobean.shared.AutoBeanFactory;

/**
 * @author luc
 *
 */
public class BeanFactory<S, T extends AutoBeanFactory> {

	private Class<S> mClass;
	private T mObjectFactory;

	public BeanFactory(Class<S> pClass, T pObjectFactory) {
		mClass = pClass;
		mObjectFactory = pObjectFactory;
	}
	
	public S makeFrom(JSONObject pObject) {
		AutoBean<S> autoBeanCloneAB = AutoBeanCodex.decode(mObjectFactory, mClass, pObject.toString() );
		return autoBeanCloneAB.as();
	}

}
