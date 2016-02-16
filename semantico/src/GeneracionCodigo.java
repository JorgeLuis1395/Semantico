import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
public class GeneracionCodigo{
	private ArrayList<String> alma=new ArrayList<String>();
	private ArrayList<String> etiqueta=new ArrayList<String>();
	private int aux=0,i=0; 
	String texto, cadena;
	FileReader nombrearchivo;
	BufferedReader lectura;
	public  GeneracionCodigo(){

			try{
				nombrearchivo=new FileReader("archivo.txt");
				lectura=new BufferedReader(nombrearchivo);
				while(((cadena=lectura.readLine())!=null)){
					StringTokenizer st = new StringTokenizer(cadena);
					try{
						while (st.hasMoreTokens()){
							texto=st.nextToken();
							VerificacionPrincipal(texto);
						}}catch(Exception e){}
				}
			}
			catch(Exception e){
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			for(int i=0;i<etiqueta.size();i++){
				StringTokenizer st = new StringTokenizer(etiqueta.get(i));
				try{
					while (st.hasMoreTokens()){
						texto=st.nextToken();
						VerificacionPrincipal(texto);
					}}catch(Exception e){}
			}
	}
	
	void VerificacionPrincipal(String texto){
		for(int i=0;i<=5;i++){
			switch(i){
			case 0:
				//cadena="int: var = ";
				Inicializacion(cadena);
				cadena="int: a = e";
				Inicializacion(cadena);
				break;
			case 1:
				//cadena="a = 4";
				Declaraciondevariables(cadena);
				//cadena="c = e";
				Declaraciondevariables(cadena);
				break;
			case 2:
				//cadena="d = e + b * 3 - 40";
				Expresionesaritmaticas(cadena);
				break;
			case 4:
				//cadena="if ( a == b ) { a= b + c ; }";
				ComandosdeControldeFlujo(cadena);
				break;
			}
		}
	}
	
	void Inicializacion(String texto){
		StringTokenizer st = new StringTokenizer(texto);
		String palabra;
		palabra=st.nextToken();
		palabra="";
		try{
			while (st.hasMoreTokens()){	
				palabra +=(st.nextToken()+" ");
			}}catch(Exception e){}
		Declaraciondevariables(palabra);
	}
	
	void Declaraciondevariables(String texto){
		StringTokenizer st = new StringTokenizer(texto);
		String palabra[]=new String[3];
		int i=0;
		try{
		while (st.hasMoreTokens()){	
			palabra[i]=st.nextToken();
			i++;
		}}catch(Exception e){}
		if(alma.indexOf(palabra[0])==-1)
			alma.add(palabra[0]);
		letraNum(palabra[2],"a0");
		System.out.println("store a0 => r" + alma.indexOf(palabra[0]));	
	}
	void Expresionesaritmaticas(String texto){
		StringTokenizer st = new StringTokenizer(texto);
		String palabra[]=new String[5];
		try{
			for(int i=0;i<5;i++)
				palabra[i]=st.nextToken();			
		}catch(Exception e){}
		letraNum(palabra[2],"t1");
		letraNum(palabra[4],"t2");
		Operaciones(palabra[3],"t2");
		try{
			while (st.hasMoreTokens()){	
				palabra[3]=st.nextToken();
				palabra[4]=st.nextToken();
				letraNum(palabra[4],"t1");
				Operaciones(palabra[3],"t3");
			}
		}catch(Exception e){}
		System.out.println("Load t3 => a0");
		System.out.println("store a0 => r" + alma.indexOf(palabra[0]));
	}
	
	void ComandosdeControldeFlujo(String texto){
		StringTokenizer st = new StringTokenizer(texto);
		String palabra=st.nextToken();
		switch(palabra){
		case "if":
			try{
				palabra="";
				while (st.hasMoreTokens()){	
					palabra+=st.nextToken()+" ";
				}
				Funif(palabra);
			}catch(Exception e){}
			break;
		case "for":
			try{
				palabra="";
				while (st.hasMoreTokens()){	
					palabra+=st.nextToken()+" ";
				}
				//Funfor();
			}catch(Exception e){}
			break;
		case "do":
			try{
				palabra="";
				while (st.hasMoreTokens()){	
					palabra+=st.nextToken()+" ";
				}
				//Fundo();
			}catch(Exception e){}
			break;
		case "while":
			try{
				palabra="";
				while (st.hasMoreTokens()){	
					palabra+=st.nextToken()+" ";
				}
				//Funwhile();
			}catch(Exception e){}
			break;
		}
	}
	
	void letraNum(String tex,String da){
		if(tex.matches("[0-9]*"))
			System.out.println("LoadI "+tex+" => "+da);
		else
			System.out.println("Load r"+alma.indexOf(tex)+" => "+da);
	}
	void Operaciones(String text, String da){
		if(text.equals("+"))
			System.out.println("ADD t1, "+da+" => t3");
		if(text.equals("-"))
			System.out.println("SUB t1, "+da+" => t3");
		if(text.equals("*"))
			System.out.println("MULT t1, "+da+" => t3");
		if(text.equals("/"))
			System.out.println("DIV t1, "+da+" => t3");
	}
	
	void Funif(String texto){
		StringTokenizer st = new StringTokenizer(texto);
		String palabra, ver;
		palabra=st.nextToken();
		palabra=st.nextToken(")");
		ExpresLogica(palabra);
		try{
			palabra="";
			while (st.hasMoreTokens()){	
				ver=st.nextToken();
				if(!ver.equals("{") && !ver.equals("}"))
					palabra+=ver+" ";
			}
			Funif(palabra);
		}catch(Exception e){}
		if(i<2){
		etiqueta.add(palabra);
		i++;
		}
	}
	
	void ExpresLogica(String texto){
		StringTokenizer st = new StringTokenizer(texto);
		String palabra[]=new String[3];
		for(int i=0;i<3;i++){
			palabra[i]=st.nextToken();
		}
		letraNum(palabra[0],"t1");
		letraNum(palabra[2],"t2");
		VericaLog(palabra[1]);
	}
	
	void VericaLog(String texto){
		switch(texto){
		case "==": System.out.println("seq $t3, $ti, $t2");
			break;
		case "<=": System.out.println("sle $t3, $ti, $t2");
			break;
		case ">=": System.out.println("sge $t3, $ti, $t2");
			break;
		case "<": System.out.println("slt $t3, $ti, $t2");
			break;
		case ">": System.out.println("sgt $t3, $ti, $t2");
			break;
		case "!=": System.out.println("sne $t3, $ti, $t2");
			break;
		}
		System.out.println("beq $t3, 1, E"+aux);
		aux++;
		System.out.println("beq $t3, 0, E"+aux);
		aux++;
	}
	
}