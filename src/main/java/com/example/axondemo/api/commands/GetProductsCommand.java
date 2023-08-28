package com.example.axondemo.api.commands;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetProductsCommand {
    int page;
    int pageSize;

    public GetProductsCommand(int page, int pageSize) {
        this.page = page;
        this.pageSize = pageSize;
    }
}