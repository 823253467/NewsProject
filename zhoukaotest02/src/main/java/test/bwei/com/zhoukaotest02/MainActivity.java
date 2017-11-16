package test.bwei.com.zhoukaotest02;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import test.bwei.com.zhoukaotest02.fragment.WeixinFragment01;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private ListView listView;
    private FrameLayout frameLayout;
    private RelativeLayout relativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        listView = (ListView) findViewById(R.id.list_view);

        frameLayout = (FrameLayout) findViewById(R.id.frame_layout);
        relativeLayout = (RelativeLayout) findViewById(R.id.relativ_layout);

        final List<String> list= new ArrayList<>();
        for (int i=1;i<10;i++){
            list.add(i+"");
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, android.R.id.text1, list);


        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                WeixinFragment01 weixinFragment01 = new WeixinFragment01();

                Bundle bundle=new Bundle();
                bundle.putString("key",list.get(position));
                weixinFragment01.setArguments(bundle);

                getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,weixinFragment01).commit();


                drawerLayout.closeDrawer(relativeLayout);

            }
        });


    }


}
