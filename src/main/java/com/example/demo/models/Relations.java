package com.example.demo.models;

public enum Relations {
    PARENT("Padre"),
    SIBLINGS("Herman@"),
    COUSINS("Prim@"),
    UNCLE("Ti@"),
    NEPHEW("Sobrin@");

    final String relation;

    Relations(final String relation) {
        this.relation = relation;
    }

    public String getRelation() {
        return relation;
    }
}
