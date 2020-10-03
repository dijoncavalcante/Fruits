package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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

    @NonNull
    @Override
    public AdapterFruit.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_fruits, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterFruit.ViewHolder holder, int position) {
//        HashMap<String, String> map = mapList.get(position);
//        holder.bindView(map);

        Fruit FruitObj = requisicaoObj.getResults().get(position);
        holder.bindView(FruitObj);
    }

    @Override
    public int getItemCount() {
        return requisicaoObj.getResults().size();// mapList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_tfvname, tv_botname, tv_othname;
        ImageView iv_imageurl;

        public ViewHolder(View itemView) {
            super(itemView);
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
    }
}
