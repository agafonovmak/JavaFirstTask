import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Maxim on 22.10.2016.
 */
public class Matrix {
    ArrayList<ArrayList<Integer>> elements;

    protected Pair<Integer, Integer> getSizes(){
        //Число строк
        int n = elements.size();

        //Число столбцов
        int m;
        if(n!=0){
            m = elements.get(0).size();
        }
        else{
            m = 0;
        }

        return new Pair<>(n,m);
    }

    public int getColumnCount(){
        return getSizes().getValue();
    }

    public int getLineCount(){
        return getSizes().getKey();
    }


    public Matrix multiplication(Matrix another){
        //Некорректный размер матрицы
        try {

            if (!getSizes().getValue().equals(another.getSizes().getKey())) {
                throw new Exception();
            }

            Matrix result = new Matrix();


            for (int row = 0; row < getLineCount(); row++) {
                ArrayList<Integer> resLine = new ArrayList<>();
                for (int col = 0; col < another.getColumnCount(); col++) {
                    resLine.add(multiplyVector(getRow(row),
                            another.getColumn(col)));
                }
                result.elements.add(resLine);
            }

            return result;
        }
        catch (Exception ex){
            System.out.println("Матрицы имеют неподходящую размерность");
        }

        return null;
    }

    public void print(){
        for(ArrayList<Integer> line : elements){
            System.out.println(line.toString());
        }
    }

    private int multiplyVector(ArrayList<Integer> left, ArrayList<Integer> right) {
        int res=0;
        for(int i=0;i< left.size();i++){
            res+=left.get(i) * right.get(i);
        }
        return res;
    }

    private ArrayList<Integer> getColumn(int columnNumber){
        ArrayList<Integer> result = new ArrayList<>();
        for(int i=0;i<getLineCount();i++){
            result.add(elements.get(i).get(columnNumber));
        }
        return result;
    }

    private ArrayList<Integer> getRow(int rowNumber){
        ArrayList<Integer> result = new ArrayList<>(elements.get(rowNumber));
        return result;
    }

    public Matrix(){
        elements = new ArrayList<ArrayList<Integer>>();
    }

    public boolean equals(Matrix another){
        if(getLineCount()!=another.getLineCount()){
            return false;
        }

        for(int i=0;i<getLineCount();i++){
            if(!elements.get(i).equals(another.elements.get(i))){
                return false;
            }
        }

        return true;
    }
}
