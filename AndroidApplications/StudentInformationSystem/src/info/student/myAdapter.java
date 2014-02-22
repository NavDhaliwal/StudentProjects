package info.student;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
class storage
{
	int key;
	String value;
}
public class myAdapter extends BaseAdapter
{
	LayoutInflater mInflater;
	StudentDB db;
	 //private SQLiteDatabase db1;
	Context c;
	public static ArrayList<storage> fnamearr;
	public static ArrayList<storage> lnamearr;
	public static ArrayList<storage> emailarr;
	
	public myAdapter(Context context,StudentDB db,ArrayList<storage> fnamearr,ArrayList<storage> lnamearr,
			ArrayList<storage> emailarr,ArrayList<storage> degreearr,ArrayList<storage> specarr) 
	{
		//super(context,0);
		 mInflater = LayoutInflater.from(context);
		 c=context;
		 this.db=db;
		// db1.execSQL("DROP TABLE IF EXISTS Info");
		 Cursor all = db.getAllInfo();
			myAdapter.fnamearr=fnamearr;
			myAdapter.lnamearr=lnamearr;
			myAdapter.emailarr=emailarr;
			for (int i = 0; i < all.getCount(); i++) 
			{
				all.moveToNext();
				int index=Integer.parseInt(all.getString(0));
				storage store1=new storage();
				storage store2=new storage();
				storage store3=new storage();
				storage store4=new storage();
				storage store5=new storage();
				store1.key=index;
				store2.key=index;
				store3.key=index;
				store4.key=index;
				store5.key=index;
				store1.value=all.getString(1);
				fnamearr.add(store1);
				store2.value=all.getString(3);
				lnamearr.add(store2);
				store3.value=all.getString(9);
				emailarr.add(store3);
				store4.value=all.getString(11);
				degreearr.add(store4);
				store5.value=all.getString(12);
				specarr.add(store5);
			}
			all.close();
	 }

	public int getCount() 
	{
		// TODO Auto-generated method stub
		return fnamearr.size();
	}

	@Override
	public  Object getItem(int position) {
		// TODO Auto-generated method stub		
		return fnamearr.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) 
	{
		ViewHolder holder;
		if (convertView == null) 
		{
			//Toast.makeText(c,"In Get VIew",(int) System.currentTimeMillis()).show();
			 convertView = mInflater.inflate(R.layout.list_view, null);
			 holder = new ViewHolder();
			 holder.text = (TextView) convertView
			 .findViewById(R.id.tv1);
			 holder.text2 = (TextView) convertView
			 .findViewById(R.id.tv2);
			 holder.text3 = (TextView) convertView
			 .findViewById(R.id.tv3);
			 convertView.setTag(holder);
		}
		else 
			 {
			 holder = (ViewHolder) convertView.getTag();
			 }
		
			 holder.text.setText(fnamearr.get(position).value);
			 holder.text2.setText(lnamearr.get(position).value);
			 holder.text3.setText(emailarr.get(position).value);
			 return convertView;

	}
	 static class ViewHolder {
		 
		  TextView text;		 
		  TextView text2;
		  TextView text3;
		  }


}
