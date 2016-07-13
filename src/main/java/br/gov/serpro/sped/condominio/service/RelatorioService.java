/**
 * 
 */
package br.gov.serpro.sped.condominio.service;

import java.io.InputStream;
import java.util.Collection;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * @author 01456231650
 *
 */
public class RelatorioService {
	private static final Logger LOG = LoggerFactory.getLogger(RelatorioService.class);
	
	public JasperPrint compilarRelatorio(String relatorio, Map<String,Object> parametros, Collection<?> dados) {
		JasperPrint retorno = null;
		try {
			InputStream input = getClass().
				getClassLoader().
				getResourceAsStream(relatorio);
			
			JRDataSource datasource = new JRBeanCollectionDataSource(dados);
			JasperReport jasperReport = JasperCompileManager.compileReport(input);
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, datasource);
			retorno = jasperPrint;
		} catch (JRException e) {
			LOG.error(e.getMessage(), e);
		}
		return retorno;
	}
}
