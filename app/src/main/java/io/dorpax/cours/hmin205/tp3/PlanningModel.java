package io.dorpax.cours.hmin205.tp3;

import androidx.lifecycle.ViewModel;

public class PlanningModel extends ViewModel {

    private String slots[] = {"Rencontre client Dupont", "Travailler dossier recrutement",
            "Réunion équipe", "Préparation dossier vente"};


    public String[] getSlots() {
        return slots;
    }

    public String getSlot(int i) {
        return slots[i];
    }
}
