package stringdemo;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/7/2522:13
 */
public class Solution {
    public int compress(char[] chars) {
        int n = chars.length;
        int write = 0, left = 0;
        for(int read = 0; read < n; read++){
            if(read == n-1 || chars[read] != chars[read+1]){
                chars[write++] = chars[read];
                int num = read-left+1;
                if(num > 1){
                    int anchor = write;
                    while(num > 0){
                        chars[write++] = (char)(num%10 + '0');
                        num /= 10;
                    }
                    reverse(chars, anchor, write-1);
                }
                left = read+1;
            }
        }
        return write;

    }

    public void reverse(char[] chars, int left, int right){
        while(left < right){
            char temp = chars[left];
            chars[left] = chars[right];
            chars[right] = temp;
            left++;
            right--;
        }
    }
    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.compress(new char[]{'w','w','w','w','w','b','b','g','g','g','g','a','a','a','i','i','i','i','y','y','p','v','v','v','u','u','u','y','y','y','y','y','y','y','y','y','s','q','q','q','q','q','q','q','q','q','q','n','n','n'}));
//        'a', 'a', 'b', 'b', 'c', 'c', 'c'
//        'a','b','b','b','b','b','b','b','b','b','b','b','b'
//        'a','b','c'
//        'o','o','o','o','o','o','o','o','o','o'
    }
}
