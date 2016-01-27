package com.epn;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class Asintatico {
	private ArrayList<String> sintatico = new ArrayList<String>();
	private ArrayList<String> tablaSimbolos = new ArrayList<String>();

	public void sintaxis(Alexico lexico) {
		ArrayList<String> aux = lexico.getLexico();
		String linea = "", variable = "", tipo = "", part1,part2 ;

		for (int i = 0; i < aux.size(); i = i + 2) {
			linea = aux.get(i);
			StringTokenizer token = new StringTokenizer(linea);
			variable = token.nextToken();
			part1=token.nextToken();
			
			if (i != (aux.size() - 1)) {
				linea = aux.get(i + 1);
				StringTokenizer token1 = new StringTokenizer(linea);
				tipo = token1.nextToken();
			}
			tablaSimbolos.add(variable + " " + tipo);

		}

	}

}
