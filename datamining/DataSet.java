package sample;

import weka.core.Instances;

import java.lang.reflect.Array;
import java.util.*;

public class DataSet {
    private Instances data = null;
    private HashMap<Integer,Double> mode = null;
    private HashMap<Integer,Double> median = null;
    private HashMap<Integer,Double> mean = null;
    private HashMap<Integer,Double> Q1 = null;
    private HashMap<Integer,Double> Q3 = null;

    public DataSet(Instances data) {
        this.data = data;
    }

    public Instances getData() {
        return data;
    }

    public void setData(Instances data) {
        this.data = data;
    }

    public double getMode(int index) {
        return mode.get(index);
    }

    public void setMode(double mode, int index) {
        this.mode.put(index,mode);
    }

    public double getMedian(int index) {
        return median.get(index);
    }

    public void setMedian(double median,int index) {
        this.median.put(index,median);
    }

    public void setMean(double mean,int index) {
        this.mean.put(index,mean);
    }

    public double computeMedian(int index) {
        double median;
        List<Double> values = new ArrayList<Double>();
        for(int i=0; i<this.data.numInstances(); i++) {
            if(!Double.isNaN(this.data.instance(i).value(index))) {
                values.add(this.data.instance(i).value(index));
            }
        }
        Collections.sort(values);
        median = values.size()%2 == 0 ? (values.get((values.size()/2)-1) + values.get(values.size()/2))/2 :values.get((values.size()/2)-1);
        return median;
    }

    public float computeMean(int index) {
        double count=0;
        float mean = 0;
        if(this.data.attribute(index).isNumeric()) {
            for (int i=0; i<this.data.numInstances(); i++) {
                if(!this.data.instance(i).isMissing(index)) {
                    count += 1;
                    mean += this.data.instance(i).value(index);
                }
            }
            mean /= count;
            return mean;
        }else{
            return 0;
        }
    }

    public void replacelMissingValues(int index) {
        if(this.calculateMissingValues(index)>0) {
            if (this.data.attribute(index).isNumeric()) {
                double mean=0;
                mean = Math.floor(this.computeMean(index));
                for (int i=0 ; i<data.numInstances(); i++) {
                    if(this.data.instance(i).isMissing(index)) {
                        this.data.instance(i).setValue(index,mean);
                    }
                }
            }else{
                double mode = this.computeMode(index);
                for(int i=0;i<data.numInstances();i++) {
                    if(Double.isNaN(data.instance(i).value(index))) {
                        data.instance(i).setValue(index,mode);
                    }
                }
            }
        }
    }

    public int calculateMissingValues(int index) {
        int missing = 0;
        for (int i=0; i<data.numInstances(); i++) {
            if(data.instance(i).isMissing(index)) {
                missing++;
            }
        }
        return missing;
    }

    public int computeDistictValues(int index) {
        return this.data.numDistinctValues(index);
    }

    public void normalize(int index) {
        double min=0;
        double max=0;
        double value=0;
        if(data.attribute(index).isNumeric()) {
            min = this.min(index);
            max = this.max(index);
            for(int i=0;i<this.data.numInstances(); i++) {
                value = (data.instance(i).value(index)-min) / (max-min);
                this.data.instance(i).setValue(index,value);
            }
        }
    }

    public double min(int index) {
        double min=0;
        if(data.attribute(index).isNumeric()) {
            min = this.data.instance(0).value(index);
            for(int i=1; i<this.data.numInstances(); i++) {
                if (min > this.data.instance(i).value(index)) {
                    min = this.data.instance(i).value(index);
                }
            }
        }
        return min;
    }

    public double max(int index) {
        double max=0;
        if(data.attribute(index).isNumeric())  {
            max = this.data.instance(0).value(index);
            for( int i = 0; i < this.data.numInstances(); i++) {
                if(max < this.data.instance(i).value(index)) {
                    max = this.data.instance(i).value(index);
                }
            }
        }
        return max;
    }

    public double Q1(int index) {
        double Q1=0;

        if(this.data.attribute(index).isNumeric()) {
            List<Double> values = new ArrayList<Double>();
            for(int i=0; i<this.data.numInstances(); i++) {
                if(!Double.isNaN(this.data.instance(i).value(index))) {
                    values.add(this.data.instance(i).value(index));
                }
            }
            Collections.sort(values);
            Q1 = values.get(Math.round(values.size()/4));
        }
        return Q1;
    }

