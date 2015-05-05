package deadlockdetection;

import java.util.ArrayList;

public class Resource {

    private int id;
    private String name;
    private ArrayList<Process> processes;
    private boolean status;

    public Resource(int id, String name) {
        this.id = id;
        this.name = name;
        this.status = false; //False means is not beign used
        this.processes = new ArrayList();
    }

    public ArrayList<Process> getProcesses() {
        return processes;
    }
    
    public void setProcesses(Process process){
        this.processes.add(process);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean getStatus() {
        return status;
    }

    public boolean setStatus(boolean status) {
        boolean deadlockStatus;

        if (this.status && status) {
            deadlockStatus = true;
        } else {
            deadlockStatus = false;
            this.status = status;
        }

        return deadlockStatus;
    }

}
