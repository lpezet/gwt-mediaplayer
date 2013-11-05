package net.sf.video4j.gwt.client.util;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import net.sf.video4j.gwt.client.model.ApplicationConfig;
import net.sf.video4j.gwt.client.model.IAdBean;

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
public class AdBeanFactoryTest extends GwtTest {

	@Test
	public void givenAdAsJSON_shouldMakeAdConfigBeanFromJSON() throws Exception {
		IAdBeanFactory oFactory = GWT.create(IAdBeanFactory.class);

		ApplicationConfig oApplicationConfig = new ApplicationConfig();
		JSONObject oAd = new JSONObject();
		oAd.put("url", new JSONString("http://ad3.liverail.com/?LR_PUBLISHER_ID=1331&LR_CAMPAIGN_ID=229&LR_SCHEMA=vast2"));
		oApplicationConfig.setAd(oAd);

		IAdBean oBean = new BeanFactory<IAdBean, AutoBeanFactory>(IAdBean.class, oFactory).makeFrom(oApplicationConfig.getAd());

		String oAdsJSONString = 
				"{"
					+ "\"url\":\"http:\\/\\/ad3.liverail.com\\/?LR_PUBLISHER_ID=1331&LR_CAMPAIGN_ID=229&LR_SCHEMA=vast2\""
				+ "}";
		assertThat(oApplicationConfig.getAd().toString(), equalTo(oAdsJSONString));
		assertThat(oBean.getURL(), equalTo("http://ad3.liverail.com/?LR_PUBLISHER_ID=1331&LR_CAMPAIGN_ID=229&LR_SCHEMA=vast2"));
	}

}
