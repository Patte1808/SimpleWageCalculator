package de.pvoss.data;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import de.pvoss.data.model.response.Workday;
import de.pvoss.data.remote.WageService;
import io.reactivex.Single;

/**
 * Created by shivam on 29/5/17.
 */
@Singleton
public class DataManager {

    private WageService wageService;

    @Inject
    public DataManager(WageService wageService) {
        this.wageService = wageService;
    }

    public Single<List<String>> getPokemonList(int limit) {
        return wageService
                .getPokemonList(limit)
                .toObservable()
                .flatMapIterable(namedResources -> namedResources.results)
                .map(namedResource -> namedResource.name)
                .toList();
    }

    public Single<Workday> getPokemon(String name) {
        return wageService.getPokemon(name);
    }
}
