package usonsonate.com.tukybirth.Comidas;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;

import java.util.ArrayList;

import usonsonate.com.tukybirth.Ejercicios.AdapterListExercise;
import usonsonate.com.tukybirth.Ejercicios.InformationExercise;
import usonsonate.com.tukybirth.InformacionEmbarazo;
import usonsonate.com.tukybirth.R;
import usonsonate.com.tukybirth.Semanas.AnimationUtil;
import usonsonate.com.tukybirth.VisorPdfActivity;

public class AdapterComida extends  RecyclerView.Adapter<AdapterComida.MyViewHolder> {
    private Context context;

    private ArrayList<InformacionComidas> data;

    private LayoutInflater inflater;

    private int previousPosition = 0;
    private static int valor;

    Dialog myDialog;

    public AdapterComida(Context context, ArrayList<InformacionComidas> data) {
        this.context = context;
        this.data = data;
        inflater = LayoutInflater.from(context);
    }
    public AdapterComida(int valor){
        this.valor = valor;
    }

    /*aqui ahy que hacer un layout personalizado*/
    /********************************************************************************************/
    @Override
    public AdapterComida.MyViewHolder onCreateViewHolder(ViewGroup parent, int position) {

        View view = inflater.inflate(R.layout.list_item_row, parent, false);

        AdapterComida.MyViewHolder holder = new AdapterComida.MyViewHolder(view);

        return holder;
    }
    /********************************************************************************************/
    @Override
    public void onBindViewHolder(AdapterComida.MyViewHolder myViewHolder, final int position) {

        myViewHolder.textview.setText(data.get(position).title);
        myViewHolder.cardView.setBackground(data.get(position).color);
        myViewHolder.imageView.setImageResource(data.get(position).imageId);

        if(position > previousPosition){ // We are scrolling DOWN

            AnimationUtil.animate(myViewHolder, true);

        }else{ // We are scrolling UP

            AnimationUtil.animate(myViewHolder, false);


        }

        previousPosition = position;


        final int currentPosition = position;
        myDialog = new Dialog(context);
        final InformacionComidas infoData = data.get(position);


        myViewHolder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context, "OnClick Called at position " + position, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, VisorPdfActivity.class);
                intent.putExtra("imgposicion",String.valueOf(position));
                intent.putExtra("trimestrePosicion",String.valueOf(valor));
                context.startActivity(intent);


            }
        });

        myViewHolder.imageView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                Toast.makeText(context, "Semana " + (position + 1) + " removida.", Toast.LENGTH_SHORT).show();

                removeItem(infoData);

                return true;
            }


        });


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView textview;
        ImageView imageView;
        LinearLayout cardView;

        public MyViewHolder(View itemView) {
            super(itemView);

            textview = itemView.findViewById(R.id.txv_row);
            imageView = itemView.findViewById(R.id.img_row);
            cardView = itemView.findViewById(R.id.MainCard);
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);

        }
    }

    // This removes the data from our Dataset and Updates the Recycler View.
    private void removeItem(InformacionComidas infoData) {

        int currPosition = data.indexOf(infoData);
        data.remove(currPosition);
        notifyItemRemoved(currPosition);
    }

    // This method adds(duplicates) a Object (item ) to our Data set as well as Recycler View.
    private void addItem(int position, InformacionComidas infoData) {

        data.add(position, infoData);
        notifyItemInserted(position);
    }


}
