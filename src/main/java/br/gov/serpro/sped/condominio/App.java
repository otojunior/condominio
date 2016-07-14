package br.gov.serpro.sped.condominio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.gov.serpro.sped.condominio.service.PropriedadesService;
import br.gov.serpro.sped.condominio.service.RelatorioService;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 * Application Main Class.
 * @author [Author name]
 */
public class App {
	/**
	 * SLF4J Logger.
	 */
	private static final Logger LOG = LoggerFactory.getLogger(App.class);
	
	/**
	 * Application main method.
	 * @param args Command line arguments.
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * @throws JRException 
	 */
	public static void main(String[] args) throws FileNotFoundException, IOException, JRException {
		LOG.info("condominio Application.");
		
		RelatorioService relatorioService = new RelatorioService();
		PropriedadesService propriedadesService = new PropriedadesService("parametros.properties");
		
		Map<String, Object> parametros = propriedadesService.obterParametros();
		Collection<?> dados = propriedadesService.obterDados();
		
		// Impressão do relatório.
		JasperPrint jasperPrint = relatorioService.compilarRelatorio("recibos.jrxml", parametros , dados);
		new JasperViewer(jasperPrint).setVisible(true);
	}
}
