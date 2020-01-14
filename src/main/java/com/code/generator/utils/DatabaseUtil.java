package com.code.generator.utils;


import com.code.generator.CodeGenerator;
import com.code.generator.model.BaseData;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: Mr.Feng
 * @create: 2018-08-06
 **/
/**————————————————
        版权声明：本文为CSDN博主「非非哥」的原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接及本声明。
        原文链接：https://blog.csdn.net/feng2036/article/details/81480483*/
public class DatabaseUtil {
    private final static Logger LOGGER = LoggerFactory.getLogger(DatabaseUtil.class);

    /**
    "com.mysql.jdbc.Driver";*/
    private static final String DRIVER = CodeGenerator.JDBC_DIVER_CLASS_NAME;
   /** private static final String URL = "jdbc:mysql://183.131.202.92:3306/ebb_ip_platform?useUnicode=true&amp;characterEncoding=utf-8&amp;useSSL=false";
   "jdbc:mysql://183.131.202.92:3306/ebb_shop?useUnicode=true&amp;characterEncoding=utf-8&amp;useSSL=false";*/
    private static final String URL = CodeGenerator.JDBC_URL;
    /**"root";*/
    private static final String USERNAME = CodeGenerator.JDBC_USERNAME;
    /**"w8Kz4#mj@";*/
    private static final String PASSWORD = CodeGenerator.JDBC_PASSWORD;
    private static final String SQL = "SELECT * FROM ";

