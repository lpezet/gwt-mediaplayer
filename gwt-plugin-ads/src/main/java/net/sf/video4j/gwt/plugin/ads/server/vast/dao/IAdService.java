package net.sf.video4j.gwt.plugin.ads.server.vast.dao;

import net.sf.video4j.gwt.plugin.ads.shared.vast.VAST;

/**
 * @author gumatias
 * 
 */
public interface IAdService {

	VAST fetchAds(String pURL);

}
