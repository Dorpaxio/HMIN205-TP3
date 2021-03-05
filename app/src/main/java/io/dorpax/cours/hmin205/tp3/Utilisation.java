package io.dorpax.cours.hmin205.tp3;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

public class Utilisation implements LifecycleObserver {

    private int nbUtilisations;

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private void nombreUtilisations() {
        ++nbUtilisations;
    }

    public int getNbUtilisations() {
        return nbUtilisations;
    }
}
