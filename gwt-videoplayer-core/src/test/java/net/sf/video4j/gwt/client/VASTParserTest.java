/**
 * 
 */
package net.sf.video4j.gwt.client;

import java.io.InputStream;
import java.io.StringWriter;

import org.apache.commons.io.IOUtils;

import com.google.gwt.junit.client.GWTTestCase;

/**
 * @author luc
 *
 */
public class VASTParserTest extends GWTTestCase {

	public void testParse() throws Exception {
		VASTParser oParser = new  VASTParser();
		
		InputStream oIS = this.getClass().getResourceAsStream("/vast2_overlays.xml");
		StringWriter writer = new StringWriter();
		IOUtils.copy(oIS, writer, "UTF8");
		String oXML = writer.toString();
		System.out.println("XML= \n" + oXML);
		oParser.parse(oXML);
		
	}

	@Override
	public String getModuleName() {
		return "net.sf.video4j.gwt.client.VASTParser";
	}
}
