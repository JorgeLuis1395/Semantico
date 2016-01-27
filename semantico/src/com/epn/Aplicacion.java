package com.epn;

import java.io.IOException;

public class Aplicacion {

	public static void main(String[] args) throws IOException {
		Alexico lexico= new Alexico();
		lexico.analizarCodigo();
		Asintatico sintactico = new Asintatico();
		sintactico.sintaxis(lexico);
		}

}
