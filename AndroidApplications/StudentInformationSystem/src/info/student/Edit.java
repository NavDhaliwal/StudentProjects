package info.student;

import java.sql.Date;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;
public class Edit extends Activity {
     
	
	EditText ed_first_name;
    EditText ed_middle_name;
    EditText ed_last_name;
    EditText ed_father_name;
    EditText ed_mother_name;
    EditText ed_dob,ed_email_id,ed_contact_no;
    Date date;
    int id;
    int spinner_selected2=-1,spinner_selected3=-1;
    String fname,lname,mname,fa_name,
    mo_name,email,contact_no,degree,spec;
    int day,month,year;
    Spinner sp2,sp3;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.edit);
		sp2 = (Spinner)findViewById(R.id.sp2);
		sp3 = (Spinner)findViewById(R.id.sp3);
	    
	    
		ArrayAdapter<String> adapter2=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item);
		adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		ArrayAdapter<String> adapter3=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item);
		adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		
	   
		adapter2.add("B.E.");
		adapter2.add("M.E.");
		adapter2.add("M.B.A");
		adapter2.add("Ph.D");
	    sp2.setAdapter(adapter2);
	    sp2.setOnItemSelectedListener(spinner_listener2);	   
		adapter3.add("Comp. Sc.");
		adapter3.add("Electronics");
		adapter3.add("Mechanical");
	    sp3.setAdapter(adapter3);
	    sp3.setOnItemSelectedListener(spinner_listener3);
		ed_first_name=(EditText)findViewById(R.id.ed1);
		ed_middle_name=(EditText)findViewById(R.id.ed2);
		ed_last_name=(EditText)findViewById(R.id.ed3);
		ed_father_name=(EditText)findViewById(R.id.ed4);
		ed_mother_name=(EditText)findViewById(R.id.ed5);
		ed_dob=(EditText)findViewById(R.id.ed6);
		ed_email_id=(EditText)findViewById(R.id.ed7);
		ed_contact_no=(EditText)findViewById(R.id.ed8);
		//Getting extra values passed from Show1
		fname=getIntent().getStringExtra("fname");
		mname=getIntent().getStringExtra("mname");
		lname=getIntent().getStringExtra("lname");
		fa_name=getIntent().getStringExtra("fa_name");
		mo_name=getIntent().getStringExtra("mo_name");
		day=getIntent().getIntExtra("day",-11);
		month=getIntent().getIntExtra("month",-11);
		year=getIntent().getIntExtra("year",-11);
		email=getIntent().getStringExtra("email");
		contact_no=getIntent().getStringExtra("contact_no");
		degree=getIntent().getStringExtra("degree");
		spec=getIntent().getStringExtra("spec");
		id=getIntent().getIntExtra("id",-11);
		if(degree.equals("be"))
		{
			sp2.setSelection(0);
			spinner_selected2=0;
		}
		if(degree.equals("me"))
		{
			sp2.setSelection(1);
			spinner_selected2=1;
		}
		if(degree.equals("mba"))
		{
			sp2.setSelection(2);
			spinner_selected2=2;
			sp3.setEnabled(false);
		}
		if(degree.equals("phd"))
		{
			sp2.setSelection(3);
			spinner_selected2=3;
		}
		if(spec.equals("comp"))
		{
			sp3.setSelection(0);
			spinner_selected3=0;
		}
		if(spec.equals("elec"))
		{
			sp3.setSelection(1);
			spinner_selected3=1;
		}
		if(spec.equals("mech"))
		{
			sp3.setSelection(2);
			spinner_selected3=2;
		}
			ed_first_name.setText(fname);
		ed_middle_name.setText(mname);
		ed_last_name.setText(lname);
		ed_father_name.setText(fa_name);
		ed_mother_name.setText(mo_name);
		ed_dob.setText(new StringBuilder().append(day).append("-").append(month).append("-").append(year));
		ed_email_id.setText(email);
		ed_contact_no.setText(contact_no);
		
		
		ed_dob.setOnClickListener(lis);
		date=new Date(day);
		 
	}
	///////DATE
	 protected Dialog onCreateDialog(int id)
	    {
	        switch(id) 
	        {
	        case 0:{
	        	Log.i("year", Integer.toString(year));
	        	Log.i("month", Integer.toString(month));
	        	Log.i("day", Integer.toString(day));
	            return (new DatePickerDialog(this,mDateSetListener,year,month-1,day));
	        }
	        }
	        return null;
	    }
	 DatePickerDialog.OnDateSetListener mDateSetListener =
	        new DatePickerDialog.OnDateSetListener() {

	            public void onDateSet(DatePicker view, int myear, 
	                                  int mmonth, int mday) {
	                //year = myear-1900;
	                year=myear;
	            	month = mmonth+1;
	                day = mday;
	                date.setDate((int)day);
	                date.setMonth((int)month);
	                date.setYear((int)year);
	                //ed_dob.setText(date.toString());	                
	                ed_dob.setText(new StringBuilder().append(day).append("-").append(month).append("-").append(year));
	            }
	        };
	///////END DATE
          OnClickListener lis = new OnClickListener() {
			
			@Override
			
			public void onClick(View v) 
			{
				if(v.getId()==R.id.ed6)//if Date edit text is clicked
				{
					showDialog(0);
				}
				
			}
		};
