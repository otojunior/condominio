/**
 * 
 */
package org.otojunior.condominio.util;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

/**
 * <p>Formatadores interface.</p>
 *
 * @author 01456231650
 * @version $Id: $Id
 */
public interface Formatadores {
	/** Constant <code>LOCALE_BRAZIL</code> */
	Locale LOCALE_BRAZIL = new Locale("pt", "BR");
	/** Constant <code>FMT_PARSE</code> */
	DateTimeFormatter FMT_PARSE = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	/** Constant <code>FMT_REF</code> */
	DateTimeFormatter FMT_REF = DateTimeFormatter.ofPattern("MMMM/yyyy", LOCALE_BRAZIL);
	/** Constant <code>FMT_PAG</code> */
	DateTimeFormatter FMT_PAG = DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG);
}
