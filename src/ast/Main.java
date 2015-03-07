package ast;

import gestorErrores.GestorErrores;
import introspector.model.IntrospectorModel;
import introspector.view.IntrospectorTree;

import java.io.FileReader;
import java.io.IOException;

import lexico.Lexico;
import sintactico.Parser;

/**
 * Prueba del analizador léxico.<br/>
 * Diseño de Lenguajes de Programación.<br/>
 * Escuela de Ingeniería Informática.<br/>
 * Universidad de Oviedo <br/>
 * 
 */

public class Main {

	public static void main(String args[]) throws IOException {
		if (args.length < 1) {
			System.err.println("Necesito el archivo de entrada.");
			return;
		}

		FileReader fr = null;
		try {
			fr = new FileReader(args[0]);
		} catch (IOException io) {
			System.err.println("El archivo " + args[0]
					+ " no se ha podido abrir.");
			return;
		}
		GestorErrores gestor = new GestorErrores();
		Lexico lexico = new Lexico(fr, gestor);

		Parser parser = new Parser(lexico, gestor);
		parser.run();

		if (!gestor.hayErrores()) {
			IntrospectorModel modelo = new IntrospectorModel("Program",
					parser.ast);
			new IntrospectorTree("Instrospector", modelo);
		}
	}

}