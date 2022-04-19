package fabric.mod.planc_.opleather.utils;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.message.EntryMessage;
import org.apache.logging.log4j.message.Message;
import org.apache.logging.log4j.message.MessageFactory;
import org.apache.logging.log4j.util.MessageSupplier;

public class LimitedLogging {
    private final Logger logger;
    private int count;
    public static final int MAX_TICKS = 20;
    public static final Logger DUMMY = new DummyLogger();

    public LimitedLogging(Logger logger) {
        this.logger = logger;
        this.count = MAX_TICKS - 1;
    }

    public Logger getLogger() {
        this.count = Math.min(MAX_TICKS, ++this.count);
        if (this.count == MAX_TICKS) {
            this.count = 0;
            return logger;
        }
        return DUMMY;
    }
}

class DummyLogger implements Logger {

    @Override
    public void catching(Level level, Throwable throwable) {

    }

    @Override
    public void catching(Throwable throwable) {

    }

    @Override
    public void debug(Marker marker, Message message) {

    }

    @Override
    public void debug(Marker marker, Message message, Throwable throwable) {

    }

    @Override
    public void debug(Marker marker, MessageSupplier messageSupplier) {

    }

    @Override
    public void debug(Marker marker, MessageSupplier messageSupplier, Throwable throwable) {

    }

    @Override
    public void debug(Marker marker, CharSequence message) {

    }

    @Override
    public void debug(Marker marker, CharSequence message, Throwable throwable) {

    }

    @Override
    public void debug(Marker marker, Object message) {

    }

    @Override
    public void debug(Marker marker, Object message, Throwable throwable) {

    }

    @Override
    public void debug(Marker marker, String message) {

    }

    @Override
    public void debug(Marker marker, String message, Object... params) {

    }

    @Override
    public void debug(Marker marker, String message, org.apache.logging.log4j.util.Supplier<?>... paramSuppliers) {

    }

    @Override
    public void debug(Marker marker, String message, Throwable throwable) {

    }

    @Override
    public void debug(Marker marker, org.apache.logging.log4j.util.Supplier<?> messageSupplier) {

    }

    @Override
    public void debug(Marker marker, org.apache.logging.log4j.util.Supplier<?> messageSupplier, Throwable throwable) {

    }

    @Override
    public void debug(Message message) {

    }

    @Override
    public void debug(Message message, Throwable throwable) {

    }

    @Override
    public void debug(MessageSupplier messageSupplier) {

    }

    @Override
    public void debug(MessageSupplier messageSupplier, Throwable throwable) {

    }

    @Override
    public void debug(CharSequence message) {

    }

    @Override
    public void debug(CharSequence message, Throwable throwable) {

    }

    @Override
    public void debug(Object message) {

    }

    @Override
    public void debug(Object message, Throwable throwable) {

    }

    @Override
    public void debug(String message) {

    }

    @Override
    public void debug(String message, Object... params) {

    }

    @Override
    public void debug(String message, org.apache.logging.log4j.util.Supplier<?>... paramSuppliers) {

    }

    @Override
    public void debug(String message, Throwable throwable) {

    }

    @Override
    public void debug(org.apache.logging.log4j.util.Supplier<?> messageSupplier) {

    }

    @Override
    public void debug(org.apache.logging.log4j.util.Supplier<?> messageSupplier, Throwable throwable) {

    }

    @Override
    public void debug(Marker marker, String message, Object p0) {

    }

    @Override
    public void debug(Marker marker, String message, Object p0, Object p1) {

    }

    @Override
    public void debug(Marker marker, String message, Object p0, Object p1, Object p2) {

    }

    @Override
    public void debug(Marker marker, String message, Object p0, Object p1, Object p2, Object p3) {

    }

    @Override
    public void debug(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4) {

    }

    @Override
    public void debug(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5) {

    }

    @Override
    public void debug(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6) {

    }

    @Override
    public void debug(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7) {

    }

    @Override
    public void debug(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8) {

    }

    @Override
    public void debug(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8, Object p9) {

    }

    @Override
    public void debug(String message, Object p0) {

    }

    @Override
    public void debug(String message, Object p0, Object p1) {

    }

    @Override
    public void debug(String message, Object p0, Object p1, Object p2) {

    }

    @Override
    public void debug(String message, Object p0, Object p1, Object p2, Object p3) {

    }

