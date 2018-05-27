/*
 * quick sort an array in-place
 */
package partition;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author xchen011
 */
public class Partition {

    public static void main(String[] args) {
        
        Scanner in= new Scanner(System.in);
        int n = in.nextInt();
        int [] arr = new int[n];
        for(int i=0;i<n;i++){
           arr[i]=in.nextInt();
        }
        QSort(arr);
        printArray(arr,0,n-1);
       
    }
   
    public static void QSort(int[] arr){
        int n = arr.length;
        sort(arr,0,n-1);
       
    }
    
    /* sort method been called recursively to make sure the left part of pivot is no greater and right part is no less than the
     *pivot.
     */
    public static void sort(int [] a, int lo, int hi){
        if(lo>=hi) return;
        int k = Partition(a,lo,hi);
        sort(a,lo,k-1);       //sort the left part of subarray
        //printArray(arr,0,k-1);
        sort(a,k+1,hi); //sort the right part of subaraay
    }
    
    /*
    *partition method, based on Hoare Partition method, two boundary indexes lo and hi, lo<=hi,one is pointing to the beginning of the array, the other is
    *pointing the end. put the first value of arr[lo] as pivot, i=lo+1,j=hi,the two indexes i and j move toward, stop at
    *arr[j]<pivot && arr[i]>pivot, then exchange arr[i] and arr[j]; terminator conduction, lo>hi;
    */
    
    public static int Partition(int [] arr, int lo, int hi){
        
        int V=arr[lo];
        int i = lo;
        int j=hi+1;
        if(isMin(arr,V)) i = lo+1; //to avoid unexpected result when the first elements is the minimal value. 
        while(true){
            while(i<hi){
                if(arr[++i]>V) 
                    break;
            }
        while(j>lo){
            if(arr[--j]<V) 
                break;
        }
            if(i>=j) break;
            arr = ExchangeTwo(arr,i,j);
        }
        
        arr = ExchangeTwo(arr,lo,j);
        
        //printArray(arr,0,arr.length-1);
        return j;
    }
    public static boolean isMin(int[] arr,int val){
        
        for(int i=1;i<arr.length-1;i++){
            if(val>arr[i]) return false;
        }
        return true;
    }
    public static boolean isMax(int[] arr,int val){
        
        for(int i=1;i<arr.length-1;i++){
            if(val<arr[i]) return false;
        }
        return true;
    }
    public static int[] ExchangeTwo(int [] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
        return arr;
    }
    
    public static void printArray(int[] arr,int start, int end){
        for(int i = start;i<=end;i++){
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}
