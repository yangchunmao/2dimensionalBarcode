package com.ycm.eidea.conf;


public class CommonConfigured implements Configured {

    protected Configuration conf = null;

    @Override
    public Configuration getConf() {
        return this.conf;
    }

    @Override
    public void setConf(Configuration conf) {
        this.conf = conf;
    }



}