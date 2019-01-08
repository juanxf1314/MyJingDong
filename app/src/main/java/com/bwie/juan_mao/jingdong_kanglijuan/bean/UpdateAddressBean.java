package com.bwie.juan_mao.jingdong_kanglijuan.bean;

/**
 * Created by 卷猫~ on 2018/11/12.
 */

public class UpdateAddressBean {

    /**
     * msg : 更新成功
     * code : 0
     * data : {"addr":"北京八维研修学院","addrid":2,"mobile":110,"name":"刘群峰","status":0,"uid":71}
     */

    private String msg;
    private String code;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * addr : 北京八维研修学院
         * addrid : 2
         * mobile : 110
         * name : 刘群峰
         * status : 0
         * uid : 71
         */

        private String addr;
        private int addrid;
        private int mobile;
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

        public int getMobile() {
            return mobile;
        }

        public void setMobile(int mobile) {
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
