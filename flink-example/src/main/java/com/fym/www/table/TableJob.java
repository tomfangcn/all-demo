package com.fym.www.table;

import com.fym.www.table.model.User;
import org.apache.flink.api.common.functions.JoinFunction;
import org.apache.flink.api.java.functions.KeySelector;
import org.apache.flink.api.java.tuple.Tuple;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.api.java.tuple.Tuple4;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.AssignerWithPeriodicWatermarks;
import org.apache.flink.streaming.api.watermark.Watermark;
import org.apache.flink.streaming.api.windowing.assigners.TumblingEventTimeWindows;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.table.api.Table;
import org.apache.flink.table.api.java.StreamTableEnvironment;
import org.apache.flink.types.Row;

import javax.annotation.Nullable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TableJob {

    public static void main(String[] args) throws Exception {
        final long time = new Date().getTime();
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
//        env.setStreamTimeCharacteristic(TimeCharacteristic.ProcessingTime);
        env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);
        final StreamTableEnvironment tbEnv = StreamTableEnvironment.getTableEnvironment(env);
        List<User> testData = new ArrayList<>();

        for (int i = 1 ; i <= 1000 ; i++){
            User tmp = new User();
            tmp.setId(i%10);
            tmp.setName("name_"+(i%10));
            tmp.setMoney(BigDecimal.ONE);

            tmp.setcTime(time+1000*i);
            testData.add(tmp);
        }

        DataStream<User> source1 = env.fromCollection(testData)
                .assignTimestampsAndWatermarks(new AssignerWithPeriodicWatermarks<User>() {

            private long maxTime ;
            @Nullable
            @Override
            public Watermark getCurrentWatermark() {
                return new Watermark(maxTime);
            }

            @Override
            public long extractTimestamp(User user, long l) {
                maxTime = user.getcTime();
                return maxTime;
            }
        });
//        DataStream<User> source2 = env.fromCollection(testData)
//                .assignTimestampsAndWatermarks(new AssignerWithPeriodicWatermarks<User>() {
//
//                    private long maxTime ;
//                    @Nullable
//                    @Override
//                    public Watermark getCurrentWatermark() {
//                        return new Watermark(maxTime);
//                    }
//
//                    @Override
//                    public long extractTimestamp(User user, long l) {
//                        maxTime = user.getcTime();
//                        return maxTime;
//                    }
//                });
        tbEnv.registerDataStream("T_USER1",source1,"id,name,money,cTime.rowtime");
//        tbEnv.registerDataStream("T_USER2",source2,"id,name,money,cTime.rowtime");
//        Table result1 = tbEnv.sqlQuery("SELECT * FROM T_USER");
//        Table result2 = tbEnv.sqlQuery("SELECT * FROM T_USER WHERE id = 1");
//        Table result3 = tbEnv.sqlQuery("SELECT id ,COUNT(id) FROM T_USER GROUP BY id");
//        Table result4 = tbEnv.sqlQuery("SELECT id ,COUNT(id) FROM T_USER WHERE id = 1 GROUP BY id");
//        Table result5 = tbEnv.sqlQuery("SELECT id ,SUM(money) as salary ,TUMBLE_START(cTime, INTERVAL '1' MINUTE) AS startTime FROM T_USER GROUP BY TUMBLE(cTime,INTERVAL '1' MINUTE), id ");
//        Table result6 = tbEnv.sqlQuery("SELECT id ,SUM(money) as salary ,TUMBLE_START(cTime, INTERVAL '1' MINUTE) AS startTime FROM T_USER WHERE id = 1 GROUP BY TUMBLE(cTime,INTERVAL '1' MINUTE), id ");
//        Table result7 = tbEnv.sqlQuery("SELECT id ,SUM(money) as salary ,TUMBLE_START(cTime, INTERVAL '1' MINUTE) AS startTime,TUMBLE_END(cTime, INTERVAL '1' MINUTE) AS endTime FROM T_USER WHERE id = 1 GROUP BY TUMBLE(cTime,INTERVAL '1' MINUTE), id ");
//        Table result8 = tbEnv.sqlQuery("SELECT id ,SUM(money) as salary ,TUMBLE_START(cTime, INTERVAL '1' MINUTE) AS startTime,TUMBLE_END(cTime, INTERVAL '1' MINUTE) AS endTime,TUMBLE_ROWTIME(cTime, INTERVAL '1' MINUTE) AS rowTime FROM T_USER WHERE id = 1 GROUP BY TUMBLE(cTime,INTERVAL '1' MINUTE), id ");
//        Table result9 = tbEnv.sqlQuery("SELECT o.*  FROM T_USER1 o,T_USER1 s WHERE o.id = s.id AND o.cTime = s.cTime - INTERVAL '1' MINUTE ");
        Table result10 = tbEnv.sqlQuery("SELECT id AS id1 ,TUMBLE_START(cTime, INTERVAL '1' MINUTE) AS startTime FROM T_USER1 WHERE id = 1 GROUP BY TUMBLE(cTime,INTERVAL '1' MINUTE), id ");
        System.out.println("result10_"+result10.getSchema());
        DataStream<Tuple2<Boolean,Row>> resStream1 = tbEnv.toRetractStream(result10, Row.class);
        Table result11 = tbEnv.sqlQuery("SELECT id AS id2 ,(TUMBLE_START(cTime,INTERVAL '1' MINUTE) + INTERVAL '1' MINUTE) AS ostartTime FROM T_USER1 WHERE id = 1 GROUP BY TUMBLE(cTime,INTERVAL '1' MINUTE), id ");
        System.out.println("result11_"+result11.getSchema());
        DataStream<Tuple2<Boolean,Row>> resStream2= tbEnv.toRetractStream(result11, Row.class);

        resStream1.join(resStream2).where(new KeySelector<Tuple2<Boolean, Row>, Object>() {
            @Override
            public Object getKey(Tuple2<Boolean, Row> booleanRowTuple2) throws Exception {
                return booleanRowTuple2.f1.getField(0);
            }
        }).equalTo(new KeySelector<Tuple2<Boolean, Row>, Object>() {
            @Override
            public Object getKey(Tuple2<Boolean, Row> booleanRowTuple2) throws Exception {
                return booleanRowTuple2.f1.getField(0);
            }
        }).window(TumblingEventTimeWindows.of(Time.minutes(1)))
                .apply(new JoinFunction<Tuple2<Boolean, Row>, Tuple2<Boolean, Row>, Tuple4<Object, Object,Object,Object>>() {
                    @Override
                    public Tuple4<Object, Object,Object,Object> join(Tuple2<Boolean, Row> booleanRowTuple2, Tuple2<Boolean, Row> booleanRowTuple22) throws Exception {
                        System.out.println("1"+booleanRowTuple2.f1.getField(1));
                        System.out.println("2"+booleanRowTuple22.f1.getField(1));
                        System.out.println(booleanRowTuple2.f1.getField(1).equals(booleanRowTuple22.f1.getField(1)));
                        if(booleanRowTuple2.f1.getField(1).equals(booleanRowTuple22.f1.getField(1))){
                            return new Tuple4<>(booleanRowTuple2.f1.getField(0),booleanRowTuple2.f1.getField(1),
                                    booleanRowTuple22.f1.getField(0),booleanRowTuple22.f1.getField(1));
                        }
                        return new Tuple4<>();
                    }
                }).printToErr();

//        Table result12 = tbEnv.sqlQuery("SELECT * FROM (SELECT id AS id1,SUM(money) as salary1 ,TUMBLE_START(cTime, INTERVAL '1' MINUTE) AS startTime FROM T_USER1 WHERE id = 1 GROUP BY TUMBLE(cTime,INTERVAL '1' MINUTE), id ) AS T1 ,(SELECT id AS id2 ,SUM(money) as salary2 ,TUMBLE_START(cTime, INTERVAL '1' MINUTE) + INTERVAL '1' MINUTE AS ostartTime FROM T_USER1 WHERE id = 1 GROUP BY TUMBLE(cTime,INTERVAL '1' MINUTE), id) AS T2 WHERE T1.startTime = T2.ostartTime");
//        Table result12 = result10.join(result11).where("id1=id2 && startTime===ostartTime");
//        System.out.println(result12.getSchema());
//        tbEnv.toRetractStream(result12, Row.class).print();
        env.execute("table test");
    }
}
