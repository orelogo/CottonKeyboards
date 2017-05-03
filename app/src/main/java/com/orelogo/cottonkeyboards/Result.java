package com.orelogo.cottonkeyboards;

/**
 * Data structure for storing total revenue and amount of aerodynamic cotton keyboards sold for a
 * single order or multiple orders.
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
