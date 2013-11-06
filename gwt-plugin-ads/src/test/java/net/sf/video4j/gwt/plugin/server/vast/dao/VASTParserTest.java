/**
 * 
 */
package net.sf.video4j.gwt.plugin.server.vast.dao;

import net.sf.video4j.gwt.plugin.AbstractVASTParserTest;
import net.sf.video4j.gwt.plugin.shared.vast.VAST;

/**
 * @author luc
 *
 */
public class VASTParserTest extends AbstractVASTParserTest {
	
	private VASTParser mParser = new VASTParser();

	@Override
	protected VAST parse(String pXML) {
		return mParser.parse(pXML);
	}

}
