/**
 * Copyright 2018 Martin Kobelka (xkobel02@stud.fit.vutbr.cz)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Martin Kobelka (xkobel02@stud.fit.vutbr.cz)
 * @version 1.0
 *
 * @brief Library for proffiling
 */
public class Proffiling {

    /**
     * Pattern of splitter for two
     */
    private final String PATTERN_SPLITTER = "((( |\\t)*),(( |\\t)*))";

    /**
     * Pattern of one double value
     */
    private final String PATTERN_DOUBLE = "((\\+|-)?([0-9]+)(\\.[0-9]+)?((e|E)[0-9]+)?)";

    /**
     * Pattern of one line for regex
     */
    private String patternLine;
    /**
     * Actual input stream
     */
    private InputStream inputStream;

    /**
     * Items for proffiling
     */
    private List<Double> Items;

    /**
     * Create new proffiling from input stream
     *
     * @param inputStream
     */
    public Proffiling(InputStream inputStream) {

        Items = new LinkedList<>();
        this.inputStream = inputStream;

        // Create pattern for one line
        patternLine = "^(("+PATTERN_DOUBLE+"+)(("+PATTERN_SPLITTER+"("+PATTERN_DOUBLE+")+)+)?)?$";

    }

    /**
     * Create empty proffiling
     */
    public Proffiling() {

        // Call parent constructor with null input stream
        this(null);

    }

    /**
     * Run proffiling
     */
    public void run() {

        System.out.println("Zadávejte hodnoty (hodnoty lze oddělovat čárkou, novými řádky a bílími znaky): ");

        // Get items from user
        getItems();

        System.out.println("Výsledná směrodatná odchylka je: ");

        // Write result of deviation
        System.out.println(getStandartDeviation());


    }

    /**
     * @brief Get standart deviation from list of values
     *
     * @return Standart deviation
     */
    public double getStandartDeviation() {

        List<Double> items = Items;

        // TODO: Add my own math library

        if(items.size() == 0) {
            return 0.0;
        }

        double suma = 0;

        double average = getAverageValue(items);

        for(double item : items) {
            suma += Math.pow((item - average), 2);
        }

        return Math.sqrt(((double)1 / items.size()) * suma);

    }

    /**
     * Get average value from items
     *
     * @param items
     * @return
     */
    private double getAverageValue(List<Double> items) {

        if(items.size() == 0) {
            return 0.0;
        }

        double suma = 0.0;

        for (double item : items) {
            suma += item;
        }

        return suma / items.size();
    }

    /**
     * @brief Get items from command line
     */
    public void getItems() {

        // Buffered reader
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        // Actual line
        String line = null;

        try {
            // Loop while next line is not empty
            while((line = reader.readLine()) != null) {

                if(testLine(line)) {

                    // If line is correct, get values from it && push them into list
                    Items.addAll(getItemsFromLine(line));
                }
                else {
                    System.err.println("Řádek ve špatném formátu");
                }
            }
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    /**
     * Test if line is in coorect format
     *
     * @param line One line of numbers
     * @return
     */
    private boolean testLine(String line) {
        return line.matches(patternLine);
    }

    /**
     * Get some from line for proffiling
     *
     * @param line
     * @return
     */
    private List<Double> getItemsFromLine(String line) {

        List<Double> itemsFromLine = new LinkedList<>();

        // Split by comma
        String[] stringItemsFromLine = line.split("(( |\t)*),(( |\t)*)");

        // Make double from all parts
        for(String item : stringItemsFromLine) {

            if(!item.equals("")) {
                itemsFromLine.add(Double.valueOf(item));
            }

        }

        return itemsFromLine;
    }

    /**
     * Set input stream
     *
     * @param inputStream
     */
    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }


}
