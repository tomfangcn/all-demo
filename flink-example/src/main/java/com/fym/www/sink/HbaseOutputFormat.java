//package com.fym.www.sink;
//
//import org.apache.flink.api.common.io.RichOutputFormat;
//import org.apache.flink.api.java.tuple.Tuple2;
//import org.apache.hadoop.hbase.client.Connection;
//import org.apache.hadoop.hbase.client.Table;
//
//import java.text.SimpleDateFormat;
//
//public class HbaseOutputFormat extends RichOutputFormat<Tuple2> {
//
//    private String host;
//    private String zkParent;
//    private String[] rowkey;
//    private String tableName;
//    private String[] columnNames;
//    private String[] columnTypes;
//
//    private String[] families;
//    private String[] qualifiers;
//
//    private transient org.apache.hadoop.conf.Configuration conf;
//    private transient Connection conn;
//    private transient Table table;
//
//    public final SimpleDateFormat ROWKEY_DATE_FORMAT = new SimpleDateFormat("yyyyMMddHHmmss");
//    public final SimpleDateFormat FIELD_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//
//}
