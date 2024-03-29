package aed;

import java.util.*;

// Todos los tipos de datos "Comparables" tienen el método compareTo()
// elem1.compareTo(elem2) devuelve un entero. Si es mayor a 0, entonces elem1 > elem2
public class ABB<T extends Comparable<T>> implements Conjunto<T> {
    // Agregar atributos privados del Conjunto
    private Nodo primero;
    private Nodo menor;
    private class Nodo {
        // Agregar atributos privados del Nodo
        T valor;
        Nodo izq;
        Nodo der;
        Nodo pad; 

        // Crear Constructor del nodo
        //Cuando no hay nodos
        public Nodo(T elem){
            this(elem, null, null, null);
        }
        //Cuando hay nodos
        public Nodo(T elem, Nodo padre, Nodo izquierdo, Nodo derecho){
            valor = elem;
            izq = izquierdo;
            der = derecho;
            pad = padre;
        }
    }

    public ABB() {
        primero = null;
        
    }

    public void actualizarMenor(){
        Nodo actual = primero;
        if(actual == null){
            menor = null;
        }else{
            while(actual.izq != null){
                actual = actual.izq;
            }
            menor = actual;
        }
    }

    public int cardinal() {
        return cardinal2(primero);
    }

    public int cardinal2(Nodo raiz) {
        if(raiz == null){
            return 0;
        }
        return cardinal2(raiz.izq) + 1 + cardinal2(raiz.der);
    }
    
    public T minimo(){
        Nodo actual = primero;
        while(actual.izq != null){
            actual = actual.izq;
        }
        return actual.valor;
    }

    public T maximo(){
        Nodo actual = primero;
        while(actual.der != null){
            actual = actual.der;
        }
        return actual.valor;
    }

    public void insertar(T elem){
        if(primero == null){
            Nodo newNode = new Nodo (elem);
            primero = newNode;
        }else if(this.pertenece(elem)){
            return;
        }else{
            boolean bandera = true;
            Nodo actual = primero;
            while(bandera == true){
                if(elem.compareTo(actual.valor) == 0){
                    return;
                }else if(elem.compareTo(actual.valor) < 0){
                    if(actual.izq == null){
                        Nodo newNode = new Nodo(elem, actual, null, null);
                        actual.izq = newNode;
                        bandera = false;
                    }else{
                        actual = actual.izq;
                    }  
                } else if (elem.compareTo(actual.valor) > 0){
                    if(actual.der == null){
                        Nodo newNode = new Nodo(elem, actual, null, null);
                        actual.der = newNode;
                        bandera = false;
                    }else{
                        actual = actual.der;
                    }  
                }  
            }
        }
        this.actualizarMenor();
    }

    public boolean pertenece(T elem){
        Nodo actual = primero;
        boolean bandera = true;
        boolean pertenece = false;
        if(primero == null){
            return false;
        } while(bandera == true){
            if(elem.compareTo(actual.valor) == 0){
                pertenece = true;
                bandera = false;
            }else if(elem.compareTo(actual.valor) < 0 && actual.izq != null){
                actual = actual.izq;
            }else if(elem.compareTo(actual.valor) > 0 && actual.der != null){
                actual = actual.der;
            }else{
                pertenece = false;
                bandera = false;
            }
        }
        return pertenece;
    }   
    public void eliminar(T elem){
        Nodo actual = primero;
        boolean bandera = true;
        while(bandera == true){
            if(elem.compareTo(actual.valor) == 0){
                bandera = false;
            }else if(elem.compareTo(actual.valor) < 0){
                actual = actual.izq;
            }else if(elem.compareTo(actual.valor) > 0){
                actual = actual.der;
            }
        }
        if(actual.izq == null && actual.der == null){
            if(actual == primero){
                primero = null;
            }else if(actual.pad.izq == actual){
                actual.pad.izq = null;
            } else if(actual.pad.der == actual){
                actual.pad.der = null;
            }
        } else if(actual.izq == null){
            if(actual == primero){
                primero = actual.der;
                actual.der.pad = null;
            }else if(actual.pad.izq == actual){
                actual.pad.izq = actual.der;
                actual.der.pad = actual.pad;
            } else if(actual.pad.der == actual){
                actual.pad.der = actual.der;
                actual.der.pad = actual.pad;
            }
        } else if(actual.der == null){
            if(actual == primero){
                primero = actual.izq;
                actual.izq.pad = null;
            }else if(actual.pad.izq == actual){
                actual.pad.izq = actual.izq;
                actual.izq.pad = actual.pad;
            }else if(actual.pad.der == actual){
                actual.pad.der = actual.izq;
                actual.izq.pad = actual.pad;
            }
        } else if(actual.der != null && actual.izq != null){
            T menor = encontrarMenor(actual.der).valor;
            this.eliminar(encontrarMenor(actual.der).valor);
            actual.valor = menor;
        }
        this.actualizarMenor();
    }

    public Nodo encontrarMenor(Nodo raiz){
        while(raiz.izq != null){
            raiz = raiz.izq;
        }
        return raiz;
    }

    public String toString(){
        boolean bandera = true;
        String string = "{";
        Nodo actual = primero;
        while(actual.izq != null){
            actual = actual.izq;
        }
        T ultimo_agregado = null;
        while(bandera == true){
            if(actual.valor == this.maximo()){
                string = string + actual.valor + "}";
                bandera = false;
            }
            else if(actual.der == null){
                string = string + actual.valor + ",";
                ultimo_agregado = actual.valor;
                while(ultimo_agregado.compareTo(actual.pad.valor) > 0){
                    actual = actual.pad;
                }
                actual = actual.pad;
            }else if(actual.der != null){
                string = string + actual.valor + ",";
                ultimo_agregado = actual.valor;
                actual = actual.der;
                while(actual.izq != null){
                    actual = actual.izq;
                }
            }
        }
        return string;
    }

    private class ABB_Iterador implements Iterador<T> {
        private Nodo _actual = menor;
        private Nodo maximo;

        public boolean haySiguiente() {            
            boolean res = false;
            Nodo maximo = primero;
            while(maximo.der != null){
                maximo = maximo.der;
            }
            if(_actual.valor == maximo){
                res = false;
            } else{
                res = true;;
            }
            return res;
        }
    
        public T siguiente() {
            boolean bandera = true;
            T res = null;
            T ultimo_agregado = null;
            if(_actual.valor == maximo){
                res = _actual.valor;
                bandera = false;
            }
            else if(_actual.der == null){
                res = _actual.valor;
                ultimo_agregado = _actual.valor;
                if(ultimo_agregado.compareTo(_actual.pad.valor) > 0){
                    _actual = _actual.pad;
                }
                _actual = _actual.pad;
            }else if(_actual.der != null){
                res = _actual.valor;
                ultimo_agregado = _actual.valor;
                _actual = _actual.der;
                while(_actual.izq != null){
                    _actual = _actual.izq;
                }
            }
            return res;
        }
    }

    public Iterador<T> iterador() {
        return new ABB_Iterador();
    }
}   