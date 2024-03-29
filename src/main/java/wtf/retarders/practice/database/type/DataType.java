package wtf.retarders.practice.database.type;

import wtf.retarders.practice.data.DataController;
import wtf.retarders.practice.data.Loadable;

public interface DataType<T, C> {

    /**
     * Load a lodable from the data structure
     *
     * @param object   the object to load it from
     * @param loadable the loadable
     */
    void load(T object, DataController<?, ?> controller, Loadable<?> loadable);

    /**
     * Save a lodable to the data structure
     *
     * @param loadable   the loadable
     * @param object     the object to save it in
     * @param collection the collection to save the object in
     */
    void save(C collection, T object, Loadable<?> loadable);

}
