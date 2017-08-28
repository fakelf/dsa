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
			"֧����֧��", "΢��֧��",
			"����֧��",
			"�ֽ�֧��" };
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
		tx.setText("�ϼƣ� "+prs+"Ԫ");
		
		ArrayList<HashMap<String,Object>>users=new ArrayList<HashMap<String,Object>>();
		for(int i=0;i<ages.length;i++){
			HashMap<String,Object> user=new HashMap<String,Object>();
			user.put("username", names[i]);
			user.put("age",(ages[i]+"Ԫ"));
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
			// ��position�����ʱ�����÷���
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
				// ���öԻ������
				.setTitle("ȷ���˲�ô��")
				// ����ͼ��
				;
			// ΪAlertDialog.Builder��ӡ�ȷ������ť
			setPositiveButton(builder);
			// ΪAlertDialog.Builder��ӡ�ȡ������ť
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
				// ���öԻ������
				.setTitle("��ѡ��֧����ʽ��")
						// ����ͼ��
				.setIcon(R.drawable.bottom)
						// ���õ�ѡ�б��Ĭ��ѡ�еڶ������Ϊ1��
				.setSingleChoiceItems(items, 1, new OnClickListener()
				{
					@Override
					public void onClick(DialogInterface dialog, int which)
					{
						//show.setText("��ѡ���ˡ�" + items[which] + "��");
					}
				});
			// ΪAlertDialog.Builder��ӡ�ȷ������ť
			setPositiveButton(builder);
			// ΪAlertDialog.Builder��ӡ�ȡ������ť
			setNegativeButton(builder)
				.create().show();
	}
	private AlertDialog.Builder setPositiveButton(
			AlertDialog.Builder builder)
	{
		// ����setPositiveButton������ӡ�ȷ������ť
		return builder.setPositiveButton("ȷ��", new OnClickListener()
		{

			@Override
			public void onClick(DialogInterface dialog, int which)
			{
				
				
				if(flag==1){
					int p=0;
					p=0;
					for(int i=0;i<len;i++) p+=ages[i];
					Toast toast = Toast.makeText(Men.this
							, "  ����֧��"+p+"Ԫ��\n����ɹ��������òͣ�"
							// ���ø�Toast��ʾ��Ϣ�ĳ���ʱ��
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
		tx.setText("�ϼƣ� "+prss+"Ԫ");
		
		ArrayList<HashMap<String,Object>>users=new ArrayList<HashMap<String,Object>>();
		for(int i=0;i<len;i++){
			HashMap<String,Object> user=new HashMap<String,Object>();
			user.put("username", names[i]);
			user.put("age",(ages[i]+"Ԫ"));
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
		// ����setNegativeButton������ӡ�ȡ������ť
		return builder.setNegativeButton("ȡ��", new OnClickListener()
		{
			@Override
			public void onClick(DialogInterface dialog, int which)
			{
				if(flag==1){
					Toast toast = Toast.makeText(Men.this
							, "  ����δ�ɹ�Ԫ��\n�������µ�ͣ�"
							// ���ø�Toast��ʾ��Ϣ�ĳ���ʱ��
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
