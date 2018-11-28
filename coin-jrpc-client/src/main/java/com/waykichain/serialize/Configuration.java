package com.waykichain.serialize;

import com.waykichain.serialize.annotations.SolidityType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * <p><b>Created:</b> 15/08/16, 11:48 AM</p>
 * @author <a href="mailto:sock.sqt@gmail.com">samuel</a>
 * @since 1.0
 */
public class Configuration {

    @Getter
    private Definition in;
    @Getter
    private Definition out;

    public Configuration() {
        super();
        this.in = new Definition();
        this.out = new Definition();
    }

    public void addInParameter(String attributeName, SolidityType solidityType) {
        this.in.getParameters().add(new Parameter(attributeName, solidityType));
    }

    public void addOutParameter(String attributeName, SolidityType solidityType) {
        this.out.getParameters().add(new Parameter(attributeName, solidityType));
    }

    /**
     *
     * <p><b>Created:</b> 15/08/16, 11:49 AM</p>
     * @author <a href="mailto:sock.sqt@gmail.com">samuel</a>
     * @since 0.1.0
     */
    @Data
    @AllArgsConstructor
    class Parameter {

        private String attributeName;
        private SolidityType solidityType;

    }

    /**
     *
     * <p><b>Created:</b> 15/08/16, 11:49 AM</p>
     * @author <a href="mailto:sock.sqt@gmail.com">samuel</a>
     * @since 0.1.0
     */
    public class Definition {

        @Setter @Getter
        private String definition;
        @Setter @Getter
        private String keccak;
        @Getter
        private List<Parameter> parameters;

        public Definition() {
            super();
            this.parameters = new ArrayList<>();
        }

    }

}
