package frc.robot.util;

import java.util.*;
import java.util.stream.Collectors;

public class LookupTable implements Map<Double, Double>
{
    /**
     * The underlying mechanism through which we store values
     */
    private Map<Double, Double> table;

    /**
     * @param firstKey   The first key to put into the hash table.
     * @param firstValue The first value to put into the hash table.
     */
    public LookupTable(Double firstKey, Double firstValue) {
        table = new HashMap<>();
        put(firstKey, firstValue);
    }


    /**
     * Make a new interpolating hash table
     *
     * @param initTable The table to start with
     */
    public LookupTable(Map<Double, Double> initTable) {
        if (initTable.keySet().isEmpty()) {
            throw new IllegalArgumentException("Initial table for the InterpolatingHashTable cannot be empty!");
        } else {
            putAll(initTable);
        }
    }

    /**
     * @return A sorted table
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        table.entrySet()
                .stream()
                .sorted(Comparator.comparingDouble(Entry::getKey))
                .forEach(doubleDoubleEntry -> stringBuilder.append("\n").append(doubleDoubleEntry.getKey()).append(": ").append(doubleDoubleEntry.getValue()));
        return stringBuilder.toString();
    }

    /**
     * @return The inverse of this lookup table. May not work properly if the table is not
     * one-to-one
     */
    public LookupTable inverse()
    {
        Map<Double, Double> mapInverted =
                table.entrySet()
                        .stream()
                        .collect(Collectors.toMap(Map.Entry::getValue, Map.Entry::getKey));
        return new LookupTable(mapInverted);
    }

    /**
     * @return How many defined values there are (i.e how many values we aren't guessing with linear regression)
     */
    @Override
    public int size() {
        return table.size();
    }

    /**
     * @return If there are any defined values for this Interpolation Map
     */
    @Override
    public boolean isEmpty() {
        return table.isEmpty();
    }

    /**a
     * @param key Some key
     * @return If it is a key for which there is a defined value in our map
     */
    @Override
    public boolean containsKey(Object key) {
        return table.containsKey(key);
    }

    /**
     * @param value Some value
     * @return If it is a value where it is fully defined for some key in the map
     */
    @Override
    public boolean containsValue(Object value) {
        return table.containsValue(value);
    }

    /**
     * @param key The key to evaluateY a value for
     * @return The value associated with the key. May be obtained through interpolation
     * @throws IllegalArgumentException If the key does not extend Double
     */
    @Override
    public Double get(Object key) throws IllegalArgumentException {
        if (key instanceof Number) {
            Number number = (Number) key;
            return this.get(number.doubleValue());
        }
        throw new IllegalArgumentException("Key was not a Double");
    }

    /**
     * Add a pair that you know is correct
     *
     * @param key   The "x" value that you put in
     * @param value The "f(x)" value that you got out
     */
    @Override
    public Double put(Double key, Double value) {
        if (table == null) {
            table = new HashMap<>();
        }
        return table.put(key, value);
    }

    /**
     * Given a key, make it no longer defined, hence impacting the "graph" of the interpolation map
     *
     * @param key A key
     * @return The value formerly associated with that key.
     */
    @Override
    public Double remove(Object key) {
        return table.remove(key);
    }

    /**
     * Given a map, make all the values in the map defined in our InterpolationMap
     *
     * @param m that map
     */
    @Override
    public void putAll(Map<? extends Double, ? extends Double> m) {
        if (table == null) {
            table = new HashMap<>();
        }
        table.putAll(m);
    }

    /**
     * Reset the table such that there are 0 defined values
     */
    @Override
    public void clear() {
        table.clear();
    }

    /**
     * @return All the keys for which we have a defined value
     */
    @Override
    public Set<Double> keySet() {
        return table.keySet();
    }

    /**
     * @return All the values that are defined in the table.
     */
    @Override
    public Collection<Double> values() {
        return table.values();
    }

    /**
     * @return Every defined key-value pair
     */
    @Override
    public Set<Entry<Double, Double>> entrySet() {
        return table.entrySet();
    }

    /**
     * Use linear regression to estimate what your "f(x)" will give when evaluated at the Double `x`
     *
     * @param x The Double to evaluate "f(x)" at
     * @return The estimated value of "f(x)"
     */
    public Double get(Double x) {
        Set<Double> keyset = table.keySet();

        // 1 defined value -> return only that value
        if (keyset.size() == 1) {
            return table.get(keyset.toArray()[0]);
        }

        Double upperBound = null;
        Double lowerBound = null;


        ArrayList<Double> keys = new ArrayList<>(keyset);
        keys.sort(Comparator.comparingDouble(a -> (double) a));

        int i = 0;

        for (Double k : keys) {
            if (k.floatValue() > x.floatValue()) {
                upperBound = k;
                if (i > 0) {
                    lowerBound = keys.get(i - 1); // the key below the current one must be smaller
                }
                break;
            }
            i++;
        }

        if (upperBound == null) { // i.e all the keys are smaller than the value we want to grab
            return table.get(keys.get(keys.size() - 1)); // evaluateY the f(x) for the biggest x. we can't do real interpolation
        }
        else if (lowerBound == null) { // i.e all the keys are bigger than the value we want to grab
            return table.get(keys.get(0)); // evaluateY the f(x) for the smallest x. we can't do real interpolation.
        }
        else { // we can do real interpolation
            double dx = upperBound - lowerBound;
            double dy = table.get(upperBound) - table.get(lowerBound);
            double slope = dy / dx;

            // the following equation comes from the point slope form of a line
            // reminder: it's y - y1 = m (x - x1)
            // here, m is slope
            // upperBound is x1
            // table.get(upperBound) is y1
            return ((slope * x) - (slope * upperBound)) + table.get(upperBound);
        }
    }
}
