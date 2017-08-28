package com.zzx.protoHandlers;

import com.zzx.dbHandlers.BaseDao;
import com.zzx.protoClasses.DataProto;

/**
 * Created by wenkai on 8/17/17.
 * 消息处理器
 */
public class MsgHandlers {
    public MsgHandlers(){

    }

    //
    /*public byte[] dataBuilder(String type, BaseDao baseDao){
        String sql =
    }*/

    public byte[] senderBuilder(byte[] dataout, byte[] type){
        byte[] start = "BG".getBytes();
        byte[] end = "ED".getBytes();
        int lg = dataout.length;
        byte[] length = new byte[4];
        for (int i = 0; i < 4; i++){
            length[i] = (byte)((lg >> 8*(3-i)) & 0xFF);
        }
        byte[] send = new byte[start.length + type.length + length.length +
                dataout.length + end.length];
        System.arraycopy(start, 0, send, 0, start.length);
        System.arraycopy(type, 0, send, start.length, type.length);
        System.arraycopy(length, 0, send, start.length+type.length, length.length);
        System.arraycopy(dataout, 0, send, start.length+type.length+length.length, dataout.length);
        System.arraycopy(end, 0, send, start.length+type.length+length.length+dataout.length, end.length);
        return send;
    }

    public String typeParser(byte[] datain){
        byte[] type = new byte[2];
        for (int i = 0; i < 2; i++){
            type[i] = datain[i+2];
        }
        String typein = new String(type);
        return typein;
    }

    /*public String systemParser(byte[] datain){
        byte[] system = new byte[3];
        for(int i = 0; i < 3; i++){
            system[i] = datain[i+5];
        }
        return new String(system);
    }*/

    public int lengthParser(byte[] datain){
        byte[] length = new byte[4];
        for (int i = 0; i < 4; i++){
            length[i] = datain[i+4];
        }
        return (length[3] & 0xFF | (length[2] & 0xFF) << 8 |
                (length[1] & 0xFF) << 16 | (length[0] & 0xFF) << 24);
    }

    public DataProto.Data.Builder dataParser(byte[] datain, int length){
        byte[] data = new byte[length];
        for (int i = 0; i < length; i++){
            data[i] = datain[i+8];
        }
        DataProto.Data.Builder databuilder = DataProto.Data.newBuilder();
        try {
            databuilder = DataProto.Data.parseFrom(data).toBuilder();
        }catch (Exception e){
            System.out.println(e);
        }
        return databuilder;
    }
}
