package sdig.model;

import java.util.ArrayList;
import java.util.Objects;

public class KqZRtwo {
    private String kqz;
    private String kqr;
    private ArrayList<KqNamethree> kqnamelist=new ArrayList<KqNamethree>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KqZRtwo kqZRtwo = (KqZRtwo) o;
        return Objects.equals(kqz, kqZRtwo.kqz) && Objects.equals(kqr, kqZRtwo.kqr) && Objects.equals(kqnamelist, kqZRtwo.kqnamelist);
    }

    @Override
    public int hashCode() {
        return Objects.hash(kqz, kqr, kqnamelist);
    }

    public String getKqz() {
        return kqz;
    }

    public void setKqz(String kqz) {
        this.kqz = kqz;
    }

    public String getKqr() {
        return kqr;
    }

    public void setKqr(String kqr) {
        this.kqr = kqr;
    }

    public ArrayList<KqNamethree> getKqnamelist() {
        return kqnamelist;
    }

    public void setKqnamelist(ArrayList<KqNamethree> kqnamelist) {
        this.kqnamelist = kqnamelist;
    }
}
