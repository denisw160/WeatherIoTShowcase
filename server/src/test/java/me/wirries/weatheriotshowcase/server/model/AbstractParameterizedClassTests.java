package me.wirries.weatheriotshowcase.server.model;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * Dies ist ein abstrakter TestCase für Klassentests, die parameterisiert ablaufen.
 *
 * @author Denis Wirries
 * @version 1.0
 * @since 15.08.14
 */
@RunWith(Parameterized.class)
public abstract class AbstractParameterizedClassTests {

    private static final Logger LOG = LoggerFactory.getLogger(AbstractParameterizedClassTests.class);

    /**
     * Parametrisierte Test-Klasse.
     */
    private final Class clazz;

    /**
     * Constructor erstellt den Testfall für die angegebene Klasse.
     *
     * @param clazz Klasse
     */
    AbstractParameterizedClassTests(final Class clazz) {
        this.clazz = clazz;
        LOG.debug("Create test for class {}", clazz);
    }

    /**
     * Liefert die Klasse zurück.
     *
     * @return Klasse für den Test
     */
    Class getClazz() {
        return clazz;
    }

    /**
     * Diese Methode liefert die Klassen für den Test zurück.
     * <p>
     * Sie ist durch eine Parameterisierung mittels
     * <code>Parameterized.Parameters</code> aufzurufen
     *
     * @param ignoredClassNames Ignorierte Klassen
     * @param packageName       Package
     * @return Klassen für den Test
     */
    static Collection<Object[]> getClassesForTest(final String packageName,
                                                  final String[] ignoredClassNames) {

        LOG.debug("Load test classes from package {}", packageName);
        final Class[] classes = getClasses(packageName);

        final Collection<Object[]> result = new ArrayList<Object[]>();
        for (final Class c : classes) {
            if (!c.getSimpleName().toUpperCase().contains("TEST")
                    && !Modifier.isAbstract(c.getModifiers())
                    && !ignored(ignoredClassNames, c.getSimpleName())) {
                result.add(new Object[]{c});
            }
        }

        LOG.debug("Found {} classes for test", result.size());
        return result;
    }

    /**
     * Liefert alle Klassen aus dem Package zurück.
     *
     * @param packageName Package
     * @return Klassen
     */
    private static Class[] getClasses(final String packageName) {
        try {
            return ClassUtils.getClasses(packageName);
        } catch (final IOException e) {
            throw new RuntimeException("Error while getting classes for package " + packageName, e);
        }
    }

    /**
     * Ist die angegebene Klasse ignoriert.
     *
     * @param ignoredClassNames Ignorierte Klassennamen
     * @param className         Klassen-Name
     * @return TRUE, wenn ignoriert
     */
    private static boolean ignored(final String[] ignoredClassNames,
                                   final String className) {

        for (final String i : ignoredClassNames) {
            if (className.equalsIgnoreCase(i))
                return true;
        }

        return false;
    }

    /**
     * Ruft alle (einfachen) Construtoren auf.
     *
     * @param clazz Klasse
     * @param <T>   Typ
     */
    static <T> void callAllConstructors(final Class<T> clazz) {
        LOG.debug("Call all constructors for {}", clazz);
        try {
            assertNotNull("class is null", clazz);
            final Constructor<T> declaredConstructor = clazz.getDeclaredConstructor();
            declaredConstructor.setAccessible(true);
            assertNotNull("new instance is null", newInstance(declaredConstructor));
        } catch (final NoSuchMethodException ex) {
            LOG.debug("Class " + clazz + " has no default constructor", ex);
        }
    }

