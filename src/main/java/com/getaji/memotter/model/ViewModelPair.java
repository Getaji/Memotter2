package com.getaji.memotter.model;

import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * Created by Getaji on 2015/09/23.
 */
public class ViewModelPair<V, VM> {
    private final V view;
    private final VM viewModel;

    public ViewModelPair(V view, VM viewModel) {
        this.view = view;
        this.viewModel = viewModel;
    }

    public ViewModelPair(Function<VM, V> viewFactory, VM viewModel) {
        this.view = viewFactory.apply(viewModel);
        this.viewModel = viewModel;
    }

    public <VP> ViewModelPair(BiFunction<VM, VP, V> viewFactory, VM viewModel, VP viewParam2) {
        this.view = viewFactory.apply(viewModel, viewParam2);
        this.viewModel = viewModel;
    }

    public V getView() {
        return view;
    }

    public VM getViewModel() {
        return viewModel;
    }
}
