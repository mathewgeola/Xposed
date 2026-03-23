package com.example.myapplication.hook;

import android.content.ClipboardManager;

import java.util.ArrayList;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class HideStack {
    public static void hook(XC_LoadPackage.LoadPackageParam lpparam) {
        XposedHelpers.findAndHookMethod(Thread.class, "getStackTrace", new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                super.afterHookedMethod(param);
                if (param.getResult() instanceof StackTraceElement[]) {
                    ArrayList<Object> result = new ArrayList<>();

                    StackTraceElement[] stackTraceElements = (StackTraceElement[]) param.getResult();
                    if (stackTraceElements != null && stackTraceElements.length > 0) {
                        for (StackTraceElement stackTraceElement : stackTraceElements) {
                            String className = stackTraceElement.getClassName();
                            if (filter(className)) result.add(stackTraceElement);
                        }
                    }
//                    stackTraceElements = result.toArray(new StackTraceElement[0]);

                    stackTraceElements = new StackTraceElement[result.size()];
                    for (int i = 0; i < result.size(); i++) {
                        stackTraceElements[i] = (StackTraceElement) result.get(i);
                    }
                    param.setResult(stackTraceElements);
                }
            }
        });

        XposedHelpers.findAndHookMethod(Throwable.class, "getStackTrace", new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                super.afterHookedMethod(param);
                if (param.getResult() instanceof StackTraceElement[]) {
                    ArrayList<Object> result = new ArrayList<>();

                    StackTraceElement[] stackTraceElements = (StackTraceElement[]) param.getResult();
                    if (stackTraceElements != null && stackTraceElements.length > 0) {
                        for (StackTraceElement stackTraceElement : stackTraceElements) {
                            String className = stackTraceElement.getClassName();
                            if (filter(className)) result.add(stackTraceElement);
                        }
                    }
//                    stackTraceElements = result.toArray(new StackTraceElement[0]);

                    stackTraceElements = new StackTraceElement[result.size()];
                    for (int i = 0; i < result.size(); i++) {
                        stackTraceElements[i] = (StackTraceElement) result.get(i);
                    }
                    param.setResult(stackTraceElements);
                }
            }
        });
    }

    private static boolean filter(String className) {
        return !className.contains("HookBridge") && !className.contains("lsposed") &&
               !className.contains("LSPHooker_");
    }
}
