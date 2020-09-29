package wtf.retarders.practice.controller;

import com.google.common.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ControllerRegistrar {

    private final List<Controller> handlers = new ArrayList<>();

    public ControllerRegistrar(Controller... handlers) {
        Arrays.stream(handlers).forEach(this::registerHandler);
    }

    /**
     * finds a handler
     *
     * @param handlerClass the class of the handler
     * @param <T>          the handler type
     * @return the handler
     */
    @SuppressWarnings("UnstableApiUsage")
    public <T extends Controller> T findHandler(Class<T> handlerClass) {
        return handlerClass.cast(handlers.stream()
                .filter(handler -> {
                    if (handler.getClass().equals(handlerClass)) {
                        return true;
                    }

                    final TypeToken<?>.TypeSet superClasses = TypeToken.of(handler.getClass()).getTypes().interfaces();

                    return superClasses.stream().anyMatch(typeToken -> typeToken.getRawType().equals(handlerClass) && !typeToken.getRawType().equals(Controller.class));
                })
                .findFirst().orElse(null));
    }

    /**
     * registers a handler
     *
     * @param handler the handler to be registered
     */
    @SuppressWarnings({"UnstableApiUsage", "unchecked"})
    public void registerHandler(Controller handler) {
        final Controller registeredHandler = this.findHandler(handler.getClass());
        final TypeToken<? extends Controller>.TypeSet superClasses = TypeToken.of(handler.getClass()).getTypes().interfaces();


        // unregister possible already registered handlers
        if (registeredHandler != null) {
            this.handlers.remove(registeredHandler);
        }

        if (!superClasses.isEmpty()) {
            superClasses.stream().filter(typeToken -> typeToken.getRawType().equals(Controller.class)).forEach(typeToken -> handlers.remove(this.findHandler((Class<? extends Controller>) typeToken.getRawType())));
        }

        // register the handler
        this.handlers.add(handler);
    }
}