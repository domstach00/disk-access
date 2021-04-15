package projekt;

public class Order {
    private String name;
    private int priority;
    private int deadline;
    int position;
    boolean isRealTime;

    public Order(int i) {
        this.name = "Process_"+i;
        this.position = createRandom(100);
        this.isRealTime = createRealTime();
        this.priority = createRandom(10);
        this.deadline = createRandom(100);
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public boolean isRealTime() {
        return isRealTime;
    }

    public void setRealTime(boolean realTime) {
        isRealTime = realTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getDeadline() {
        return deadline;
    }

    public void setDeadline(int deadline) {
        this.deadline = deadline;
    }



    public int createRandom(int x){
        int res = (int)(Math.random()*x);
        if (res==0)
            return 1;
        else
            return res;
    }

    public boolean createRealTime(){

        if (Math.random()<=0.2) { //prawdopodobienstwo 20%
            return true;
        }
        else
            return false;

    }


    @Override
    public String toString() {
        return name +
                ", position: " + position +
                ", deadline: " + deadline +
                ", priority: " + priority +
                ", isRealTime: " + isRealTime;
    }
}
