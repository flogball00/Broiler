package com.mav.broiler;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class BroilerActivity extends Activity implements OnClickListener, OnSeekBarChangeListener {
    /** Called when the activity is first created. */
	 private static final String TAG = "BroilerActivity";
	 SeekBar mSeekBar;
	 EditText targetTemp, alarmHi, alarmLo;
	 RadioButton alarmOff, alarmTarget, alarmHiLo;
	 TextView alarmHigh, alarmLow;
	 Spinner sensorSpinner;
	 Object selection;
	 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sensor_settings);
        
        Log.d(TAG , "On Create");
        //gets the sensor info from previous activity
        Bundle b = this.getIntent().getExtras();
        String currentSensor = b.getString("SensorID");
        //prep sensor name
        TextView sensorName = (TextView)findViewById(R.id.sensorName);
        //prep temp
        TextView currentTemperature = (TextView)findViewById(R.id.currentTemparature);
        Typeface font = Typeface.createFromAsset(getAssets(), "digital-7.ttf");
        currentTemperature.setTypeface(font);
        
       
        
        //prepping radio group
        alarmOff = (RadioButton) findViewById(R.id.alarmOff);
        alarmTarget = (RadioButton) findViewById(R.id.alarmTarget);
        alarmHiLo = (RadioButton) findViewById(R.id.alarmHiLo);
        alarmOff.setOnClickListener(radio_listener);
        alarmTarget.setOnClickListener(radio_listener);
        alarmHiLo.setOnClickListener(radio_listener);
        
        //prepping update button
        Button button = (Button) findViewById(R.id.updateButton);
        button.setOnClickListener(this);
        
        //temp values for testing
        String currTemp = "253";
        //String sensorNameTemp = "beef";
        String targetTempString = "400";
        
        
        
        //setting sensorname and temperature info
        sensorName.setText(currentSensor);
        currentTemperature.setText(currTemp);
        
        //setting target temp seek bar
        mSeekBar = (SeekBar)findViewById(R.id.targetSeekBar);
        mSeekBar.setMax(600);  //currently hardcoded for maximum stoker temp
        mSeekBar.setProgress(Integer.parseInt(targetTempString));
        mSeekBar.setOnSeekBarChangeListener(this);
        //display the target temp
        targetTemp = (EditText)findViewById(R.id.targetTemp); 
        targetTemp.setText(targetTempString);
        
        //Hiding alarm hi/lo fields
        	//the edit texts
        alarmHi = (EditText)findViewById(R.id.et_alarm_hi);
        alarmLo = (EditText)findViewById(R.id.et_alarm_lo);
        alarmHi.setVisibility(View.INVISIBLE);
        alarmLo.setVisibility(View.INVISIBLE);
        	//the text labels
        alarmHigh = (TextView)findViewById(R.id.tv_alarm_hi);
        alarmLow = (TextView)findViewById(R.id.tv_alarm_lo);
        alarmHigh.setVisibility(View.INVISIBLE);
        alarmLow.setVisibility(View.INVISIBLE);
        
        Log.d(TAG , "PreSpinner");
        //sensor ID 
        sensorSpinner = (Spinner)findViewById(R.id.switchIDSpinner);
        ArrayAdapter <CharSequence> adapter =
        	  new ArrayAdapter <CharSequence> (this, android.R.layout.simple_spinner_item );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Log.d(TAG , "Mid Spinner");
        adapter.add("001");
        adapter.add("020");
        adapter.add("300");
        
        Log.d(TAG , "Mid Spinner2");
        sensorSpinner.setAdapter(adapter);
        Log.d(TAG , "Mid Spinner3");
        sensorSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
            	  Log.d(TAG , "Mid Spinner4");
            	selection = parent.getItemAtPosition(pos);
                Log.d(TAG , " spinnerlistener");
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        Log.d(TAG , "End Spinner");
    }
   
    private OnClickListener radio_listener = new OnClickListener() {
        public void onClick(View v) {
            // Perform action on clicks
            //RadioButton rb = (RadioButton) v;
           // Toast.makeText(BroilerActivity.this, rb.getText(), Toast.LENGTH_SHORT).show();
            
            switch (v.getId()){
            case R.id.alarmHiLo:
            //alarm Hi/Lo
            
            	 Log.d(TAG , "alarm Hi/Lo");
            	//ui component
            	setAlarmVisible();
            	break;
            case R.id.alarmOff:
				//alarm off stuff
            	Log.d(TAG , "alarm off");
            	setAlarmInvisible();
			break;
            case R.id.alarmTarget:
				//target allarm stuff
            	Log.d(TAG , "alarm target");
            	setAlarmInvisible();
			break;
            }
        }
    };

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		 Log.d(TAG , "Update Settings Clicked");
		 Toast.makeText(BroilerActivity.this, "Settings Updated", Toast.LENGTH_SHORT).show();
		
		//perform updating functionality
		
	}
	
	//sets hi/lo u/i fields invisible
	private void setAlarmInvisible(){
	    	alarmHi.setVisibility(View.INVISIBLE);
	        alarmLo.setVisibility(View.INVISIBLE);
	        alarmHigh.setVisibility(View.INVISIBLE);
	        alarmLow.setVisibility(View.INVISIBLE);
	        alarmHiLo.setText(R.string.alarm_hilo);
	}
	//sets hi/lo u/i fields visible
	private void setAlarmVisible(){
	    	alarmHiLo.setText(R.string.alarm);
	    	alarmHi.setVisibility(View.VISIBLE);
	    	alarmHigh.setVisibility(View.VISIBLE);
	    	alarmLo.setVisibility(View.VISIBLE);
	    	alarmLow.setVisibility(View.VISIBLE);
	}
	 
	@Override
	 public void onProgressChanged(SeekBar seekBar, int progress, boolean fromTouch) {
		 //Log.d(TAG , "seekbar changed");
        targetTemp.setText(String.valueOf(progress));
    }

    public void onStartTrackingTouch(SeekBar seekBar) {
       // mTrackingText.setText(getString(R.string.seekbar_tracking_on));
    }

    public void onStopTrackingTouch(SeekBar seekBar) {
       // mTrackingText.setText(getString(R.string.seekbar_tracking_off));
    }
}