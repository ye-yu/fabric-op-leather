package fabric.mod.planc_.opleather.utils;

import org.apache.logging.log4j.Logger;

import java.util.Optional;
import java.util.function.Supplier;

public class LimitedLogging {
    private final Supplier<Logger> supplier;
    private int count;
    public static final int MAX_TICKS = 20;


    public LimitedLogging(Supplier<Logger> logger) {
        this.supplier = logger;
        this.count = MAX_TICKS - 1;
    }

    public Optional<Logger> get() {
        this.count = Math.min(MAX_TICKS, ++this.count);
        if (this.count == MAX_TICKS) {
            this.count = 0;
            return Optional.of(supplier.get());
        }
        return Optional.empty();
    }
}
