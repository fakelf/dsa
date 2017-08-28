package com.example.fda;


import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore.MediaColumns;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;



import android.R.raw;


public class Sanguo extends Activity
				implements MediaPlayer.OnPreparedListener,
				MediaPlayer.OnErrorListener,
				MediaPlayer.OnCompletionListener{

	
	Uri uri;
	MediaPlayer mper;
	Button btnplay,btnstop;
	Toast tos;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sanguo);
		
		uri=Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.tt);
		btnplay=(Button) findViewById(R.id.button1);
		btnstop=(Button) findViewById(R.id.button2);
		
		mper=new MediaPlayer();
		mper.setOnPreparedListener(this);
		mper.setOnErrorListener(this);
		mper.setOnCompletionListener(this);
		tos=Toast.makeText(this, "", Toast.LENGTH_SHORT);
		prepareMusic();
	}

	void prepareMusic(){
		
		btnplay.setEnabled(false);
		btnstop.setEnabled(false);
		try{
			mper.reset();
			mper.setDataSource(this,uri);
			mper.prepareAsync();
		}catch (Exception e){
			tos.setText("´íÎó"+e.toString());
			tos.show();
		}
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sanguo, menu);
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
	
	protected void onActivityResult(int reque,int resu,Intent data){
		super.onActivityResult(reque, resu, data);
		if(reque==Activity.RESULT_OK){
			uri=convertUri(data.getData());
			prepareMusic();;
		}
	}
	
	Uri convertUri(Uri uri){
		
		if(uri.toString().substring(0,7).equals("content")){
			String[] colname={MediaColumns.DATA};
			Cursor cursor=getContentResolver().query(uri, colname, null, null, null);
			cursor.moveToFirst();
			uri=Uri.parse("file://"+cursor.getString(0));
			cursor.close();
		}
		return uri;
	}
	
	public void onk(View view){
		if(mper.isPlaying()){
			;
		}
		else{
			mper.start();
			
			btnstop.setEnabled(true);
		}
	}

	@Override
	public void onCompletion(MediaPlayer mp) {
		mper.seekTo(0);
		
		btnstop.setEnabled(false);
		
	}

	@Override
	public boolean onError(MediaPlayer mp, int what, int extra) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onPrepared(MediaPlayer mp) {
		// TODO Auto-generated method stub
		btnplay.setEnabled(true);
	}
	
	public void onmpstop(View v){
		mper.pause();
		mper.seekTo(0);
		
		btnstop.setEnabled(false);
		Intent intent =new Intent(Sanguo.this,Waiter.class);
		startActivity(intent);
	}
	
	@Override
	protected void onPause(){
		super .onPause();
		if(mper.isPlaying()){
			mper.pause();
		}
	}
	
	@Override
	protected void onDestroy(){
		mper.release();
		super.onDestroy();
	}
	
	
	
	
	
	
	
}
