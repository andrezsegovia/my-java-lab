import org.junit.BeforeClass;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import java.util.Set;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

public class UserTest {

    private static Validator validator;

    @BeforeClass
    public static void septUpClass() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    public void validUser() {
        User user = new User(
                "Andrez",
                "andrez@email.com",
                asList(1,2));
        Set<ConstraintViolation<User>> cv = validator.validate(user);
        assertTrue(cv.isEmpty());
    }

    @Test
    public void invalidName() {
        User user = new User(
                "",
                "andrez@email.com",
                asList(1,2));
        Set<ConstraintViolation<User>> cv = validator.validate(user);
        assertEquals(1, cv.size());
    }

    @Test
    public void invalidEmail() {
        User user = new User(
                "Andrez",
                "andrez-email-com",
                asList(1,2));
        Set<ConstraintViolation<User>> cv = validator.validate(user);
        assertEquals(1, cv.size());
    }

    @Test
    public void invalidId() {
        User user = new User(
                "Andrez",
                "andrez@email.com",
                asList(-1,-2,1,2));
        Set<ConstraintViolation<User>> cv = validator.validate(user);
        assertEquals(2, cv.size());
    }
}
