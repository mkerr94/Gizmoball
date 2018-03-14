package Model;

import Physics.Vect;

public  class CollisionDetails {
    private double tuc;
    private Vect velo;

    CollisionDetails(double t, Vect v) {
        tuc = t;
        velo = v;
    }

    public double getTuc() {
        return tuc;
    }

    public Vect getVelo() {
        return velo;
    }

}
