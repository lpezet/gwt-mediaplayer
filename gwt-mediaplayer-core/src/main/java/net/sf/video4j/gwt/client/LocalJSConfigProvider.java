package net.sf.video4j.gwt.client;

import net.sf.video4j.gwt.client.config.model.jso.JSOApplicationConfig;
import net.sf.video4j.gwt.client.model.ApplicationConfig;
import net.sf.video4j.gwt.client.model.IApplicationConfig;

/**
 * @author Gustavo Matias
 * 
 */
public class LocalJSConfigProvider implements IConfigProvider {

  @Override
	public IApplicationConfig getConfig() {
		JSOApplicationConfig oJSOApplication = JSOApplicationConfig.build();
		ApplicationConfig oConfig = new ApplicationConfig();
		oConfig.setPlaylist(oJSOApplication.getPlaylist());
		oConfig.setCommon(oJSOApplication.getCommon());
		oConfig.setPlugins(oJSOApplication.getPlugins());
		return oConfig;
  }

}
