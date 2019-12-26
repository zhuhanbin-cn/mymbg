package com.code.generator.model;

/**
 * @author: Mr.Feng
 * @create: 2018-08-07
 **/
/**————————————————
        版权声明：本文为CSDN博主「非非哥」的原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接及本声明。
        原文链接：https://blog.csdn.net/feng2036/article/details/81480483*/
public class BaseData {

    private String columnComment;
    private String columnName_;

    private String columnType;

    private String columnName;


    public String getColumnName_() {
        return columnName_;
    }

    public void setColumnName_(String columnName_) {
        this.columnName_ = columnName_;
    }

    public String getColumnComment() {
        return columnComment;
    }

    public void setColumnComment(String columnComment) {
        this.columnComment = columnComment;
    }

    public String getColumnType() {
        return columnType;
    }

    public void setColumnType(String columnType) {
        this.columnType = columnType;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }
}
