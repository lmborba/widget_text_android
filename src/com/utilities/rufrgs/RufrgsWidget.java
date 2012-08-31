package com.utilities.rufrgs;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.RemoteViews;

public class RufrgsWidget extends AppWidgetProvider {
	
	public void onUpdate(Context context, AppWidgetManager appMan, int [] appWidIds)
	{
		
		Intent intent = new Intent(context.getApplicationContext(),
									UpdateRufrgsService.class);
		intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidIds);

		// Update the widgets via the service
		context.startService(intent);
	}
/*
	private void updateAppWidget(Context context, AppWidgetManager appMan,
			int appWidgetId) {
		
		service(Context context, AppWidgetManager appMan,
				int appWidgetId, String titlePrefix);
		
		RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.main);
		views.setTextViewText(R.id.textView1, "SUPERTESTE DO MOMENTO");
		appMan.updateAppWidget(appWidgetId, views);
		
	}*/

}
