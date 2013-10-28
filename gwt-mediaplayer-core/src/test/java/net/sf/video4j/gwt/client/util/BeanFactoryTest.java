/**
 * 
 */
package net.sf.video4j.gwt.client.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.json.client.JSONBoolean;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.google.web.bindery.autobean.shared.AutoBean;
import com.google.web.bindery.autobean.shared.AutoBeanFactory;
import com.googlecode.gwt.test.GwtModule;
import com.googlecode.gwt.test.GwtTest;

/**
 * @author luc
 *
 */
@GwtModule("net.sf.video4j.gwt.client.util.BeanFactory")
public class BeanFactoryTest extends GwtTest {
	
	public interface MyBean {
		String getName();
		void setName(String pName);
		boolean getVegeterian();
		void setVegetarian(boolean pVegetarian);
	}
	
	public interface MySecondBean {
		String getFirstName();
		void setFirstName(String pFirstName);
		String getLastName();
		void setLastName(String pLastName);
	}
	
	public interface MyBeanFactory extends AutoBeanFactory {
		AutoBean<MyBean> makeBean();
		AutoBean<MySecondBean> makeSecondBean();
	}
	

	@Test
	public void make() throws Exception {
		MyBeanFactory oFactory = GWT.create(MyBeanFactory.class);
		
		JSONObject oJSON = new JSONObject();
		oJSON.put("name", new JSONString("luc"));
		oJSON.put("vegetarian", JSONBoolean.getInstance(false));
				
		MyBean oBean = new BeanFactory<MyBean, AutoBeanFactory>(MyBean.class, oFactory).makeFrom(oJSON);
		assertNotNull(oBean);
		assertEquals("luc", oBean.getName());
		assertEquals(false, oBean.getVegeterian());
	}

}
