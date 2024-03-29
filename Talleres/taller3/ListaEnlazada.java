package aed;

import java.util.*;

import javax.swing.plaf.synth.SynthPasswordFieldUI;

public class ListaEnlazada<T> implements Secuencia<T> {
    // Completar atributos privado
    private Nodo primero;
    private T punt_primero;
    private T punt_ultimo;

    private class Nodo {
        // Completar
        T valor;
        Nodo sig;
        Nodo ant;
        //Constructor cuando no hay nodos
        public Nodo(T elem){
            this(elem, null, null);
        }
        //Constructor cuando hay nodos
        public Nodo(T elem, Nodo anterior, Nodo siguiente){
            valor = elem;
            sig = siguiente;
            ant = anterior;
        }
    }

    public ListaEnlazada() {
        primero = null;
    }

    public int longitud() {
        int res = 1;
        if (primero == null){
            return 0;
        } else{
            Nodo actual = primero;
            while(actual.sig != null){
                res ++;
                actual = actual.sig;
            }
        }
        return res;
    }

    public void agregarAdelante(T elem) {
        if (primero == null){
            Nodo newNodo = new Nodo(elem);
            primero = newNodo;
            punt_primero = newNodo.valor;
            punt_ultimo = newNodo.valor;
        } else {
            Nodo actual = primero;
            Nodo newNodo = new Nodo(elem,null,actual);
            actual.ant = newNodo;
            while(actual.sig != null){
                actual = actual.sig;
            }
            punt_primero = newNodo.valor;
            punt_ultimo = actual.valor;
            primero = newNodo;
            }
        }
    

    public void agregarAtras(T elem) {
        if (primero == null){
            Nodo newNodo = new Nodo(elem);
            primero = newNodo;
            punt_primero = newNodo.valor;
            punt_ultimo = newNodo.valor;
        } else {
            Nodo actual = primero;
            while (actual.sig != null){
                actual = actual.sig;
            }
            Nodo newNodo = new Nodo(elem,actual, null);
            actual.sig = newNodo;
            punt_ultimo = newNodo.valor;
            }
    }

    public T obtener(int i) {
        int contador = 0;
        Nodo actual = primero;
        while(contador < i){
            actual = actual.sig;
            contador ++;
        }
        return actual.valor;
    }

    public void eliminar(int i) {
        Nodo actual = primero;
        int contador = 0;
        if(i == 0 && actual.sig == null){
            primero = null;  
        } if((i == 0) && (actual.sig != null)){
            primero = actual.sig;
            punt_primero = actual.sig.valor;
        } else{
            while(contador < i){
                actual = actual.sig;
                contador ++;
            }
            actual.ant.sig = actual.sig;
            if(actual.sig != null){
            actual.sig.ant = actual.ant; 
            punt_ultimo = actual.valor;
            }
        }
    }

    public void modificarPosicion(int indice, T elem) {
        int contador = 0;
        Nodo actual = primero;
        while(contador < indice){
            actual = actual.sig;
            contador ++;
        }
        actual.valor = elem;
    }

    public ListaEnlazada<T> copiar() {
        ListaEnlazada<T> listaCopiada =  new ListaEnlazada<>();
        if(primero == null){
            return listaCopiada;
        } else{
            Nodo actual = primero;
            listaCopiada.punt_primero = punt_primero;
            listaCopiada.punt_ultimo = punt_ultimo;
            while(actual.sig != null){
                listaCopiada.agregarAtras(actual.valor);
                actual = actual.sig;
            }
            listaCopiada.agregarAtras(actual.valor);
        }
        return listaCopiada;
    }

    public ListaEnlazada(ListaEnlazada<T> lista) {
        if (lista.primero == null){
            this.primero = null;
        } else{
            this.primero = new Nodo(lista.primero.valor);
            Nodo actual = this.primero;
            Nodo actual_original = lista.primero;
            while(actual_original.sig != null){
                actual.sig = new Nodo(actual_original.sig.valor);
                actual = actual.sig;
                actual_original = actual_original.sig;
            }
        }
    }

    @Override
    public String toString() {
        Nodo actual = primero;
        String string = "[";
        if(primero == null){
            string = string + "]";
        } else{
            while(actual.sig != null){
                string = string + actual.valor + "," + " ";
                actual = actual.sig;
            }
            string = string + actual.valor + "]";
        }
        return string;
    }

    private class ListaIterador implements Iterador<T> {
    	// Completar atributos privados
        private Nodo actual;
        private Nodo primero;
        private Nodo ultimo;
        private Nodo nodo_final;

        public ListaIterador(ListaEnlazada<T> lista){
            actual = lista.primero;
            primero = lista.primero;
            if(actual != null){
                while(actual.sig != null){
                    actual = actual.sig;
                }
                ultimo = actual;
                Nodo nodo_final = new Nodo(primero.valor, ultimo, null);
                ultimo.sig = nodo_final;
                actual = lista.primero;
            }else {
                ultimo = lista.primero;
            }
        }

        public boolean haySiguiente() {
            if(actual == null){
                return false;
            }
            if(actual.sig == null && actual != primero){
                return false;
            } else{
                return true;
            }
        }
        
        public boolean hayAnterior() {
            if(actual == primero){
                return false;
            } else{
                return true;
            }
        }

        public T siguiente() {
            T valor = actual.valor;
            actual = actual.sig;
            return valor;
        }
        
        public T anterior() {
            actual = actual.ant;
            T valor = actual.valor;
            return valor;
        }
    }

    public Iterador<T> iterador() {
        return new ListaIterador(this);
    }
}

