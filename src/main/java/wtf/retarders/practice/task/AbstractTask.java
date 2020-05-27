package wtf.retarders.practice.task;

import org.bukkit.scheduler.BukkitRunnable;
import wtf.retarders.practice.PracticePlugin;

public abstract class AbstractTask extends BukkitRunnable {

    /**
     * Constructor for creating a new task
     *
     * @param delay the delay between ticks
     * @param duration the duration of the
     */
    public AbstractTask(long delay, long duration) {
        this.runTaskTimer(PracticePlugin.getPlugin(), delay, duration);
    }

    /**
     * Call the tick method
     */
    public void run() {
        try {
            this.tick();
        } catch (Exception $exception) {
            final RuntimeException exception = new RuntimeException(taskName() + " caused an exception in " + Thread.currentThread().getName());
            exception.setStackTrace($exception.getStackTrace());

            throw exception;
        }
    }


    /**
     * Get the name of the task
     *
     * @return the name
     */
    public abstract String taskName();

    /**
     * Called every tick of the task.
     */
    public abstract void tick();


}