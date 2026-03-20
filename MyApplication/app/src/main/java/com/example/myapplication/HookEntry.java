package com.example.myapplication;

import android.util.Log;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.IXposedHookZygoteInit;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class HookEntry implements IXposedHookZygoteInit, IXposedHookLoadPackage {
    private static final String TAG = "HookEntry";

    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
        Log.d(TAG, "handleLoadPackage: " + lpparam.packageName);
    }

    @Override
    public void initZygote(StartupParam startupParam) throws Throwable {

    }
}
