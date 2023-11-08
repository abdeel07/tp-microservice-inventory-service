package com.ms.inventoryservice.configurations;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component @ConfigurationProperties("my-configs")
public class ApplicationPropertiesConfiguration {
    private int limitCustomers;

    public int getLimitCustomers(){
        return limitCustomers;
    }

    public void setLimitCustomers(int limitCustomers){
        this.limitCustomers = limitCustomers;
    }
}
