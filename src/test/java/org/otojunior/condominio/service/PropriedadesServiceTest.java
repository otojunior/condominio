/**
 * 
 */
package org.otojunior.condominio.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Collection;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author 01456231650
 *
 */
public class PropriedadesServiceTest {
	private PropriedadesService service;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		service = new PropriedadesService("parametros.properties");
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		service = null;
	}

	/**
	 * Test method for {@link org.otojunior.condominio.service.PropriedadesService#PropriedadesService(java.lang.String)}.
	 */
	@Test
	public final void testPropriedadesService() {
		assertNotNull(service);
	}
	
	/**
	 * Test method for {@link org.otojunior.condominio.service.PropriedadesService#obterDados()}.
	 */
	@Test
	public final void testObterDados() {
		Collection<?> dados = service.obterDados();
		assertEquals(14, dados.size());
	}
}
