package com.turkcell.rentacar.inventoryService.core.business.abstracts;

public interface MessageService {

    String getMessage(String key);
    String getMessageWithArgs(String key, Object... args);
}
