package com.newsing.utils;

import com.newsing.db.NewingEntity;

/**
 * Created by Qzhu on 2017/7/27.
 */

public class DBManager {

    public static int Insert(NewingEntity record){
        record.save();
        return record.getId();
    }

    public static int delete(NewingEntity record){
        return record.delete();
    }
}
