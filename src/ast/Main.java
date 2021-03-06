package ast;

import generacionCodigo.GeneracionCodigo;
import gestionMemoria.GestionMemoria;
import gestorErrores.GestorErrores;

import java.io.FileReader;
import java.io.IOException;

import lexico.Lexico;
import semantico.IdentificacionVisitor;
import semantico.InferenciaVisitor;
import sintactico.Parser;

/**
 * Prueba del analizador l�xico.<br/>
 * Dise�o de Lenguajes de Programaci�n.<br/>
 * Escuela de Ingenier�a Inform�tica.<br/>
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

		// XMLVisitor vXML= new XMLVisitor();
		// parser.ast.acepta(vXML);

		if (!gestor.hayErrores()) {
			IdentificacionVisitor vIdentificacion = new IdentificacionVisitor(
					gestor);
			parser.ast.acepta(vIdentificacion);
		}

		if (!gestor.hayErrores()) {
			InferenciaVisitor vInferencia = new InferenciaVisitor(gestor);
			parser.ast.acepta(vInferencia);

			GestionMemoria vGestionMemoria = new GestionMemoria();
			parser.ast.acepta(vGestionMemoria);
		}

		if (!gestor.hayErrores()) {
			GeneracionCodigo gCodigo = new GeneracionCodigo();
			parser.ast.acepta(gCodigo);
		}

	/*	if (!gestor.hayErrores()) {
			IntrospectorModel modelo = new IntrospectorModel("Program",
					parser.ast);
			new IntrospectorTree("Instrospector", modelo);
		}*/

	}

}