package sample;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.BoxAndWhiskerToolTipGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BoxAndWhiskerRenderer;
import org.jfree.data.statistics.BoxAndWhiskerCategoryDataset;
import org.jfree.data.statistics.DefaultBoxAndWhiskerCategoryDataset;
import sun.plugin.javascript.navig.Anchor;
import weka.core.Instance;
import weka.core.converters.ArffLoader;

import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Controller {
    DataSet dataSet = null;
    @FXML
    public AnchorPane details;
    @FXML
    public Button ouvrir;
    @FXML
    public ChoiceBox fileList;
    @FXML
    public ChoiceBox attributeList;
    @FXML
    public ScrollPane stats;
    @FXML
    public HBox statsBox;
    @FXML
    public VBox dataVisulalisation;
    @FXML
    public AnchorPane tablespace;
    @FXML
    public AnchorPane graphspace;
    @FXML
    public HBox datatable;
    @FXML
    public AnchorPane histogram;
    @FXML
    public AnchorPane boxplot;
    @FXML
    public MenuButton actions;
    @FXML
    public MenuItem replaceAllmissing;
    /******************* text fields *************************/
    @FXML
    public Text relationName;
    @FXML
    public Text relationInstances;
    @FXML
    public Text relationAttributes;
    @FXML
    public Text selectedAttrName;
    @FXML
    public Text selectedAttrType;
    @FXML
    public Text selectedAttrMissing;
    @FXML
    public Text selectedAttrDistinct;
    @FXML
    public Text selectedAttrUnique;
    @FXML
    public GridPane gpane = new GridPane();
    @FXML
    public TableView tableview = new TableView();

    public void OpenFolder() {

        DirectoryChooser chooser = new DirectoryChooser();
        chooser.setTitle("JavaFX Projects");
        File defaultDirectory = new File("/home/malek/");
        chooser.setInitialDirectory(defaultDirectory);
        File selectedDirectory = chooser.showDialog(new Stage());
        File fList[] = selectedDirectory.listFiles();
        ObservableList<String> fileNames  = FXCollections.observableArrayList();
        for (File file : fList) {
            if(file.getName().endsWith(".arff")) {
                System.out.println(file.getName());
                fileNames.add(file.getAbsolutePath());
            }
        }

        fileList.setItems(fileNames);
    }

    public void CreateDataSet(ActionEvent actionEvent) throws IOException {

        dataSet = readFile(fileList.getValue().toString());
        ObservableList<String> attributeNames  = FXCollections.observableArrayList();
        System.out.println(dataSet.getData());
        this.relationName.setText(dataSet.getData().relationName());
        this.relationAttributes.setText(String.valueOf(dataSet.getData().numAttributes()));
        this.relationInstances.setText(String.valueOf(dataSet.getData().numInstances()));
        for(int i=0;i<this.dataSet.getData().numAttributes();i++) {
            attributeNames.add(this.dataSet.getData().attribute(i).name().toString());
        }
        this.attributeList.setItems(attributeNames);
        this.fillGridView();
    }

    public void handleAttribute(ActionEvent actionEvent) {
        int attrindex = dataSet.getData().attribute(attributeList.getValue().toString()).index();
        this.selectedAttrName.setText(attributeList.getValue().toString());

        /* details */
        if(this.dataSet.getData().attribute(attrindex).isNumeric()) {
            this.selectedAttrType.setText("Numeric");
        }else {
            this.selectedAttrType.setText("Nominal");
        }
        this.selectedAttrMissing.setText(Integer.toString(dataSet.calculateMissingValues(attrindex)));
        this.selectedAttrDistinct.setText(Integer.toString(dataSet.computeDistictValues(attrindex)));

        this.gpane.getChildren().clear();
        if(this.dataSet.getData().attribute(attrindex).isNumeric()) { // to be refactored !!
            gpane.add(new Label("Statistic"),1,1);
            gpane.add(new Label("Value"),2,1);
            gpane.add(new Text("Min"),1,2);
            gpane.add(new Text("Q1"),1,3);
            gpane.add(new Text("Median"),1,4);
            gpane.add(new Text("Q3"),1,5);
            gpane.add(new Text("Max"),1,6);
            gpane.add(new Text("Mode"),1,7);
            gpane.add(new Text("Mean"),1,8);

            gpane.add(new Text(Double.toString(dataSet.min(attrindex))),2,2);
            gpane.add(new Text(Double.toString(dataSet.Q1(attrindex))),2,3);
            gpane.add(new Text(Double.toString(dataSet.computeMedian(attrindex))),2,4);
            gpane.add(new Text(Double.toString(dataSet.Q3(attrindex))),2,5);
            gpane.add(new Text(Double.toString(dataSet.max(attrindex))),2,6);
            gpane.add(new Text(Double.toString(dataSet.computeMode(attrindex))),2,7);
            gpane.add(new Text(Double.toString(dataSet.computeMean(attrindex))),2,8);

        }else{
            HashMap<String,Integer> counts = this.dataSet.valuesCount(attrindex);
            gpane.add(new Label("Label"),1,1);
            gpane.add(new Label("Count"),2,1);
            int i=2;
            for(String key : counts.keySet()){
                gpane.add(new Text(key),1,i);
                gpane.add(new Text(Integer.toString(counts.get(key))),2,i);
                i++;
            }
        }
        // styling
        gpane.setHgap(15);
        gpane.setVgap(10);
        gpane.setAlignment(Pos.CENTER);
        stats.setContent(gpane);

        // ploting
        this.histogram(attrindex);
    }

    public static DataSet readFile(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        ArffLoader.ArffReader arff = new ArffLoader.ArffReader(reader);
        return new DataSet(arff.getData());
    }

    public void fillGridView() {
        int i=0;
        this.datatable.getChildren().clear();
        this.tableview.getColumns().clear();

        ObservableList<Instance> tableContent = FXCollections.observableArrayList(dataSet.getData());
        ArrayList<TableColumn<Instance, String>> atrributes = new ArrayList<>();
        ArrayList <Instance> instances = new ArrayList<>();

        for (i =0;i<dataSet.getData().size();i++)
        {
            instances.add(dataSet.getData().get(i));
        }

        for (i = 0; i < dataSet.getData().numAttributes(); i++) {
            TableColumn<Instance, String> column
                    = new TableColumn<Instance,String>(dataSet.getData().attribute(i).name());
            final int attIndex = i ;
            column.setCellValueFactory(cellData ->
                    new SimpleStringProperty(cellData.getValue().toString(attIndex)));
            atrributes.add(column);
        }
        tableview.getColumns().clear();
        tableview.getColumns().addAll(atrributes);
        tableview.setItems(tableContent);
        tableview.setVisible(true);

        this.tableview.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        this.datatable.getChildren().add(this.tableview);
    }

    public void histogram(int index) {
        histogram.getChildren().clear();
        if(dataSet.getData().attribute(index).isNumeric()) {

        }else{
            HashMap<String,Integer> values = this.dataSet.valuesCount(index);
            final CategoryAxis xAxis = new CategoryAxis();
            final NumberAxis yAxis = new NumberAxis();
            final BarChart<String,Number> bc =
                    new BarChart<String,Number>(xAxis,yAxis);
            bc.setTitle(this.dataSet.getData().attribute(index).name());
            xAxis.setLabel("Label");
            yAxis.setLabel("Value");

            XYChart.Series series1 = new XYChart.Series();

            values.forEach((k,v)->{
                series1.getData().add(new XYChart.Data(k,v));
            });

            bc.getData().add(series1);
            this.histogram.getChildren().add(bc);

        }
    }

    public void fixMissing(ActionEvent actionEvent) {
        for(int i=0;i<this.dataSet.getData().numAttributes();i++) {
            this.dataSet.replacelMissingValues(i);
            this.fillGridView();
        }
    }

    public void boxplot(ActionEvent actionEvent)  {
        this.boxplot.getChildren().clear();
        final BoxAndWhiskerCategoryDataset dataset = createSampleDataset();
        final org.jfree.chart.axis.CategoryAxis xAxis = new org.jfree.chart.axis.CategoryAxis("Label");
        final org.jfree.chart.axis.NumberAxis yAxis = new org.jfree.chart.axis.NumberAxis("Value");
        yAxis.setAutoRangeIncludesZero(false);
        final BoxAndWhiskerRenderer renderer = new BoxAndWhiskerRenderer();
        renderer.setFillBox(true);
        renderer.setMeanVisible(false);

        //renderer.setToolTipGenerator(new BoxAndWhiskerToolTipGenerator());
        final CategoryPlot plot = new CategoryPlot(dataset, xAxis, yAxis, renderer);

        final JFreeChart chart = new JFreeChart(
                "Box-and-Whisker",
                new Font("ubuntu-regular", Font.CENTER_BASELINE, 14),
                plot,
                true
        );
        final ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 400));
        javafx.embed.swing.SwingNode d = new javafx.embed.swing.SwingNode();
        d.setContent(chartPanel);
        this.boxplot.getChildren().add(d);
    }

    private BoxAndWhiskerCategoryDataset createSampleDataset() {
        int categoryCount = 0;
        for(int i=0;i<this.dataSet.getData().numAttributes();i++) {
            if(this.dataSet.getData().attribute(i).isNumeric()){
                categoryCount++;
            }
        }
        final int entityCount = this.dataSet.getData().numInstances();
        final DefaultBoxAndWhiskerCategoryDataset dataset = new DefaultBoxAndWhiskerCategoryDataset();
        for (int j = 0; j < categoryCount; j++) {
            if(this.dataSet.getData().attribute(j).isNumeric()) {
                final List list = new ArrayList<>();
                for (int k = 0; k < entityCount; k++) {
                    list.add(this.dataSet.getData().instance(k).value(j));
                }
                dataset.add(list, "DataSet : "+this.dataSet.getData().relationName(), this.dataSet.getData().attribute(j).name());
            }
        }

        return dataset;
    }

    public void normalizeAll(ActionEvent actionEvent) {
        this.dataSet.normalizeAll();
        this.fillGridView();
    }
}

/*
* always working on numeric data
* five number summary ==>
*
*
* */

// symetric => mode == median == mean
// positively skewed => mode < median
// negatively skewed => mode > median

/**
 * bar plot for nominal attributes
 * historgrams for numerical attributes
 */
