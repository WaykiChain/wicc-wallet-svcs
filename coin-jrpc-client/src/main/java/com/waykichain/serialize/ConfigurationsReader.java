package com.waykichain.serialize;

import com.waykichain.encrypt.KeccakUtils;
import com.waykichain.serialize.annotations.MethodConfiguration;
import com.waykichain.serialize.annotations.MethodsConfiguration;
import com.waykichain.serialize.annotations.SolidityParameter;
import com.waykichain.utils.ArrayUtils;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * <p><b>Created:</b> 15/08/16, 11:38 AM</p>
 * @author <a href="mailto:sock.sqt@gmail.com">samuel</a>
 * @since 1.0
 */
public class ConfigurationsReader {

    @Getter
    private Map<String, Configuration> configs;

    public ConfigurationsReader() {
        super();
        this.configs = new HashMap<>();
    }

    public void createSimpleConfiguration(Class clazz) {
        MethodConfiguration methodConfig = (MethodConfiguration) clazz.getAnnotation(MethodConfiguration.class);
        this.createSimpleConfiguration(methodConfig);
    }

    public void createComplexConfiguration(Class clazz) {
        MethodsConfiguration methodsConfig = (MethodsConfiguration) clazz.getAnnotation(MethodsConfiguration.class);
        for (MethodConfiguration methodConfig : methodsConfig.configurations()) {
            this.createSimpleConfiguration(methodConfig);
        }
    }

    private void createSimpleConfiguration(MethodConfiguration methodConfig) {
        Configuration config = new Configuration();
        this.configs.put(methodConfig.id(), config);

        // Processing IN parameters [methodName(uint,address)]
        if (ArrayUtils.isNotEmpty(methodConfig.inParameters())) {
            StringBuilder def = new StringBuilder(methodConfig.methodName());
            def.append("(");
            String comma = "";

            for (SolidityParameter annotationParameter : methodConfig.inParameters()) {
                def.append(comma);
                comma = ",";
                def.append(annotationParameter.type());

                config.addInParameter(annotationParameter.name(), annotationParameter.type());
            }
            def.append(")");
            final String solvedDefinition = def.toString();

            config.getIn().setDefinition(solvedDefinition);
            config.getIn().setKeccak(KeccakUtils.sha3(solvedDefinition));
        }

        // Processing OUT parameters [(uint,address)]
        if (ArrayUtils.isNotEmpty(methodConfig.outParameters())) {
            StringBuilder def = new StringBuilder();

            def.append("(");

            String comma = "";
            for (SolidityParameter annotationParameter : methodConfig.outParameters()) {
                def.append(comma);
                comma = ",";
                def.append(annotationParameter.type());

                config.addOutParameter(annotationParameter.name(), annotationParameter.type());
            }
            def.append(")");

            config.getOut().setDefinition(def.toString());
        }
    }

}
