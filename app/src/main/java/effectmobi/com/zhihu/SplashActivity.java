package effectmobi.com.zhihu;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import java.io.File;

public class SplashActivity extends Activity {

    ImageView iv_start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        iv_start = (ImageView) findViewById(R.id.iv_start);

        initImageView();

    }

    /**
     * 展示图片的放大效果
     * 这里需要处理 展示网络图片
     *   float fromX 动画起始时 X坐标上的伸缩尺寸
         float toX 动画结束时 X坐标上的伸缩尺寸
         float fromY 动画起始时Y坐标上的伸缩尺寸
         float toY 动画结束时Y坐标上的伸缩尺寸
         int pivotXType 动画在X轴相对于物件位置类型
         float pivotXValue 动画相对于物件的X坐标的开始位置
         int pivotYType 动画在Y轴相对于物件位置类型
         float pivotYValue 动画相对于物件的Y坐标的开始位置
     */
    private void initImageView() {

        File file = new File(getFilesDir(),"start.jpg");
        if(file.exists())
        {
            //使用从网络下载过来的图片
            iv_start.setImageBitmap(BitmapFactory.decodeFile(file.getAbsolutePath()));
        }else
        {
            //从资源文件中使用图片
            iv_start.setImageResource(R.drawable.start);
        }

        ScaleAnimation scaleAnimation = new ScaleAnimation(1.0f,1.6f,1.0f,1.6f, Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        scaleAnimation.setDuration(3000);
        scaleAnimation.setFillAfter(true);
        scaleAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                //TODO:从网络上下载图片，存储到getFileDir()目录中，这里暂时不做处理
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        iv_start.startAnimation(scaleAnimation);




    }
}
