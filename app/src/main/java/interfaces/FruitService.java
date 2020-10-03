package interfaces;


import model.RequisicaoObj;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

//.baseUrl("http://tropicalfruitandveg.com/")
public interface FruitService {
    @GET("api/tfvjsonapi.php?search=all")
    Call<RequisicaoObj> getAllFruits();

    @GET("api/tfvxmlapi.php")
    Call<RequisicaoObj> getSearch(@Query("search") String itemSearch);

    @GET("api/tfvjsonapi.php")
    Call<RequisicaoObj> getFruitDetail(@Query("tfvitem") String item);


}
