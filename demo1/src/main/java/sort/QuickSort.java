package sort;

import java.util.Arrays;

/**
 * @Description: 螺母和螺帽问题
 * @Author: ls
 * @Date: 2024/8/25 9:23
 */
public class QuickSort {
    static int n = 1, temp;
    static int[] a = { 4,5,2,6,1,7,8,9,3 };
    static int[] b = { 6,2,1,8,9,7,4,5,3 };
    public static void main(String[] args) {
        Sort(a, b, 0, a.length - 1);
        System.out.println(Arrays.toString(a));
        System.out.println(Arrays.toString(b));
    }

    public static void Sort(int[] a, int[] b, int left, int right) {
        if(left < right){
            int temp = a[left];
            int i = left, j = right;
            while(i < j){
                while(i<j && b[i] < temp) i++;
                while(i<j && b[j] > temp) j--;
                if(i < j) swap2(i, j, b);
            }
            swap2(i, left, b);
            System.out.println("第"+n+"次操作 b:  "+Arrays.toString(b));

            temp = b[left+1];
            i = left+1;
            j = right;
            while(i < j){
                while(i < j && a[i] < temp) i++;
                while (i<j&&a[j]>temp) 	j--;
                if (i<j) swap1(i,j,a);
            }
            swap1(left+1, i, a);
            System.out.println("第"+n+"次操作 a:  "+ Arrays.toString(a));

            n++;
            Sort(a,b,left+2,i);
            Sort(a,b,i+1,right);
        }
    }
    public static void swap1(int m,int n,int[] a) {
        temp = a[m];
        a[m] = a[n];
        a[n] = temp;
    }
    public static void swap2(int m,int n,int[] b) {
        temp=b[m];
        b[m]=b[n];
        b[n]=temp;
    }

}
