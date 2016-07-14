/**
 * 
 */
package br.gov.serpro.sped.condominio.util;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

/**
 * @author 01456231650
 *
 */
public interface Formatadores {
	Locale LOCALE_BRAZIL = new Locale("pt", "BR");
	DateTimeFormatter FMT_PARSE = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	DateTimeFormatter FMT_REF = DateTimeFormatter.ofPattern("MMMM/yyyy", LOCALE_BRAZIL);
	DateTimeFormatter FMT_PAG = DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG);
}
