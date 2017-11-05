package de.pvoss.features.detail;

import de.pvoss.data.model.response.Workday;
import de.pvoss.data.model.response.Statistic;
import de.pvoss.features.base.MvpView;

public interface DetailMvpView extends MvpView {

    void showPokemon(Workday workday);

    void showStat(Statistic statistic);

    void showProgress(boolean show);

    void showError(Throwable error);
}
