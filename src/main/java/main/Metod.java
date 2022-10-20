package main;

import java.util.ArrayList;
import java.util.List;

public class Metod {
    public static List num() {
        ArrayList list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add("Номер " + i);
        }
        return list;
    }
}
