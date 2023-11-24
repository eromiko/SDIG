package sdig.model;

import java.util.ArrayList;

public class KqOne {
    private String kqorgname;
    private int kqn;
    private int kqy;
    private ArrayList<KqZRtwo> kqzrlist=new ArrayList<KqZRtwo>();

    public String getKqorgname() {
        return kqorgname;
    }

    public void setKqorgname(String kqorgname) {
        this.kqorgname = kqorgname;
    }

    public int getKqn() {
        return kqn;
    }

    public void setKqn(int kqn) {
        this.kqn = kqn;
    }

    public int getKqy() {
        return kqy;
    }

    public void setKqy(int kqy) {
        this.kqy = kqy;
    }

    public ArrayList<KqZRtwo> getKqzrlist() {
        return kqzrlist;
    }

    public void setKqzrlist(ArrayList<KqZRtwo> kqzrlist) {
        this.kqzrlist = kqzrlist;
    }
}
