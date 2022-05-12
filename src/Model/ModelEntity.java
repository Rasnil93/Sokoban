package Model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

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
