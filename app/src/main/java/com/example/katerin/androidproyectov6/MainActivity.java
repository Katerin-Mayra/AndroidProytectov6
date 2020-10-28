package com.example.katerin.androidproyectov6;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.Menu;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {
    Button login,Ver_Restaurante,mapa,img,img2;
    Button registrarse;
    Button registrar_restaurante;
    Button menus,registrarMenu;
    Button pedido,registrarPedido;
    static final int code_camera=999;
    private MainActivity root=this;
    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://www.facebook.com/"));
                startActivity(intent);
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home,R.id.nav_login,R.id.nav_registrar_Usuario,R.id.nav_ver_Restaurantes_MAIN)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        //ICONO
        this.getSupportActionBar().setDisplayShowHomeEnabled(true);
        this.getSupportActionBar().setIcon(R.drawable.ic_home_black_24dp);


        Toast.makeText(this, "OnCreate",Toast.LENGTH_LONG).show();
        loadcomponents();
    }

    private void loadcomponents() {
        Ver_Restaurante=this.findViewById(R.id.restaurantes);
        img=this.findViewById(R.id.img);
        img2=this.findViewById(R.id.img2);
       // mapa=this.findViewById(R.id.mapa);

     //   registrarse = findViewById(R.id.register2);
      //  login=this.findViewById(R.id.login);
       /*
        registrar_restaurante=this.findViewById(R.id.Registrar_Restaurante);
        menus=this.findViewById(R.id.menus);
        registrarMenu=this.findViewById(R.id.registroMenus);
        pedido=this.findViewById(R.id.verPedidoMA);
        registrarPedido=this.findViewById(R.id.registrarPedidoMA);*/
/*
        registrarPedido.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent registrar_restauranteActivity =new Intent(root,CrearMenu.class);
                root.startActivity(registrar_restauranteActivity);
            }

        });

        pedido.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent registrar_restauranteActivity =new Intent(root,Ver_menus.class);
                root.startActivity(registrar_restauranteActivity);
            }

        });


        registrarMenu.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent registrar_restauranteActivity =new Intent(root,CrearMenu.class);
                root.startActivity(registrar_restauranteActivity);
            }

        });

        menus.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent registrar_restauranteActivity =new Intent(root,Ver_menus.class);
                root.startActivity(registrar_restauranteActivity);
            }

        });


        registrar_restaurante.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent registrar_restauranteActivity =new Intent(root,Registrar_Restaurant.class);
                root.startActivity(registrar_restauranteActivity);
            }

        });*/

        Ver_Restaurante.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Ver_RestauranteActivity =new Intent(root,Ver_Restaurantes_MAIN.class);
                root.startActivity(Ver_RestauranteActivity);

            }
        }));

        img.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ver_img =new Intent(root,pruebaimg.class);
                root.startActivity(ver_img);

            }
        }));

        img2.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ver_img =new Intent(root,FotoRestaurante.class);
                root.startActivity(ver_img);

            }
        }));


        /*
        mapa.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pmapa =new Intent(root,pruebaemail.class);
                root.startActivity(pmapa);

            }
        }));*/

       /* registrarse.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent Registrar_UsuarioActivity =new Intent(root,Registrar_Usuario.class);
                root.startActivity(Registrar_UsuarioActivity);
            }

        });

*//*

        login.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent LoginActivity =new Intent(root,login.class);
                root.startActivity(LoginActivity);

            }
        }));*/


        /*imagen1.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ////CAMARA////
                Intent camera = new Intent (MediaStore.ACTION_IMAGE_CAPTURE);
                if(camera.resolveActivity(root.getPackageManager())!=null){
                    root.startActivityForResult(camera,code_camera);
                }
            }
        }));*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}