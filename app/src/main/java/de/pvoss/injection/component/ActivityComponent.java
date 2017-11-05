package de.pvoss.injection.component;

import dagger.Subcomponent;
import de.pvoss.features.detail.DetailActivity;
import de.pvoss.features.edit.EditActivity;
import de.pvoss.features.main.MainActivity;
import de.pvoss.injection.PerActivity;
import de.pvoss.injection.module.ActivityModule;

@PerActivity
@Subcomponent(modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(MainActivity mainActivity);

    void inject(DetailActivity detailActivity);

    void inject(EditActivity editActivity);
}
