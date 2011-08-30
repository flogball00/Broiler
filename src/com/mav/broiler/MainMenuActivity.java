package com.mav.broiler;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class MainMenuActivity extends Activity implements OnClickListener{
	 private static final String TAG = "MainMenu";
	 Spinner spinner;
	 Object selection;
	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.main);
	        
	        spinner = (Spinner)findViewById(R.id.sensorSelectionSpinner);
	        
	        Button viewButton = (Button) findViewById(R.id.viewSensor);
	        viewButton.setOnClickListener(this);
	        Button viewAllButton = (Button) findViewById(R.id.viewAll);
	        viewAllButton.setOnClickListener(this);
	        
	        ArrayAdapter <CharSequence> adapter =
	        	  new ArrayAdapter <CharSequence> (this, android.R.layout.simple_spinner_item );
	        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	        
	        adapter.add("Beef");
	        adapter.add("Chicken");
	        adapter.add("Pork");
	        adapter.add("Shrimp");
	        adapter.add("Lobster");
	        adapter.add("Duck");
	        adapter.add("Frog");
	        adapter.add("Stoker");
	        
	        spinner.setAdapter(adapter);
	        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
	            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
	                selection = parent.getItemAtPosition(pos);
	                Log.d(TAG , " spinnerlistener");
	            }
	            public void onNothingSelected(AdapterView<?> parent) {
	            }
	        });
	        
	 }

	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		
			switch (view.getId()) {
			case R.id.viewSensor:
				Log.d(TAG , " clicked");
				Intent viewSensorIntent = new Intent(this, BroilerActivity.class);
				Bundle b = new Bundle();
				Log.d(TAG, selection.toString());
				b.putString("SensorID", selection.toString());
				viewSensorIntent.putExtras(b);
				startActivity(viewSensorIntent);
			break;
			case R.id.viewAll:
				//intent to view all activity
			break;
			}
			
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		getMenuInflater().inflate(R.menu.status_activity_options, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Intent intent;
		switch (item.getItemId()){
		case R.id.menu_prefs:
			//display the preference activity
			intent = new Intent(this, PrefsActivity.class);
			startActivity(intent);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
}
