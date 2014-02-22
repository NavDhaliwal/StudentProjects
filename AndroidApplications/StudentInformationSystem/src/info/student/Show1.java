package info.student;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Show1 extends Activity {

	
	static StudentDB db;
	int id;
	Cursor record;
	TextView tv1,tv2,tv3,tv4,tv5,tv6,tv7,tv8;
	ImageView img;
	String fname,mname,lname,fa_name,
	mo_name,email,contact_no,degree,spec;
	int position;
	int day,month,year;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.show1);		
		tv1=(TextView)findViewById(R.id.t1);
		tv2=(TextView)findViewById(R.id.t_degree);
		tv3=(TextView)findViewById(R.id.t_special);
		tv4=(TextView)findViewById(R.id.t4);
		tv5=(TextView)findViewById(R.id.t5);
		tv6=(TextView)findViewById(R.id.t6);
		tv7=(TextView)findViewById(R.id.t7);
		tv8=(TextView)findViewById(R.id.t8);
		img=(ImageView)findViewById(R.id.img);
		img.setImageResource(R.drawable.student);
		id=getIntent().getIntExtra("id",-1);
		//////FOr sd card image
		String filepath = "/sdcard/";
		File imagefile = new File(filepath+id+".png");
		if(imagefile.exists())
		{
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(imagefile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Bitmap bi = BitmapFactory.decodeStream(fis);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		bi.compress(Bitmap.CompressFormat.PNG, 100, baos);
		//String str="s"+id;
		img.setImageBitmap(bi);
		}
		///////////////////////
		
		//Toast.makeText(this,"Id got from intent = "+id,(int)System.currentTimeMillis()).show();
		//record = db.getInfo(id);		
		/*record=db.getAllInfo();
		for(int i=0;i<record.getCount();i++)
		{
			record.moveToNext();			
			if(Integer.parseInt(record.getString(0))==id)
			{
				//position=record.getPosition();
				position=id;
				break;
			}
		}*/
		 record=db.getInfo(id);
		 record.moveToNext();
		//Toast.makeText(this,"Record position is = "+position,(int)System.currentTimeMillis()).show();
		
		//record.moveToPosition(position);
		//Toast.makeText(this,"Id in database = "+position,(int)System.currentTimeMillis()).show();
		fname=record.getString(1);
		mname=record.getString(2);
		lname=record.getString(3);
		fa_name=record.getString(4);
		mo_name=record.getString(5);
		day=Integer.parseInt(record.getString(6));
		month=Integer.parseInt(record.getString(7));
		year=Integer.parseInt(record.getString(8));
		email=record.getString(9);
		contact_no=record.getString(10);
		degree=record.getString(11);
		spec=record.getString(12);
		record.close();
		tv1.setText(fname+" "+mname+" "+lname);
		String d=null,s=null;
		if(degree.equals("be"))
			d="B.E.";
		if(degree.equals("me"))
			d="M.E.";
		if(degree.equals("mba"))
			d="M.B.A";
		if(degree.equals("phd"))
			d="Ph.D";
		if(spec.equals("mba"))
			s=" ";
		if(spec.equals("comp"))
			s="Computer Science";
		if(spec.equals("elec"))
			s="Electronics";
		if(spec.equals("mech"))
			s="Mechanical";
		tv2.setText(d);
		tv3.setText(s);
		tv4.setText(fa_name);
		tv5.setText(mo_name);
		tv6.setText(new StringBuilder().append(day).append("-").append(month).append("-").append(year));
		tv7.setText(email);
		tv8.setText(contact_no);
					
		
			
	}
	/*
	  OnClickListener li =new OnClickListener() {
		
		  Intent in1 = new Intent("back.start");
		  Intent in2 = new Intent("edit.start");
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch(v.getId())
			{
			case R.id.back:
				
				startActivity(in1);
				break;
			case R.id.edit:
				in2.putExtra("fname",fname);
				in2.putExtra("mname",mname);
				in2.putExtra("lname",lname);
				in2.putExtra("fa_name",fa_name);
				in2.putExtra("mo_name",mo_name);
				in2.putExtra("day",day);
				in2.putExtra("month",month);
				in2.putExtra("year",year);
				in2.putExtra("email",email);
				in2.putExtra("contact_no",contact_no);
				in2.putExtra("id",id);
				in2.putExtra("degree",degree);
				in2.putExtra("spec",spec);
				startActivity(in2);
				break;
			case R.id.del:
				db.deleteInfo(id);
				startActivity(in1);
				
				
			}
			
			
		}
	};
	*/
	static void getDB(StudentDB db)
	{
		Show1.db=db;
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.menu_show1, menu);
	    return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	        case R.id.edit_student: 
	        	Intent in2 = new Intent("edit.start");
	        	in2.putExtra("fname",fname);
				in2.putExtra("mname",mname);
				in2.putExtra("lname",lname);
				in2.putExtra("fa_name",fa_name);
				in2.putExtra("mo_name",mo_name);
				in2.putExtra("day",day);
				in2.putExtra("month",month);
				in2.putExtra("year",year);
				in2.putExtra("email",email);
				in2.putExtra("contact_no",contact_no);
				in2.putExtra("id",id);
				in2.putExtra("degree",degree);
				in2.putExtra("spec",spec);
				startActivityForResult(in2,20);
	                            break;
	        case R.id.delete_student: 
	        	//Intent in1 = new Intent("back.start");
	        	db.deleteInfo(id);
	        	setResult(110);
	        	finish();
				//startActivity(in1);
	                            break;
	                            
	    }
	    return true;
	}
	//
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		if(requestCode==20)
		{
			if(resultCode==200)
			{
				setResult(110);
				finish();
				//lv.setAdapter(adapter);
				//Toast.makeText(Start.this,"Student Added",(int)System.currentTimeMillis()).show();
			}
		}
		
		
	}
	//

}
