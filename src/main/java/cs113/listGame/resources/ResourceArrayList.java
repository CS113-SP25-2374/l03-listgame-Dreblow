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
        if (resourceObjects.contains(resource)) { return; } // gotcha moment, don't add it twice!
        resourceObjects.add(resource);
    }

    @Override
    public void remove(ResourceObject resource) {
        resourceObjects.remove(resource);
    }

    @Override
    public void truncate(ResourceObject resource) {
        int index = resourceObjects.indexOf(resource);
        if (index != -1) {
            while (resourceObjects.size() > index) {
                resourceObjects.remove(index); // Always remove from `index`
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
}
