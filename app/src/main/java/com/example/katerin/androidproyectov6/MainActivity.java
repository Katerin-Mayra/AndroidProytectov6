package com.example.katerin.androidproyectov6;

import android.content.Intent;
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
    Button login,Ver_Restaurante;
    Button registrarse;
    Button registrar_restaurante;
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
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow,R.id.nav_login,R.id.nav_restaurante)
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
        registrarse = findViewById(R.id.register2);
        login=this.findViewById(R.id.login);
        registrar_restaurante=this.findViewById(R.id.Registrar_Restaurante);

        registrar_restaurante.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent registrar_restauranteActivity =new Intent(root,Registrar_Restaurant.class);
                root.startActivity(registrar_restauranteActivity);
            }

        });


        registrarse.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent Registrar_UsuarioActivity =new Intent(root,Registrar_Usuario.class);
                root.startActivity(Registrar_UsuarioActivity);
            }

        });


        Ver_Restaurante.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Ver_RestauranteActivity =new Intent(root,Ver_Restaurante1.class);
                root.startActivity(Ver_RestauranteActivity);

            }
        }));
        login.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent LoginActivity =new Intent(root,login.class);
                root.startActivity(LoginActivity);

            }
        }));


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