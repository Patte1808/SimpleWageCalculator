package de.pvoss.data.remote;

import de.pvoss.data.model.response.Workday;
import de.pvoss.data.model.response.PokemonListResponse;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface WageService {

    @GET("pokemon")
    Single<PokemonListResponse> getPokemonList(@Query("limit") int limit);

    @GET("pokemon/{name}")
    Single<Workday> getPokemon(@Path("name") String name);
}
