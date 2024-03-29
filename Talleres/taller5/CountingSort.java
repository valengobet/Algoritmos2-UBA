package aed;
public class CountingSort<T extends Comparable<T>>{
    public void coutingSort(Fragment[] arr, int k){
        int [] B = new int[k];
        for(int i = 0; i < arr.length; i++){
            B[arr[i]._id] = B[arr[i]._id] + 1;
        }
        int indexArr = 0;
        for(int j = 0; j < k; j++){
            for(int l = 1; l <= B[j]; l++){
                arr[indexArr] = new Fragment(j);
                indexArr++;
            }
        }
    }
}
