package com.waykichain.utils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * Created at 8/14/16, 23:05.
 * @author <a href="sock.sqt@gmail.com">sockosg</a>
 * @since 1.0
 */
public class ClassFinder {

    /**
     * Scans all classes accessible from the context class loader which belong to the given package and subpackages.
     * @param packageName - The base package
     * @return The classes
     * @throws ClassNotFoundException
     */
    public static List<Class> getClasses(String packageName) throws ClassNotFoundException {
        List<Class> classes = new ArrayList<>();
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        assert classLoader != null;
        String path = packageName.replace('.', '/');
        Enumeration<URL> resources = null;

        try {
            resources = classLoader.getResources(path);
        } catch (IOException ioExc) {
            // TODO : Print warning
            return classes;
        }

        List<File> dirs = new ArrayList<>();
        while (resources.hasMoreElements()) {
            URL resource = resources.nextElement();
            dirs.add(new File(resource.getFile()));
        }
        for (File directory : dirs) {
            classes.addAll(findClasses(directory, packageName));
        }
        return classes;
    }

    /**
     * Recursive method used to find all classes in a given directory and subdirs.
     * @param directory - The base directory
     * @param packageName - The package name for classes found inside the base directory
     * @return The classes
     * @throws ClassNotFoundException
     */
    public static List<Class> findClasses(File directory, String packageName) throws ClassNotFoundException {
        List<Class> classes = new ArrayList<>();
        if (!directory.exists()) {
            return classes;
        }
        File[] files = directory.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                assert !file.getName().contains(".");
                classes.addAll(findClasses(file, packageName + "." + file.getName()));
            } else if (file.getName().endsWith(".class")) {
                classes.add(Class.forName(packageName + '.' + file.getName().replace(".class", "")));
            }
        }
        return classes;
    }

}