//////////Spinner Listener 2
		OnItemSelectedListener spinner_listener2=new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,long arg3) 
			{
				// TODO Auto-generated method stub
				//Toast.makeText(Start.this,"Spinner Clicked"+ arg3,(int) System.currentTimeMillis()).show();
				spinner_selected2=(int)arg3;
				if(spinner_selected2!=2)
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
				if(spinner_selected2!=2)
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
		@Override
		public boolean onCreateOptionsMenu(Menu menu) {
		    MenuInflater inflater = getMenuInflater();
		    inflater.inflate(R.menu.menu_edit, menu);
		    return true;
		}
		@Override
		public boolean onOptionsItemSelected(MenuItem item) {
		    switch (item.getItemId()) {
		        case R.id.edit_student:     
		        	fname=ed_first_name.getText().toString();
					mname=ed_middle_name.getText().toString();
					lname=ed_last_name.getText().toString();
					fa_name=ed_father_name.getText().toString();
					mo_name=ed_mother_name.getText().toString();
					email=ed_email_id.getText().toString();
					contact_no=ed_contact_no.getText().toString();
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
	          		        		    		   if(spinner_selected2>=0)
	          		        		    		   {
	          		        		    			   if(spinner_selected3>=0 || spinner_selected2==2)
	          		        		    			   {
	          		        		    				 int count=0;
	  		        		    					   for(int j=0;j<Add.emailarr.size();j++)
	  		        		    					   {
	  		        		    						   
	  		        		    						   if(id!=Add.emailarr.get(j).key && ed_email_id.getText().toString().equals(Add.emailarr.get(j).value))
	  		        		    							   count++;
	  		        		    					   }
	  		        		    					   if(count==0)
	  		        		    					   {
	          		        		    				   
	          		        		       	
	          		        		       	String no=null;
	          		        		       	if(ed_contact_no.getText().toString().equals(""))
	          		        		       	{
	          		        		       		no="0";	
	          		        		       		contact_no=no;
	          		        		       	}
	          		        		       	
	          		        		       	else
	          		        		       		no=ed_contact_no.getText().toString();
	          		        		       	switch(spinner_selected2)
	          		        		       	{
	          		        		       	case 0:
	          		        		       		degree="be";
	          		        		       		break;
	          		        		       	case 1:
	          		        		       		degree="me";
	          		        		       		break;
	          		        		       	case 2:
	          		        		       		degree="mba";
	          		        		       		break;
	          		        		       	case 3:
	          		        		       		degree="phd";
	          		        		       		break;
	          		        		       	}
	          		        		       	switch(spinner_selected3)
	          		        		       	{
	          		        		       	case 0:
	          		        		       		spec="comp";
	          		        		       		break;
	          		        		       	case 1:
	          		        		       		spec="elec";
	          		        		       		break;
	          		        		       	case 2:
	          		        		       		spec="mech";
	          		        		       		break;
	          		        		       	}
	          		        		      if(degree.equals("mba"))
	      		        		       		spec="mba";   
	          		        		    //Intent in = new Intent("back.start");
	           		        		    	Show1.db.updateInfo(id,fname,mname,lname,fa_name,mo_name,day,month,year,email,Long.parseLong(contact_no),degree,spec);
	           		        		    	Toast.makeText(Edit.this,"Entry Updated",(int)System.currentTimeMillis()).show();
	           		     		//startActivity(in);
	           		        		    	setResult(200);
	           		        		    	finish();
	  		        		    					   }
	  		        		    					 else
			        		    						   Toast.makeText(Edit.this,"User with this Email ID exists",Toast.LENGTH_SHORT).show();
	          		        		    				  
	          		        		    					
	          		        		    		   }
	          		        		    		   else
	          		        		    			   Toast.makeText(Edit.this,"Enter Specialization",Toast.LENGTH_SHORT).show();
	          		        		    		   }
	          		        		    		   else
	          		        		    			   Toast.makeText(Edit.this,"Enter Degree",Toast.LENGTH_SHORT).show();
		        		                      }
	          		        		    	   else
	          		        		    		   Toast.makeText(Edit.this,"Enter email id",Toast.LENGTH_SHORT).show();
	                               		     		
	               		        		                      }
	               		        		       else
	               		        		          Toast.makeText(Edit.this,"Write Date of Birth",Toast.LENGTH_SHORT).show();
	               		        		       
	               		        	         }
	               		        	   
	          		        	        else
	               		        	   Toast.makeText(Edit.this,"Write Mother's Name",Toast.LENGTH_SHORT).show();
	               		          }
	               		        
	               		     else 
	               		         Toast.makeText(Edit.this,"Write Father's Name",Toast.LENGTH_SHORT).show();
	               		     }
	                
	               	   else 
	               	      Toast.makeText(Edit.this,"Write Last Name",Toast.LENGTH_SHORT).show();
	           }  
	           
	             else
	               Toast.makeText(Edit.this,"Write First Name",Toast.LENGTH_SHORT).show();
		                            break;
		      
		                            
		    }
		    return true;
		}
}
