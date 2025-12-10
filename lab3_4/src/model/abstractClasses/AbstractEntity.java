package model.abstractClasses;

import utils.IdGen;

import java.util.Objects;

public abstract class AbstractEntity {
    protected String id;
    protected String name;
    protected String location;

    public AbstractEntity(String name, String location){
        this.id = IdGen.generateId(name);
        this.name = name;
        this.location = location;
    }

    public String getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public abstract String toString();
    
    @Override
    public int hashCode(){
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object object){
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        AbstractEntity that = (AbstractEntity) object;
        return Objects.equals(id, that.id);
    }

}
