package de.pvoss;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import de.pvoss.common.TestDataFactory;
import de.pvoss.data.DataManager;
import de.pvoss.data.model.response.Workday;
import de.pvoss.features.detail.DetailMvpView;
import de.pvoss.features.detail.DetailPresenter;
import de.pvoss.util.RxSchedulersOverrideRule;
import io.reactivex.Single;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by ravindra on 24/12/16.
 */
@RunWith(MockitoJUnitRunner.class)
public class DetailPresenterTest {

    @Rule
    public final RxSchedulersOverrideRule overrideSchedulersRule = new RxSchedulersOverrideRule();

    @Mock
    DetailMvpView mockDetailMvpView;
    @Mock
    DataManager mockDataManager;
    private DetailPresenter detailPresenter;

    @Before
    public void setUp() {
        detailPresenter = new DetailPresenter(mockDataManager);
        detailPresenter.attachView(mockDetailMvpView);
    }

    @After
    public void tearDown() {
        detailPresenter.detachView();
    }

    @Test
    public void getPokemonDetailReturnsPokemon() throws Exception {
        Workday workday = TestDataFactory.makePokemon("id");
        when(mockDataManager.getPokemon(anyString())).thenReturn(Single.just(workday));

        detailPresenter.getPokemon(anyString());

        verify(mockDetailMvpView, times(2)).showProgress(anyBoolean());
        verify(mockDetailMvpView).showPokemon(workday);
        verify(mockDetailMvpView, never()).showError(any(Throwable.class));
    }

    @Test
    public void getPokemonDetailReturnsError() throws Exception {
        when(mockDataManager.getPokemon("id")).thenReturn(Single.error(new RuntimeException()));

        detailPresenter.getPokemon("id");

        verify(mockDetailMvpView, times(2)).showProgress(anyBoolean());
        verify(mockDetailMvpView).showError(any(Throwable.class));
        verify(mockDetailMvpView, never()).showPokemon(any(Workday.class));
    }
}
