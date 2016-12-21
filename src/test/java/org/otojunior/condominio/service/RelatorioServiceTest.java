/**
 * 
 */
package org.otojunior.condominio.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import net.sf.jasperreports.engine.JasperPrint;

/**
 * @author 01456231650
 *
 */
public class RelatorioServiceTest {
	private Map<String, Object> parametros;
	private Collection<Entry<Object, Object>> dados;
	private RelatorioService service;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		parametros = new HashMap<>();
		parametros.put("pMesAnoPag", "10 de Dezembro de 2016");
		parametros.put("pMesAnoRef", "Novembro/2016");
		parametros.put("pValor", "R$ 110,00 (Cento e Dez Reais)");
		
		Map<Object, Object> mapadados = new LinkedHashMap<>();
		mapadados.put("101", "Morador 1");
		mapadados.put("102", "Morador 2");
		mapadados.put("103", "Morador 3");
		dados = new ArrayList<>(mapadados.entrySet());
		
		service = new RelatorioService();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		parametros = null;
		dados = null;
		service = null;
	}

	/**
	 * Test method for {@link org.otojunior.condominio.service.RelatorioService#compilarRelatorio(java.lang.String, java.util.Map, java.util.Collection)}.
	 */
	@Test
	public final void testCompilarRelatorioOk() {
		JasperPrint rel = service.compilarRelatorio("recibos.jrxml", parametros , dados);
		assertNotNull(rel);
	}
	
	/**
	 * Test method for {@link org.otojunior.condominio.service.RelatorioService#compilarRelatorio(java.lang.String, java.util.Map, java.util.Collection)}.
	 */
	@Test
	public final void testCompilarRelatorioCaminhoNaoExistente() {
		JasperPrint rel = service.compilarRelatorio("dummy", parametros , dados);
		assertNull(rel);
	}
	
	/**
	 * Test method for {@link org.otojunior.condominio.service.RelatorioService#compilarRelatorio(java.lang.String, java.util.Map, java.util.Collection)}.
	 */
	@Test
	public final void testCompilarRelatorioCaminhoArquivoInvalido() {
		JasperPrint rel = service.compilarRelatorio("META-INF/MANIFEST.MF", parametros , dados);
		assertNull(rel);
	}

	/**
	 * Test method for {@link org.otojunior.condominio.service.RelatorioService#gerarRelatorio(java.lang.String, java.util.Map, java.util.Collection)}.
	 */
	@Test
	public final void testGerarRelatorioOk() {
		JasperPrint rel = service.gerarRelatorio("jasper/recibos.jasper", parametros , dados);
		assertNotNull(rel);
	}
	
	/**
	 * Test method for {@link org.otojunior.condominio.service.RelatorioService#gerarRelatorio(java.lang.String, java.util.Map, java.util.Collection)}.
	 */
	@Test
	public final void testGerarRelatorioCaminhoNaoExistente() {
		JasperPrint rel = service.gerarRelatorio("dummy", parametros , dados);
		assertNull(rel);
	}
	
	/**
	 * Test method for {@link org.otojunior.condominio.service.RelatorioService#gerarRelatorio(java.lang.String, java.util.Map, java.util.Collection)}.
	 */
	@Test
	public final void testGerarRelatorioCaminhoArquivoInvalido() {
		JasperPrint rel = service.gerarRelatorio("META-INF/MANIFEST.MF", Collections.emptyMap() , dados);
		assertNull(rel);
	}
}
