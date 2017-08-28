package com.example.fda;



import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.method.KeyListener;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Switch;

public class MainActivity extends Activity {

	int i=0;//switch�ı��0Ϊǰ̨��
	SQLiteDatabase db;
	Switch wlan=null; 
	EditText name;
	EditText pass;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_main);
		Button b=(Button)findViewById(R.id.button1);
		name=(EditText) findViewById(R.id.editText1);
		pass=(EditText) findViewById(R.id.editText2);
		wlan=(Switch)findViewById(R.id.switch1);
		
		
		db=SQLiteDatabase.openOrCreateDatabase(getFilesDir().toString()+"/ttj11.db3", null);
  		String createSQL="create table t1(_id integer primary key autoincrement,dy,type,picture,price,flag,dis,wait)";
  		String searchSQL="select * from sqlite_master where name='t1'";
  		String getDataSQL="select * from t1";
  		Cursor cursor=db.rawQuery(searchSQL, null);
  		if(cursor.getCount()==0){
  			db.execSQL(createSQL);
  		}
  		cursor=db.rawQuery(getDataSQL, null);
  		if(cursor.getCount()==0){
			String insertSQL="insert into t1 values(null,?,?,?,?,?,?,?)";
			db.execSQL(insertSQL,new Object[]{"����","�������",new Integer(R.drawable.p1),new Integer(50),new Integer(0),new Integer(10),new Integer(10)});
			db.execSQL(insertSQL,new Object[]{"����","��÷��",new Integer(R.drawable.p2),new Integer(20),new Integer(1),new Integer(10),new Integer(10)});
			db.execSQL(insertSQL,new Object[]{"����","�����",new Integer(R.drawable.p3),new Integer(30),new Integer(1),new Integer(10),new Integer(10)});
			db.execSQL(insertSQL,new Object[]{"����","�������㼦",new Integer(R.drawable.p4),new Integer(40),new Integer(0),new Integer(10),new Integer(10)});
			db.execSQL(insertSQL,new Object[]{"����","����Ϻ��",new Integer(R.drawable.p5),new Integer(60),new Integer(1),new Integer(10),new Integer(10)});
			db.execSQL(insertSQL,new Object[]{"����","������",new Integer(R.drawable.p6),new Integer(88),new Integer(1),new Integer(10),new Integer(10)});
			db.execSQL(insertSQL,new Object[]{"����","��β��",new Integer(R.drawable.p7),new Integer(99),new Integer(1),new Integer(10),new Integer(10)});
			db.execSQL(insertSQL,new Object[]{"����","ӣ�ұ���",new Integer(R.drawable.p8),new Integer(34),new Integer(0),new Integer(10),new Integer(10)});
			db.execSQL(insertSQL,new Object[]{"����","�����",new Integer(R.drawable.p9),new Integer(10),new Integer(0),new Integer(10),new Integer(10)});
			db.execSQL(insertSQL,new Object[]{"����","���ֽ��",new Integer(R.drawable.p10),new Integer(77),new Integer(0),new Integer(10),new Integer(10)});
			db.execSQL(insertSQL,new Object[]{"����","һƷ����",new Integer(R.drawable.p11),new Integer(28),new Integer(1),new Integer(10),new Integer(10)});
			db.execSQL(insertSQL,new Object[]{"����","��������",new Integer(R.drawable.p12),new Integer(55),new Integer(1),new Integer(10),new Integer(10)});
  		}
		
		
		
		
		/*��ΪSwitch����̳���CompoundButton���ڴ����п���ͨ��ʵ��CompoundButton.OnCheckedChangeListener�ӿڣ��� 
        ʵ�����ڲ����onCheckedChanged������״̬�仯��*/  
        wlan.setOnCheckedChangeListener(new OnCheckedChangeListener() {
        
			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
					if(i==0) i=1;
					else if(i==1) i=0;
				
			}
		});
        
            //ȡ�û򴴽����ݿ�
      		
        
        
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	
	public void onclick(View view){
		if(i==0)
		{
			Intent intent =new Intent(MainActivity.this,Waiter.class);
			startActivity(intent);
		}
		else if(i==1){
			Intent intent =new Intent(MainActivity.this,Waiter.class);
			startActivity(intent);
		}
		
	}
}
