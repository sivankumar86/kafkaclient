package com.kafka.learn.utils;

import org.junit.Test;

public class ApplicationPropertiesTest {

    @Test
    public void testPropertyLoad(){
        ApplicationProperties applicationProperties= new ApplicationProperties();
        System.out.println(applicationProperties.getProperties());

    }
}
