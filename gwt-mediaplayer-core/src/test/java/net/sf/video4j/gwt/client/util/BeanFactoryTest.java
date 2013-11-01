/**
 * 
 */
package net.sf.video4j.gwt.client.util;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;

import net.sf.video4j.gwt.client.model.AdBean;
import net.sf.video4j.gwt.client.model.ApplicationBean;
import net.sf.video4j.gwt.client.model.PlaylistBean;
import net.sf.video4j.gwt.client.model.PluginsBean;

import org.junit.Test;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.json.client.JSONArray;
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

	@Test
	public void givenAd_shouldMakeAdConfigBeanFromJSON() throws Exception {
		AdBeanFactory oFactory = GWT.create(AdBeanFactory.class);

		JSONObject oJSON = new JSONObject();
		oJSON.put("vast_tag", new JSONString("http://abc.com/123"));

		AdBean oBean = new BeanFactory<AdBean, AutoBeanFactory>(AdBean.class, oFactory).makeFrom(oJSON);

		assertThat(oBean.getVastTag(), equalTo("http://abc.com/123"));
	}

	@Test
	public void givenApplicationBeanClassAndFactory_shouldMakeApplicationConfigBeanFromJSON() throws Exception {
		ApplicationBeanFactory oFactory = GWT.create(ApplicationBeanFactory.class);

		JSONObject oJSON = new JSONObject();
		oJSON.put("width", new JSONString("800px"));
		oJSON.put("height", new JSONString("600px"));
		oJSON.put("auto_play", JSONBoolean.getInstance(true));

		ApplicationBean oBean = new BeanFactory<ApplicationBean, AutoBeanFactory>(ApplicationBean.class, oFactory).makeFrom(oJSON);

		assertThat(oBean.getWidth(), equalTo("800px"));
		assertThat(oBean.getHeight(), equalTo("600px"));
		assertThat(oBean.getAutoPlay(), equalTo(true));
	}

	@Test
	public void givenPlaylist_shouldMakePlaylistConfigBeanFromJSON() throws Exception {
		PlaylistBeanFactory oFactory = GWT.create(PlaylistBeanFactory.class);

		JSONObject oJSON = new JSONObject();
		JSONArray oPlayListJSONArray = new JSONArray();
		JSONObject oPlayItemJSONObject1 = new JSONObject();
		oPlayItemJSONObject1.put("url", new JSONString("http://videos.tripfilms.com/360p/4BAE2BA4EE543703AF8BC2DF6550BB73.mp4"));
		oPlayListJSONArray.set(0, oPlayItemJSONObject1);
		JSONObject oPlayItemJSONObject2 = new JSONObject();
		oPlayItemJSONObject2.put("url", new JSONString("http://videos.tripfilms.com/480p/ECAA41D5E30E4BECC13FC1D3DA823750.mp4"));
		oPlayListJSONArray.set(1, oPlayItemJSONObject2);
		oJSON.put("playlist", oPlayListJSONArray);

		PlaylistBean oBean = new BeanFactory<PlaylistBean, AutoBeanFactory>(PlaylistBean.class, oFactory).makeFrom(oJSON);

		assertThat(oBean.getPlayItems().get(0).getURL(), equalTo("http://videos.tripfilms.com/360p/4BAE2BA4EE543703AF8BC2DF6550BB73.mp4"));
		assertThat(oBean.getPlayItems().get(1).getURL(), equalTo("http://videos.tripfilms.com/480p/ECAA41D5E30E4BECC13FC1D3DA823750.mp4"));
	}

	@Test
	public void givenOnePlugin_shouldMakePluginsConfigBeanFromJSON() throws Exception {
		PluginsBeanFactory oFactory = GWT.create(PluginsBeanFactory.class);

		JSONObject oJSON = new JSONObject();
		JSONArray oPluginsJSONArray = new JSONArray();
		JSONObject oPlugin1 = new JSONObject();
		JSONObject oPluginProperties = new JSONObject();
		oPluginProperties.put("publisher_id", new JSONString("11111"));
		oPluginProperties.put("layout_sking_message", new JSONString("Advertisement. Video will resume in {COUNTDOWN} seconds."));
		oPlugin1.put("net.sf.video4j.gwt.plugins.ads.liverail", oPluginProperties);
		oPluginsJSONArray.set(0, oPlugin1);
		oJSON.put("plugins", oPluginsJSONArray);

		PluginsBean oBean = new BeanFactory<PluginsBean, AutoBeanFactory>(PluginsBean.class, oFactory).makeFrom(oJSON);

		assertThat(new ArrayList<String>(oBean.getPlugins().get(0).keySet()).get(0), 
				equalTo("net.sf.video4j.gwt.plugins.ads.liverail"));
		assertThat(oBean.getPlugins().get(0).get("net.sf.video4j.gwt.plugins.ads.liverail").size(), equalTo(2));
		assertThat(oBean.getPlugins().get(0).get("net.sf.video4j.gwt.plugins.ads.liverail").get("publisher_id"), equalTo("11111"));
		assertThat(oBean.getPlugins().get(0).get("net.sf.video4j.gwt.plugins.ads.liverail").get("layout_sking_message"),
				equalTo("Advertisement. Video will resume in {COUNTDOWN} seconds."));
	}

	@Test
	public void givenMultiplePlugins_shouldMakePluginsConfigBeanFromJSON() throws Exception {
		PluginsBeanFactory oFactory = GWT.create(PluginsBeanFactory.class);

		JSONObject oJSON = new JSONObject();
		JSONArray oPluginsJSONArray = new JSONArray();

		JSONObject oPlugin1 = new JSONObject();
		JSONObject oPlugin1Properties = new JSONObject();
		oPlugin1Properties.put("publisher_id", new JSONString("11111"));
		oPlugin1Properties.put("layout_sking_message", new JSONString("Advertisement. Video will resume in {COUNTDOWN} seconds."));
		oPlugin1.put("net.sf.video4j.gwt.plugins.ads.liverail", oPlugin1Properties);
		oPluginsJSONArray.set(0, oPlugin1);

		JSONObject oPlugin2 = new JSONObject();
		JSONObject oPlugin2Properties = new JSONObject();
		oPlugin2Properties.put("publisher_id", new JSONString("22222"));
		oPlugin2Properties.put("layout_sking_message", new JSONString("Advertisement. Video coming in {COUNTDOWN} seconds."));
		oPlugin2.put("net.sf.video4j.gwt.plugins.ads.microsoft", oPlugin2Properties);
		oPluginsJSONArray.set(1, oPlugin2);

		oJSON.put("plugins", oPluginsJSONArray);

		PluginsBean oBean = new BeanFactory<PluginsBean, AutoBeanFactory>(PluginsBean.class, oFactory).makeFrom(oJSON);

		assertThat(new ArrayList<String>(oBean.getPlugins().get(0).keySet()).get(0),
				equalTo("net.sf.video4j.gwt.plugins.ads.liverail"));
		assertThat(oBean.getPlugins().get(0).get("net.sf.video4j.gwt.plugins.ads.liverail").size(), equalTo(2));
		assertThat(oBean.getPlugins().get(0).get("net.sf.video4j.gwt.plugins.ads.liverail").get("publisher_id"), equalTo("11111"));
		assertThat(oBean.getPlugins().get(0).get("net.sf.video4j.gwt.plugins.ads.liverail").get("layout_sking_message"),
				equalTo("Advertisement. Video will resume in {COUNTDOWN} seconds."));

		assertThat(new ArrayList<String>(oBean.getPlugins().get(1).keySet()).get(0),
				equalTo("net.sf.video4j.gwt.plugins.ads.microsoft"));
		assertThat(oBean.getPlugins().get(1).get("net.sf.video4j.gwt.plugins.ads.microsoft").size(), equalTo(2));
		assertThat(oBean.getPlugins().get(1).get("net.sf.video4j.gwt.plugins.ads.microsoft").get("publisher_id"), equalTo("22222"));
		assertThat(oBean.getPlugins().get(1).get("net.sf.video4j.gwt.plugins.ads.microsoft").get("layout_sking_message"),
				equalTo("Advertisement. Video coming in {COUNTDOWN} seconds."));
	}

}
