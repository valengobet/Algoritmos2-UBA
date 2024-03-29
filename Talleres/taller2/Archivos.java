package aed;

import java.util.Locale;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

class Archivos {
    static float[] leerVector(Scanner entrada, int largo) {
        float[] vec = new float[largo];
        int contador = 0;
        while(entrada.hasNextFloat() && contador < largo){
            vec[contador] = entrada.nextFloat();
            contador ++;
        }
        return vec;
    }

    float[][] leerMatriz(Scanner entrada, int filas, int columnas) {
        float[][] matriz = new float[filas][columnas];
        int contador_filas = 0;
        while(contador_filas < filas){
            matriz[contador_filas] = leerVector(entrada, columnas);
            contador_filas ++;
        }
        return matriz;
    }

    void imprimirPiramide(PrintStream salida, int alto) {
        int cantidad_puntos = 0;
        int cantidad_espacios = alto - 1;
        for(int i = 0; i < alto; i++){
            cantidad_puntos = (2 * i) + 1;
            salida.println(" ".repeat(cantidad_espacios) + "*".repeat(cantidad_puntos) + " " .repeat(cantidad_espacios));
            cantidad_espacios = cantidad_espacios - 1;
        }
    }
}


