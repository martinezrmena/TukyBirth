package usonsonate.com.tukybirth;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

import usonsonate.com.tukybirth.SQLite.DB;
import usonsonate.com.tukybirth.SQLite.Personas;

public class Login extends AppCompatActivity {

    CollapsingToolbarLayout collapsingToolbarLayout;
    private String Name = "Rafael";
    private String Password = "contraseña";
    private DB db;
    private ArrayList<Personas> lstPersonas;
    private Button btnLogin;
    private boolean Inicializar;
    private EditText txbNombre, txbPassword, txbPasswordConfirm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txbNombre = findViewById(R.id.txbNombre);
        txbPassword = findViewById(R.id.txbPassword);
        txbPasswordConfirm = findViewById(R.id.txbPasswordConfirm);
        collapsingToolbarLayout = findViewById(R.id.collapsedToolBar);
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppBar);
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedAppBar);
        btnLogin = findViewById(R.id.btnLogIn);

        //inicializando lista y db
        db = new DB(Login.this);

        Inicializar = ConsultarPersona();

        if(Inicializar){
            Toast.makeText(this, "Ingresa tus credenciales.", Toast.LENGTH_SHORT).show();
            btnLogin.setText("Acceder");

        }else{
            Toast.makeText(this, "El sistema se inicializará por primera vez.", Toast.LENGTH_SHORT).show();
        }


    }

    public void btnLoginOnClick(View v){


        if (!txbNombre.getText().toString().isEmpty()
                && !txbPassword.getText().toString().isEmpty()
                && !txbPassword.getText().toString().isEmpty()){

            if (txbPassword.getText().toString().equals(txbPasswordConfirm.getText().toString())){
                if(!Inicializar){
                    AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
                    builder.setIcon(R.drawable.pregnant).
                            setTitle("Atención").setMessage("¿Está segura de proceder con los datos ingresados?").setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            /************************************************************************************************************************************************/
                            /**CODIGO PARA VALIDAR  EL USUARIO EN FIREBASE CON EMAIL**/
                            Intent intent = new Intent(Login.this, VerificacionCorreoActivity.class);
                            intent.putExtra("USERNAME", txbNombre.getText().toString());
                            intent.putExtra("PASSWORD", txbPassword.getText().toString());
                            startActivity(intent);
                            finish();
                            /**************************************************************************************************************************************************/

                        }
                    }).setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            dialogInterface.dismiss();
                            Toast.makeText(Login.this, "La acción fue cancelada.", Toast.LENGTH_SHORT).show();
                        }
                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();

                }else{


                    if (txbNombre.getText().toString().equals(lstPersonas.get(0).getNombre()) &&
                            txbPassword.getText().toString().equals(lstPersonas.get(0).getPassword()) ){

                        Intent intent = new Intent(Login.this, CalendarLogin.class);
                        intent.putExtra("PERSONA", lstPersonas.get(0));
                        startActivity(intent);
                        finish();

                    }else{

                        txbNombre.setText("");
                        txbPassword.setText("");
                        txbNombre.requestFocus();
                        Toast.makeText(this, "Las credenciales que proporciono no son correctas.", Toast.LENGTH_SHORT).show();
                    }
                }
            }else{
                txbPassword.setText("");
                txbPasswordConfirm.setText("");
                txbPassword.requestFocus();
                Toast.makeText(this, "Las contraseñas no coinciden.", Toast.LENGTH_SHORT).show();
            }

        }else{

            if(txbNombre.getText().toString().isEmpty()){
                txbNombre.requestFocus();
                Toast.makeText(this, "No ha ingresado ningún nombre.", Toast.LENGTH_SHORT).show();

            }else if(txbPassword.getText().toString().isEmpty()){
                txbPassword.requestFocus();
                Toast.makeText(this, "No ha ingresado ninguna contraseña.", Toast.LENGTH_SHORT).show();
            }else if(txbPasswordConfirm.getText().toString().isEmpty()){
                txbPasswordConfirm.requestFocus();
                Toast.makeText(this, "No ha ingresado ninguna contraseña de confirmación.", Toast.LENGTH_SHORT).show();
            }

        }

    }


    private boolean ConsultarPersona() {

        boolean insertado = false;

        lstPersonas = null;

        lstPersonas = db.getArrayPersonas(
                db.getCursorPersona()
        );

        if (lstPersonas == null) {
            lstPersonas = new ArrayList<>();//si no hay datos
        }else{
            insertado = true;
        }

        return insertado;

    }
}
