package com.stuff.bigdata.util.hbase;

import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellScanner;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.List;

/**
 * Created by Beauty on 2/21/16.
 */
public class HBaseUtil {

    public static void getByRowKey(Table table, String rowKey) throws IOException {

        System.out.println("=========== RowKey Get ============");
        Get get = new Get(Bytes.toBytes(rowKey));

        Result result = table.get(get);

        System.out.println(result);

        byte[] row = result.getRow();
        System.out.println(new String(row));
        System.out.println(new String(result.getValue(Bytes.toBytes("colfam1"), Bytes.toBytes("q1"))));

        result.raw();

        System.out.println("---->list cells");
        List<Cell> cells = result.listCells();

        for (Cell cell: cells) {
            System.out.println("cell: " + cell);
            System.out.println("cell family array" + new String(cell.getFamilyArray()));

        }

        System.out.println("---->cell scanner");
        CellScanner cellScanner = result.cellScanner();

        while(cellScanner.advance()){

            System.out.println(cellScanner.current());
        }
    }

    public static void scanTable(Table table) throws IOException {

        System.out.println("=========== Scan ============");
        Scan scan = new Scan();
        scan.setStartRow(Bytes.toBytes("myrow-1"));
        scan.setStopRow(Bytes.toBytes("myrow-3"));

        ResultScanner scanner = table.getScanner(scan);

        for(Result r : scanner){

            System.out.println("Row: " + new String(r.getRow()));

            CellScanner cellScanner = r.cellScanner();
            while (cellScanner.advance()){
                Cell cell = cellScanner.current();
                System.out.println("current cell: " + cell);
                System.out.println("cell value: " + new String(cell.getValueArray()));
                System.out.println("cell value offset: " + cell.getValueOffset());
                System.out.println("cell value length: " + cell.getValueLength());
            }
        }

    }
}
