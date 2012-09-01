package com.utilities.rufrgs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.net.URL;

import android.R.string;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.view.View;
import android.widget.RemoteViews;
import android.widget.TabHost;
import android.widget.TabHost.TabContentFactory;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

public class RufrgsActivity extends Activity {

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	
    	//String arrDays[] = {"Segunda", "Terça", "Quarta", "Quinta", "Sexta"};
    	//int arrIds[] = {R.id.textView1, R.id.textView2, R.id.textView3, R.id.textView4, R.id.textView5};
    	//int tabs[] = {R.id.tab1, R.id.tab2, R.id.tab3, R.id.tab4, R.id.tab5};
    	//int tabs[] = new int[5];
    	
    	LoadCardapio cardapio = new LoadCardapio(getApplicationContext());
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        ViewPager viewPager = (ViewPager) findViewById(R.id.pageslider);
 
        PageSlider ps = new PageSlider(this,cardapio);
        viewPager.setAdapter(ps);
 
 /*       
        TextView tv = (TextView) findViewById(R.id.textView1);
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
        	tv.setText(Html.fromHtml(total));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
    }
}