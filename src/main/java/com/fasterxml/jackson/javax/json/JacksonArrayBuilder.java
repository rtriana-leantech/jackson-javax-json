package com.fasterxml.jackson.javax.json;

import java.math.*;

import javax.json.*;

import com.fasterxml.jackson.databind.node.*;

public class JacksonArrayBuilder implements JsonArrayBuilder {
    private final JsonNodeFactory _jsonNodeFactory;
    private final NodeFactory _nodeFactory;
    private final ArrayNode _delegate;

    public JacksonArrayBuilder(JsonNodeFactory jsonNodeFactory, NodeFactory nodeFactory) {
        _jsonNodeFactory = jsonNodeFactory;
        _nodeFactory = nodeFactory;
        _delegate = jsonNodeFactory.arrayNode();
    }

    public ArrayNode delegate() {
        return _delegate;
    }

    @Override
    public JsonArrayBuilder add(JsonValue value) {
        if (value == JsonValue.NULL) {
            _delegate.addNull();
        } else if (value == JsonValue.FALSE) {
            _delegate.add(false);
        } else if (value == JsonValue.TRUE) {
            _delegate.add(true);
        } else if (value instanceof JacksonValue) {
            _delegate.add(((JacksonValue<?>) value).delegate());
        } else throw new UnsupportedOperationException("No compatibility with other implementation yet.");

        return this;
    }

    @Override
    public JsonArrayBuilder add(String value) {
        _delegate.add(value);
        return this;
    }

    @Override
    public JsonArrayBuilder add(BigDecimal value) {
        _delegate.add(value);
        return this;
    }

    @Override
    public JsonArrayBuilder add(BigInteger value) {
        _delegate.add(_jsonNodeFactory.numberNode(value));
        return this;
    }

    @Override
    public JsonArrayBuilder add(int value) {
        _delegate.add(value);
        return this;
    }

    @Override
    public JsonArrayBuilder add(long value) {
        _delegate.add(value);
        return this;
    }

    @Override
    public JsonArrayBuilder add(double value) {
        _delegate.add(value);
        return this;
    }

    @Override
    public JsonArrayBuilder add(boolean value) {
        _delegate.add(value);
        return this;
    }

    @Override
    public JsonArrayBuilder addNull() {
        _delegate.addNull();
        return this;
    }

    @Override
    public JsonArrayBuilder add(JsonObjectBuilder builder) {
        if (!(builder instanceof JacksonObjectBuilder))
            throw new UnsupportedOperationException("No compatibility with other implementation yet.");
        _delegate.add(((JacksonObjectBuilder) builder).delegate());
        return this;
    }

    @Override
    public JsonArrayBuilder add(JsonArrayBuilder builder) {
        if (!(builder instanceof JacksonArrayBuilder))
            throw new UnsupportedOperationException("No compatibility with other implementation yet.");
        _delegate.add(((JacksonArrayBuilder) builder).delegate());
        return this;
    }

    @Override
    public JsonArray build() {
        return new JacksonArray(_delegate, _nodeFactory);
    }
}