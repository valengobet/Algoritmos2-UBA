package aed;
import aed.HeapSort;
import aed.CountingSort;
public class InternetToolkit {
    public InternetToolkit() {
    }

    public Fragment[] tcpReorder(Fragment[] fragments) {
        // IMPLEMENTAR
        if(verificaOrden(fragments) == true){
            return fragments;
        }else{ 
            int max = fragments[0]._id;
            for (int i = 1; i < fragments.length; i++) {
                if (fragments[i]._id > max) {
                    max = fragments[i]._id;
                }
            }
            CountingSort countintSortInstance = new CountingSort();
            countintSortInstance.coutingSort(fragments, max + 1);
            return fragments;
        }
    }

    public boolean verificaOrden(Fragment[] lista){
        for (int i = 0; i < lista.length-1; i++){
            if (lista[i].compareTo(lista[i+1]) > 0){
                return false;
            }
        }
        return true;
    }

    // public Router[] kTopRouters(Router[] routers, int k, int umbral) {
    //     // IMPLEMENTAR
    //     HeapSort heapSortInstance = new HeapSort();
    //     heapSortInstance.heapSort(routers);
    //     int pasan_umbral = 0;
    //     for(int i = 0; i < routers.length; i++){
    //         if(routers[i].getTrafico() > umbral){
    //             pasan_umbral++;
    //         } 
    //     }

    //     Router[] routers_congestionados;

    //     if(pasan_umbral <= k){
    //         routers_congestionados = new Router[pasan_umbral];
    //     }else{
    //         routers_congestionados = new Router[k];
    //     }

    //     for(int j = 0; j < routers_congestionados.length; j++){
    //         routers_congestionados[j] = routers[j];
    //     }

    //     return routers_congestionados;
    // }

    public Router[] kTopRouters(Router[] routers, int k, int umbral) {
        // IMPLEMENTAR
        HeapSort heapSortInstance = new HeapSort();

        int pasan_umbral = 0;
        for(int i = 0; i < routers.length; i++){
            if(routers[i].getTrafico() > umbral){
                pasan_umbral++;
            } 
        }

        Router[] routers_congestionados = new Router[pasan_umbral];
        for(int i = 0; i < routers.length; i++){
            if(routers[i].getTrafico() > umbral){
                routers_congestionados[i] = routers[i];
            } 
        }

        heapSortInstance.heapSort(routers_congestionados);

        if(k < pasan_umbral){
            Router[] routers_congestionados2 = new Router[k];
            for(int j = 0; j < k; j++){
                routers_congestionados2[j] = routers_congestionados[j];
            }
            return routers_congestionados2;
        }

        return routers_congestionados;
    }

    

    public IPv4Address[] sortIPv4(String[] ipv4) {
        // IMPLEMENTAR
        IPv4Address[] IPS = new IPv4Address[ipv4.length];
        for(int i = 0; i < ipv4.length; i++){
            IPv4Address ipv4Instance = new IPv4Address(ipv4[i]);
            IPS[i] = ipv4Instance;
        }

        InsertionSort insertionSortInstance = new InsertionSort();
        for(int i = 3; i >= 0; i--){
            insertionSortInstance.insertionSort(IPS, i);
        }

        return IPS;
    }
}
