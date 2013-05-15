package com.github.pgelinas.jackson.javax.json;

import java.util.*;

import javax.json.*;

import com.fasterxml.jackson.databind.*;

public class JacksonBuilderFactory implements JsonBuilderFactory {
    private final ObjectMapper _mapper;
    private final NodeFactory _nodeFactory;

    public JacksonBuilderFactory(Map<String, ?> config) {
        _mapper = new ObjectMapper();
        _nodeFactory = new NodeFactory();
    }

    public JacksonBuilderFactory(ObjectMapper mapper, NodeFactory nodeFactory) {
        _mapper = mapper;
        _nodeFactory = nodeFactory;
    }

    @Override
    public JsonObjectBuilder createObjectBuilder() {
        return new JacksonObjectBuilder(_mapper.getDeserializationConfig().getNodeFactory(), _nodeFactory);
    }

    @Override
    public JsonArrayBuilder createArrayBuilder() {
        return new JacksonArrayBuilder(_mapper.getDeserializationConfig().getNodeFactory(), _nodeFactory);
    }

    @Override
    public Map<String, ?> getConfigInUse() {
        return Collections.emptyMap();
    }
}
