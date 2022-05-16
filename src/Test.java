import Model.MapModel;

import java.util.ArrayList;
import java.util.Scanner;

public class Test {

    public static void main(String[] args) {
        App newApp = new App();
        MapModel mapModel = newApp.getMapModel();
        mapModel.setTesting(true);

        Scanner in = new Scanner(System.in);

        System.out.println("Available tests: 1-4");
        System.out.println("Enter the test you want to do: ");
        int a = in.nextInt();
        ArrayList<ArrayList<Integer>> map;
        switch (a) {
            case 1:
                mapModel.restart(0);
                 map = mapModel.getMap();
                for (ArrayList<Integer> integers : map) {
                    for (Integer integer : integers) {
                        System.out.print(integer + " ");
                    }
                    System.out.println( "");
                }
                break;
            case 2:
                mapModel.restart(1);
                map = mapModel.getMap();
                for (ArrayList<Integer> integers : map) {
                    for (Integer integer : integers) {
                        System.out.print(integer + " ");
                    }
                    System.out.println( "");
                }
                break;
        }
    }



}
