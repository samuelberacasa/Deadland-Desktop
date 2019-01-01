package Time;

public class Stopwatch {
    private long run;
    private long late;

    public Stopwatch(int frames){
        late = (1000/30)*frames;
        run = System.currentTimeMillis();
    }

    public boolean isTime(){
        long now = System.currentTimeMillis();
        if((now-run)>= late){
            run = now;
            return true;
        }
        return false;
    }
}
