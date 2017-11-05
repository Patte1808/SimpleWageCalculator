package de.pvoss.features.edit;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;

import javax.inject.Inject;

import butterknife.BindView;
import de.pvoss.R;
import de.pvoss.features.base.BaseActivity;
import de.pvoss.injection.component.ActivityComponent;

/**
 * Created by Pattelicious on 05.11.17.
 */

public class EditActivity extends BaseActivity implements EditView {

    public static final String EDIT_WORKDAY = "EDIT_WORKDAY";

    @Inject
    EditPresenter presenter;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) actionBar.setDisplayHomeAsUpEnabled(true);
    }

    public static Intent getStartIntent(Context context, int workdayId) {
        Intent intent = new Intent(context, EditActivity.class);
        intent.putExtra(EDIT_WORKDAY, workdayId);
        return intent;
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_edit;
    }

    @Override
    protected void inject(ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }

    @Override
    protected void attachView() {
        presenter.attachView(this);
    }

    @Override
    protected void detachPresenter() {
        presenter.detachView();
    }
}
