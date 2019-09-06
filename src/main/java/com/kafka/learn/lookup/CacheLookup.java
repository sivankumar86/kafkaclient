package com.kafka.learn.lookup;

import com.google.common.cache.*;
import com.kafka.learn.mock.UserdataGenerator;
import com.kafka.learn.model.DetailData;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class CacheLookup  {



    static Cache cache  = CacheBuilder.newBuilder()
            .maximumSize(1000)
            .expireAfterWrite(10, TimeUnit.MINUTES)
            .removalListener(new RemovalListener(){

                        public void onRemoval(RemovalNotification notification) {
                            System.out.println("Going to remove data from InputDataPool");
                           // logger.info("Following data is being removed:"+notification.getKey());
                            if(notification.getCause()==RemovalCause.EXPIRED)
                            {
                                System.out.println("This data expired:"+notification.getKey());
                            }else
                            {
                                System.out.println("This data didn't expired but evacuated intentionally"+notification.getKey());
                            }

                        }}
            )
            .concurrencyLevel(4)
            .recordStats()
            .build();

    /**
     * Cache thousands items
     * @param userid
     * @return
     * @throws ExecutionException
     */
    public static Long getLastSeen(String userid,Long timestamp) throws ExecutionException {
         Long cacheTimetamp=(Long)cache.getIfPresent(userid);
         if(cacheTimetamp==null){
             cache.put(userid,timestamp);
             return timestamp;
         }
        return cacheTimetamp;
    }




}
