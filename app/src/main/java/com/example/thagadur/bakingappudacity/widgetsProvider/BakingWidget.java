package com.example.thagadur.bakingappudacity.widgetsProvider;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.example.thagadur.bakingappudacity.R;

import java.util.ArrayList;

import static com.example.thagadur.bakingappudacity.widgetsProvider.UpdateBakingService.FROM_ACTIVITY_INGREDIENTS_LIST;

public class BakingWidget extends AppWidgetProvider {

    static ArrayList<String> ingredientsList = new ArrayList<>();

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_grid_view);
        Intent intent = new Intent(context, BakingWidgetService.class);
        views.setRemoteAdapter(R.id.widget_grid_view, intent);

        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    public static void updateBakingWidgets(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {

    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(context, BakingWidget.class));

        final String action = intent.getAction();

        if (action.equals("android.appwidget.action.APPWIDGET_UPDATE2")) {
            ingredientsList = intent.getExtras().getStringArrayList(FROM_ACTIVITY_INGREDIENTS_LIST);
            appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds, R.id.widget_grid_view);

            BakingWidget.updateBakingWidgets(context, appWidgetManager, appWidgetIds);
            super.onReceive(context, intent);
        }
    }
}

