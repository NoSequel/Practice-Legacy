package wtf.retarders.practice.event;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import wtf.retarders.practice.match.IMatch;

@Getter
@Setter
public abstract class MatchEvent extends Event implements Cancellable {

    private boolean cancelled;
    private final IMatch<?> match;

    /**
     * main constructor
     *
     * @param match the match
     */
    public MatchEvent(IMatch<?> match) {
        this.match = match;
    }

    @Override
    public HandlerList getHandlers() {
        return new HandlerList();
    }
}
