package com.example.game;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class BubblePopGame extends Application {
    private Stage primaryStage;

    private Pane root;
    private List<Circle> bubbles;
    private int counter;
    private Random random;


    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Bubble Pop Game");

        // Create main menu
        BorderPane mainMenu = createMainMenu();

        // Set main menu as the scene
        Scene scene = new Scene(mainMenu, 600, 400);
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    private BorderPane createMainMenu() {
        BorderPane mainMenu = new BorderPane();

        Button startGameButton = new Button("Start Game");
        startGameButton.setOnAction(e -> startGame());

        Button exitButton = new Button("Exit");
        exitButton.setOnAction(e -> primaryStage.close());

        mainMenu.setCenter(startGameButton);
        mainMenu.setBottom(exitButton);

        return mainMenu;
    }

    public void startGame() {

        root = new Pane();
        Scene scene = new Scene(root, 600, 400);
        primaryStage.setScene(scene);
        bubbles = new ArrayList<>();
        random = new Random();

        scene.setOnMouseClicked(this::handleMouseClick);


        createBubbles();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                update(now);
            }
        };
        timer.start();
    }

    private void createBubbles() {
        for (int i = 0; i < 10; i++) {
            spawnBubble();
        }
    }

    private void spawnBubble() {
        Circle bubble = new Circle(0, Color.BLUE);
        bubble.setCenterX(random.nextInt((int) (root.getWidth() - 40)) + 20);
        bubble.setCenterY(random.nextInt((int) (root.getHeight() - 40)) + 20);

        bubbles.add(bubble);
        root.getChildren().add(bubble);
    }

    private void handleMouseClick(MouseEvent event) {
        double x = event.getX();
        double y = event.getY();

        Iterator<Circle> iterator = bubbles.iterator();
        while (iterator.hasNext()) {
            Circle bubble = iterator.next();
            if (bubble.getBoundsInParent().contains(x, y)) {
                counter++;
                System.out.println(counter);
                iterator.remove();
                root.getChildren().remove(bubble);
                spawnBubble();
            }
        }
    }


    private void update(long now) {
        if (now % 10000 == 0) {
            System.out.println("Now=" + now);
        }
        for (Circle bubble : bubbles) {
            bubble.setCenterX(bubble.getCenterX() + random.nextInt(5) - 2);
            bubble.setCenterY(bubble.getCenterY() + random.nextInt(5) - 2);
            double newSize = Math.min(20, bubble.getRadius() + 0.3);
            bubble.setRadius(newSize);


            // Ensure bubbles stay within the window boundaries
            double maxX = root.getWidth() - bubble.getRadius();
            double maxY = root.getHeight() - bubble.getRadius();

            double minX = bubble.getRadius();
            double minY = bubble.getRadius();

            if (bubble.getCenterX() > maxX) {
                bubble.setCenterX(maxX);
            }
            if (bubble.getCenterX() < minX) {
                bubble.setCenterX(minX);
            }
            if (bubble.getCenterY() > maxY) {
                bubble.setCenterY(maxY);
            }
            if (bubble.getCenterY() < minY) {
                bubble.setCenterY(minY);
            }
        }
    }
}

