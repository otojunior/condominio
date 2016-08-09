package org.otojunior.condominio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;
import java.util.Map;

import org.otojunior.condominio.service.PropriedadesService;
import org.otojunior.condominio.service.RelatorioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 * Application Main Class.
 *
 * @author [Author name]
 * @version $Id: $Id
 */
public class App {
	/**
	 * SLF4J Logger.
	 */
	private static final Logger LOG = LoggerFactory.getLogger(App.class);
	
	/**
	 * Application main method.
	 *
	 * @param args Command line arguments.
	 * @throws java.io.IOException if any.
	 * @throws java.io.FileNotFoundException if any.
	 * @throws net.sf.jasperreports.engine.JRException if any.
	 */
	public static void main(String[] args) throws FileNotFoundException, IOException, JRException {
		LOG.info("condominio Application.");
		
		RelatorioService relatorioService = new RelatorioService();
		PropriedadesService propriedadesService = new PropriedadesService("parametros.properties");
		
		Map<String, Object> parametros = propriedadesService.obterParametros();
		Collection<?> dados = propriedadesService.obterDados();
		
		// Impressão do relatório.
		JasperPrint jasperPrint = relatorioService.gerarRelatorio("jasper/recibos.jasper", parametros , dados);
		new JasperViewer(jasperPrint).setVisible(true);
	}
}
