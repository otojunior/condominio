/**
 * 
 */
package org.otojunior.condominio.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author 01456231650
 *
 */
public class FormatadoresTest {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testInterface() {
		assertEquals("pt", Formatadores.LOCALE_BRAZIL.getLanguage());
		assertEquals("BR", Formatadores.LOCALE_BRAZIL.getCountry());
		assertNotNull(Formatadores.FMT_PAG);
		assertNotNull(Formatadores.FMT_PARSE);
		assertNotNull(Formatadores.FMT_REF);
	}

}
