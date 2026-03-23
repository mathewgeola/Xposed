package com.example.myapplication.hook;

import android.content.ContentResolver;
import android.provider.Settings;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class HideDev {
    public static final String[] KEYS = {"development_settings_enabled", "adb_enabled",
                                         "adb_wifi_enabled"};

    public static void hook(XC_LoadPackage.LoadPackageParam lpparam) {
        XC_MethodHook hook = new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                super.beforeHookedMethod(param);
                String key = (String) param.args[1];
                for (String k : KEYS) {
                    if (k.equals(key)) {
                        param.setResult(0);
                        return;
                    }
                }
            }
        };

        XposedHelpers.findAndHookMethod(Settings.Global.class, "getInt", ContentResolver.class, String.class, hook);
        XposedHelpers.findAndHookMethod(Settings.Global.class, "getInt", ContentResolver.class, String.class, int.class, hook);

        XposedHelpers.findAndHookMethod(Settings.Secure.class, "getInt", ContentResolver.class, String.class, hook);
        XposedHelpers.findAndHookMethod(Settings.Secure.class, "getInt", ContentResolver.class, String.class, int.class, hook);
    }
}
