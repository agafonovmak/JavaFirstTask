import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;

/**
 * Created by Maxim on 22.10.2016.
 */
public class Main {

    public static void main(String[] args) {

        System.out.print("Тест поиска повторов : ");
        if(testSearchRepetition()){
            System.out.println("пройден");
        }
        else{
            System.out.println("не пройден");
        }

        System.out.print("Тест числа вхождений : ");
        if(testFrequency()){
            System.out.println("пройден");
        }
        else{
            System.out.println("не пройден");
        }

        System.out.print("Тест пересечения массивов : ");
        if(testIntersection()){
            System.out.println("пройден");
        }
        else{
            System.out.println("не пройден");
        }

        System.out.print("Тест перемножения матриц : ");
        if(testMultiplication()){
            System.out.println("пройден");
        }
        else{
            System.out.println("не пройден");
        }

    }

    private static int getNumberOfOccurrences(String str, char symbol){
        int res = 0;
        for(int i = 0;i<str.length();i++){
            if(str.charAt(i) == symbol)res++;
        }
        return res;
    }

    private static boolean hasRepeats(ArrayList<String> a){
        for(String element:a){
            //Если элемент встречается в массиве более одного раза
            if(Collections.frequency(a,element)>1){
                return true;
            }
        }
        return false;
    }

    private static ArrayList<Double> intersection(ArrayList<Double> firstArray, ArrayList<Double> secondArray){
        ArrayList<Double> res = new ArrayList<>();

        //Если элемент, содержащийся в а, содержится и в b, то добавляем его в результат
        for(Double element:firstArray){
            if(secondArray.contains(element))res.add(element);
        }
        return res;
    }

    //Функция проверки умножения двух матриц
    //Читает матрицы с их размерностями из MatrixTest
    private static boolean testMultiplication(){
        ArrayList<Integer> integers = new ArrayList<>();
        try{
            Path filePath = Paths.get("MatrixTest");
            Scanner scanner = new Scanner(filePath);
            while (scanner.hasNext()) {
                if (scanner.hasNextInt()) {
                    integers.add(scanner.nextInt());
                } else {
                    scanner.next();
                }
            }

            int height1 = integers.get(0);
            int width1 = integers.get(1);
            int height2 = integers.get(2);
            int width2 = integers.get(3);

            int resHeight = integers.get(4);
            int resWidth = integers.get(5);

            Matrix a = new Matrix();
            Matrix b = new Matrix();
            Matrix exactRes = new Matrix();

            int index = 6;

            for(int i=0;i<height1;i++){
                ArrayList<Integer> tempLine = new ArrayList<>();
                for(int j=0;j<width1;j++){
                    tempLine.add(integers.get(index));
                    index++;
                }
                a.elements.add(tempLine);
            }

            for(int i=0;i<height2;i++){
                ArrayList<Integer> tempLine = new ArrayList<>();
                for(int j=0;j<width2;j++){
                    tempLine.add(integers.get(index));
                    index++;
                }
                b.elements.add(tempLine);
            }

            for(int i=0;i<resHeight;i++){
                ArrayList<Integer> tempLine = new ArrayList<>();
                for(int j=0;j<resWidth;j++){
                    tempLine.add(integers.get(index));
                    index++;
                }
                exactRes.elements.add(tempLine);
            }

            Matrix c = a.multiplication(b);


            if(c!=null){
                return c.equals(exactRes);
            }
            else{
                return false;
            }

        }
        catch (IOException ex){
            ex.printStackTrace();
            return false;
        }
    }

    private static boolean testSearchRepetition(){
        try{

            ArrayList<String> stringList1 = new ArrayList<>();
            ArrayList<String> stringList2 = new ArrayList<>();

            Path filePath1 = Paths.get("RepetitionTest1");
            Scanner scanner1 = new Scanner(filePath1);
            while (scanner1.hasNext()) {
                if (scanner1.hasNextLine()) {
                    stringList1.add(scanner1.nextLine());
                } else {
                    scanner1.next();
                }
            }

            Path filePath2 = Paths.get("RepetitionTest2");
            Scanner scanner2 = new Scanner(filePath2);
            while (scanner2.hasNext()) {
                if (scanner2.hasNextLine()) {
                    stringList2.add(scanner2.nextLine());
                } else {
                    scanner2.next();
                }
            }

            boolean first = hasRepeats(stringList1);
            boolean second = hasRepeats(stringList2);

            if(!first && second){
                return true;
            }
            else{
                return false;
            }

        }
        catch (IOException ex){
            ex.printStackTrace();
            return false;
        }
    }

    //Функция проверки пересечения двух массиов
    //Читает массивы с их размерами из IntersectionTest
    private static boolean testIntersection(){
        ArrayList<Double> doubles = new ArrayList<>();

        try {
            Path filePath = Paths.get("IntersectionTest");
            Scanner scanner = new Scanner(filePath);
            while (scanner.hasNext()) {
                if (scanner.hasNextDouble()) {
                    doubles.add(scanner.nextDouble());
                } else {
                    scanner.next();
                }
            }

            double size1 = doubles.get(0);
            double size2 = doubles.get(1);
            double intersectionSize = doubles.get(2);
            int index = 3;

            ArrayList<Double> firstArray = new ArrayList<>();
            ArrayList<Double> secondArray = new ArrayList<>();
            ArrayList<Double> result = new ArrayList<>();

            for(int i = 0;i<size1;i++){
                firstArray.add(doubles.get(index));
                index++;
            }
            for(int i = 0;i<size2;i++){
                secondArray.add(doubles.get(index));
                index++;
            }

            for(int i = 0;i<intersectionSize;i++){
                result.add(doubles.get(index));
                index++;
            }

            return intersection(firstArray, secondArray).equals(result);

        }
        catch (IOException ex){
            return false;
        }
    }

    private static boolean testFrequency(){
        try {
            Path filePath = Paths.get("FrequencyTest");
            String str = "";
            Scanner scanner = new Scanner(filePath);
            while (scanner.hasNext()) {
                if (scanner.hasNextLine()) {
                    str = scanner.nextLine();
                } else {
                    scanner.next();
                }
            }

            return getNumberOfOccurrences(str, 'a') == 17;

        }
        catch (IOException ex){
            return false;
        }
    }

}
