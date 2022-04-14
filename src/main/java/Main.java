import java.io.*;

import com.dant.entity.*;
import com.dant.storage.BasicStorage;


public class Main {

    public static void main(String[] args) throws IOException {
        String [] columns = new String[] {"vendor_name"};
        Table table = Utils.loadTableFromData("yellow_tripdata_2009-01.csv");
        String[][] data = table.getBasicStorage().selecttest(columns);
    }
}