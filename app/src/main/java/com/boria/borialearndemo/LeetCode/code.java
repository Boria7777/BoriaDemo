package com.boria.borialearndemo.LeetCode;

import java.util.HashMap;
import java.util.Map;

/***********************************************************************************************
 * 类名称:   
 * 类描述:  
 * 创建人:   包勇 2019/3/21. 
 * 创建时间:   2019/3/21. 
 * 创建备注：
 * 创建版本:  
 * 修改人:    
 * 修改时间:  
 * 修改备注: 
 *
 ************************************************************************************************/

public class code {

  //两数之和算法
  //解法好几种 一种暴力的 双层循环来算 时间复杂度：O(n^2) 空间复杂度：O(1)
  //利用HashMap的Key-value对应机制来计算
  //一种是先全入库 然后一个一个找 时间复杂度：O(n) 空间复杂度：O(n)
  //另一种是一边入库一边往前找 时间复杂度：O(n) 空间复杂度：O(n)
  public int[] twoSum(int[] nums, int target) {
    Map<Integer, Integer> map = new HashMap<>();
    for (int i = 0; i < nums.length; i++) {
      int complement = target - nums[i];
      if (map.containsKey(complement)) {
        return new int[]{map.get(complement), i};
      }
      map.put(nums[i], i);
    }
    throw new IllegalArgumentException("No two sum solution");
  }


  //无重复字符的最长子串
  //利用HashMap来判断
  //
  public int lengthOfLongestSubstring(String s) {
    int n = s.length(), ans = 0;
    Map<Character, Integer> map = new HashMap<>();
    int i = 0;
    for (int j = 0; j < n; j++) {
      if (map.containsKey(s.charAt(j))) {
        i = Math.max(map.get(s.charAt(j)), i);
      }
      ans = Math.max(ans, j - i + 1);
      map.put(s.charAt(j), j + 1);
    }
    return ans;
  }
}

