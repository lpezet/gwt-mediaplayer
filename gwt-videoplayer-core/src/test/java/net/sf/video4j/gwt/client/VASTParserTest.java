/**
 * 
 */
package net.sf.video4j.gwt.client;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

import net.sf.video4j.gwt.client.vast.Ad;
import net.sf.video4j.gwt.client.vast.InLine;
import net.sf.video4j.gwt.client.vast.VAST;
import net.sf.video4j.gwt.client.vast.VASTParser;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import com.googlecode.gwt.test.GwtModule;
import com.googlecode.gwt.test.GwtTest;

/**
 * @author luc
 */
@GwtModule("net.sf.video4j.gwt.client.VASTParser")
public class VASTParserTest extends GwtTest {

	@Test
	public void overlays() throws Exception {
		VASTParser oParser = new  VASTParser();
		String oXML = getXMLAsString("/vast2_overlays.xml");
		
		VAST oVAST = oParser.parse(oXML);
		
		assertNotNull(oVAST);
	}
	
	@Test
	public void regularLinear() throws Exception {
		VASTParser oParser = new  VASTParser();
		String oXML = getXMLAsString("/vast2_regular_linear.xml");
		
		VAST oVAST = oParser.parse(oXML);
		assertNotNull(oVAST);
		assertNotNull(oVAST.getAds());
		assertEquals(1, oVAST.getAds().size());
		
		Ad oAd = oVAST.getAds().get(0);
		assertEquals("preroll-1", oAd.getId());
		assertTrue(oAd instanceof InLine);
		
		InLine oInLine = (InLine) oAd;
		assertEquals("2.0", oInLine.getAdSystem().getName());
		assertEquals("5748406", oInLine.getAdTitle());
		assertEquals(2, oInLine.getCreatives().size());
	}

	private String getXMLAsString(String pResourcePath) throws IOException {
		InputStream oIS = this.getClass().getResourceAsStream(pResourcePath);
		StringWriter writer = new StringWriter();
		IOUtils.copy(oIS, writer, "UTF8");
		String oXML = writer.toString();
		return oXML;
	}

}