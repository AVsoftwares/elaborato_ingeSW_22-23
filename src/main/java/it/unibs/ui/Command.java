package it.unibs.ui;

import java.util.Scanner;

@FunctionalInterface
public interface Command {
    void onSelection();
}
