package cn.omsfuk.blog.domain;

import java.sql.Date;

/**
 * Created by omsfuk on 17-4-24.
 */
public class Picture {

    private Date tdate;

    private String fileName;

    public Date getTdate() {
        return tdate;
    }

    public void setTdate(Date tdate) {
        this.tdate = tdate;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
