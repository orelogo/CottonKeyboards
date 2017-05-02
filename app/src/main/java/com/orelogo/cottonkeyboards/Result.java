package com.orelogo.cottonkeyboards;

/**
 * Created by patso on 5/2/2017.
 */

public class Result {
    private double totalRevenue;
    private int aeroCottonKeyboardCount;

    public Result(double totalRevenue, int aeroCottonKeyboardCount) {
        this.totalRevenue = totalRevenue;
        this.aeroCottonKeyboardCount = aeroCottonKeyboardCount;
    }

    public double getTotalRevenue() {
        return totalRevenue;
    }

    public int getACKeyboardCount() {
        return aeroCottonKeyboardCount;
    }
}
