package io.dorpax.cours.hmin205.tp3;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class PlanningModel extends ViewModel {

    private final List<MutableLiveData<String>> slots = new ArrayList<>();

    private String intitules[] = {"Rencontre client Dupont", "Travailler dossier recrutement",
            "Réunion équipe", "Préparation dossier vente"};

    public PlanningModel() {
        for (String intitule : intitules) {
            MutableLiveData<String> liveData = new MutableLiveData<>();
            liveData.setValue(intitule);
            this.slots.add(liveData);
        }
    }

    public List<MutableLiveData<String>> getSlots() {
        return slots;
    }

    public void setSlot(int i, String value) {
        slots.get(i).postValue(value);
    }

    public MutableLiveData<String> getSlot(int i) {
        return slots.get(i);
    }
}
