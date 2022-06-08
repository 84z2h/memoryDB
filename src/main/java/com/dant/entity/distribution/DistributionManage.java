package com.dant.entity.distribution;

import com.dant.entity.CSVLoading;
import com.dant.entity.ResultSet;
import com.dant.entity.Table;
import com.dant.entity.TimerManage;
import com.dant.entity.columns.Column;
import com.dant.entity.dto.ColumnDTO;
import com.dant.entity.dto.TableDTO;
import com.dant.storage.BasicStorage;
import org.jboss.resteasy.specimpl.MultivaluedMapImpl;

import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DistributionManage {

    public static String insertTableDistribution(String nameDB,String csv_filename, int max_size, InputStream input, int alternate) throws IOException {
        System.out.println("start loading data");
        String s = null;
        int j = 0;
        long i = 0;
        // TIMER START
        TimerManage.start();
        // Get Table
        Table table = BasicStorage.getTable(csv_filename);
        BufferedReader in = new BufferedReader(new InputStreamReader(input));
        String firstLine = in.readLine();

        String[] columnsSplited = firstLine.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
        List<String> columns = Arrays.asList(columnsSplited);
        List<String> lines =  new ArrayList<>();
        int cpt_alternate  = 0;
        while ((s = in.readLine()) != null && i < max_size) {
            lines.add(s);
            i++;
            if(i % alternate==0){
                DistributionManage.distributed(nameDB ,csv_filename, cpt_alternate, columns, lines, j);
                lines = new ArrayList<>();
                cpt_alternate++;
            }
        }
        DistributionManage.distributed(nameDB,csv_filename, 0, columns, lines,j);
        in.close();
        System.out.println(i  + " lines have been inserted successfully");
        table.setSize(table.getSize()+i);
        // TIMER END
        TimerManage.pause();
        String Time = "Elapsed Time : " + TimerManage.getTime()+" ms\n";
        Time += "Lines inserted : " + i;
        System.out.println(Time);
        return Time;
    }

    public static void distributed(String nameDB,String table_name, int cpt, List<String> columns, List<String> lines, int nbColumns) {
        if (cpt%(ServiceClient.getNbNode() +1)==0) {
            if (lines.size()==0) {
                return ;
            }
            // add rows
            Table table = BasicStorage.getTable(table_name);
            for (String row: lines) {
                String[] line = row.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                for(String column_name: table.getColumns().keySet()) {
                    nbColumns = columns.indexOf(column_name);
                    table.getColumns().get(column_name).addElement(line[nbColumns]);
                }
            }
        }
        else {
            MultivaluedMap<String, Object> map = new MultivaluedMapImpl<>();
            map.add("table", table_name);
            map.add("db",nameDB);
            ServiceClient.singlePostRequest(ServiceClient.getNodes_Name().get(cpt%(ServiceClient.getNbNode()+1)-1) + "/api/insert/Distributed/", lines, map);
        }
    }
}
