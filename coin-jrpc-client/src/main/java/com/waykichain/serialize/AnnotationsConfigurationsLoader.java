package com.waykichain.serialize;

import com.waykichain.serialize.annotations.MethodConfiguration;
import com.waykichain.serialize.annotations.MethodsConfiguration;
import com.waykichain.utils.ArrayUtils;
import com.waykichain.utils.ClassFinder;
import com.waykichain.utils.CollectionUtils;
import com.waykichain.utils.StringUtils;
import lombok.Setter;

import java.util.List;

/**
 *
 * Created at 8/14/16, 22:02.
 * @author <a href="sock.sqt@gmail.com">sockosg</a>
 * @since 1.0
 */
public class AnnotationsConfigurationsLoader implements ConfigurationsLoader {

    private String packageToScan;
    private String[] packagesToScan;

    @Setter
    private ConfigurationsReader reader;

    public AnnotationsConfigurationsLoader() {
        super();
        this.reader = new ConfigurationsReader();
    }

    public AnnotationsConfigurationsLoader(ConfigurationsReader reader) {
        super();
        this.reader = reader;
    }

    public void setPackageToScan(String packageToScan) {
        this.packageToScan = packageToScan;
    }

    public void setPackagesToScan(String... packagesToScan) {
        this.packagesToScan = packagesToScan;
    }

    public void loadConfigurations() throws Exception {
        if (StringUtils.isEmpty(this.packageToScan) && ArrayUtils.isEmpty(this.packagesToScan)) {
            throw new Exception();
        }

        if (StringUtils.isNotEmpty(this.packageToScan)) {
            List<Class> classes = ClassFinder.getClasses(this.packageToScan);

            if (CollectionUtils.isEmpty(classes)) {
                // TODO : print warning that there are no packages configured
                return;
            }

            int[] configs = {0};
            classes.forEach(clazz -> {
                if (clazz.isAnnotationPresent(MethodsConfiguration.class) || clazz.isAnnotationPresent(MethodConfiguration.class)) {
                    this.createConfiguration(clazz);
                    configs[0]++;
                }
            });

            if (configs[0] == 0) {
                throw new Exception();
            }
        }
    }

    @Override
    public Configuration getConfigs(String id) {
        return this.reader.getConfigs().get(id);
    }

    private void createConfiguration(Class clazz) {
        if (clazz.isAnnotationPresent(MethodsConfiguration.class)) {
            this.reader.createComplexConfiguration(clazz);
        } else if (clazz.isAnnotationPresent(MethodConfiguration.class)) {
            this.reader.createSimpleConfiguration(clazz);
        }
    }

}
