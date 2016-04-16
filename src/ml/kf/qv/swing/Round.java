package ml.kf.qv.swing;

/**
 *
 * @author Kanfa.
 *
 */
public class Round {


    private int arcx;
    private int arcy;

    private static int DEFAULT_ARCX = 5;
    private static int DEFAULT_ARCY = 5;

    public Round(int arcx, int arcy){
        this.arcx = arcx;
        this.arcy = arcy;
    }

    public Round(){
        this(DEFAULT_ARCX, DEFAULT_ARCY);
    }

    public int getArcx(){
        return this.arcx;
    }

    public void setArcx(int arcx){
        this.arcx = arcx;
    }

    public int getArcy(){
        return this.arcy;
    }

    public void setArcy(int arcy){
        this.arcy = arcy;
    }
}
