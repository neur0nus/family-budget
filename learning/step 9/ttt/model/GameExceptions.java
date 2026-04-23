package ttt.model;

public class GameExceptions extends Exception {
    
    public GameExceptions(String message) {
        super(message);
    }
    
    public static GameExceptions emptyName() {
        return new GameExceptions("Ошибка! Имя не может быть пустым.");
    }
    
    public static GameExceptions invalidChoice() {
        return new GameExceptions("Ошибка! Введите 1 (да) или 2 (нет).");
    }
    
    public static GameExceptions notNumber() {
        return new GameExceptions("Ошибка! Введите число.");
    }
    
    public static GameExceptions outOfRange() {
        return new GameExceptions("Ошибка! Число должно быть от 1 до 9.");
    }
    
    public static GameExceptions cellOccupied(int cell) {
        return new GameExceptions("Ошибка! Клетка " + cell + " уже занята.");
    }
    
    public static GameExceptions gameOver() {
        return new GameExceptions("Игра уже закончена!");
    }
}