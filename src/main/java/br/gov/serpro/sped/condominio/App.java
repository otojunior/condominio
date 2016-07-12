package br.gov.serpro.sped.condominio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
		
		Properties parametros = new Properties();
		parametros.load(resource("parametros.properties"));
		
		Map<String, Object> mapa = new HashMap<>();
		mapa.put("pMesAnoRef", parametros.getProperty("mes_ano_ref"));
		mapa.put("pMesAnoPag", parametros.getProperty("mes_ano_pag"));
		mapa.put("pValor", parametros.getProperty("valor"));
		
		parametros.remove("mes_ano_ref");
		parametros.remove("mes_ano_pag");
		parametros.remove("valor");
		
		Set<Entry<Object, Object>> entrySet = parametros.entrySet();
		JRBeanCollectionDataSource datasource = new JRBeanCollectionDataSource(entrySet);
		
		JasperReport jasperReport = JasperCompileManager.compileReport(resource("recibos.jrxml"));
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, mapa, datasource);
		new JasperViewer(jasperPrint).setVisible(true);
	}
	
	/**
	 * 
	 * @param resource
	 * @return
	 */
	public static InputStream resource(String resource) {
		InputStream stream = App.class.
			getClassLoader().
			getResourceAsStream(resource);
		return stream;
	}
}
