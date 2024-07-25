package stringdemo;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/7/2522:13
 */
public class Solution {
    public int minimumOperations(String num) {
//        List<Integer> l0 = new ArrayList<>(), l2 = new ArrayList<>(), l5 = new ArrayList<>(), l7 = new ArrayList<>();
//        int len = num.length();
//        for(int i = len-1; i >= 0; i--){
//            if(num.charAt(i) == '0'){
//                l0.add(i);
//            }else if(num.charAt(i) == '2'){
//                l2.add(i);
//            }else if(num.charAt(i) == '5'){
//                l5.add(i);
//            }else if(num.charAt(i) == '7'){
//                l7.add(i);
//            }
//        }
//        int n00 = len, n25 = len, n50 = len, n75 = len, n0 = len;
//        if(l0.size() >= 1){
//            n0 = len-1;
//        }
//        if(l0.size() >= 2)
//            n00 =  len-1 -l0.get(1)-1;
//        if(l5.size() >= 1 && l2.size()>=1){
//            int index2 = l2.get(0), i = 0;
//            while(index2 > l5.get(0) && i < l2.size()-1){
//                index2 = l2.get(++i);
//            }
//            if(index2 < l5.get(0)) n25 =  len-index2-2;
//            // System.out.println(n25);
//        }
//        if(l0.size() >= 1 && l5.size()>=1){
//            int index2 = l5.get(0), i = 0;
//            while(index2 > l0.get(0) && i < l5.size()-1){
//                index2 = l5.get(++i);
//            }
//            if(index2 < l0.get(0)) n50 =  len-index2-2;
//            // System.out.println(n50);
//
//        }
//        if(l5.size() >= 1 && l7.size()>=1){
//            int index2 = l7.get(0), i = 0;
//            while(index2 > l5.get(0) && i < l7.size()-1){
//                index2 = l7.get(++i);
//            }
//            if(index2 < l5.get(0)) n75 =  len-index2-2;
//            // System.out.println(n75);
//
//        }
//
//        return Math.min(n0, Math.min(Math.min(n50, n75), Math.min(n00, n25)));
        int n = num.length();
        boolean find0 = false, find5 = false;
        for(int i = n-1; i >= 0; i--){
            if(num.charAt(i) == '0' || num.charAt(i) == '5'){
                if(find0){
                    return n-i-2;
                }
                if(num.charAt(i) == '0'){
                    find0 = true;
                }else{
                    find5 = true;
                }
            }else if(num.charAt(i) == '2' || num.charAt(i) == '7'){
                if (find5) {
                    return n - i - 2;
                }
            }
        }
        if(find0){
            return n-1;
        }
        return n;
    }

    public static void main(String[] args){
        Solution s = new Solution();
        System.out.println(s.minimumOperations("2908305"));
    }
}
