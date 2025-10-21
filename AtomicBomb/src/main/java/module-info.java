module AtomicBomb {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires jdk.compiler;
    requires java.desktop;
    exports view;
    opens view to javafx.fxml;
    exports view.menus;
    opens view.menus to javafx.fxml;
    exports view.atomicExplosionAnimation;
    opens view.atomicExplosionAnimation to javafx.fxml;
    exports view.explosionAnimation;
    opens view.explosionAnimation to javafx.fxml;
    exports view.movingAnimation;
    opens view.movingAnimation to javafx.fxml;
    exports view.shootingAnimation;
    opens view.shootingAnimation to javafx.fxml;
}