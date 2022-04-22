package fabric.mod.planc_.opleather.schedulers;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class EndWorldTickScheduler {
    public static final ConcurrentHashMap<UUID, Task> DELAYED_TASK = new ConcurrentHashMap<>();

    public static void addDelayedTask(final int delayForTicksDuration, Runnable task) {
        DELAYED_TASK.put(UUID.randomUUID(), new Task(delayForTicksDuration, task));
    }

    public static void tickAllEvents() {
        DELAYED_TASK.entrySet().stream().filter(uuidTaskEntry -> uuidTaskEntry.getValue().canRun()).forEach(uuidTaskEntry -> {
            uuidTaskEntry.getValue().task.run();
            DELAYED_TASK.remove(uuidTaskEntry.getKey());
        });
    }

    public static class Task {
        int ticksRemaining;
        Runnable task;

        Task(int ticksRemaining, Runnable task) {
            this.ticksRemaining  = ticksRemaining;
            this.task = task;
        }

        public boolean canRun() {
            return --this.ticksRemaining <= 0;
        }
    }
}
