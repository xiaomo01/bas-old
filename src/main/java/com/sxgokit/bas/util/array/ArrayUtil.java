package com.sxgokit.bas.util.array;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class ArrayUtil {

    /**
     * Long类型数组去重
     * @param arr
     * @return
     */
    public static Long[] removeDupl(Long[] arr){
        if(arr != null && arr.length != 0){
            Set<Object> set = new HashSet<>(arr.length);
            Collections.addAll(set, arr);
            arr = Arrays.stream(set.toArray()).map(p -> Long.valueOf(p.toString())).toArray(Long[]::new);
        }
        return arr;
    }
}
