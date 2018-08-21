package artemshumidub.ru.news.injection.scope;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import javax.inject.Scope;

/**
 * Scope : Per Activity
 *
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerActivity {
}

