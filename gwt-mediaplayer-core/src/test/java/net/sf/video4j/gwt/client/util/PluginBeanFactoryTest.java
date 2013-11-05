package net.sf.video4j.gwt.client.util;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.ArrayList;

import net.sf.video4j.gwt.client.model.ApplicationConfig;
import net.sf.video4j.gwt.client.model.IPluginsBean;

import org.junit.Ignore;
import org.junit.Test;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.google.web.bindery.autobean.shared.AutoBeanFactory;
import com.googlecode.gwt.test.GwtModule;
import com.googlecode.gwt.test.GwtTest;

/**
 * @author Gustavo Matias
 */
@GwtModule("net.sf.video4j.gwt.client.util.BeanFactory")
public class PluginBeanFactoryTest extends GwtTest {

	@Ignore
	@Test
	public void givenPluginAsJSON_shouldMakePluginsConfigBeanFromJSON() throws Exception {
		PluginsBeanFactory oFactory = GWT.create(PluginsBeanFactory.class);

		ApplicationConfig oApplicationConfig = new ApplicationConfig();
		JSONObject oPlugin = new JSONObject();
		JSONObject oPlugin1Properties = new JSONObject();
		oPlugin1Properties.put("publisher_id", new JSONString("11111"));
		oPlugin1Properties.put("layout_sking_message", new JSONString("Advertisement. Video will resume in {COUNTDOWN} seconds."));
		oPlugin.put("net.sf.video4j.gwt.plugins.ads.liverail", oPlugin1Properties);
		oApplicationConfig.setPlugins(oPlugin);

		IPluginsBean oBean = new BeanFactory<IPluginsBean, AutoBeanFactory>(IPluginsBean.class, oFactory).makeFrom(oApplicationConfig.getPlugins());

		String oPluginsJSONString = 
				"{\"net.sf.video4j.gwt.plugins.ads.liverail\":"
					+ "{"
						+ "\"publisher_id\":\"11111\", "
						+ "\"layout_sking_message\":\"Advertisement. Video will resume in {COUNTDOWN} seconds.\""
					+ "}"
				+ "}";
		assertThat(oApplicationConfig.getPlugins().toString(), equalTo(oPluginsJSONString));
		assertThat(new ArrayList<String>(oBean.getPlugins().keySet()).get(0),
				equalTo("net.sf.video4j.gwt.plugins.ads.liverail"));
		assertThat(oBean.getPlugins().get("net.sf.video4j.gwt.plugins.ads.liverail").size(), equalTo(2));
		assertThat(oBean.getPlugins().get("net.sf.video4j.gwt.plugins.ads.liverail").get("publisher_id"), equalTo("11111"));
		assertThat(oBean.getPlugins().get("net.sf.video4j.gwt.plugins.ads.liverail").get("layout_sking_message"),
				equalTo("Advertisement. Video will resume in {COUNTDOWN} seconds."));
	}

//	@Test
//	public void givenMultiplePluginsAsJSON_shouldMakePluginsConfigBeanFromJSON() throws Exception {
//		PluginsBeanFactory oFactory = GWT.create(PluginsBeanFactory.class);
//
//		ApplicationConfig oApplicationConfig = new ApplicationConfig();
//		JSONObject oPlugins = new JSONObject();
//		JSONArray oPluginsArray = new JSONArray();
//
//		JSONObject oPlugin1 = new JSONObject();
//		JSONObject oPlugin1Properties = new JSONObject();
//		oPlugin1Properties.put("publisher_id", new JSONString("11111"));
//		oPlugin1Properties.put("layout_sking_message", new JSONString("Advertisement. Video will resume in {COUNTDOWN} seconds."));
//		oPlugin1.put("net.sf.video4j.gwt.plugins.ads.liverail", oPlugin1Properties);
//		oPluginsArray.set(0, oPlugin1);
//
//		JSONObject oPlugin2 = new JSONObject();
//		JSONObject oPlugin2Properties = new JSONObject();
//		oPlugin2Properties.put("publisher_id", new JSONString("22222"));
//		oPlugin2Properties.put("layout_sking_message", new JSONString("Advertisement. Video coming in {COUNTDOWN} seconds."));
//		oPlugin2.put("net.sf.video4j.gwt.plugins.ads.microsoft", oPlugin2Properties);
//		oPluginsArray.set(1, oPlugin2);
//
//		oPlugins.put("plugins", oPluginsArray);
//		oApplicationConfig.setPlugins(oPlugins);
//
//		IPluginsBean oBean = new BeanFactory<IPluginsBean, AutoBeanFactory>(IPluginsBean.class, oFactory).makeFrom(oApplicationConfig.getPlugins());
//
//		String oPluginsJSONString =
//				"{\"plugins\":["
//						+ "{\"net.sf.video4j.gwt.plugins.ads.liverail\":"
//							+ "{"
//								+ "\"publisher_id\":\"11111\", "
//								+ "\"layout_sking_message\":\"Advertisement. Video will resume in {COUNTDOWN} seconds.\""
//							+ "}"
//						+ "},"
//						+ "{\"net.sf.video4j.gwt.plugins.ads.microsoft\":"
//							+ "{"
//								+ "\"publisher_id\":\"22222\", "
//								+ "\"layout_sking_message\":\"Advertisement. Video coming in {COUNTDOWN} seconds.\""
//							+ "}"
//						+ "}"
//					+ "]"
//				+ "}";
//		assertThat(oApplicationConfig.getPlugins().toString(), equalTo(oPluginsJSONString));
//		assertThat(new ArrayList<String>(oBean.getPlugins().get(0).keySet()).get(0),
//				equalTo("net.sf.video4j.gwt.plugins.ads.liverail"));
//		assertThat(oBean.getPlugins().get(0).get("net.sf.video4j.gwt.plugins.ads.liverail").size(), equalTo(2));
//		assertThat(oBean.getPlugins().get(0).get("net.sf.video4j.gwt.plugins.ads.liverail").get("publisher_id"), equalTo("11111"));
//		assertThat(oBean.getPlugins().get(0).get("net.sf.video4j.gwt.plugins.ads.liverail").get("layout_sking_message"),
//				equalTo("Advertisement. Video will resume in {COUNTDOWN} seconds."));
//
//		assertThat(new ArrayList<String>(oBean.getPlugins().get(1).keySet()).get(0),
//				equalTo("net.sf.video4j.gwt.plugins.ads.microsoft"));
//		assertThat(oBean.getPlugins().get(1).get("net.sf.video4j.gwt.plugins.ads.microsoft").size(), equalTo(2));
//		assertThat(oBean.getPlugins().get(1).get("net.sf.video4j.gwt.plugins.ads.microsoft").get("publisher_id"), equalTo("22222"));
//		assertThat(oBean.getPlugins().get(1).get("net.sf.video4j.gwt.plugins.ads.microsoft").get("layout_sking_message"),
//				equalTo("Advertisement. Video coming in {COUNTDOWN} seconds."));
//	}

}
