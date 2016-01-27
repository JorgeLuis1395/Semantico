package com.epn;

import java.util.ArrayList;
import java.util.Collections;

public class TipoDato {
	private ArrayList<String> listaTipoDato = new ArrayList<String>();

	public TipoDato() {
		super();
		Collections.addAll(listaTipoDato, "int", "float", "bool", "char", "string");
	}
	
	public boolean esTipoDato(String texto) {
		boolean x=false;
		if(listaTipoDato.contains(texto)){
			x=true;
		}
	return x;	
	}
		
	
}
