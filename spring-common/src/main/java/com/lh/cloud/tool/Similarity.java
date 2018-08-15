package com.lh.cloud.tool;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * 字符串相似性匹配算法
 * Created by panther on 15-7-20.
 */
public class Similarity {
    Map<Character, int[]> vectorMap = new HashMap<>();

    int[] tempArray = null;

    public Similarity(String string1, String string2) {
        for (Character character1 : string1.toCharArray()) {
            if (vectorMap.containsKey(character1)) {
                vectorMap.get(character1)[0]++;
            } else {
                tempArray = new int[2];
                tempArray[0] = 1;
                tempArray[1] = 0;
                vectorMap.put(character1, tempArray);
            }
        }
        for (Character character2 : string2.toCharArray()) {
            if (vectorMap.containsKey(character2)) {
                vectorMap.get(character2)[1]++;
            } else {
                tempArray = new int[2];
                tempArray[0] = 0;
                tempArray[1] = 1;
                vectorMap.put(character2, tempArray);
            }
        }
    }

    // 求余弦相似度
    public double sim() {
        double result = 0;
        result = pointMulti(vectorMap) / sqrtMulti(vectorMap);
        return result;
    }

    private double sqrtMulti(Map<Character, int[]> paramMap) {
        double result = 0;
        result = squares(paramMap);
        result = Math.sqrt(result);
        return result;
    }

    // 求平方和
    private double squares(Map<Character, int[]> paramMap) {
        double result1 = 0;
        double result2 = 0;
        Set<Character> keySet = paramMap.keySet();
        for (Character character : keySet) {
            int temp[] = paramMap.get(character);
            result1 += (temp[0] * temp[0]);
            result2 += (temp[1] * temp[1]);
        }
        return result1 * result2;
    }

    // 点乘法
    private double pointMulti(Map<Character, int[]> paramMap) {
        double result = 0;
        Set<Character> keySet = paramMap.keySet();
        for (Character character : keySet) {
            int temp[] = paramMap.get(character);
            result += (temp[0] * temp[1]);
        }
        return result;
    }

    /*public static void main(String[] args) {
        String s1 = "令狐荣辉";
        String s2 = "辉荣狐";
        Similarity similarity = new Similarity(s1, s2);
        System.out.println(similarity.sim());
    }*/


    /*
     * 计算两个字符串(英文字符)的相似度，简单的余弦计算，未添权重
     */
    public static double getSimilarDegree(String str1, String str2) {
        //创建向量空间模型，使用map实现，主键为词项，值为长度为2的数组，存放着对应词项在字符串中的出现次数
        Map<Character, int[]> vectorSpace = new HashMap<>();
        int[] itemCountArray;//为了避免频繁产生局部变量，所以将itemCountArray声明在此

        //以空格为分隔符，分解字符串
        char[] chars = str1.toCharArray();
        for (char aChar : chars) {
            if (vectorSpace.containsKey(aChar))
                ++(vectorSpace.get(aChar)[0]);
            else {
                itemCountArray = new int[2];
                itemCountArray[0] = 1;
                itemCountArray[1] = 0;
                vectorSpace.put(aChar, itemCountArray);
            }
        }
        chars = str2.toCharArray();
        for (char aChar : chars) {
            if (vectorSpace.containsKey(aChar))
                ++(vectorSpace.get(aChar)[1]);
            else {
                itemCountArray = new int[2];
                itemCountArray[0] = 0;
                itemCountArray[1] = 1;
                vectorSpace.put(aChar, itemCountArray);
            }
        }

        //计算相似度
        double vector1Modulo = 0.00;//向量1的模
        double vector2Modulo = 0.00;//向量2的模
        double vectorProduct = 0.00; //向量积
        Iterator iter = vectorSpace.entrySet().iterator();

        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            itemCountArray = (int[]) entry.getValue();

            vector1Modulo += itemCountArray[0] * itemCountArray[0];
            vector2Modulo += itemCountArray[1] * itemCountArray[1];

            vectorProduct += itemCountArray[0] * itemCountArray[1];
        }

        vector1Modulo = Math.sqrt(vector1Modulo);
        vector2Modulo = Math.sqrt(vector2Modulo);

        //返回相似度
        return (vectorProduct / (vector1Modulo * vector2Modulo));
    }

    /*
     *
     */
    public static void main(String args[]) {
        String str1 = "令狐";
        String str2 = "令狐";
        System.out.println(Similarity.getSimilarDegree(str1, str2));

        Similarity similarity = new Similarity(str1, str2);
        System.out.println(similarity.sim());
    }

}
