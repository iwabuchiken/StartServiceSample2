package example.android.startservicesample2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class StartServiceSample2Activity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.startservicesample2);
        
        // set a listener on the button
        Button startButton = (Button) findViewById(R.id.startButton);
        startButton.setOnClickListener(new StartButtonClickListener());
        
    }//public void onCreate(Bundle savedInstanceState)
    
    class StartButtonClickListener implements OnClickListener {

		@Override
		public void onClick(View arg0) {
			// TODO 自動生成されたメソッド・スタブ
			// gen an intent
			Intent intent = new Intent(StartServiceSample2Activity.this, StartServiceSample2Service.class);
			
			// set the end time on the intent
			EditText stopcount = (EditText) findViewById(R.id.stopcount);
			intent.putExtra("STOPTIME", stopcount.getText().toString());
			
			// start the service
			startService(intent);
			
		}//public void onClick(View arg0)
		
	}//class StartButtonClickListener implements OnClickListener
}