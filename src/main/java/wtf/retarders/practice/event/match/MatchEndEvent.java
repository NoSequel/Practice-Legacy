package wtf.retarders.practice.event.match;

import wtf.retarders.practice.event.MatchEvent;
import wtf.retarders.practice.match.IMatch;

public class MatchEndEvent extends MatchEvent {

    public MatchEndEvent(IMatch<?> match) {
        super(match);
    }
}
