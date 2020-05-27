package wtf.retarders.practice.event.match;

import wtf.retarders.practice.event.MatchEvent;
import wtf.retarders.practice.match.IMatch;

public class MatchStartEvent extends MatchEvent {

    public MatchStartEvent(IMatch<?> match) {
        super(match);
    }
}
