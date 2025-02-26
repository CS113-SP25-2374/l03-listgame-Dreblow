package cs113.listGame.enemies;

import java.util.Random;

import cs113.listGame.ListClasses.LinkedListDAD;
import cs113.listGame.behaviors.Behavior;
import cs113.listGame.behaviors.MoveToPoint;
import cs113.listGame.behaviors.Waiting;
import javafx.geometry.Point2D;
import javafx.scene.image.ImageView;

public class EnemyLinkedList extends EnemyObject {
    private final LinkedListDAD<Behavior> enemyBehaviors;
    private final Random random = new Random();

    public EnemyLinkedList(ImageView imageView) { 
        super(imageView); 
        enemyBehaviors = new LinkedListDAD<>();
    }

    @Override
    public void seedBehaviors() {
        Point2D dest = new Point2D(random.nextDouble(500), random.nextDouble(400));

        enemyBehaviors.add(new Waiting(this, 3000));
        enemyBehaviors.add(new MoveToPoint(this, 4000, dest));
    }

    @Override
    public Behavior getNextBehavior() {
        int count = 0;

        for (int i = 0; i < enemyBehaviors.length() && enemyBehaviors.get(i) != null; i++) {
            count++;
        }

        if (count == 0) return null;

        int index = random.nextInt(count);
        Behavior behavior = enemyBehaviors.get(index);
        enemyBehaviors.set(index,null );

        for (int i = 0; i < enemyBehaviors.length() - 1; i++) {
            if (enemyBehaviors.get(i) == null) {
                enemyBehaviors.set(i, enemyBehaviors.get(i + 1));
            }
        }

        // Remove last element (to prevent duplication)
        enemyBehaviors.remove(enemyBehaviors.size() - 1);

        return behavior;
    }
}
