package model.exceptions;

import model.entities.Worker;

public class WorkerNotFoundException extends Exception{
    private final Worker worker;

    public WorkerNotFoundException(Worker worker){
        super("Error");
        this.worker = worker;
    }

    @Override
    public String toString(){
        return String.format("Работника %s нет на данной барже", worker.getName());
    }
}
