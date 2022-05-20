package Model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/*
If a model is the parent of all listeners, it should extend and use this class. It contains all of the necessary
information to be able to have observers to it.
*/
public abstract class ModelEntity {
    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        this.pcs.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        this.pcs.removePropertyChangeListener(listener);
    }

    public void firePropertyChange(String propertyName) {
        this.pcs.firePropertyChange(propertyName, null, null);
    }

}
