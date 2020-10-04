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

import org.w3c.dom.Text;

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

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterFruit.ViewHolder holder, final int position) {
        Fruit FruitObj = requisicaoObj.getResults().get(position);
        holder.bindView(FruitObj);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView tv_name = (TextView) view.findViewById(R.id.tv_tfvname);
                fruit = new Fruit();
                fruit.setTfvname(tv_name.getText().toString());
                listener.onRecyclerViewItemClicked(fruit);
            }
        });
    }

    @Override
    public int getItemCount() {
        return requisicaoObj.getResults().size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_tfvname;
        ImageView iv_imageurl;
        View.OnClickListener onClickListener;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_tfvname = itemView.findViewById(R.id.tv_tfvname);
            iv_imageurl = itemView.findViewById(R.id.iv_imageurl);
        }

        public void bindView(Fruit FruitObj) {
            tv_tfvname.setText(FruitObj.getTfvname());
            String imageurl = FruitObj.getImageurl();
            Picasso.with(iv_imageurl.getContext())
                    .load(imageurl)
                    .placeholder(R.drawable.ic_camera)
                    .error(R.drawable.ic_camera)
                    .into(iv_imageurl);
        }
    }

    public interface AdapterServiceInterface {
        void onClick(View view);

        void onItemClick(int position);
    }

    public interface OnRecyclerViewItemClickListener {
        void onRecyclerViewItemClicked(Fruit FruitObj);
    }
}
