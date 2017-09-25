package getwreckt.cs2340.rattrack.model;

/**
 * Created by maya v on 9/21/2017.
 */

public class Model {
    private static final Model _instance = new Model();
    public static Model getInstance() {return _instance;}
    private Model() {
        loadDummyData();
    }

    private void loadDummyData() {
        UserList.addUser("user", CryptHash.hash("pass"));
    }

}
