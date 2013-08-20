/**
 * 
 */
package net.sf.video4j.gwt.client.module;

import net.sf.video4j.gwt.client.controller.ApplicationController;

import com.google.gwt.inject.client.AbstractGinModule;

/**
 * @author luc
 *
 */
public class ApplicationModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(ApplicationController.class);
	}

}
