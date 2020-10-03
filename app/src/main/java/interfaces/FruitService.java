package interfaces;


import model.Fruit;
import model.RequisicaoObj;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

//.baseUrl("http://tropicalfruitandveg.com/")
public interface FruitService {
    @GET("api/tfvjsonapi.php?search=all")
    Call<RequisicaoObj> getAllFruits();

    @GET("api/tfvxmlapi.php?search=banana")
    Call<Fruit> getFruit();

//    @GET("http://api.tropicalfruitandveg.com/tfvjsonapi.php?tfvitem={fruit}")
//    Call<Fruit> getFruitDetail(@Path("fruit") String fruit);
//http://tropicalfruitandveg.com/api/tfvjsonapi.php?tfvitem=banana
//    @GET("api/tfvjsonapi.php?tfvitem={fruit}")
    @GET("api/tfvjsonapi.php")
    Call<RequisicaoObj> getFruitDetail(@Query("tfvitem") String fruit);


}
