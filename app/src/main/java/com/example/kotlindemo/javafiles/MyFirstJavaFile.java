package com.example.kotlindemo.javafiles;

import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Arrays;

public class MyFirstJavaFile {

    public static void main(String[] args) {

        new MyFirstJavaFile().reverseNum(143234);

        int arr[] = {7, 5, 16, 1, 2};

//        System.out.println("Array Before Bubble Sort");
//        for (int i = 0; i < arr.length; i++) {
//            System.out.print(arr[i] + " ");
//        }
//        System.out.println();
//        Arrays.sort(arr);
//
//        String value = ArrayAdditionI(arr);
//        System.out.println(value);


    }

    static void bubbleSort(int[] arr) {
        int n = arr.length;

        int sum = 0;
        int largestNo = 0;
        int temp = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < (n - i); j++) {
                if (arr[j - 1] > arr[j]) {
                    //swap elements
                    temp = arr[j - 1];
                    arr[j - 1] = arr[j];
                    arr[j] = temp;
                }
            }
        }

        largestNo = arr[arr.length - 1];
        System.out.println(largestNo);

        for (int i = 0; i < n - 1; i++) {
            sum += arr[i];

        }

        System.out.println(sum);

    }

    public static int getArea(int a, int b) {
        return a * b;
    }

    public void reverseNum(int num) {
        int rev = 0;
        while (num != 0) {
            rev = rev * 10 + num % 10;
            num = num / 10;
        }
        System.out.println(rev);
    }

    static String ArrayAdditionI(int[] A) {
        Arrays.sort(A);
        int n = A.length;
        int target = A[n - 1];
        int vale = Math.abs(A[0]);
        int MAX = A[0] >= 0 ? target + 1 : target + 1 + Math.abs(A[0]);
        int ZERO = A[0] >= 0 ? 0 : -A[0];
        boolean[][] D = new boolean[n][MAX];

        System.out.println(MAX + " " + vale);

        D[0][0] = true;
        for (int i = 0; i < n; i++) D[i][0] = true;
        D[0][ZERO] = true;
        for (int i = 0; i < n; i++) D[i][ZERO] = true;

        for (int i = 1; i <= n - 1; i++) {
            if (A[i - 1] >= 0) {
                for (int j = 1; j <= MAX - 1; j++) {
                    D[i][j] = D[i - 1][j];
                    if (j - A[i - 1] >= 0) {
                        D[i][j] |= D[i - 1][j - A[i - 1]];
                    }
                }
            } else {
                for (int j = MAX - 1 + A[i - 1]; j >= 1; j--) {
                    D[i][j] = D[i - 1][j];
                    if (j - A[i - 1] >= 0) {
                        D[i][j] |= D[i - 1][j - A[i - 1]];
                    }
                }
            }
        }
        return D[n - 1][MAX - 1] ? "true" : "false";
    }
}
