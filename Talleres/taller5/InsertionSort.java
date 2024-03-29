package aed;
public class InsertionSort {
    public void insertionSort(IPv4Address[] IPs, int octet){
        for(int i = 1; i < IPs.length; i++){
            insertar(IPs, octet, i);
        }
    }

    public void insertar(IPv4Address[] IPs, int octet, int i){
         int j = i - 1;
         IPv4Address elem = IPs[i];

         while( j >= 0 && IPs[j].getOctet(octet) > elem.getOctet(octet)){
            IPs[j+1] = IPs[j];
            j = j -1;
         }
         IPs[j+1] = elem;
    }
}
