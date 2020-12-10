package com.example.demo.simplify;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.SerializableString;
import com.fasterxml.jackson.core.util.DefaultIndenter;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class GalvanizePrettyPrinter extends DefaultPrettyPrinter {

    GalvanizePrettyPrinter(GalvanizePrettyPrinter base,
                         SerializableString rootSeparator)
    {
        super(base, rootSeparator);
    }

    public GalvanizePrettyPrinter(GalvanizePrettyPrinter galvanizePrettyPrinter) {
        this(galvanizePrettyPrinter,galvanizePrettyPrinter._rootSeparator);
    }

    public GalvanizePrettyPrinter(SerializableString rootSeperator) {
        super(rootSeperator);
    }

    @Override
    public GalvanizePrettyPrinter createInstance() {
            return new GalvanizePrettyPrinter(this);
    }

    @Override
    public void writeObjectFieldValueSeparator(JsonGenerator g) throws IOException {
        // TODO:  ALL OF THIS FOR THE SEPERATOR BELOW, IS THERE A BETTER WAY?
        g.writeRaw(": ");
    }
}
