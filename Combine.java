import java.util.ArrayList;
import java.util.List;

public class Combine {

    public static final int Z = 0;

    public static void main(String[] args) {

        List<Integer> list1 = new ArrayList<Integer>();
        for (int i = Z; i < 100; i++) {
            list1.add(i);
        }
//        list1.add(3);
//        list1.add(5);
//        list1.add(8);

        List<Integer> list2 = new ArrayList<Integer>();
        for (int i = 200; i < 300; i++) {
            list2.add(i);
        }
//        list2.add(1);
//        list2.add(2);

        List<Integer> list3 = new ArrayList<Integer>();
        for (int i = 300; i < 400; i++) {
            list3.add(i);
        }
//        list3.add(10);
//        list3.add(11);


        List<List> list = new ArrayList<List>();
        list.add(list1);
        list.add(list2);
        list.add(list3);

        //String[]
        long startTime = System.currentTimeMillis();
        System.out.println("Combine to List<String[]>\n" + doCombineString(list).size());
        long stopTime = System.currentTimeMillis();
        System.out.println(stopTime - startTime);

        //Integer[]
        startTime = System.currentTimeMillis();
        System.out.println("Combine to List<Integer[]>\n" + doCombineNums(list).size());
        stopTime = System.currentTimeMillis();
        System.out.println(stopTime - startTime);

        //Recursive
        startTime = System.currentTimeMillis();
        System.out.println("Recursive combine List<String[]>\n" + recursiveCombine(list, Z).size());
        stopTime = System.currentTimeMillis();
        System.out.println(stopTime - startTime);

    }

    public static List<String> doCombineString(List<List> list) {

        List<String> tempList = new ArrayList<String>();
        List<String> resultList = new ArrayList<String>();

        for (int i = Z; i < list.get(Z).size(); i++) {
            resultList.add(String.valueOf(list.get(Z).get(i)));
        }

        StringBuilder sb;
        for (int i = 1; i < list.size(); i++) {
            for (String tempItem : resultList) {
                for (int j = Z; j < list.get(i).size(); j++) {
                    sb = new StringBuilder(tempItem);
                    tempList.add(String.valueOf(sb.append(":").append(list.get(i).get(j))));
                }
            }
            resultList = tempList;
            tempList = new ArrayList<String>();
        }
        return resultList;
    }

    public static List<Integer[]> doCombineNums(List<List> list) {

        List<Integer[]> tempList = new ArrayList<Integer[]>();
        List<Integer[]> resultList = new ArrayList<Integer[]>();

        for (int i = Z; i < list.get(Z).size(); i++) {
            Integer[] tempArray = new Integer[list.size()];
            tempArray[Z] = (Integer) list.get(Z).get(i);
            resultList.add(tempArray);
        }

        Integer[] cloneTempItem;
        for (int i = 1; i < list.size(); i++) {
            for (Integer[] tempItem : resultList) {
                for (int j = Z; j < list.get(i).size(); j++) {
                    cloneTempItem = tempItem.clone();
                    cloneTempItem[i] = (Integer) list.get(i).get(j);
                    tempList.add(cloneTempItem);
                }
            }
            resultList = tempList;
            tempList = new ArrayList<Integer[]>();
        }
        return resultList;
    }

    public static List<String> recursiveCombine(List<List> list, int z) {

        List<String> resultList = new ArrayList<String>();
        StringBuilder sb;

        if (z == list.size() - 1) {
            for (int i = Z; i < list.get(z).size(); i++)
                resultList.add(list.get(z).get(i).toString());

        } else {
            List<String> appendList = recursiveCombine(list, z + 1);
            for (int index = Z; index < list.get(z).size(); index++)
                for (String appendItem : appendList) {
                    sb = new StringBuilder();
                    resultList.add(sb.append(list.get(z).get(index)).append(":").append(appendItem).toString());
                }
        }
        return resultList;
    }
}
