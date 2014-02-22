package com.example.dynamiclocationfeed;

import java.util.ArrayList;

import android.os.Build;
import android.os.Bundle;
import android.annotation.TargetApi;
import android.app.Activity;
import android.util.Log;
import android.view.DragEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnDragListener;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class MainActivity extends Activity implements OnClickListener,OnScrollListener{
	
	private Button add;
	private ListView lv;
	
	String TAG="MainActivity";
	
	ArrayList<String> stringArray;
	private int mI;
	ArrayAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		add=(Button)findViewById(R.id.button1);
		lv=(ListView)findViewById(R.id.listView1);
		
		stringArray =new ArrayList<String>();
		
		for(int i=0;i<5;i++)
		{
			stringArray.add(String.valueOf(i));
			mI=i;
		}		
		
		adapter=new ArrayAdapter(getApplicationContext(),android.R.layout.simple_list_item_1 , stringArray);
		
		lv.setAdapter(adapter);
		lv.setStackFromBottom(true);
		
		lv.setOnScrollListener(this);
		add.setOnClickListener(this);

			
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
			mI++;
		
			stringArray.add(0, String.valueOf(mI));
			//stringArray.add(String.valueOf(mI));
			adapter.notifyDataSetChanged();
			lv.smoothScrollToPosition(0);
		
	}

	@Override
	public void onScroll(AbsListView arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		//Log.d(TAG,"first visble item:"+String.valueOf(arg1));
		//Log.d(TAG,"last visible item:" +String.valueOf(arg0.getLastVisiblePosition()));
		//Log.d(TAG,"total visble item:"+String.valueOf(arg2));
		//Log.d(TAG,"total adapter item:"+String.valueOf(arg3));
		
		if(arg0.getLastVisiblePosition()==arg3-1)
		{
			Log.d(TAG,"load more items");
			loadOldItems();
			
		}
		
	}

	@Override
	public void onScrollStateChanged(AbsListView arg0, int arg1) {
		// TODO Auto-generated method stub
		
		
	}
	
	
	private void loadOldItems()
	{
		if(mI<100)
		{
			mI++;
			stringArray.add(String.valueOf(mI));
			adapter.notifyDataSetChanged();
			lv.smoothScrollToPosition(stringArray.size()-1);
		}
		
	}

	

}
