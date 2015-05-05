package deadlockdetection;

import java.util.ArrayList;

public class DeadlockDetection {

    ArrayList<Resource> resources;
    ArrayList<Process> processes;

    ArrayList<Process> busyProcesses;
    boolean deadlock;

    public DeadlockDetection() {
        deadlock = false;
        resources = new ArrayList();
        processes = new ArrayList();
        busyProcesses = new ArrayList();
        startResources();
        startProcesses();
        //Now Let's asign resources to process
    }

    public void startResources() {
        resources.add(new Resource(1, "A"));
        resources.add(new Resource(2, "B"));
        resources.add(new Resource(3, "C"));
        resources.add(new Resource(4, "D"));
        resources.add(new Resource(5, "E"));

        System.out.println("Resources initialized: ");
        for (int i = 0; i < resources.size(); i++) {
            System.out.println("ID: " + resources.get(i).getId() + "\tNAME: " + resources.get(i).getName());
        }

        System.out.println("\n");
    }

    public void startProcesses() {
        processes.add(new Process(0, "P0"));
        processes.add(new Process(1, "P1"));
        processes.add(new Process(2, "P2"));
        processes.add(new Process(3, "P3"));
        processes.add(new Process(4, "P4"));

        System.out.println("Processes initialized: ");
        for (int i = 0; i < processes.size(); i++) {
            System.out.println("ID: " + processes.get(i).getId() + "\tNAME: " + processes.get(i).getName());
        }

        System.out.println("\n");
    }

    public void isDeadLock() {
        processes.get(0).setResource(resources.get(0));
        processes.get(1).setResource(resources.get(1));

        resources.get(0).setProcesses(processes.get(1));
        resources.get(1).setProcesses(processes.get(0));
    }

    public void noDeadLock() {
        processes.get(0).setResource(resources.get(1));
        processes.get(1).setResource(resources.get(2));
        processes.get(2).setResource(resources.get(3));
        processes.get(0).setResource(resources.get(0));
    }
    
    public void process(){
        int i = 0;
        int nodeSize = processes.size();
        
        while(i <nodeSize){
            if(!DetectDeadlock(processes.get(i))){
               processes.set(i,null);
            }
            i++;
        }
        
        if(deadlock){
            System.out.println("Deadlock Detected");
        } else {
            System.out.println("Deadlock not Detected");
        }
    }

    public boolean DetectDeadlock(Process process) {
        //Here we detect if the process is already busy and if then is deadlocked
        if(process != null){
            busyProcesses.add(process);
            ArrayList<Resource> nodeResources = process.getResources();
            
            try {
                for (int i = 0; i < nodeResources.size(); i++) {
                    ArrayList<Process> nodeProcesses = nodeResources.get(i).getProcesses();
                    for (int j = 0; j < nodeProcesses.size(); j++) {
                        DetectDeadlock(nodeProcesses.get(j));
                    }
                }
            } catch(StackOverflowError t) {
                //If a stack overflow exception is catched is because there's a processes and resources cycle
                deadlock = true;
            }
               
        }
        
        return detectDuplicate();        
    }
    
    public boolean detectDuplicate(){
        int count = 0;
        boolean duplicate = false;
        Process process;
        
        for (int i = 0; i < busyProcesses.size() && !duplicate; i++) {
            process = busyProcesses.get(i);
            
            for (int j = 0; j < busyProcesses.size(); j++) {
                if(busyProcesses.get(j)==process)
                    count++;
            }
            
            if(count>=2)
                duplicate = true;
            else
                count = 0;
            
        }
        
        return duplicate;
    }

    public void printProcesses() {
        for (int i = 0; i < processes.size(); i++) {
            Process process = processes.get(i);
            System.out.print("Process " + process.getId() + ": ");
            if (process.isDeadlockStatus()) {
                System.out.print("Process in DeadLock");
                System.out.println("\n");
            } else {
                System.out.print("Process completed");
                System.out.println("\n");
            };
        }
    }

    public static void main(String[] args) {
        DeadlockDetection dd = new DeadlockDetection();
        dd.isDeadLock();
        //dd.noDeadLock();
        dd.process();
    }

}
