package com.dijon.fruit;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import model.Fruit;
import model.RequisicaoObj;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Detail_Activity extends AppCompatActivity {
    private final static String TAG = "Detail_Activity";
    ImageView iv_imageurlDetail;
    TextView tv_tfvnameDetail, tv_botnameDetail, tv_othnameDetail, tv_climateDetail, tv_descriptionDetail, tv_healthDetail, tv_propagationDetail, tv_soilDetail, tv_usesDetail;
    Call<RequisicaoObj> call;
    RequisicaoObj requisicaoObj;
    Fruit fruit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        iv_imageurlDetail = findViewById(R.id.iv_imageurlDetail);
        tv_tfvnameDetail = findViewById(R.id.tv_tfvnameDetail);
        tv_botnameDetail = findViewById(R.id.tv_botnameDetail);
        tv_othnameDetail = findViewById(R.id.tv_othnameDetail);
        tv_climateDetail = findViewById(R.id.tv_climateDetail);
        tv_descriptionDetail = findViewById(R.id.tv_descriptionDetail);
        tv_healthDetail = findViewById(R.id.tv_healthDetail);
        tv_propagationDetail = findViewById(R.id.tv_propagationDetail);
        tv_soilDetail = findViewById(R.id.tv_soilDetail);
        tv_usesDetail = findViewById(R.id.tv_usesDetail);

        final Fruit fruit_ = (Fruit) getIntent().getSerializableExtra("fruit");
        if (fruit_ != null) {
            call = new RetrofitConfig().getFruitService().getFruitDetail(fruit_.getTfvname());
            call.enqueue(new Callback<RequisicaoObj>() {
                @Override
                public void onResponse(Call<RequisicaoObj> call, Response<RequisicaoObj> response) {
                    Log.i(TAG, "onResponse: ");
                    requisicaoObj = response.body();
                    Fruit[] teste = requisicaoObj.getResults().toArray(new Fruit[requisicaoObj.getResults().size()]);
                    ArrayList<Fruit> fruitArrayList = requisicaoObj.getResults();
                    Picasso.with(iv_imageurlDetail.getContext())
                            .load(teste[0].getImageurl())
                            .placeholder(R.drawable.ic_camera)
                            .error(R.drawable.ic_camera)
                            .into(iv_imageurlDetail);

                    tv_botnameDetail.setText(teste[0].getBotname());
                    tv_climateDetail.setText(teste[0].getClimate());
                    tv_descriptionDetail.setText(teste[0].getDescription());
                    tv_healthDetail.setText(teste[0].getHealth());
                    tv_othnameDetail.setText(teste[0].getOthname());
                    tv_propagationDetail.setText(teste[0].getPropagation());
                    tv_soilDetail.setText(teste[0].getSoil());
                    tv_tfvnameDetail.setText(teste[0].getTfvname());
                    tv_usesDetail.setText(teste[0].getUses());
                }

                @Override
                public void onFailure(Call<RequisicaoObj> call, Throwable t) {
                    Log.e(TAG, "No internet connection");
                }
            });
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}