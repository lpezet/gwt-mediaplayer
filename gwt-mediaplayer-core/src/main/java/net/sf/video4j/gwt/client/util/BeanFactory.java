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
	/*
	public List<S> makeFrom(JSONArray pArray) {
		List<S> oResult = new ArrayList<S>();
		for (int i = 0; i < pArray.size(); i++) {
			AutoBean<S> oAutoBean = AutoBeanCodex.decode(mObjectFactory, mClass, pArray.get(i).toString() );
			oResult.add(oAutoBean.as());
		}
		return oResult;
	}
	*/
	public AutoBean<S> makeABFrom(JSONObject pObject) {
		return AutoBeanCodex.decode(mObjectFactory, mClass, pObject.toString() );
	}
	/*
	public List<AutoBean<S>> makeABFrom(JSONArray pArray) {
		List<AutoBean<S>> oResult = new ArrayList<AutoBean<S>>();
		for (int i = 0; i < pArray.size(); i++) {
			AutoBean<S> oAutoBean = AutoBeanCodex.decode(mObjectFactory, mClass, pArray.get(i).toString() );
			oResult.add(oAutoBean);
		}
		return oResult;
	}
	*/
}
