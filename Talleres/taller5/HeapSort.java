package aed;

public class HeapSort<T extends Comparable<T>>{

    public void heapSort(T[] heap) {
        toHeap(heap);
        for (int i = heap.length - 1; i >= 0; i--) {
            T min = heap[0];
            heap[0] = heap[i];
            bajar(heap, heap[0], 0, i);
            heap[i] = min;
        }
    }
    
    public void toHeap(T[] arr) {
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            bajar(arr, arr[i], i, arr.length);
        }
    }
    
    public void bajar(T[] heap, T actual, int pos, int heap_size) {
        int hijo_izq = 2 * pos + 1;
        int hijo_der = 2 * pos + 2;
    
        int hijoMayor;
    
        if (hijo_izq < heap_size && actual.compareTo(heap[hijo_izq]) > 0) {
            hijoMayor = hijo_izq;
        } else {
            hijoMayor = pos;
        }
    
        if (hijo_der < heap_size && heap[hijoMayor].compareTo(heap[hijo_der]) > 0) {
            hijoMayor = hijo_der;
        }
    
        if (hijoMayor != pos) {
            heap[pos] = heap[hijoMayor];
            heap[hijoMayor] = actual;
            bajar(heap, actual, hijoMayor, heap_size);
        }
    }


    public void heapSortIP(IPv4Address[] heap, int octet) {
        toHeapIP(heap, octet);
        for (int i = heap.length - 1; i >= 0; i--) {
            IPv4Address max = heap[0];
            heap[0] = heap[i];
            bajarIP(heap, heap[0], 0, i, octet);
            heap[i] = max;
        }
    }
    
    public void toHeapIP(IPv4Address[] arr, int octet) {
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            bajarIP(arr, arr[i], i, arr.length, octet);
        }
    }
    
    public void bajarIP(IPv4Address[] heap, IPv4Address actual, int pos, int heap_size, int octet) {
        int hijo_izq = 2 * pos + 1;
        int hijo_der = 2 * pos + 2;
    
        int hijoMayor;
    
        if (hijo_izq < heap_size && actual.getOctet(octet) < heap[hijo_izq].getOctet(octet)) {
            hijoMayor = hijo_izq;
        } else {
            hijoMayor = pos;
        }
    
        if (hijo_der < heap_size && heap[hijoMayor].getOctet(octet) < heap[hijo_der].getOctet(octet)) {
            hijoMayor = hijo_der;
        }
    
        if (hijoMayor != pos) {
            heap[pos] = heap[hijoMayor];
            heap[hijoMayor] = actual;
            bajarIP(heap, actual, hijoMayor, heap_size, octet);
        }
    }
}
