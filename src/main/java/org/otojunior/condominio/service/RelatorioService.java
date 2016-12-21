/**
 * 
 */
package org.otojunior.condominio.service;

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
 * <p>RelatorioService class.</p>
 *
 * @author 01456231650
 * @version $Id: $Id
 */
public class RelatorioService {
	private static final Logger LOG = LoggerFactory.getLogger(RelatorioService.class);
	
	/**
	 * <p>compilarRelatorio.</p>
	 *
	 * @param relatorio a {@link java.lang.String} object.
	 * @param parametros a {@link java.util.Map} object.
	 * @param dados a {@link java.util.Collection} object.
	 * @return a {@link net.sf.jasperreports.engine.JasperPrint} object.
	 */
	public JasperPrint compilarRelatorio(String relatorio, Map<String,Object> parametros, Collection<?> dados) {
		JasperPrint retorno = null;
		try {
			InputStream input = getClass().
				getClassLoader().
				getResourceAsStream(relatorio);
			
			if (input != null) {
				JRDataSource datasource = new JRBeanCollectionDataSource(dados);
				JasperReport jasperReport = JasperCompileManager.compileReport(input);
				JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, datasource);
				retorno = jasperPrint;
			} else {
				LOG.error("Não encontrado arquivo \'.jrxml\' do relatório. Verifique o caminho informado.");
			}
		} catch (JRException e) {
			LOG.error(e.getMessage(), e);
		}
		return retorno;
	}
	
	/**
	 * <p>compilarRelatorio.</p>
	 *
	 * @param relatorio a {@link java.lang.String} object.
	 * @param parametros a {@link java.util.Map} object.
	 * @param dados a {@link java.util.Collection} object.
	 * @return a {@link net.sf.jasperreports.engine.JasperPrint} object.
	 */
	public JasperPrint gerarRelatorio(String relatorio, Map<String,Object> parametros, Collection<?> dados) {
		JasperPrint retorno = null;
		try {
			InputStream input = getClass().
				getClassLoader().
				getResourceAsStream(relatorio);
			
			if (input != null) {
				JRDataSource datasource = new JRBeanCollectionDataSource(dados);
				JasperPrint jasperPrint = JasperFillManager.fillReport(input, parametros, datasource);
				retorno = jasperPrint;
			} else {
				LOG.error("Não encontrado arquivo \'.jasper\' do relatório. Verifique o caminho informado.");
			}
		} catch (JRException e) {
			LOG.error(e.getMessage(), e);
		}
		return retorno;
	}
}
