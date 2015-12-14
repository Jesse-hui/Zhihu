package effectmobi.com.zhihu;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;


public class InfoActivity extends AppCompatActivity {

    ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle("谷歌宣布量子计算机真的可行");


        imageView = (ImageView) findViewById(R.id.backdrop);
        Glide.with(this).load(R.drawable.cheese_1).into(imageView);


    }

    public void discuss(View v){
        Toast.makeText(InfoActivity.this,"discuss",Toast.LENGTH_SHORT).show();
    }



}
