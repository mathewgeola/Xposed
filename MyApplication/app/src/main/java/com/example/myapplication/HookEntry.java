package com.example.myapplication;

import android.util.Log;

import com.example.myapplication.hook.ClipboardHook;
import com.example.myapplication.hook.HideDev;
import com.example.myapplication.hook.HideStack;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.IXposedHookZygoteInit;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class HookEntry implements IXposedHookZygoteInit, IXposedHookLoadPackage {
    private static final String TAG = "HookEntry";

    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
        Log.d(TAG, "handleLoadPackage: " + lpparam.packageName);

        // 类查找
//        XposedHelpers.findClass()
//        XposedHelpers.findClassIfExists()

        // 字段的查找/设置
//        XposedHelpers.findField()
//        XposedHelpers.findFieldIfExists()
//        XposedHelpers.findFirstFieldByExactType()
//        XposedHelpers.setObjectField();
//        XposedHelpers.setBooleanField();
//        XposedHelpers.setByteField();
//        XposedHelpers.setCharField();
//        XposedHelpers.setDoubleField();
//        XposedHelpers.setFloatField();
//        XposedHelpers.setIntField();
//        XposedHelpers.setLongField();
//        XposedHelpers.setShortField();

        // 方法的Hook
//        XposedHelpers.findAndHookMethod()
//        XposedHelpers.findMethodExact()
//        XposedHelpers.findMethodExactIfExists()
//        XposedHelpers.findMethodsByExactParameters()

        // 构造函数的Hook
//        XposedHelpers.findConstructorExact()
//        XposedHelpers.findConstructorExactIfExists()
//        XposedHelpers.findAndHookConstructor()
//        XposedHelpers.findConstructorBestMatch()


        ClassLoader classLoader = lpparam.classLoader;
        Class<?> aClass = XposedHelpers.findClass("com.yuanrenxue.challenge.fragment.exercise.Ex012Fragment", classLoader);
        XposedHelpers.findAndHookMethod("com.yuanrenxue.challenge.fragment.exercise.Ex012Fragment", classLoader, "instanceMethod", "java.lang.String", new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                Log.d(TAG, "beforeHookedMethod: " + param.args[0]);
                super.beforeHookedMethod(param);
            }

            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                super.afterHookedMethod(param);
                Log.d(TAG, "afterHookedMethod: " + param.getResult());
            }
        });
        XposedHelpers.findAndHookMethod("com.yuanrenxue.challenge.fragment.exercise.Ex012Fragment", classLoader, "staticMethod", int.class, new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                Log.d(TAG, "beforeHookedMethod: " + param.args[0]);
                super.beforeHookedMethod(param);
            }

            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                super.afterHookedMethod(param);
                Log.d(TAG, "afterHookedMethod: " + param.getResult());
            }
        });
        XposedHelpers.findAndHookConstructor("com.yuanrenxue.challenge.fragment.exercise.Ex012Fragment$CustomObject", classLoader, "java.lang.String", new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                Log.d(TAG, "beforeHookedMethod: " + param.args[0]);
                super.beforeHookedMethod(param);
            }

            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                super.afterHookedMethod(param);
            }
        });
        XposedHelpers.findAndHookMethod("com.yuanrenxue.challenge.fragment.exercise.Ex012Fragment", classLoader, "addCounter", new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                Log.d(TAG, "beforeHookedMethod: " + param.thisObject);
                int counter = XposedHelpers.getIntField(param.thisObject, "counter");
                Log.d(TAG, "beforeHookedMethod: counter=" + counter);
                super.beforeHookedMethod(param);
            }

            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                super.afterHookedMethod(param);
                int counter = XposedHelpers.getIntField(param.thisObject, "counter");
                Log.d(TAG, "afterHookedMethod: counter=" + counter);
                XposedHelpers.setIntField(param.thisObject, "counter", counter + 100);
                Log.d(TAG, "afterHookedMethod: counter=" + counter);
            }
        });
        XposedHelpers.findAndHookMethod("com.yuanrenxue.challenge.fragment.exercise.Ex012Fragment", classLoader, "overloadedMethod", new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                super.beforeHookedMethod(param);
            }

            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                super.afterHookedMethod(param);
                Log.d(TAG, "afterHookedMethod: " + param.thisObject);
            }
        });
        XposedHelpers.findAndHookMethod("com.yuanrenxue.challenge.fragment.exercise.Ex012Fragment", classLoader, "overloadedMethod", "java.lang.String", new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                super.beforeHookedMethod(param);
            }

            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                super.afterHookedMethod(param);
                Log.d(TAG, "afterHookedMethod: " + param.thisObject);
            }
        });


        HideDev.hook(lpparam);
        ClipboardHook.hook(lpparam);
        HideStack.hook(lpparam);

        XposedHelpers.findAndHookMethod("com.yuanrenxue.challenge.fragment.exercise.Ex013Fragment", classLoader, "getStackTrace", new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                super.beforeHookedMethod(param);
            }

            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                super.afterHookedMethod(param);
            }
        });
    }

    @Override
    public void initZygote(StartupParam startupParam) throws Throwable {

    }
}
