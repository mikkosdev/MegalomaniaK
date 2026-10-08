package megalomaniak;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class AppIT {

    @Test
    void applicationNodeCanBeCreatedFromTheIntegrationTestSourceSet() {
        assertNotNull(new MyNode());
    }
}
