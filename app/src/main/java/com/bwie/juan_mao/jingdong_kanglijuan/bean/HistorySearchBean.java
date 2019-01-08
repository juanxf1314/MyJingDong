package com.bwie.juan_mao.jingdong_kanglijuan.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by 卷猫~ on 2018/11/15.
 */

@Entity(nameInDb = "history")
public class HistorySearchBean {

    @Id(autoincrement = true)
    private Long id;
    private String searchName;
    @Generated(hash = 1265952957)
    public HistorySearchBean(Long id, String searchName) {
        this.id = id;
        this.searchName = searchName;
    }
    @Generated(hash = 954352461)
    public HistorySearchBean() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getSearchName() {
        return this.searchName;
    }
    public void setSearchName(String searchName) {
        this.searchName = searchName;
    }
}
