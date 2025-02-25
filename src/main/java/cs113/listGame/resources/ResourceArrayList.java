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
        resourceObjects.add(resource);
    }

    @Override
    public void remove(ResourceObject resource) {
        resourceObjects.remove(resource);
    }

    @Override
    public void truncate(ResourceObject resource) {
        int indexOfResource = resourceObjects.indexOf(resource);

        // If the element was found, remove all elements from that index onward
        if (indexOfResource != -1) {
            for (int i = resourceObjects.size() - 1; i >= indexOfResource; i--) {
                resourceObjects.remove(i);
            }
        }
    }

    @Override
    public void follow(GameObject leader) {
        for (int i = 0; i < resourceObjects.length(); i++) {
            if (resourceObjects.get(i) != null) {
                resourceObjects.get(i).moveTowards(leader.getEchoCenter());
                leader = resourceObjects.get(i);
            }
        }
    }

    private boolean contains(ResourceObject resource) {
        for (int i = 0; i < resourceObjects.length(); i++) {
            if (resourceObjects.get(i) == resource) return true;
        }
        return false;
    }
}
