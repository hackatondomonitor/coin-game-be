package com.hackatonalcoolico.coingame.config.common;

import java.util.List;

public interface Page<T> {

  List<T> getItems();

  int getSize();

  int getNumber();

  int getTotalItems();

  int getTotalPages();

}
