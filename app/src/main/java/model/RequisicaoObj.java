package model;

import java.util.ArrayList;

public class RequisicaoObj {
    public ArrayList<Fruit> results = new ArrayList<>();
    private float tfvcount;

    public ArrayList<Fruit> getResults() {
        return results;
    }

    public void setResults(ArrayList<Fruit> results) {
        this.results = results;
    }

    public float getTfvcount() {
        return tfvcount;
    }

    public void setTfvcount( float tfvcount ) {
        this.tfvcount = tfvcount;
    }
}
