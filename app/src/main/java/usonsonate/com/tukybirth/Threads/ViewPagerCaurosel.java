package usonsonate.com.tukybirth.Threads;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import usonsonate.com.tukybirth.R;

public class ViewPagerCaurosel extends PagerAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    int[] images = {
            R.drawable.semana_1,
            R.drawable.semana_2,
            R.drawable.semana_3,
            R.drawable.semana_4,
            R.drawable.semana_5,
            R.drawable.semana_6,
            R.drawable.semana_7,

            R.drawable.semana_8,
            R.drawable.semana_9,
            R.drawable.semana_10,
            R.drawable.semana_11,
            R.drawable.semana_12,

            R.drawable.semana_13,
            R.drawable.semana_14,
            R.drawable.semana_15,
            R.drawable.ani_deer_four,

            R.drawable.semana_17,
            R.drawable.semana_18,
            R.drawable.semana_19,
            R.drawable.bird_parrot_four,
            R.drawable.bird_parrot_five,
            R.drawable.bird_parrot_six,
            R.drawable.bird_parrot_seven,
            R.drawable.bird_parrot_eight
    };

    public ViewPagerCaurosel(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position){

        layoutInflater =(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.customviewcarousel,null);
        ImageView imageView = view.findViewById(R.id.imageView);
        imageView.setImageResource(images[position]);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

        ViewPager vp = (ViewPager) container;
        vp.addView(view, 0);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object){
        ViewPager vp = (ViewPager) container;
        View view = (View) object;
        vp.removeView(view);
    }

}
