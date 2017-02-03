package com.joaquinjimenezgarcia;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {

	// El bote con las bolas de la partida.
	private static int[] pot;

	// La bola actual dentro del array pot.
	private static int currentBall = 0;

	private static Scanner input = new Scanner(System.in);

	public static void main(String[] args) {
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
			//checkLine();
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
	static int [] createPaper(){
		int cantidad = 15; //numeros aleatorio
		int aux = cantidad;  //auxiliar;
		int[] numeros = new int[cantidad];
		int[] resultado = new int[cantidad];
		Random rnd = new Random();
		int res;


		//se rellena una array del 1 al 90
		for(int i=0;i<cantidad;i++){
			numeros[i]=(int)((1 + Math.random() * 90));
		}

		for(int i=0;i<cantidad;i++){
			res=rnd.nextInt(aux);
			resultado[i]=numeros[res];
			numeros[res]=numeros[aux-1];
			aux--;

		}

		//Ordena el array
		Arrays.sort(resultado);

		//se imprime el resultado;
		return resultado;

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

