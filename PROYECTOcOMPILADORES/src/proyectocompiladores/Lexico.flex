package proyectocompiladores;
import java_cup.runtime.*;
import java.io.Reader;
      
%%
%class Lexico
%line
%column
%cup
   
%{
    private Symbol symbol(int type) {
        return new Symbol(type, yyline, yycolumn);
    }
    
    private Symbol symbol(int type, Object value) {
        return new Symbol(type, yyline, yycolumn, value);
    }
%}
caracter=[A-Za-z]
numero=[0-9] 
expre={caracter}|{numero}
IDENT={caracter}({expre})*

Salto = \r|\n|\r\n
Espacio     = {Salto} | [ \t\f]
Entero = 0 | [1-9][0-9]*
%% 
   

   
<YYINITIAL> {
"+" {// System.out.print(" + ");
                          return symbol(sym.SUMA); }
"-" {// System.out.print(" - ");
                          return symbol(sym.RESTA); }
"*" {// System.out.print(" * ");
                          return symbol(sym.MULT); }
"/" {// System.out.print(" / ");
                          return symbol(sym.DIV); }
":" {// System.out.print(" : ");
                          return symbol(sym.DOSP); }
";" {// System.out.print(" ; " + "\n");
                          return symbol(sym.TER);}
"," {// System.out.print(" , ");
                          return symbol(sym.COMA); }
"(" {// System.out.print(" ( ");
                          return symbol(sym.PARENTESISABIERTO); }
"{" {// System.out.print(" { " + "\n");
                          return symbol(sym.LLAVEABIERTA); }
")" {//System.out.print(" ) " + "\n");
                          return symbol(sym.PARENTESISCERRADO); }
"}" {// System.out.print(" } " + "\n");
                          return symbol(sym.LLAVECERRADA); }
"=" {// System.out.print(" = ");
                          return symbol(sym.EQUAL); }
"<" {// System.out.print(" < ");
                          return symbol(sym.MENOR); }
">" {// System.out.print(" > ");
                          return symbol(sym.MAYOR); }
"++" {// System.out.print(" ++ ");
                          return symbol(sym.INCREMENTO); }					  						  
"[" {// System.out.print(" [ ");
                          return symbol(sym.CORCHIZQ); }
"]" {// System.out.print(" ] ");
                          return symbol(sym.CORCHDER); }
"==" {// System.out.print(" == ");
                          return symbol(sym.IGUALIGUAL); }
"<=" {// System.out.print(" <= ");
                          return symbol(sym.MAYORIGUAL); }
">=" {// System.out.print(">= ");
                          return symbol(sym.MENORIGUAL); }
					  						  
"int" {// System.out.print(" int ");
                      return symbol(sym.INT);}
"while" {// System.out.print(" while") ; 
                      return symbol(sym.WHILE);}
"float" {// System.out.print(yytext()); 
                      return symbol(sym.FLOA, new Integer(yytext()));}
"bool" {// System.out.print("bool"); 
                      return symbol(sym.BOOL);}
"char" {// System.out.print("char"); 
                      return symbol(sym.CHA);}
"String" {// System.out.print("String"); 
                      return symbol(sym.string);}
"if" {// System.out.print("if"); 
                      return symbol(sym.IF);}
"then" {// System.out.print("then  "); 
                      return symbol(sym.THEN);}
"else" {// System.out.print("else"); 
                      return symbol(sym.PELSE);}
"do" {//System.out.print("do "); 
                      return symbol(sym.DO);}
"input" {//System.out.print("input"); 
                      return symbol(sym.INPUT);}
"output" {//System.out.print("output"); 
                      return symbol(sym.OUTPUT);}
"return" {//System.out.print("return"); 
                      return symbol(sym.RET);}
"true"|"false" {//System.out.print(yytext()); 
                      return symbol(sym.BOOLEANO);}					  
'([a-z|A-Z][a-z|A-Z|0-9]*)' {//System.out.print(yytext()); 
return symbol(sym.CHAR, new String (yytext()));}

"""([a-z|A-Z][a-z|A-Z|0-9]*)""" {//System.out.print(yytext()); 
                      return symbol(sym.STRING, new String(yytext()));
}
[a-z][a-z|A-Z|0-9]* {//System.out.print(yytext()); 
return symbol(sym.IDENTIFICADOR, new String(yytext()));}
[+-]?{numero}*[.]{numero}+ {System.out.print(yytext()); 
return symbol(sym.FLOTANTE, new Integer(yytext()));}
[+-]? {numero}+ { //System.out.print(yytext()); 
                      return symbol(sym.INTEGER, new Integer(yytext())); }   
{Espacio}       { /* ignora el espacio */ } 
	
}

[^]                    { throw new Error("Caracter ilegal <"+yytext()+">"); }
