/**
 * 
 */
package net.sf.video4j.gwt.plugin.caption.webvtt;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.InputStream;

import net.sf.video4j.gwt.plugin.caption.webvtt.Parser;
import net.sf.video4j.gwt.plugin.caption.webvtt.WebVTT;

import org.junit.Test;

/**
 * @author luc
 *
 */
public class ParserTest {

	@Test
	public void cueTextAndTiming() throws Exception {
		InputStream oIS = this.getClass().getResourceAsStream("/cuetext_and_timing.vtt");
		assertNotNull(oIS);
		Parser oParser = new Parser();
		WebVTT oActual = oParser.parse(oIS);
		assertNotNull(oActual);
		assertEquals(13, oActual.getCues().size());
	}
}
