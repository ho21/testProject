package jzb.ask;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.view.MotionEventCompat;
import android.view.MotionEvent;
import android.view.View;

import android.view.ViewGroup;
import android.widget.Gallery;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import com.orhanobut.logger.Logger;

import static android.R.attr.action;
import static android.R.attr.height;
import static android.R.attr.layout_width;
import static android.R.attr.width;


/**
 * Created by Administrator on 2016-12-26.
 */
public class MainActivity  extends Activity implements ImageSwitcher.ViewFactory,ImageSwitcher.OnTouchListener {
//public class MainActivity  extends Activity{

    private ImageSwitcher mImageSwitcher;
    private int[] images = {R.mipmap.ee1,R.mipmap.ee2,R.mipmap.start};
    private int index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.d("onCreate.....");
        setTheme(R.style.AppTheme);
        setContentView(R.layout.activity_main);
        SystemClock.sleep(500);
        mImageSwitcher = (ImageSwitcher) this.findViewById(R.id.imageSwitcher);
        mImageSwitcher.setFactory(this);
        mImageSwitcher.setOnTouchListener(this);

    }

    @Override
    public View makeView() {
        ImageView iv = new ImageView(this);
        //设置图片大小
        iv.setLayoutParams(new ImageSwitcher.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 800));
        iv.setImageResource(images[0]);
        iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
        return iv;
    }

    float startX;
    float endX;

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        int imgSum = images.length-1;
        int action = motionEvent.getAction();
//        int action = MotionEventCompat.getActionMasked(motionEvent);
        switch(action) {
            case MotionEvent.ACTION_DOWN:
                Logger.d("ACTION_DOWN");
                startX = motionEvent.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                Logger.d("ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Logger.d("ACTION_UP");
                endX = motionEvent.getX();
                if(endX-startX>20){
                    if(imgSum==index){ index = 0;} else{ if(imgSum>index) index = index+1; }
                    mImageSwitcher.setImageResource(images[index]);
                }else if(endX-startX<20){
                    if(index==0){ index = imgSum;} else{ if(imgSum>=index) index = index-1; }
                    mImageSwitcher.setImageResource(images[index]);
                }
                break;
        }
//        Logger.d("图片索引+"+imgSum+":"+index);
//        Logger.d("当前图片:"+index);
//        Logger.d("偏移量:"+(endX-startX));
        return true;
    }

}
