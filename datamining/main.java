package com.company;

import javafx.util.Pair;
import weka.core.*;
import weka.core.converters.ArffLoader;

import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("/home/malek/Labs/datamining/data/labor.arff"));
        ArffLoader.ArffReader arff = new ArffLoader.ArffReader(reader);
        DataSet dataSet = new DataSet(arff.getData());
        Instances data = dataSet.getData();

        for(int i=0; i<data.numAttributes();i++) {
            dataSet.replacelMissingValues(i);
        }

        for (int i=0; i<data.numInstances(); i++) {
            System.out.println(data.instance(i));
        }

        dataSet.normalize(0);

        for(int i=0;i<data.numInstances();i++) {
            System.out.println(data.instance(i).value(0));
        }



    }

}


