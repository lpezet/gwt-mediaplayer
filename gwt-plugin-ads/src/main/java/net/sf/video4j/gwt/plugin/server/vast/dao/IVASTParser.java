/**
 * 
 */
package net.sf.video4j.gwt.plugin.server.vast.dao;

import net.sf.video4j.gwt.plugin.shared.vast.VAST;

/**
 * @author luc
 *
 */
public interface IVASTParser {
	VAST parse(String pXML);
}
