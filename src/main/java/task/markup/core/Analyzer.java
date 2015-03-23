package task.markup.core;

/**
 * Created by user on 19.03.15.
 */
public interface Analyzer<T, R> {

    static final String ERROR_MSG = "Can`t handle";

    default R handle(T matter) {
        if (canAnalyze(matter)) {
            return analyze(matter);
        } else throw new RuntimeException(ERROR_MSG);
    }

    boolean canAnalyze(T matter);

    R analyze(T matter);

}
