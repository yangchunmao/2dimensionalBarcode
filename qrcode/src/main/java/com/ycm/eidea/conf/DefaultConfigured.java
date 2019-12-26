package com.ycm.eidea.conf;

public class DefaultConfigured extends CommonConfigured {

    public DefaultConfigured() {
        setConf(Configuration.getDefault());
    }
}