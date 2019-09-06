package com.kafka.learn.lookup;

import com.google.common.cache.*;
import com.kafka.learn.KafkaProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class CacheLookup  {

    private static final Logger logger = LoggerFactory.getLogger(KafkaProducer.class);


    static Cache cache  = CacheBuilder.newBuilder()
            .maximumSize(1000)
            .expireAfterWrite(10, TimeUnit.MINUTES)
            .removalListener(new RemovalListener(){

                        public void onRemoval(RemovalNotification notification) {
                            logger.debug("Going to remove data from InputDataPool");
                           // logger.info("Following data is being removed:"+notification.getKey());
                            if(notification.getCause()==RemovalCause.EXPIRED)
                            {
                                logger.debug("This data expired:"+notification.getKey());
                            }else
                            {
                                logger.debug("This data didn't expired but evacuated intentionally"+notification.getKey());
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
