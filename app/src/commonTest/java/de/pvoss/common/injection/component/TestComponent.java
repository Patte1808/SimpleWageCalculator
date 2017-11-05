package de.pvoss.common.injection.component;

import javax.inject.Singleton;

import dagger.Component;
import de.pvoss.common.injection.module.ApplicationTestModule;
import de.pvoss.injection.component.AppComponent;

@Singleton
@Component(modules = ApplicationTestModule.class)
public interface TestComponent extends AppComponent {
}
