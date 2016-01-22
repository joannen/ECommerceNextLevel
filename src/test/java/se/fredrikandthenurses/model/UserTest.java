package se.fredrikandthenurses.model;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by joanne on 21/12/15.
 */
public class UserTest {

    @Test
    public void testEquals() throws Exception {
        User user = new User("joanne", "password");
        User user1 = new User("joanne", "password");
        assertEquals(user, user1);
    }

    @Test
    public void testHashCode() throws Exception {
        User user = new User("anna", "123");
        User user1 = new User("anna", "123");
        assertEquals(user, user1);
        assertEquals(user.hashCode(), user1.hashCode());
    }
}