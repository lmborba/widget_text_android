package com.utilities.rufrgs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

public class RufrgsActivity extends Activity {

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
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
		}
    }
}