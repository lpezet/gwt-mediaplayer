package net.sf.video4j.gwt.client.util;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import net.sf.video4j.gwt.client.model.ApplicationConfig;
import net.sf.video4j.gwt.client.model.ICommonBean;

import org.junit.Test;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.json.client.JSONBoolean;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.google.web.bindery.autobean.shared.AutoBeanFactory;
import com.googlecode.gwt.test.GwtModule;
import com.googlecode.gwt.test.GwtTest;

/**
 * @author Gustavo Matias
 */
@GwtModule("net.sf.video4j.gwt.client.util.BeanFactory")
public class CommonBeanFactoryTest extends GwtTest {

	@Test
	public void givenCommonBeanClassAndFactory_shouldMakeCommonBeanFromJSON() throws Exception {
		ApplicationBeanFactory oFactory = GWT.create(ApplicationBeanFactory.class);

		ApplicationConfig oApplicationConfig = new ApplicationConfig();
		JSONObject oCommon = new JSONObject();
		oCommon.put("auto_play", JSONBoolean.getInstance(true));
		oCommon.put("width", new JSONString("800px"));
		oCommon.put("height", new JSONString("600px"));
		oApplicationConfig.setCommon(oCommon);

		ICommonBean oBean = new BeanFactory<ICommonBean, AutoBeanFactory>(ICommonBean.class, oFactory).makeFrom(oApplicationConfig.getCommon());

		String oCommonJSONString = 
				"{"
					+ "\"auto_play\":true, "
					+ "\"width\":\"800px\", "
					+ "\"height\":\"600px\""
				+ "}";
		assertThat(oApplicationConfig.getCommon().toString(), equalTo(oCommonJSONString));
		assertThat(oBean.getAutoPlay(), equalTo(true));
		assertThat(oBean.getWidth(), equalTo("800px"));
		assertThat(oBean.getHeight(), equalTo("600px"));
	}

}
