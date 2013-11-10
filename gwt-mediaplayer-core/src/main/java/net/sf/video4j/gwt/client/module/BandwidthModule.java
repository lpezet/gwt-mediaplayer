/**
 * 
 */
package net.sf.video4j.gwt.client.module;

import net.sf.video4j.gwt.client.controller.BandwidthController;

import com.google.gwt.inject.client.AbstractGinModule;

/**
 * @author luc
 *
 */
public class BandwidthModule  extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(BandwidthController.class);
	}
}
