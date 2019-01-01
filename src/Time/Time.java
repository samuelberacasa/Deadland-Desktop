package Time;

public class Time {
    private long previousTime;
    private long totalTime;
    private long maxDeltaTime;

    public Time(){
        this.previousTime = 0;
        this.totalTime = 0;
        this.maxDeltaTime = 0;
    }

    public void calculateTime(){
        if(this.previousTime == 0){
            previousTime = System.currentTimeMillis();
        }
        long currentTime = System.currentTimeMillis();
        long deltaTime = currentTime - this.previousTime;
        if(deltaTime > this.maxDeltaTime){
            this.maxDeltaTime = deltaTime;
        }
        this.previousTime = currentTime;
        this.totalTime += deltaTime;
    }
}
