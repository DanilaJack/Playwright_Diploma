package com.utils;

import io.qameta.allure.Step;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TxtFileProcessing {

    @Step("Чтение из текстового файла в двумерный массив, формирующийся из строк: в каждой строке regex - пробел")
    public static List<List<String>> fileReaderIntoDoubleArray(String path, String fileName) {
        List<List<String>> list = new ArrayList<>();
        String filePath = path + fileName;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null){
                String[] values = line.split(" ");
                list.add(Arrays.stream(values).toList());
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return list;
    }
}
