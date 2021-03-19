package com.dijon.fruit;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import adapter.AdapterFruit;
import model.Fruit;
import model.RequisicaoObj;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/*
1) Escreva um aplicativo Android com duas atividades, na primeira, você deve:

liste todas as frutas tropicais e vegetais disponíveis nesta API
http://tropicalfruitandveg.com/api/tfvjsonapi.php?search=all
(apenas o nome de frutas ou vegetais é necessário nesta tela)

e uma caixa de pesquisa, portanto, se o usuário digitar na caixa de pesquisa
você deve filtrar os resultados na lista.

No segundo, você deve mostrar um tela detalhada
(nome, nomes de bot, outros nomes, imagem e descrição)
de cada fruta clicada usada na primeira tela, você precisará fazer uma solicitação para
http://api.tropicalfruitandveg.com/tfvjsonapi.php?tfvitem= <nome do item>
alterar <item_name> por um nome de fruta ou vegetal (por exemplo
http://api..com/tfvjsonapi.php?tfvitem=banana
 */
public class MainActivity extends AppCompatActivity {
    private final static String TAG = "MainActivity";
    AdapterFruit adapterFruit;
    RecyclerView rvList;
    LinearLayoutManager linearLayoutManager;
    Call<RequisicaoObj> call;
    RequisicaoObj requisicaoObj;
    SearchView searchView;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        searchFruits();
    }

    public void searchFruits() {
        linearLayoutManager = new LinearLayoutManager(this);
        rvList = findViewById(R.id.rvList);
        rvList.setHasFixedSize(true);
        rvList.setLayoutManager(linearLayoutManager);
        rvList.setFitsSystemWindows(true);
        call = new RetrofitConfig().getFruitService().getAllFruits();
        call.enqueue(new Callback<RequisicaoObj>() {
            @Override
            public void onResponse(Call<RequisicaoObj> call, Response<RequisicaoObj> response) {
                requisicaoObj = response.body();
                adapterFruit = new AdapterFruit(MainActivity.this, requisicaoObj);
                rvList.setAdapter(adapterFruit);
                adapterFruit.setOnItemClickListener(new AdapterFruit.OnRecyclerViewItemClickListener() {
                    @Override
                    public void onRecyclerViewItemClicked(Fruit FruitObj) {
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("fruit", FruitObj);
                        Intent intent = new Intent(MainActivity.this, Detail_Activity.class);
                        intent.putExtras(bundle);
                        MainActivity.this.startActivity(intent);
                    }
                });
            }

            @Override
            public void onFailure(Call<RequisicaoObj> call, Throwable t) {
                Log.e(TAG, "No internet connection");
                Snackbar snackbar = Snackbar
                        .make(rvList, "No internet connection", Snackbar.LENGTH_LONG)
                        .setAction("Try", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                searchFruits();
                            }
                        });
                snackbar.show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_fruits_and_veg, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                try {
                    adapterFruit.getFilter().filter(s);
                } catch (NullPointerException e) {
                    Snackbar snackbar = Snackbar
                            .make(rvList, "No internet connection", Snackbar.LENGTH_LONG)
                            .setAction("Try", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    searchFruits();
                                }
                            });
                    snackbar.show();
                }
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                try {
                    adapterFruit.getFilter().filter(s);
                } catch (NullPointerException e) {
                    Snackbar snackbar = Snackbar
                            .make(rvList, "No internet connection", Snackbar.LENGTH_LONG)
                            .setAction("Try", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    searchFruits();
                                }
                            });
                    snackbar.show();
                }
                return true;
            }
        });
        return true;
    }

}