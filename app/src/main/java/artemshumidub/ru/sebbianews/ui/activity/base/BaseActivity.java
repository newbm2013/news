package artemshumidub.ru.sebbianews.ui.activity.base;

import android.support.v7.app.AppCompatActivity;

import artemshumidub.ru.sebbianews.NewsApp;
import artemshumidub.ru.sebbianews.injection.component.ActivityComponent;
import artemshumidub.ru.sebbianews.injection.component.DaggerActivityComponent;
import artemshumidub.ru.sebbianews.injection.module.ActivityModule;

public abstract class BaseActivity extends AppCompatActivity implements IViewContract{

    private ActivityComponent mActivityComponent;

    protected ActivityComponent getActivityComponent() {
        if (mActivityComponent == null) {
            //noinspection deprecation
            mActivityComponent = DaggerActivityComponent.builder()
                    .activityModule(new ActivityModule(this))
                    .applicationComponent(NewsApp.get(this).getComponent())
                    .build();
        }
        return mActivityComponent;
    }
}
