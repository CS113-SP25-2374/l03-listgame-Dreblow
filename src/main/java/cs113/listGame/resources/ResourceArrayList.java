package cs113.listGame.resources;

import cs113.listGame.ListClasses.ArrayListDAD;
import cs113.listGame.gamecore.GameObject;

public class ResourceArrayList implements ResourceList {
    private ArrayListDAD<ResourceObject> resourceObjects;

    public ResourceArrayList() {
        resourceObjects = new ArrayListDAD<>();
    }

    @Override
    public void add(ResourceObject resource) {
        if (contains(resource)) { return; } // gotcha moment, don't add it twice!
        

    }

    @Override
    public void remove(ResourceObject resource) {

    }

    @Override
    public void truncate(ResourceObject resource) {

    }

    @Override
    public void follow(GameObject leader) {

    }

    private boolean contains(ResourceObject resource) {
        for (int i = 0; i < resourceObjects.length(); i++) {
            if (resourceObjects.get(i) == resource) return true;
        }
        return false;
    }
}
