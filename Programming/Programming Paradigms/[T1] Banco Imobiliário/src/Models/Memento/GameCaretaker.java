package Models.Memento;

import java.io.*;

// Caretaker - Gerencia o armazenamento do memento
public class GameCaretaker {
    private static final String SAVE_FILE = "game_state.dat";

    // Salva o memento em um arquivo
    public void saveToDisk(GameStateMemento memento) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SAVE_FILE))) {
            oos.writeObject(memento);
            System.out.println("Estado do jogo salvo em disco.");
        } catch (IOException e) {
            System.err.println("Erro ao salvar o estado do jogo: " + e.getMessage());
        }
    }

    // Carrega o memento de um arquivo
    public GameStateMemento loadFromDisk() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(SAVE_FILE))) {
            GameStateMemento memento = (GameStateMemento) ois.readObject();
            System.out.println("Estado do jogo carregado de disco.");
            return memento;
        } catch (FileNotFoundException e) {
            System.out.println("Nenhum estado salvo encontrado.");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao carregar o estado do jogo: " + e.getMessage());
        }
        return null;
    }
}
