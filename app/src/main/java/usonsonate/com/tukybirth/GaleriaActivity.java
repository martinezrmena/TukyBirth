package usonsonate.com.tukybirth;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.ImageAssetDelegate;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.github.chrisbanes.photoview.PhotoView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import usonsonate.com.tukybirth.Galeria.AdaptadorIMG;
import usonsonate.com.tukybirth.Galeria.DMImg;
import usonsonate.com.tukybirth.Galeria.IMG;

public class GaleriaActivity extends AppCompatActivity {

    private GridView viewlista;
    private FloatingActionButton btntomarfoto;
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
    private AdaptadorIMG adaptadorIMG;
    private String nameV;
    private String vacio;
    private Context TheThis;
    private String NameOfFolder = "/ImgTukyBirth";
    private String NameOfFile = "imagenTuky";
    private DMImg dbImg;
    private ArrayList<IMG> lstIMG;
    private Dialog myDialog;
    private PhotoView imagenPopUp;
    private TextView txtclose,txtgaleriavacia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galeria);
        getSupportActionBar().setTitle("Galeria TukyBirth");

        btntomarfoto = findViewById(R.id.btntomarfoto);
        verifyStoragePermissions(this);

        myDialog = new Dialog(GaleriaActivity.this);
        dbImg = new DMImg(GaleriaActivity.this);
        lstIMG = new ArrayList<>();
        adaptadorIMG = new AdaptadorIMG(GaleriaActivity.this, lstIMG);
        viewlista = findViewById(R.id.lstIMG);
        viewlista.setAdapter(adaptadorIMG);
        txtgaleriavacia = findViewById(R.id.txtgaleriavacia);
        vacio = String.valueOf(dbImg.getArrayNombreIMG(dbImg.getCursorNombreIMG()));

        if(!vacio.equals("null")){
            FillListContraccioones();
        }else{
            txtgaleriavacia.setVisibility(View.VISIBLE);

        }


        viewlista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //Toast.makeText(GaleriaActivity.this, "item " +lstIMG.get(position).getNombreImg().toString(), Toast.LENGTH_SHORT).show();
                myDialog.setContentView(R.layout.custompopup_img);
                imagenPopUp = myDialog.findViewById(R.id.ImagePopup);
                imagenPopUp.setScaleType(ImageView.ScaleType.FIT_XY);
                txtclose = myDialog.findViewById(R.id.txtclose);

               Bitmap bmImg = BitmapFactory.decodeFile("/storage/emulated/0/ImgTukyBirth/"+lstIMG.get(position).getNombreImg().toString());
               imagenPopUp.setImageBitmap(bmImg);


                txtclose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        myDialog.dismiss();
                    }
                });

                myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                myDialog.show();

            }
        });

        btntomarfoto.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent,0);

            }
        });

    }


    //region metodo para recorrer el lista de imagenes
    public void FillListContraccioones() {

        for (final IMG c :dbImg.getArrayNombreIMG(dbImg.getCursorNombreIMG())) {
            lstIMG.add(c);
            adaptadorIMG.notifyDataSetChanged();
        }
    }
//endregion

    //region Para verificar Permisos
    public static void verifyStoragePermissions(Activity activity) {
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
// We don’t have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
    }
    //endregion

    //region resultado de la camara
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

      try{
              Bitmap bitmap =(Bitmap)data.getExtras().get("data");
              SaveImage(GaleriaActivity.this, bitmap);
             txtgaleriavacia.setVisibility(View.INVISIBLE);

      }catch (Exception e){
          Toast.makeText(GaleriaActivity.this, "Camara cancelada.", Toast.LENGTH_SHORT).show();
      }




    }
//endregion

    //region  Clase para Guardar Imagen
    /*********************************************************************************************************************************/

    public void SaveImage(Context context, Bitmap ImageToSave) {

        TheThis = context;
        String file_path = Environment.getExternalStorageDirectory().getAbsolutePath() + NameOfFolder;
        String CurrentDateAndTime = getCurrentDateAndTime();
        File dir = new File(file_path);

        if (!dir.exists()) {
            dir.mkdirs();
        }

        File file = new File(dir, NameOfFile + CurrentDateAndTime + ".jpg");
        nameV = file.getName();
        try {
            FileOutputStream fOut = new FileOutputStream(file);

            ImageToSave.compress(Bitmap.CompressFormat.JPEG, 100, fOut);
            fOut.flush();
            fOut.close();
            MakeSureFileWasCreatedThenMakeAvabile(file);
            AbleToSave();
        } catch (FileNotFoundException e) {
            UnableToSave();
        } catch (IOException e) {
            UnableToSave();
        }

    }

    private void MakeSureFileWasCreatedThenMakeAvabile(File file) {
        MediaScannerConnection.scanFile(TheThis,
                new String[]{file.toString()}, null,
                new MediaScannerConnection.OnScanCompletedListener() {

                    public void onScanCompleted(String path, Uri uri) {
                    }
                });
    }

    private String getCurrentDateAndTime() {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd-HH-mm-­ss");
        String formattedDate = df.format(c.getTime());
        return formattedDate;
    }

    private void UnableToSave() {
        Toast.makeText(TheThis, "¡No se ha podido guardar la imagen!", Toast.LENGTH_SHORT).show();
    }

    private void AbleToSave() {
        IMG lista = new IMG("", nameV);
        dbImg.guardar_O_ActualizarNotas(lista);
        lstIMG.add(lista);
        adaptadorIMG.notifyDataSetChanged();
        Toast.makeText(TheThis, "Imagen guardada.", Toast.LENGTH_SHORT).show();
    }
    /*********************************************************************************************************************************/
    //endregion

}
