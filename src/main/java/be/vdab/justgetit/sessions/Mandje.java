package be.vdab.justgetit.sessions;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.io.Serializable;
import java.util.*;

@Component
@SessionScope
public class Mandje implements Serializable {
    private final long serialVersionUID = 1L;
    private final Map<Long, Integer> mandje = new LinkedHashMap<>();
    private boolean gevuld = false;

    public void put (long id, int aantal) {
        mandje.put(id, aantal);
        this.gevuld = true;
    }

    public boolean contains (long id) {
        return mandje.containsKey(id);
    }

    public boolean isGevuld () {
        return gevuld;
    }

    public void remove (long id) {
        if (mandje.size() == 1) {
            mandje.remove(id);
            this.gevuld = false;
        } else {
            mandje.remove(id);
        }
    }

    public Map<Long, Integer> getMandje () {
        return Collections.unmodifiableMap(mandje);
    }
}
