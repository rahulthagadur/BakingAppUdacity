package com.example.thagadur.bakingappudacity.utils;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;


public class MyActivity {
    public static void launch(Context context, Class<?> cls) {
        Intent intent = new Intent(context, cls);
        context.startActivity(intent);
    }

    public static void launchWithBundle(Context context, Class<?> cls, Bundle bundle) {
        Intent intent = new Intent(context, cls);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    public static void launchClearStack(Context context, Class<?> cls) {
        Intent intent = new Intent(context, cls);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }


}