    /**
     * Ruft alle Standard-Methoden von Object auf.
     *
     * @param clazz Klasse
     * @param <T>   Typ
     */
    @SuppressWarnings({"EqualsWithItself", "ObjectEqualsNull"})
    static <T> void callDefaultObjectMethods(final Class<T> clazz) {
        LOG.debug("Call all constructors for {}", clazz);
        try {
            assertNotNull("class is null", clazz);
            final Constructor<T> declaredConstructor = clazz.getDeclaredConstructor();
            declaredConstructor.setAccessible(true);
            final T newInstance = newInstance(declaredConstructor);
            assertNotNull("new instance is null", newInstance);
            assertNotNull(newInstance.hashCode());
            assertNotNull(newInstance.toString());

            assertNotNull(newInstance);
            assertTrue(newInstance.equals(newInstance));
            assertFalse(newInstance.equals(new Object()));

            final T newInstance2 = newInstance(declaredConstructor);
            assertNotNull("new instance 2 is null", newInstance);
            assertNotNull(newInstance.equals(newInstance2));
        } catch (final NoSuchMethodException ex) {
            LOG.debug("Class {} has no default constructor, Exp: {}", clazz, ex);
        }
    }

    /**
     * Ruft alle Public Static Methoden und Felder auf.
     *
     * @param clazz Klasse
     */
    static void callAllPublicStaticGetters(final Class clazz) {
        final Method[] methods = clazz.getDeclaredMethods();
        assertNotNull(methods);
        for (final Method method : methods) {
            if (!method.getName().toUpperCase().contains("TEST")
                    && Modifier.isPublic(method.getModifiers())
                    && Modifier.isStatic(method.getModifiers())) {
                LOG.debug("call static method {}", method.getName());
                invoke(clazz, method, null);
            }
        }

        final Field[] fields = clazz.getDeclaredFields();
        assertNotNull(fields);
        for (final Field field : fields) {
            if (!field.getName().toUpperCase().contains("TEST")
                    && Modifier.isPublic(field.getModifiers())
                    && Modifier.isStatic(field.getModifiers())) {
                LOG.debug("call static field {}", field.getName());
                get(clazz, field, null);
            }
        }
    }

    /**
     * Ruft alle Public Instanz-Methoden und Felder auf.
     *
     * @param clazz Klasse
     */
    static void callAllPublicGetters(final Class clazz) {
        final Constructor[] constructors = clazz.getConstructors();
        assertNotNull(constructors);

        for (final Constructor constructor : constructors) {
            if (Modifier.isPublic(constructor.getModifiers()) && constructor.getParameterCount() == 0) {
                callAllPublicGetters(clazz, constructor);
            }
        }
    }

    /**
     * Ruft alle Public Instanz-Methoden und Felder auf.
     *
     * @param clazz       Klasse
     * @param constructor Konstruktor
     */
    private static void callAllPublicGetters(final Class clazz, final Constructor constructor) {
        LOG.debug("create new instance for {}", clazz.getSimpleName());
        final Object o = newInstance(constructor);
        assertNotNull("New Instance for constructor " + constructor + " is null", o);

        final Method[] methods = clazz.getDeclaredMethods();
        assertNotNull(methods);
        for (final Method method : methods) {
            if (!method.getName().toUpperCase().contains("TEST")
                    && Modifier.isPublic(method.getModifiers())) {
                LOG.debug("call method {}", method.getName());
                invoke(clazz, method, o);
            }
        }

        final Field[] fields = clazz.getDeclaredFields();
        assertNotNull(fields);
        for (final Field method : fields) {
            if (!method.getName().toUpperCase().contains("TEST")
                    && Modifier.isPublic(method.getModifiers())) {
                LOG.debug("call field {}", method.getName());
                get(clazz, method, o);
            }
        }
    }

    /**
     * Ruft alle Public Instanz-Methoden und Felder auf.
     *
     * @param clazz Klasse
     */
    static void callAllPublicSetters(final Class clazz) {
        final Constructor[] constructors = clazz.getConstructors();
        assertNotNull(constructors);

        for (final Constructor constructor : constructors) {
            if (Modifier.isPublic(constructor.getModifiers()) && constructor.getParameterCount() == 0) {
                LOG.debug("create new instance for {}", clazz.getSimpleName());
                final Object o = newInstance(constructor);
                assertNotNull("New Instance for constructor " + constructor + " is null", o);

                final Method[] methods = clazz.getDeclaredMethods();
                assertNotNull(methods);
                for (final Method method : methods) {
                    callAllPublicSetters(clazz, method, o);
                }
            }
        }
    }

