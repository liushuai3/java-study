package cn.lcools.exercises;

import org.junit.Test;

import java.util.Random;

/**
 * Copyright: Copyright (c) 2020
 *
 * @ClassName: RandomNumber
 * @Description: 生成1-100不重复的随机数
 * @version: v1.0.0
 * @author: liushuai
 * @date: 2020/8/25 13:34
 * *****
 * Modification History:
 * Date         Author          Version            Description
 * ---------------------------------------------------------*
 * 2020/8/25     liushuai           v1.0.0               修改原因
 */
public class RandomNumber {

    public int[] getNumber(int n) {
        int[] original = new int[n];
        int[] ret = new int[n];
        for (int i = 0; i < n; i++) {
            original[i] = i;
        }
        for (int i = 0; i < n; i++) {
            int index = new Random().nextInt(n-i);
            ret[i] = original[index];
            original[index] = original[n-i-1];
        }
        return ret;
    }

    @Test
    public void test(){
        int[] a = getNumber(100);
        for (int i = 0; i < a.length; i++) {
            System.out.printf(a[i]+",");
        }
    }
}
