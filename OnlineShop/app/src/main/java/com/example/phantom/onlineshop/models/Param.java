package com.example.phantom.onlineshop.models;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.Text;

@Root(name = "param", strict = false)
public class Param {
    @Attribute(required = false, name = "Вес")
    public static String weight;
    @Text(required = false, empty = "")
    private static String weight_text;
    @Attribute(required = false, name = "Диаметр")
    public String diameter;
    @Attribute(required = false, name = "Каллорийность")
    public String calories;
    @Attribute(required = false, name = "Белки")
    public String protein;
    @Attribute(required = false, name = "Жиры")
    public String fat;
    @Attribute(required = false, name = "Углеводы")
    public String carbohydrate;
    @Attribute(required = false, name = "Кол-во")
    public String count;

    public static String getWeight_text() {
        return weight_text;
    }

    public static String getWeight() {
        return weight;
    }

    public String getDiameter() {
        return diameter;
    }

    public String getCalories() {
        return calories;
    }

    public String getProtein() {
        return protein;
    }

    public String getFat() {
        return fat;
    }

    public String getCarbohydrate() {
        return carbohydrate;
    }

    public String getCount() {
        return count;
    }
}