    @Override
    public void debug(String message, Object p0, Object p1, Object p2, Object p3, Object p4) {

    }

    @Override
    public void debug(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5) {

    }

    @Override
    public void debug(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6) {

    }

    @Override
    public void debug(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7) {

    }

    @Override
    public void debug(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8) {

    }

    @Override
    public void debug(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8, Object p9) {

    }

    @Override
    public void entry() {

    }

    @Override
    public void entry(Object... params) {

    }

    @Override
    public void error(Marker marker, Message message) {

    }

    @Override
    public void error(Marker marker, Message message, Throwable throwable) {

    }

    @Override
    public void error(Marker marker, MessageSupplier messageSupplier) {

    }

    @Override
    public void error(Marker marker, MessageSupplier messageSupplier, Throwable throwable) {

    }

    @Override
    public void error(Marker marker, CharSequence message) {

    }

    @Override
    public void error(Marker marker, CharSequence message, Throwable throwable) {

    }

    @Override
    public void error(Marker marker, Object message) {

    }

    @Override
    public void error(Marker marker, Object message, Throwable throwable) {

    }

    @Override
    public void error(Marker marker, String message) {

    }

    @Override
    public void error(Marker marker, String message, Object... params) {

    }

    @Override
    public void error(Marker marker, String message, org.apache.logging.log4j.util.Supplier<?>... paramSuppliers) {

    }

    @Override
    public void error(Marker marker, String message, Throwable throwable) {

    }

    @Override
    public void error(Marker marker, org.apache.logging.log4j.util.Supplier<?> messageSupplier) {

    }

    @Override
    public void error(Marker marker, org.apache.logging.log4j.util.Supplier<?> messageSupplier, Throwable throwable) {

    }

    @Override
    public void error(Message message) {

    }

    @Override
    public void error(Message message, Throwable throwable) {

    }

    @Override
    public void error(MessageSupplier messageSupplier) {

    }

    @Override
    public void error(MessageSupplier messageSupplier, Throwable throwable) {

    }

    @Override
    public void error(CharSequence message) {

    }

    @Override
    public void error(CharSequence message, Throwable throwable) {

    }

    @Override
    public void error(Object message) {

    }

    @Override
    public void error(Object message, Throwable throwable) {

    }

    @Override
    public void error(String message) {

    }

    @Override
    public void error(String message, Object... params) {

    }

    @Override
    public void error(String message, org.apache.logging.log4j.util.Supplier<?>... paramSuppliers) {

    }

    @Override
    public void error(String message, Throwable throwable) {

    }

    @Override
    public void error(org.apache.logging.log4j.util.Supplier<?> messageSupplier) {

    }

    @Override
    public void error(org.apache.logging.log4j.util.Supplier<?> messageSupplier, Throwable throwable) {

    }

    @Override
    public void error(Marker marker, String message, Object p0) {

    }

    @Override
    public void error(Marker marker, String message, Object p0, Object p1) {

    }

    @Override
    public void error(Marker marker, String message, Object p0, Object p1, Object p2) {

    }

    @Override
    public void error(Marker marker, String message, Object p0, Object p1, Object p2, Object p3) {

    }

    @Override
    public void error(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4) {

    }

    @Override
    public void error(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5) {

    }

    @Override
    public void error(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6) {

    }

    @Override
    public void error(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7) {

    }

    @Override
    public void error(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8) {

    }

    @Override
    public void error(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8, Object p9) {

    }

    @Override
    public void error(String message, Object p0) {

    }

    @Override
    public void error(String message, Object p0, Object p1) {

    }

    @Override
    public void error(String message, Object p0, Object p1, Object p2) {

    }

    @Override
    public void error(String message, Object p0, Object p1, Object p2, Object p3) {

    }

    @Override
    public void error(String message, Object p0, Object p1, Object p2, Object p3, Object p4) {

    }

    @Override
    public void error(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5) {

    }

    @Override
    public void error(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6) {

    }

    @Override
    public void error(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7) {

    }

    @Override
    public void error(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8) {

    }

    @Override
    public void error(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8, Object p9) {

    }

    @Override
    public void exit() {

    }

    @Override
    public <R> R exit(R result) {
        return null;
    }

    @Override
    public void fatal(Marker marker, Message message) {

    }

