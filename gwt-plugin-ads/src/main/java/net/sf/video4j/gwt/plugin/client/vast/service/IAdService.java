package net.sf.video4j.gwt.plugin.client.vast.service;

import net.sf.video4j.gwt.plugin.shared.vast.AdRequestCallback;


/**
 * @author gumatias
 */
public interface IAdService {
    void fetchAds(String pURL, AdRequestCallback pCallback);
}
