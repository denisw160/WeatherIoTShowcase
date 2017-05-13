package me.wirries.weatheriotshowcase.server;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

/**
 * Dies ist eine abstrakte Test-Methode, um Model-Klassen zu testen.
 * Die Testfälle müssen mit der Annotation <code>@RunWith(Parameterized.class)</code>
 * versehen werden. (Optional)
 * Für die Durchführung des Tests muss einfach noch eine Methode implementiert werden,
 * die die zu testenden Klassen zurückliefert. Ein Besipiel findet sich ModelsTests.
 * (Die Methode wird mit der Annotation <code>@Parameterized.Parameters(name = "Modell-Test: \{0\}")</code>
 * versehen.)
 *
 * @author Denis Wirries
 * @version 1.0
 * @since 03.07.2016
 */
@RunWith(Parameterized.class)
public abstract class AbstractModelsTests extends AbstractParameterizedClassTests {

    /**
     * Construktur für den Testfall.
     *
     * @param clazz Klasse für den Test
     */
    public AbstractModelsTests(final Class clazz) {
        super(clazz);
    }

    /**
     * Führt den Test für die Utility-Klasse durch.
     */
    @Test
    public void testModel() {
        callAllConstructors(getClazz());
        callAllPublicGetters(getClazz());
        callAllPublicSetters(getClazz());
        callAllPublicStaticGetters(getClazz());
        callDefaultObjectMethods(getClazz());
    }

}
