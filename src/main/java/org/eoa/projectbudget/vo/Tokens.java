package org.eoa.projectbudget.vo;

import java.util.Date;

/**
 * @Author: 张骏山
 * @Date: 2023/11/6 15:26
 * @PackageName: org.eoa.projectbudget.vo
 * @ClassName: Tokens
 * @Description: TODO
 * @Version: 1.0
 **/


public class Tokens {
    String shortToken;
    String longToken;
    Date timeStamp;

    public String getShortToken() {
        return shortToken;
    }

    public Tokens setShortToken(String shortToken) {
        this.shortToken = shortToken;
        return this;
    }

    public String getLongToken() {
        return longToken;
    }

    public Tokens setLongToken(String longToken) {
        this.longToken = longToken;
        return this;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public Tokens setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
        return this;
    }

    @Override
    public String toString() {
        return "Tokens{" +
                "shortToken='" + shortToken + '\'' +
                ", longToken='" + longToken + '\'' +
                ", timeStamp=" + timeStamp +
                '}';
    }
}
