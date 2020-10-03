package adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dijon.fruit.Detail_Activity;
import com.dijon.fruit.MainActivity;
import com.dijon.fruit.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import model.Fruit;
import model.RequisicaoObj;

public class AdapterFruit extends RecyclerView.Adapter<AdapterFruit.ViewHolder> {

    List<HashMap<String, String>> mapList;
    RequisicaoObj requisicaoObj;
    Fruit fruit;
    Context context;

    public AdapterFruit(List<HashMap<String, String>> mapList) {
        this.mapList = mapList;
    }

    public AdapterFruit() {
    }

    public AdapterFruit(Context context, RequisicaoObj requisicaoObj) {
        this.requisicaoObj = requisicaoObj;
        this.context = context;
    }
    //TODO prestar atencao aqui buscar fruit por item
    public AdapterFruit(Context context, Fruit fruit) {
        this.fruit = fruit;
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterFruit.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_fruits, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterFruit.ViewHolder holder, int position) {
        Fruit FruitObj = requisicaoObj.getResults().get(position);
        holder.bindView(FruitObj);
    }

    @Override
    public int getItemCount() {
        return requisicaoObj.getResults().size();
    }

    class ViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener{
        TextView tv_tfvname, tv_botname, tv_othname;
        ImageView iv_imageurl;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            tv_tfvname = itemView.findViewById(R.id.tv_tfvname);
            tv_botname = itemView.findViewById(R.id.tv_botname);
            tv_othname = itemView.findViewById(R.id.tv_othname);
            iv_imageurl = itemView.findViewById(R.id.iv_imageurl);
        }

        public void bindView(Fruit FruitObj) {
            tv_tfvname.setText(FruitObj.getTfvname());
            tv_botname.setText(FruitObj.getBotname());
            tv_othname.setText(FruitObj.getOthname());
            String imageurl = FruitObj.getImageurl();
            Picasso.with(context)
                    .load(imageurl)
                    .placeholder(R.drawable.ic_camera)
                    .error(R.drawable.ic_camera)
                    .into(iv_imageurl);


        }

        @Override
        public void onClick(View view) {
            Fruit fruit = new Fruit();
            fruit.setBotname(tv_botname.getText().toString());
            fruit.setClimate("");
            fruit.setDescription("");
            fruit.setHealth("");
            fruit.setImageurl("");
            fruit.setOthname(tv_othname.getText().toString());
            fruit.setPropagation("");
            fruit.setSoil("");
            fruit.setTfvname(tv_tfvname.getText().toString());
            fruit.setUses("");
            Bundle bundle = new Bundle();
            bundle.putSerializable("fruit", fruit);
            Intent intent = new Intent(view.getContext(), Detail_Activity.class);
            intent.putExtras(bundle);
            view.getContext().startActivity(intent);
        }
    }
}
