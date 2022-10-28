package com.example.maze;


import javafx.scene.Node;

public class DraggableMaker {

    private double mouseAnchorX;
    private double mouseAnchorY;
    private double startPositionX;
    private double startPositionY;

    public void makeDraggable(Node node, int screen_width /*String typeTile*/) {
        node.setOnMousePressed(mouseEvent -> {
            startPositionX = node.getTranslateX();
            startPositionY = node.getTranslateY();
            mouseAnchorX = mouseEvent.getSceneX() - node.getTranslateX();
            mouseAnchorY = mouseEvent.getSceneY() - node.getTranslateY();

        });

        node.setOnMouseDragged(mouseEvent -> {
            node.setTranslateX(mouseEvent.getSceneX() - mouseAnchorX);
            node.setTranslateY(mouseEvent.getSceneY() - mouseAnchorY);
          /*  if (mouseEvent.getSceneX() > (screen_width / 3)*2) {
                Game.inventory.setStyle("-fx-background-color:rgba(0, 200, 100, 0.2)");
            } else {
                Game.inventory.setStyle("\n" +
                        "-fx-background-radius:1;" +
                        "-fx-border-color:Blue;" +
                        "-fx-font-family: Avenir;" +
                        "-fx-font-weight: bold;" +
                        "-fx-background-color: White;");
            }*/

        });
        node.setOnMouseReleased(mouseEvent -> {
            //If Dragged to Inventory --> Disappear
            if (mouseEvent.getSceneX() > (screen_width / 3) * 2) {
                node.setVisible(false);

              /*  if (typeTile == "vaccine") {
                    Game.label_vaccine.setText(String.valueOf(++Game.found_vaccines));
                    Game.label_vaccine.setVisible(true);
                    Game.image_vaccin.setVisible(true);
                }
                if (typeTile == "test") {
                    Game.label_test.setText(String.valueOf(++Game.found_tests));
                    Game.label_test.setVisible(true);
                    Game.image_test.setVisible(true);
                }*/

            }

            //Otherwise, back to Game
            else {
                node.setTranslateX(startPositionX);
                node.setTranslateY(startPositionY);
            }

          /*  Game.inventory.setStyle("\n" +
                    "-fx-background-radius:1;" +
                    "-fx-border-color:Blue;" +
                    "-fx-font-family: Avenir;" +
                    "-fx-font-weight: bold;" +
                    "-fx-background-color: White;");*/
        });


    }
}

