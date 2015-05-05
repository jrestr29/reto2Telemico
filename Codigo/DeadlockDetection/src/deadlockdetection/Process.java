package deadlockdetection;

import java.util.ArrayList;

public class Process {
    private int id;
    private String name;
    private ArrayList<Resource> resource;
    private boolean deadlockStatus;

    public Process(int id, String name) {
        deadlockStatus = false;
        resource = new ArrayList();

        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isDeadlockStatus() {
        return deadlockStatus;
    }

    public ArrayList<Resource> getResources() {
        return resource;
    }

    public void setResource(Resource resource) {
        if (!deadlockStatus) {
            deadlockStatus = resource.setStatus(true);
            this.resource.add(resource);
        }
    }
    
    public void printStatus (){
        if(!deadlockStatus){
            System.out.println("Process in DeadLock");
        } else {
            System.out.println("Process completed");
        }
    }

}