package com.cqm.appdemo.util;

import android.content.Context;

/**
 * Created by chenqunming on 2017/3/9.
 */
public class UserInstance {
    private static UserInstance ourInstance = null;
    private static Context context = null;

    private UserInstance() {
    }

    public static UserInstance getInstance() {
        if (ourInstance == null) {
            synchronized (UserInstance.class) {
                if (ourInstance == null) {
                    ourInstance = new UserInstance();
                }
            }
        }
        return ourInstance;
    }

    public void setContext(Context mContext) {
        context = mContext;
    }


    public String getStringValue(String key) {
        return null;
    }

    public int getIntValue(String key) {
        return 0;
    }


}
