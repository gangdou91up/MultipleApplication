package guanzhihui.com.multipleapplication;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import guanzhihui.com.multipleapplication.bean.Brand;

public class MainActivity extends AppCompatActivity {


    private ListView mListView; //首页的ListView
    private List<Brand> namesList; //用于装载数据的集合
    private int selectPosition = -1;//用于记录用户选择的变量
    private Brand selectBrand; //用户选择的品牌

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initDatas();
    }

    private void initView(){
        mListView = (ListView)findViewById(R.id.id_myList);
    }

    private void initDatas(){
        //初始化ListView适配器的数据
        namesList = new ArrayList<>();
        Brand brand0 = new Brand("apple");
        Brand brand1 = new Brand("sony");
        Brand brand2 = new Brand("xiaomi");
        Brand brand3 = new Brand("oppo");
        Brand brand4 = new Brand("meizu");
        Brand brand5 = new Brand("smartisan");
        Brand brand6 = new Brand("vivo");
        Brand brand7 = new Brand("samsung");
        Brand brand8 = new Brand("letv");
        Brand brand9 = new Brand("nubia");
        Brand brand10 = new Brand("lg");
        Brand brand11 = new Brand("qiku");
        Brand brand12 = new Brand("huawei");
        namesList.add(brand0);
        namesList.add(brand1);
        namesList.add(brand2);
        namesList.add(brand3);
        namesList.add(brand4);
        namesList.add(brand5);
        namesList.add(brand6);
        namesList.add(brand7);
        namesList.add(brand8);
        namesList.add(brand9);
        namesList.add(brand10);
        namesList.add(brand11);
        final MyAdapter myAdapter = new MyAdapter(this,namesList);
        mListView.setAdapter(myAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //获取选中的参数
                selectPosition = position;
                myAdapter.notifyDataSetChanged();
                selectBrand = namesList.get(position);
                Toast.makeText(MainActivity.this,"您选中的手机品牌是："+selectBrand.getBandname(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    public class MyAdapter extends BaseAdapter{
        Context context;
        List<Brand> brandsList;
        LayoutInflater mInflater;
        public MyAdapter(Context context,List<Brand> mList){
            this.context = context;
            this.brandsList = mList;
            mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        @Override
        public int getCount() {
            return brandsList.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            ViewHolder viewHolder = null;
            if(convertView == null){
                convertView = mInflater.inflate(R.layout.adapter_item,parent,false);
                viewHolder = new ViewHolder();
                viewHolder.name = (TextView)convertView.findViewById(R.id.id_name);
                viewHolder.select = (RadioButton)convertView.findViewById(R.id.id_select);
                convertView.setTag(viewHolder);
            }else{
                viewHolder = (ViewHolder)convertView.getTag();
            }
            viewHolder.name.setText(brandsList.get(position).getBandname());

            if(selectPosition == position){
                viewHolder.select.setChecked(true);
            }
            else{
                viewHolder.select.setChecked(false);
            }
            return convertView;
        }
    }


    public class ViewHolder{
        TextView name;
        RadioButton select;
    }
}
