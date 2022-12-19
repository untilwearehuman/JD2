package homework.one_december_08;

import java.io.*;
import java.util.*;

public class TableDataSorter {

    public static final String INPUT_DATA_FILE_PATH = "src/homework/one_december_08/in.txt";
    public static final String OUTPUT_DATA_FILE_PATH = "src/homework/one_december_08/out.txt";
    public static final String REGEX_TABULATION = "\t";

    public static void main(String[] args) {

        List<String> stringList = new ArrayList<>();

        readFile(stringList);

        List<String[]> stringList1 = new ArrayList<>();

        splitLinesIntoWords(stringList, stringList1);

//        for (int i = 0; i < stringList1.size(); i++) {
//            System.out.println(Arrays.toString(stringList1.get(i)));
//        }

//        System.out.println("SORTED");

        stringList1.sort(new AscendingComparator());

//        for (String[] strings : stringList1) {
//            System.out.println(Arrays.toString(strings));
//        }

        File file2 = new File(OUTPUT_DATA_FILE_PATH);
        createOutputFileIfNotExist(file2);

        writeSortedDataIntoOutputFile(stringList1, file2);

    }

    private static void writeSortedDataIntoOutputFile(List<String[]> stringList1, File file2) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file2))) {
            for (String[] line : stringList1) {
                for (String word : line) {
                    bufferedWriter.write(word.concat(REGEX_TABULATION));
                }
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void createOutputFileIfNotExist(File file2) {
        if (!file2.exists()) {
            try {
                file2.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void splitLinesIntoWords(List<String> stringList, List<String[]> stringList1) {
        for (String l : stringList) {
            stringList1.add(l.split(REGEX_TABULATION));
        }
    }

    private static void readFile(List<String> stringList) {
        File file = new File(INPUT_DATA_FILE_PATH);
        if(!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            while (bufferedReader.ready()) {
                stringList.add(bufferedReader.readLine().strip());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static class AscendingComparator implements Comparator<String[]> {

        @Override
        public int compare(String[] array1, String[] array2) {

            int result = 0;

            for (int i = 0; i < array1.length; i++) {
                boolean element1IsNumber = isNumber(array1[i]);
                boolean element2IsNumber = isNumber(array2[i]);

                if (element1IsNumber && element2IsNumber) {
                    result = Integer.compare(Integer.parseInt(array1[i]), Integer.parseInt(array2[i]));
                    if (result == 0) {
                        continue;
                    } else {
                        return result;
                    }
                }

                if (!element1IsNumber && !element2IsNumber) {
                    result = array1[i].compareTo(array2[i]);
                    if (result == 0) {
                        continue;
                    } else {
                        return result;
                    }
                }
                if (element1IsNumber) {
                    return -1;
                } else {
                    return 1;
                }
            }
            return 0;
        }

        private boolean isNumber(String str) {
            try {
                Integer.valueOf(str);
            } catch (NumberFormatException e) {
                return false;
            }
            return true;
        }
    }
}