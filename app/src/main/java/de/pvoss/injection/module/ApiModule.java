package de.pvoss.injection.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import de.pvoss.data.remote.WageService;
import retrofit2.Retrofit;

/**
 * Created by shivam on 29/5/17.
 */
@Module(includes = {NetworkModule.class})
public class ApiModule {

    @Provides
    @Singleton
    WageService providePokemonApi(Retrofit retrofit) {
        return retrofit.create(WageService.class);
    }
}
