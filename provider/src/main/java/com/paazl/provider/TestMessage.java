package com.paazl.provider;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Builder
public class TestMessage implements Serializable {
    private Integer id;
    private String message;
}
