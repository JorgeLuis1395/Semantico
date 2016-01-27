package com.epn;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.swing.JOptionPane;

public class Alexico {
	File fe = new File("ejemplo.c");
	BufferedReader entrada;
	private ArrayList<String> lexico = new ArrayList<String>();

	public Alexico() {
		super();
		try {
			entrada = new BufferedReader(new FileReader(fe));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "No se ha podido acceder al archivo");
		}
	}
	

	public ArrayList<String> getLexico() {
		return lexico;
	}


	public void setLexico(ArrayList<String> lexico) {
		this.lexico = lexico;
	}


	public void analizarCodigo() throws IOException {
		String linea, lexema = "", ingreso, token;
		TipoDato tipo = new TipoDato();
		Identificador identificador = new Identificador();
		int contLinea = 0;
		while (entrada.ready()) {
			contLinea++;
			linea = entrada.readLine();
			linea.trim();
			StringTokenizer tokens = new StringTokenizer(linea, " ;+", true);
			while (tokens.hasMoreTokens()) {
				lexema = tokens.nextToken();
				if (lexema.equals(" ") == false) {
					for (int op = 0; op < 6; op++) {
						switch (op) {
						case 0:

							if (tipo.esTipoDato(lexema)) {
								ingreso = lexema;
								token = "tipoDato";
								lexico.add(ingreso + " " + token);
								op = 6;

							}
							break;
						case 1:
							if (identificador.esIdentificador(lexema)) {
								ingreso = lexema;
								token = "identificador";
								lexico.add(ingreso + " " + token);
								op = 6;
							}
							break;
						case 2:

							if (lexema.equals(";")) {
								ingreso = lexema;
								token = ";";
								lexico.add(ingreso + " " + token);
								op = 6;
							}
							break;
						case 3:

							if (lexema.equals("=")) {
								ingreso = lexema;
								token = "=";
								lexico.add(ingreso + " " + token);
								op = 6;
							}
							break;
						case 4:
							if (lexema.equals("+")) {
								ingreso = lexema;
								token = "+";
								lexico.add(ingreso + " " + token);
								op = 6;
							}
							break;
						case 5:
							System.out.print("Error lexico\n");
							break;
						}
					}
				}

			}

		}
		System.out.println("Se ha terminado la lectura");
		
	}
}
