/**
 * 
 */
package net.sf.video4j.gwt.client;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

import net.sf.video4j.gwt.client.be.VAST;

import org.apache.commons.io.IOUtils;
import org.junit.Test;
import static org.junit.Assert.*;

import com.googlecode.gwt.test.GwtModule;
import com.googlecode.gwt.test.GwtTest;

/**
 * @author luc
 */
@GwtModule("net.sf.video4j.gwt.client.VASTParser")
public class VASTParserTest extends GwtTest {

	@Test
	public void shouldParse() throws Exception {
		VASTParser oParser = new  VASTParser();
		String oXML = getXMLAsString();
		
		VAST oVAST = oParser.parse(oXML);
		
		assertNotNull(oVAST);
	}

	private String getXMLAsString() throws IOException {
		InputStream oIS = this.getClass().getResourceAsStream("/vast2_overlays.xml");
		StringWriter writer = new StringWriter();
		IOUtils.copy(oIS, writer, "UTF8");
		String oXML = writer.toString();
		return oXML;
	}

}