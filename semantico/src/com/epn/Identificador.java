package com.epn;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Identificador {

	public boolean esIdentificador(String texto) {
		boolean res = false;
		String patron = "[a-z]+";
		TipoDato tipo= new TipoDato();
		Pattern p = Pattern.compile(patron);
		
		Matcher matcher = p.matcher(texto);
		if (matcher.matches() && (tipo.esTipoDato(texto)==false)) {
			res = true;
		}
		return res;
	}

}
