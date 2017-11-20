package com.ec.pizzaserve.assignment1.service;
import java.util.List;

public class MathsCalculate {

    public static float totalSumOfList(List<Float> m)
    {
        float sum = 0;
        for(int i = 0; i < m.size(); i++)
        {
            sum += m.get(i);
        }
        return sum;
    }
}
