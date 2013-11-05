package net.sf.video4j.gwt.client.util;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.List;

import net.sf.video4j.gwt.client.model.ApplicationConfig;
import net.sf.video4j.gwt.client.model.IPlayItemBean;

import org.junit.Test;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.google.web.bindery.autobean.shared.AutoBeanFactory;
import com.googlecode.gwt.test.GwtModule;
import com.googlecode.gwt.test.GwtTest;

/**
 * @author Gustavo Matias
 */
@GwtModule("net.sf.video4j.gwt.client.util.BeanFactory")
public class PlaylistBeanFactoryTest extends GwtTest {

	@Test
	public void givenPlaylistAsJSON_shouldMakePlaylistConfigBeanFromJSON() throws Exception {
		PlaylistBeanFactory oFactory = GWT.create(PlaylistBeanFactory.class);

		ApplicationConfig oApplicationConfig = new ApplicationConfig();
		JSONArray oPlaylistArray = new JSONArray();
		JSONObject oPlayItem1 = new JSONObject();
		oPlayItem1.put("url", new JSONString("http://videos.tripfilms.com/360p/4BAE2BA4EE543703AF8BC2DF6550BB73.mp4"));
		oPlaylistArray.set(0, oPlayItem1);
		JSONObject oPlayItem2 = new JSONObject();
		oPlayItem2.put("url", new JSONString("http://videos.tripfilms.com/480p/ECAA41D5E30E4BECC13FC1D3DA823750.mp4"));
		oPlaylistArray.set(1, oPlayItem2);
		oApplicationConfig.setPlaylist(oPlaylistArray);

		List<IPlayItemBean> oBean = new BeanFactory<IPlayItemBean, AutoBeanFactory>(IPlayItemBean.class, oFactory).makeFrom(oApplicationConfig.getPlaylist());

		String oPlaylistJSONString = 
				"["
					+ "{\"url\":\"http:\\/\\/videos.tripfilms.com\\/360p\\/4BAE2BA4EE543703AF8BC2DF6550BB73.mp4\"},"
					+ "{\"url\":\"http:\\/\\/videos.tripfilms.com\\/480p\\/ECAA41D5E30E4BECC13FC1D3DA823750.mp4\"}"
				+ "]";
		assertThat(oApplicationConfig.getPlaylist().toString(), equalTo(oPlaylistJSONString));
		assertThat(oBean.get(0).getURL(), equalTo("http://videos.tripfilms.com/360p/4BAE2BA4EE543703AF8BC2DF6550BB73.mp4"));
		assertThat(oBean.get(1).getURL(), equalTo("http://videos.tripfilms.com/480p/ECAA41D5E30E4BECC13FC1D3DA823750.mp4"));
	}

}