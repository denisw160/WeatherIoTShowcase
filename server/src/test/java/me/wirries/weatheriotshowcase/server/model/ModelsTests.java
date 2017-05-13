package me.wirries.weatheriotshowcase.server.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Collection;

/**
 * Dies ist eine Implementierung für einen Test der Modell-Klassen.
 *
 * @author Denis Wirries
 * @version 1.0
 * @since 15.08.14
 */
@RunWith(Parameterized.class)
public class ModelsTests extends AbstractModelsTests {

    /**
     * Findet die Klassen für den Test.
     *
     * @return Testklassen
     */
    @Parameterized.Parameters(name = "Modell-Test: {0}")
    public static Collection<Object[]> getClassesForTest() {
        return getClassesForTest("me.wirries.weatheriotshowcase.server.model", new String[]{});
    }

    /**
     * Construktur für den Testfall.
     *
     * @param clazz Klasse für den Test
     */
    public ModelsTests(final Class clazz) {
        super(clazz);
    }

    @Test
    public void testMarker() throws Exception {
        // All test in abstract class
    }

}
