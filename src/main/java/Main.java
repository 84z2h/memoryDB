import java.io.*;
import java.util.Arrays;

import com.dant.entity.*;
import com.dant.storage.BasicStorage;


public class Main {

    public static void main(String[] args) throws IOException {
        /*
        String [] columns = new String[] {"vendor_name"};
        Table table = Utils.loadTableFromData("yellow_tripdata_2009-01.csv");
        String[][] data = table.getBasicStorage().selecttest(columns);

         */
        /*
        String tablecsv="yellow_tripdata_2009-01.csv";
            System.out.println("start loading data");
            BufferedReader in = new BufferedReader(new FileReader("src\\main\\resources\\"+tablecsv));

            String s = null;
            s=in.readLine();

            String[] columnsCSV = s.split(",");
            Column[] columns =  new Column[columnsCSV.length];
            System.out.println(Arrays.toString(columnsCSV));
            for(int i = 0; i < columnsCSV.length; i++){
                columns[i] = new Column(columnsCSV[i], "String");
                //System.out.println(columnsCSV[i]);
            }
        s = in.readLine();
        String[][] data = new String[5][columnsCSV.length];
        int i = 0;
        // load lines
        while ((s = in.readLine()) != null && i < 5) {
            String[] line = s.split(",");
            System.out.println(line[0]);
            data[i] = line;
            i++;
        }
        StringBuilder res = new StringBuilder();
        for(i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                res = res.append(data[i][j]).append(" ");
            }
            res = res.append("\n");
        }

         */
    }
}