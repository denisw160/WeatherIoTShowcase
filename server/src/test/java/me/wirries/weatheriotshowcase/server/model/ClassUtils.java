package me.wirries.weatheriotshowcase.server.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * Hilfsklasse für den Zugriff auf Klassen.
 *
 * @author Denis.Wirries
 * @version 1.1
 * @since 04.08.2010
 */
public final class ClassUtils {

    private static final Logger LOG = LoggerFactory.getLogger(ClassUtils.class);

    /**
     * suffix = .class (len=6).
     */
    private static final int LEN_SUFFIX = 6;

    /**
     * Nur statische Methoden.
     */
    private ClassUtils() {
    }

    /**
     * Sucht nach allen im Classloader verfügbaren Klassen, die zum angegeben Package passen.
     *
     * @param packageName Packagename
     * @return gefundende Klassen
     * @throws IOException Fehler beim Zugriff auf das Package
     */
    public static Class[] getClasses(final String packageName) throws IOException {
        return getClasses(packageName, false);
    }

    /**
     * Sucht nach allen im Classloader verfügbaren Klassen, die zum angegeben Package passen.
     *
     * @param packageName Packagename
     * @param recursive   Wenn TRUE, dann werden auch die Subpackages untersucht.
     * @return gefundende Klassen
     * @throws IOException Fehler beim Zugriff auf das Package
     */
    public static Class[] getClasses(final String packageName, final boolean recursive) throws IOException {
        LOG.trace("Scanning classes in package {}", packageName);

        final ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        assert classLoader != null;

        final String path = packageName.replace('.', '/');
        final Enumeration<URL> resources = classLoader.getResources(path);

        final List<File> dirs = new ArrayList<File>();
        while (resources.hasMoreElements()) {
            final URL resource = resources.nextElement();
            dirs.add(new File(resource.getFile()));
        }

        LOG.trace("Found {} directories for package", dirs.size());
        final List<Class> classes = new ArrayList<Class>();
        for (final File directory : dirs) {
            classes.addAll(findClasses(directory, packageName, recursive));
        }

        LOG.trace("Found {} classes in package", classes.size());
        return classes.toArray(new Class[classes.size()]);
    }

    /**
     * Liefert alle Klassen aus dem angegebenen Verzeichnis zurück.
     * Wenn der Parameter recursive auf TRUE gesertr wird, werden auch alle untergeordneten Packages
     * durchsucht.
     *
     * @param directory   Verzeichnis der Klassen
     * @param packageName Package des Verzeichnisses
     * @return gefundende Klassen
     */
    private static List<Class> findClasses(final File directory,
                                           final String packageName,
                                           final boolean recursive) {

        LOG.trace("Scanning classes in directory {}", directory);

        final List<Class> classes = new ArrayList<Class>();
        if (!directory.exists()) {
            return classes;
        }

        final File[] files = directory.listFiles();
        if (files == null)
            return new ArrayList<Class>();

        for (final File file : files) {
            if (file.isDirectory() && recursive) {
                assert !file.getName().contains(".");
                classes.addAll(findClasses(file, packageName + "." + file.getName(), true));
            } else if (file.isFile() && file.getName().endsWith(".class")) {
                addClassForName(classes, packageName, file);
            }
        }

        return classes;
    }

    /**
     * Fügt die Klasse zur Liste der gefundenden Klassen hinzu.
     *
     * @param classes     Liste mit den gefundenden Klassen
     * @param packageName Packagename
     * @param file        Datei der Klasse
     */
    private static void addClassForName(final List<Class> classes, final String packageName, final File file) {
        final String fileName = file.getName();
        final String className = packageName + '.' + fileName.substring(0, fileName.length() - LEN_SUFFIX);
        LOG.trace("Get class for name {}", className);

        try {
            final Class<?> clazz = Class.forName(className);
            classes.add(clazz);
        } catch (final ClassNotFoundException e) {
            LOG.warn("Class {} not found, exp {}", className, e);
        }
    }

}