    /**
     * Ruft alle Public Instanz-Methoden und Felder auf.
     *
     * @param clazz  Klasse
     * @param method Methode
     * @param o      Object
     */
    private static void callAllPublicSetters(final Class clazz,
                                             final Method method,
                                             final Object o) {

        if (!method.getName().toUpperCase().contains("TEST")
                && Modifier.isPublic(method.getModifiers())
                && method.getParameterTypes().length == 1) {
            LOG.debug("call method {}", method.getName());
            final Class<?> aClass = method.getParameterTypes()[0];
            if (isDate(aClass))
                invoke(clazz, method, o, new Date());
            else if (isInteger(aClass))
                invoke(clazz, method, o, 1);
            else if (isLong(aClass))
                invoke(clazz, method, o, 1L);
            else if (isDouble(aClass))
                invoke(clazz, method, o, 1.0);
            else if (isBoolean(aClass))
                invoke(clazz, method, o, Boolean.TRUE);
            else
                invoke(clazz, method, o, aClass.cast(null));
        }
    }

    /**
     * Erstellt eine neue Instanz.
     *
     * @param constructor Konstruktor
     * @param <T>         Typ
     * @return neue Instanz
     */
    private static <T> T newInstance(final Constructor<T> constructor) {
        if (constructor.getParameterTypes().length != 0)
            return null;

        try {
            return constructor.newInstance();
        } catch (final Exception e) {
            throw new RuntimeException("Error while creating new Instance", e);
        }
    }

    /**
     * Liefert den Feldinhalt zurück.
     *
     * @param clazz Klasse
     * @param field Feld
     * @param o     Object
     */
    private static void get(final Class clazz, final Field field, final Object o) {
        try {
            field.get(o);
        } catch (final Exception e) {
            throwException(clazz, field.getName(), e);
        }
    }

    /**
     * Führt eine Methode aus.
     *
     * @param clazz  Klasse
     * @param method Methode
     * @param o      Object
     */
    private static void invoke(final Class clazz, final Method method, final Object o) {
        if (method.getParameterTypes().length != 0)
            return;

        try {
            method.invoke(o);
        } catch (final Exception e) {
            throwException(clazz, method.getName(), e);
        }
    }

    /**
     * Führt eine Methode aus.
     *
     * @param clazz  Klasse
     * @param method Methode
     * @param o      Object
     * @param args   Argumente
     */
    private static void invoke(final Class clazz, final Method method, final Object o, final Object... args) {
        if (method.getName().startsWith("__cobertura")) return;
        if (method.getParameterTypes().length != 1) return;

        try {
            method.invoke(o, args);
        } catch (final Exception e) {
            throwException(clazz, method.getName(), e);
        }
    }

    /**
     * Wirft eine Exception.
     *
     * @param clazz  Klasse
     * @param method Methode
     * @param e      Exception
     */
    private static void throwException(final Class clazz, final String method, final Exception e) {
        final String message = e.getClass().getSimpleName() + " thrown in method " + method + " from class " + clazz.getSimpleName();
        LOG.error(message, e);
        throw new RuntimeException(message, e);
    }

    /**
     * List Typ ein Long.
     *
     * @param type Typ
     * @return True oder False
     */
    private static boolean isLong(final Class type) {
        return type == Long.class || type == long.class;
    }

    /**
     * List Typ ein Integer.
     *
     * @param type Typ
     * @return True oder False
     */
    private static boolean isInteger(final Class type) {
        return type == Integer.class || type == int.class;
    }

    /**
     * List Typ ein Double.
     *
     * @param type Typ
     * @return True oder False
     */
    private static boolean isDouble(final Class type) {
        return type == Double.class || type == double.class;
    }

    /**
     * List Typ ein Boolean.
     *
     * @param type Typ
     * @return True oder False
     */
    private static boolean isBoolean(final Class type) {
        return type == Boolean.class || type == boolean.class;
    }

    /**
     * List Typ ein Date.
     *
     * @param type Typ
     * @return True oder False
     */
    private static boolean isDate(final Class type) {
        return type == Date.class;
    }

}
