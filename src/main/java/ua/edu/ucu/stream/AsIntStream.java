package ua.edu.ucu.stream;

import ua.edu.ucu.function.*;

import java.util.ArrayList;

public class AsIntStream implements IntStream {
    private ArrayList<Integer> arrayList;
    private AsIntStream(ArrayList<Integer> arrayList) {
        this.arrayList = arrayList;
    }

    private void ifIsEmpty() {
        if(arrayList.size() == 0) {
            throw new IllegalArgumentException("Stream is empty!");
        }
    }
    public static IntStream of(int... values) {
        ArrayList<Integer> arr = new ArrayList<>();
        for(int element: values) {
            arr.add(element);
        }
        return new AsIntStream(arr);
    }

    @Override
    public Double average() {
        ifIsEmpty();
        double result = 0.0D;
        for(int element:arrayList) {
            result += element;
        }
        result = result / arrayList.size();
        return result;

    }

    @Override
    public Integer max() {
        ifIsEmpty();
        int maxElement = -999999999;
        for(int element: arrayList) {
            if(element > maxElement) {
                maxElement = element;
            }
        }
        return maxElement;
    }

    @Override
    public Integer min() {
        ifIsEmpty();
        int minElement = 999999999;
        for(int element: arrayList) {
            if(element < minElement) {
                minElement = element;
            }
        }
        return minElement;
    }

    @Override
    public long count() {
        return arrayList.size();
    }

    @Override
    public Integer sum() {
        ifIsEmpty();
        int elemSum = 0;
        for(int element: arrayList) {
            elemSum += element;
        }
        return elemSum;
    }

    @Override
    public IntStream filter(IntPredicate predicate) {
        for(int i = 0; i < arrayList.size(); i++) {
            if(!predicate.test(arrayList.get(i))) {
                arrayList.remove(i);
            }
        }
        return this;
    }

    @Override
    public void forEach(IntConsumer action) {
        for (int i = 0; i < arrayList.size(); i++) {
            action.accept(arrayList.get(i));
        }
    }

    @Override
    public IntStream map(IntUnaryOperator mapper) {
        for(int j = 0; j < arrayList.size(); j++) {
            arrayList.set(j, mapper.apply(arrayList.get(j)));
        }
        return this;
    }

    @Override
    public IntStream flatMap(IntToIntStreamFunction func) {
        ArrayList newArray = new ArrayList();
        for(int i = 0; i < arrayList.size(); i++) {
            newArray.add(func.applyAsIntStream(arrayList.get(i)));
        }
        return new AsIntStream(newArray);
    }

    @Override
    public int reduce(int identity, IntBinaryOperator op) {
        int result = identity;
        for(int i = 0; i < arrayList.size(); i++) {
            result = op.apply(result, arrayList.get(i));
        }
        return result;

    }

    @Override
    public int[] toArray() {
        int[] result = new int[arrayList.size()];
        for(int j = 0; j < arrayList.size(); j++) {
            result[j] = arrayList.get(j);
        }
        return result;
    }

}
