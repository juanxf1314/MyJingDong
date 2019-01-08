package com.bwie.juan_mao.jingdong_kanglijuan.utils;

import android.content.Context;

import com.bwie.juan_mao.jingdong_kanglijuan.bean.DaoMaster;
import com.bwie.juan_mao.jingdong_kanglijuan.bean.DaoSession;

/**
 * Created by 卷猫~ on 2018/11/15.
 */

public class DaoManager {
    private static volatile DaoManager instance;
    private final DaoSession daoSession;

    private DaoManager(Context context) {
        daoSession = DaoMaster.newDevSession(context, "history.db");
    }

    public static DaoManager getInstance(Context context) {
        if (instance == null) {
            synchronized (DaoManager.class) {
                if (null == instance) {
                    instance = new DaoManager(context);
                }
            }
        }
        return instance;
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }
}
