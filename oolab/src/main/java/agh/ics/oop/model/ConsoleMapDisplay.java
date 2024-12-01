package agh.ics.oop.model;

public class ConsoleMapDisplay implements MapChangeListener {
    private int counter = 0;
    public void mapChanged(WorldMap worldMap, String message) {
        synchronized (System.out) {
            counter++;
            System.out.println("id: " + worldMap.getId());
            System.out.println(message);
            System.out.println(worldMap);
            System.out.println("liczba dotychczasowych aktualizacji: " + counter);
            System.out.println();
        }
    }
}
