package getwreckt.cs2340.rattrack.model;

/**
 * Created by maya v on 9/21/2017.
 */

public class Model {
    private Model() {
        loadDummyData();
    }

    private void loadDummyData() {
        UserList.addUser("user", "pass");
    }

}
