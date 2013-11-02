/**
 * 
 */
package net.sf.video4j.gwt.client.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author luc
 *
 */
public class Application implements IApplication {
	
	private Map<String, IPlugin> mPlugins = new HashMap<String, IPlugin>();
	private IApplicationConfig mConfig;
	
	public Application(IApplicationConfig pConfig) {
		mConfig = pConfig;
	}
	
	@Override
	public IApplicationConfig getConfig() {
		return mConfig;
	}

	@Override
	public Collection<IPlugin> getPlugins() {
		return mPlugins.values();
	}

	@Override
	public IPlugin getPlugin(String pPluginId) {
		return mPlugins.get(pPluginId);
	}
	
	@Override
	public void addPlugin(IPlugin pPlugin) {
		mPlugins.put(pPlugin.getPluginId(), pPlugin);
	}

	

}
