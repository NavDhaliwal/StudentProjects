package info.student;

import java.util.ArrayList;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager.OnActivityResultListener;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
public class Start extends Activity {
	
	EditText et;
	ListView lv;
	static Bundle b;
	StudentDB db;	
	Spinner sp1,sp2,sp3;
	int spinner_selected1=0,spinner_selected2=0,spinner_selected3=0;
	String fname_search="";
	ArrayList<storage> fnamearr;
	ArrayList<storage> lnamearr;
	ArrayList<storage> emailarr;
	ArrayList<storage> fname_searched;
	ArrayList<storage> lname_searched;
	ArrayList<storage> email_searched;
	ArrayList<storage> degreearr;
	ArrayList<storage> specarr;
	myAdapter adapter;	
	ArrayList<Integer> id_original_list;
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.start);	
		b=savedInstanceState;
		
		
		sp1 = (Spinner)findViewById(R.id.sp1);
		sp2 = (Spinner)findViewById(R.id.sp2);
		sp3 = (Spinner)findViewById(R.id.sp3);
		//Adapters for spinners
	    ArrayAdapter<String> adapter1=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item);
		adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		
		ArrayAdapter<String> adapter2=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item);
		adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		
		ArrayAdapter<String> adapter3=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item);
		adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		
		adapter1.add("Name");
		adapter1.add("Email Id");
		//adapter1 attached to spinner object sp1
	    sp1.setAdapter(adapter1);
	    sp1.setOnItemSelectedListener(spinner_listener1);
	    
	    adapter2.add("All");
		adapter2.add("B.E.");
		adapter2.add("M.E.");
		adapter2.add("MBA");
		adapter2.add("Ph.D");
		//adapter2 attached to spinner object sp2
	    sp2.setAdapter(adapter2);
	    sp2.setOnItemSelectedListener(spinner_listener2);
	    
	    adapter3.add("All");
		adapter3.add("Comp. Sc.");
		adapter3.add("Electronics");
		adapter3.add("Mechanical");
		//adapter3 attached to spinner object sp3
	    sp3.setAdapter(adapter3);
	    sp3.setOnItemSelectedListener(spinner_listener3);
		//id_original_list stores database id of searched students
	    id_original_list=new ArrayList<Integer>();
	    //List View lv 
		lv = (ListView) findViewById(R.id.list);
		et = (EditText) findViewById(R.id.search);
		
		//Creating ArrayList Objects 
		fnamearr = new ArrayList<storage>();
		lnamearr = new ArrayList<storage>();
		emailarr = new ArrayList<storage>();
		degreearr = new ArrayList<storage>();
		specarr = new ArrayList<storage>();
		fname_searched = new ArrayList<storage>();
		lname_searched= new ArrayList<storage>();
		email_searched= new ArrayList<storage>();
		//db is an Object of StudentDB class		
		db= new StudentDB(this);
		//open the database
		db.open();
		Show1.getDB(db);
		//Creating Custom Adapter's myAdapter Object
		//Also adds elements to ArrayLists passed in Constructor
		adapter=new myAdapter(this,db,fnamearr,lnamearr,emailarr,degreearr,specarr);
		Add.getEmailarr(emailarr);//passing emailarr to Add class
		//Attach adapter to list view object lv
		lv.setAdapter(adapter);		
		lv.setOnItemClickListener(Listlistener);
		//puts all database id's in id_original_list
		for(int i=0;i<fnamearr.size();i++)
    	{
    		id_original_list.add(fnamearr.get(i).key);
    	}
		et.addTextChangedListener(tw_listener);
		Delete_multiple.delete_multiple_get(fnamearr,lnamearr);
		
	}
	//OnCreate Ends
	
	//////Text Changed Listener
	TextWatcher tw_listener=new TextWatcher() 
	{
		
		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void afterTextChanged(Editable s) 
		{
				                        	 
       	 fname_search=et.getText().toString();//gets the text in Edit Text View       
       	 fname_searched.clear();//clear all previous searched ArrayLists
       	 lname_searched.clear();
       	email_searched.clear();
       	 id_original_list.clear();
       	 if(!(fname_search.equals("")))//if Edit Text View not null
       	 {
       	 for(int i=0;i<fnamearr.size();i++)
       	 {
       		 if(spinner_selected1==0)//if search by name
       		 {
       		 if((fnamearr.get(i).value.toLowerCase()).startsWith(fname_search.toLowerCase()))//compares name of Edit Text and database entries 
       		 {
       			switch(spinner_selected2)//which degree is selected for search
       			   {
       				 case 0://all degrees searched
       					 switch(spinner_selected3)//which specialization is selected for search
       				      {
       					    case 0://all specializations searched
       			              fname_searched.add(fnamearr.get(i));      			
       			              id_original_list.add(fnamearr.get(i).key);			                        			 
       			              lname_searched.add(lnamearr.get(i));
       			              email_searched.add(emailarr.get(i));
       			              break;
       					 
       					    case 1://if degree is all and specialization is computer science
       						if(specarr.get(i).value.equals("comp"))
          					 {
          						fname_searched.add(fnamearr.get(i));      			
          	       			    id_original_list.add(fnamearr.get(i).key);			                        			 
          	       			    lname_searched.add(lnamearr.get(i));
          	       			    email_searched.add(emailarr.get(i));
          					 }
       						 break;
       					
       					    case 2://if degree is all and specialization is electronics
       						if(specarr.get(i).value.equals("elec"))
          					 {
          						fname_searched.add(fnamearr.get(i));      			
          	       			    id_original_list.add(fnamearr.get(i).key);			                        			 
          	       			    lname_searched.add(lnamearr.get(i));
          	       			    email_searched.add(emailarr.get(i));
          					 }
       						 break;
       					
       					    case 3://if degree is all and specialization is mechanical
       						if(specarr.get(i).value.equals("mech"))
          					 {
          						fname_searched.add(fnamearr.get(i));      			
          	       			    id_original_list.add(fnamearr.get(i).key);			                        			 
          	       			    lname_searched.add(lnamearr.get(i));
          	       			    email_searched.add(emailarr.get(i));
          					 }
       						 break;
       					 }
       			break;
       				 
       				 case 1://if degree is B.E.
       					 if(degreearr.get(i).value.equals("be"))
       					 {
       						switch(spinner_selected3)
          					 {
          					 case 0://if degree is B.E. and specialization is all
          			           fname_searched.add(fnamearr.get(i));      			
          			           id_original_list.add(fnamearr.get(i).key);			                        			 
          			           lname_searched.add(lnamearr.get(i));
          			           email_searched.add(emailarr.get(i));
          			        break;
          					
          					 case 1://if degree is B.E. and specialization is computer science
          						if(specarr.get(i).value.equals("comp"))
             					 {
             						fname_searched.add(fnamearr.get(i));      			
             	       		        id_original_list.add(fnamearr.get(i).key);			                        			 
             	       			    lname_searched.add(lnamearr.get(i));
             	       			    email_searched.add(emailarr.get(i));
             					 }
          						 break;
          					
          					 case 2://if degree is B.E. and specialization is electronics
          						if(specarr.get(i).value.equals("elec"))
             					 {
             						fname_searched.add(fnamearr.get(i));      			
             	       			    id_original_list.add(fnamearr.get(i).key);			                        			 
             	       			    lname_searched.add(lnamearr.get(i));
             	       			    email_searched.add(emailarr.get(i));
             					 }
          						 break;
          					
          					 case 3://if degree is B.E. and specialization is mechanical
          						if(specarr.get(i).value.equals("mech"))
             					 {
             						fname_searched.add(fnamearr.get(i));      			
             	       			    id_original_list.add(fnamearr.get(i).key);			                        			 
             	       			    lname_searched.add(lnamearr.get(i));
             	       			    email_searched.add(emailarr.get(i));
             					 }
          						 break;
          					 }
       					 }
       					 break;
       				
       				 case 2://if degree is M.E.
      					 if(degreearr.get(i).value.equals("me"))
      					 {
      						switch(spinner_selected3)
          					 {
          					 case 0://specialization is all
          			              fname_searched.add(fnamearr.get(i));      			
          			              id_original_list.add(fnamearr.get(i).key);			                        			 
          			              lname_searched.add(lnamearr.get(i));
          			              email_searched.add(emailarr.get(i));
          			         break;
          					 
          					 case 1://if degree is M.E. and specialization is computer science
          						if(specarr.get(i).value.equals("comp"))
             					 {
             						fname_searched.add(fnamearr.get(i));      			
             	       			    id_original_list.add(fnamearr.get(i).key);			                        			 
             	       			    lname_searched.add(lnamearr.get(i));
             	       			    email_searched.add(emailarr.get(i));
             					 }
          						 break;
          					
          					 case 2://if degree is M.E. and specialization is electronics
          						if(specarr.get(i).value.equals("elec"))
             					 {
             						fname_searched.add(fnamearr.get(i));      			
             	       			    id_original_list.add(fnamearr.get(i).key);			                        			 
             	       			    lname_searched.add(lnamearr.get(i));
             	       			    email_searched.add(emailarr.get(i));
             					 }
          						 break;
          					
          					 case 3://if degree is M.E. and specialization is mechanical
          						if(specarr.get(i).value.equals("mech"))
             					 {
             						fname_searched.add(fnamearr.get(i));      			
             	       			    id_original_list.add(fnamearr.get(i).key);			                        			 
             	       			    lname_searched.add(lnamearr.get(i));
             	       			    email_searched.add(emailarr.get(i));
             					 }
          						 break;
          					 }
      					 }
      					 break;
       				
       				 case 3://if degree is M.B.A.
      					 if(degreearr.get(i).value.equals("mba"))
      					 {
      						
          					 
          			              fname_searched.add(fnamearr.get(i));      			
          			              id_original_list.add(fnamearr.get(i).key);			                        			 
          			              lname_searched.add(lnamearr.get(i));
          			              email_searched.add(emailarr.get(i));
          			        
          					 
          					
          					
          					
          					
          					 
      					 }
      					 break;
      					 
       				case 4://If degree is Ph.D.
      					 if(degreearr.get(i).value.equals("phd"))
      					 {
      						switch(spinner_selected3)
          					 {
          					 case 0://specialization is all
          			           fname_searched.add(fnamearr.get(i));      			
          			           id_original_list.add(fnamearr.get(i).key);			                        			 
          			           lname_searched.add(lnamearr.get(i));
          			           email_searched.add(emailarr.get(i));
          			         break;
          					 
          					 case 1://if degree is Ph.D. and specialization is computer science
          						if(specarr.get(i).value.equals("comp"))
             					 {
             					fname_searched.add(fnamearr.get(i));      			
             	       			id_original_list.add(fnamearr.get(i).key);			                        			 
             	       			lname_searched.add(lnamearr.get(i));
             	       			email_searched.add(emailarr.get(i));
             					 }
          					 break;
          					
          					 case 2://if degree is Ph.D. and specialization is electronics
          						if(specarr.get(i).value.equals("elec"))
             					 {
             					fname_searched.add(fnamearr.get(i));      			
             	       		    id_original_list.add(fnamearr.get(i).key);			                        			 
             	       			lname_searched.add(lnamearr.get(i));
             	       			email_searched.add(emailarr.get(i));
             					 }
          						 break;
          					
          					 case 3://if degree is Ph.D. and specialization is mechanical
          						if(specarr.get(i).value.equals("mech"))
             					 {
             					fname_searched.add(fnamearr.get(i));      			
             	       			id_original_list.add(fnamearr.get(i).key);			                        			 
             	       			lname_searched.add(lnamearr.get(i));
             	       			email_searched.add(emailarr.get(i));
             					 }
          						 break;
          					 }
      					 }
      					 break;
       				 }
       		 }
       		 }
       		 if(spinner_selected1==1)//if email is selected
       		 {
       			 if((emailarr.get(i).value.toLowerCase()).startsWith(fname_search.toLowerCase()))//compares email id with that of database
       			 {
       				 switch(spinner_selected2)
       				 {
       					 
       				 case 0://if degree is all
       					 switch(spinner_selected3)
       					 {
       					 case 0://if specialization is all
       			              fname_searched.add(fnamearr.get(i));      			
       			              id_original_list.add(fnamearr.get(i).key);			                        			 
       			              lname_searched.add(lnamearr.get(i));
       			              email_searched.add(emailarr.get(i));
       			          break;
       					 
       					 case 1://if specialization is computer science
       						if(specarr.get(i).value.equals("comp"))
          					 {
          					 fname_searched.add(fnamearr.get(i));      			
          	       			 id_original_list.add(fnamearr.get(i).key);			                        			 
          	       			 lname_searched.add(lnamearr.get(i));
          	       			 email_searched.add(emailarr.get(i));
          					 }
       						 break;
       					
       					 case 2://if specialization is electronics
       						if(specarr.get(i).value.equals("elec"))
          					 {
          					 fname_searched.add(fnamearr.get(i));      			
          	       			 id_original_list.add(fnamearr.get(i).key);			                        			 
          	       			 lname_searched.add(lnamearr.get(i));
          	       			 email_searched.add(emailarr.get(i));
          					 }
       						 break;
       					case 3://if specialization is mechanical
       						if(specarr.get(i).value.equals("mech"))
          					 {
          					fname_searched.add(fnamearr.get(i));      			
          	       			id_original_list.add(fnamearr.get(i).key);			                        			 
          	       			lname_searched.add(lnamearr.get(i));
          	       			email_searched.add(emailarr.get(i));
          					 }
       						 break;
       					 }
       			break;
       				 case 1://If degree is B.E.
       					 if(degreearr.get(i).value.equals("be"))
       					 {
       						switch(spinner_selected3)
          					 {
          					 case 0://if specialization is all
          			 fname_searched.add(fnamearr.get(i));      			
          			 id_original_list.add(fnamearr.get(i).key);			                        			 
          			 lname_searched.add(lnamearr.get(i));
          			email_searched.add(emailarr.get(i));
          			break;
          					 case 1://if specialization is computer science
          						if(specarr.get(i).value.equals("comp"))
             					 {
             						fname_searched.add(fnamearr.get(i));      			
             	       			 id_original_list.add(fnamearr.get(i).key);			                        			 
             	       			 lname_searched.add(lnamearr.get(i));
             	       			email_searched.add(emailarr.get(i));
             					 }
          						 break;
          					case 2://if specialization is electronics
          						if(specarr.get(i).value.equals("elec"))
             					 {
             						fname_searched.add(fnamearr.get(i));      			
             	       			 id_original_list.add(fnamearr.get(i).key);			                        			 
             	       			 lname_searched.add(lnamearr.get(i));
             	       			email_searched.add(emailarr.get(i));
             					 }
          						 break;
          					case 3://if specialization is mechanical
          						if(specarr.get(i).value.equals("mech"))
             					 {
             						fname_searched.add(fnamearr.get(i));      			
             	       			 id_original_list.add(fnamearr.get(i).key);			                        			 
             	       			 lname_searched.add(lnamearr.get(i));
             	       			email_searched.add(emailarr.get(i));
             					 }
          						 break;
          					 }
       					 }
       					 break;
       				case 2://if degree is M.E.
      					 if(degreearr.get(i).value.equals("me"))
      					 {
      						switch(spinner_selected3)
          					 {
          					 case 0://if specialization is all
          			 fname_searched.add(fnamearr.get(i));      			
          			 id_original_list.add(fnamearr.get(i).key);			                        			 
          			 lname_searched.add(lnamearr.get(i));
          			email_searched.add(emailarr.get(i));
          			break;
          					 case 1://if specialization is computer science
          						if(specarr.get(i).value.equals("comp"))
             					 {
             						fname_searched.add(fnamearr.get(i));      			
             	       			 id_original_list.add(fnamearr.get(i).key);			                        			 
             	       			 lname_searched.add(lnamearr.get(i));
             	       			email_searched.add(emailarr.get(i));
             					 }
          						 break;
          					case 2://if specialization is electronics
          						if(specarr.get(i).value.equals("elec"))
             					 {
             						fname_searched.add(fnamearr.get(i));      			
             	       			 id_original_list.add(fnamearr.get(i).key);			                        			 
             	       			 lname_searched.add(lnamearr.get(i));
             	       			email_searched.add(emailarr.get(i));
             					 }
          						 break;
          					case 3://if specialization is mechanical
          						if(specarr.get(i).value.equals("mech"))
             					 {
             						fname_searched.add(fnamearr.get(i));      			
             	       			 id_original_list.add(fnamearr.get(i).key);			                        			 
             	       			 lname_searched.add(lnamearr.get(i));
             	       			email_searched.add(emailarr.get(i));
             					 }
          						 break;
          					 }
      					 }
      					 break;
       				case 3://if degree is M.B.A.
      					 if(degreearr.get(i).value.equals("mba"))
      					 {
      						
          			 fname_searched.add(fnamearr.get(i));      			
          			 id_original_list.add(fnamearr.get(i).key);			                        			 
          			 lname_searched.add(lnamearr.get(i));
          			email_searched.add(emailarr.get(i));
          			
          					 
          					
          					
          					 
      					 }
      					 break;
       				case 4://if degree is Ph.D.
      					 if(degreearr.get(i).value.equals("phd"))
      					 {
      						switch(spinner_selected3)
          					 {
          					 case 0://if specialization is all
          			 fname_searched.add(fnamearr.get(i));      			
          			 id_original_list.add(fnamearr.get(i).key);			                        			 
          			 lname_searched.add(lnamearr.get(i));
          			email_searched.add(emailarr.get(i));
          			break;
          					 case 1://if specialization is computer science
          						if(specarr.get(i).value.equals("comp"))
             					 {
             						fname_searched.add(fnamearr.get(i));      			
             	       			 id_original_list.add(fnamearr.get(i).key);			                        			 
             	       			 lname_searched.add(lnamearr.get(i));
             	       			email_searched.add(emailarr.get(i));
             					 }
          						 break;
          					case 2://if specialization is electronics
          						if(specarr.get(i).value.equals("elec"))
             					 {
             						fname_searched.add(fnamearr.get(i));      			
             	       			 id_original_list.add(fnamearr.get(i).key);			                        			 
             	       			 lname_searched.add(lnamearr.get(i));
             	       			email_searched.add(emailarr.get(i));
             					 }
          						 break;
          					case 3://if specialization is mechanical
          						if(specarr.get(i).value.equals("mech"))
             					 {
             						fname_searched.add(fnamearr.get(i));      			
             	       			 id_original_list.add(fnamearr.get(i).key);			                        			 
             	       			 lname_searched.add(lnamearr.get(i));
             	       			email_searched.add(emailarr.get(i));
             					 }
          						 break;
          					 }
      					 }
      					 break;
       				 }
       			 }
       		 }
       	 }
       	 //If no record found Dialog for "adding user" is opened
       	 /*
       	 if(fname_searched.isEmpty())
           	{
           		AlertDialog.Builder add = new AlertDialog.Builder(Start.this);
           		 
                   // set the message to display
                   add.setMessage("No record found.Add User?");
        
                   // add a neutral button to the alert box and assign a click listener
                   add.setNeutralButton("Add User", new DialogInterface.OnClickListener() 
                   {
        
                       // click listener on the alert box
                       public void onClick(DialogInterface arg0, int arg1) 
                       {
                           Intent in=new Intent("add");
                           startActivity(in);
                       }
                   });
                   
                   add.setNegativeButton("Back", new DialogInterface.OnClickListener() 
                   {
        
                       // click listener on the alert box
                       public void onClick(DialogInterface arg0, int arg1) 
                       {
                           // the button was clicked
                    	   if(fname_search.length()>1)
                          fname_search=fname_search.substring(0,fname_search.length()-1);
                    	   else
                    		   fname_search="";
                          et.setText(fname_search);                         
                    	   //Toast.makeText(getApplicationContext(), "Back button clicked", Toast.LENGTH_LONG).show();
                       }
                   });                
                   add.show();
           		
           	}
       	*/
       		//END FOR ADDING a User
       	
       	myAdapter.fnamearr=fname_searched;
       	myAdapter.lnamearr=lname_searched;
       	myAdapter.emailarr=email_searched;
       			 
       	 }
/////////If Edit Text Null
       	 else
       	 {
       		
               	for(int i=0;i<fnamearr.size();i++)
               	{
                switch(spinner_selected2)
  				 {
  					 
  				 case 0:
  					 switch(spinner_selected3)
  					 {
  					 case 0:
  			 fname_searched.add(fnamearr.get(i));      			
  			 id_original_list.add(fnamearr.get(i).key);			                        			 
  			 lname_searched.add(lnamearr.get(i));
  			email_searched.add(emailarr.get(i));
  			break;
  					 case 1:
  						if(specarr.get(i).value.equals("comp"))
     					 {
     						fname_searched.add(fnamearr.get(i));      			
     	       			 id_original_list.add(fnamearr.get(i).key);			                        			 
     	       			 lname_searched.add(lnamearr.get(i));
     	       			email_searched.add(emailarr.get(i));
     					 }
  						 break;
  					case 2:
  						if(specarr.get(i).value.equals("elec"))
     					 {
     						fname_searched.add(fnamearr.get(i));      			
     	       			 id_original_list.add(fnamearr.get(i).key);			                        			 
     	       			 lname_searched.add(lnamearr.get(i));
     	       			email_searched.add(emailarr.get(i));
     					 }
  						 break;
  					case 3:
  						if(specarr.get(i).value.equals("mech"))
     					 {
     						fname_searched.add(fnamearr.get(i));      			
     	       			 id_original_list.add(fnamearr.get(i).key);			                        			 
     	       			 lname_searched.add(lnamearr.get(i));
     	       			email_searched.add(emailarr.get(i));
     					 }
  						 break;
  					 }
  			break;
  				 case 1:
  					 if(degreearr.get(i).value.equals("be"))
  					 {
  						switch(spinner_selected3)
     					 {
     					 case 0:
     			 fname_searched.add(fnamearr.get(i));      			
     			 id_original_list.add(fnamearr.get(i).key);			                        			 
     			 lname_searched.add(lnamearr.get(i));
     			email_searched.add(emailarr.get(i));
     			break;
     					 case 1:
     						if(specarr.get(i).value.equals("comp"))
        					 {
        						fname_searched.add(fnamearr.get(i));      			
        	       			 id_original_list.add(fnamearr.get(i).key);			                        			 
        	       			 lname_searched.add(lnamearr.get(i));
        	       			email_searched.add(emailarr.get(i));
        					 }
     						 break;
     					case 2:
     						if(specarr.get(i).value.equals("elec"))
        					 {
        						fname_searched.add(fnamearr.get(i));      			
        	       			 id_original_list.add(fnamearr.get(i).key);			                        			 
        	       			 lname_searched.add(lnamearr.get(i));
        	       			email_searched.add(emailarr.get(i));
        					 }
     						 break;
     					case 3:
     						if(specarr.get(i).value.equals("mech"))
        					 {
        						fname_searched.add(fnamearr.get(i));      			
        	       			 id_original_list.add(fnamearr.get(i).key);			                        			 
        	       			 lname_searched.add(lnamearr.get(i));
        	       			email_searched.add(emailarr.get(i));
        					 }
     						 break;
     					 }
  					 }
  					 break;
  				case 2:
 					 if(degreearr.get(i).value.equals("me"))
 					 {
 						switch(spinner_selected3)
     					 {
     					 case 0:
     			 fname_searched.add(fnamearr.get(i));      			
     			 id_original_list.add(fnamearr.get(i).key);			                        			 
     			 lname_searched.add(lnamearr.get(i));
     			email_searched.add(emailarr.get(i));
     			break;
     					 case 1:
     						if(specarr.get(i).value.equals("comp"))
        					 {
        						fname_searched.add(fnamearr.get(i));      			
        	       			 id_original_list.add(fnamearr.get(i).key);			                        			 
        	       			 lname_searched.add(lnamearr.get(i));
        	       			email_searched.add(emailarr.get(i));
        					 }
     						 break;
     					case 2:
     						if(specarr.get(i).value.equals("elec"))
        					 {
        						fname_searched.add(fnamearr.get(i));      			
        	       			 id_original_list.add(fnamearr.get(i).key);			                        			 
        	       			 lname_searched.add(lnamearr.get(i));
        	       			email_searched.add(emailarr.get(i));
        					 }
     						 break;
     					case 3:
     						if(specarr.get(i).value.equals("mech"))
        					 {
        						fname_searched.add(fnamearr.get(i));      			
        	       			 id_original_list.add(fnamearr.get(i).key);			                        			 
        	       			 lname_searched.add(lnamearr.get(i));
        	       			email_searched.add(emailarr.get(i));
        					 }
     						 break;
     					 }
 					 }
 					 break;
  				case 3:
 					 if(degreearr.get(i).value.equals("mba"))
 					 {
 						switch(spinner_selected3)
     					 {
     					 case 0:
     			 fname_searched.add(fnamearr.get(i));      			
     			 id_original_list.add(fnamearr.get(i).key);			                        			 
     			 lname_searched.add(lnamearr.get(i));
     			email_searched.add(emailarr.get(i));
     			break;
     					 case 1:
     						if(specarr.get(i).value.equals("comp"))
        					 {
        						fname_searched.add(fnamearr.get(i));      			
        	       			 id_original_list.add(fnamearr.get(i).key);			                        			 
        	       			 lname_searched.add(lnamearr.get(i));
        	       			email_searched.add(emailarr.get(i));
        					 }
     						 break;
     					case 2:
     						if(specarr.get(i).value.equals("elec"))
        					 {
        						fname_searched.add(fnamearr.get(i));      			
        	       			 id_original_list.add(fnamearr.get(i).key);			                        			 
        	       			 lname_searched.add(lnamearr.get(i));
        	       			email_searched.add(emailarr.get(i));
        					 }
     						 break;
     					case 3:
     						if(specarr.get(i).value.equals("mech"))
        					 {
        						fname_searched.add(fnamearr.get(i));      			
        	       			 id_original_list.add(fnamearr.get(i).key);			                        			 
        	       			 lname_searched.add(lnamearr.get(i));
        	       			email_searched.add(emailarr.get(i));
        					 }
     						 break;
     					 }
 					 }
 					 break;
  				case 4:
 					 if(degreearr.get(i).value.equals("phd"))
 					 {
 						switch(spinner_selected3)
     					 {
     					 case 0:
     			 fname_searched.add(fnamearr.get(i));      			
     			 id_original_list.add(fnamearr.get(i).key);			                        			 
     			 lname_searched.add(lnamearr.get(i));
     			email_searched.add(emailarr.get(i));
     			break;
     					 case 1:
     						if(specarr.get(i).value.equals("comp"))
        					 {
        						fname_searched.add(fnamearr.get(i));      			
        	       			 id_original_list.add(fnamearr.get(i).key);			                        			 
        	       			 lname_searched.add(lnamearr.get(i));
        	       			email_searched.add(emailarr.get(i));
        					 }
     						 break;
     					case 2:
     						if(specarr.get(i).value.equals("elec"))
        					 {
        						fname_searched.add(fnamearr.get(i));      			
        	       			 id_original_list.add(fnamearr.get(i).key);			                        			 
        	       			 lname_searched.add(lnamearr.get(i));
        	       			email_searched.add(emailarr.get(i));
        					 }
     						 break;
     					case 3:
     						if(specarr.get(i).value.equals("mech"))
        					 {
        						fname_searched.add(fnamearr.get(i));      			
        	       			 id_original_list.add(fnamearr.get(i).key);			                        			 
        	       			 lname_searched.add(lnamearr.get(i));
        	       			email_searched.add(emailarr.get(i));
        					 }
     						 break;
     					 }
 					 }
 					 break;
  				 }
       	 }///////For Loop ENDS
               	myAdapter.fnamearr=fname_searched;
               	myAdapter.lnamearr=lname_searched;
               	myAdapter.emailarr=email_searched;
       	 }
////////If Edit Text Null ENDS
       		 
       	
       	lv.setAdapter(adapter);//refreshing the list view. Re-attaching the adapter
		}
	};
	//////Text Changed Listener END
	OnItemSelectedListener spinner_listener1=new OnItemSelectedListener() {

		@Override
		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,long arg3) 
		{
			// TODO Auto-generated method stub
			//Toast.makeText(Start.this,"Spinner Clicked"+ arg3,(int) System.currentTimeMillis()).show();
			spinner_selected1=(int)arg3;
			tw_listener.afterTextChanged(null);
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) 
		{
			// TODO Auto-generated method stub
			
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
			if(spinner_selected2==3)
			{
				sp3.setEnabled(false);
				
			}
			else
				sp3.setEnabled(true);
			tw_listener.afterTextChanged(null);
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
			spinner_selected3=(int)arg3;
			tw_listener.afterTextChanged(null);
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) 
		{
			// TODO Auto-generated method stub
			
		}
	};
	////////////
	//LIST LISTENER
	OnItemClickListener Listlistener =new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,long arg3) 
		{
			// TODO Auto-generated method stub
			int id=id_original_list.get((int)arg3);						
			Intent in=new Intent("show1"); 
			in.putExtra("id",id);//passing the id of database of clicked item			
			//startActivity(in);
			startActivityForResult(in,11);
		}
	};
	 
	
		
		@Override
		public void onActivityResult(int requestCode, int resultCode, Intent data) {
			// TODO Auto-generated method stub
			if(requestCode==10)
			{
				if(resultCode==100)
				{
					Start.this.onCreate(b);
					//lv.setAdapter(adapter);
					Toast.makeText(Start.this,"Student Added",(int)System.currentTimeMillis()).show();
				}
			}
			if(requestCode==11)
			{
				if(resultCode==110)
				{
				Start.this.onCreate(b);

				//Toast.makeText(Start.this,"From Show",(int)System.currentTimeMillis()).show();
				}
			}
			if(requestCode==12)
			{
				if(resultCode==120)
				{
				Start.this.onCreate(b);

				//Toast.makeText(Start.this,"From Show",(int)System.currentTimeMillis()).show();
				}
			}
			
		}
		
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.menu_start, menu);
	    return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	        case R.id.add_student:     
	        	Intent in_add_student=new Intent("add");
				startActivityForResult(in_add_student,10);
	                            break;
	        case R.id.del_students:
	        	startActivityForResult(new Intent("delete_multiple"),12);
	      break;
	                            
	    }
	    return true;
	}

}