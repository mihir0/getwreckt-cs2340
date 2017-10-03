package getwreckt.cs2340.rattrack.model;

/**
 * Created by maya v on 9/21/2017.
 */

public class Model {
    private static final Model _instance = new Model();

    /**
     * The private Model instance.
     * @return the private Model instance
     */
    public static Model getInstance() {return _instance;}

    /**
     * Creates a Model with dummy data.
     */
    private Model() {
        loadDummyData();
    }

    private User currentUser;

    /**
     * Adds a sample user to the Model.
     */
    private void loadDummyData() {
        UserList.addUser(new User("user", "password"));
    }

    /**
     * Current user in the system.
     * @return current user
     */
    public User getCurrentUser() {return this.currentUser;}

    /**
     * Sets the current user to {@code currentUser}.
     * @param currentUser the user to set as the current user
     */
    public void setCurrentUser(User currentUser) {this.currentUser = currentUser;}

}
