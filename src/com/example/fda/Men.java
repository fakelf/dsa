package com.example.fda;


import java.util.ArrayList;
import java.util.HashMap;



import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class Men extends Activity 
implements DialogInterface.OnClickListener{

	int po;
	int flag=3;
	int len;
	Button bt;
	String[] items = new String[] {
			"支付宝支付", "微信支付",
			"网银支付",
			"现金支付" };
	String[] names;
	String[] pr;
	int[] ages;
	TextView tx;
	ListView ls1;
	ListView ls2;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_men);
		
		tx=(TextView) findViewById(R.id.bottom1);
		Intent intent = getIntent();
		names = (String[]) intent.getSerializableExtra("names");
		ages = (int[]) intent.getSerializableExtra("ages");
		ls1=(ListView) findViewById(R.id.lvv);
		int prs;
		prs=0;
		bt=(Button) findViewById(R.id.submit1);
		
		len=ages.length;
		for(int i=0;i<ages.length;i++) prs+=ages[i];
		tx.setText("合计： "+prs+"元");
		
		ArrayList<HashMap<String,Object>>users=new ArrayList<HashMap<String,Object>>();
		for(int i=0;i<ages.length;i++){
			HashMap<String,Object> user=new HashMap<String,Object>();
			user.put("username", names[i]);
			user.put("age",(ages[i]+"元"));
			users.add(user);
		}
		SimpleAdapter salmageltems1= new SimpleAdapter(this,
				users,
				R.layout.user2,
				new String[]{"username","age"},
				new int[]{R.id.caiming,R.id.jiage});
		
		
		ls1.setAdapter(salmageltems1);
		
		
		ls1.setOnItemClickListener(new OnItemClickListener()
		{
			// 第position项被单击时激发该方法
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
									int position, long id)
			{
				po=position;
				
				flag=0;
				stdd();
				
			}
		});
		
	}
	
	public int stdd(){
		
		Builder builder = new Builder(this)
				// 设置对话框标题
				.setTitle("确定退菜么？")
				// 设置图标
				;
			// 为AlertDialog.Builder添加“确定”按钮
			setPositiveButton(builder);
			// 为AlertDialog.Builder添加“取消”按钮
			setNegativeButton(builder)
				.create()
				.show();
		return 1;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.men, menu);
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
	public void on1(View view){
		Intent intent = new Intent(Men.this,Waiter.class);
		startActivity(intent);
	}
	public void onb5(View view){
		flag=1;
		AlertDialog.Builder builder = new AlertDialog.Builder(this)
				// 设置对话框标题
				.setTitle("请选择支付方式：")
						// 设置图标
				.setIcon(R.drawable.bottom)
						// 设置单选列表项，默认选中第二项（索引为1）
				.setSingleChoiceItems(items, 1, new OnClickListener()
				{
					@Override
					public void onClick(DialogInterface dialog, int which)
					{
						//show.setText("你选中了《" + items[which] + "》");
					}
				});
			// 为AlertDialog.Builder添加“确定”按钮
			setPositiveButton(builder);
			// 为AlertDialog.Builder添加“取消”按钮
			setNegativeButton(builder)
				.create().show();
	}
	private AlertDialog.Builder setPositiveButton(
			AlertDialog.Builder builder)
	{
		// 调用setPositiveButton方法添加“确定”按钮
		return builder.setPositiveButton("确定", new OnClickListener()
		{

			@Override
			public void onClick(DialogInterface dialog, int which)
			{
				
				
				if(flag==1){
					int p=0;
					p=0;
					for(int i=0;i<len;i++) p+=ages[i];
					Toast toast = Toast.makeText(Men.this
							, "  共计支付"+p+"元！\n付款成功，请您用餐！"
							// 设置该Toast提示信息的持续时间
							, Toast.LENGTH_LONG);
					Intent intent =new Intent(Men.this,Waiter.class);
					startActivity(intent);
					toast.show();
					flag=3;
			    }
				if(flag==0){
					
					;///++++++++++++++++++++++++=
					shuxin();
					flag=3;
				}
			}
		});
	}
	
	public void shuxin(){
		int prss;
		prss=0;
		for(int i=po;i<len-1;i++){
			names[i]=names[i+1];
			ages[i]=ages[i+1];
		}
		len=len-1;
		
		
		for(int i=0;i<len;i++) prss+=ages[i];
		tx.setText("合计： "+prss+"元");
		
		ArrayList<HashMap<String,Object>>users=new ArrayList<HashMap<String,Object>>();
		for(int i=0;i<len;i++){
			HashMap<String,Object> user=new HashMap<String,Object>();
			user.put("username", names[i]);
			user.put("age",(ages[i]+"元"));
			users.add(user);
		}
		SimpleAdapter salmageltems1= new SimpleAdapter(this,
				users,
				R.layout.user2,
				new String[]{"username","age"},
				new int[]{R.id.caiming,R.id.jiage});
		ls1.setAdapter(salmageltems1);
	}
	
	
	private AlertDialog.Builder setNegativeButton(
			AlertDialog.Builder builder)
	{
		// 调用setNegativeButton方法添加“取消”按钮
		return builder.setNegativeButton("取消", new OnClickListener()
		{
			@Override
			public void onClick(DialogInterface dialog, int which)
			{
				if(flag==1){
					Toast toast = Toast.makeText(Men.this
							, "  付款未成功元！\n请您重新点餐！"
							// 设置该Toast提示信息的持续时间
							, Toast.LENGTH_LONG);
					Intent intent =new Intent(Men.this,Waiter.class);
					startActivity(intent);
					toast.show();;
					flag=3;
					}
			}
		});
	}
	@Override
	public void onClick(DialogInterface dialog, int which) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
