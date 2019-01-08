package com.bwie.juan_mao.jingdong_kanglijuan.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 卷猫~ on 2018/11/12.
 */

public class AddressBean {

    /**
     * msg : 地址列表请求成功
     * code : 0
     * data : [{"addr":"北京市 昌平区金域国际1-1-","addrid":13141,"mobile":18210926066,"name":"li1694020767","status":0,"uid":21017},{"addr":"北京市 昌平区金域国际1-1-","addrid":13142,"mobile":123,"name":"康利娟","status":0,"uid":21017},{"addr":"北京市昌平区金域国际1-1-","addrid":13143,"mobile":18210926066,"name":"康利娟","status":0,"uid":21017},{"addr":"北京市昌平区金域国际1-1-","addrid":13144,"mobile":18210926066,"name":"康利娟","status":0,"uid":21017},{"addr":"北京市昌平区金域国际1-1-","addrid":13145,"mobile":18210926066,"name":"康利娟","status":0,"uid":21017},{"addr":"Beijing ShangDi","addrid":13146,"mobile":13453201111,"name":"zhangsan","status":0,"uid":21017},{"addr":"TianJing shangHai","addrid":13147,"mobile":12345678944,"name":"hahaha","status":0,"uid":21017}]
     */

    private String msg;
    private String code;
    private List<DataBean> data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable {
        /**
         * addr : 北京市 昌平区金域国际1-1-
         * addrid : 13141
         * mobile : 18210926066
         * name : li1694020767
         * status : 0
         * uid : 21017
         */

        private String addr;
        private int addrid;
        private long mobile;
        private String name;
        private int status;
        private int uid;

        public String getAddr() {
            return addr;
        }

        public void setAddr(String addr) {
            this.addr = addr;
        }

        public int getAddrid() {
            return addrid;
        }

        public void setAddrid(int addrid) {
            this.addrid = addrid;
        }

        public long getMobile() {
            return mobile;
        }

        public void setMobile(long mobile) {
            this.mobile = mobile;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }
    }
}
