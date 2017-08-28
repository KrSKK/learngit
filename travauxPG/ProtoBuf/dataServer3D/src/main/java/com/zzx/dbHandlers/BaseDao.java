package com.zzx.dbHandlers;

import com.zzx.protoClasses.AddressBookProtos;
import com.zzx.protoClasses.DataProto;
import com.zzx.protoClasses.DbDataProto;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;

/**
 * Created by wenkai on 8/10/17.
 * 数据库操作
 */
public class BaseDao {
    public Connection connection = null;
    private Statement stmt = null;
    private String tableName = "device_config";

    public BaseDao(){

    }

    public Connection DbConnector(){
        String url = "jdbc:mysql://localhost:3306/test?"
                + "user=root&password=123456&useUnicode=true&characterEncoding=UTF8";
        try{
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("driver loaded succeed!");
            connection = DriverManager.getConnection(url);
            //connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root", "123456");
            stmt = connection.createStatement();
            connection.setAutoCommit(false);
            System.out.println("MySQL connection succeed!");
        }catch (Exception e){
            System.out.println(e);
        }finally {
            return connection;
        }
    }

    public ResultSet DataResearch(String module){
        ResultSet rs = null;
        String sql = "SELECT  * FROM " + tableName + "where MODULE = " + module;
        try {
            rs = stmt.executeQuery(sql);
        }catch (Exception e){
            System.out.println(e);
        }finally {
            return rs;
        }
    }

    public boolean DataUpdate(String sql) throws SQLException {
        try {
            stmt.executeUpdate(sql);
            connection.commit();
            return true;
        }catch (SQLException e){
            System.out.println(e);
            connection.rollback();
            return false;
        }
    }

    public String SQLMaker(String type, String module, DbDataProto.DbData dbData){
        String sql = null;
        switch (Integer.parseInt(type)){
            case 11:
                sql = "insert into " + tableName + "(deviceid,devicename,devicepos,deviceip,module) values(" +
                       dbData.getDeviceId() + "," + dbData.getDeviceName() + "," + dbData.getDevicePos() + "," +
                        dbData.getDeviceIp() + "," + dbData.getModule() + ")";
                break;
            case 21:
                sql = "update " + tableName + " set deviceid = " + dbData.getDeviceId() + ", devicename = " +
                        dbData.getDeviceName() + ", devicepos = " + dbData.getDevicePos() + ", deviceip = " +
                        dbData.getDeviceIp() + ", module = " + dbData.getModule() + " where guid = " +
                        dbData.getGuiId();
                break;
            case 31:
                sql = "delete from " + tableName + " where guid = " + dbData.getGuiId();
                break;
            default:
                break;
        }
        return sql;
    }
}
