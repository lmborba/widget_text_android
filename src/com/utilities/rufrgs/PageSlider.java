package com.utilities.rufrgs;

//import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.view.Gravity;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;


public class PageSlider extends PagerAdapter {
	
	Activity cxt;
	LoadCardapio cardapio;
	
	PageSlider(Activity act, LoadCardapio _cardapio) {
			cxt = act;
			cardapio = _cardapio;
	}

	String arrDays[] = {"Segunda", "Terça", "Quarta", "Quinta", "Sexta"};
	int ids[] = {R.id.day_cardapio1,R.id.day_cardapio2,R.id.day_cardapio3,R.id.day_cardapio4,R.id.day_cardapio5};
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return arrDays.length;
	}
	
	@Override
    public Object instantiateItem(View collection, int position) {
        ScrollView sc = new ScrollView(cxt);
        //sc.set'
        //sc.generateLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        sc.setFillViewport(true);
        TextView tv = new TextView(cxt);
        tv.setGravity(Gravity.CENTER);
        tv.setTextColor(Color.BLACK);
        //tv.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        tv.setText(Html.fromHtml(cardapio.getDay(position)));
        tv.setPadding(5,5,5,5);
        sc.addView(tv);
        tv.setId(ids[position]);
        ((ViewPager) collection).addView(sc);
        return sc;
    }
	
	@Override
    public void destroyItem(View collection, int position, Object view) {
        ((ViewPager) collection).removeView((ScrollView) view);
    }
	
	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		// TODO Auto-generated method stub
		return arg0==((ScrollView)arg1);
	}

}
