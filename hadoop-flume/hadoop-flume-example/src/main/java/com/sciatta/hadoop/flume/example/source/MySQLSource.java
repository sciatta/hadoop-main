package com.sciatta.hadoop.flume.example.source;

import org.apache.flume.Context;
import org.apache.flume.Event;
import org.apache.flume.EventDeliveryException;
import org.apache.flume.PollableSource;
import org.apache.flume.conf.Configurable;
import org.apache.flume.event.SimpleEvent;
import org.apache.flume.source.AbstractSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by yangxiaoyu on 2020/5/8<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * MySQLSource
 */
public class MySQLSource extends AbstractSource implements Configurable, PollableSource {

    private static final Logger LOG = LoggerFactory.getLogger(MySQLSource.class);
    private MySQLHelper sqlSourceHelper;

    @Override
    public long getBackOffSleepIncrement() {
        return 0;
    }

    @Override
    public long getMaxBackOffSleepInterval() {
        return 0;
    }

    @Override
    public void configure(Context context) {
        // 初始化
        sqlSourceHelper = new MySQLHelper(context);
    }

    @Override
    public PollableSource.Status process() throws EventDeliveryException {
        try {
            List<Event> events = new ArrayList<>();
            HashMap<String, String> header = new HashMap<>();

            // 查询数据表
            List<List<Object>> result = sqlSourceHelper.executeQuery();

            // 如果有返回数据，则将数据封装为event
            if (!result.isEmpty()) {
                List<String> allRows = sqlSourceHelper.getAllRows(result);
                Event event;
                for (String row : allRows) {
                    event = new SimpleEvent();
                    event.setBody(row.getBytes());
                    event.setHeaders(header);
                    events.add(event);
                }

                // 将event写入channel
                this.getChannelProcessor().processEventBatch(events);

                // 更新数据表中的offset信息
                sqlSourceHelper.updateOffset2DB(result.size());
            }

            // 等待时长
            Thread.sleep(sqlSourceHelper.getRunQueryDelay());
            return Status.READY;

        } catch (InterruptedException e) {
            LOG.error("Error processing row：", e);
            return Status.BACKOFF;
        }
    }

    @Override
    public synchronized void stop() {
        LOG.info("Stopping sql source {} ...", getName());
        try {
            // 关闭资源
            sqlSourceHelper.close();
        } finally {
            super.stop();
        }
    }
}
