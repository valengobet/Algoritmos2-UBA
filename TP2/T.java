package aed;

public class T {
    //INVARIANTE DE REPRESENTACIOM
    // Para cada elemento de tipo T:
    // -0<id<P
    // -cociente es el cociente calculado con el sistema D'Hont
    // -bancas_obtenidas suman las bancas totales para cada distrito y se calculan con el sistema D'Hont
    // -votos_totales son los elementos de resultados diputados para su correspondiente distrito y partido.

    public int id;
    public int cociente;
    public int bancas_obtenidas;
    public int votos_totales;

    T(int id_partido, int votos_partido){
        id = id_partido;
        votos_totales = votos_partido;
        cociente = votos_partido;
        bancas_obtenidas = 0;
    }

    public static void toHeap(T[] arrDeT){
        for(int i = arrDeT.length/2 - 1; i >= 0; i--){
            T actual = arrDeT[i];
            int hijo_izq = 2*i +1;
            int hijo_der = 2*i +2;
            if((hijo_der) > arrDeT.length - 1){
                if((hijo_izq) <= arrDeT.length - 1){
                    if(actual.cociente < arrDeT[hijo_izq].cociente){
                        arrDeT[i] = arrDeT[hijo_izq];
                        arrDeT[hijo_izq] = actual;
                        reOrdenaHeap(arrDeT, actual, hijo_izq);
                    } 
                }
            }
            else if(actual.cociente < arrDeT[hijo_izq].cociente || actual.cociente < arrDeT[hijo_der].cociente){
                if(arrDeT[hijo_izq].cociente >= arrDeT[hijo_der].cociente){
                    arrDeT[i] = arrDeT[hijo_izq];
                    arrDeT[hijo_izq] = actual;

                    reOrdenaHeap(arrDeT, actual, hijo_izq);
                } else{
                    arrDeT[i] = arrDeT[hijo_der];
                    arrDeT[hijo_der] = actual;
                    reOrdenaHeap(arrDeT, actual, hijo_der);
                }
            }
        }
        
    }

    public static void reOrdenaHeap(T[] heap, T raiz, int pos){
        T actual = raiz;
        int hijo_izq = 2* pos +1;
        int hijo_der = 2* pos +2;
        if((2*pos +2) > heap.length - 1){
            if((hijo_izq) <= heap.length - 1){
                if(heap[pos].cociente < heap[hijo_izq].cociente){
                    heap[pos] = heap[hijo_izq];
                    heap[hijo_izq] = actual;
                }
            }
        } else if(heap[pos].cociente < heap[hijo_izq].cociente || heap[pos].cociente < heap[hijo_der].cociente){
            if(heap[hijo_izq].cociente >= heap[hijo_der].cociente){
                heap[pos] = heap[hijo_izq];
                heap[hijo_izq] = actual;
                reOrdenaHeap(heap, heap[hijo_izq], hijo_izq);
            } else{
                heap[pos] = heap[hijo_der];
                heap[hijo_der] = actual;
                reOrdenaHeap(heap, heap[hijo_der], hijo_der);
            }
        }
    }
}