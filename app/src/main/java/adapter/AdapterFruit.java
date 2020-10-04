package adapter;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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
import retrofit2.Callback;

public class AdapterFruit extends RecyclerView.Adapter<AdapterFruit.ViewHolder> {

    List<HashMap<String, String>> mapList;
    RequisicaoObj requisicaoObj;
    Fruit fruit;
    Context context;
    OnRecyclerViewItemClickListener listener;

    public AdapterFruit(List<HashMap<String, String>> mapList) {
        this.mapList = mapList;
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

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener)
    {
        this.listener = listener;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterFruit.ViewHolder holder, final int position) {

        Fruit FruitObj = requisicaoObj.getResults().get(position);
        holder.bindView(FruitObj);
        //holder.iv_imageurl.setOnClickListener(holder.iv_imageurl.setOnClickListener(MainActivity.ExtraData.class.getEve));

        holder.iv_imageurl.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                listener.onRecyclerViewItemClicked(position, -1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return requisicaoObj.getResults().size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_tfvname, tv_botname, tv_othname;
        ImageView iv_imageurl;
        View.OnClickListener onClickListener;

        public ViewHolder(View itemView) {
            super(itemView);
            //itemView.setOnClickListener(this);
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
            Picasso.with(iv_imageurl.getContext())
                    .load(imageurl)
                    .placeholder(R.drawable.ic_camera)
                    .error(R.drawable.ic_camera)
                    .into(iv_imageurl);
        }
//        @Override
//        public void onClick(View view) {
//            Fruit fruit = new Fruit();
//            fruit.setBotname(tv_botname.getText().toString());
//            fruit.setClimate("");
//            fruit.setDescription("");
//            fruit.setHealth("");
//            fruit.setImageurl("");
//            fruit.setOthname(tv_othname.getText().toString());
//            fruit.setPropagation("");
//            fruit.setSoil("");
//            fruit.setTfvname(tv_tfvname.getText().toString());
//            fruit.setUses("");
//            Bundle bundle = new Bundle();
//            bundle.putSerializable("fruit", fruit);
//            Intent intent = new Intent(view.getContext(), Detail_Activity.class);
//            intent.putExtras(bundle);
//            view.getContext().startActivity(intent);
//        }
    }

    public interface AdapterServiceInterface {
        void onClick(View view);

        void onItemClick(int position);
    }

    public interface OnRecyclerViewItemClickListener {
        public void onRecyclerViewItemClicked(int position, int id);
    }
}