    @Override
    public void fatal(Marker marker, Message message, Throwable throwable) {

    }

    @Override
    public void fatal(Marker marker, MessageSupplier messageSupplier) {

    }

    @Override
    public void fatal(Marker marker, MessageSupplier messageSupplier, Throwable throwable) {

    }

    @Override
    public void fatal(Marker marker, CharSequence message) {

    }

    @Override
    public void fatal(Marker marker, CharSequence message, Throwable throwable) {

    }

    @Override
    public void fatal(Marker marker, Object message) {

    }

    @Override
    public void fatal(Marker marker, Object message, Throwable throwable) {

    }

    @Override
    public void fatal(Marker marker, String message) {

    }

    @Override
    public void fatal(Marker marker, String message, Object... params) {

    }

    @Override
    public void fatal(Marker marker, String message, org.apache.logging.log4j.util.Supplier<?>... paramSuppliers) {

    }

    @Override
    public void fatal(Marker marker, String message, Throwable throwable) {

    }

    @Override
    public void fatal(Marker marker, org.apache.logging.log4j.util.Supplier<?> messageSupplier) {

    }

    @Override
    public void fatal(Marker marker, org.apache.logging.log4j.util.Supplier<?> messageSupplier, Throwable throwable) {

    }

    @Override
    public void fatal(Message message) {

    }

    @Override
    public void fatal(Message message, Throwable throwable) {

    }

    @Override
    public void fatal(MessageSupplier messageSupplier) {

    }

    @Override
    public void fatal(MessageSupplier messageSupplier, Throwable throwable) {

    }

    @Override
    public void fatal(CharSequence message) {

    }

    @Override
    public void fatal(CharSequence message, Throwable throwable) {

    }

    @Override
    public void fatal(Object message) {

    }

    @Override
    public void fatal(Object message, Throwable throwable) {

    }

    @Override
    public void fatal(String message) {

    }

    @Override
    public void fatal(String message, Object... params) {

    }

    @Override
    public void fatal(String message, org.apache.logging.log4j.util.Supplier<?>... paramSuppliers) {

    }

    @Override
    public void fatal(String message, Throwable throwable) {

    }

    @Override
    public void fatal(org.apache.logging.log4j.util.Supplier<?> messageSupplier) {

    }

    @Override
    public void fatal(org.apache.logging.log4j.util.Supplier<?> messageSupplier, Throwable throwable) {

    }

    @Override
    public void fatal(Marker marker, String message, Object p0) {

    }

    @Override
    public void fatal(Marker marker, String message, Object p0, Object p1) {

    }

    @Override
    public void fatal(Marker marker, String message, Object p0, Object p1, Object p2) {

    }

    @Override
    public void fatal(Marker marker, String message, Object p0, Object p1, Object p2, Object p3) {

    }

    @Override
    public void fatal(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4) {

    }

    @Override
    public void fatal(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5) {

    }

    @Override
    public void fatal(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6) {

    }

    @Override
    public void fatal(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7) {

    }

    @Override
    public void fatal(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8) {

    }

    @Override
    public void fatal(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8, Object p9) {

    }

    @Override
    public void fatal(String message, Object p0) {

    }

    @Override
    public void fatal(String message, Object p0, Object p1) {

    }

    @Override
    public void fatal(String message, Object p0, Object p1, Object p2) {

    }

    @Override
    public void fatal(String message, Object p0, Object p1, Object p2, Object p3) {

    }

    @Override
    public void fatal(String message, Object p0, Object p1, Object p2, Object p3, Object p4) {

    }

    @Override
    public void fatal(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5) {

    }

    @Override
    public void fatal(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6) {

    }

    @Override
    public void fatal(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7) {

    }

    @Override
    public void fatal(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8) {

    }

    @Override
    public void fatal(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8, Object p9) {

    }

    @Override
    public Level getLevel() {
        return null;
    }

