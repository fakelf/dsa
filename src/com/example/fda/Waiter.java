package com.example.fda;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;



import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class Waiter extends Activity{

	SQLiteDatabase db;
	ListView lv;
	Button bu1,bu2,bu3,bu4;
	int[] x=new int[12];
	int flag1=1;
	int flag2=0;
	
	
	int[] orde=new int[12];
	TextView tx;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_waiter);
		bu1=(Button) findViewById(R.id.cuisines1);
		bu2=(Button) findViewById(R.id.cuisines2);
		bu3=(Button) findViewById(R.id.cuisines3);
		bu4=(Button) findViewById(R.id.cuisines4);
		tx=(TextView) findViewById(R.id.submit1);
		
		for(int h=0;h<12;h++)  {x[h]=0;orde[h]=0;}
		
		db=SQLiteDatabase.openOrCreateDatabase(getFilesDir().toString()+"/ttj11.db3", null);
  		String createSQL="create table t1(_id integer primary key autoincrement,dy,type,picture,price,flag,dis,wait)";
  		String searchSQL="select * from sqlite_master where name='t1'";
  		String getDataSQL="select * from t1";
  		Cursor cursor=db.rawQuery(searchSQL, null);
  		if(cursor.getCount()==0){
  			db.execSQL(createSQL);
  		}
  		cursor=db.rawQuery(getDataSQL, null);
  		int min=1;
		int max=12;
		
		String getDataSQL1="select * from t1 where _id>="+min+" and _id<="+max;
		Cursor cursor1=db.rawQuery(getDataSQL1, null);
		int count = cursor1.getCount();
		String[] dy = new String[count];
		String[] type = new String[count];
		int[] imageIds = new int[count];
		int[] price = new int[count];
		int[] flag = new int[count];
		int[] dis = new int[count];
		int[] wait = new int[count];
		
		
		
		if(count>0){
			int i=0;
			while(cursor1.moveToNext()){
				dy[i] = cursor1.getString(1);
				type[i] = cursor1.getString(2);
				imageIds[i] = cursor1.getInt(3);
				price[i] = cursor1.getInt(4);
				flag[i] = cursor1.getInt(5);
				dis[i] = cursor1.getInt(6);
				wait[i] = cursor1.getInt(7);
				i++;
			}
		}
		
		
		ArrayList<HashMap<String,Object>>users=new ArrayList<HashMap<String,Object>>();
		for(int i=0;i<dy.length;i++){
			HashMap<String,Object> user=new HashMap<String,Object>();
			user.put("img",imageIds[i]);
			user.put("username", type[i]);
			user.put("age",("价格: "+price[i]+"元"));
			users.add(user);
		}
		
		
		SimpleAdapter salmageltems1= new SimpleAdapter(this,
				users,
				R.layout.use,
				new String[]{"img","username","age"},
				new int[]{ R.id.img,R.id.name,R.id.age});
		lv=(ListView)findViewById(R.id.order1);
		lv.setAdapter(salmageltems1);
		
		final ListView list = (ListView) findViewById(R.id.order1);
		//-------------list.getItemAtPosition(1);
		// 为ListView的列表项的单击事件绑定事件监听器
		list.setOnItemClickListener(new OnItemClickListener()
		{
			// 第position项被单击时激发该方法
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
									int position, long id)
			{
				int or;
          
				if(flag1==1){
					return;
				}
				else{
					or=(flag1-2)*4+position;
					if(x[position]==0){
						if(orde[or]==0) orde[or]=1;
						else orde[or]=0;
					 view.setBackgroundColor(Color.parseColor("#bbbbbb"));
					 x[position]=1;
					}
					else{
						if(orde[or]==0) orde[or]=1;
						else orde[or]=0;
						view.setBackgroundColor(Color.parseColor("#ffffff"));
						 x[position]=0;
					}
				}	
			}
		});	
		
		lv.setOnItemLongClickListener(new OnItemLongClickListener(){  
            @Override  
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,  
                    int arg2, long arg3) {  
                // TODO Auto-generated method stub  
                // When clicked, show a toast with the TextView text  
            	Intent intent =new Intent(Waiter.this,Sanguo.class);
        		startActivity(intent); 
                return false;  
            }  
        });  
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.waiter, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void sanguo(View view){
		
		for(int i=0;i<4;i++) orde[i]=0;
		for(int i=0;i<12;i++) x[i]=0;
		flag1=2;
		bu2.setBackgroundColor(Color.parseColor("#ffffff"));
		bu2.setTextColor(Color.parseColor("#000000"));
		bu1.setBackgroundColor(Color.parseColor("#bbbbbb"));
		bu1.setTextColor(Color.parseColor("#ffffff"));
		bu3.setBackgroundColor(Color.parseColor("#bbbbbb"));
		bu3.setTextColor(Color.parseColor("#ffffff"));
		bu4.setBackgroundColor(Color.parseColor("#bbbbbb"));
		bu4.setTextColor(Color.parseColor("#ffffff"));
		int min=1;
		int max=4;
		String getDataSQL="select * from t1 where _id>="+min+" and _id<="+max;
		Cursor cursor=db.rawQuery(getDataSQL, null);
		int count = cursor.getCount();
		String[] dy = new String[count];
		String[] type = new String[count];
		int[] imageIds = new int[count];
		int[] price = new int[count];
		int[] flag = new int[count];
		int[] dis = new int[count];
		int[] wait = new int[count];
		
		if(count>0){//如果查询到符合条件的数据
			//创建几个数组，分别存储昵称、描述、头像和年龄
			int i=0;
			while(cursor.moveToNext()){
				dy[i] = cursor.getString(1);
				type[i] = cursor.getString(2);
				imageIds[i] = cursor.getInt(3);
				price[i] = cursor.getInt(4);
				flag[i] = cursor.getInt(5);
				dis[i] = cursor.getInt(6);
				wait[i] = cursor.getInt(7);
				i++;
			}
		}
		
		
		
		ArrayList<HashMap<String,Object>>users=new ArrayList<HashMap<String,Object>>();
		for(int i=0;i<dy.length;i++){
			HashMap<String,Object> user=new HashMap<String,Object>();
			user.put("img",imageIds[i]);
			user.put("username", type[i]);
			user.put("age",("价格: "+price[i]+""));
			users.add(user);
		}
		SimpleAdapter salmageltems1= new SimpleAdapter(this,
				users,
				R.layout.use,
				new String[]{"img","username","age"},
				new int[]{ R.id.img,R.id.name,R.id.age});
		lv=(ListView)findViewById(R.id.order1);
		lv.setAdapter(salmageltems1);
		/*
		View v = (View)lv.getChildAt(1);
		v.setBackgroundColor(Color.parseColor("#ff0000"));
		Log.v("test", "-------");*/
	}
	public void onb1(View view){
		
		flag1=1;
		bu1.setBackgroundColor(Color.parseColor("#ffffff"));
		bu1.setTextColor(Color.parseColor("#000000"));
		bu2.setBackgroundColor(Color.parseColor("#bbbbbb"));
		bu2.setTextColor(Color.parseColor("#ffffff"));
		bu3.setBackgroundColor(Color.parseColor("#bbbbbb"));
		bu3.setTextColor(Color.parseColor("#ffffff"));
		bu4.setBackgroundColor(Color.parseColor("#bbbbbb"));
		bu4.setTextColor(Color.parseColor("#ffffff"));
		int min=1;
		int max=12;
		String getDataSQL="select * from t1 where _id>="+min+" and _id<="+max;
		Cursor cursor=db.rawQuery(getDataSQL, null);
		int count = cursor.getCount();
		String[] dy = new String[count];
		String[] type = new String[count];
		int[] imageIds = new int[count];
		int[] price = new int[count];
		int[] flag = new int[count];
		int[] dis = new int[count];
		int[] wait = new int[count];
		
		if(count>0){//如果查询到符合条件的数据
			//创建几个数组，分别存储昵称、描述、头像和年龄
			int i=0;
			while(cursor.moveToNext()){
				dy[i] = cursor.getString(1);
				type[i] = cursor.getString(2);
				imageIds[i] = cursor.getInt(3);
				price[i] = cursor.getInt(4);
				flag[i] = cursor.getInt(5);
				dis[i] = cursor.getInt(6);
				wait[i] = cursor.getInt(7);
				i++;
			}
		}
		
		ArrayList<HashMap<String,Object>>users=new ArrayList<HashMap<String,Object>>();
		for(int i=0;i<dy.length;i++){
			HashMap<String,Object> user=new HashMap<String,Object>();
			user.put("img",imageIds[i]);
			user.put("username", type[i]);
			user.put("age",("价格: "+price[i]+"元"));
			users.add(user);
		}
		SimpleAdapter salmageltems1= new SimpleAdapter(this,
				users,
				R.layout.use,
				new String[]{"img","username","age"},
				new int[]{ R.id.img,R.id.name,R.id.age});
		lv=(ListView)findViewById(R.id.order1);
		lv.setAdapter(salmageltems1);		
	}
	public void onb3(View view){
	
		for(int i=4;i<8;i++) orde[i]=0;
		for(int i=0;i<12;i++) x[i]=0;
		flag1=3;
		bu3.setBackgroundColor(Color.parseColor("#ffffff"));
		bu3.setTextColor(Color.parseColor("#000000"));
		bu2.setBackgroundColor(Color.parseColor("#bbbbbb"));
		bu2.setTextColor(Color.parseColor("#ffffff"));
		bu1.setBackgroundColor(Color.parseColor("#bbbbbb"));
		bu1.setTextColor(Color.parseColor("#ffffff"));
		bu4.setBackgroundColor(Color.parseColor("#bbbbbb"));
		bu4.setTextColor(Color.parseColor("#ffffff"));
		int min=5;
		int max=8;
		String getDataSQL="select * from t1 where _id>="+min+" and _id<="+max;
		Cursor cursor=db.rawQuery(getDataSQL, null);
		int count = cursor.getCount();
		String[] dy = new String[count];
		String[] type = new String[count];
		int[] imageIds = new int[count];
		int[] price = new int[count];
		int[] flag = new int[count];
		int[] dis = new int[count];
		int[] wait = new int[count];
		
		if(count>0){//如果查询到符合条件的数据
			//创建几个数组，分别存储昵称、描述、头像和年龄
			int i=0;
			while(cursor.moveToNext()){
				dy[i] = cursor.getString(1);
				type[i] = cursor.getString(2);
				imageIds[i] = cursor.getInt(3);
				price[i] = cursor.getInt(4);
				flag[i] = cursor.getInt(5);
				dis[i] = cursor.getInt(6);
				wait[i] = cursor.getInt(7);
				i++;
			}
		}
		ArrayList<HashMap<String,Object>>users=new ArrayList<HashMap<String,Object>>();
		for(int i=0;i<dy.length;i++){
			HashMap<String,Object> user=new HashMap<String,Object>();
			user.put("img",imageIds[i]);
			user.put("username", type[i]);
			user.put("age",("价格: "+price[i]+"元"));
			users.add(user);
		}
		SimpleAdapter salmageltems1= new SimpleAdapter(this,
				users,
				R.layout.use,
				new String[]{"img","username","age"},
				new int[]{ R.id.img,R.id.name,R.id.age});
		lv=(ListView)findViewById(R.id.order1);
		lv.setAdapter(salmageltems1);
}

	public void onb4(View view){
	
		for(int i=8;i<12;i++) orde[i]=0;
		for(int i=0;i<12;i++) x[i]=0;
		flag1=4;
		bu4.setBackgroundColor(Color.parseColor("#ffffff"));
		bu4.setTextColor(Color.parseColor("#000000"));
		bu2.setBackgroundColor(Color.parseColor("#bbbbbb"));
		bu2.setTextColor(Color.parseColor("#ffffff"));
		bu3.setBackgroundColor(Color.parseColor("#bbbbbb"));
		bu3.setTextColor(Color.parseColor("#ffffff"));
		bu1.setBackgroundColor(Color.parseColor("#bbbbbb"));
		bu1.setTextColor(Color.parseColor("#ffffff"));
		int min=9;
		int max=12;
		String getDataSQL="select * from t1 where _id>="+min+" and _id<="+max;
		Cursor cursor=db.rawQuery(getDataSQL, null);
		int count = cursor.getCount();
		String[] dy = new String[count];
		String[] type = new String[count];
		int[] imageIds = new int[count];
		int[] price = new int[count];
		int[] flag = new int[count];
		int[] dis = new int[count];
		int[] wait = new int[count];
		
		if(count>0){//如果查询到符合条件的数据
			//创建几个数组，分别存储昵称、描述、头像和年龄
			int i=0;
			while(cursor.moveToNext()){
				dy[i] = cursor.getString(1);
				type[i] = cursor.getString(2);
				imageIds[i] = cursor.getInt(3);
				price[i] = cursor.getInt(4);
				flag[i] = cursor.getInt(5);
				dis[i] = cursor.getInt(6);
				wait[i] = cursor.getInt(7);
				i++;
			}
		}
		ArrayList<HashMap<String,Object>>users=new ArrayList<HashMap<String,Object>>();
		for(int i=0;i<dy.length;i++){
			HashMap<String,Object> user=new HashMap<String,Object>();
			user.put("img",imageIds[i]);
			user.put("username", type[i]);
			user.put("age",("价格: "+price[i]+"元"));
			users.add(user);
		}
		SimpleAdapter salmageltems1= new SimpleAdapter(this,
				users,
				R.layout.use,
				new String[]{"img","username","age"},
				new int[]{ R.id.img,R.id.name,R.id.age});
		lv=(ListView)findViewById(R.id.order1);
		lv.setAdapter(salmageltems1);
	}
	public void onb5(View view){
		int min=1;
		int max=12;
		String getDataSQL="select * from t1 where _id>="+min+" and _id<="+max;
		Cursor cursor=db.rawQuery(getDataSQL, null);
		int count = cursor.getCount();
		String[] dy = new String[count];
		String[] type = new String[count];
		int[] imageIds = new int[count];
		int[] price = new int[count];
		int[] flag = new int[count];
		int[] dis = new int[count];
		int[] wait = new int[count];
		
		if(count>0){//如果查询到符合条件的数据
			//创建几个数组，分别存储昵称、描述、头像和年龄
			int i=0;
			while(cursor.moveToNext()){
				dy[i] = cursor.getString(1);
				type[i] = cursor.getString(2);
				imageIds[i] = cursor.getInt(3);
				price[i] = cursor.getInt(4);
				flag[i] = cursor.getInt(5);
				dis[i] = cursor.getInt(6);
				wait[i] = cursor.getInt(7);
				i++;
			}
		}
		count=0;
	    for(int i=0;i<12;i++){
	    	if(orde[i]==1)
	    		count++;	
	    }
	    if(count==0){
	    	// 创建一个Toast提示信息
			Toast toast = Toast.makeText(Waiter.this
					, "请先点餐！"
					// 设置该Toast提示信息的持续时间
					, Toast.LENGTH_SHORT);
			toast.show();
	    	return;
	    }
	    String[] names = new String[count];
	    int[] ages = new int[count];
	    
	    count=0;
	    for(int i=0;i<12;i++){
	    	if(orde[i]==1)
	    	{
	    		names[count]=type[i];
	    		ages[count]=price[i];
	    		count++;
	    	}	
	    }
	    Bundle data = new Bundle();
		data.putSerializable("names", names);
		data.putSerializable("ages", ages);
		Intent intent =new Intent(Waiter.this,Men.class);
		intent.putExtras(data);
		startActivity(intent);
	}
	
}






