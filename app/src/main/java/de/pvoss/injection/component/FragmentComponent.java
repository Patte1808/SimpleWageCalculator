package de.pvoss.injection.component;

import dagger.Subcomponent;
import de.pvoss.injection.PerFragment;
import de.pvoss.injection.module.FragmentModule;

/**
 * This component inject dependencies to all Fragments across the application
 */
@PerFragment
@Subcomponent(modules = FragmentModule.class)
public interface FragmentComponent {
}
