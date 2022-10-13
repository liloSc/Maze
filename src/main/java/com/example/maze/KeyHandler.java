package com.example.maze;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class KeyHandler implements EventHandler {

    public boolean upPressed, downPressed, leftPressed, rightPressed;

    @Override
    public void handle(Event event) {
       /* if (KeyEvent.KEY_PRESSED.equals(event.getEventType())) {
         //   activeKeys.add(event.getCode());
        } else if (KeyEvent.KEY_RELEASED.equals(event.getEventType())) {
           // activeKeys.remove(event.getCode());
        }*/
        if (KeyEvent.KEY_PRESSED.equals(event.getEventType())) {
            if (((KeyEvent) event).getCode() == KeyCode.UP) {
                System.out.println("Key: UP");
                upPressed = true;
            }
            if (((KeyEvent) event).getCode() == KeyCode.DOWN) {
                System.out.println("Key: Down");
                downPressed = true;
            }
            if (((KeyEvent) event).getCode() == KeyCode.LEFT) {
                System.out.println("Key: Left");
                leftPressed = true;
            }
            if (((KeyEvent) event).getCode() == KeyCode.RIGHT) {
                System.out.println("Key: Right");
                rightPressed = true;
            }
        } else if (KeyEvent.KEY_RELEASED.equals(event.getEventType())) {
            if (((KeyEvent) event).getCode() == KeyCode.UP) {
                System.out.println("Key: UP");
                upPressed = false;
            }
            if (((KeyEvent) event).getCode() == KeyCode.DOWN) {
                System.out.println("Key: Down");
                downPressed = false;
            }
            if (((KeyEvent) event).getCode() == KeyCode.LEFT) {
                System.out.println("Key: Left");
                leftPressed = false;
            }
            if (((KeyEvent) event).getCode() == KeyCode.RIGHT) {
                System.out.println("Key: Right");
                rightPressed = false;
            }
        }

    }


}
