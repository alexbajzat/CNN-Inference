package com.bjz.cnninference.model;

import com.bjz.cnninference.exceptions.InvalidConfigurationException;
import com.bjz.cnninference.layers.apis.ComplexLayer;
import com.bjz.cnninference.layers.apis.SimpleLayer;
import com.bjz.cnninference.layers.apis.TransitionLayer;

import java.util.ArrayList;
import java.util.List;

/**
 * Brought to life by bjz on 3/4/2018.
 */
public class ModelBuilder {
    private List<SimpleLayer> simpleLayers = new ArrayList<SimpleLayer>();
    private List<ComplexLayer> complexLayers = new ArrayList<ComplexLayer>();
    private TransitionLayer transitionLayer;

    public Model build() {
        if (simpleLayers.isEmpty() || (complexLayers.isEmpty() && simpleLayers.isEmpty())) {
            throw new InvalidConfigurationException("Inconsistent layer configuration");
        }
        if (transitionLayer == null) {
            throw new InvalidConfigurationException("No transition layer present");
        }
        return new Model(simpleLayers, complexLayers, transitionLayer);
    }

    public ModelBuilder addSimpleLayer(SimpleLayer layer) {
        this.simpleLayers.add(layer);
        return this;
    }

    public ModelBuilder addComplexLayer(ComplexLayer layer) {
        this.complexLayers.add(layer);
        return this;
    }

    public ModelBuilder setTransitionLayer(TransitionLayer layer) {
        this.transitionLayer = layer;
        return this;
    }
}
