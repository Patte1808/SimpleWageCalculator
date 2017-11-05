package de.pvoss;

import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.RuleChain;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;

import de.pvoss.common.TestComponentRule;
import de.pvoss.common.TestDataFactory;
import de.pvoss.data.model.response.Workday;
import de.pvoss.data.model.response.Statistic;
import de.pvoss.features.detail.DetailActivity;
import de.pvoss.util.ErrorTestUtil;
import io.reactivex.Single;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(AndroidJUnit4.class)
public class DetailActivityTest {

    public final TestComponentRule component =
            new TestComponentRule(InstrumentationRegistry.getTargetContext());
    public final ActivityTestRule<DetailActivity> main =
            new ActivityTestRule<>(DetailActivity.class, false, false);

    // TestComponentRule needs to go first to make sure the Dagger ApplicationTestComponent is set
    // in the Application before any Activity is launched.
    @Rule
    public TestRule chain = RuleChain.outerRule(component).around(main);

    @Test
    public void checkPokemonDisplays() {
        Workday workday = TestDataFactory.makePokemon("id");
        stubDataManagerGetPokemon(Single.just(workday));
        main.launchActivity(
                DetailActivity.getStartIntent(InstrumentationRegistry.getContext(), workday.name));

        for (Statistic stat : workday.stats) {
            onView(withText(stat.stat.name)).check(matches(isDisplayed()));
        }
    }

    @Test
    public void checkErrorViewDisplays() {
        stubDataManagerGetPokemon(Single.error(new RuntimeException()));
        Workday workday = TestDataFactory.makePokemon("id");
        main.launchActivity(
                DetailActivity.getStartIntent(InstrumentationRegistry.getContext(), workday.name));
        ErrorTestUtil.checkErrorViewsDisplay();
    }

    public void stubDataManagerGetPokemon(Single<Workday> single) {
        when(component.getMockApiManager().getPokemon(anyString())).thenReturn(single);
    }
}
