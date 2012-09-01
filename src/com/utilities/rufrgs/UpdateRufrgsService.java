package com.utilities.rufrgs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.PendingIntent;
import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.text.Html;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.TextView;

public class UpdateRufrgsService extends Service {


	public void onStart(Intent intent, int startId) {
		
		AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this
				.getApplicationContext());

		int[] allWidgetIds = intent
				.getIntArrayExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS);

		ComponentName thisWidget = new ComponentName(getApplicationContext(),
				RufrgsWidget.class);
		//int[] allWidgetIds2 = appWidgetManager.getAppWidgetIds(thisWidget);
		
		for (int widgetId : allWidgetIds) {
			
			Log.d("HERE","UPDATING");

			RemoteViews remoteViews = new RemoteViews(this
					.getApplicationContext().getPackageName(),
					R.layout.old_widget);
			// Set the text
			
			//TextView tv = (TextView) findViewById(R.id.textView1);
	        try {
	        	URL url = new URL("http://rufrgs.heroku.com/cardapio/index");
	        	BufferedReader in = new BufferedReader(
	        	            new InputStreamReader(	
	        	            url.openStream()));

	        	String inputLine;
	        	String total = "";

	        	while ((inputLine = in.readLine()) != null)
	        	    total += inputLine;

	        	in.close();
	        	remoteViews.setTextViewText(R.id.textView1,Html.fromHtml(total));
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
