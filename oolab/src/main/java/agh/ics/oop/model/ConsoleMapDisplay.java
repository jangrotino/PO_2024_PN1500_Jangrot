package agh.ics.oop.model;

public class ConsoleMapDisplay implements MapChangeListener {
    private int counter = 0;
    public void mapChanged(WorldMap worldMap, String message) {
        System.out.println("id: " + worldMap.getId());
        System.out.println(message);
        System.out.println(worldMap);
        counter++;
        System.out.println("liczba dotychczasowych aktualizacji: " + counter);
    }
}