    @Override
    public <MF extends MessageFactory> MF getMessageFactory() {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public void info(Marker marker, Message message) {

    }

    @Override
    public void info(Marker marker, Message message, Throwable throwable) {

    }

    @Override
    public void info(Marker marker, MessageSupplier messageSupplier) {

    }

    @Override
    public void info(Marker marker, MessageSupplier messageSupplier, Throwable throwable) {

    }

    @Override
    public void info(Marker marker, CharSequence message) {

    }

    @Override
    public void info(Marker marker, CharSequence message, Throwable throwable) {

    }

    @Override
    public void info(Marker marker, Object message) {

    }

    @Override
    public void info(Marker marker, Object message, Throwable throwable) {

    }

    @Override
    public void info(Marker marker, String message) {

    }

    @Override
    public void info(Marker marker, String message, Object... params) {

    }

    @Override
    public void info(Marker marker, String message, org.apache.logging.log4j.util.Supplier<?>... paramSuppliers) {

    }

    @Override
    public void info(Marker marker, String message, Throwable throwable) {

    }

    @Override
    public void info(Marker marker, org.apache.logging.log4j.util.Supplier<?> messageSupplier) {

    }

    @Override
    public void info(Marker marker, org.apache.logging.log4j.util.Supplier<?> messageSupplier, Throwable throwable) {

    }

    @Override
    public void info(Message message) {

    }

    @Override
    public void info(Message message, Throwable throwable) {

    }

    @Override
    public void info(MessageSupplier messageSupplier) {

    }

    @Override
    public void info(MessageSupplier messageSupplier, Throwable throwable) {

    }

    @Override
    public void info(CharSequence message) {

    }

    @Override
    public void info(CharSequence message, Throwable throwable) {

    }

    @Override
    public void info(Object message) {

    }

    @Override
    public void info(Object message, Throwable throwable) {

    }

    @Override
    public void info(String message) {

    }

    @Override
    public void info(String message, Object... params) {

    }

    @Override
    public void info(String message, org.apache.logging.log4j.util.Supplier<?>... paramSuppliers) {

    }

    @Override
    public void info(String message, Throwable throwable) {

    }

    @Override
    public void info(org.apache.logging.log4j.util.Supplier<?> messageSupplier) {

    }

    @Override
    public void info(org.apache.logging.log4j.util.Supplier<?> messageSupplier, Throwable throwable) {

    }

    @Override
    public void info(Marker marker, String message, Object p0) {

    }

    @Override
    public void info(Marker marker, String message, Object p0, Object p1) {

    }

    @Override
    public void info(Marker marker, String message, Object p0, Object p1, Object p2) {

    }

    @Override
    public void info(Marker marker, String message, Object p0, Object p1, Object p2, Object p3) {

    }

    @Override
    public void info(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4) {

    }

    @Override
    public void info(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5) {

    }

    @Override
    public void info(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6) {

    }

    @Override
    public void info(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7) {

    }

    @Override
    public void info(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8) {

    }

    @Override
    public void info(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8, Object p9) {

    }

    @Override
    public void info(String message, Object p0) {

    }

    @Override
    public void info(String message, Object p0, Object p1) {

    }

    @Override
    public void info(String message, Object p0, Object p1, Object p2) {

    }

    @Override
    public void info(String message, Object p0, Object p1, Object p2, Object p3) {

    }

    @Override
    public void info(String message, Object p0, Object p1, Object p2, Object p3, Object p4) {

    }

    @Override
    public void info(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5) {

    }

    @Override
    public void info(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6) {

    }

    @Override
    public void info(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7) {

    }

    @Override
    public void info(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8) {

    }

    @Override
    public void info(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8, Object p9) {

    }

    @Override
    public boolean isDebugEnabled() {
        return false;
    }

    @Override
    public boolean isDebugEnabled(Marker marker) {
        return false;
    }

    @Override
    public boolean isEnabled(Level level) {
        return false;
    }

    @Override
    public boolean isEnabled(Level level, Marker marker) {
        return false;
    }

    @Override
    public boolean isErrorEnabled() {
        return false;
    }

    @Override
    public boolean isErrorEnabled(Marker marker) {
        return false;
    }

    @Override
    public boolean isFatalEnabled() {
        return false;
    }

    @Override
    public boolean isFatalEnabled(Marker marker) {
        return false;
    }

    @Override
    public boolean isInfoEnabled() {
        return false;
    }

    @Override
    public boolean isInfoEnabled(Marker marker) {
        return false;
    }

    @Override
    public boolean isTraceEnabled() {
        return false;
    }

    @Override
    public boolean isTraceEnabled(Marker marker) {
        return false;
    }

    @Override
    public boolean isWarnEnabled() {
        return false;
    }

    @Override
    public boolean isWarnEnabled(Marker marker) {
        return false;
    }

    @Override
    public void log(Level level, Marker marker, Message message) {

    }

    @Override
    public void log(Level level, Marker marker, Message message, Throwable throwable) {

    }

    @Override
    public void log(Level level, Marker marker, MessageSupplier messageSupplier) {

    }

    @Override
    public void log(Level level, Marker marker, MessageSupplier messageSupplier, Throwable throwable) {

    }

    @Override
    public void log(Level level, Marker marker, CharSequence message) {

    }

    @Override
    public void log(Level level, Marker marker, CharSequence message, Throwable throwable) {

    }

    @Override
    public void log(Level level, Marker marker, Object message) {

    }

    @Override
    public void log(Level level, Marker marker, Object message, Throwable throwable) {

    }

    @Override
    public void log(Level level, Marker marker, String message) {

    }

    @Override
    public void log(Level level, Marker marker, String message, Object... params) {

    }

    @Override
    public void log(Level level, Marker marker, String message, org.apache.logging.log4j.util.Supplier<?>... paramSuppliers) {

    }

    @Override
    public void log(Level level, Marker marker, String message, Throwable throwable) {

    }

    @Override
    public void log(Level level, Marker marker, org.apache.logging.log4j.util.Supplier<?> messageSupplier) {

    }

    @Override
    public void log(Level level, Marker marker, org.apache.logging.log4j.util.Supplier<?> messageSupplier, Throwable throwable) {

    }

    @Override
    public void log(Level level, Message message) {

    }

    @Override
    public void log(Level level, Message message, Throwable throwable) {

    }

    @Override
    public void log(Level level, MessageSupplier messageSupplier) {

    }

    @Override
    public void log(Level level, MessageSupplier messageSupplier, Throwable throwable) {

    }

    @Override
    public void log(Level level, CharSequence message) {

    }

    @Override
    public void log(Level level, CharSequence message, Throwable throwable) {

    }

    @Override
    public void log(Level level, Object message) {

    }

    @Override
    public void log(Level level, Object message, Throwable throwable) {

    }

    @Override
    public void log(Level level, String message) {

    }

    @Override
    public void log(Level level, String message, Object... params) {

    }

    @Override
    public void log(Level level, String message, org.apache.logging.log4j.util.Supplier<?>... paramSuppliers) {

    }

    @Override
    public void log(Level level, String message, Throwable throwable) {

    }

    @Override
    public void log(Level level, org.apache.logging.log4j.util.Supplier<?> messageSupplier) {

    }

    @Override
    public void log(Level level, org.apache.logging.log4j.util.Supplier<?> messageSupplier, Throwable throwable) {

    }

    @Override
    public void log(Level level, Marker marker, String message, Object p0) {

    }

    @Override
    public void log(Level level, Marker marker, String message, Object p0, Object p1) {

    }

    @Override
    public void log(Level level, Marker marker, String message, Object p0, Object p1, Object p2) {

    }

    @Override
    public void log(Level level, Marker marker, String message, Object p0, Object p1, Object p2, Object p3) {

    }

    @Override
    public void log(Level level, Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4) {

    }

    @Override
    public void log(Level level, Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5) {

    }

    @Override
    public void log(Level level, Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6) {

    }

    @Override
    public void log(Level level, Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7) {

    }

    @Override
    public void log(Level level, Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8) {

    }

    @Override
    public void log(Level level, Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8, Object p9) {

    }

    @Override
    public void log(Level level, String message, Object p0) {

    }

    @Override
    public void log(Level level, String message, Object p0, Object p1) {

    }

    @Override
    public void log(Level level, String message, Object p0, Object p1, Object p2) {

    }

    @Override
    public void log(Level level, String message, Object p0, Object p1, Object p2, Object p3) {

    }

    @Override
    public void log(Level level, String message, Object p0, Object p1, Object p2, Object p3, Object p4) {

    }

    @Override
    public void log(Level level, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5) {

    }

    @Override
    public void log(Level level, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6) {

    }

    @Override
    public void log(Level level, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7) {

    }

    @Override
    public void log(Level level, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8) {

    }

    @Override
    public void log(Level level, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8, Object p9) {

    }

    @Override
    public void printf(Level level, Marker marker, String format, Object... params) {

    }

    @Override
    public void printf(Level level, String format, Object... params) {

    }

    @Override
    public <T extends Throwable> T throwing(Level level, T throwable) {
        return null;
    }

    @Override
    public <T extends Throwable> T throwing(T throwable) {
        return null;
    }

    @Override
    public void trace(Marker marker, Message message) {

    }

    @Override
    public void trace(Marker marker, Message message, Throwable throwable) {

    }

    @Override
    public void trace(Marker marker, MessageSupplier messageSupplier) {

    }

    @Override
    public void trace(Marker marker, MessageSupplier messageSupplier, Throwable throwable) {

    }

    @Override
    public void trace(Marker marker, CharSequence message) {

    }

    @Override
    public void trace(Marker marker, CharSequence message, Throwable throwable) {

    }

    @Override
    public void trace(Marker marker, Object message) {

    }

    @Override
    public void trace(Marker marker, Object message, Throwable throwable) {

    }

    @Override
    public void trace(Marker marker, String message) {

    }

    @Override
    public void trace(Marker marker, String message, Object... params) {

    }

    @Override
    public void trace(Marker marker, String message, org.apache.logging.log4j.util.Supplier<?>... paramSuppliers) {

    }

    @Override
    public void trace(Marker marker, String message, Throwable throwable) {

    }

    @Override
    public void trace(Marker marker, org.apache.logging.log4j.util.Supplier<?> messageSupplier) {

    }

    @Override
    public void trace(Marker marker, org.apache.logging.log4j.util.Supplier<?> messageSupplier, Throwable throwable) {

    }

    @Override
    public void trace(Message message) {

    }

    @Override
    public void trace(Message message, Throwable throwable) {

    }

    @Override
    public void trace(MessageSupplier messageSupplier) {

    }

    @Override
    public void trace(MessageSupplier messageSupplier, Throwable throwable) {

    }

    @Override
    public void trace(CharSequence message) {

    }

    @Override
    public void trace(CharSequence message, Throwable throwable) {

    }

    @Override
    public void trace(Object message) {

    }

    @Override
    public void trace(Object message, Throwable throwable) {

    }

    @Override
    public void trace(String message) {

    }

    @Override
    public void trace(String message, Object... params) {

    }

    @Override
    public void trace(String message, org.apache.logging.log4j.util.Supplier<?>... paramSuppliers) {

    }

    @Override
    public void trace(String message, Throwable throwable) {

    }

    @Override
    public void trace(org.apache.logging.log4j.util.Supplier<?> messageSupplier) {

    }

    @Override
    public void trace(org.apache.logging.log4j.util.Supplier<?> messageSupplier, Throwable throwable) {

    }

    @Override
    public void trace(Marker marker, String message, Object p0) {

    }

    @Override
    public void trace(Marker marker, String message, Object p0, Object p1) {

    }

    @Override
    public void trace(Marker marker, String message, Object p0, Object p1, Object p2) {

    }

    @Override
    public void trace(Marker marker, String message, Object p0, Object p1, Object p2, Object p3) {

    }

    @Override
    public void trace(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4) {

    }

    @Override
    public void trace(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5) {

    }

    @Override
    public void trace(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6) {

    }

    @Override
    public void trace(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7) {

    }

    @Override
    public void trace(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8) {

    }

    @Override
    public void trace(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8, Object p9) {

    }

    @Override
    public void trace(String message, Object p0) {

    }

    @Override
    public void trace(String message, Object p0, Object p1) {

    }

    @Override
    public void trace(String message, Object p0, Object p1, Object p2) {

    }

    @Override
    public void trace(String message, Object p0, Object p1, Object p2, Object p3) {

    }

    @Override
    public void trace(String message, Object p0, Object p1, Object p2, Object p3, Object p4) {

    }

    @Override
    public void trace(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5) {

    }

    @Override
    public void trace(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6) {

    }

    @Override
    public void trace(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7) {

    }

    @Override
    public void trace(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8) {

    }

    @Override
    public void trace(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8, Object p9) {

    }

    @Override
    public EntryMessage traceEntry() {
        return null;
    }

    @Override
    public EntryMessage traceEntry(String format, Object... params) {
        return null;
    }

    @Override
    public EntryMessage traceEntry(org.apache.logging.log4j.util.Supplier<?>... paramSuppliers) {
        return null;
    }

    @Override
    public EntryMessage traceEntry(String format, org.apache.logging.log4j.util.Supplier<?>... paramSuppliers) {
        return null;
    }

    @Override
    public EntryMessage traceEntry(Message message) {
        return null;
    }

    @Override
    public void traceExit() {

    }

    @Override
    public <R> R traceExit(R result) {
        return null;
    }

    @Override
    public <R> R traceExit(String format, R result) {
        return null;
    }

    @Override
    public void traceExit(EntryMessage message) {

    }

    @Override
    public <R> R traceExit(EntryMessage message, R result) {
        return null;
    }

    @Override
    public <R> R traceExit(Message message, R result) {
        return null;
    }

    @Override
    public void warn(Marker marker, Message message) {

    }

    @Override
    public void warn(Marker marker, Message message, Throwable throwable) {

    }

    @Override
    public void warn(Marker marker, MessageSupplier messageSupplier) {

    }

    @Override
    public void warn(Marker marker, MessageSupplier messageSupplier, Throwable throwable) {

    }

    @Override
    public void warn(Marker marker, CharSequence message) {

    }

    @Override
    public void warn(Marker marker, CharSequence message, Throwable throwable) {

    }

    @Override
    public void warn(Marker marker, Object message) {

    }

    @Override
    public void warn(Marker marker, Object message, Throwable throwable) {

    }

    @Override
    public void warn(Marker marker, String message) {

    }

    @Override
    public void warn(Marker marker, String message, Object... params) {

    }

    @Override
    public void warn(Marker marker, String message, org.apache.logging.log4j.util.Supplier<?>... paramSuppliers) {

    }

    @Override
    public void warn(Marker marker, String message, Throwable throwable) {

    }

    @Override
    public void warn(Marker marker, org.apache.logging.log4j.util.Supplier<?> messageSupplier) {

    }

    @Override
    public void warn(Marker marker, org.apache.logging.log4j.util.Supplier<?> messageSupplier, Throwable throwable) {

    }

    @Override
    public void warn(Message message) {

    }

    @Override
    public void warn(Message message, Throwable throwable) {

    }

    @Override
    public void warn(MessageSupplier messageSupplier) {

    }

    @Override
    public void warn(MessageSupplier messageSupplier, Throwable throwable) {

    }

    @Override
    public void warn(CharSequence message) {

    }

    @Override
    public void warn(CharSequence message, Throwable throwable) {

    }

    @Override
    public void warn(Object message) {

    }

    @Override
    public void warn(Object message, Throwable throwable) {

    }

    @Override
    public void warn(String message) {

    }

    @Override
    public void warn(String message, Object... params) {

    }

    @Override
    public void warn(String message, org.apache.logging.log4j.util.Supplier<?>... paramSuppliers) {

    }

    @Override
    public void warn(String message, Throwable throwable) {

    }

    @Override
    public void warn(org.apache.logging.log4j.util.Supplier<?> messageSupplier) {

    }

    @Override
    public void warn(org.apache.logging.log4j.util.Supplier<?> messageSupplier, Throwable throwable) {

    }

    @Override
    public void warn(Marker marker, String message, Object p0) {

    }

    @Override
    public void warn(Marker marker, String message, Object p0, Object p1) {

    }

    @Override
    public void warn(Marker marker, String message, Object p0, Object p1, Object p2) {

    }

    @Override
    public void warn(Marker marker, String message, Object p0, Object p1, Object p2, Object p3) {

    }

    @Override
    public void warn(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4) {

    }

    @Override
    public void warn(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5) {

    }

    @Override
    public void warn(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6) {

    }

    @Override
    public void warn(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7) {

    }

    @Override
    public void warn(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8) {

    }

    @Override
    public void warn(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8, Object p9) {

    }

    @Override
    public void warn(String message, Object p0) {

    }

    @Override
    public void warn(String message, Object p0, Object p1) {

    }

    @Override
    public void warn(String message, Object p0, Object p1, Object p2) {

    }

    @Override
    public void warn(String message, Object p0, Object p1, Object p2, Object p3) {

    }

    @Override
    public void warn(String message, Object p0, Object p1, Object p2, Object p3, Object p4) {

    }

    @Override
    public void warn(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5) {

    }

    @Override
    public void warn(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6) {

    }

    @Override
    public void warn(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7) {

    }

    @Override
    public void warn(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8) {

    }

    @Override
    public void warn(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8, Object p9) {

    }
}

