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
    private User currentUser;

    private void loadDummyData() {
        UserList.addUser(new User("user", "password"));
    }

    private User getCurrentUser() {return this.currentUser;}

    private void setCurrentUser(User currentUser) {this.currentUser = currentUser;}

}
