package br.gov.serpro.sped.condominio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.gov.serpro.sped.condominio.service.RelatorioService;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
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
		
		RelatorioService service = new RelatorioService();
		
		
		// Leitura do arquivo de properties.
		Properties prop = new Properties();
		prop.load(resource("parametros.properties"));
		
		// Obtenção do Mês/Ano de refereência.
		String text = "01/" + prop.getProperty("mes_ano_ref");

		// Obtenção de datas
		LocalDate dtRef = LocalDate.parse(text, Formatadores.FMT_PARSE);
		LocalDate dtPag = dtRef.plusMonths(1).plusDays(9);
		
		// Parâmetros de relatório.
		Map<String, Object> parametros = new HashMap<>();
		parametros.put("pMesAnoRef", dtRef.format(Formatadores.FMT_REF));
		parametros.put("pMesAnoPag", dtPag.format(Formatadores.FMT_PAG));
		parametros.put("pValor", prop.getProperty("valor"));
		
		/*
		 * Retirada dos parâmetros de relatório. 
		 * (para aproveitar o mesmo arquivo de properties para os dados. 
		 */
		prop.remove("mes_ano_ref");
		prop.remove("valor");
		
		// Criação do Datasource.
		Set<Entry<Object, Object>> entrySet = prop.entrySet();
		JRBeanCollectionDataSource datasource = new JRBeanCollectionDataSource(entrySet);
		
		// Impressão do relatório.
		JasperPrint jasperPrint = service.compilarRelatorio("recibos.jrxml", parametros, null);
		new JasperViewer(jasperPrint).setVisible(true);
	}
	
	/**
	 * 
	 * @param resource
	 * @return
	 */
	private static InputStream resource(String resource) {
		InputStream stream = App.class.
			getClassLoader().
			getResourceAsStream(resource);
		return stream;
	}
}
