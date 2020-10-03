package interfaces;


import model.Fruit;
import model.RequisicaoObj;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
//.baseUrl("http://tropicalfruitandveg.com/")
public interface FruitService {
    @GET("api/tfvjsonapi.php?search=all")
    Call<RequisicaoObj> getAllFruits();

    @GET("api/tfvxmlapi.php?search=banana")
    Call<Fruit> getFruit();

    @GET("http://api.tropicalfruitandveg.com/tfvjsonapi.php?tfvitem={fruit}")
    Call<Fruit> getFruitDetail(@Path("fruit") String fruit);
}
