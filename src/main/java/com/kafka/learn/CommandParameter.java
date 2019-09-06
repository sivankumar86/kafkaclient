package com.kafka.learn;

import com.beust.jcommander.Parameter;

public class CommandParameter {
    @Parameter(names = "-clientconf", description = "Client configurations", required = true)
    private String clinetconfig;

    public String getClinetconfig() {
        return clinetconfig;
    }


}
