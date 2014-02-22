package info.student;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class Delete_multiple extends Activity {
	LinearLayout l;
	static ArrayList<storage> fnamearr;
	static ArrayList<storage> lnamearr;
	CheckBox[] cb;
	
	public static void delete_multiple_get(ArrayList<storage> fnamearr,ArrayList<storage> lnamearr)
	{
		Delete_multiple.fnamearr=fnamearr;
		Delete_multiple.lnamearr=lnamearr;
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.delete_multiple);
		cb=new CheckBox[fnamearr.size()];
		l=(LinearLayout)findViewById(R.id.Del_stud);
		
		for(int i=0;i<fnamearr.size();i++)
		{
			cb[i]=new CheckBox(this);
			cb[i].setText(fnamearr.get(i).value+" "+lnamearr.get(i).value);
			//cb[i].setOnCheckedChangeListener(listener);
			
			l.addView(cb[i]);
			
		}
		
		
	}
	


@Override
public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.delete_multiple_menu, menu);
    return true;
}
@Override
public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
       
        case R.id.del_student:
        	for(int i=0;i<fnamearr.size();i++)
        	{
        		if(cb[i].isChecked())
        	Show1.db.deleteInfo(fnamearr.get(i).key);
        	}
        	setResult(120);
        	finish();
      break;
        case R.id.select_all:
        	for(int i=0;i<fnamearr.size();i++)
        		cb[i].setChecked(true);
      break;
      case R.id.deselect_all:

      	for(int i=0;i<fnamearr.size();i++)
      		cb[i].setChecked(false);
  break;
                            
    }
    return true;
}
}
