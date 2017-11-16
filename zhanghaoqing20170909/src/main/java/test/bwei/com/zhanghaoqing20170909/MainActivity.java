package test.bwei.com.zhanghaoqing20170909;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import test.bwei.com.zhanghaoqing20170909.fragment.Fragment01;
import test.bwei.com.zhanghaoqing20170909.fragment.Fragment02;
import test.bwei.com.zhanghaoqing20170909.fragment.Fragment03;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView textView;
    private TextView textView1;
    private TextView textView2;
    private Fragment01 fragment01;
    private Fragment02 fragment02;
    private Fragment03 fragment03;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.btn01);
        textView1 = (TextView) findViewById(R.id.btn02);
        textView2 = (TextView) findViewById(R.id.btn03);
        textView.setOnClickListener(this);
        textView1.setOnClickListener(this);
        textView2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (fragment01!=null){
            transaction.hide(fragment01);
        }
        if (fragment02!=null){
            transaction.hide(fragment02);
        }if (fragment03!=null){
            transaction.hide(fragment03);
        }

        switch (v.getId()){
            case R.id.btn01:

                if (fragment01==null){
                    fragment01 = new Fragment01();
                    transaction.add(R.id.relative,fragment01);
                }else {

                   transaction.show(fragment01);
                }

            break;
            case R.id.btn02:

                if (fragment02==null){
                    fragment02 = new Fragment02();
                    transaction.add(R.id.relative,fragment02);
                }else {

                    transaction.show(fragment02);
                }
                break;

            case R.id.btn03:

                if (fragment03==null){
                    fragment03 = new Fragment03();
                    transaction.add(R.id.relative,fragment03);
                }else {

                    transaction.show(fragment03);
                }
                break;


            default:break;

        }
        transaction.commit();
    }
}
