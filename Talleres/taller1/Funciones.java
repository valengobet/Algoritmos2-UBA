package aed;

class Funciones {
    int cuadrado(int x) {
        // COMPLETAR
        int res = x * x;
        return res;
    }

    double distancia(double x, double y) {
        // COMPLETAR
        double res = Math.sqrt((x*x)+(y*y));
        return res;
    }

    boolean esPar(int n) {
        // COMPLETAR
        if(n % 2 == 0){
            return true;
        } 
        return false;
    }

    boolean esBisiesto(int n) {
        // COMPLETAR
        if((n % 4 == 0 && n % 100 != 0) || (n % 400 == 0)){
            return true;
        }
        return false;
    }

    int factorialIterativo(int n) {
        // COMPLETAR
        int res = 1;
        while(n != 0){
            res = res * n;
            n = n -1;
        }
        return res;
    }

    int factorialRecursivo(int n) {
        // COMPLETAR
        if(n == 0 || n == 1){
            return 1;
        }
        return n * factorialRecursivo(n -1);
    }

    boolean esPrimo(int n) {
        // COMPLETAR
        if(n == 1 || n == 0){
            return false;
        }
        for(int i = 2; i < n; i++){
            if(n % i == 0){
                return false;
            }
        }
        return true;
    }

    int sumatoria(int[] numeros) {
        // COMPLETAR
        int res = 0;
        for(int i = 0; i < numeros.length; i++){
            res = res + numeros[i];
        }
        return res;
    }

    int busqueda(int[] numeros, int buscado) {
        // COMPLETAR
        int posicion = 0;
        for(int i = 0; i < numeros.length; i++){
            if(numeros[i] == buscado){
                posicion = i;
            }
        }
        return posicion;
    }

    boolean tienePrimo(int[] numeros) {
        // COMPLETAR
        for(int i = 0; i < numeros.length; i++){
            if(esPrimo(numeros[i]) == true){
                return true;
            }
        }
        return false;
    }

    boolean todosPares(int[] numeros) {
        // COMPLETAR
        for(int i = 0; i < numeros.length; i++){
            if(!esPar(numeros[i])){
                return false;
            }
        }
        return true;
    }

    boolean esPrefijo(String s1, String s2) {
        // COMPLETAR
        if(s1.length() > s2.length()){
            return false;
        }
        for(int i = 0; i < s1.length(); i++){
            if(s1.charAt(i) != s2.charAt(i)){
                return false;
            }
        }
        return true;
    }

    boolean esSufijo(String s1, String s2) {
        // COMPLETAR
        if(s1.length() > s2.length()){
            return false;
        }
        int diferencia_lenght = s2.length() - s1.length();
        for(int i = 0; i < s1.length(); i++){
            if(s1.charAt(i) != s2.charAt(i + diferencia_lenght)){
                return false;
            }
        }
        return true;
    }
}
