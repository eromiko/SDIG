package sdig.model;

import java.util.ArrayList;
import java.util.Objects;

public class KqNamethree {
    private String name;
    private ArrayList<KqTypefour> KqTypelist=new ArrayList<KqTypefour>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KqNamethree that = (KqNamethree) o;
        return Objects.equals(name, that.name) && Objects.equals(KqTypelist, that.KqTypelist);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, KqTypelist);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<KqTypefour> getKqTypelist() {
        return KqTypelist;
    }

    public void setKqTypelist(ArrayList<KqTypefour> kqTypelist) {
        KqTypelist = kqTypelist;
    }
}
