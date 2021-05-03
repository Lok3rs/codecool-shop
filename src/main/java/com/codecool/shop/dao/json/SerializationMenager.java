package com.codecool.shop.dao.json;

import com.codecool.shop.model.Order;
import com.google.gson.Gson;

import java.io.File;
import java.io.PrintWriter;
import java.time.LocalDate;

public class SerializationMenager {
    private Gson gson;

    public SerializationMenager() {
        this.gson = new Gson();
    }

    public void saveLog(Order order){
        LocalDate local = LocalDate.now();
        File file = new File("logs/" + "order_" + order.getId() + "_" + local + ".json");
        try(PrintWriter out = new PrintWriter(file.getAbsolutePath())){
            out.println(gson.toJson(order));
        }catch (Exception e){
            System.out.println("It is imposible to save log file of order: " + order.getId());
        }
    }



}
