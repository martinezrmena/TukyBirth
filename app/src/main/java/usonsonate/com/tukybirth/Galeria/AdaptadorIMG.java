package usonsonate.com.tukybirth.Galeria;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import java.util.List;

import usonsonate.com.tukybirth.R;

public class AdaptadorIMG extends ArrayAdapter<IMG> {

    public AdaptadorIMG(@NonNull Context context, @NonNull List<IMG> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        IMG lstIMG = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.img_layout,parent,false);
        }
        ImageView imgCamaraview = convertView.findViewById(R.id.imgviewid);

        Bitmap bmImg = BitmapFactory.decodeFile("/storage/emulated/0/ImgTukyBirth/"+lstIMG.getNombreImg());
        imgCamaraview.setImageBitmap(bmImg);

        return convertView;
    }

}
