package com.joaquinjimenezgarcia;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {

	// El bote con las bolas de la partida.
	private static int[] pot;

	// Array que contiene todos los cartones generados.
	private static int[][][] papers;

	// La bola actual dentro del array pot.
	private static int currentBall = 16;

	// Está en false mientras no haya salido línea en ningún cartón.
	private static boolean hasLineComeOut = false;

	private static Scanner input = new Scanner(System.in);

	public static void main(String[] args) {
		int numCartones;

		System.out.print("Introduzca el número de cartones a generar: ");
		numCartones = input.nextInt();
		papers = createPapers(numCartones);

		System.out.println("Pulse ENTER para empezar.");
		input.nextLine();

		pot = createPot();
		nextTurn();
	}

	//////////// PARTE DE MARCO ////////////

	/**
	 *	Función que coge las bolas del bote generado y las va sacando de 1 en 1 y mostrando por pantalla.
	 *
	 */
	public static void nextTurn(){

		for (int i=0; i < pot.length; i++){

			// A partir de la quinta bola sacada del bote, comprobamos con la función checkLine si se produce línea con
			// cada nueva bola sacada
			if (i >= 4){
				checkPapers(papers);
			}
			// Imprimimos las bolas extraídas del bote
			System.out.println();
			System.out.println("Bola " + pot[i]);
			System.out.println();
		}
	}


	////////////////////////////////////////

	//////////// PARTE DE PABLO ////////////

	/**
	 * Desordena el array dado de forma aleatoria
	 *
	 * @param array Un array de enteros
	 * @return El array con los elementos desordenados
	 */
	private static int[] shuffleArray(int[] array) {
		/*
		 *	Implementación de la versión de Richard Durstenfeld de la mezcla de Fisher–Yates
		 *	https://en.wikipedia.org/wiki/Fisher%E2%80%93Yates_shuffle
		 */
		for (int i = 0; i < array.length - 1; i++) {
			int j = randomInteger(i, array.length - 2);

			// Intercambiar array[i] con array[j]
			// Para ello, guardamos array[j] en una variable temporal
			int tmp = array[j];
			array[j] = array[i];
			array[i] = tmp;
		}

		return array;
	}

	/**
	 * @param min El número mínimo, incluido.
	 * @param max El número máximo, incluido.
	 * @return Un número aleatorio entre min y max
	 */
	private static int randomInteger(int min, int max) {
		return (int) (Math.random() * (((max - min) + 1) + min));
	}

	////////////////////////////////////////

	//////////// PARTE DE ALE //////////////

	/**
	 * Funcion Que crea un bote con numeros del 1 al 90 desordenados
	 * @return Devuelve el bote desordenado
	 */
	static int [] createPot (){
		int array [] = new int [90];

		for (int i = 0; i < array.length; i++){
			array [i] = i + 1;

		}
		//Llamada a la función de desordenación del array
		return shuffleArray(array);
	}

	////////////////////////////////////////

	//////////// PARTE DE FRAN /////////////

	/**
	 * Funcion que comprueba si un carton tiene linea o bingo,
	 * copiando el carton en un array paperCopy y poniendo los
	 * numeros que ya salieron en el pot a 0 en dicho array.
	 * Si la suma de todos los numeros de una fila es 0, hay linea en ese carton.
	 * Si la suma de todos los numeros del carton es 0, hay bingo en ese carton.
	 * @param paper int Array de dos dimensiones con los valores a comprobar.
	 * @return String Devuelve bingo, line o nothing dependiendo de las comprobaciones.
	 */
	static String checkPaper(int paper[][]){
		int potNumber, bingo = 0;
		int paperCopy[][] = new int[3][5];
		boolean checkLine=false;

		//Copia el array paper en paperCopy
		for (int i = 0; i < paper.length; i++) {
			for (int j = 0; j < paper[i].length; j++) {
				paperCopy[i][j] = paper[i][j];
			}
		}

		//Bucle que itera por cada numero del pot que haya salido.
		for (int i = 0; i < currentBall; i++) {
			potNumber = pot[i];

			//Pone a 0 los numeros de paperCopy que ya hayan salido en el pot.
			for (int j = 0; j < paperCopy.length; j++) {
				for (int k = 0; k < paperCopy[j].length; k++) {
					if (potNumber == paperCopy[j][k]){
						paperCopy[j][k] = 0;
					}
				}
			}
		}

		//Comprueba si hay linea en cada fila del carton.
		for (int i = 0, line = 0; i < paperCopy.length && !checkLine; i++) {
			for (int j = 0; j < paperCopy[i].length; j++) {
				line += paperCopy[i][j];

			}

			if (line == 0){
				checkLine = true;

			}
		}

		//Comprueba si hay bingo en el carton.
		for (int i = 0; i < paperCopy.length; i++) {
			for (int j = 0; j < paperCopy[i].length; j++) {
				bingo += paperCopy[i][j];
			}
		}

		if (bingo == 0){
			return "bingo";
		}else if (checkLine){
			return "line";
		}else{
			return "nothing";
		}
	}

	/**
	 * Funcion que comprueba si hay linea o bingo en cada uno de los cartones con cada bola que va saliendo en el pot.
	 * @param papers int Array papers en el que la primera dimension es el numero de carton y las otras dos los calores que se comprueban.
	 */
	static void checkPapers(int papers[][][]){
		//Bucle que comprueba si hay linea o bingo en cada uno de los cartones.

		for (int i = 0; i < papers.length; i++) {
			switch (checkPaper(papers[i])){
				case "bingo":
					System.out.println("Hay bingo en el carton " + i + "!!!!");
					return;
				case "line":
					if (!hasLineComeOut) {
						System.out.println("Hay linea en el carton " + i + "!!!!");
						hasLineComeOut = true;
					}
					// No ponemos break para que se ejecute nextTurn()
				case "nothing":
					nextTurn();
					break;
			}

		}
	}




	////////////////////////////////////////

	//////////// PARTE DE JORGE ////////////

	static void createPapers() {
		Scanner input = new Scanner(System.in);

		int num;

		System.out.println("Numero de cartones para jugar:");
		num=input.nextInt();

		for (int i = 0; i < num ; i++) {
			createPaper();
		}
	}

	////////////////////////////////////////

	//// PARTE DEL PUTO AMO DE JOAKI ////

	/**
	 * First creates a single array with no-repeated numbers and orders it.
	 * Then, places them in a two dimensions array for an easier checking of lines
	 * @return paper array
	 */
	static int[][] createPaper(){
		int quantity = 5; // random numbers
		int lines = 3; // lines
		int [] array = new int [quantity*lines]; // single array for order
		int [][] paper = new int[lines][quantity]; // new array with lines

		// Fill the first array
		for (int i = 0; i < quantity*lines ; i++) {
			int number = 0;
			do{
				number = (int)((1 + Math.random() * 90));
			}while(exists(number, array)); // Call the function to check numbers

			array[i] = number;
		}

		// Order it
		Arrays.sort(array);

		// Place numbers in the new array
		for (int i = 0, j = 0; i < lines ; i++) {
			for (int k = 0; k < quantity; k++, j++) { // "j" is for the single array
				paper[i][k] = array[j];
			}
		}
		return paper; // Returns the new array
	}

	/**
	 * Function that checks if the number exists in the array
	 * @param number
	 * @param array
	 * @return true or false (depens on the existence of the number in the array)
	 */
	public static boolean exists(int number, int [] array){
		for (int i = 0; i < array.length; i++) {
			// Checks if number exists at "i" in the array
			if(number == array[i]){
				return true; // If exists, returns true
			}
		}
		return false; // If doesn't exist, returns false
	}

	////////////////////////////////////////
}

/*
Pregunta por cartones
Bolas del 1 al 90
Cartones con 3 filas de 5 números (15 nums)
Generar bolas (descartando las que sales)
Los cartones presentan los números ordenados de menor a mayor
Hay que mostrar qué cartón ha ganado con los números que han salido
Y el total de bolas que han salido con sus números
 */

