/**
 * 
 */
package net.sf.video4j.gwt.client.module;

import net.sf.video4j.gwt.client.controller.ApplicationController;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

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
