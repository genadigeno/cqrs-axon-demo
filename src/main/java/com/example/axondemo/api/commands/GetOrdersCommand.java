package com.example.axondemo.api.commands;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetOrdersCommand {
    int page;
    int pageSize;

    public GetOrdersCommand(int page, int pageSize) {
        this.page = page;
        this.pageSize = pageSize;
    }
}