package com.example.myhomework;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	//public String[] myTask={"1 大大猫~~","2 四条腿~~","3 笑眯眯~~","4 学代码~~","5 笨笨的~","6 使劲学~~"};
	public String myTaskString =new String();//"   大大猫~~\n   四条腿~~\n    笑眯眯~~\n    学代码~~\n    笨笨的~\n    使劲学~~\n     ";
	public String[] myTask = new String[150]; //myTaskString.split("\n");
	/*  让listview显示静态数组  (完成）
	 *  把一句话用\n分割，然后用函数编程数组 (完成）
	 *  让listview读取SD卡并用Toast显示（完成） 
	 *  让输入框能写文字到追加在txt里面
	 *  点击后，输入框清空，并且下面ListView刷新
	 */
	//
	public ListView myList = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Button myButton = (Button) findViewById(R.id.button1);
		myButton.setOnClickListener(dothis);
		myList = (ListView) findViewById(R.id.listView1);
		myList.setAdapter(new myAdapter());
		//----------------读取数据----------------------------------
		try{
			FileInputStream fileIS = new FileInputStream("/sdcard/kk.txt");
			BufferedReader buf = new BufferedReader(new InputStreamReader(fileIS));
			String readString = new String();
			while((readString = buf.readLine())!= null){
				myTaskString = myTaskString + readString;
				
			}
			myTask = myTaskString.split(",");
			fileIS.close();
		} catch (IOException e){
			Toast.makeText(this, "冷静！！先啃棉花糖再改Bug！！", Toast.LENGTH_LONG).show();
			e.printStackTrace();
		}
	}
	//----------------读取数据----------------------------------
	public class myAdapter extends BaseAdapter{
		@Override
		public int getCount() {
			return 5;
		}
		@Override
		public Object getItem(int position) {
			return null;
		}
		@Override
		public long getItemId(int position) {
			return 0;
		}
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View tempView = LayoutInflater.from(MainActivity.this).inflate(R.layout.forshow, null);
			TextView tempTextView = (TextView) tempView.findViewById(R.id.textView4);
			tempTextView.setText(position+ "   "+myTask[position]);
			return tempView;
		}
	}

	OnClickListener dothis = new OnClickListener() {
		@Override
		public void onClick(View v) {
			EditText myEditText = (EditText) findViewById(R.id.editText1);
			myTaskString= ","+myEditText.getText()+ myTaskString;
			//——————————————————写入文件——————————————————
			File f = new File(android.os.Environment.getExternalStorageDirectory()+"/kk.txt");
			myEditText.setText("");
			try{
				FileOutputStream fileOS=new FileOutputStream(f);
				fileOS.write(myTaskString.getBytes());
				fileOS.close();
				BufferedWriter buf = new BufferedWriter (new OutputStreamWriter(fileOS));
				buf.write(myTaskString,0,myTaskString.length());
				buf.flush();
				buf.close();
			}catch(IOException e){
				Log.d("错误","只能这样了嗷嗷~~");
			}
			//myList.setAdapter(new myAdapter());明显是不行的，但怎样才能好呢？
			//——————————————————写入文件——————————————————			 
		}
	};






}
