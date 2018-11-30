package usonsonate.com.tukybirth;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class VerificacionCorreoActivity extends AppCompatActivity {

    private Button btnverificar;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private LottieAnimationView animationViewlotiee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verificacion_correo);

        btnverificar = findViewById(R.id.btnVerificarCorreo);
        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
        /*animacion*/
        animationViewlotiee  = findViewById(R.id.animation_view);
        animationViewlotiee.loop(false);
        animationViewlotiee.playAnimation();

        final String correo = getIntent().getStringExtra("USERNAME");
        final String pass =  getIntent().getStringExtra("PASSWORD");


        progressDialog.setMessage("Realizando registro en linea...");
        progressDialog.show();

        /************************************************************************************************************************************************/
        /**CODIGO PARA VALIDAR  EL USUARIO EN FIREBASE CON EMAIL**/
        firebaseAuth.createUserWithEmailAndPassword(correo,pass).addOnCompleteListener(VerificacionCorreoActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(!task.isSuccessful()){
                    Toast.makeText(VerificacionCorreoActivity.this,"La verificacion del correo fallo, Vuelva a intentarlo", Toast.LENGTH_SHORT).show();
                      progressDialog.dismiss();
                }
                else{
                    Toast.makeText(VerificacionCorreoActivity.this,"Se envio un Link de verificacion a tu correo,para continuar valide su correo", Toast.LENGTH_LONG).show();
                    FirebaseUser user = firebaseAuth.getCurrentUser();
                    user.sendEmailVerification();
                    progressDialog.dismiss();
                }
            }
        });

        /**************************************************************************************************************************************************/
        /**ESTE ES EL LISTENER DE FIREBASE QUE VERIFICA SI EL REGISTRO FUE COREECTO  O  NO */
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user != null){
                    if(user.isEmailVerified()){
                     /******************************************************************/

                        AlertDialog.Builder builder = new AlertDialog.Builder(VerificacionCorreoActivity.this);
                        builder.setIcon(R.drawable.pregnant).
                                setTitle("Atención").setMessage("¡La verificacion de su correo se realizo con exito!").setPositiveButton("Continuar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(VerificacionCorreoActivity.this, SlideLogin.class);
                                intent.putExtra("USERNAME", correo);
                                intent.putExtra("PASSWORD", pass);
                                startActivity(intent);
                                finish();
                            }
                        });
                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();
                        /******************************************************************/


                    }

                }


            }
        };
/**********************************************************************************************************************************************/


        btnverificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signInWithEmailAndPassword(correo,pass).addOnCompleteListener(VerificacionCorreoActivity.this, new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(!task.isSuccessful()){
                         //   Toast.makeText(VerificacionCorreoActivity.this,"hubo un error al verificar el usuario en firebase!!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                /*para recargar el activity*/
                finish();
                startActivity(getIntent());

            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(authStateListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(authStateListener != null){
            firebaseAuth.removeAuthStateListener(authStateListener);
        }

    }
}
