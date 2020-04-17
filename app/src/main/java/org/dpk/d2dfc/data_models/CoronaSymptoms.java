package org.dpk.d2dfc.data_models;

public class CoronaSymptoms {
    private Symptoms fever, dryCough, fatigue, coughMucus,shortnessOfBreath,
            achesNPain,
            soreThroat,
            chillis,
            nausea,
            nasalCongestion,
            diarrhea;

    public CoronaSymptoms(Symptoms fever, Symptoms dryCough, Symptoms shortnessOfBreath) {
        this.fever = fever;
        this.dryCough = dryCough;
        this.shortnessOfBreath = shortnessOfBreath;
    }

    public Symptoms getFever() {
        return fever;
    }

    public void setFever(Symptoms fever) {
        this.fever = fever;
    }

    public Symptoms getDryCough() {
        return dryCough;
    }

    public void setDryCough(Symptoms dryCough) {
        this.dryCough = dryCough;
    }

    public Symptoms getFatigue() {
        return fatigue;
    }

    public void setFatigue(Symptoms fatigue) {
        this.fatigue = fatigue;
    }

    public Symptoms getCoughMucus() {
        return coughMucus;
    }

    public void setCoughMucus(Symptoms coughMucus) {
        this.coughMucus = coughMucus;
    }

    public Symptoms getShortnessOfBreath() {
        return shortnessOfBreath;
    }

    public void setShortnessOfBreath(Symptoms shortnessOfBreath) {
        this.shortnessOfBreath = shortnessOfBreath;
    }

    public Symptoms getAchesNPain() {
        return achesNPain;
    }

    public void setAchesNPain(Symptoms achesNPain) {
        this.achesNPain = achesNPain;
    }

    public Symptoms getSoreThroat() {
        return soreThroat;
    }

    public void setSoreThroat(Symptoms soreThroat) {
        this.soreThroat = soreThroat;
    }

    public Symptoms getChillis() {
        return chillis;
    }

    public void setChillis(Symptoms chillis) {
        this.chillis = chillis;
    }

    public Symptoms getNausea() {
        return nausea;
    }

    public void setNausea(Symptoms nausea) {
        this.nausea = nausea;
    }

    public Symptoms getNasalCongestion() {
        return nasalCongestion;
    }

    public void setNasalCongestion(Symptoms nasalCongestion) {
        this.nasalCongestion = nasalCongestion;
    }

    public Symptoms getDiarrhea() {
        return diarrhea;
    }

    public void setDiarrhea(Symptoms diarrhea) {
        this.diarrhea = diarrhea;
    }
}
