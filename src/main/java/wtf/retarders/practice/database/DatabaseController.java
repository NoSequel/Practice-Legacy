package wtf.retarders.practice.database;

import wtf.retarders.practice.controller.Controller;
import wtf.retarders.practice.database.handler.DataHandler;
import wtf.retarders.practice.database.options.DatabaseOption;
import wtf.retarders.practice.database.type.DataType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DatabaseController implements Controller {

    private final DataType<?, ?> type;
    private final DatabaseOption option;

    private DataHandler dataHandler;

    /**
     * Constructor for creating a new DatabaseController
     *
     * @param option      the options of the database
     * @param type        the type of the data
     */
    public DatabaseController(DatabaseOption option, DataType<?, ?> type) {
        this.type = type;
        this.option = option;
    }
}