package info.student;


import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class Add extends Activity {

	Button submit_button;
	Button reset_button;
	Intent in2;
	EditText ed_first_name;
    EditText ed_middle_name;
    EditText ed_last_name;
    EditText ed_father_name;
    EditText ed_mother_name;
    EditText ed_dob,ed_email_id,ed_contact_no;
    StudentDB db;
    static int focus=0;
    int day,month,year;
    Date date;
    int spinner_selected2=0,spinner_selected3=0;
    Spinner sp2,sp3;
    static ArrayList<storage> emailarr;
    static void getEmailarr(ArrayList<storage> emailarr)
    {
    	Add.emailarr=emailarr;
    }
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add);
		ed_first_name=(EditText)findViewById(R.id.ed1);
        ed_middle_name=(EditText)findViewById(R.id.ed2);
        ed_last_name=(EditText)findViewById(R.id.ed3);
        ed_father_name=(EditText)findViewById(R.id.ed4);
        ed_mother_name=(EditText)findViewById(R.id.ed5);
        ed_dob=(EditText)findViewById(R.id.ed6);
        ed_email_id=(EditText)findViewById(R.id.ed7);
        ed_contact_no=(EditText)findViewById(R.id.ed8);
		
		sp2 = (Spinner)findViewById(R.id.sp2);
		sp3 = (Spinner)findViewById(R.id.sp3);
	    
	    //Array Adapters for spinner listeners
		ArrayAdapter<String> adapter2=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item);
		adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		ArrayAdapter<String> adapter3=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item);
		adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		
	    adapter2.add("Select Degree");
		adapter2.add("B.E.");
		adapter2.add("M.E.");
		adapter2.add("M.B.A");
		adapter2.add("Ph.D");
	    sp2.setAdapter(adapter2);
	    sp2.setOnItemSelectedListener(spinner_listener2);
	    adapter3.add("Select Specialization");
		adapter3.add("Comp. Sc.");
		adapter3.add("Electronics");
		adapter3.add("Mechanical");
	    sp3.setAdapter(adapter3);
	    sp3.setOnItemSelectedListener(spinner_listener3);
	    submit_button = (Button)findViewById(R.id.sub);
		submit_button.setOnClickListener(listener);
		
		reset_button = (Button)findViewById(R.id.reset);
		reset_button.setOnClickListener(listener);
		
		 db=new StudentDB(this);
		 ed_dob.setOnFocusChangeListener(focus_listener);//for Date 		
		 Calendar c=Calendar.getInstance();
			day=c.get(Calendar.DAY_OF_MONTH);
			month=c.get(Calendar.MONTH);
			year=c.get(Calendar.YEAR);
			date=new Date(day);
	            date.setMonth(month);
	            date.setYear(year);
	}
	 protected Dialog onCreateDialog(int id)
	    {
	        switch (id) 
	        {
	        case 0:
	            return (new DatePickerDialog(this,mDateSetListener,year,month,day));
	        }
	        return null;
	    }
	 //Called when Date is set
	 DatePickerDialog.OnDateSetListener mDateSetListener =
	        new DatePickerDialog.OnDateSetListener() {

	            public void onDateSet(DatePicker view, int myear, 
	                                  int mmonth, int mday) {
	                //year = myear-1900;
	                year=myear;
	            	month = mmonth+1;
	                day = mday;
	                date.setDate(day);
	                date.setMonth(month);
	                date.setYear(year);	                
	                ed_dob.setText(new StringBuilder().append(day).append("-").append(month).append("-").append(year));
	            }
	        };
	 OnFocusChangeListener focus_listener=new OnFocusChangeListener() {
			
		
			public void onFocusChange(View v, boolean hasFocus) 
			{
				// TODO Auto-generated method stub
				if(focus==0)
				{
				//Toast.makeText(Add.this,"On focus",Toast.LENGTH_SHORT).show();
				focus=1;
				showDialog(0);
				
								
				}
				else
					focus=0;
			}
	 };
	OnClickListener listener=new OnClickListener()
	{
		public void onClick(View v)
        {
                
                switch(v.getId())
        {
               case R.id.sub:
            	   
            	 //Validations of fields filled     
        if(!(ed_first_name.getText().toString().equals("")))
            {
               	 if(!(ed_last_name.getText().toString().equals("")))
                	     {
                		        if(!(ed_father_name.getText().toString().equals("")))
                		          {
                		        	   if(!(ed_mother_name.getText().toString().equals("")))
                		        	          {
                                		        		       if(!(ed_dob.getText().toString().equals("")))
                		        		                      {
                                		        		    	   if(!(ed_email_id.getText().toString().equals("")))
                                		        		    	   { 
                                		        		    		   if(spinner_selected2>0)
                                		        		    		   {
                                		        		    			   if(spinner_selected3>0 || spinner_selected2==3)
                                		        		    			   {
                                		        		    				   if(!(spinner_selected3>0 && spinner_selected2==3))
                                		        		    				   {
                                		        		    					   Boolean b=true;
                                		        		    					   for(int j=0;j<Add.emailarr.size();j++)
                                		        		    					   {
                                		        		    						   if(ed_email_id.getText().toString().equals(Add.emailarr.get(j).value))
                                		        		    							   b=false;
                                		        		    					   }
                                		        		    					   if(b)
                                		        		    					   {
                                		        		       //	in2 = new Intent("submit.back");
                                		        		    						  // in2 = new Intent("android.intent.action.MAIN");
                                		        		    						   String no=null;
                                		        		       	if(ed_contact_no.getText().toString().equals(""))
                                		        		       	no="0";	
                                		        		       	else
                                		        		       		no=ed_contact_no.getText().toString();
                                		        		       	String degree=null,spec=null;
                                		        		       	switch(spinner_selected2)
                                		        		       	{
                                		        		       	case 1:
                                		        		       		degree="be";
                                		        		       		break;
                                		        		       	case 2:
                                		        		       		degree="me";
                                		        		       		break;
                                		        		       	case 3:
                                		        		       		degree="mba";
                                		        		       		break;
                                		        		       	case 4:
                                		        		       		degree="phd";
                                		        		       		break;
                                		        		       	}
                                		        		       	switch(spinner_selected3)
                                		        		       	{
                                		        		       	case 0:
                                		        		       		spec="mba";
                                		        		       		break;
                                		        		       	case 1:
                                		        		       		spec="comp";
                                		        		       		break;
                                		        		       	case 2:
                                		        		       		spec="elec";
                                		        		       		break;
                                		        		       	case 3:
                                		        		       		spec="mech";
                                		        		       		break;
                                		        		       	}
                                		        		       	if(degree.equals("mba"))
                                		        		       		spec="mba";
                                		        		       	db.open();
                                		        		       	db.insertInfo(ed_first_name.getText().toString(),
                                		        		       			ed_middle_name.getText().toString(),
                                		        		       			ed_last_name.getText().toString(),
                                		        		       			ed_father_name.getText().toString(),
                                		        		       			ed_mother_name.getText().toString(),
                                		        		       			day,
                                		        		       			month,
                                		        		       			year,
                                		        		       			ed_email_id.getText().toString(),
                                		        		       			Long.parseLong(no),degree,spec
                                		        		       			);
                                		        		       			//////For Image Dialog Box
                                		        		       	/*
                                		        		       	AlertDialog.Builder add = new AlertDialog.Builder(Add.this);
                                		                  		 
                                		                        // set the message to display
                                		                        add.setMessage("Take a Picture?");
                                		             
                                		                        // add a neutral button to the alert box and assign a click listener
                                		                        add.setPositiveButton("Yes", new DialogInterface.OnClickListener() 
                                		                        {
                                		             
                                		                            // click listener on the alert box
                                		                            public void onClick(DialogInterface arg0, int arg1) 
                                		                            {
                                		                                Take_pic.getDB(db);
                                		                                Intent in=new Intent("take_pic");
                                		                                startActivity(in);
                                		                            }
                                		                        });
                                		                        
                                		                        add.setNegativeButton("No", new DialogInterface.OnClickListener() 
                                		                        {
                                		             
                                		                            // click listener on the alert box
                                		                            public void onClick(DialogInterface arg0, int arg1) 
                                		                            {
                                		                                // the button was clicked
                                		                            	startActivity(in2);
                                		                               //goes to Start Activity
                                		                         	   
                                		                            }
                                		                        });
                                		             
                                		                        // show it
                                		                        add.show();
                                		                        */
                                		        		       	//////////For Image End
                                		        		       	//startActivity(in2);
                                		        		      
                                		        		       	setResult(100);
                                		        		       	finish();
                                		        		    				   }
                                		        		    					   else
                                		        		    						   Toast.makeText(Add.this,"User with this Email ID exists",Toast.LENGTH_SHORT).show();
                                		        		    				   }
                                		        		    				   else
                                		        		    					   Toast.makeText(Add.this,"MBA does not have a specialization",Toast.LENGTH_SHORT).show();
                                		        		    		   }
                                		        		    		   else
                                		        		    			   Toast.makeText(Add.this,"Enter Specialization",Toast.LENGTH_SHORT).show();
                                		        		    		   }
                                		        		    		   else
                                		        		    			   Toast.makeText(Add.this,"Enter Degree",Toast.LENGTH_SHORT).show();
                		        		                      }
                                		        		    	   else
                                		        		    		   Toast.makeText(Add.this,"Enter email id",Toast.LENGTH_SHORT).show();
                		        		                      }
                		        		       else
                		        		          Toast.makeText(Add.this,"Write Date of Birth",Toast.LENGTH_SHORT).show();
                		        		       
                		        	         }
                		        	   
           		        	        else
                		        	   Toast.makeText(Add.this,"Write Mother's Name",Toast.LENGTH_SHORT).show();
                		          }
                		        
                		     else 
                		         Toast.makeText(Add.this,"Write Father's Name",Toast.LENGTH_SHORT).show();
                		     }
                 
                	   else 
                	      Toast.makeText(Add.this,"Write Last Name",Toast.LENGTH_SHORT).show();
            } 
            
              else
                Toast.makeText(Add.this,"Write First Name",Toast.LENGTH_SHORT).show();
                break;
        
                 case R.id.reset:
                    
                    ed_first_name.setText("");
                    
                    ed_middle_name.setText(""); 
                    
                    ed_last_name.setText("");
                    
                    ed_father_name.setText("");
                    
                    ed_mother_name.setText("");
                    
                    ed_dob.setText("");
                    ed_email_id.setText("");
                    ed_contact_no.setText("");
                    break; 
	               }

              }
	};
///////////Spinner Listener 2
	OnItemSelectedListener spinner_listener2=new OnItemSelectedListener() {

		@Override
		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,long arg3) 
		{
			// TODO Auto-generated method stub
			//Toast.makeText(Start.this,"Spinner Clicked"+ arg3,(int) System.currentTimeMillis()).show();
			spinner_selected2=(int)arg3;
			if(spinner_selected2!=3)
				sp3.setEnabled(true);
			else
				sp3.setEnabled(false);
			//tw_listener.afterTextChanged(null);
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) 
		{
			// TODO Auto-generated method stub
			
		}
	};
	//////////
	////////////Spinner Listener 3
	OnItemSelectedListener spinner_listener3=new OnItemSelectedListener() {

		@Override
		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,long arg3) 
		{
			// TODO Auto-generated method stub
			//Toast.makeText(Start.this,"Spinner Clicked"+ arg3,(int) System.currentTimeMillis()).show();
			if(spinner_selected2!=3)
			spinner_selected3=(int)arg3;
			else
				spinner_selected3=0;
			//tw_listener.afterTextChanged(null);
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) 
		{
			// TODO Auto-generated method stub
			
		}
	};
	////////////
}
