package com.careerconnect.dto;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;

public class HateoasResponse<T> extends RepresentationModel<HateoasResponse<T>> {
    private T data;

    public HateoasResponse(T data) {
        this.data = data;
    }

    public HateoasResponse(T data, List<Link> links) {
        this.data = data;
        this.add(links);
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
} 