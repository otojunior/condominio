package br.gov.serpro.sped.condominio.service;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.gov.serpro.sped.condominio.util.Formatadores;

public class PropriedadesService {
	private static final Logger LOG = LoggerFactory.getLogger(PropriedadesService.class);
	
	private Properties propriedades;
	
	/**
	 * 
	 * @param arquivo
	 */
	public PropriedadesService(String arquivo) {
		InputStream inStream = getClass().
			getClassLoader().
			getResourceAsStream(arquivo);

		try {
			propriedades = new Properties();
			propriedades.load(inStream);
		} catch (IOException e) {
			LOG.error(e.getMessage(), e);
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public Map<String, Object> obterParametros() {
		// Obtenção de datas.
		LocalDate dtRef = LocalDate.now().minusMonths(1);
		LocalDate dtPag = LocalDate.now().withDayOfMonth(10);
		
		// Criação dos parâmertros.
		Map<String, Object> parametros = new HashMap<>();
		parametros.put("pMesAnoRef", dtRef.format(Formatadores.FMT_REF));
		parametros.put("pMesAnoPag", dtPag.format(Formatadores.FMT_PAG));
		parametros.put("pValor", propriedades.getProperty("valor"));
		
		return parametros;
	}
	
	/**
	 * 
	 * @return
	 */
	public Collection<?> obterDados() {
		propriedades.remove("valor");
		
		Set<Entry<Object, Object>> entrySet = propriedades.entrySet();
		List<Entry<Object, Object>> lst = new ArrayList<>(entrySet);
		
		Comparator<Entry<Object, Object>> c = (Entry<Object, Object> e1, Entry<Object, Object> e2)-> {
			String key1 = (String)e1.getKey();
			String key2 = (String)e2.getKey();
			return key1.compareTo(key2);
		};

		lst.sort(c);
		return lst;
	}
}