    public double Q3(int index) {
        double Q3=0;
        if(this.data.attribute(index).isNumeric()) {
            List<Double> values = new ArrayList<Double>();
            for(int i=0; i<this.data.numInstances(); i++) {
                if(!Double.isNaN(this.data.instance(i).value(index))) {
                    values.add(this.data.instance(i).value(index));
                }
            }
            Collections.sort(values);
            Q3 = values.get(Math.round((values.size()*3)/4));
        }
        return Q3;
    }

    public double computeMode(int index) {
        double key = 0;
        double mode = 0;
        HashMap<Double, Integer> freqs = new HashMap<Double, Integer>();
        // get set of values
        for (int i = 0; i < this.data.numInstances(); i++) {
            key = this.data.instance(i).value(index);
            if(!Double.isNaN(key)) {
                if (!freqs.containsKey(key)) {
                    freqs.put(key, 0);
                } else {
                    freqs.replace(key, freqs.get(key), freqs.get(key) + 1);
                }
            }
        }
        key = 0;
        for (Double k : freqs.keySet()) {
            if (mode < freqs.get(k)) {
                mode = freqs.get(k);
                key = k;
            }
        }
        return key;
    }

    public HashMap<String,Integer> valuesCount(int index) {
        HashMap<String,Integer> v = new HashMap<>();
        int[] counts = this.data.attributeStats(index).nominalCounts;
        Enumeration labels = this.data.attribute(index).enumerateValues();
        int i =0;
        while(labels.hasMoreElements()) {
            v.put(labels.nextElement().toString(),counts[i]);
            i++;
        }
        return v;
    }

    public Double interQuartileRange(int index) {
        return this.Q1(index)-this.Q3(index);
    }

    public List numricValues(int index) {
        List a = new ArrayList();
        if(this.getData().attribute(index).isNumeric()) {
            for(int i=0;i<this.getData().numInstances();i++) {
                a.add(this.getData().instance(index).value(index));
            }
            return a;
        }else{
            return null;
        }
    }

    public Double range(int index) {
        return this.max(index) - this.min(index);
    }
    public Double midRange(int index) { return (this.max(index)+this.min(index))/2;}

    public HashMap<String,Integer> discretize(int index) {
        HashMap<String,Integer> h = new HashMap<>();

        return h;
    }

    public void normalizeAll() {
        for(int i=0;i<this.data.numAttributes();i++) {
            if(this.data.attribute(i).isNumeric()){
                this.normalize(i);
            }
        }
    }

    public void discriticize(int index) {

    }

    public int checkSkewness(int index) {
        double mode = this.computeMode(index);
        double median = this.computeMedian(index);
        if(mode == median) {
            return 0; // symetric
        }else if(mode < median) {
            return 1; // negatively skewed
        }else{
            return 2; // positively skewed
        }
    }

    /*public ArrayList<Double> computeBins(int index) {
        double min = this.min(index);
        double max = this.max(index);
        double k = 5 * Math.log(this.computeDistictValues(index));
        double range = this.data.numInstances() / k;

        System.out.println(k);

        double bin = min;

        ArrayList<Double> bins = new ArrayList<>();

        while (bin<max) {
            bins.add(bin);
            bin += range;
        }

        return bins;
    }

    public HashMap<String,Double> valuesPerBin(int index) {
        ArrayList<Double> bins = this.computeBins(index);
        HashMap<String,Double> values = new HashMap<>();

        for(int i=0;i<bins.size();i++) {

        }

        return values;
    }
    */

    public HashMap<String,Integer> valuesPerBin(int index){
        HashMap<String,Integer> bins = new HashMap<>();
        double min = this.min(index);
        double Q1 = this.Q1(index);
        double median = this.computeMedian(index);
        double Q3 = this.Q3(index);
        double max = this.max(index);
        double value = 0;
        int b1=0,b2=0,b3=0,b4 = 0;

        for(int i=0;i<this.getData().numInstances();i++) {
            value = this.getData().instance(i).value(index);
            if(value < Q1) {
                b1+= 1;
            }else if(value > Q1 && value <median) {
                b2+=1;
            }else if(value >median && value<Q3) {
                b2+=1;
            }else{
                b4+=1;
            }
        }

        bins.put("["+Double.toString(min)+"-"+Double.toString(Q1)+"]",b1);
        bins.put("["+Double.toString(Q1)+"-"+Double.toString(median)+"]",b2);
        bins.put("["+Double.toString(median)+"-"+Double.toString(Q3)+"]",b3);
        bins.put("["+Double.toString(Q3)+"-"+Double.toString(max)+"]",b4);

        return bins;

    }

}


