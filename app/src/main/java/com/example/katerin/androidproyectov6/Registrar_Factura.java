package com.example.katerin.androidproyectov6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.katerin.androidproyectov6.adapter1.EsRestaurante;
import com.example.katerin.androidproyectov6.adapter3.EsCliente;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class Registrar_Factura extends AppCompatActivity {
    Button crearres,veres,VerMenusResAD;
    Button edicuenta1;
    Button elicuenta1,controlar;
    TextView pago_total;
    TextView lugar_envio;
    TextView cantidad;
    TextView precios,correo,correoC;
    TextView item1;
    TextView cliente,_id;
    ImageButton atrasAD;
    EditText titulo,texto;
    Button enviar;
    String correo2;

    ArrayList<EsCliente> datos=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar__factura);

        elicuenta1 = findViewById(R.id.irpedidosfac);



        EsCliente item= new EsCliente();
        item.setEmail("email");
        datos.add(item);

        pago_total=(TextView) findViewById(R.id.pago_totalfac);
        pago_total.setText( getIntent().getExtras().getString("pago_total"));

        lugar_envio=(TextView) findViewById(R.id.lugar_enviofac);
        lugar_envio.setText( getIntent().getExtras().getString("lugar_envio"));

        cantidad=(TextView) findViewById(R.id.cantidadfac);
        cantidad.setText( getIntent().getExtras().getString("cantidad"));

        precios=(TextView) findViewById(R.id.preciosfac);
        precios.setText( getIntent().getExtras().getString("precios"));

        cliente=(TextView) findViewById(R.id.clientefac);
        cliente.setText( getIntent().getExtras().getString("cliente"));

        correo=(TextView) findViewById(R.id.idpedidofac);
        correo.setText( getIntent().getExtras().getString("correo"));

        correoC=(TextView) findViewById(R.id.clientefac2);
        correoC.setText( getIntent().getExtras().getString("correoC"));
/*
        _id=(TextView) findViewById(R.id.idpedidofac);
        _id.setText( getIntent().getExtras().getString("id"));*/

        item1= (TextView) findViewById(R.id.itemer);
        item1.setText(" __ FACTURA ELECTRONICA __"+"  "+" Pago Total =   "+ getIntent().getExtras().getString("pago_total")+ "           " +"   _________Informacion_________  :  " + "   "  +"  Cantidad del producto = "+" "
                + getIntent().getExtras().getString("cantidad")+""+""+"_Precio del Producto_ = "+""
                + getIntent().getExtras().getString("precios")+ "       "+"  Lugar de envio = "+""
                + getIntent().getExtras().getString("lugar_envio")+ "      "+ "    *MUCHAS GRACIAS POR SU COMPRA =)*    "
        );



        //titulo=(EditText) findViewById(R.id.titulofac);
       // texto=(EditText) findViewById(R.id.textofac);
        enviar=(Button) findViewById(R.id.irpedidosfac);

        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mandarMail();
            }
        });

//ocultar navegacion kato
        getSupportActionBar().hide();

    }
    private void mandarMail(){
        Intent email=new Intent(Intent.ACTION_SEND);
        email.setData(Uri.parse("mailto:"));
        email.setType("text/plain");
        email.putExtra(Intent.EXTRA_EMAIL,correo.getText());
        email.putExtra(Intent.EXTRA_CC,correo.getText());
        email.putExtra(Intent.EXTRA_BCC,correo.getText());
        email.putExtra(Intent.EXTRA_SUBJECT,lugar_envio.getText());
        email.putExtra(Intent.EXTRA_TEXT,item1.getText());

        startActivity(Intent.createChooser(email,"Send Email"));

    }


}