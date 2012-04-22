package example.android.startservicesample2;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.widget.Toast;

public class StartServiceSample2Service extends Service {

	// Timer object
	private Timer timer = null;
	
	// elapsed time
	private int countTime;
	
	// end time
	private int stopTime;
	
	
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}//public IBinder onBind(Intent arg0)
	
	// onCreate
	@Override
	public  void onCreate() {
		super.onCreate();
		
		// toast
		Toast.makeText(this, "サービスを起動します", Toast.LENGTH_SHORT).show();
		
		// initialize the timer and the elapsed time
		timer = new Timer();
		countTime = 0;
		
	}//onCreate()
	
	// onStart
	public  void onStart(Intent intent, int startId) {
		super.onStart(intent, startId);
		
		// toast
		Toast.makeText(this, "サービスを開始します", Toast.LENGTH_SHORT).show();
		
		// set the timer
		timer.schedule(task, 10000, 10000);
		
		// get the end time
		Bundle bundle = intent.getExtras();
		stopTime = Integer.parseInt(bundle.getString("STOPTIME"));
		
	}//onStart()
	
	// onDestroy
	@Override
	public  void onDestroy() {
		super.onDestroy();
		
		// toast
		Toast.makeText(this, "サービスを終了します", Toast.LENGTH_SHORT).show();
		
		// unset the timer
		timer.cancel();
		timer.purge();
		
	}//onDestroy()
	
	// gen a handler
	private Handler handler	= new Handler() {
		// show message
		public  void handleMessage(Message msg) {
			// toast
			Toast.makeText(StartServiceSample2Service.this, 
									(String) msg.obj, 
									Toast.LENGTH_SHORT).show();
		}//handleMessage()
	};//private Handler handler	= new Handler()
	
	// TimerTask object
	private TimerTask task	= new TimerTask() {
		@Override
		public  void run() {
			// Count up every 10 seconds
			countTime += 10;
			
			if (countTime / 60 == stopTime) {
				// stop the service
				stopSelf();
			} else {//if (countTime / 60 == stopTime)
				// send message to the handler
				handler.sendMessage(
						Message.obtain(handler, 
											0, 
											countTime / 60 + "分" + 
											countTime % 60 + "秒経過した！"));
			}//if (countTime / 60 == stopTime)
			
		}//run()
	};//private TimerTask object	= new TimerTask()
	
	
	
}