    static {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            LOGGER.error("can not load jdbc driver", e);
        }
    }

    /**
     * 获取数据库连接
     *
     * @return
     */
    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            LOGGER.error("get connection failure", e);
        }
        return conn;
    }

    /**
     * 关闭数据库连接
     * @param conn
     */
    public static void closeConnection(Connection conn) {
        if(conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                LOGGER.error("close connection failure", e);
            }
        }
    }

    /**
     * 获取表中字段名称
     * @param tableName 表名
     * @return
     */
    public static List<String> getColumnNames(String tableName) {
        List<String> columnNames = new ArrayList<String>();
        //与数据库的连接
        Connection conn = getConnection();
        PreparedStatement pStemt = null;
        String tableSql = SQL + tableName;
        try {
            pStemt = conn.prepareStatement(tableSql);
            //结果集元数据
            ResultSetMetaData rsmd = pStemt.getMetaData();
            //表列数
            int size = rsmd.getColumnCount();
            for (int i = 0; i < size; i++) {
                columnNames.add(rsmd.getColumnName(i + 1));
            }
        } catch (SQLException e) {
            LOGGER.error("getColumnNames failure", e);
        } finally {
            if (pStemt != null) {
                try {
                    pStemt.close();
                    closeConnection(conn);
                } catch (SQLException e) {
                    LOGGER.error("getColumnNames close pstem and connection failure", e);
                }
            }
        }
        return columnNames;
    }

    /**
     * 获取表中所有字段类型
     * @param tableName
     * @return
     */
    public static List<String> getColumnTypes(String tableName) {
        List<String> columnTypes = new ArrayList<String>();
        //与数据库的连接
        Connection conn = getConnection();
        PreparedStatement pStemt = null;
        String tableSql = SQL + tableName;
        try {
            pStemt = conn.prepareStatement(tableSql);
            //结果集元数据
            ResultSetMetaData rsmd = pStemt.getMetaData();
            //表列数
            int size = rsmd.getColumnCount();
            for (int i = 0; i < size; i++) {
                columnTypes.add(rsmd.getColumnTypeName(i + 1));
            }
        } catch (SQLException e) {
            LOGGER.error("getColumnTypes failure", e);
        } finally {
            if (pStemt != null) {
                try {
                    pStemt.close();
                    closeConnection(conn);
                } catch (SQLException e) {
                    LOGGER.error("getColumnTypes close pstem and connection failure", e);
                }
            }
        }
        return columnTypes;
    }

    /**
     * 获取表中字段的注释
     * @param tableName
     * @return
     */
    public static List<String> getColumnComments(String tableName) {
        List<String> columnTypes = new ArrayList<String>();
        Connection conn = getConnection();
        PreparedStatement pStemt = null;
        String tableSql = SQL + tableName;
        List<String> columnComments = new ArrayList<String>();
        ResultSet rs = null;
        try {
            pStemt = conn.prepareStatement(tableSql);
            rs = pStemt.executeQuery("show full columns from " + tableName);
            while (rs.next()) {
                columnComments.add(rs.getString("Comment"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                    closeConnection(conn);
                } catch (SQLException e) {
                    LOGGER.error("getColumnComments close ResultSet and connection failure", e);
                }
            }
        }
        return columnComments;
    }

    /**
     * 获得某表的建表语句
     * @param tableName
     * @return
     * @throws Exception
     */
    public static String getCommentByTableName(String tableName)  {
        String comment =null;
        try {
        Map map = new HashMap();
        Connection conn = getConnection();

        Statement stmt = (Statement) conn.createStatement();
        ResultSet rs = stmt.executeQuery("SHOW CREATE TABLE " + tableName);
            if (rs != null && rs.next()) {
                String createDDL = rs.getString(2);
                comment =parse(createDDL);
            }
        rs.close();
        stmt.close();
        conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return comment;
    }
    /**
     * 返回注释信息
     * @param all
     * @return
     */

    public static String parse(String all) {
        String comment = null;
        int index = all.indexOf("COMMENT='");
        if (index < 0) {
            return "";
        }
        comment = all.substring(index + 9);
        comment = comment.substring(0, comment.length() - 1);
        return comment;
    }
    public static void main(String[] args) {
        String tableName = "t_run_shop";
        List<BaseData> table = getBaseDataList("t_run_shop");

       // System.out.println(getCommentByTableName("t_run_shop"));
    }

    public static List<BaseData> getBaseDataList(String tableName){
        List<String> columnNames = getColumnNames(tableName);
        List<String> columnTypes = getColumnTypes(tableName);
        List<String> columnComments = getColumnComments(tableName);
        List<BaseData> list = new ArrayList<BaseData>();
        for (int i = 0 ; i<columnNames.size(); i++){
            BaseData baseData = new BaseData();
            baseData.setColumnName_(columnNames.get(i));
            baseData.setColumnName(firstUpperCamelCase(columnNames.get(i)));
            String columnType = null;
            switch (columnTypes.get(i)){
                case "VARCHAR":
                    columnType = "String";
                    break;
                case "DATETIME":
                    columnType = "Date";
                    break;
                case "INT":
                    columnType = "Integer";
                    break;
                case "TINYINT":
                    columnType = "Integer";
                    break;
                case "DECIMAL":
                    columnType = "BigDecimal";
                    break;
                case "TEXT":
                    columnType = "String";
                    break;
                case "BIGINT":
                    columnType = "Long";
                    break;
                default:
                    columnType = "未知类型";
                    System.out.print("存在不支持类型！请手写。");
                    break;

            }
            baseData.setColumnType(columnType);
            baseData.setColumnComment(columnComments.get(i));
            list.add(baseData);
        }
        return list;
    }

    public static String firstUpperCamelCase(String str) {
        if (StringUtils.isNotBlank(str)) {
            str = str.replace("T_", "");
            str = str.toLowerCase();
            String[] strs = str.split("_");
            if (strs.length == 1) {
                return str.substring(0, 1).toLowerCase()+ str.substring(1);
            } else {
                String convertedStr = "";
                for (int i = 0; i < strs.length; i++) {
                    convertedStr += firstLetterUpper(strs[i]);
                }
                return convertedStr.substring(0, 1).toLowerCase()+ convertedStr.substring(1);
            }
        }
        return str;
    }

    public static String firstLetterUpper(String str) {
        if (StringUtils.isNotBlank(str)) {
            str = str.replace("T_", "");
            str = str.toLowerCase();
            return str.substring(0, 1).toUpperCase()
                    + str.substring(1, str.length());
        }
        return str;
    }

}
