package com.kafka.learn.lookup;

import com.kafka.learn.mock.UserdataGenerator;
import com.kafka.learn.model.DetailData;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static com.kafka.learn.lookup.CacheLookup.getLastSeen;
import static org.junit.Assert.assertTrue;

public class CacheLookupTest {

    @Test
    public void testCache() throws ExecutionException, IOException, CloneNotSupportedException, InterruptedException {
        DetailData d= UserdataGenerator.getRow();
        Thread.sleep(20);
        DetailData e= UserdataGenerator.getRow();
        e.setUserId(d.getUserId());

        System.out.println(getLastSeen(d.getUserId(),d.getMetadata().getTimestamp()));
        System.out.println(getLastSeen(e.getUserId(),e.getMetadata().getTimestamp()));
    }
}
