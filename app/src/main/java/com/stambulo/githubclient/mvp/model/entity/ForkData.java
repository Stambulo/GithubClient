package com.stambulo.githubclient.mvp.model.entity;

public class ForkData {
    private static String forkName;
    private static int forksSum;


    public static String getForkName() {
        return forkName;
    }

    public static void setForkName(String forkName) {
        ForkData.forkName = forkName;
    }

    public static int getForksSum() {
        return forksSum;
    }

    public static void setForksSum(int forksSum) {
        ForkData.forksSum = forksSum;
    }
}
