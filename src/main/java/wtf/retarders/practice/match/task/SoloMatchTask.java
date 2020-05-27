package wtf.retarders.practice.match.task;

import wtf.retarders.practice.match.types.SoloMatch;
import wtf.retarders.practice.task.AbstractTask;

public class SoloMatchTask extends AbstractTask {

    private final SoloMatch match;
    private int cooldown;

    /**
     * Constructor for creating a new SoloMatchTask
     *
     * @param match the math of the task
     */
    public SoloMatchTask(SoloMatch match) {
        super(20, 3);

        this.cooldown = 3;
        this.match = match;
    }

    @Override
    public String taskName() {
        return "Task-" + match.toString();
    }

    @Override
    public void tick() {
        if (cooldown > 0) {
            match.broadcast("&a" + cooldown + "...");
            cooldown = -1;
        }

        match.broadcast("&a&lMatch started!");
        this.cancel();
    }
}