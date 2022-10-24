import java.util.Random;
/**
 * @author Nick Oehler
 * Mathe 3 - Computer Algebra Systeme
 * Blatt 5, Aufgabe 1
 * 
 * In dieser Aufgabe soll herausgefunden werden, ob
 * die Geschwindigkeit der Berechnung des Matrix-Vektor-Produkts
 * von der Art der Definition der Matrix abhängt.
 *
 * 1. Matrix liegt als 2-dimensionales Array vor
 * 2. Matrix liegt als 2 eindimensionale Arrays vor
*/
public class MatrixVektorProdukt {

	public static void main(String[] args) {


		/************** Parameter ****************/
		Random r = new Random();
		double rangeMin = 0;
		double rangeMax = 10;


		// Matrix als eindimensionale Arrays
		int m = 100;
		int[] spalten = new int[m];
		int[] zeilen = new int[m];
		double[] werte = new double[m];

		
		
		// Größe der Matrix und des Vektors
		int n=10;
		// Spaltenvektor
		double[] v = new double[n]; // Faktor
		// Matrix
		double[][] matrixA = new double[n][n];

		// Produkt-Vektoren
		double[] u = new double[n];
		double[] u2 = new double[n];


		/***** Initialisierung Matrix+Vektoren für (a) ******/
		for(int i=0; i<n; i++) {
			v[i] = rangeMin + (rangeMax - rangeMin) * r.nextDouble();

		}

		for(int i=0; i<m; i++)
		{
			spalten[i] = (i % n) +1;
			zeilen[i] = (i/n) +1;
			werte[i] = rangeMax * r.nextDouble();
		}
		
		
		/************ Belegung der Matrix für (b) ********/
		for (int i=0; i<m; i++)
			matrixA[ zeilen[i]-1 ][ spalten[i]-1 ] = werte[i];


		/************** Berechnung ***************/
		// Ausführung der Berechnung + Zeitmessung
		long startTime = System.nanoTime();
		u = berechneProduktMatrix(matrixA, v);
		long endTime = System.nanoTime();

		long startTime2 = System.nanoTime();
		u2 = berechneProduktArrays(zeilen, spalten, werte, v);
		long endTime2 = System.nanoTime();


		// Angabe in Nanosekunden, für höhere Präzision
		long dauer = (endTime - startTime);
		long dauer2 = (endTime2 - startTime2);


		/************* Ausgabe Ergebnis - ALT ********/
		// Ausgabe des berechneten Vektors u
		System.out.print("u1 = (");
		for(int i=0; i<n; i++)
			System.out.print(" " + u[i] + " ");
		System.out.println(")");

		// Ausgabe des berechneten Vektors u2
		System.out.print("u2 = (");
		for(int i=0; i<n; i++)
			System.out.print(" " + u2[i] + " ");
		System.out.println(")");

/*		System.out.println("Startzeit: " + startTime);
		System.out.println("Endzeit: " + endTime + "\n"); 
		

		/************* Ausgabe Ergebnis - NEU ********/
		System.out.println("\n          | Dauer der Berechnung");
		System.out.println("mit Matrix| " + dauer + " Nanosekunden");
		System.out.println("mit Arrays| " + dauer2 + " Nanosekunden");
	}

	/* Funktionen */

	static double[] berechneProduktMatrix(double[][] matrix, double[] vektor) {
		int length = vektor.length;
		double[] ergebnis = new double[length];

		for (int i=0; i<length; i++) {
			for (int j=0; j<length; j++) {
				ergebnis[i] += vektor[j] * matrix[i][j];
			}
		}
		return ergebnis;
	}
	static double[] berechneProduktArrays(int[] zeilen, int[] spalten, double[] werte, double[] vektor) {
		int length = vektor.length;
		double[] ergebnis = new double[length];

		for (int i=0; i<werte.length; i++)
			ergebnis[ zeilen[i]-1 ] += werte[i] * vektor[ spalten[i]-1 ];

		return ergebnis;
	}
}