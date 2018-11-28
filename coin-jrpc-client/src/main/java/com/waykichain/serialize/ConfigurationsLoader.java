package com.waykichain.serialize;

/**
 * Created at 8/14/16, 22:00.
 *
 * @author <a href="sock.sqt@gmail.com">sockosg</a>
 * @since 1.0
 */
public interface ConfigurationsLoader {

    void setPackageToScan(String setPackageToScan);
    void setPackagesToScan(String... setPackagesToScan);

    void loadConfigurations() throws Exception;

    Configuration getConfigs(String id);

}
