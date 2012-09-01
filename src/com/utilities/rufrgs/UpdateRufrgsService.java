package com.utilities.rufrgs;

import java.util.Calendar;
import java.util.Date;

import android.app.PendingIntent;
import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.os.IBinder;
import android.text.Html;
import android.util.Log;
import android.widget.RemoteViews;

public class UpdateRufrgsService extends Service {


	public void onStart(Intent intent, int startId) {
		
		AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this
				.getApplicationContext());

		int[] allWidgetIds = intent
				.getIntArrayExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS);

		//ComponentName thisWidget = new ComponentName(getApplicationContext(),
				//RufrgsWidget.class);
		//int[] allWidgetIds2 = appWidgetManager.getAppWidgetIds(thisWidget);
		
		for (int widgetId : allWidgetIds) {
			

			RemoteViews remoteViews = new RemoteViews(this
					.getApplicationContext().getPackageName(),
					R.layout.old_widget);
			// Set the text
			
			//TextView tv = (TextView) findViewById(R.id.textView1);
			
			LoadCardapio cardapio = new LoadCardapio(getApplicationContext());
			
	        Date now = new Date();
	        Calendar calendar = Calendar.getInstance();  
	        calendar.setTime(now);  
	        int week_day = calendar.get(Calendar.DAY_OF_WEEK) - 1;
	        if ((week_day == 0) || (week_day == 6)) {
	        	week_day = 1;
	        }
	        week_day--;

        	remoteViews.setTextViewText(R.id.textView1,Html.fromHtml(cardapio.getDay(week_day)));
//				
			/*Intent clickIntent = new Intent(this.getApplicationContext(),
					RufrgsWidget.class);

			clickIntent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
			clickIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS,
					allWidgetIds);
*/
	        Intent clickIntent = new Intent(getBaseContext(), RufrgsActivity.class);
	        PendingIntent pendingIntent = PendingIntent.getActivity(getBaseContext(), 0, clickIntent, 0);
	        
	        /*Intent clickIntent = new Intent("android.intent.action.MAIN");
	        Context context = getApplicationContext();
	        clickIntent.setComponent(new ComponentName(context.getPackageName(),RufrgsActivity.class.getName()));
	        clickIntent.addCategory("android.intent.category.LAUNCHER");
	        startActivity(clickIntent);*/
	        
			/*PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, clickIntent,
					PendingIntent.FLAG_UPDATE_CURRENT);*/
			remoteViews.setOnClickPendingIntent(R.id.textView1, pendingIntent);

			appWidgetManager.updateAppWidget(widgetId, remoteViews);
		}
		stopSelf();

		super.onStart(intent, startId);
	}
	
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}
