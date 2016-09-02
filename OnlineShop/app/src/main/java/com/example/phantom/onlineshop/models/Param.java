package com.example.phantom.onlineshop.models;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.Text;

@Root(name = "param", strict = false)
@Attribute(required = false, name = "name")
public class Param {

    @Attribute(required = false, name = "name")
    @Namespace(reference = "Вес")
    public static String weight;
    @Text(required = false)
    private static String weightText;

    @Namespace(reference = "Диаметр")
    public String diameter;

    @Namespace(reference = "Каллорийность")
    public String calories;

    @Namespace(reference = "Белки")
    public String protein;

    @Namespace(reference = "Жиры")
    public String fat;

    @Namespace(reference = "Углеводы")
    public String carbohydrate;

    @Namespace(reference = "Кол-во")
    public String count;

    public static String getWeightText() {
        return weightText;
    }

    public static String getWeight() {
        return weight;
    }
}