package com.orelogo.cottonkeyboards;

/**
 * Created by patso on 5/2/2017.
 */

class Result {
    private double totalRevenue;
    private int aeroCottonKeyboardCount;

    Result(double totalRevenue, int aeroCottonKeyboardCount) {
        this.totalRevenue = totalRevenue;
        this.aeroCottonKeyboardCount = aeroCottonKeyboardCount;
    }

    double getTotalRevenue() {
        return totalRevenue;
    }

    int getACKeyboardCount() {
        return aeroCottonKeyboardCount;
    }
}
